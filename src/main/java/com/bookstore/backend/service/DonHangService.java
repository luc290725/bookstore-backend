package com.bookstore.backend.service;

import com.bookstore.backend.model.DonHang;
import com.bookstore.backend.repository.DonHangRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DonHangService {

    private final DonHangRepository donHangRepository;

    public DonHangService(DonHangRepository donHangRepository) {
        this.donHangRepository = donHangRepository;
    }

    // Lấy tất cả đơn hàng
    public List<DonHang> getAllOrders() {
        return donHangRepository.getAllOrders();
    }

    // Lấy đơn hàng theo id
    public DonHang getOrderById(Integer id) {
        DonHang donHang = donHangRepository.getOrderById(id);

        if (donHang == null) {
            throw new RuntimeException("Không tìm thấy đơn hàng có id: " + id);
        }

        return donHang;
    }

    // Lấy danh sách đơn hàng theo id khách hàng
    public List<DonHang> getOrdersByCustomerId(Integer idKhachHang) {
        return donHangRepository.getOrdersByCustomerId(idKhachHang);
    }

    // Lấy danh sách đơn hàng theo trạng thái
    public List<DonHang> getOrdersByStatus(String trangThai) {
        return donHangRepository.getOrdersByStatus(trangThai);
    }

    // Thêm đơn hàng mới
    public String insertOrder(DonHang donHang) {
        if (donHang.getNgayDat() == null) {
            donHang.setNgayDat(LocalDate.now());
        }

        if (donHang.getTongTien() == null) {
            donHang.setTongTien(BigDecimal.ZERO);
        }

        if (donHang.getTrangThai() == null || donHang.getTrangThai().trim().isEmpty()) {
            donHang.setTrangThai("CHO_XAC_NHAN");
        }

        int result = donHangRepository.insertOrder(
                donHang.getIdKhachHang(),
                donHang.getNgayDat(),
                donHang.getTongTien(),
                donHang.getTrangThai()
        );

        if (result > 0) {
            return "Thêm đơn hàng thành công";
        }

        return "Thêm đơn hàng thất bại";
    }

    // Cập nhật đơn hàng
    public String updateOrder(Integer id, DonHang donHangMoi) {
        // Kiểm tra xem đơn hàng có tồn tại không
        getOrderById(id);

        if (donHangMoi.getNgayDat() == null) {
            donHangMoi.setNgayDat(LocalDate.now());
        }

        if (donHangMoi.getTongTien() == null) {
            donHangMoi.setTongTien(BigDecimal.ZERO);
        }

        if (donHangMoi.getTrangThai() == null || donHangMoi.getTrangThai().trim().isEmpty()) {
            donHangMoi.setTrangThai("CHO_XAC_NHAN");
        }

        int result = donHangRepository.updateOrder(
                id,
                donHangMoi.getIdKhachHang(),
                donHangMoi.getNgayDat(),
                donHangMoi.getTongTien(),
                donHangMoi.getTrangThai()
        );

        if (result > 0) {
            return "Cập nhật đơn hàng thành công";
        }

        return "Cập nhật đơn hàng thất bại";
    }

    // Cập nhật trạng thái đơn hàng
    public String updateOrderStatus(Integer id, String trangThai) {
        // Kiểm tra xem đơn hàng có tồn tại không
        getOrderById(id);

        int result = donHangRepository.updateOrderStatus(id, trangThai);

        if (result > 0) {
            return "Cập nhật trạng thái đơn hàng thành công";
        }

        return "Cập nhật trạng thái đơn hàng thất bại";
    }

    // Xóa đơn hàng
    public String deleteOrder(Integer id) {
        // Kiểm tra xem đơn hàng có tồn tại không
        getOrderById(id);

        int result = donHangRepository.deleteOrder(id);

        if (result > 0) {
            return "Xóa đơn hàng thành công";
        }

        return "Xóa đơn hàng thất bại";
    }
}