package org.vtrao.listings.response.impl;

import org.vtrao.listings.response.Response;

public class ListingCliResponse implements Response {
    private String message;
    private int code;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getStatus() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getHrMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }
}
