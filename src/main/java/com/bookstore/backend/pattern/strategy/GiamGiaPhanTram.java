package com.bookstore.backend.pattern.strategy;

import java.math.BigDecimal;

/**
 * STRATEGY cụ thể: Giảm giá theo phần trăm
 */
public class GiamGiaPhanTram implements GiamGiaStrategy {

    private double phanTramGiam;

    public GiamGiaPhanTram(double phanTramGiam) {
        this.phanTramGiam = phanTramGiam; // Ví dụ: 10 (tương đương 10%)
    }

    @Override
    public BigDecimal tinhTienSauGiamGia(BigDecimal tongTienGoc) {
        // Tính số tiền được giảm
        BigDecimal mucGiam = tongTienGoc.multiply(BigDecimal.valueOf(phanTramGiam / 100));

        // Trả về số tiền còn lại phải trả
        return tongTienGoc.subtract(mucGiam);
    }
}
