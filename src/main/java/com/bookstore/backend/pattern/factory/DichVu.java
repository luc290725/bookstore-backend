package com.bookstore.backend.pattern.factory;

/**
 * Interface chung cho tất cả các dịch vụ trong hệ thống.
 * Mỗi dịch vụ (Sách, Đơn hàng, Tài khoản...) đều phải implement interface này.
 */
public interface DichVu {

    /**
     * Lấy tên của dịch vụ
     * @return tên dịch vụ dạng chuỗi
     */
    String getTenDichVu();
}
