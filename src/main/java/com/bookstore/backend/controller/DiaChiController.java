package com.bookstore.backend.controller;

import com.bookstore.backend.model.DiaChi;
import com.bookstore.backend.service.DiaChiService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diachi")
@CrossOrigin(origins = "*")
public class DiaChiController {

    private final DiaChiService diaChiService;

    public DiaChiController(DiaChiService diaChiService) {
        this.diaChiService = diaChiService;
    }

    // API lấy tất cả địa chỉ
    @GetMapping
    public ResponseEntity<List<DiaChi>> getAllAddresses() {
        List<DiaChi> addresses = diaChiService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    // API lấy địa chỉ theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Integer id) {
        try {
            DiaChi diaChi = diaChiService.getAddressById(id);
            return ResponseEntity.ok(diaChi);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy địa chỉ có id: " + id);
        }
    }

    // API lấy danh sách địa chỉ theo id khách hàng
    @GetMapping("/khachhang/{idKhachHang}")
    public ResponseEntity<List<DiaChi>> getAddressesByCustomerId(@PathVariable Integer idKhachHang) {
        List<DiaChi> addresses = diaChiService.getAddressesByCustomerId(idKhachHang);
        return ResponseEntity.ok(addresses);
    }

    // API lấy địa chỉ mặc định của khách hàng
    @GetMapping("/khachhang/{idKhachHang}/macdinh")
    public ResponseEntity<?> getDefaultAddressByCustomerId(@PathVariable Integer idKhachHang) {
        try {
            DiaChi diaChi = diaChiService.getDefaultAddressByCustomerId(idKhachHang);
            return ResponseEntity.ok(diaChi);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy địa chỉ mặc định của khách hàng có id: " + idKhachHang);
        }
    }

    // API thêm địa chỉ mới
    @PostMapping
    public ResponseEntity<String> insertAddress(@Valid @RequestBody DiaChi diaChi) {
        try {
            String result = diaChiService.insertAddress(diaChi);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm địa chỉ thất bại");
        }
    }

    // API cập nhật địa chỉ
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable Integer id,
                                                @Valid @RequestBody DiaChi diaChi) {
        try {
            String result = diaChiService.updateAddress(id, diaChi);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy địa chỉ có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật địa chỉ thất bại");
        }
    }

    // API xóa địa chỉ
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id) {
        try {
            String result = diaChiService.deleteAddress(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy địa chỉ có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa địa chỉ thất bại");
        }
    }
}