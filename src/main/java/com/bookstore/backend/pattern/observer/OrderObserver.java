package com.bookstore.backend.pattern.observer;

/**
 * OBSERVER PATTERN - Interface Người Quan Sát (Observer)
 *
 * Mục đích: Định nghĩa một phương thức cập nhật chung cho tất cả các đối tượng
 * muốn nhận thông báo khi có sự thay đổi trạng thái.
 */
public interface OrderObserver {

    /**
     * Phương thức được gọi khi trạng thái đơn hàng thay đổi
     *
     * @param orderId mã đơn hàng
     * @param status trạng thái mới của đơn hàng
     */
    void update(Integer orderId, String status);
}
