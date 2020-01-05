package org.vtrao.listings.listing_mgmt.service.impl;

import org.vtrao.listings.category_mgmt.model.Category;
import org.vtrao.listings.category_mgmt.service.CategoryService;
import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.listing_mgmt.ListingConstants;
import org.vtrao.listings.listing_mgmt.dao.ListingDAO;
import org.vtrao.listings.listing_mgmt.model.Listing;
import org.vtrao.listings.listing_mgmt.service.ListingService;

import java.util.List;

public class ListingServiceImpl implements ListingService {
    private ListingDAO listingDAO;
    private CategoryService categoryService;
    private Validate listingValidator;

    public ListingServiceImpl(ListingDAO listingDAO,
                              Validate listingValidator,
                              CategoryService categoryService) {
        this.listingDAO = listingDAO;
        this.listingValidator = listingValidator;
        this.categoryService = categoryService;
    }

    @Override
    public long insertListing(Listing listing) throws ListingAppException {
        if (null == listing) {
            throw new UserException(ListingConstants.ERROR_LISTING_NULL_INPUT);
        }
        listingValidator.validate(listing);
        categoryService.insertCategory(new Category(listing.getCategoryId()));
        return listingDAO.insertListing(listing);
    }

    @Override
    public void deleteListing(long listingId, String userId) throws ListingAppException {
        listingDAO.deleteListing(listingId, userId);
    }

    @Override
    public Listing getListing(long listingId) throws ListingAppException {
        return listingDAO.getListing(listingId);
    }

    @Override
    public List<Listing> getListingByCategory(String categoryId, SortType sortType, SortOrder sortOrder, int limit) throws ListingAppException {
        categoryService.checkCategory(categoryId);
        return listingDAO.getListingByCategory(categoryId, sortType, sortOrder, limit);
    }
}
