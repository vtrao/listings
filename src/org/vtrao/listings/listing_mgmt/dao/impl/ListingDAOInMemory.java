package org.vtrao.listings.listing_mgmt.dao.impl;

import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.listing_mgmt.ListingConstants;
import org.vtrao.listings.listing_mgmt.dao.ListingDAO;
import org.vtrao.listings.listing_mgmt.model.Listing;
import org.vtrao.listings.listing_mgmt.service.ListingService;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ListingDAOInMemory implements ListingDAO {
    private Map<Long, Listing> listingData = new HashMap();
    private AtomicLong listingIdCounter = new AtomicLong(100000);

    // simple in memory indexes for sort feature
    private Map<String, TreeSet<Listing>> listingByCategoryOnPriceAsc = new HashMap();
    private Map<String, TreeSet<Listing>> listingByCategoryOnPriceDesc = new HashMap();
    private Map<String, TreeSet<Listing>> listingByCategoryByCreationTimeAsc = new HashMap();
    private Map<String, TreeSet<Listing>> listingByCategoryByCreationTimeDesc = new HashMap();


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
        return listing.getListingId();
    }

    private void updateIndexesInsert(Listing listing) {
        // Indexes for ascending sort
        TreeSet<Listing> listingSetPriceAsc = listingByCategoryOnPriceAsc.get(listing.getCategoryId().toLowerCase());
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
        listingByCategoryOnPriceAsc.put(listing.getCategoryId().toLowerCase(), listingSetPriceAsc);

        TreeSet<Listing> listingSetCreationTimeAsc = listingByCategoryByCreationTimeAsc.get(listing.getCategoryId().toLowerCase());
        if (null == listingSetCreationTimeAsc) {
            listingSetCreationTimeAsc = new TreeSet(new Comparator<Listing>() {
                @Override
                public int compare(Listing l1, Listing l2) {
                    if (l1.getCreationTime().compareTo(l2.getCreationTime()) > 0) {
                        return 1;
                    } else if (l1.getCreationTime().compareTo(l2.getCreationTime()) == 0){
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
        listingSetCreationTimeAsc.add(listing);
        listingByCategoryByCreationTimeAsc.put(listing.getCategoryId().toLowerCase(), listingSetCreationTimeAsc);

        // Indexes for Descending sort
        TreeSet<Listing> listingSetPriceDesc = listingByCategoryOnPriceDesc.get(listing.getCategoryId().toLowerCase());
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
        listingByCategoryOnPriceDesc.put(listing.getCategoryId().toLowerCase(), listingSetPriceDesc);

        TreeSet<Listing> listingSetCreationTimeDesc = listingByCategoryByCreationTimeDesc.get(listing.getCategoryId().toLowerCase());
        if (null == listingSetCreationTimeDesc) {
            listingSetCreationTimeDesc = new TreeSet<Listing>(new Comparator<Listing>() {
                @Override
                public int compare(Listing l1, Listing l2) {
                    if (l1.getCreationTime().compareTo(l2.getCreationTime()) < 0) {
                        return 1;
                    } else if (l1.getCreationTime().compareTo(l2.getCreationTime()) == 0){
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
        listingSetCreationTimeDesc.add(listing);
        listingByCategoryByCreationTimeDesc.put(listing.getCategoryId().toLowerCase(), listingSetCreationTimeDesc);
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
            return;
        }
        throw new ListingException(ListingConstants.ERROR_LISTING_DOESNT_EXIST);
    }

    private void updateIndexesDelete(Listing listing) {
        // Indexes for ascending sort
        TreeSet<Listing> listingSetPriceAsc = listingByCategoryOnPriceAsc.get(listing.getCategoryId().toLowerCase());
        if( null != listingSetPriceAsc) {
            listingSetPriceAsc.remove(listing);
        }
        TreeSet<Listing> listingSetCreationTimeAsc = listingByCategoryByCreationTimeAsc.get(listing.getCategoryId().toLowerCase());
        if( null != listingSetCreationTimeAsc) {
            listingSetCreationTimeAsc.remove(listing);
        }

        // Indexes for Descending sort
        TreeSet<Listing> listingSetPriceDesc = listingByCategoryOnPriceDesc.get(listing.getCategoryId().toLowerCase());
        if( null != listingSetPriceDesc) {
            listingSetPriceDesc.remove(listing);
        }
        TreeSet<Listing> listingSetCreationTimeDesc = listingByCategoryByCreationTimeDesc.get(listing.getCategoryId().toLowerCase());
        if( null != listingSetCreationTimeDesc) {
            listingSetCreationTimeDesc.remove(listing);
        }
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
            if ( resultSet == null || resultSet.isEmpty()) {
                throw new ListingException(ListingConstants.ERROR_NOT_FOUND);
            } else {
                result = new ArrayList<>(resultSet);
            }
        }
        return result;

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


