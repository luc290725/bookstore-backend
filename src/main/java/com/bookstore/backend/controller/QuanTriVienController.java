package com.bookstore.backend.controller;

import com.bookstore.backend.model.QuanTriVien;
import com.bookstore.backend.service.QuanTriVienService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quantrivien")
@CrossOrigin(origins = "*")
public class QuanTriVienController {

    private final QuanTriVienService quanTriVienService;

    public QuanTriVienController(QuanTriVienService quanTriVienService) {
        this.quanTriVienService = quanTriVienService;
    }

    // API lấy tất cả quản trị viên
    @GetMapping
    public ResponseEntity<List<QuanTriVien>> getAllAdmins() {
        List<QuanTriVien> admins = quanTriVienService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // API lấy quản trị viên theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Integer id) {
        try {
            QuanTriVien quanTriVien = quanTriVienService.getAdminById(id);
            return ResponseEntity.ok(quanTriVien);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy quản trị viên có id: " + id);
        }
    }

    // API lấy quản trị viên theo id tài khoản
    @GetMapping("/taikhoan/{idTaiKhoan}")
    public ResponseEntity<?> getAdminByAccountId(@PathVariable Integer idTaiKhoan) {
        try {
            QuanTriVien quanTriVien = quanTriVienService.getAdminByAccountId(idTaiKhoan);
            return ResponseEntity.ok(quanTriVien);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy quản trị viên có id tài khoản: " + idTaiKhoan);
        }
    }

    // API tìm kiếm quản trị viên theo họ tên
    @GetMapping("/search")
    public ResponseEntity<List<QuanTriVien>> searchAdminsByName(@RequestParam String keyword) {
        List<QuanTriVien> admins = quanTriVienService.searchAdminsByName(keyword);
        return ResponseEntity.ok(admins);
    }

    // API thêm quản trị viên mới
    @PostMapping
    public ResponseEntity<String> insertAdmin(@Valid @RequestBody QuanTriVien quanTriVien) {
        try {
            String result = quanTriVienService.insertAdmin(quanTriVien);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm quản trị viên thất bại");
        }
    }

    // API cập nhật quản trị viên
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdmin(@PathVariable Integer id,
                                              @Valid @RequestBody QuanTriVien quanTriVien) {
        try {
            String result = quanTriVienService.updateAdmin(id, quanTriVien);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy quản trị viên có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật quản trị viên thất bại");
        }
    }

    // API xóa quản trị viên
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer id) {
        try {
            String result = quanTriVienService.deleteAdmin(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy quản trị viên có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa quản trị viên thất bại");
        }
    }
}