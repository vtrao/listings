package org.vtrao.listings.commons.authentication.impl;

import org.vtrao.listings.commons.GlobalConstants;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.user_mgmt.model.User;
import org.vtrao.listings.user_mgmt.service.UserService;

public class SimpleAuthenticationImpl implements Authentication {
    private UserService userService;

    public SimpleAuthenticationImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void authenticate(String userName) throws UserException {
        User user = userService.getUser(userName);
        if ( null == user) {
            throw new UserException(GlobalConstants.INVALID_USER);
        }
    }
}
