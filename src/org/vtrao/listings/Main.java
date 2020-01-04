package org.vtrao.listings;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static final String CLI_COMMAND_REGISTER = "REGISTER";
    public static final String CLI_COMMAND_CREATE_LISTING = "CREATE_LISTING";
    public static final String CLI_COMMAND_DELETE_LISTING = "DELETE_LISTING";
    public static final String CLI_COMMAND_GET_LISTING = "GET_LISTING";
    public static final String CLI_COMMAND_GET_CATEGORY = "GET_CATEGORY";
    public static final String CLI_COMMAND_GET_TOP_CATEGORY = "GET_TOP_CATEGORY";



    /**
     * REGISTER <username>
     * Responses:
     * "Success"
     * "Error - user already existing"
     *
     * CREATE_LISTING <username> <title> <description> <price> <category>
     * Responses:
     * "<listing id>"
     * "Error - unknown user"
     *
     * DELETE_LISTING <username> <listing_id>
     * Responses:
     * "Success"
     * "Error - listing does not exist"
     * "Error - listing owner mismatch"
     * GET_LISTING <username> <listing_id>
     * Responses:
     * "<title>|<description>|<price>|<created_at>|<category>|<username>"
     * This command should return any listing queried according to listing_id, not limited to listings created by the user. Username is taken just for the purpose of authentication.
     * "Error - not found"
     * "Error - unknown user"
     *
     * GET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}
     * Responses:
     * "Error - category not found"
     * "Error - unknown user"
     * "<title>|<description>|<price>|<created_at>
     *  <title>|<description>|<price>|<created_at>
     *  <title>|<description>|<price>|<created_at>
     *  .....
     *
     * GET_TOP_CATEGORY <username>
     * Responses:
     * "Error - unknown user"
     * <category name> (Category having the highest total number of listings). This command should consider all listings in the marketplace and not just the listings created by the user issuing the command. Username is taken just for the purpose of authentication.
     * This operation is expected to be a read heavy operation as it can be used on the home page etc. Please ensure suitable optimization for the same.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the CLI of Listing, q to quit, h for help");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        boolean loop = true;
        while (loop) {
            System.out.print("# ");
            String input = scanner.nextLine();
            System.out.println(input);
            switch (input) {
                case "q":
                    loop = false;
                    break;
                case CLI_COMMAND_REGISTER:
                case "r":
                    System.out.println("TODO " + CLI_COMMAND_REGISTER);
                    break;
                case CLI_COMMAND_CREATE_LISTING:
                case "cl":
                    System.out.println("TODO " + CLI_COMMAND_CREATE_LISTING);
                    break;
                case CLI_COMMAND_DELETE_LISTING:
                case "dl":
                    System.out.println("TODO " + CLI_COMMAND_DELETE_LISTING);
                    break;
                case CLI_COMMAND_GET_LISTING:
                case "gl":
                    System.out.println("TODO " + CLI_COMMAND_GET_LISTING);
                    break;
                case CLI_COMMAND_GET_CATEGORY:
                case "gc":
                    System.out.println("TODO " + CLI_COMMAND_GET_CATEGORY);
                    break;
                case CLI_COMMAND_GET_TOP_CATEGORY:
                case "gtc":
                    System.out.println("TODO " + CLI_COMMAND_GET_TOP_CATEGORY);
                    break;
                case "h":
                default:
                    System.out.println("Output the help here: TODO");
                    break;
            }
        }
    }
}
