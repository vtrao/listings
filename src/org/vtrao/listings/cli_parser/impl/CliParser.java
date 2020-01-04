package org.vtrao.listings.cli_parser.impl;

import org.vtrao.listings.cli_parser.cli_appfacade.Facade;
import org.vtrao.listings.cli_parser.Parser;
import org.vtrao.listings.commons.response.Response;

import java.util.Scanner;

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

    public void run() {
        System.out.println("Welcome to the CLI of " +  purpose + ", q or quit to exit, h or help for help");
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.print(prompt + SPACE);
            String input = scanner.nextLine();
            String[] inputStrings = input.split("\\s+");
            System.out.println(input);
            switch (inputStrings[0].toUpperCase()) {
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
