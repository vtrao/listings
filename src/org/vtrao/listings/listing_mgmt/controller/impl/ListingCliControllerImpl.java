package org.vtrao.listings.listing_mgmt.controller.impl;

import org.vtrao.listings.commons.GlobalConstants;
import org.vtrao.listings.commons.authentication.impl.Authentication;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.response.impl.ListingCliResponse;
import org.vtrao.listings.listing_mgmt.controller.ListingCliController;
import org.vtrao.listings.listing_mgmt.service.ListingService;

public class ListingCliControllerImpl implements ListingCliController {
    private Authentication authentication;
    private ListingService listingService;

    public ListingCliControllerImpl(Authentication authentication,
                                    ListingService listingService) {
        this.authentication = authentication;
        this.listingService = listingService;
    }

    @Override
    public void createListing(String[] inputStrings, ListingCliResponse response) {
        String userName = null;
        if (inputStrings.length > 1 ) {
            userName = inputStrings[1];
        }
        try {
            this.authentication.authenticate(userName);
        } catch (UserException ex) {
            response.setMessage("");
        }
        response.setMessage("TODO: CL");
    }

    @Override
    public void deleteListing(String[] inputStrings, ListingCliResponse response) {
        response.setMessage("TODO: DL");
    }

    @Override
    public void getListingById(String[] inputStrings, ListingCliResponse response) {
        response.setMessage("TODO: GL");
    }

    @Override
    public void getListingByCategory(String[] inputStrings, ListingCliResponse response) {
        response.setMessage("TODO: GC");
    }




    @Override
    public void searchListing(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }
}
