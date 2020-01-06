package org.vtrao.listings.listing_mgmt.service;

import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.listing_mgmt.model.Listing;

import java.util.List;

public interface ListingService {
    long insertListing(Listing listing) throws ListingAppException;

    void deleteListing(long listintId, String userId) throws ListingAppException;

    Listing getListing(long listingId) throws ListingAppException;

    List<Listing> getListingByCategory(String categoryId,
                                       SortType sortType,
                                       SortOrder sortOrder,
                                       int limit) throws ListingAppException;
    enum SortType {
        PRICE, CREATIONTIME
    }

    enum SortOrder {
        ASC, DESC
    }
}
