package com.bookstore.backend.controller;

import com.bookstore.backend.model.TacGia;
import com.bookstore.backend.service.TacGiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tacgia")
@CrossOrigin(origins = "*")
public class TacGiaController {

    private final TacGiaService tacGiaService;

    public TacGiaController(TacGiaService tacGiaService) {
        this.tacGiaService = tacGiaService;
    }

    // API lấy tất cả tác giả
    @GetMapping
    public ResponseEntity<List<TacGia>> getAllAuthors() {
        List<TacGia> authors = tacGiaService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // API lấy tác giả theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Integer id) {
        try {
            TacGia tacGia = tacGiaService.getAuthorById(id);
            return ResponseEntity.ok(tacGia);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy tác giả có id: " + id);
        }
    }

    // API tìm kiếm tác giả theo tên
    @GetMapping("/search")
    public ResponseEntity<List<TacGia>> searchAuthorsByName(@RequestParam String keyword) {
        List<TacGia> authors = tacGiaService.searchAuthorsByName(keyword);
        return ResponseEntity.ok(authors);
    }

    // API thêm tác giả mới
    @PostMapping
    public ResponseEntity<String> insertAuthor(@Valid @RequestBody TacGia tacGia) {
        try {
            String result = tacGiaService.insertAuthor(tacGia);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm tác giả thất bại");
        }
    }

    // API cập nhật tác giả
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable Integer id,
                                               @Valid @RequestBody TacGia tacGia) {
        try {
            String result = tacGiaService.updateAuthor(id, tacGia);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy tác giả có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật tác giả thất bại");
        }
    }

    // API xóa tác giả
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        try {
            String result = tacGiaService.deleteAuthor(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("Không tìm thấy")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Không thể xóa tác giả này vì vẫn còn sách của tác giả trong hệ thống.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa tác giả thất bại");
        }
    }
}