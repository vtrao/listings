package org.vtrao.listings.category_mgmt.controller;

import org.vtrao.listings.commons.response.impl.ListingCliResponse;

public interface CategoryCliController {
    /**
     * GET_TOP_CATEGORY <username>
     * Responses:
     * "Error - unknown user"
     * <category name> (Category having the highest total number of listings). This command should consider all listings
     * in the marketplace and not just the listings created by the user issuing the command. Username is taken just for
     * the purpose of authentication.
     * This operation is expected to be a read heavy operation as it can be used on the home page etc.
     * Please ensure suitable optimization for the same.
     *
     * @param inputStrings
     * @param response
     */
    void getTopCategory(String[] inputStrings, ListingCliResponse response);




    void createCategory(String[] inputStrings, ListingCliResponse response);

    void updateCategory(String[] inputStrings, ListingCliResponse response);

    void deleteCategory(String[] inputStrings, ListingCliResponse response);

    void getCategoryAndItsChildren(String[] inputStrings, ListingCliResponse response);

    void getParentCategory(String[] inputStrings, ListingCliResponse response);
}
