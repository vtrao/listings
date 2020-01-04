package org.vtrao.listings.user_mgmt.service.impl;


import org.vtrao.listings.commons.GlobalConstants;
import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.user_mgmt.service.UserService;
import org.vtrao.listings.user_mgmt.UserConstants;
import org.vtrao.listings.user_mgmt.dao.UserDAO;
import org.vtrao.listings.user_mgmt.model.User;

public class UserServiceBaseImpl implements UserService {

    private UserDAO userDAO;
    private Validate userNameValidator;

    public UserServiceBaseImpl(UserDAO userDAO, Validate userNameValidator) {
        this.userDAO = userDAO;
        this.userNameValidator = userNameValidator;
    }

    @Override
    public void registerUser(User user) throws ListingException {
        if (null == user) {
            throw new UserException(UserConstants.ERROR_USER_NULL_INPUT);
        }
        validateUser(user);
        userDAO.insertUser(user);
    }

    private void validateUser(User user) throws ListingException {
        userNameValidator.validate(user.getUserName());
    }

    @Override
    public User getUser(String userName) throws UserException  {
        if (null == userName) {
            throw new UserException(UserConstants.ERROR_USER_NULL_INPUT);
        }
        return userDAO.getUser(userName);
    }

    @Override
    public boolean checkUser(String userName) throws UserException {
        return false;
    }

    @Override
    public void updateUser(User user) throws UserException  {
        if (null == user) {
            throw new UserException(UserConstants.ERROR_USER_NULL_INPUT);
        }
        throw new UserException(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void deleteUser(User user) throws UserException {
        throw new UserException(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public User getUserByEmailId(String emailId) throws UserException  {
        throw new UserException(GlobalConstants.UNSUPPORTED_OPERATION);
    }
}
