package org.example.exception;

import java.time.Instant;


public class ErrorResponse {
    private int status;
    private String message;
    private Instant time;


    public ErrorResponse(int status, String message, Instant time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTime() {
        return time;
    }
}
