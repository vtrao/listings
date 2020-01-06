package org.vtrao.listings.listing_mgmt.dao.impl;

import org.vtrao.listings.category_mgmt.dao.CategoryDAO;
import org.vtrao.listings.commons.Utils;
import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.listing_mgmt.ListingConstants;
import org.vtrao.listings.listing_mgmt.dao.ListingDAO;
import org.vtrao.listings.listing_mgmt.model.Listing;
import org.vtrao.listings.listing_mgmt.service.ListingService;
import org.vtrao.listings.category_mgmt.dao.CategoryDAO.UpdateCategoryListingType;
import org.vtrao.listings.listing_mgmt.validators.ListingValidationConstants;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class ListingDAOInMemory implements ListingDAO {
    private static final Logger logger = Logger.getLogger(ListingDAOInMemory.class.getName());

    private Map<Long, Listing> listingData = new HashMap();
    private AtomicLong listingIdCounter = new AtomicLong(100000);
    private CategoryDAO categoryDAO;

    // simple in memory indexes for sort feature
    private Map<String, TreeSet<Listing>> listingByCategoryOnPriceAsc = new HashMap();
    private Map<String, TreeSet<Listing>> listingByCategoryOnPriceDesc = new HashMap();
    private Map<String, TreeSet<Listing>> listingByCategoryByCreationTimeAsc = new HashMap();
    private Map<String, TreeSet<Listing>> listingByCategoryByCreationTimeDesc = new HashMap();

    public ListingDAOInMemory(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
        schedulerForTopCategories();
    }

    @Override
    public long insertListing(Listing listing) throws ListingException {
        if (null == listing) {
            throw new ListingException(ListingConstants.ERROR_LISTING_NULL_INPUT);
        }
        listing.setEnabled(true); // for future use
        listing.setCreationTime(new Date());
        listing.setListingId(listingIdCounter.incrementAndGet());
        listingData.put(listing.getListingId(), listing);
        updateIndexesInsert(listing);

        updateCategoryListings(listing.getCategoryId(), UpdateCategoryListingType.NEW);
        return listing.getListingId();
    }

    @Override
    public void checkListing(Listing inputListing) throws ListingException {
        AtomicReference<Long> existingListingId = new AtomicReference(new Long(-1));
        // this helps for idempotency, need to fine tune
        listingData.forEach( (id,listing)-> {
            if ( listing.getDescription().equalsIgnoreCase(inputListing.getDescription())
            && listing.getTitle().equalsIgnoreCase(inputListing.getTitle())
            && listing.getCategoryId().equalsIgnoreCase(inputListing.getCategoryId())
            && listing.getPrice() == inputListing.getPrice()
            && listing.getUserId().equalsIgnoreCase(inputListing.getUserId())) {
                existingListingId.set(id);
            }
        });
        if ( existingListingId.get() != -1 ) {
            throw new ListingException(ListingValidationConstants.ERROR_LISTING_EXISTS + existingListingId.get());
        }
    }

    @Override
    public void deleteListing(long listingId, String userId) throws ListingException {
        Listing listing = listingData.get(listingId);
        if (null != listing) {
            if (!listing.getUserId().equalsIgnoreCase(userId)) {
                throw new ListingException(ListingConstants.ERROR_LISTING_OWNER_MISMATCH);
            }
            updateIndexesDelete(listing);
            listingData.remove(listingId);

            updateCategoryListings(listing.getCategoryId(), UpdateCategoryListingType.REMOVE);
            return;
        }
        throw new ListingException(ListingConstants.ERROR_LISTING_DOESNT_EXIST);
    }

    @Override
    public Listing getListing(long listingId) throws ListingException {
        Listing listing = listingData.get(listingId);
        if (null != listing && listing.isEnabled()) {
            return listing;
        }
        throw new ListingException(ListingConstants.ERROR_NOT_FOUND);
    }

    @Override
    public List<Listing> getListingByCategory(String categoryId, ListingService.SortType sortType,
                                              ListingService.SortOrder sortOrder, int limit) throws ListingException {
        List<Listing> result = Collections.emptyList();
        Map<String, TreeSet<Listing>> indexToConsider = getIndex(sortType, sortOrder);

        if (limit != ListingConstants.NO_LIMIT_IN_RESULT) {
            result = new ArrayList<>();
            int count = 0;
            for (Listing resultListing : indexToConsider.get(categoryId.toLowerCase())) {
                result.add(resultListing);
                if (++count == limit) {
                    break;
                }
            }
        } else {
            Set<Listing> resultSet = indexToConsider.get(categoryId.toLowerCase());
            if (resultSet == null || resultSet.isEmpty()) {
                throw new ListingException(ListingConstants.ERROR_NOT_FOUND);
            } else {
                result = new ArrayList<>(resultSet);
            }
        }
        return result;

    }


    private void schedulerForTopCategories() {
        if ( false == Utils.getFileRun()) { // For interactive mode the Top Categories would be computed in async batched mode
            updateListingCountsRunnable = new UpdateListingCountsRunnable(categoryDAO, categoryListingCounts);
            updateListingCounterScheduledExecutorService.scheduleAtFixedRate(updateListingCountsRunnable, 1, 5, TimeUnit.SECONDS);
        }
    }

    private Map<String, Integer> categoryListingCounts = new HashMap();
    private UpdateListingCountsRunnable updateListingCountsRunnable;
    private final ScheduledExecutorService updateListingCounterScheduledExecutorService = Executors.newScheduledThreadPool(1);

    class UpdateListingCountsRunnable implements Runnable {
        private CategoryDAO categoryDAO;
        private Map<String, Integer> categoryListingCounts;

        public UpdateListingCountsRunnable(CategoryDAO categoryDAO, Map<String, Integer> categoryListingCounts) {
            this.categoryDAO = categoryDAO;
            this.categoryListingCounts = categoryListingCounts;
        }

        public void run() {
            if(!categoryListingCounts.isEmpty()) {
                logger.info("Top Categories compute: updating " + categoryListingCounts.size() + " categories");
                categoryDAO.updateCategoryListings(categoryListingCounts);
                categoryListingCounts.clear();
                logger.info("Top Categories compute: done ");
            }
        }
    }

    private void updateCategoryListings(String categoryId, UpdateCategoryListingType updateCategoryListingType) {
        // TODO: To Maintain Top Categories: This should be handled asynchronously on create/remove listing
        // Doing it the Push way, periodically using a scheduled task
        Integer listingCount = categoryListingCounts.get(categoryId.toLowerCase());
        if (listingCount == null) {
            switch (updateCategoryListingType) {
                case NEW:
                    listingCount = new Integer(1);
                    break;
                case REMOVE:
                    listingCount = new Integer(-1);
                    break;
            }
        } else {
            switch (updateCategoryListingType) {
                case NEW:
                    ++listingCount;
                    break;
                case REMOVE:
                    --listingCount;
                    break;
            }
        }
        categoryListingCounts.put(categoryId.toLowerCase(), listingCount);
        if ( Utils.getFileRun()) {
            categoryDAO.updateCategoryListings(categoryListingCounts);
            categoryListingCounts.clear();
        }
    }

    private void updateIndexesInsert(Listing listing) {
        String categoryId = listing.getCategoryId().toLowerCase();
        // Indexes for ascending sort
        TreeSet<Listing> listingSetPriceAsc = listingByCategoryOnPriceAsc.get(categoryId);
        if (null == listingSetPriceAsc) {
            listingSetPriceAsc = new TreeSet(new Comparator<Listing>() {
                @Override
                public int compare(Listing l1, Listing l2) {
                    if (l1.getPrice() > l2.getPrice()) {
                        return 1;
                    } else if (l1.getPrice() == l2.getPrice()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
        listingSetPriceAsc.add(listing);
        listingByCategoryOnPriceAsc.put(categoryId, listingSetPriceAsc);

        TreeSet<Listing> listingSetCreationTimeAsc = listingByCategoryByCreationTimeAsc.get(categoryId);
        if (null == listingSetCreationTimeAsc) {
            listingSetCreationTimeAsc = new TreeSet(new Comparator<Listing>() {
                @Override
                public int compare(Listing l1, Listing l2) {
                    if (l1.getCreationTime().compareTo(l2.getCreationTime()) > 0) {
                        return 1;
                    } else if (l1.getCreationTime().compareTo(l2.getCreationTime()) == 0) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
        listingSetCreationTimeAsc.add(listing);
        listingByCategoryByCreationTimeAsc.put(categoryId, listingSetCreationTimeAsc);

        // Indexes for Descending sort
        TreeSet<Listing> listingSetPriceDesc = listingByCategoryOnPriceDesc.get(categoryId);
        if (null == listingSetPriceDesc) {
            listingSetPriceDesc = new TreeSet<Listing>(new Comparator<Listing>() {
                @Override
                public int compare(Listing l1, Listing l2) {
                    if (l1.getPrice() < l2.getPrice()) {
                        return 1;
                    } else if (l1.getPrice() == l2.getPrice()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
        listingSetPriceDesc.add(listing);
        listingByCategoryOnPriceDesc.put(categoryId, listingSetPriceDesc);

        TreeSet<Listing> listingSetCreationTimeDesc = listingByCategoryByCreationTimeDesc.get(categoryId);
        if (null == listingSetCreationTimeDesc) {
            listingSetCreationTimeDesc = new TreeSet<Listing>(new Comparator<Listing>() {
                @Override
                public int compare(Listing l1, Listing l2) {
                    if (l1.getCreationTime().compareTo(l2.getCreationTime()) < 0) {
                        return 1;
                    } else if (l1.getCreationTime().compareTo(l2.getCreationTime()) == 0) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
        listingSetCreationTimeDesc.add(listing);
        listingByCategoryByCreationTimeDesc.put(categoryId, listingSetCreationTimeDesc);
    }

    private void updateIndexesDelete(Listing listing) {
        String categoryId = listing.getCategoryId().toLowerCase();
        // Indexes for ascending sort
        TreeSet<Listing> listingSetPriceAsc = listingByCategoryOnPriceAsc.get(categoryId);
        if (null != listingSetPriceAsc) {
            listingSetPriceAsc.remove(listing);
        }
        TreeSet<Listing> listingSetCreationTimeAsc = listingByCategoryByCreationTimeAsc.get(categoryId);
        if (null != listingSetCreationTimeAsc) {
            listingSetCreationTimeAsc.remove(listing);
        }

        // Indexes for Descending sort
        TreeSet<Listing> listingSetPriceDesc = listingByCategoryOnPriceDesc.get(categoryId);
        if (null != listingSetPriceDesc) {
            listingSetPriceDesc.remove(listing);
        }
        TreeSet<Listing> listingSetCreationTimeDesc = listingByCategoryByCreationTimeDesc.get(categoryId);
        if (null != listingSetCreationTimeDesc) {
            listingSetCreationTimeDesc.remove(listing);
        }
    }

    private Map<String, TreeSet<Listing>> getIndex(ListingService.SortType sortType, ListingService.SortOrder sortOrder) {
        Map<String, TreeSet<Listing>> indexToConsider = null;
        switch (sortType) {
            case PRICE:
                switch (sortOrder) {
                    case ASC:
                        indexToConsider = listingByCategoryOnPriceAsc;
                        break;
                    case DESC:
                        indexToConsider = listingByCategoryOnPriceDesc;
                        break;
                }
                break;
            case CREATIONTIME:
                switch (sortOrder) {
                    case ASC:
                        indexToConsider = listingByCategoryByCreationTimeAsc;
                        break;
                    case DESC:
                        indexToConsider = listingByCategoryByCreationTimeDesc;
                        break;
                }
                break;
        }
        return indexToConsider;
    }

}


