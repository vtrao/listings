package org.vtrao.listings.response;

public interface Response {
    String getStatus();
    String getMessage();
    String getHrMessage();
    int getCode();
}
