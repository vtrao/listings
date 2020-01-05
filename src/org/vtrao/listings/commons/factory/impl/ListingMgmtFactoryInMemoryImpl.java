package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.commons.factory.AuthenticationFactory;
import org.vtrao.listings.commons.factory.CategoryMgmtFactory;
import org.vtrao.listings.commons.factory.ListingMgmtFactory;
import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.listing_mgmt.controller.ListingCliController;
import org.vtrao.listings.listing_mgmt.controller.impl.ListingCliControllerImpl;
import org.vtrao.listings.listing_mgmt.dao.ListingDAO;
import org.vtrao.listings.listing_mgmt.dao.impl.ListingDAOInMemory;
import org.vtrao.listings.listing_mgmt.service.ListingService;
import org.vtrao.listings.listing_mgmt.service.impl.ListingServiceImpl;
import org.vtrao.listings.listing_mgmt.validators.ListingValidator;

public class ListingMgmtFactoryInMemoryImpl implements ListingMgmtFactory {
    private AuthenticationFactory authenticationFactory;
    private CategoryMgmtFactory categoryMgmtFactory;
    private ListingDAO listingDAO;
    private ListingValidator listingValidator;
    private ListingService listingService;
    private ListingCliController listingCliController;

    public ListingMgmtFactoryInMemoryImpl(AuthenticationFactory authenticationFactory,
                                          CategoryMgmtFactory categoryMgmtFactory) {
        this.authenticationFactory = authenticationFactory;
        this.categoryMgmtFactory = categoryMgmtFactory;
        this.listingDAO = new ListingDAOInMemory();
        this.listingValidator = new ListingValidator();
        this.listingService = new ListingServiceImpl(getDAO(), getListingValidator(), categoryMgmtFactory.getService());
        this.listingCliController = new ListingCliControllerImpl(authenticationFactory.getAuthentication(), getService());
    }

    @Override
    public ListingCliController getCliController() {
        return listingCliController;
    }

    @Override
    public ListingDAO getDAO() {
        return listingDAO;
    }

    @Override
    public ListingService getService() {
        return listingService;
    }

    @Override
    public Validate getListingValidator() {
        return listingValidator;
    }
}
