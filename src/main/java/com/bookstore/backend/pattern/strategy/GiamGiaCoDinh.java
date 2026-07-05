package com.bookstore.backend.pattern.strategy;

import java.math.BigDecimal;

/**
 * STRATEGY cụ thể: Giảm một số tiền cố định
 */
public class GiamGiaCoDinh implements GiamGiaStrategy {

    private BigDecimal soTienGiam;

    public GiamGiaCoDinh(BigDecimal soTienGiam) {
        this.soTienGiam = soTienGiam; // Ví dụ: 20000 (Giảm 20k)
    }

    @Override
    public BigDecimal tinhTienSauGiamGia(BigDecimal tongTienGoc) {
        // Nếu số tiền giảm lớn hơn tổng tiền gốc thì chỉ trả bằng 0
        if (soTienGiam.compareTo(tongTienGoc) > 0) {
            return BigDecimal.ZERO;
        }

        // Trả về số tiền còn lại phải trả
        return tongTienGoc.subtract(soTienGiam);
    }
}
