package com.bookstore.backend.service;

import com.bookstore.backend.model.TaiKhoan;
import com.bookstore.backend.repository.TaiKhoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {

    private final TaiKhoanRepository taiKhoanRepository;

    public TaiKhoanService(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    // Lấy tất cả tài khoản
    public List<TaiKhoan> getAllAccounts() {
        return taiKhoanRepository.getAllAccounts();
    }

    // Lấy tài khoản theo id
    public TaiKhoan getAccountById(Integer id) {
        TaiKhoan taiKhoan = taiKhoanRepository.getAccountById(id);

        if (taiKhoan == null) {
            throw new RuntimeException("Không tìm thấy tài khoản có id: " + id);
        }

        return taiKhoan;
    }

    // Tìm kiếm tài khoản theo tên đăng nhập
    public List<TaiKhoan> searchAccountsByUsername(String keyword) {
        return taiKhoanRepository.searchAccountsByUsername(keyword);
    }

    // Đăng nhập
    public TaiKhoan login(String tenDangNhap, String matKhau) {
        TaiKhoan taiKhoan = taiKhoanRepository.login(tenDangNhap, matKhau);

        if (taiKhoan == null) {
            throw new RuntimeException("Tên đăng nhập hoặc mật khẩu không đúng");
        }

        return taiKhoan;
    }

    // Thêm tài khoản mới
    public String insertAccount(TaiKhoan taiKhoan) {
        TaiKhoan taiKhoanTonTai = taiKhoanRepository.getAccountByUsername(taiKhoan.getTenDangNhap());

        if (taiKhoanTonTai != null) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        int result = taiKhoanRepository.insertAccount(
                taiKhoan.getTenDangNhap(),
                taiKhoan.getMatKhau(),
                taiKhoan.getEmail(),
                taiKhoan.getVaiTro()
        );

        if (result > 0) {
            return "Thêm tài khoản thành công";
        }

        return "Thêm tài khoản thất bại";
    }

    // Cập nhật tài khoản
    public String updateAccount(Integer id, TaiKhoan taiKhoanMoi) {
        // Kiểm tra xem tài khoản có tồn tại không
        getAccountById(id);

        int result = taiKhoanRepository.updateAccount(
                id,
                taiKhoanMoi.getTenDangNhap(),
                taiKhoanMoi.getMatKhau(),
                taiKhoanMoi.getEmail(),
                taiKhoanMoi.getVaiTro()
        );

        if (result > 0) {
            return "Cập nhật tài khoản thành công";
        }

        return "Cập nhật tài khoản thất bại";
    }

    // Xóa tài khoản
    public String deleteAccount(Integer id) {
        // Kiểm tra xem tài khoản có tồn tại không
        getAccountById(id);

        int result = taiKhoanRepository.deleteAccount(id);

        if (result > 0) {
            return "Xóa tài khoản thành công";
        }

        return "Xóa tài khoản thất bại";
    }
}