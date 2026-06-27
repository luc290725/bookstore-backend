package com.bookstore.backend.service;

import com.bookstore.backend.model.Sach;
import com.bookstore.backend.repository.SachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SachService {

    private final SachRepository sachRepository;

    public SachService(SachRepository sachRepository) {
        this.sachRepository = sachRepository;
    }

    // Lấy tất cả sách
    public List<Sach> getAllBooks() {
        return sachRepository.getAllBooks();
    }

    // Lấy sách theo id
    public Sach getBookById(Integer id) {
        Sach sach = sachRepository.getBookById(id);

        if (sach == null) {
            throw new RuntimeException("Không tìm thấy sách có id: " + id);
        }

        return sach;
    }

    // Tìm kiếm sách theo tên
    public List<Sach> searchBooksByName(String keyword) {
        return sachRepository.searchBooksByName(keyword);
    }

    // Lấy sách theo thể loại
    public List<Sach> getBooksByCategory(Integer idTheLoai) {
        return sachRepository.getBooksByCategory(idTheLoai);
    }

    // Lấy sách theo tác giả
    public List<Sach> getBooksByAuthor(Integer idTacGia) {
        return sachRepository.getBooksByAuthor(idTacGia);
    }

    // Lấy sách theo nhà xuất bản
    public List<Sach> getBooksByPublisher(Integer idNxb) {
        return sachRepository.getBooksByPublisher(idNxb);
    }

    // Thêm sách mới
    public String insertBook(Sach sach) {
        int result = sachRepository.insertBook(
                sach.getTenSach(),
                sach.getGiaBan(),
                sach.getMoTa(),
                sach.getNamXuatBan(),
                sach.getSoTrang(),
                sach.getHinhAnh(),
                sach.getSoLuongTon(),
                sach.getIdTheLoai(),
                sach.getIdTacGia(),
                sach.getIdNxb()
        );

        if (result > 0) {
            return "Thêm sách thành công";
        }

        return "Thêm sách thất bại";
    }

    // Cập nhật sách
    public String updateBook(Integer id, Sach sachMoi) {
        // Kiểm tra xem sách có tồn tại không
        getBookById(id);

        int result = sachRepository.updateBook(
                id,
                sachMoi.getTenSach(),
                sachMoi.getGiaBan(),
                sachMoi.getMoTa(),
                sachMoi.getNamXuatBan(),
                sachMoi.getSoTrang(),
                sachMoi.getHinhAnh(),
                sachMoi.getSoLuongTon(),
                sachMoi.getIdTheLoai(),
                sachMoi.getIdTacGia(),
                sachMoi.getIdNxb()
        );

        if (result > 0) {
            return "Cập nhật sách thành công";
        }

        return "Cập nhật sách thất bại";
    }

    // Xóa sách
    public String deleteBook(Integer id) {
        // Kiểm tra xem sách có tồn tại không
        getBookById(id);

        int result = sachRepository.deleteBook(id);

        if (result > 0) {
            return "Xóa sách thành công";
        }

        return "Xóa sách thất bại";
    }
}