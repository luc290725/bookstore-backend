package com.bookstore.backend.controller;

import com.bookstore.backend.model.TheLoai;
import com.bookstore.backend.service.TheLoaiService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theloai")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})
public class TheLoaiController {

    private final TheLoaiService theLoaiService;

    public TheLoaiController(TheLoaiService theLoaiService) {
        this.theLoaiService = theLoaiService;
    }

    // API lấy tất cả thể loại
    @GetMapping
    public ResponseEntity<List<TheLoai>> getAllCategories() {
        List<TheLoai> categories = theLoaiService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // API lấy thể loại theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        try {
            TheLoai theLoai = theLoaiService.getCategoryById(id);
            return ResponseEntity.ok(theLoai);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy thể loại có id: " + id);
        }
    }

    // API tìm kiếm thể loại theo tên
    @GetMapping("/search")
    public ResponseEntity<List<TheLoai>> searchCategoriesByName(@RequestParam String keyword) {
        List<TheLoai> categories = theLoaiService.searchCategoriesByName(keyword);
        return ResponseEntity.ok(categories);
    }

    // API thêm thể loại mới
    @PostMapping
    public ResponseEntity<String> insertCategory(@Valid @RequestBody TheLoai theLoai) {
        try {
            String result = theLoaiService.insertCategory(theLoai);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm thể loại thất bại");
        }
    }

    // API cập nhật thể loại
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id,
                                                 @Valid @RequestBody TheLoai theLoai) {
        try {
            String result = theLoaiService.updateCategory(id, theLoai);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy thể loại có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật thể loại thất bại");
        }
    }

    // API xóa thể loại
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        try {
            String result = theLoaiService.deleteCategory(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy thể loại có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa thể loại thất bại");
        }
    }
}