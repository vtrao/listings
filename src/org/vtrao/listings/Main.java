package org.vtrao.listings;

import org.vtrao.listings.cli_parser.Parser;
import org.vtrao.listings.cli_parser.cli_appfacade.Facade;
import org.vtrao.listings.cli_parser.cli_appfacade.impl.ListingFacade;
import org.vtrao.listings.cli_parser.impl.CliParser;
import org.vtrao.listings.commons.factory.impl.AppFactoryInMemoryImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Facade listingFacade = new ListingFacade(new AppFactoryInMemoryImpl());
        logger.log(Level.INFO, "Application started");
        Parser parser = new CliParser("#", "Listing", listingFacade);
        parser.run();
    }
}
