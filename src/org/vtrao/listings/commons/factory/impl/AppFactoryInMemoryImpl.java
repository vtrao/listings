package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.commons.factory.*;

public class AppFactoryInMemoryImpl implements AbstractAppFactory {
    @Override
    public UserMgmtFactory getUserMgmtFactory() {
        return new UserMgmtFactoryInMemoryImpl();
    }

    @Override
    public AuthenticationFactory getAuthentiationFactory() {
        return new AuthenticationFactoryImpl(getUserMgmtFactory());
    }

    @Override
    public CategoryMgmtFactory getCategoryMgmtFactory() {
        return new CategoryMgmtFactoryInMemoryImpl(getAuthentiationFactory());
    }

    @Override
    public ListingMgmtFactory getListingMgmtFactory() {
        return new ListingMgmtFactoryInMemoryImpl(getAuthentiationFactory());
    }
}
