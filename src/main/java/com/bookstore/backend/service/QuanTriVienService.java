package com.bookstore.backend.service;

import com.bookstore.backend.model.QuanTriVien;
import com.bookstore.backend.repository.QuanTriVienRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuanTriVienService {

    private final QuanTriVienRepository quanTriVienRepository;

    public QuanTriVienService(QuanTriVienRepository quanTriVienRepository) {
        this.quanTriVienRepository = quanTriVienRepository;
    }

    // Lấy tất cả quản trị viên
    public List<QuanTriVien> getAllAdmins() {
        return quanTriVienRepository.getAllAdmins();
    }

    // Lấy quản trị viên theo id
    public QuanTriVien getAdminById(Integer id) {
        QuanTriVien quanTriVien = quanTriVienRepository.getAdminById(id);

        if (quanTriVien == null) {
            throw new RuntimeException("Không tìm thấy quản trị viên có id: " + id);
        }

        return quanTriVien;
    }

    // Lấy quản trị viên theo id tài khoản
    public QuanTriVien getAdminByAccountId(Integer idTaiKhoan) {
        QuanTriVien quanTriVien = quanTriVienRepository.getAdminByAccountId(idTaiKhoan);

        if (quanTriVien == null) {
            throw new RuntimeException("Không tìm thấy quản trị viên có id tài khoản: " + idTaiKhoan);
        }

        return quanTriVien;
    }

    // Tìm kiếm quản trị viên theo họ tên
    public List<QuanTriVien> searchAdminsByName(String keyword) {
        return quanTriVienRepository.searchAdminsByName(keyword);
    }

    // Thêm quản trị viên mới
    public String insertAdmin(QuanTriVien quanTriVien) {
        int result = quanTriVienRepository.insertAdmin(
                quanTriVien.getIdTaiKhoan(),
                quanTriVien.getHoTen(),
                quanTriVien.getEmail()
        );

        if (result > 0) {
            return "Thêm quản trị viên thành công";
        }

        return "Thêm quản trị viên thất bại";
    }

    // Cập nhật quản trị viên
    public String updateAdmin(Integer id, QuanTriVien quanTriVienMoi) {
        // Kiểm tra xem quản trị viên có tồn tại không
        getAdminById(id);

        int result = quanTriVienRepository.updateAdmin(
                id,
                quanTriVienMoi.getIdTaiKhoan(),
                quanTriVienMoi.getHoTen(),
                quanTriVienMoi.getEmail()
        );

        if (result > 0) {
            return "Cập nhật quản trị viên thành công";
        }

        return "Cập nhật quản trị viên thất bại";
    }

    // Xóa quản trị viên
    public String deleteAdmin(Integer id) {
        // Kiểm tra xem quản trị viên có tồn tại không
        getAdminById(id);

        int result = quanTriVienRepository.deleteAdmin(id);

        if (result > 0) {
            return "Xóa quản trị viên thành công";
        }

        return "Xóa quản trị viên thất bại";
    }
}