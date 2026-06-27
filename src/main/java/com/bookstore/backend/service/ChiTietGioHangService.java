package com.bookstore.backend.service;

import com.bookstore.backend.model.ChiTietGioHang;
import com.bookstore.backend.repository.ChiTietGioHangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietGioHangService {

    private final ChiTietGioHangRepository chiTietGioHangRepository;

    public ChiTietGioHangService(ChiTietGioHangRepository chiTietGioHangRepository) {
        this.chiTietGioHangRepository = chiTietGioHangRepository;
    }

    // Lấy tất cả chi tiết giỏ hàng
    public List<ChiTietGioHang> getAllCartDetails() {
        return chiTietGioHangRepository.getAllCartDetails();
    }

    // Lấy danh sách chi tiết theo id giỏ hàng
    public List<ChiTietGioHang> getCartDetailsByCartId(Integer idGioHang) {
        return chiTietGioHangRepository.getCartDetailsByCartId(idGioHang);
    }

    // Lấy một chi tiết giỏ hàng theo id giỏ hàng và id sách
    public ChiTietGioHang getCartDetail(Integer idGioHang, Integer idSach) {
        ChiTietGioHang chiTietGioHang = chiTietGioHangRepository.getCartDetail(idGioHang, idSach);

        if (chiTietGioHang == null) {
            throw new RuntimeException("Không tìm thấy chi tiết giỏ hàng");
        }

        return chiTietGioHang;
    }

    // Thêm chi tiết giỏ hàng mới
    public String insertCartDetail(ChiTietGioHang chiTietGioHang) {
        ChiTietGioHang tonTai = chiTietGioHangRepository.getCartDetail(
                chiTietGioHang.getIdGioHang(),
                chiTietGioHang.getIdSach()
        );

        if (tonTai != null) {
            throw new RuntimeException("Sách này đã có trong giỏ hàng");
        }

        int result = chiTietGioHangRepository.insertCartDetail(
                chiTietGioHang.getIdGioHang(),
                chiTietGioHang.getIdSach(),
                chiTietGioHang.getSoLuong(),
                chiTietGioHang.getDonGia()
        );

        if (result > 0) {
            return "Thêm chi tiết giỏ hàng thành công";
        }

        return "Thêm chi tiết giỏ hàng thất bại";
    }

    // Cập nhật chi tiết giỏ hàng
    public String updateCartDetail(Integer idGioHang, Integer idSach, ChiTietGioHang chiTietGioHangMoi) {
        // Kiểm tra xem chi tiết giỏ hàng có tồn tại không
        getCartDetail(idGioHang, idSach);

        int result = chiTietGioHangRepository.updateCartDetail(
                idGioHang,
                idSach,
                chiTietGioHangMoi.getSoLuong(),
                chiTietGioHangMoi.getDonGia()
        );

        if (result > 0) {
            return "Cập nhật chi tiết giỏ hàng thành công";
        }

        return "Cập nhật chi tiết giỏ hàng thất bại";
    }

    // Cập nhật số lượng sách trong giỏ hàng
    public String updateQuantity(Integer idGioHang, Integer idSach, Integer soLuong) {
        // Kiểm tra xem chi tiết giỏ hàng có tồn tại không
        getCartDetail(idGioHang, idSach);

        int result = chiTietGioHangRepository.updateQuantity(idGioHang, idSach, soLuong);

        if (result > 0) {
            return "Cập nhật số lượng thành công";
        }

        return "Cập nhật số lượng thất bại";
    }

    // Xóa một sách khỏi giỏ hàng
    public String deleteCartDetail(Integer idGioHang, Integer idSach) {
        // Kiểm tra xem chi tiết giỏ hàng có tồn tại không
        getCartDetail(idGioHang, idSach);

        int result = chiTietGioHangRepository.deleteCartDetail(idGioHang, idSach);

        if (result > 0) {
            return "Xóa chi tiết giỏ hàng thành công";
        }

        return "Xóa chi tiết giỏ hàng thất bại";
    }

    // Xóa toàn bộ chi tiết của một giỏ hàng
    public String deleteCartDetailsByCartId(Integer idGioHang) {
        int result = chiTietGioHangRepository.deleteCartDetailsByCartId(idGioHang);

        if (result > 0) {
            return "Xóa toàn bộ chi tiết giỏ hàng thành công";
        }

        return "Không có chi tiết giỏ hàng để xóa";
    }
}