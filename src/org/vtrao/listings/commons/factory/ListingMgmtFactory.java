package org.vtrao.listings.commons.factory;

import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.listing_mgmt.controller.ListingCliController;
import org.vtrao.listings.listing_mgmt.dao.ListingDAO;
import org.vtrao.listings.listing_mgmt.service.ListingService;

public interface ListingMgmtFactory {
    ListingCliController getCliController();
    ListingDAO getDAO();
    ListingService getService();
    Validate getListingValidator();
}
