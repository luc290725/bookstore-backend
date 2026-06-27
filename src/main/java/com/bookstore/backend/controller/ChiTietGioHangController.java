package com.bookstore.backend.controller;

import com.bookstore.backend.model.ChiTietGioHang;
import com.bookstore.backend.service.ChiTietGioHangService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chitietgiohang")
@CrossOrigin(origins = "*")
public class ChiTietGioHangController {

    private final ChiTietGioHangService chiTietGioHangService;

    public ChiTietGioHangController(ChiTietGioHangService chiTietGioHangService) {
        this.chiTietGioHangService = chiTietGioHangService;
    }

    // API lấy tất cả chi tiết giỏ hàng
    @GetMapping
    public ResponseEntity<List<ChiTietGioHang>> getAllCartDetails() {
        List<ChiTietGioHang> cartDetails = chiTietGioHangService.getAllCartDetails();
        return ResponseEntity.ok(cartDetails);
    }

    // API lấy danh sách chi tiết theo id giỏ hàng
    @GetMapping("/giohang/{idGioHang}")
    public ResponseEntity<List<ChiTietGioHang>> getCartDetailsByCartId(@PathVariable Integer idGioHang) {
        List<ChiTietGioHang> cartDetails = chiTietGioHangService.getCartDetailsByCartId(idGioHang);
        return ResponseEntity.ok(cartDetails);
    }

    // API lấy một chi tiết giỏ hàng theo id giỏ hàng và id sách
    @GetMapping("/giohang/{idGioHang}/sach/{idSach}")
    public ResponseEntity<?> getCartDetail(@PathVariable Integer idGioHang,
                                           @PathVariable Integer idSach) {
        try {
            ChiTietGioHang chiTietGioHang = chiTietGioHangService.getCartDetail(idGioHang, idSach);
            return ResponseEntity.ok(chiTietGioHang);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy chi tiết giỏ hàng");
        }
    }

    // API thêm chi tiết giỏ hàng mới
    @PostMapping
    public ResponseEntity<String> insertCartDetail(@Valid @RequestBody ChiTietGioHang chiTietGioHang) {
        try {
            String result = chiTietGioHangService.insertCartDetail(chiTietGioHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm chi tiết giỏ hàng thất bại");
        }
    }

    // API cập nhật chi tiết giỏ hàng
    @PutMapping("/giohang/{idGioHang}/sach/{idSach}")
    public ResponseEntity<String> updateCartDetail(@PathVariable Integer idGioHang,
                                                   @PathVariable Integer idSach,
                                                   @Valid @RequestBody ChiTietGioHang chiTietGioHang) {
        try {
            String result = chiTietGioHangService.updateCartDetail(idGioHang, idSach, chiTietGioHang);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy chi tiết giỏ hàng");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật chi tiết giỏ hàng thất bại");
        }
    }

    // API cập nhật số lượng sách trong giỏ hàng
    @PutMapping("/giohang/{idGioHang}/sach/{idSach}/soluong")
    public ResponseEntity<String> updateQuantity(@PathVariable Integer idGioHang,
                                                 @PathVariable Integer idSach,
                                                 @RequestParam Integer soLuong) {
        try {
            String result = chiTietGioHangService.updateQuantity(idGioHang, idSach, soLuong);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy chi tiết giỏ hàng");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật số lượng thất bại");
        }
    }

    // API xóa một sách khỏi giỏ hàng
    @DeleteMapping("/giohang/{idGioHang}/sach/{idSach}")
    public ResponseEntity<String> deleteCartDetail(@PathVariable Integer idGioHang,
                                                   @PathVariable Integer idSach) {
        try {
            String result = chiTietGioHangService.deleteCartDetail(idGioHang, idSach);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy chi tiết giỏ hàng");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa chi tiết giỏ hàng thất bại");
        }
    }

    // API xóa toàn bộ chi tiết của một giỏ hàng
    @DeleteMapping("/giohang/{idGioHang}")
    public ResponseEntity<String> deleteCartDetailsByCartId(@PathVariable Integer idGioHang) {
        try {
            String result = chiTietGioHangService.deleteCartDetailsByCartId(idGioHang);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa toàn bộ chi tiết giỏ hàng thất bại");
        }
    }
}