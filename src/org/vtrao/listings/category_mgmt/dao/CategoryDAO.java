package org.vtrao.listings.category_mgmt.dao;

import org.vtrao.listings.category_mgmt.model.Category;
import org.vtrao.listings.commons.exceptions.CategoryException;

import java.util.List;

public interface CategoryDAO {
    void insertCategory(Category category) throws CategoryException;
    void checkCategory(String categoryId) throws CategoryException;
    List<String> getTopCategory(int limit) throws CategoryException;
}
