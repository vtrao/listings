package org.vtrao.listings.category_mgmt.controller.impl;

import org.vtrao.listings.category_mgmt.controller.CategoryCliController;
import org.vtrao.listings.category_mgmt.service.CategoryService;
import org.vtrao.listings.commons.GlobalConstants;
import org.vtrao.listings.commons.authentication.Authentication;
import org.vtrao.listings.commons.response.impl.ListingCliResponse;

public class CategoryCliControllerImpl implements CategoryCliController {
    private Authentication authentication;
    private CategoryService categoryService;

    public CategoryCliControllerImpl(Authentication authentication, CategoryService categoryService) {
        this.authentication = authentication;
        this.categoryService = categoryService;
    }

    @Override
    public void getTopCategory(String[] inputStrings, ListingCliResponse response) {
        String userName = null;
        if (inputStrings.length > 1 ) {
            userName = inputStrings[1];
        }
        response.setMessage("TODO: GTC");
    }




    @Override
    public void createCategory(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void updateCategory(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void deleteCategory(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void getCategoryAndItsChildren(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    @Override
    public void getParentCategory(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }
}
