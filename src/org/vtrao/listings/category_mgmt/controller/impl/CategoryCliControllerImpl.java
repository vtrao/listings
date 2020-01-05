package org.vtrao.listings.category_mgmt.controller.impl;

import org.vtrao.listings.category_mgmt.CategoryConstants;
import org.vtrao.listings.category_mgmt.controller.CategoryCliController;
import org.vtrao.listings.category_mgmt.service.CategoryService;
import org.vtrao.listings.cli_parser.cli_appfacade.CliFacadeConstants;
import org.vtrao.listings.commons.GlobalConstants;
import org.vtrao.listings.commons.Utils;
import org.vtrao.listings.commons.authentication.Authentication;
import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.response.impl.ListingCliResponse;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryCliControllerImpl implements CategoryCliController {
    private static final Logger logger = Logger.getLogger(CategoryCliControllerImpl.class.getName());

    private Authentication authentication;
    private CategoryService categoryService;

    public CategoryCliControllerImpl(Authentication authentication, CategoryService categoryService) {
        this.authentication = authentication;
        this.categoryService = categoryService;
    }

    @Override
    public void getTopCategory(String[] inputStrings, ListingCliResponse response) {
        // Parameter 1: <username>
        if (!parseUserAndAuthenticate(inputStrings, response, CategoryCommandType.GTC)) {
            return;
        }

        try {
            List<String> categories = categoryService.getTopCategories(1);
            if ( categories != null && !categories.isEmpty() ) {
                response.setMessage(categories.get(0));
            } else {
                response.setMessage(CategoryConstants.ERROR_NO_TOP_CATEGORIES);
            }
        } catch (ListingAppException ex) {
            // Validation errors goes here
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            response.setMessage(ex.getMessage());
        }
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


    private boolean parseUserAndAuthenticate(String[] inputStrings,
                                             ListingCliResponse response,
                                             CategoryCommandType commandType) {
        String userName = null;
        if (inputStrings.length >= 2) {
            userName = inputStrings[1];
        } else {
            commandFormat(response, commandType);
            return false;
        }
        try {
            this.authentication.authenticate(userName);
        } catch (UserException ex) {
            response.setMessage(GlobalConstants.ERROR_UNKNOWN_USER);
            return false;
        } catch (Exception ex) {
            logger.log(Level.WARNING, ex.getMessage());
            response.setMessage(ex.getMessage());
            return false;
        }
        return true;
    }

    enum CategoryCommandType {
        GTC
    }

    private void commandFormat(ListingCliResponse response, CategoryCommandType command) {
        switch (command) {
            case GTC:
                response.setMessage(CliFacadeConstants.GTC_COMMAND_FORMAT);
                break;
        }
    }

}
