package com.bookstore.backend.exception;

/**
 * Lớp đại diện cho một Lỗi (Exception) khi không tìm thấy tài nguyên
 * (ví dụ: Không tìm thấy sách, Không tìm thấy đơn hàng...)
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
