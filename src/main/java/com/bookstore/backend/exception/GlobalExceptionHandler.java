package com.bookstore.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * THÀNH PHẦN XỬ LÝ CHUNG - Bắt tất cả các lỗi của hệ thống (Global Exception Handler)
 *
 * Mục đích: Quản lý lỗi tập trung. Bất kỳ lỗi nào ném ra trong toàn bộ hệ thống
 * đều sẽ chạy vào đây để xử lý và trả về JSON chuẩn xác cho người dùng.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý lỗi không tìm thấy tài nguyên (ví dụ: tìm sách ID=999 không có)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Xử lý lỗi dữ liệu không hợp lệ (Ví dụ: tên sách để trống)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Xử lý tất cả các lỗi Runtime khác (Ví dụ: lỗi code null pointer, chia cho 0...)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        // Ghi log ra màn hình console để bảo trì
        System.err.println("[LỖI HỆ THỐNG] Đã xảy ra lỗi: " + ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Bắt toàn bộ Exception còn lại
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        System.err.println("[LỖI KHÔNG XÁC ĐỊNH] " + ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Hệ thống đang gặp sự cố, vui lòng thử lại sau!"
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
