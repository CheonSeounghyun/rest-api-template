package com.wini.restapitemplate.api;

import lombok.Data;

@Data
public class ApiResponse {
    private String status;
    private String message;
    private String detail;
    private Object data;

    public ApiResponse(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }

}
