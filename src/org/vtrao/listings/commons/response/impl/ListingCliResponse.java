package org.vtrao.listings.commons.response.impl;

import org.vtrao.listings.commons.response.Response;

public class ListingCliResponse implements Response {
    private String message;
    private int code;
    private String data;

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
    public String getData() {
        return data == null ? message : data;
    }

    @Override
    public int getCode() {
        return code;
    }
}
