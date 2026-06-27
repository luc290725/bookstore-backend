package com.bookstore.backend.service;

import com.bookstore.backend.model.KhachHang;
import com.bookstore.backend.repository.KhachHangRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KhachHangService {

    private final KhachHangRepository khachHangRepository;

    public KhachHangService(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    // Lấy tất cả khách hàng
    public List<KhachHang> getAllCustomers() {
        return khachHangRepository.getAllCustomers();
    }

    // Lấy khách hàng theo id
    public KhachHang getCustomerById(Integer id) {
        KhachHang khachHang = khachHangRepository.getCustomerById(id);

        if (khachHang == null) {
            throw new RuntimeException("Không tìm thấy khách hàng có id: " + id);
        }

        return khachHang;
    }

    // Lấy khách hàng theo id tài khoản
    public KhachHang getCustomerByAccountId(Integer idTaiKhoan) {
        KhachHang khachHang = khachHangRepository.getCustomerByAccountId(idTaiKhoan);

        if (khachHang == null) {
            throw new RuntimeException("Không tìm thấy khách hàng có id tài khoản: " + idTaiKhoan);
        }

        return khachHang;
    }

    // Tìm kiếm khách hàng theo họ tên
    public List<KhachHang> searchCustomersByName(String keyword) {
        return khachHangRepository.searchCustomersByName(keyword);
    }

    // Thêm khách hàng mới
    public String insertCustomer(KhachHang khachHang) {
        if (khachHang.getNgayDangKy() == null) {
            khachHang.setNgayDangKy(LocalDate.now());
        }

        int result = khachHangRepository.insertCustomer(
                khachHang.getIdTaiKhoan(),
                khachHang.getHoTen(),
                khachHang.getSoDienThoai(),
                khachHang.getEmail(),
                khachHang.getNgaySinh(),
                khachHang.getGioiTinh(),
                khachHang.getNgayDangKy()
        );

        if (result > 0) {
            return "Thêm khách hàng thành công";
        }

        return "Thêm khách hàng thất bại";
    }

    // Cập nhật khách hàng
    public String updateCustomer(Integer id, KhachHang khachHangMoi) {
        // Kiểm tra xem khách hàng có tồn tại không
        getCustomerById(id);

        if (khachHangMoi.getNgayDangKy() == null) {
            khachHangMoi.setNgayDangKy(LocalDate.now());
        }

        int result = khachHangRepository.updateCustomer(
                id,
                khachHangMoi.getIdTaiKhoan(),
                khachHangMoi.getHoTen(),
                khachHangMoi.getSoDienThoai(),
                khachHangMoi.getEmail(),
                khachHangMoi.getNgaySinh(),
                khachHangMoi.getGioiTinh(),
                khachHangMoi.getNgayDangKy()
        );

        if (result > 0) {
            return "Cập nhật khách hàng thành công";
        }

        return "Cập nhật khách hàng thất bại";
    }

    // Xóa khách hàng
    public String deleteCustomer(Integer id) {
        // Kiểm tra xem khách hàng có tồn tại không
        getCustomerById(id);

        int result = khachHangRepository.deleteCustomer(id);

        if (result > 0) {
            return "Xóa khách hàng thành công";
        }

        return "Xóa khách hàng thất bại";
    }
}