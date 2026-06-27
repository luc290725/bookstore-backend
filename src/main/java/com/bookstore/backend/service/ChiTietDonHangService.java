package com.bookstore.backend.service;

import com.bookstore.backend.model.ChiTietDonHang;
import com.bookstore.backend.repository.ChiTietDonHangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietDonHangService {

    private final ChiTietDonHangRepository chiTietDonHangRepository;

    public ChiTietDonHangService(ChiTietDonHangRepository chiTietDonHangRepository) {
        this.chiTietDonHangRepository = chiTietDonHangRepository;
    }

    // Lấy tất cả chi tiết đơn hàng
    public List<ChiTietDonHang> getAllOrderDetails() {
        return chiTietDonHangRepository.getAllOrderDetails();
    }

    // Lấy danh sách chi tiết theo id đơn hàng
    public List<ChiTietDonHang> getOrderDetailsByOrderId(Integer idDonHang) {
        return chiTietDonHangRepository.getOrderDetailsByOrderId(idDonHang);
    }

    // Lấy một chi tiết đơn hàng theo id đơn hàng và id sách
    public ChiTietDonHang getOrderDetail(Integer idDonHang, Integer idSach) {
        ChiTietDonHang chiTietDonHang = chiTietDonHangRepository.getOrderDetail(idDonHang, idSach);

        if (chiTietDonHang == null) {
            throw new RuntimeException("Không tìm thấy chi tiết đơn hàng");
        }

        return chiTietDonHang;
    }

    // Thêm chi tiết đơn hàng mới
    public String insertOrderDetail(ChiTietDonHang chiTietDonHang) {
        ChiTietDonHang tonTai = chiTietDonHangRepository.getOrderDetail(
                chiTietDonHang.getIdDonHang(),
                chiTietDonHang.getIdSach()
        );

        if (tonTai != null) {
            throw new RuntimeException("Sách này đã có trong chi tiết đơn hàng");
        }

        int result = chiTietDonHangRepository.insertOrderDetail(
                chiTietDonHang.getIdDonHang(),
                chiTietDonHang.getIdSach(),
                chiTietDonHang.getSoLuong(),
                chiTietDonHang.getDonGia()
        );

        if (result > 0) {
            return "Thêm chi tiết đơn hàng thành công";
        }

        return "Thêm chi tiết đơn hàng thất bại";
    }

    // Cập nhật chi tiết đơn hàng
    public String updateOrderDetail(Integer idDonHang, Integer idSach, ChiTietDonHang chiTietDonHangMoi) {
        // Kiểm tra xem chi tiết đơn hàng có tồn tại không
        getOrderDetail(idDonHang, idSach);

        int result = chiTietDonHangRepository.updateOrderDetail(
                idDonHang,
                idSach,
                chiTietDonHangMoi.getSoLuong(),
                chiTietDonHangMoi.getDonGia()
        );

        if (result > 0) {
            return "Cập nhật chi tiết đơn hàng thành công";
        }

        return "Cập nhật chi tiết đơn hàng thất bại";
    }

    // Cập nhật số lượng sách trong đơn hàng
    public String updateQuantity(Integer idDonHang, Integer idSach, Integer soLuong) {
        // Kiểm tra xem chi tiết đơn hàng có tồn tại không
        getOrderDetail(idDonHang, idSach);

        int result = chiTietDonHangRepository.updateQuantity(idDonHang, idSach, soLuong);

        if (result > 0) {
            return "Cập nhật số lượng thành công";
        }

        return "Cập nhật số lượng thất bại";
    }

    // Xóa một chi tiết đơn hàng
    public String deleteOrderDetail(Integer idDonHang, Integer idSach) {
        // Kiểm tra xem chi tiết đơn hàng có tồn tại không
        getOrderDetail(idDonHang, idSach);

        int result = chiTietDonHangRepository.deleteOrderDetail(idDonHang, idSach);

        if (result > 0) {
            return "Xóa chi tiết đơn hàng thành công";
        }

        return "Xóa chi tiết đơn hàng thất bại";
    }

    // Xóa toàn bộ chi tiết của một đơn hàng
    public String deleteOrderDetailsByOrderId(Integer idDonHang) {
        int result = chiTietDonHangRepository.deleteOrderDetailsByOrderId(idDonHang);

        if (result > 0) {
            return "Xóa toàn bộ chi tiết đơn hàng thành công";
        }

        return "Không có chi tiết đơn hàng để xóa";
    }
}