package com.bookstore.backend.pattern.factory;

/**
 * Dịch vụ quản lý tài khoản - implement interface DichVu
 */
public class DichVuQuanLyTaiKhoan implements DichVu {

    @Override
    public String getTenDichVu() {
        return "Dịch vụ Quản lý Tài khoản";
    }
}
