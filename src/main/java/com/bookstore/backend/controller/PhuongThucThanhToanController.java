package com.bookstore.backend.controller;

import com.bookstore.backend.model.PhuongThucThanhToan;
import com.bookstore.backend.service.PhuongThucThanhToanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phuongthucthanhtoan")
@CrossOrigin(origins = "*")
public class PhuongThucThanhToanController {

    private final PhuongThucThanhToanService phuongThucThanhToanService;

    public PhuongThucThanhToanController(PhuongThucThanhToanService phuongThucThanhToanService) {
        this.phuongThucThanhToanService = phuongThucThanhToanService;
    }

    // API lấy tất cả phương thức thanh toán
    @GetMapping
    public ResponseEntity<List<PhuongThucThanhToan>> getAllPaymentMethods() {
        List<PhuongThucThanhToan> paymentMethods = phuongThucThanhToanService.getAllPaymentMethods();
        return ResponseEntity.ok(paymentMethods);
    }

    // API lấy phương thức thanh toán theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentMethodById(@PathVariable Integer id) {
        try {
            PhuongThucThanhToan phuongThucThanhToan = phuongThucThanhToanService.getPaymentMethodById(id);
            return ResponseEntity.ok(phuongThucThanhToan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy phương thức thanh toán có id: " + id);
        }
    }

    // API tìm kiếm phương thức thanh toán theo tên
    @GetMapping("/search")
    public ResponseEntity<List<PhuongThucThanhToan>> searchPaymentMethodsByName(@RequestParam String keyword) {
        List<PhuongThucThanhToan> paymentMethods = phuongThucThanhToanService.searchPaymentMethodsByName(keyword);
        return ResponseEntity.ok(paymentMethods);
    }

    // API thêm phương thức thanh toán mới
    @PostMapping
    public ResponseEntity<String> insertPaymentMethod(@Valid @RequestBody PhuongThucThanhToan phuongThucThanhToan) {
        try {
            String result = phuongThucThanhToanService.insertPaymentMethod(phuongThucThanhToan);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm phương thức thanh toán thất bại");
        }
    }

    // API cập nhật phương thức thanh toán
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePaymentMethod(@PathVariable Integer id,
                                                      @Valid @RequestBody PhuongThucThanhToan phuongThucThanhToan) {
        try {
            String result = phuongThucThanhToanService.updatePaymentMethod(id, phuongThucThanhToan);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy phương thức thanh toán có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật phương thức thanh toán thất bại");
        }
    }

    // API xóa phương thức thanh toán
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable Integer id) {
        try {
            String result = phuongThucThanhToanService.deletePaymentMethod(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy phương thức thanh toán có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa phương thức thanh toán thất bại");
        }
    }
}