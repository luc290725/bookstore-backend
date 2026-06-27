package com.bookstore.backend.controller;

import com.bookstore.backend.model.DonHang;
import com.bookstore.backend.service.DonHangService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donhang")
@CrossOrigin(origins = "*")
public class DonHangController {

    private final DonHangService donHangService;

    public DonHangController(DonHangService donHangService) {
        this.donHangService = donHangService;
    }

    // API lấy tất cả đơn hàng
    @GetMapping
    public ResponseEntity<List<DonHang>> getAllOrders() {
        List<DonHang> orders = donHangService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // API lấy đơn hàng theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        try {
            DonHang donHang = donHangService.getOrderById(id);
            return ResponseEntity.ok(donHang);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đơn hàng có id: " + id);
        }
    }

    // API lấy danh sách đơn hàng theo id khách hàng
    @GetMapping("/khachhang/{idKhachHang}")
    public ResponseEntity<List<DonHang>> getOrdersByCustomerId(@PathVariable Integer idKhachHang) {
        List<DonHang> orders = donHangService.getOrdersByCustomerId(idKhachHang);
        return ResponseEntity.ok(orders);
    }

    // API lấy danh sách đơn hàng theo trạng thái
    @GetMapping("/trangthai/{trangThai}")
    public ResponseEntity<List<DonHang>> getOrdersByStatus(@PathVariable String trangThai) {
        List<DonHang> orders = donHangService.getOrdersByStatus(trangThai);
        return ResponseEntity.ok(orders);
    }

    // API thêm đơn hàng mới
    @PostMapping
    public ResponseEntity<String> insertOrder(@Valid @RequestBody DonHang donHang) {
        try {
            String result = donHangService.insertOrder(donHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm đơn hàng thất bại");
        }
    }

    // API cập nhật đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Integer id,
                                              @Valid @RequestBody DonHang donHang) {
        try {
            String result = donHangService.updateOrder(id, donHang);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đơn hàng có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật đơn hàng thất bại");
        }
    }

    // API cập nhật trạng thái đơn hàng
    @PutMapping("/{id}/trangthai")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Integer id,
                                                    @RequestParam String trangThai) {
        try {
            String result = donHangService.updateOrderStatus(id, trangThai);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đơn hàng có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật trạng thái đơn hàng thất bại");
        }
    }

    // API xóa đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        try {
            String result = donHangService.deleteOrder(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đơn hàng có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa đơn hàng thất bại");
        }
    }
}