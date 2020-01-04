package org.vtrao.listings.parser.impl;

import org.vtrao.listings.parser.Facade;
import org.vtrao.listings.response.Response;
import org.vtrao.listings.response.impl.ListingCliResponse;

public class ListingFacade implements Facade {
    public static final String CLI_COMMAND_REGISTER = "REGISTER";
    public static final String CLI_COMMAND_CREATE_LISTING = "CREATE_LISTING";
    public static final String CLI_COMMAND_DELETE_LISTING = "DELETE_LISTING";
    public static final String CLI_COMMAND_GET_LISTING = "GET_LISTING";
    public static final String CLI_COMMAND_GET_CATEGORY = "GET_CATEGORY";
    public static final String CLI_COMMAND_GET_TOP_CATEGORY = "GET_TOP_CATEGORY";

    @Override
    public Response execute(String input) {
        ListingCliResponse response = new ListingCliResponse();
        String[] inputStrings = input.split("\\\\s+");
        if ( inputStrings.length == 0 ) {
            response.setMessage(help());
        } else {
            switch (inputStrings[0]) {
                case CLI_COMMAND_REGISTER:
                case "r":
                    response.setCode(200);
                    response.setMessage("1TODOX " + CLI_COMMAND_REGISTER);
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

    private String help() {
        return "Output the help here: TODOX ";
    }
}