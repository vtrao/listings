package org.vtrao.listings.commons.factory;

import org.vtrao.listings.category_mgmt.controller.CategoryCliController;
import org.vtrao.listings.category_mgmt.dao.CategoryDAO;
import org.vtrao.listings.category_mgmt.service.CategoryService;
import org.vtrao.listings.commons.validate.Validate;

public interface CategoryMgmtFactory {
    CategoryCliController getCliController();
    CategoryDAO getDAO();
    CategoryService getService();
    Validate getCategoryValidator();
}
