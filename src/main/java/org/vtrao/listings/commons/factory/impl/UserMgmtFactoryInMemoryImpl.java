package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.commons.factory.UserMgmtFactory;
import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.user_mgmt.controller.UserCliController;
import org.vtrao.listings.user_mgmt.controller.impl.UserCliControllerImpl;
import org.vtrao.listings.user_mgmt.dao.UserDAO;
import org.vtrao.listings.user_mgmt.dao.impl.UserDAOInMemory;
import org.vtrao.listings.user_mgmt.service.UserService;
import org.vtrao.listings.user_mgmt.service.impl.UserServiceBaseImpl;
import org.vtrao.listings.user_mgmt.validators.UserValidator;

public class UserMgmtFactoryInMemoryImpl implements UserMgmtFactory {
    private UserCliController userCliController;
    private UserDAO userDAO;
    private UserService userService;
    private Validate userValidator;

    public UserMgmtFactoryInMemoryImpl() {
        this.userDAO = new UserDAOInMemory();
        this.userValidator = new UserValidator();
        this.userService = new UserServiceBaseImpl(getDAO(), getUserNameValidator());
        this.userCliController = new UserCliControllerImpl(getService());
    }
    @Override
    public UserCliController getCliController() {
        return userCliController;
    }

    @Override
    public UserDAO getDAO() {
        return userDAO;
    }

    @Override
    public UserService getService() {
        return userService;
    }

    @Override
    public Validate getUserNameValidator() {
        return userValidator;
    }
}
