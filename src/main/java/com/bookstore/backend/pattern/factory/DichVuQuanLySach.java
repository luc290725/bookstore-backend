package com.bookstore.backend.pattern.factory;

/**
 * Dịch vụ quản lý sách - implement interface DichVu
 */
public class DichVuQuanLySach implements DichVu {

    @Override
    public String getTenDichVu() {
        return "Dịch vụ Quản lý Sách";
    }
}
