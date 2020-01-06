package org.vtrao.listings.commons.factory;

public interface AbstractAppFactory {
    UserMgmtFactory getUserMgmtFactory();
    AuthenticationFactory getAuthentiationFactory();
    CategoryMgmtFactory getCategoryMgmtFactory();
    ListingMgmtFactory getListingMgmtFactory();
}
