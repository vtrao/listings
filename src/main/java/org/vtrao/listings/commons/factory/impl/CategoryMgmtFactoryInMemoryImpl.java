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
    private AuthenticationFactory authenticationFactory;
    private CategoryCliController categoryCliController;
    private CategoryDAO categoryDAO;
    private CategoryService categoryService;
    private Validate categoryValidator;

    public CategoryMgmtFactoryInMemoryImpl(AuthenticationFactory authenticationFactory) {
        this.authenticationFactory = authenticationFactory;
        this.categoryDAO = new CategoryDAOInMemoryImpl();
        this.categoryValidator = new CategoryValidator();
        this.categoryService = new CategoryServiceImpl(getDAO(), getCategoryValidator());
        this.categoryCliController = new CategoryCliControllerImpl(authenticationFactory.getAuthentication(), getService());
    }

    @Override
    public CategoryCliController getCliController() {
        return categoryCliController;
    }

    @Override
    public CategoryDAO getDAO() {
        return categoryDAO;
    }

    @Override
    public CategoryService getService() {
        return categoryService;
    }

    @Override
    public Validate getCategoryValidator() {
        return categoryValidator;
    }
}
