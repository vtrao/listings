package org.vtrao.listings.commons.validations.impl;

import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.commons.exceptions.UserRegisterException;
import org.vtrao.listings.commons.validations.Validate;
import org.vtrao.listings.commons.validations.ValidationConstants;

public class UserNameValidator implements Validate {

    @Override
    public void validate(Object input) throws ListingException {
        String inputString = String.valueOf(input);
        if ( input == null ) {
            throw new UserRegisterException(ValidationConstants.USER_NAME_NULL);
        }

        if ( inputString.length() < 5) {
            throw new UserRegisterException(ValidationConstants.USER_NAME_SHORT);
        }
        // TODO: Add more validation for username input
    }
}
