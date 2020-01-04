package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.commons.factory.UserMgmtFactory;
import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.user_mgmt.controller.UserCliController;
import org.vtrao.listings.user_mgmt.controller.impl.UserCliControllerImpl;
import org.vtrao.listings.user_mgmt.dao.UserDAO;
import org.vtrao.listings.user_mgmt.dao.impl.UserDAOInMemory;
import org.vtrao.listings.user_mgmt.service.UserService;
import org.vtrao.listings.user_mgmt.service.impl.UserServiceBaseImpl;
import org.vtrao.listings.user_mgmt.validators.UserNameValidator;

public class UserMgmtFactoryInMemoryImpl implements UserMgmtFactory {
    @Override
    public UserCliController getCliController() {
        return new UserCliControllerImpl(getService());
    }

    @Override
    public UserDAO getDAO() {
        return new UserDAOInMemory();
    }

    @Override
    public UserService getService() {
        return new UserServiceBaseImpl(getDAO(), getUserNameValidator());
    }

    @Override
    public Validate getUserNameValidator() {
        return new UserNameValidator();
    }
}
