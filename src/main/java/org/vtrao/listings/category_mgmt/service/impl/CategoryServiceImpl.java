package org.vtrao.listings.category_mgmt.service.impl;

import org.vtrao.listings.category_mgmt.dao.CategoryDAO;
import org.vtrao.listings.category_mgmt.model.Category;
import org.vtrao.listings.category_mgmt.service.CategoryService;
import org.vtrao.listings.commons.exceptions.CategoryException;
import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.commons.validate.Validate;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private Validate categoryIdValidator;
    private CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO, Validate categoryIdValidator) {
        this.categoryIdValidator = categoryIdValidator;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void insertCategory(Category category) throws ListingAppException {
        categoryDAO.insertCategory(category);
    }

    @Override
    public void checkCategory(String category) throws CategoryException {
        categoryDAO.checkCategory(category);
    }

    @Override
    public List<String> getTopCategories(int limit) throws ListingAppException {
        return categoryDAO.getTopCategory(limit);
    }
}
