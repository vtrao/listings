package org.vtrao.listings.response.impl;


import org.vtrao.listings.response.Response;

public class GenericResponse implements Response {

    private String status;
    private String message;
    private String hrMessage;
    private int code;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getHrMessage() {
        return hrMessage == null ? message : hrMessage;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHrMessage(String hrMessage) {
        this.hrMessage = hrMessage;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
