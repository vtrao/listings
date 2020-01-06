package org.vtrao.listings.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utils {

    private static boolean filerun = false;

    public static void setFileRun(boolean filerunArg) {
        filerun = filerunArg;
    }

    public static boolean getFileRun() {
        return filerun;
    }

    private Utils(){}

    public static void loggingConfig(String className) {
        try {
            // Programmatic configuration
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");
            System.setErr(new PrintStream(new FileOutputStream(new File("./stderr.log"))));
            /*final FileHandler fileHandler = new FileHandler("./application.log");
            fileHandler.setLevel(Level.FINEST);
            fileHandler.setFormatter(new SimpleFormatter());
            final Logger app = Logger.getLogger(className);
            app.setLevel(Level.FINEST);
            app.addHandler(fileHandler);*/
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
