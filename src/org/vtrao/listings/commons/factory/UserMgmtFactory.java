package org.vtrao.listings.commons.factory;

import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.user_mgmt.controller.UserCliController;
import org.vtrao.listings.user_mgmt.dao.UserDAO;
import org.vtrao.listings.user_mgmt.service.UserService;

public interface UserMgmtFactory {
    UserCliController getCliController();
    UserDAO getDAO();
    UserService getService();
    Validate getUserNameValidator();
}
