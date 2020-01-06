package org.vtrao.listings.cli_parser.impl;

import org.vtrao.listings.cli_parser.cli_appfacade.Facade;
import org.vtrao.listings.cli_parser.Parser;
import org.vtrao.listings.commons.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CliParser implements Parser {
    public static final String SPACE = " ";

    private String prompt;
    private String purpose;
    private Facade facade;

    public CliParser(String prompt, String purpose, Facade facade) {
        this.purpose = purpose;
        this.facade = facade;
        this.prompt = prompt;
    }

    public static final String REGEX_CLI = "([^']\\S*|'.*?')\\s*";

    @Override
    public String run(String input) {
        String[] inputStrings = splitInput(input);
        System.out.println(prompt + SPACE + input);
        Response output = facade.execute(inputStrings);
        return output.getHrMessage();
    }

    private String[] splitInput(String input) {
        List<String> list = new ArrayList();
        Matcher m = Pattern.compile(REGEX_CLI).matcher(input);
        while (m.find()) {
            list.add(m.group(1));
        }
        return list.toArray(new String[0]);
    }

    @Override
    public void run() {
        System.out.println("Welcome to the CLI of " +  purpose + ", q or quit to exit, h or help for help");
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.print(prompt + SPACE);
            String input = scanner.nextLine();
            String[] inputStrings = splitInput(input);
            switch (inputStrings[0].toUpperCase()) {
                case "\n":
                    break;
                case "Q":
                case "QUIT":
                    loop = false;
                    break;
                default:
                    Response output = facade.execute(inputStrings);
                    System.out.println(output.getHrMessage());

            }
        }
    }
}
