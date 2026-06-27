package com.bookstore.backend.service;

import com.bookstore.backend.model.TacGia;
import com.bookstore.backend.repository.TacGiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacGiaService {

    private final TacGiaRepository tacGiaRepository;

    public TacGiaService(TacGiaRepository tacGiaRepository) {
        this.tacGiaRepository = tacGiaRepository;
    }

    // Lấy tất cả tác giả
    public List<TacGia> getAllAuthors() {
        return tacGiaRepository.getAllAuthors();
    }

    // Lấy tác giả theo id
    public TacGia getAuthorById(Integer id) {
        TacGia tacGia = tacGiaRepository.getAuthorById(id);

        if (tacGia == null) {
            throw new RuntimeException("Không tìm thấy tác giả có id: " + id);
        }

        return tacGia;
    }

    // Tìm kiếm tác giả theo tên
    public List<TacGia> searchAuthorsByName(String keyword) {
        return tacGiaRepository.searchAuthorsByName(keyword);
    }

    // Thêm tác giả mới
    public String insertAuthor(TacGia tacGia) {
        int result = tacGiaRepository.insertAuthor(
                tacGia.getTenTacGia(),
                tacGia.getTieuSu()
        );

        if (result > 0) {
            return "Thêm tác giả thành công";
        }

        return "Thêm tác giả thất bại";
    }

    // Cập nhật tác giả
    public String updateAuthor(Integer id, TacGia tacGiaMoi) {
        // Kiểm tra xem tác giả có tồn tại không
        getAuthorById(id);

        int result = tacGiaRepository.updateAuthor(
                id,
                tacGiaMoi.getTenTacGia(),
                tacGiaMoi.getTieuSu()
        );

        if (result > 0) {
            return "Cập nhật tác giả thành công";
        }

        return "Cập nhật tác giả thất bại";
    }

    // Xóa tác giả
    public String deleteAuthor(Integer id) {
        // Kiểm tra xem tác giả có tồn tại không
        getAuthorById(id);

        int result = tacGiaRepository.deleteAuthor(id);

        if (result > 0) {
            return "Xóa tác giả thành công";
        }

        return "Xóa tác giả thất bại";
    }
}