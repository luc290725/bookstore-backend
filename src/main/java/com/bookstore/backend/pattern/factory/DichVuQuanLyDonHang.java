package com.bookstore.backend.pattern.factory;

/**
 * Dịch vụ quản lý đơn hàng - implement interface DichVu
 */
public class DichVuQuanLyDonHang implements DichVu {

    @Override
    public String getTenDichVu() {
        return "Dịch vụ Quản lý Đơn hàng";
    }
}
