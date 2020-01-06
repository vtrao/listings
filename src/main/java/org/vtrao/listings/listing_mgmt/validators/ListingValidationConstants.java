package org.vtrao.listings.listing_mgmt.validators;

import org.vtrao.listings.cli_parser.cli_appfacade.CliFacadeConstants;

public class ListingValidationConstants {
    // Listing validation constants
    public static final String ERROR_LISTING_INPUT_MISSING_CATEGORY = "Input error: missing category";
    public static final String ERROR_LISTING_INPUT_MISSING_PRICE = "Input error: missing price";
    public static final String ERROR_LISTING_INPUT_MISSING_DESCRIPTION = "Input error: missing description";
    public static final String ERROR_LISTING_INPUT_MISSING_TITLE = "Input error: missing title";
    public static final String ERROR_LISTING_INPUT_MISSING_USERNAME = "Input error: missing username";

    public static final int MIN_CHARS_DESCRIPTION = 2;
    public static final int MIN_CHARS_TITLE = 2;
    public static final int MIN_CHARS_CATEGORY = 2;


    public static final String ERROR_LISTING_INPUT_TITLE = "<title> cant be very short should be " + MIN_CHARS_TITLE + " chars long \n" + CliFacadeConstants.CL_COMMAND_FORMAT;
    public static final String ERROR_LISTING_INPUT_DESCRIPTION = "<description> cant be very short should be " + MIN_CHARS_DESCRIPTION + " chars long \n" + CliFacadeConstants.CL_COMMAND_FORMAT;
    public static final String ERROR_LISTING_INPUT_CATEGORY = "<category> cant be very short should be " + MIN_CHARS_CATEGORY + " chars long \n" + CliFacadeConstants.CL_COMMAND_FORMAT;
    public static final String ERROR_LISTING_EXISTS = "Listing with the provided details already exists, please check  " ;

    private ListingValidationConstants() {
    }
}
