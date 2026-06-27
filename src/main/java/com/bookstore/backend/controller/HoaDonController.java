package com.bookstore.backend.controller;

import com.bookstore.backend.model.HoaDon;
import com.bookstore.backend.service.HoaDonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hoadon")
@CrossOrigin(origins = "*")
public class HoaDonController {

    private final HoaDonService hoaDonService;

    public HoaDonController(HoaDonService hoaDonService) {
        this.hoaDonService = hoaDonService;
    }

    // API lấy tất cả hóa đơn
    @GetMapping
    public ResponseEntity<List<HoaDon>> getAllInvoices() {
        List<HoaDon> invoices = hoaDonService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    // API lấy hóa đơn theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Integer id) {
        try {
            HoaDon hoaDon = hoaDonService.getInvoiceById(id);
            return ResponseEntity.ok(hoaDon);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy hóa đơn có id: " + id);
        }
    }

    // API lấy hóa đơn theo id đơn hàng
    @GetMapping("/donhang/{idDonHang}")
    public ResponseEntity<?> getInvoiceByOrderId(@PathVariable Integer idDonHang) {
        try {
            HoaDon hoaDon = hoaDonService.getInvoiceByOrderId(idDonHang);
            return ResponseEntity.ok(hoaDon);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy hóa đơn của đơn hàng có id: " + idDonHang);
        }
    }

    // API lấy danh sách hóa đơn theo ngày lập
    @GetMapping("/ngaylap")
    public ResponseEntity<List<HoaDon>> getInvoicesByDate(@RequestParam LocalDate ngayLap) {
        List<HoaDon> invoices = hoaDonService.getInvoicesByDate(ngayLap);
        return ResponseEntity.ok(invoices);
    }

    // API thêm hóa đơn mới
    @PostMapping
    public ResponseEntity<String> insertInvoice(@Valid @RequestBody HoaDon hoaDon) {
        try {
            String result = hoaDonService.insertInvoice(hoaDon);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm hóa đơn thất bại");
        }
    }

    // API cập nhật hóa đơn
    @PutMapping("/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable Integer id,
                                                @Valid @RequestBody HoaDon hoaDon) {
        try {
            String result = hoaDonService.updateInvoice(id, hoaDon);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy hóa đơn có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật hóa đơn thất bại");
        }
    }

    // API xóa hóa đơn
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Integer id) {
        try {
            String result = hoaDonService.deleteInvoice(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy hóa đơn có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa hóa đơn thất bại");
        }
    }
}