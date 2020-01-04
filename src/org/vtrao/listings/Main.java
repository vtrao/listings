package org.vtrao.listings;

import org.vtrao.listings.parser.Facade;
import org.vtrao.listings.parser.Parser;
import org.vtrao.listings.parser.impl.CliParser;
import org.vtrao.listings.parser.impl.ListingFacade;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Facade listingFacade = new ListingFacade();
        Parser parser = new CliParser( "#", "Listing", listingFacade);
        parser.run();
    }
}
