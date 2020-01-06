package org.vtrao.listings;

import org.vtrao.listings.category_mgmt.dao.impl.CategoryDAOInMemoryImpl;
import org.vtrao.listings.cli_parser.Parser;
import org.vtrao.listings.cli_parser.cli_appfacade.Facade;
import org.vtrao.listings.cli_parser.cli_appfacade.impl.ListingFacade;
import org.vtrao.listings.cli_parser.impl.CliParser;
import org.vtrao.listings.commons.Utils;
import org.vtrao.listings.commons.factory.impl.AppFactoryInMemoryImpl;

import java.io.*;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(CategoryDAOInMemoryImpl.class.getName());

    public static void main(String[] args) {
        if ( args.length >= 1) { // consider the first argument as a test file
            boolean writeToFile = false;
            if ( args.length >= 2 ) {
                writeToFile = true;
            }

            Utils.setFileRun(true);
            Utils.loggingConfig(CategoryDAOInMemoryImpl.class.getName());
            Facade listingFacade = new ListingFacade(new AppFactoryInMemoryImpl());
            logger.log(Level.INFO, "Application started in file reading mode: " + args[0]);
            Parser parser = new CliParser("#", "Listing", listingFacade);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                BufferedWriter writer = null;
                if ( writeToFile) {
                    writer = new BufferedWriter(new FileWriter(args[1]));
                }
                String line = reader.readLine();
                while (line != null) {
                    if ( line.length() > 2 ) {
                        String output = parser.run(line);
                        System.out.println(output);
                        if ( writeToFile) {
                            writer.write(output+"\n");
                        }
                    }
                    line = reader.readLine(); // read next line
                }
                writer.flush();
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Utils.setFileRun(false); // To ensure that Top categories are computed with a delay
            Utils.loggingConfig(CategoryDAOInMemoryImpl.class.getName());
            Facade listingFacade = new ListingFacade(new AppFactoryInMemoryImpl());
            logger.log(Level.INFO, "Application started");
            Parser parser = new CliParser("#", "Listing", listingFacade);
            parser.run();
            System.out.println("Thank You!!");
        }
    }
}
