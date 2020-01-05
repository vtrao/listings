package org.vtrao.listings.commons.authentication;

import org.vtrao.listings.commons.exceptions.UserException;

public interface Authentication {
    void authenticate(String data) throws UserException;
}
