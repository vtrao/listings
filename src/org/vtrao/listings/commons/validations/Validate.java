package org.vtrao.listings.commons.validations;

import org.vtrao.listings.commons.exceptions.ListingException;

public interface Validate {
    void validate(Object input) throws ListingException;
}
