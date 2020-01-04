package org.vtrao.listings.user_mgmt.service.impl;


import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.validations.Validate;
import org.vtrao.listings.commons.validations.impl.UserNameValidator;
import org.vtrao.listings.user_mgmt.UserService;
import org.vtrao.listings.user_mgmt.commons.Constants;
import org.vtrao.listings.user_mgmt.dao.UserDAO;
import org.vtrao.listings.user_mgmt.model.User;

public class UserServiceBaseImpl implements UserService {

    private UserDAO userDAO;
    private Validate userNameValidator;

    public UserServiceBaseImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.userNameValidator = new UserNameValidator();
    }

    @Override
    public void registerUser(User user) throws ListingException {
        if (null == user) {
            throw new UserException(Constants.ERROR_USER_NULL_INPUT);
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
            throw new UserException(Constants.ERROR_USER_NULL_INPUT);
        }
        return null;
    }

    @Override
    public boolean checkUser(String userName) throws UserException {
        return false;
    }

    @Override
    public void updateUser(User user) throws UserException  {
        if (null == user) {
            throw new UserException(Constants.ERROR_USER_NULL_INPUT);
        }
        throw new UserException(Constants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void deleteUser(User user) throws UserException {
        throw new UserException(Constants.UNSUPPORTED_OPERATION);
    }

    @Override
    public User getUserByEmailId(String emailId) throws UserException  {
        throw new UserException(Constants.UNSUPPORTED_OPERATION);
    }
}
