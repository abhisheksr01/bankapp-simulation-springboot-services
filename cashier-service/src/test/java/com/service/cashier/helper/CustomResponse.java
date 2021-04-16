package com.service.cashier.helper;

import java.util.Objects;

public class CustomResponse {
    private int statusCode;
    private String responseMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomResponse)) return false;
        CustomResponse that = (CustomResponse) o;
        return getStatusCode() == that.getStatusCode() &&
                Objects.equals(getResponseMessage(), that.getResponseMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusCode(), getResponseMessage());
    }
}
