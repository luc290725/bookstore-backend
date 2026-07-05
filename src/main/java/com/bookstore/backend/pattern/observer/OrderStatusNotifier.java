package com.bookstore.backend.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * OBSERVER PATTERN - Đối tượng Bị Quan Sát (Subject/Observable)
 *
 * Mục đích: Quản lý danh sách các Observers và thông báo cho họ
 * mỗi khi trạng thái đơn hàng thay đổi.
 *
 * Áp dụng:
 * Khi trạng thái đơn hàng đổi thành "Đã giao", thay vì viết code gửi email
 * trực tiếp vào Service (gây rối và khó mở rộng), ta chỉ cần "thông báo"
 * cho lớp này. Lớp này sẽ tự động báo cho tất cả các Observer đang đăng ký.
 */
public class OrderStatusNotifier {

    // Danh sách các đối tượng đang "theo dõi" (lắng nghe)
    private List<OrderObserver> observers = new ArrayList<>();

    // Thêm một người quan sát mới
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    // Xóa một người quan sát
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    /**
     * Hàm này được gọi khi trạng thái đơn hàng thay đổi.
     * Nó sẽ lặp qua tất cả những người đang theo dõi và gọi hàm update() của họ.
     */
    public void notifyObservers(Integer orderId, String newStatus) {
        System.out.println("[Subject] Trạng thái đơn hàng #" + orderId + " đã thay đổi thành: " + newStatus);
        System.out.println("[Subject] Bắt đầu thông báo cho các hệ thống liên quan...");

        for (OrderObserver observer : observers) {
            observer.update(orderId, newStatus);
        }
    }
}
