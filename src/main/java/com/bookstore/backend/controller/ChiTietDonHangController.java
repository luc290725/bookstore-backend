package com.bookstore.backend.controller;

import com.bookstore.backend.model.ChiTietDonHang;
import com.bookstore.backend.service.ChiTietDonHangService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chitietdonhang")
@CrossOrigin(origins = "*")
public class ChiTietDonHangController {

    private final ChiTietDonHangService chiTietDonHangService;

    public ChiTietDonHangController(ChiTietDonHangService chiTietDonHangService) {
        this.chiTietDonHangService = chiTietDonHangService;
    }

    // API lấy tất cả chi tiết đơn hàng
    @GetMapping
    public ResponseEntity<List<ChiTietDonHang>> getAllOrderDetails() {
        List<ChiTietDonHang> orderDetails = chiTietDonHangService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetails);
    }

    // API lấy danh sách chi tiết theo id đơn hàng
    @GetMapping("/donhang/{idDonHang}")
    public ResponseEntity<List<ChiTietDonHang>> getOrderDetailsByOrderId(@PathVariable Integer idDonHang) {
        List<ChiTietDonHang> orderDetails = chiTietDonHangService.getOrderDetailsByOrderId(idDonHang);
        return ResponseEntity.ok(orderDetails);
    }

    // API lấy một chi tiết đơn hàng theo id đơn hàng và id sách
    @GetMapping("/donhang/{idDonHang}/sach/{idSach}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Integer idDonHang,
                                            @PathVariable Integer idSach) {
        try {
            ChiTietDonHang chiTietDonHang = chiTietDonHangService.getOrderDetail(idDonHang, idSach);
            return ResponseEntity.ok(chiTietDonHang);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy chi tiết đơn hàng");
        }
    }

    // API thêm chi tiết đơn hàng mới
    @PostMapping
    public ResponseEntity<String> insertOrderDetail(@Valid @RequestBody ChiTietDonHang chiTietDonHang) {
        try {
            String result = chiTietDonHangService.insertOrderDetail(chiTietDonHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm chi tiết đơn hàng thất bại");
        }
    }

    // API cập nhật chi tiết đơn hàng
    @PutMapping("/donhang/{idDonHang}/sach/{idSach}")
    public ResponseEntity<String> updateOrderDetail(@PathVariable Integer idDonHang,
                                                    @PathVariable Integer idSach,
                                                    @Valid @RequestBody ChiTietDonHang chiTietDonHang) {
        try {
            String result = chiTietDonHangService.updateOrderDetail(idDonHang, idSach, chiTietDonHang);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy chi tiết đơn hàng");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật chi tiết đơn hàng thất bại");
        }
    }

    // API cập nhật số lượng sách trong đơn hàng
    @PutMapping("/donhang/{idDonHang}/sach/{idSach}/soluong")
    public ResponseEntity<String> updateQuantity(@PathVariable Integer idDonHang,
                                                 @PathVariable Integer idSach,
                                                 @RequestParam Integer soLuong) {
        try {
            String result = chiTietDonHangService.updateQuantity(idDonHang, idSach, soLuong);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy chi tiết đơn hàng");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật số lượng thất bại");
        }
    }

    // API xóa một chi tiết đơn hàng
    @DeleteMapping("/donhang/{idDonHang}/sach/{idSach}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Integer idDonHang,
                                                    @PathVariable Integer idSach) {
        try {
            String result = chiTietDonHangService.deleteOrderDetail(idDonHang, idSach);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy chi tiết đơn hàng");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa chi tiết đơn hàng thất bại");
        }
    }

    // API xóa toàn bộ chi tiết của một đơn hàng
    @DeleteMapping("/donhang/{idDonHang}")
    public ResponseEntity<String> deleteOrderDetailsByOrderId(@PathVariable Integer idDonHang) {
        try {
            String result = chiTietDonHangService.deleteOrderDetailsByOrderId(idDonHang);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa toàn bộ chi tiết đơn hàng thất bại");
        }
    }
}