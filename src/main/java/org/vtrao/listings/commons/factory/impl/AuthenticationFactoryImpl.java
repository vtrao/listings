package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.commons.authentication.Authentication;
import org.vtrao.listings.commons.authentication.impl.SimpleAuthenticationImpl;
import org.vtrao.listings.commons.factory.AuthenticationFactory;
import org.vtrao.listings.commons.factory.UserMgmtFactory;

public class AuthenticationFactoryImpl implements AuthenticationFactory {
    private UserMgmtFactory userMgmtFactory;
    private Authentication authentication;

    public AuthenticationFactoryImpl(UserMgmtFactory userMgmtFactory) {
        this.userMgmtFactory = userMgmtFactory;
        this.authentication = new SimpleAuthenticationImpl(userMgmtFactory.getService());
    }

    @Override
    public Authentication getAuthentication() {
        return authentication;
    }
}
