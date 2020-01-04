package org.vtrao.listings.category_mgmt.service.impl;

import org.vtrao.listings.category_mgmt.dao.CategoryDAO;
import org.vtrao.listings.category_mgmt.service.CategoryService;
import org.vtrao.listings.commons.validate.Validate;

public class CategoryServiceImpl implements CategoryService {
    private Validate categoryIdValidator;
    private CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO, Validate categoryIdValidator) {
        this.categoryIdValidator = categoryIdValidator;
        this.categoryDAO = categoryDAO;
    }
}
