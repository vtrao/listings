package org.vtrao.listings.user_mgmt;

import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.response.Response;
import org.vtrao.listings.user_mgmt.model.User;

public interface UserService {
    void registerUser(User user) throws ListingException;

    void updateUser(User user) throws UserException;

    void deleteUser(User user) throws UserException;

    boolean checkUser(String userName) throws UserException;

    User getUser(String userName) throws UserException;

    User getUserByEmailId(String emailId) throws UserException;
}
