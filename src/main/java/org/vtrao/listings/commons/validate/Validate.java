package org.vtrao.listings.commons.validate;

import org.vtrao.listings.commons.exceptions.ListingAppException;

public interface Validate {
    void validate(Object input) throws ListingAppException;
}
