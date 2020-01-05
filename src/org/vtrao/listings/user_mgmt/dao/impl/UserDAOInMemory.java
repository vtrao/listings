package org.vtrao.listings.user_mgmt.dao.impl;

import org.vtrao.listings.commons.GlobalConstants;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.exceptions.UserRegisterException;
import org.vtrao.listings.user_mgmt.UserConstants;
import org.vtrao.listings.user_mgmt.dao.UserDAO;
import org.vtrao.listings.user_mgmt.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDAOInMemory implements UserDAO {
    // TODO: Concurrent access
    private Map<String, User> userData = new HashMap();

    @Override
    public void insertUser(User user) throws UserException {
        if (null == user) {
            throw new UserException(UserConstants.ERROR_USER_NULL_INPUT);
        }
        User userExists = getUser(user.getUserName());
        if (null == userExists) {
            userData.put(user.getUserName(), user);
        } else {
            throw new UserRegisterException(UserConstants.ERROR_USER_EXISTS);
        }
    }

    @Override
    public User getUser(String userName) throws UserException {
        if (null == userName) {
            throw new UserException(UserConstants.ERROR_USER_NULL_INPUT);
        }
        return userData.get(userName);
    }

    @Override
    public boolean checkUser(String userName)throws UserException {
        if (null == userName) {
            throw new UserException(UserConstants.ERROR_USER_NULL_INPUT);
        }
        // TODO: Probablistic data structure like Bloom filter
        //  when the userbase grows and for quicker response
        return userData.containsKey(userName);
    }

    @Override
    public boolean checkUserByEmail(String userName)throws UserException {
        throw new UserException(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void deleteUser(User user) throws UserException {
        throw new UserException(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void updateUser(User user) throws UserException {
        throw new UserException(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        throw new UserException(GlobalConstants.UNSUPPORTED_OPERATION);
    }
}
