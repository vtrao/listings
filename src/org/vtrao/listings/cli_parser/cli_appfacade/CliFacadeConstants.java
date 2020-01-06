package org.vtrao.listings.cli_parser.cli_appfacade;

public class CliFacadeConstants {
    public static final String GC_COMMAND_SORT_PRICE = "sort_price";
    public static final String GC_COMMAND_SORT_TIME = "sort_time";
    public static final String GC_COMMAND_SORT_ASC = "asc";
    public static final String GC_COMMAND_SORT_DSC = "dsc";

    public static final String CL_COMMAND_FORMAT = "CREATE_LISTING <username> <title> <description> <price> <category>";
    public static final String DL_COMMAND_FORMAT = "DELETE_LISTING <username> <listing_id>";
    public static final String GL_COMMAND_FORMAT = "GET_LISTING <username> <listing_id>";
    public static final String GC_COMMAND_FORMAT = "GET_CATEGORY <username> <category> {" + GC_COMMAND_SORT_PRICE + "|" + GC_COMMAND_SORT_TIME + "} " +
            "{" + GC_COMMAND_SORT_ASC + "|" + GC_COMMAND_SORT_DSC + "}";
    public static final String GTC_COMMAND_FORMAT = "GET_TOP_CATEGORY <username>";

    public static final String INVALID_SORT_TYPE = "Invalid sort type: allowed {" + GC_COMMAND_SORT_PRICE + "|" + GC_COMMAND_SORT_TIME + "} \n" +
            GC_COMMAND_FORMAT;
    public static final String INVALID_SORT_ORDER = "Invalid sort order: allowed {" + GC_COMMAND_SORT_ASC + "|" + GC_COMMAND_SORT_DSC + "} \n" +
            GC_COMMAND_FORMAT;

    public static final String PRICE_FORMAT_ERROR = "Price should be a valid number \n" + GC_COMMAND_FORMAT;

    public static final String LISTINGID_FORMAT_ERROR_DL = "ListingId should be a valid number \n" + DL_COMMAND_FORMAT;

    public static final String LISTINGID_FORMAT_ERROR_GL = "ListingId should be a valid number \n" + GL_COMMAND_FORMAT;

    private CliFacadeConstants() {
    }
}
