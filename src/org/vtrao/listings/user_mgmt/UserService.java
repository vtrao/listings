package org.vtrao.listings.user_mgmt;

import org.vtrao.listings.user_mgmt.model.User;

public interface UserService {
    boolean registerUser(User user);

    boolean updateUser(User user);

    User getUser(String userName);

    User getUserByEmailId(String emailId);
}
