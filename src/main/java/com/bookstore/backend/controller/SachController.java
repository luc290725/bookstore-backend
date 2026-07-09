package com.bookstore.backend.controller;

import com.bookstore.backend.model.Sach;
import com.bookstore.backend.service.SachService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sach")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class SachController {

    private final SachService sachService;

    public SachController(SachService sachService) {
        this.sachService = sachService;
    }

    // API lấy tất cả sách
    @GetMapping
    public ResponseEntity<List<Sach>> getAllBooks() {
        List<Sach> books = sachService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // API lấy sách theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        try {
            Sach sach = sachService.getBookById(id);
            return ResponseEntity.ok(sach);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy sách có id: " + id);
        }
    }

    // API tìm kiếm sách theo tên
    @GetMapping("/search")
    public ResponseEntity<List<Sach>> searchBooksByName(@RequestParam String keyword) {
        List<Sach> books = sachService.searchBooksByName(keyword);
        return ResponseEntity.ok(books);
    }

    // API lấy sách theo thể loại
    @GetMapping("/theloai/{idTheLoai}")
    public ResponseEntity<List<Sach>> getBooksByCategory(@PathVariable Integer idTheLoai) {
        List<Sach> books = sachService.getBooksByCategory(idTheLoai);
        return ResponseEntity.ok(books);
    }

    // API lấy sách theo tác giả
    @GetMapping("/tacgia/{idTacGia}")
    public ResponseEntity<List<Sach>> getBooksByAuthor(@PathVariable Integer idTacGia) {
        List<Sach> books = sachService.getBooksByAuthor(idTacGia);
        return ResponseEntity.ok(books);
    }

    // API lấy sách theo nhà xuất bản
    @GetMapping("/nhaxuatban/{idNxb}")
    public ResponseEntity<List<Sach>> getBooksByPublisher(@PathVariable Integer idNxb) {
        List<Sach> books = sachService.getBooksByPublisher(idNxb);
        return ResponseEntity.ok(books);
    }

    // API thêm sách mới
    @PostMapping
    public ResponseEntity<String> insertBook(@Valid @RequestBody Sach sach) {
        try {
            String result = sachService.insertBook(sach);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm sách thất bại");
        }
    }

    // API cập nhật sách
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Integer id,
                                             @Valid @RequestBody Sach sach) {
        try {
            String result = sachService.updateBook(id, sach);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy sách có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật sách thất bại");
        }
    }

    // API xóa sách
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        try {
            String result = sachService.deleteBook(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("Không tìm thấy")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Không thể xóa sách này vì sách đã nằm trong lịch sử đơn hàng hoặc giỏ hàng.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa sách thất bại");
        }
    }
}