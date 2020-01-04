package org.vtrao.listings;

import org.vtrao.listings.parser.Facade;
import org.vtrao.listings.parser.Parser;
import org.vtrao.listings.parser.impl.CliParser;
import org.vtrao.listings.parser.impl.ListingFacade;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Facade listingFacade = new ListingFacade();
        logger.log(Level.INFO, "Application started");
        Parser parser = new CliParser( "#", "Listing", listingFacade);
        parser.run();
    }
}
