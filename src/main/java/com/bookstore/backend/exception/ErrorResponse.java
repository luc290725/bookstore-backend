package com.bookstore.backend.exception;

import java.time.LocalDateTime;

/**
 * Lớp đại diện cho cấu trúc trả về khi có lỗi xảy ra (JSON format)
 */
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
