package org.vtrao.listings.parser.impl;

import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.commons.exceptions.UserException;
import org.vtrao.listings.commons.exceptions.UserRegisterException;
import org.vtrao.listings.commons.response.ResponseConstants;
import org.vtrao.listings.parser.Facade;
import org.vtrao.listings.commons.response.Response;
import org.vtrao.listings.commons.response.impl.ListingCliResponse;
import org.vtrao.listings.user_mgmt.UserService;
import org.vtrao.listings.user_mgmt.dao.impl.UserDAOInMemory;
import org.vtrao.listings.user_mgmt.model.User;
import org.vtrao.listings.user_mgmt.service.impl.UserServiceBaseImpl;

import java.net.HttpURLConnection;
import java.util.logging.Logger;

public class ListingFacade implements Facade {
    private static final Logger logger = Logger.getLogger(ListingFacade.class.getName());

    public static final String CLI_COMMAND_REGISTER = "REGISTER";
    public static final String CLI_COMMAND_CREATE_LISTING = "CREATE_LISTING";
    public static final String CLI_COMMAND_DELETE_LISTING = "DELETE_LISTING";
    public static final String CLI_COMMAND_GET_LISTING = "GET_LISTING";
    public static final String CLI_COMMAND_GET_CATEGORY = "GET_CATEGORY";
    public static final String CLI_COMMAND_GET_TOP_CATEGORY = "GET_TOP_CATEGORY";

    private UserService userService;

    public ListingFacade() {
        this.userService = new UserServiceBaseImpl(new UserDAOInMemory());
    }

    @Override
    public Response execute(String input) {
        ListingCliResponse response = new ListingCliResponse();
        String[] inputStrings = input.split("\\s+");
        if (inputStrings.length == 0) {
            response.setMessage(help());
        } else {
            switch (inputStrings[0].toUpperCase()) {
                case CLI_COMMAND_REGISTER:
                case "r":
                    if (inputStrings.length == 1) {
                        registerUser(null, response);
                    } else {
                        registerUser(inputStrings[1], response);
                    }
                    break;
                case CLI_COMMAND_CREATE_LISTING:
                case "cl":
                    response.setMessage("TODOX " + CLI_COMMAND_CREATE_LISTING);
                    break;
                case CLI_COMMAND_DELETE_LISTING:
                case "dl":
                    response.setMessage("TODOX " + CLI_COMMAND_DELETE_LISTING);
                    break;
                case CLI_COMMAND_GET_LISTING:
                case "gl":
                    response.setMessage("TODOX " + CLI_COMMAND_GET_LISTING);
                    break;
                case CLI_COMMAND_GET_CATEGORY:
                case "gc":
                    response.setMessage("TODOX " + CLI_COMMAND_GET_CATEGORY);
                    break;
                case CLI_COMMAND_GET_TOP_CATEGORY:
                case "gtc":
                    response.setMessage("TODOX " + CLI_COMMAND_GET_TOP_CATEGORY);
                    break;
                case "h":
                default:
                    response.setMessage(help());
                    break;
            }
        }
        return response;
    }

    private void registerUser(String userName, ListingCliResponse response) {
        User user = new User();
        user.setUserName(userName);
        try {
            userService.registerUser(user);
            response.setCode(200);
            response.setMessage(ResponseConstants.SUCCESS_MESSAGE);
        } catch (UserRegisterException ex) {
            response.setCode(HttpURLConnection.HTTP_BAD_REQUEST);
            response.setMessage(ex.getMessage());
        } catch (UserException ex) {
            response.setCode(HttpURLConnection.HTTP_BAD_REQUEST);
            response.setMessage(ex.getMessage());
        } catch (ListingException ex) {
            response.setCode(HttpURLConnection.HTTP_BAD_REQUEST);
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setCode(HttpURLConnection.HTTP_BAD_REQUEST);
            response.setMessage(ex.getMessage());
        }

    }

    private String help() {
        return "Output the help here: TODOX ";
    }
}