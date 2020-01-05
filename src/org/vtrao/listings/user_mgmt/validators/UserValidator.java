package org.vtrao.listings.user_mgmt.validators;

import org.vtrao.listings.commons.exceptions.ListingAppException;
import org.vtrao.listings.commons.exceptions.UserRegisterException;
import org.vtrao.listings.commons.validate.Validate;
import org.vtrao.listings.user_mgmt.model.User;

public class UserValidator implements Validate {

    @Override
    public void validate(Object input) throws ListingAppException {
        User user = (User) input;
        String userName = user.getUserName();
        if ( userName == null ) {
            throw new UserRegisterException(UserValidationConstants.USER_NAME_NULL);
        }

        if ( userName.length() < 5) {
            throw new UserRegisterException(UserValidationConstants.USER_NAME_SHORT);
        }

        // TODO: Add more validation for other user fields
    }
}
