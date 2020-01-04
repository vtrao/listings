package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.category_mgmt.controller.CategoryCliController;
import org.vtrao.listings.category_mgmt.controller.impl.CategoryCliControllerImpl;
import org.vtrao.listings.category_mgmt.dao.CategoryDAO;
import org.vtrao.listings.category_mgmt.dao.impl.CategoryDAOInMemoryImpl;
import org.vtrao.listings.category_mgmt.service.CategoryService;
import org.vtrao.listings.category_mgmt.service.impl.CategoryServiceImpl;
import org.vtrao.listings.category_mgmt.validators.CategoryValidator;
import org.vtrao.listings.commons.factory.AuthenticationFactory;
import org.vtrao.listings.commons.factory.CategoryMgmtFactory;
import org.vtrao.listings.commons.validate.Validate;

public class CategoryMgmtFactoryInMemoryImpl implements CategoryMgmtFactory {
    AuthenticationFactory authenticationFactory;

    public CategoryMgmtFactoryInMemoryImpl(AuthenticationFactory authenticationFactory) {
        this.authenticationFactory = authenticationFactory;
    }

    @Override
    public CategoryCliController getCliController() {
        return new CategoryCliControllerImpl(authenticationFactory.getAuthentication(), getService());
    }

    @Override
    public CategoryDAO getDAO() {
        return new CategoryDAOInMemoryImpl();
    }

    @Override
    public CategoryService getService() {
        return new CategoryServiceImpl(getDAO(), getCategoryValidator());
    }

    @Override
    public Validate getCategoryValidator() {
        return new CategoryValidator();
    }
}
