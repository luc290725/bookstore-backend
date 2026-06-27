package com.bookstore.backend.controller;

import com.bookstore.backend.model.KhuyenMai;
import com.bookstore.backend.service.KhuyenMaiService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khuyenmai")
@CrossOrigin(origins = "*")
public class KhuyenMaiController {

    private final KhuyenMaiService khuyenMaiService;

    public KhuyenMaiController(KhuyenMaiService khuyenMaiService) {
        this.khuyenMaiService = khuyenMaiService;
    }

    // API lấy tất cả khuyến mãi
    @GetMapping
    public ResponseEntity<List<KhuyenMai>> getAllDiscounts() {
        List<KhuyenMai> discounts = khuyenMaiService.getAllDiscounts();
        return ResponseEntity.ok(discounts);
    }

    // API lấy khuyến mãi theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountById(@PathVariable Integer id) {
        try {
            KhuyenMai khuyenMai = khuyenMaiService.getDiscountById(id);
            return ResponseEntity.ok(khuyenMai);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy khuyến mãi có id: " + id);
        }
    }

    // API lấy khuyến mãi theo mã
    @GetMapping("/ma/{maKhuyenMai}")
    public ResponseEntity<?> getDiscountByCode(@PathVariable String maKhuyenMai) {
        try {
            KhuyenMai khuyenMai = khuyenMaiService.getDiscountByCode(maKhuyenMai);
            return ResponseEntity.ok(khuyenMai);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy khuyến mãi có mã: " + maKhuyenMai);
        }
    }

    // API tìm kiếm khuyến mãi theo mã
    @GetMapping("/search")
    public ResponseEntity<List<KhuyenMai>> searchDiscountsByCode(@RequestParam String keyword) {
        List<KhuyenMai> discounts = khuyenMaiService.searchDiscountsByCode(keyword);
        return ResponseEntity.ok(discounts);
    }

    // API lấy danh sách khuyến mãi còn hiệu lực
    @GetMapping("/active")
    public ResponseEntity<List<KhuyenMai>> getActiveDiscounts() {
        List<KhuyenMai> discounts = khuyenMaiService.getActiveDiscounts();
        return ResponseEntity.ok(discounts);
    }

    // API thêm khuyến mãi mới
    @PostMapping
    public ResponseEntity<String> insertDiscount(@Valid @RequestBody KhuyenMai khuyenMai) {
        try {
            String result = khuyenMaiService.insertDiscount(khuyenMai);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm khuyến mãi thất bại");
        }
    }

    // API cập nhật khuyến mãi
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDiscount(@PathVariable Integer id,
                                                 @Valid @RequestBody KhuyenMai khuyenMai) {
        try {
            String result = khuyenMaiService.updateDiscount(id, khuyenMai);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật khuyến mãi thất bại");
        }
    }

    // API xóa khuyến mãi
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiscount(@PathVariable Integer id) {
        try {
            String result = khuyenMaiService.deleteDiscount(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy khuyến mãi có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa khuyến mãi thất bại");
        }
    }
}