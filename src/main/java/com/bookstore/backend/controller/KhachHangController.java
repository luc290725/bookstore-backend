package com.bookstore.backend.controller;

import com.bookstore.backend.model.KhachHang;
import com.bookstore.backend.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khachhang")
@CrossOrigin(origins = "*")
public class KhachHangController {

    private final KhachHangService khachHangService;

    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    // API lấy tất cả khách hàng
    @GetMapping
    public ResponseEntity<List<KhachHang>> getAllCustomers() {
        List<KhachHang> customers = khachHangService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // API lấy khách hàng theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        try {
            KhachHang khachHang = khachHangService.getCustomerById(id);
            return ResponseEntity.ok(khachHang);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy khách hàng có id: " + id);
        }
    }

    // API lấy khách hàng theo id tài khoản
    @GetMapping("/taikhoan/{idTaiKhoan}")
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Integer idTaiKhoan) {
        try {
            KhachHang khachHang = khachHangService.getCustomerByAccountId(idTaiKhoan);
            return ResponseEntity.ok(khachHang);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy khách hàng có id tài khoản: " + idTaiKhoan);
        }
    }

    // API tìm kiếm khách hàng theo họ tên
    @GetMapping("/search")
    public ResponseEntity<List<KhachHang>> searchCustomersByName(@RequestParam String keyword) {
        List<KhachHang> customers = khachHangService.searchCustomersByName(keyword);
        return ResponseEntity.ok(customers);
    }

    // API thêm khách hàng mới
    @PostMapping
    public ResponseEntity<String> insertCustomer(@Valid @RequestBody KhachHang khachHang) {
        try {
            String result = khachHangService.insertCustomer(khachHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm khách hàng thất bại");
        }
    }

    // API cập nhật khách hàng
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer id,
                                                 @Valid @RequestBody KhachHang khachHang) {
        try {
            String result = khachHangService.updateCustomer(id, khachHang);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy khách hàng có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật khách hàng thất bại");
        }
    }

    // API xóa khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        try {
            String result = khachHangService.deleteCustomer(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy khách hàng có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa khách hàng thất bại");
        }
    }
}