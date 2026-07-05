package com.bookstore.backend.pattern.strategy;

import java.math.BigDecimal;

/**
 * Context Class (Lớp ngữ cảnh) sử dụng Strategy
 *
 * Class này nhận một GiamGiaStrategy bất kỳ từ bên ngoài truyền vào,
 * và sử dụng nó để tính toán mà không cần biết chi tiết bên trong thuật toán.
 */
public class HoaDonThanhToan {

    private BigDecimal tongTienGoc;
    private GiamGiaStrategy strategyGiamGia; // Biến lưu trữ thuật toán đang dùng

    public HoaDonThanhToan(BigDecimal tongTienGoc) {
        this.tongTienGoc = tongTienGoc;
    }

    // Hàm này cho phép thay đổi thuật toán giảm giá vào thời điểm "runtime"
    public void setStrategyGiamGia(GiamGiaStrategy strategyGiamGia) {
        this.strategyGiamGia = strategyGiamGia;
    }

    public BigDecimal thanhToan() {
        if (strategyGiamGia == null) {
            return tongTienGoc; // Nếu không có mã giảm giá thì trả nguyên giá
        }

        // Ủy quyền (delegate) việc tính toán cho Strategy đang được set
        return strategyGiamGia.tinhTienSauGiamGia(tongTienGoc);
    }
}
