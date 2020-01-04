package org.vtrao.listings.cli_parser.cli_appfacade.impl;


import org.vtrao.listings.category_mgmt.controller.CategoryCliController;
import org.vtrao.listings.category_mgmt.controller.impl.CategoryCliControllerImpl;
import org.vtrao.listings.cli_parser.cli_appfacade.Facade;
import org.vtrao.listings.commons.factory.AbstractAppFactory;
import org.vtrao.listings.commons.response.Response;
import org.vtrao.listings.commons.response.impl.ListingCliResponse;
import org.vtrao.listings.listing_mgmt.controller.ListingCliController;
import org.vtrao.listings.listing_mgmt.controller.impl.ListingCliControllerImpl;
import org.vtrao.listings.user_mgmt.controller.UserCliController;
import org.vtrao.listings.user_mgmt.controller.impl.UserCliControllerImpl;
import org.vtrao.listings.user_mgmt.dao.impl.UserDAOInMemory;
import org.vtrao.listings.user_mgmt.service.impl.UserServiceBaseImpl;
import org.vtrao.listings.user_mgmt.validators.UserNameValidator;

import java.util.logging.Logger;

public class ListingFacade implements Facade {
    private static final Logger logger = Logger.getLogger(ListingFacade.class.getName());

    public static final String CLI_COMMAND_REGISTER = "REGISTER";
    public static final String CLI_COMMAND_CREATE_LISTING = "CREATE_LISTING";
    public static final String CLI_COMMAND_DELETE_LISTING = "DELETE_LISTING";
    public static final String CLI_COMMAND_GET_LISTING = "GET_LISTING";
    public static final String CLI_COMMAND_GET_CATEGORY = "GET_CATEGORY";
    public static final String CLI_COMMAND_GET_TOP_CATEGORY = "GET_TOP_CATEGORY";

    private UserCliController userCliController;
    private ListingCliController listingCliController;
    private CategoryCliController categoryCliController;

    public ListingFacade(AbstractAppFactory abstractAppFactory) {
        userCliController = abstractAppFactory.getUserMgmtFactory().getCliController();
        listingCliController = abstractAppFactory.getListingMgmtFactory().getCliController();
        categoryCliController = abstractAppFactory.getCategoryMgmtFactory().getCliController();

    }

    @Override
    public Response execute(String[] inputStrings) {
        ListingCliResponse response = new ListingCliResponse();
        if (inputStrings.length == 0) {
            response.setMessage(help());
        } else {
            switch (inputStrings[0].toUpperCase()) {
                case CLI_COMMAND_REGISTER:
                case "R":
                    userCliController.registerUser(inputStrings, response);
                    break;
                case CLI_COMMAND_CREATE_LISTING:
                case "CL":
                    listingCliController.createListing(inputStrings, response);
                    break;
                case CLI_COMMAND_DELETE_LISTING:
                case "DL":
                    listingCliController.deleteListing(inputStrings, response);
                    break;
                case CLI_COMMAND_GET_LISTING:
                case "GL":
                    listingCliController.getListingById(inputStrings, response);
                    break;
                case CLI_COMMAND_GET_CATEGORY:
                case "GC":
                    listingCliController.getListingByCategory(inputStrings, response);
                    break;
                case CLI_COMMAND_GET_TOP_CATEGORY:
                case "GTC":
                    categoryCliController.getTopCategory(inputStrings, response);
                    break;
                case "H":
                case "HELP":
                default:
                    response.setMessage(help());
                    break;
            }
        }
        return response;
    }

    private String help() {
        return "Commands allowed:\n" +
                "   # help or h\n" +
                "   # quit or q\n" +
                "   # REGISTER <username>\n" +
                "   # CREATE_LISTING <username> <title> <description> <price> <category>\n" +
                "   # DELETE_LISTING <username> <listing_id>\n" +
                "   # GET_LISTING <username> <listing_id>\n" +
                "   # GET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}\n" +
                "   # GET_TOP_CATEGORY <username>\n"+
                "\n\nCommands: TODO\n"+
                "   # CHECK_USER\n"+
                "   # SEARCH_LISTING\n"+
                "   # CREATE_CATEGORY\n"+
                "....\n\n";
    }
}