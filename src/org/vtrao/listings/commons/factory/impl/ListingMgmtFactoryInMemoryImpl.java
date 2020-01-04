package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.commons.factory.AuthenticationFactory;
import org.vtrao.listings.commons.factory.ListingMgmtFactory;
import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.listing_mgmt.controller.ListingCliController;
import org.vtrao.listings.listing_mgmt.controller.impl.ListingCliControllerImpl;
import org.vtrao.listings.listing_mgmt.dao.ListingDAO;
import org.vtrao.listings.listing_mgmt.dao.impl.ListingDAOImpl;
import org.vtrao.listings.listing_mgmt.service.ListingService;
import org.vtrao.listings.listing_mgmt.service.impl.ListingServiceImpl;
import org.vtrao.listings.listing_mgmt.validators.ListingValidator;

public class ListingMgmtFactoryInMemoryImpl implements ListingMgmtFactory {
    AuthenticationFactory authenticationFactory;

    public ListingMgmtFactoryInMemoryImpl(AuthenticationFactory authenticationFactory) {
        this.authenticationFactory = authenticationFactory;
    }

    @Override
    public ListingCliController getCliController() {
        return new ListingCliControllerImpl(authenticationFactory.getAuthentication(), getService());
    }

    @Override
    public ListingDAO getDAO() {
        return new ListingDAOImpl();
    }

    @Override
    public ListingService getService() {
        return new ListingServiceImpl(getDAO(), getListingValidator());
    }

    @Override
    public Validate getListingValidator() {
        return new ListingValidator();
    }
}
