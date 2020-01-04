package org.vtrao.listings.parser.impl;

import org.vtrao.listings.parser.Facade;
import org.vtrao.listings.parser.Parser;
import org.vtrao.listings.response.Response;

import java.io.InputStreamReader;
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
        System.out.println("Welcome to the CLI of " +  purpose + ", q to quit, h for help");
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.print(prompt + SPACE);
            String input = scanner.nextLine();
            System.out.println(input);
            switch (input) {
                case "q":
                    loop = false;
                    break;
                default:
                    Response output = facade.execute(input);
                    System.out.println(output.getHrMessage());

            }
        }
    }
}
