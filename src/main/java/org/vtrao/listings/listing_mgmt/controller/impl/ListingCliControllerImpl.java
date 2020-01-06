package org.vtrao.listings.listing_mgmt.controller.impl;

import org.vtrao.listings.cli_parser.cli_appfacade.CliFacadeConstants;
import org.vtrao.listings.commons.GlobalConstants;
import org.vtrao.listings.commons.authentication.Authentication;
import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.response.ResponseConstants;
import org.vtrao.listings.commons.response.impl.ListingCliResponse;
import org.vtrao.listings.listing_mgmt.ListingConstants;
import org.vtrao.listings.listing_mgmt.controller.ListingCliController;
import org.vtrao.listings.listing_mgmt.model.Listing;
import org.vtrao.listings.listing_mgmt.service.ListingService;
import org.vtrao.listings.listing_mgmt.service.ListingService.SortOrder;
import org.vtrao.listings.listing_mgmt.service.ListingService.SortType;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListingCliControllerImpl implements ListingCliController {
    private static final Logger logger = Logger.getLogger(ListingCliControllerImpl.class.getName());

    private Authentication authentication;
    private ListingService listingService;

    public ListingCliControllerImpl(Authentication authentication,
                                    ListingService listingService) {
        this.authentication = authentication;
        this.listingService = listingService;
    }

    @Override
    public void createListing(String[] inputStrings, ListingCliResponse response) {
        // Parameter 1: <username>
        if (!parseUserAndAuthenticate(inputStrings, response, ListingCommandType.CL)) {
            return;
        }
        String userName = inputStrings[1];

        // Parameter 2: <title>
        String title = null;
        if (inputStrings.length >= 2) {
            title = removePreAndPostInvertedComma(inputStrings[2]);
        } else {
            commandFormat(response, ListingCommandType.CL);
            return;
        }
        // Parameter 3: <description>
        String description = null;
        if (inputStrings.length >= 3) {
            description = removePreAndPostInvertedComma(inputStrings[3]);
        } else {
            commandFormat(response, ListingCommandType.CL);
            return;
        }

        // Parameter 4: <price>
        String price = null;
        if (inputStrings.length >= 4) {
            price = inputStrings[4];
        } else {
            commandFormat(response, ListingCommandType.CL);
            return;
        }

        // Parameter 5: <category>
        String category = null;
        if (inputStrings.length >= 5) {
            category = removePreAndPostInvertedComma(inputStrings[5]);
        } else {
            commandFormat(response, ListingCommandType.CL);
            return;
        }

        try {
            Listing listing = new Listing();
            listing.setUserId(userName);
            listing.setTitle(title);
            listing.setDescription(description);
            listing.setPrice(Double.valueOf(price));
            listing.setCategoryId(category);
            response.setMessage(String.valueOf(listingService.insertListing(listing)));
        } catch (NumberFormatException nfe) {
            response.setMessage(CliFacadeConstants.PRICE_FORMAT_ERROR);
        } catch (ListingAppException ex) {
            // Validation errors goes here
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            response.setMessage(ex.getMessage());
        }
    }

    @Override
    public void deleteListing(String[] inputStrings, ListingCliResponse response) {
        // Parameter 1: <username>
        if (!parseUserAndAuthenticate(inputStrings, response, ListingCommandType.DL)) {
            return;
        }
        String userName = inputStrings[1];

        // Parameter 2: <listing_id>
        long listingId = parseListingId(inputStrings, response, ListingCommandType.DL);
        if (listingId == -1) {
            return;
        }

        try {
            listingService.deleteListing(listingId, userName);
            response.setMessage(ResponseConstants.SUCCESS_MESSAGE);
        } catch (ListingAppException ex) {
            // Validation errors goes here
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            response.setMessage(ex.getMessage());
        }
    }

    @Override
    public void getListingById(String[] inputStrings, ListingCliResponse response) {
        // Parameter 1: <username>
        if (!parseUserAndAuthenticate(inputStrings, response, ListingCommandType.GL)) {
            return;
        }

        // Parameter 2: <listing_id>
        long listingId = parseListingId(inputStrings, response, ListingCommandType.GL);
        if (listingId == -1) {
            return;
        }

        try {
            Listing listing = listingService.getListing(listingId);
            if (null == listing) {
                response.setMessage(ListingConstants.ERROR_NOT_FOUND);
            } else {
                response.setMessage(listing.toString());
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
    public void getListingByCategory(String[] inputStrings, ListingCliResponse response) {
        // Parameter 1: <username>
        if (!parseUserAndAuthenticate(inputStrings, response, ListingCommandType.GL)) {
            return;
        }

        // Parameter 2: <category>
        java.lang.String category = null;
        if (inputStrings.length >= 3) {
            category = removePreAndPostInvertedComma(inputStrings[2]);
        } else {
            commandFormat(response, ListingCommandType.GL);
            return;
        }

        // Parameter 3: <sort_type>
        SortType sortType = parseAndGetSortType(inputStrings, response);
        if (null == sortType) {
            return;
        }

        // Parameter 4: <sort_order>
        SortOrder sortOrder = parseAndGetSortOrder(inputStrings, response);
        if (null == sortOrder) {
            return;
        }

        // fetch the result from listing service
        try {
            List<Listing> listingsList = listingService.getListingByCategory(category, sortType,
                    sortOrder, ListingConstants.NO_LIMIT_IN_RESULT);
            if (null == listingsList || listingsList.isEmpty()) {
                response.setMessage(ListingConstants.ERROR_NOT_FOUND);
            } else {
                StringBuilder outputList = new StringBuilder();
                int index = 0;
                for (; index < listingsList.size() - 1; ++index) {
                    outputList.append(listingsList.get(index) + "\n");
                }
                outputList.append(listingsList.get(index));
                response.setMessage(outputList.toString());
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
    public void searchListing(String[] inputStrings, ListingCliResponse response) {
        response.setMessage(GlobalConstants.UNSUPPORTED_OPERATION);
    }

    private SortType parseAndGetSortType(String[] inputStrings, ListingCliResponse response) {
        String sortParam = null;
        SortType sortType = null;
        if (inputStrings.length >= 4) {
            sortParam = inputStrings[3];
            switch (sortParam.toLowerCase()) {
                case CliFacadeConstants.GC_COMMAND_SORT_PRICE:
                    sortType = SortType.PRICE;
                    break;
                case CliFacadeConstants.GC_COMMAND_SORT_TIME:
                    sortType = SortType.CREATIONTIME;
                    break;
                default:
                    response.setMessage(CliFacadeConstants.INVALID_SORT_TYPE);
            }
        } else {
            commandFormat(response, ListingCommandType.GL);
        }
        return sortType;
    }

    private SortOrder parseAndGetSortOrder(String[] inputStrings, ListingCliResponse response) {
        String sortParam = null;
        SortOrder sortorder = null;
        if (inputStrings.length >= 5) {
            sortParam = inputStrings[4];
            switch (sortParam.toLowerCase()) {
                case CliFacadeConstants.GC_COMMAND_SORT_ASC:
                    sortorder = SortOrder.ASC;
                    break;
                case CliFacadeConstants.GC_COMMAND_SORT_DSC:
                    sortorder = SortOrder.DESC;
                    break;
                default:
                    response.setMessage(CliFacadeConstants.INVALID_SORT_ORDER);
            }
        } else {
            commandFormat(response, ListingCommandType.GL);
        }
        return sortorder;
    }

    private boolean parseUserAndAuthenticate(String[] inputStrings,
                                             ListingCliResponse response,
                                             ListingCommandType commandType) {
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

    private long parseListingId(String[] inputStrings,
                                ListingCliResponse response,
                                ListingCommandType commandType) {
        long listingId = -1;
        try {
            if (inputStrings.length >= 3) {
                listingId = Long.valueOf(inputStrings[2]);
            } else {
                commandFormat(response, commandType);
            }
        } catch (NumberFormatException ne) {
            if (commandType.equals(ListingCommandType.GL)) {
                response.setMessage(CliFacadeConstants.LISTINGID_FORMAT_ERROR_GL);
            } else {
                response.setMessage(CliFacadeConstants.LISTINGID_FORMAT_ERROR_DL);
            }
        }
        return listingId;
    }

    enum ListingCommandType {
        CL, DL, GL, GC
    }

    private void commandFormat(ListingCliResponse response, ListingCommandType command) {
        switch (command) {
            case CL:
                response.setMessage(CliFacadeConstants.CL_COMMAND_FORMAT);
                break;
            case DL:
                response.setMessage(CliFacadeConstants.DL_COMMAND_FORMAT);
                break;
            case GL:
                response.setMessage(CliFacadeConstants.GL_COMMAND_FORMAT);
                break;
            case GC:
                response.setMessage(CliFacadeConstants.GC_COMMAND_FORMAT);
                break;
        }
    }

    private String removePreAndPostInvertedComma(String input) {
        return input.substring(1, input.length() - 1);
    }
}
