package com.bookstore.backend.controller;

import com.bookstore.backend.model.VanChuyen;
import com.bookstore.backend.service.VanChuyenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vanchuyen")
@CrossOrigin(origins = "*")
public class VanChuyenController {

    private final VanChuyenService vanChuyenService;

    public VanChuyenController(VanChuyenService vanChuyenService) {
        this.vanChuyenService = vanChuyenService;
    }

    // API lấy tất cả vận chuyển
    @GetMapping
    public ResponseEntity<List<VanChuyen>> getAllShippings() {
        List<VanChuyen> shippings = vanChuyenService.getAllShippings();
        return ResponseEntity.ok(shippings);
    }

    // API lấy vận chuyển theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getShippingById(@PathVariable Integer id) {
        try {
            VanChuyen vanChuyen = vanChuyenService.getShippingById(id);
            return ResponseEntity.ok(vanChuyen);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy vận chuyển có id: " + id);
        }
    }

    // API tìm kiếm vận chuyển theo đơn vị
    @GetMapping("/search")
    public ResponseEntity<List<VanChuyen>> searchShippingsByUnit(@RequestParam String keyword) {
        List<VanChuyen> shippings = vanChuyenService.searchShippingsByUnit(keyword);
        return ResponseEntity.ok(shippings);
    }

    // API thêm vận chuyển mới
    @PostMapping
    public ResponseEntity<String> insertShipping(@Valid @RequestBody VanChuyen vanChuyen) {
        try {
            String result = vanChuyenService.insertShipping(vanChuyen);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm vận chuyển thất bại");
        }
    }

    // API cập nhật vận chuyển
    @PutMapping("/{id}")
    public ResponseEntity<String> updateShipping(@PathVariable Integer id,
                                                 @Valid @RequestBody VanChuyen vanChuyen) {
        try {
            String result = vanChuyenService.updateShipping(id, vanChuyen);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy vận chuyển có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật vận chuyển thất bại");
        }
    }

    // API xóa vận chuyển
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipping(@PathVariable Integer id) {
        try {
            String result = vanChuyenService.deleteShipping(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy vận chuyển có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa vận chuyển thất bại");
        }
    }
}