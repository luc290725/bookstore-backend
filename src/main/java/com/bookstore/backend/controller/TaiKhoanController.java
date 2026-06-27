package com.bookstore.backend.controller;

import com.bookstore.backend.model.TaiKhoan;
import com.bookstore.backend.service.TaiKhoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taikhoan")
@CrossOrigin(origins = "*")
public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;

    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    // API lấy tất cả tài khoản
    @GetMapping
    public ResponseEntity<List<TaiKhoan>> getAllAccounts() {
        List<TaiKhoan> accounts = taiKhoanService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // API lấy tài khoản theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Integer id) {
        try {
            TaiKhoan taiKhoan = taiKhoanService.getAccountById(id);
            return ResponseEntity.ok(taiKhoan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy tài khoản có id: " + id);
        }
    }

    // API tìm kiếm tài khoản theo tên đăng nhập
    @GetMapping("/search")
    public ResponseEntity<List<TaiKhoan>> searchAccountsByUsername(@RequestParam String keyword) {
        List<TaiKhoan> accounts = taiKhoanService.searchAccountsByUsername(keyword);
        return ResponseEntity.ok(accounts);
    }

    // API đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TaiKhoan taiKhoan) {
        try {
            TaiKhoan result = taiKhoanService.login(
                    taiKhoan.getTenDangNhap(),
                    taiKhoan.getMatKhau()
            );
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Tên đăng nhập hoặc mật khẩu không đúng");
        }
    }

    // API thêm tài khoản mới
    @PostMapping
    public ResponseEntity<String> insertAccount(@Valid @RequestBody TaiKhoan taiKhoan) {
        try {
            String result = taiKhoanService.insertAccount(taiKhoan);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm tài khoản thất bại");
        }
    }

    // API cập nhật tài khoản
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Integer id,
                                                @Valid @RequestBody TaiKhoan taiKhoan) {
        try {
            String result = taiKhoanService.updateAccount(id, taiKhoan);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy tài khoản có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật tài khoản thất bại");
        }
    }

    // API xóa tài khoản
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
        try {
            String result = taiKhoanService.deleteAccount(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy tài khoản có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa tài khoản thất bại");
        }
    }
}