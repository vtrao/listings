package org.vtrao.listings.user_mgmt.controller;

import org.vtrao.listings.commons.response.impl.ListingCliResponse;

public interface UserCliController {
    /**
     * REGISTER <username>
     * Responses:
     * "Success"
     * "Error - user already existing"
     *
     * @param inputStrings
     * @param response
     */
    void registerUser(String[] inputStrings, ListingCliResponse response);


    void checkUser(String[] inputStrings, ListingCliResponse response);
    void getUser(String[] inputStrings, ListingCliResponse response);

    void updateUser(String[] inputStrings, ListingCliResponse response);
    void deleteUser(String[] inputStrings, ListingCliResponse response);
    void getUserByEmail(String[] inputStrings, ListingCliResponse response);
}
