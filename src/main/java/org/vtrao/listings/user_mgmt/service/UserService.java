package org.vtrao.listings.user_mgmt.service;

import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.user_mgmt.model.User;

public interface UserService {
    void registerUser(User user) throws ListingAppException;

    User getUser(String userName) throws UserException;


    boolean checkUser(String userName) throws UserException;

    void updateUser(User user) throws UserException;

    void deleteUser(User user) throws UserException;

    User getUserByEmailId(String emailId) throws UserException;
}
