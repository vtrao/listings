package org.vtrao.listings.listing_mgmt.controller;

import org.vtrao.listings.commons.response.impl.ListingCliResponse;

public interface ListingCliController {
    /**
     * CREATE_LISTING <username> <title> <description> <price> <category>
     * Responses:
     * "<listing id>"
     * "Error - unknown user"
     *
     * @param inputStrings
     * @param response
     */
    void createListing(String[] inputStrings, ListingCliResponse response);

    /**
     * DELETE_LISTING <username> <listing_id>
     * Responses:
     * "Success"
     * "Error - listing does not exist"
     * "Error - listing owner mismatch"
     *
     * @param inputStrings
     * @param response
     */
    void deleteListing(String[] inputStrings, ListingCliResponse response);

    /**
     * GET_LISTING <username> <listing_id>
     * Responses:
     * "<title>|<description>|<price>|<created_at>|<category>|<username>"
     * This command should return any listing queried according to listing_id, not limited to listings created by the user. Username is taken just for the purpose of authentication.
     * "Error - not found"
     * "Error - unknown user"
     *
     * @param inputStrings
     * @param response
     */
    void getListingById(String[] inputStrings, ListingCliResponse response);

    /**
     * GET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}
     * Responses:
     * "Error - category not found"
     * "Error - unknown user"
     * "<title>|<description>|<price>|<created_at>
     *  <title>|<description>|<price>|<created_at>
     *  <title>|<description>|<price>|<created_at>
     *  .....
     *
     * @param inputStrings
     * @param response
     */
    void getListingByCategory(String[] inputStrings, ListingCliResponse response);

    void searchListing(String[] inputStrings, ListingCliResponse response);
}
