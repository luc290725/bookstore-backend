package com.bookstore.backend.controller;

import com.bookstore.backend.model.GioHang;
import com.bookstore.backend.service.GioHangService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/giohang")
@CrossOrigin(origins = "*")
public class GioHangController {

    private final GioHangService gioHangService;

    public GioHangController(GioHangService gioHangService) {
        this.gioHangService = gioHangService;
    }

    // API lấy tất cả giỏ hàng
    @GetMapping
    public ResponseEntity<List<GioHang>> getAllCarts() {
        List<GioHang> carts = gioHangService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    // API lấy giỏ hàng theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable Integer id) {
        try {
            GioHang gioHang = gioHangService.getCartById(id);
            return ResponseEntity.ok(gioHang);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy giỏ hàng có id: " + id);
        }
    }

    // API lấy giỏ hàng theo id khách hàng
    @GetMapping("/khachhang/{idKhachHang}")
    public ResponseEntity<?> getCartByCustomerId(@PathVariable Integer idKhachHang) {
        try {
            GioHang gioHang = gioHangService.getCartByCustomerId(idKhachHang);
            return ResponseEntity.ok(gioHang);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy giỏ hàng của khách hàng có id: " + idKhachHang);
        }
    }

    // API thêm giỏ hàng mới
    @PostMapping
    public ResponseEntity<String> insertCart(@Valid @RequestBody GioHang gioHang) {
        try {
            String result = gioHangService.insertCart(gioHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm giỏ hàng thất bại");
        }
    }

    // API cập nhật giỏ hàng
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCart(@PathVariable Integer id,
                                             @Valid @RequestBody GioHang gioHang) {
        try {
            String result = gioHangService.updateCart(id, gioHang);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy giỏ hàng có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật giỏ hàng thất bại");
        }
    }

    // API cập nhật tổng tiền giỏ hàng
    @PutMapping("/{id}/tongtien")
    public ResponseEntity<String> updateCartTotal(@PathVariable Integer id,
                                                  @RequestParam BigDecimal tongTien) {
        try {
            String result = gioHangService.updateCartTotal(id, tongTien);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy giỏ hàng có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật tổng tiền giỏ hàng thất bại");
        }
    }

    // API xóa giỏ hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Integer id) {
        try {
            String result = gioHangService.deleteCart(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy giỏ hàng có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa giỏ hàng thất bại");
        }
    }
}