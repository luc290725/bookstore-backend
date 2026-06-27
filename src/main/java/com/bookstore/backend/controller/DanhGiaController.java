package com.bookstore.backend.controller;

import com.bookstore.backend.model.DanhGia;
import com.bookstore.backend.service.DanhGiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/danhgia")
@CrossOrigin(origins = "*")
public class DanhGiaController {

    private final DanhGiaService danhGiaService;

    public DanhGiaController(DanhGiaService danhGiaService) {
        this.danhGiaService = danhGiaService;
    }

    // API lấy tất cả đánh giá
    @GetMapping
    public ResponseEntity<List<DanhGia>> getAllReviews() {
        List<DanhGia> reviews = danhGiaService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    // API lấy đánh giá theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Integer id) {
        try {
            DanhGia danhGia = danhGiaService.getReviewById(id);
            return ResponseEntity.ok(danhGia);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đánh giá có id: " + id);
        }
    }

    // API lấy danh sách đánh giá theo id sách
    @GetMapping("/sach/{idSach}")
    public ResponseEntity<List<DanhGia>> getReviewsByBookId(@PathVariable Integer idSach) {
        List<DanhGia> reviews = danhGiaService.getReviewsByBookId(idSach);
        return ResponseEntity.ok(reviews);
    }

    // API lấy danh sách đánh giá theo id khách hàng
    @GetMapping("/khachhang/{idKhachHang}")
    public ResponseEntity<List<DanhGia>> getReviewsByCustomerId(@PathVariable Integer idKhachHang) {
        List<DanhGia> reviews = danhGiaService.getReviewsByCustomerId(idKhachHang);
        return ResponseEntity.ok(reviews);
    }

    // API lấy đánh giá của một khách hàng cho một sách
    @GetMapping("/khachhang/{idKhachHang}/sach/{idSach}")
    public ResponseEntity<?> getReviewByCustomerAndBook(@PathVariable Integer idKhachHang,
                                                        @PathVariable Integer idSach) {
        try {
            DanhGia danhGia = danhGiaService.getReviewByCustomerAndBook(idKhachHang, idSach);
            return ResponseEntity.ok(danhGia);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đánh giá của khách hàng cho sách này");
        }
    }

    // API lấy danh sách đánh giá theo số sao
    @GetMapping("/sosao/{soSao}")
    public ResponseEntity<List<DanhGia>> getReviewsByRating(@PathVariable Integer soSao) {
        List<DanhGia> reviews = danhGiaService.getReviewsByRating(soSao);
        return ResponseEntity.ok(reviews);
    }

    // API thêm đánh giá mới
    @PostMapping
    public ResponseEntity<String> insertReview(@Valid @RequestBody DanhGia danhGia) {
        try {
            String result = danhGiaService.insertReview(danhGia);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm đánh giá thất bại");
        }
    }

    // API cập nhật đánh giá
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Integer id,
                                               @Valid @RequestBody DanhGia danhGia) {
        try {
            String result = danhGiaService.updateReview(id, danhGia);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đánh giá có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật đánh giá thất bại");
        }
    }

    // API xóa đánh giá
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer id) {
        try {
            String result = danhGiaService.deleteReview(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy đánh giá có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa đánh giá thất bại");
        }
    }
}