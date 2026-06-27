package com.bookstore.backend.service;

import com.bookstore.backend.model.TheLoai;
import com.bookstore.backend.repository.TheLoaiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheLoaiService {

    private final TheLoaiRepository theLoaiRepository;

    public TheLoaiService(TheLoaiRepository theLoaiRepository) {
        this.theLoaiRepository = theLoaiRepository;
    }

    // Lấy tất cả thể loại
    public List<TheLoai> getAllCategories() {
        return theLoaiRepository.getAllCategories();
    }

    // Lấy thể loại theo id
    public TheLoai getCategoryById(Integer id) {
        TheLoai theLoai = theLoaiRepository.getCategoryById(id);

        if (theLoai == null) {
            throw new RuntimeException("Không tìm thấy thể loại có id: " + id);
        }

        return theLoai;
    }

    // Tìm kiếm thể loại theo tên
    public List<TheLoai> searchCategoriesByName(String keyword) {
        return theLoaiRepository.searchCategoriesByName(keyword);
    }

    // Thêm thể loại mới
    public String insertCategory(TheLoai theLoai) {
        int result = theLoaiRepository.insertCategory(
                theLoai.getTenTheLoai(),
                theLoai.getMoTa()
        );

        if (result > 0) {
            return "Thêm thể loại thành công";
        }

        return "Thêm thể loại thất bại";
    }

    // Cập nhật thể loại
    public String updateCategory(Integer id, TheLoai theLoaiMoi) {
        // Kiểm tra xem thể loại có tồn tại không
        getCategoryById(id);

        int result = theLoaiRepository.updateCategory(
                id,
                theLoaiMoi.getTenTheLoai(),
                theLoaiMoi.getMoTa()
        );

        if (result > 0) {
            return "Cập nhật thể loại thành công";
        }

        return "Cập nhật thể loại thất bại";
    }

    // Xóa thể loại
    public String deleteCategory(Integer id) {
        // Kiểm tra xem thể loại có tồn tại không
        getCategoryById(id);

        int result = theLoaiRepository.deleteCategory(id);

        if (result > 0) {
            return "Xóa thể loại thành công";
        }

        return "Xóa thể loại thất bại";
    }
}