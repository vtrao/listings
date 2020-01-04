package org.vtrao.listings.listing_mgmt.service.impl;

import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.listing_mgmt.dao.ListingDAO;
import org.vtrao.listings.listing_mgmt.service.ListingService;

public class ListingServiceImpl implements ListingService {
    private ListingDAO listingDAO;
    private Validate listingValidator;

    public ListingServiceImpl(ListingDAO listingDAO, Validate listingValidator) {
        this.listingDAO = listingDAO;
        this.listingValidator = listingValidator;
    }
}
