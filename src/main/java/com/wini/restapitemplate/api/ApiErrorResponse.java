package com.wini.restapitemplate.api;

import lombok.Data;

@Data
public class ApiErrorResponse {
    private String status;
    private String message;
    private Object detail;

    public ApiErrorResponse(String status, String message, Object detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }
}
