package org.vtrao.listings.category_mgmt.dao.impl;

import org.vtrao.listings.category_mgmt.CategoryConstants;
import org.vtrao.listings.category_mgmt.dao.CategoryDAO;
import org.vtrao.listings.category_mgmt.model.Category;
import org.vtrao.listings.commons.exceptions.CategoryException;

import java.util.*;

public class CategoryDAOInMemoryImpl implements CategoryDAO {
    private Map<String,Category> categoryData = new HashMap();

    // NOTE: A very basic implementation to just cater to the demo use cases
    @Override
    public void insertCategory(Category category) throws CategoryException {
        categoryData.put(category.getCategoryId().toLowerCase(), category);
    }

    @Override
    public void checkCategory(String categoryId) throws CategoryException {
        if (!categoryData.containsKey(categoryId)) {
            throw new CategoryException(CategoryConstants.ERROR_CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public List<String> getTopCategory(int limit) throws CategoryException {
        return null;
    }
}
