package org.vtrao.listings.cli_parser.cli_appfacade;

import org.vtrao.listings.commons.response.Response;

public interface Facade {
    Response execute(String[] inputStrings);
}
