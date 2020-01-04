package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.commons.authentication.impl.Authentication;
import org.vtrao.listings.commons.authentication.impl.SimpleAuthenticationImpl;
import org.vtrao.listings.commons.factory.AuthenticationFactory;
import org.vtrao.listings.commons.factory.UserMgmtFactory;

public class AuthenticationFactoryImpl implements AuthenticationFactory {
    UserMgmtFactory userMgmtFactory;

    public AuthenticationFactoryImpl(UserMgmtFactory userMgmtFactory) {
        this.userMgmtFactory = userMgmtFactory;
    }

    @Override
    public Authentication getAuthentication() {
        return new SimpleAuthenticationImpl(userMgmtFactory.getService());
    }
}
