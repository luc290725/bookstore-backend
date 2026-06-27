package com.bookstore.backend.service;

import com.bookstore.backend.model.GioHang;
import com.bookstore.backend.repository.GioHangRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GioHangService {

    private final GioHangRepository gioHangRepository;

    public GioHangService(GioHangRepository gioHangRepository) {
        this.gioHangRepository = gioHangRepository;
    }

    // Lấy tất cả giỏ hàng
    public List<GioHang> getAllCarts() {
        return gioHangRepository.getAllCarts();
    }

    // Lấy giỏ hàng theo id
    public GioHang getCartById(Integer id) {
        GioHang gioHang = gioHangRepository.getCartById(id);

        if (gioHang == null) {
            throw new RuntimeException("Không tìm thấy giỏ hàng có id: " + id);
        }

        return gioHang;
    }

    // Lấy giỏ hàng theo id khách hàng
    public GioHang getCartByCustomerId(Integer idKhachHang) {
        GioHang gioHang = gioHangRepository.getCartByCustomerId(idKhachHang);

        if (gioHang == null) {
            throw new RuntimeException("Không tìm thấy giỏ hàng của khách hàng có id: " + idKhachHang);
        }

        return gioHang;
    }

    // Thêm giỏ hàng mới
    public String insertCart(GioHang gioHang) {
        if (gioHang.getTongTien() == null) {
            gioHang.setTongTien(BigDecimal.ZERO);
        }

        int result = gioHangRepository.insertCart(
                gioHang.getIdKhachHang(),
                gioHang.getTongTien()
        );

        if (result > 0) {
            return "Thêm giỏ hàng thành công";
        }

        return "Thêm giỏ hàng thất bại";
    }

    // Cập nhật giỏ hàng
    public String updateCart(Integer id, GioHang gioHangMoi) {
        // Kiểm tra xem giỏ hàng có tồn tại không
        getCartById(id);

        if (gioHangMoi.getTongTien() == null) {
            gioHangMoi.setTongTien(BigDecimal.ZERO);
        }

        int result = gioHangRepository.updateCart(
                id,
                gioHangMoi.getIdKhachHang(),
                gioHangMoi.getTongTien()
        );

        if (result > 0) {
            return "Cập nhật giỏ hàng thành công";
        }

        return "Cập nhật giỏ hàng thất bại";
    }

    // Cập nhật tổng tiền giỏ hàng
    public String updateCartTotal(Integer id, BigDecimal tongTien) {
        // Kiểm tra xem giỏ hàng có tồn tại không
        getCartById(id);

        int result = gioHangRepository.updateCartTotal(id, tongTien);

        if (result > 0) {
            return "Cập nhật tổng tiền giỏ hàng thành công";
        }

        return "Cập nhật tổng tiền giỏ hàng thất bại";
    }

    // Xóa giỏ hàng
    public String deleteCart(Integer id) {
        // Kiểm tra xem giỏ hàng có tồn tại không
        getCartById(id);

        int result = gioHangRepository.deleteCart(id);

        if (result > 0) {
            return "Xóa giỏ hàng thành công";
        }

        return "Xóa giỏ hàng thất bại";
    }
}