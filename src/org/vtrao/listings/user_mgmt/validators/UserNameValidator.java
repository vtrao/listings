package org.vtrao.listings.user_mgmt.validators;

import org.vtrao.listings.commons.exceptions.ListingException;
import org.vtrao.listings.commons.exceptions.UserRegisterException;
import org.vtrao.listings.commons.validate.Validate;

public class UserNameValidator implements Validate {

    @Override
    public void validate(Object input) throws ListingException {
        String inputString = String.valueOf(input);
        if ( input == null ) {
            throw new UserRegisterException(UserValidationConstants.USER_NAME_NULL);
        }

        if ( inputString.length() < 5) {
            throw new UserRegisterException(UserValidationConstants.USER_NAME_SHORT);
        }
        // TODO: Add more validation for username input
    }
}
