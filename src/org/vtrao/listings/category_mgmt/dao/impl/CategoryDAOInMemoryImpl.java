package org.vtrao.listings.category_mgmt.dao.impl;

import org.vtrao.listings.category_mgmt.CategoryConstants;
import org.vtrao.listings.category_mgmt.dao.CategoryDAO;
import org.vtrao.listings.category_mgmt.model.Category;
import org.vtrao.listings.commons.Utils;
import org.vtrao.listings.commons.exceptions.CategoryException;

import java.util.*;
import java.util.logging.Logger;

public class CategoryDAOInMemoryImpl implements CategoryDAO {
    private static final Logger logger = Logger.getLogger(CategoryDAOInMemoryImpl.class.getName());

    private Map<String, Category> categoryData = new HashMap();

    private Map<String, Integer> categoryListingCounts = new HashMap();

    private List<String> topCategories = new ArrayList();

    // NOTE: A very basic implementation to just cater to the demo use cases
    @Override
    public void insertCategory(Category category) throws CategoryException {
        categoryData.put(category.getCategoryId().toLowerCase(), category);
    }

    @Override
    public void checkCategory(String categoryId) throws CategoryException {
        if (!categoryData.containsKey(categoryId.toLowerCase())) {
            throw new CategoryException(CategoryConstants.ERROR_CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public List<String> getTopCategory(int limit) throws CategoryException {
        return topCategories;
    }

    @Override
    public void updateCategoryListings(Map<String, Integer> categoryListingCountsInput) {
        // Update the actual collection here
        categoryListingCountsInput.forEach((categoryId, counter) -> {
            Integer listingCount = categoryListingCounts.get(categoryId.toLowerCase());
            if (listingCount == null) {
                listingCount = new Integer(counter);
            } else {
                listingCount += counter;
            }
            categoryListingCounts.put(categoryId.toLowerCase(), listingCount);
        });
        computeTopCategories();
    }

    private void computeTopCategories() {
        String topCategory = "";
        if (!topCategories.isEmpty()) {
            topCategory = topCategories.get(0);
        }

        logger.info("Top Categories compute starting: before compute: top category is: " + topCategory);
        TreeMap<Integer, List<String>> result = new TreeMap(Collections.reverseOrder());
        categoryListingCounts.forEach((categoryId, count) -> {
            List<String> categoryIdList = result.get(count);
            if (categoryIdList == null) {
                categoryIdList = new ArrayList();
                categoryIdList.add(categoryId);
            } else {
                // The order for same number of listings for the category doesnt matter, unless decided
                // That approach can be implemented here
                categoryIdList.add(0,categoryId);
            }
            result.put(count, categoryIdList);
        });
        List<String> tempTopCategories = new ArrayList();
        result.forEach((count, categoryIdList) -> {
            tempTopCategories.addAll(categoryIdList);
        });
        // Replace categoryId whose version is taken off with name
        List<String> topCategoryNames = new ArrayList();
        topCategories = topCategoryNames;
        tempTopCategories.forEach( categoryId -> topCategoryNames.add(categoryData.get(categoryId).getName()));

        if (!topCategories.isEmpty()) {
            topCategory = topCategories.get(0);
        }
        logger.info("Top Categories compute done: after compute: top category is : " + topCategory);
    }

}
