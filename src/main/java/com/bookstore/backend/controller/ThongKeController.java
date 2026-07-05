package com.bookstore.backend.controller;

import com.bookstore.backend.service.ThongKeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST Controller cho phần báo cáo và thống kê
 */
@RestController
@RequestMapping("/api/thongke")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class ThongKeController {

    private final ThongKeService thongKeService;

    public ThongKeController(ThongKeService thongKeService) {
        this.thongKeService = thongKeService;
    }

    /**
     * API: Lấy báo cáo tổng quan
     * GET: http://localhost:8080/identity/api/thongke/tong-quan
     */
    @GetMapping("/tong-quan")
    public ResponseEntity<Map<String, Object>> getBaoCaoTongQuan() {
        Map<String, Object> ketQua = thongKeService.getBaoCaoTongQuan();
        return ResponseEntity.ok(ketQua);
    }
}
