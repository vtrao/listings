package org.vtrao.listings.listing_mgmt.dao;

import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.listing_mgmt.model.Listing;
import org.vtrao.listings.listing_mgmt.service.ListingService;

import java.util.List;

public interface ListingDAO {
    long insertListing(Listing listing) throws ListingException;
    void deleteListing(long listingId, String userId) throws ListingException;
    Listing getListing(long listingId) throws ListingException;
    List<Listing> getListingByCategory(String categoryId,
                                       ListingService.SortType sortType,
                                       ListingService.SortOrder sortOrder,
                                       int limit) throws ListingException;
}
