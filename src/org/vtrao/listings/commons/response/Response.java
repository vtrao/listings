package org.vtrao.listings.commons.response;

public interface Response {
    String getStatus();
    String getMessage();
    String getHrMessage();
    String getData();
    int getCode();
}
