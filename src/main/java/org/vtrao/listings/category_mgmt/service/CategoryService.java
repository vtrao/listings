package org.vtrao.listings.category_mgmt.service;

import org.vtrao.listings.category_mgmt.model.Category;
import org.vtrao.listings.commons.exceptions.CategoryException;
import org.vtrao.listings.commons.exceptions.ListingAppException;

import java.util.List;

public interface CategoryService {
    void insertCategory(Category category) throws ListingAppException;
    void checkCategory(String category) throws CategoryException;
    List<String> getTopCategories(int limit) throws ListingAppException;
}
