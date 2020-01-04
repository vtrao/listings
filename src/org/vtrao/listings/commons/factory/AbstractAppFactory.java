package org.vtrao.listings.commons.factory;

import org.vtrao.listings.commons.authentication.impl.Authentication;

public interface AbstractAppFactory {
    UserMgmtFactory getUserMgmtFactory();
    AuthenticationFactory getAuthentiationFactory();
    CategoryMgmtFactory getCategoryMgmtFactory();
    ListingMgmtFactory getListingMgmtFactory();
}
