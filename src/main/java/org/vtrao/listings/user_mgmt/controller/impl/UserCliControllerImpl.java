package org.vtrao.listings.user_mgmt.controller.impl;

import org.vtrao.listings.commons.GlobalConstants;
import org.vtrao.listings.commons.Utils;
import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.commons.response.ResponseConstants;
import org.vtrao.listings.commons.response.impl.ListingCliResponse;
import org.vtrao.listings.user_mgmt.controller.UserCliController;
import org.vtrao.listings.user_mgmt.model.User;
import org.vtrao.listings.user_mgmt.service.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserCliControllerImpl implements UserCliController {
    private static final Logger logger = Logger.getLogger(UserCliControllerImpl.class.getName());

    private UserService userService;

    public UserCliControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerUser(String[] inputStrings, ListingCliResponse response) {
        String userName = null;
        if (inputStrings.length > 1 ) {
            userName = inputStrings[1];
        }

        User user = new User();
        user.setUserName(userName);
        try {
            userService.registerUser(user);
            response.setCode(200);
            response.setMessage(ResponseConstants.SUCCESS_MESSAGE);
        } catch (ListingAppException ex) {
            // Validation errors goes here
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            response.setMessage(ex.getMessage());
        }
    }

    @Override
    public void checkUser(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void getUser(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void updateUser(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void deleteUser(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void getUserByEmail(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }
}
