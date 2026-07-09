package com.bookstore.backend.controller;

import com.bookstore.backend.model.NhaXuatBan;
import com.bookstore.backend.service.NhaXuatBanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhaxuatban")
@CrossOrigin(origins = "*")
public class NhaXuatBanController {

    private final NhaXuatBanService nhaXuatBanService;

    public NhaXuatBanController(NhaXuatBanService nhaXuatBanService) {
        this.nhaXuatBanService = nhaXuatBanService;
    }

    // API lấy tất cả nhà xuất bản
    @GetMapping
    public ResponseEntity<List<NhaXuatBan>> getAllPublishers() {
        List<NhaXuatBan> publishers = nhaXuatBanService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    // API lấy nhà xuất bản theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getPublisherById(@PathVariable Integer id) {
        try {
            NhaXuatBan nhaXuatBan = nhaXuatBanService.getPublisherById(id);
            return ResponseEntity.ok(nhaXuatBan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy nhà xuất bản có id: " + id);
        }
    }

    // API tìm kiếm nhà xuất bản theo tên
    @GetMapping("/search")
    public ResponseEntity<List<NhaXuatBan>> searchPublishersByName(@RequestParam String keyword) {
        List<NhaXuatBan> publishers = nhaXuatBanService.searchPublishersByName(keyword);
        return ResponseEntity.ok(publishers);
    }

    // API thêm nhà xuất bản mới
    @PostMapping
    public ResponseEntity<String> insertPublisher(@Valid @RequestBody NhaXuatBan nhaXuatBan) {
        try {
            String result = nhaXuatBanService.insertPublisher(nhaXuatBan);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Thêm nhà xuất bản thất bại");
        }
    }

    // API cập nhật nhà xuất bản
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePublisher(@PathVariable Integer id,
                                                  @Valid @RequestBody NhaXuatBan nhaXuatBan) {
        try {
            String result = nhaXuatBanService.updatePublisher(id, nhaXuatBan);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy nhà xuất bản có id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật nhà xuất bản thất bại");
        }
    }

    // API xóa nhà xuất bản
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable Integer id) {
        try {
            String result = nhaXuatBanService.deletePublisher(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("Không tìm thấy")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Không thể xóa nhà xuất bản này vì vẫn còn sách của họ trong hệ thống.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xóa nhà xuất bản thất bại");
        }
    }
}