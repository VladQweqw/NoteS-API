package com.example.noteS.CustomError;

public class CustomResponseError {
    private Integer status_code;
    private String message;

    public CustomResponseError(Integer status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public String getMessage() {
        return message;
    }
}
