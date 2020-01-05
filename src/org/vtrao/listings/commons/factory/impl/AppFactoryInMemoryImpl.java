package org.vtrao.listings.commons.factory.impl;

import org.vtrao.listings.commons.factory.*;

public class AppFactoryInMemoryImpl implements AbstractAppFactory {
    private UserMgmtFactory userMgmtFactory;
    private AuthenticationFactory authenticationFactory;
    private CategoryMgmtFactory categoryMgmtFactory;
    private ListingMgmtFactory listingMgmtFactory;

    public AppFactoryInMemoryImpl() {
        this.userMgmtFactory = new UserMgmtFactoryInMemoryImpl();
        this.authenticationFactory = new AuthenticationFactoryImpl(getUserMgmtFactory());
        this.categoryMgmtFactory = new CategoryMgmtFactoryInMemoryImpl(getAuthentiationFactory());
        this.listingMgmtFactory = new ListingMgmtFactoryInMemoryImpl(getAuthentiationFactory(), getCategoryMgmtFactory());
    }

    @Override
    public UserMgmtFactory getUserMgmtFactory() {
        return userMgmtFactory;
    }

    @Override
    public AuthenticationFactory getAuthentiationFactory() {
        return authenticationFactory;
    }

    @Override
    public CategoryMgmtFactory getCategoryMgmtFactory() {
        return categoryMgmtFactory;
    }

    @Override
    public ListingMgmtFactory getListingMgmtFactory() {
        return listingMgmtFactory;
    }
}
