package org.vtrao.listings.user_mgmt.dao;

import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.user_mgmt.model.User;

public interface UserDAO {
    void insertUser(User user) throws UserException;
    User getUser(String userName) throws UserException;

    boolean checkUser(String userName) throws UserException;
    void deleteUser(User user) throws UserException;
    void updateUser(User user) throws UserException;
    boolean checkUserByEmail(String email) throws UserException;
    User getUserByEmail(String email) throws UserException;
}
