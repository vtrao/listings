package org.vtrao.listings.listing_mgmt.validators;

import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.listing_mgmt.model.Listing;

public class ListingValidator implements Validate {


    @Override
    public void validate(Object input) throws ListingAppException {
        Listing listing = (Listing) input;
        // Validate for length
        if(listing.getCategoryId().length() < ListingValidationConstants.MIN_CHARS_CATEGORY) {
            throw new ListingAppException(ListingValidationConstants.ERROR_LISTING_INPUT_CATEGORY);
        }
        if(listing.getTitle().length() < ListingValidationConstants.MIN_CHARS_TITLE) {
            throw new ListingAppException(ListingValidationConstants.ERROR_LISTING_INPUT_TITLE);
        }
        if(listing.getDescription().length() < ListingValidationConstants.MIN_CHARS_DESCRIPTION) {
            throw new ListingAppException(ListingValidationConstants.ERROR_LISTING_INPUT_DESCRIPTION);
        }
        // TODO validate for content
    }
}
