package com.bookstore.backend.pattern.observer;

/**
 * Observer cụ thể: Gửi Email thông báo cho khách hàng
 *
 * Class này "lắng nghe" sự thay đổi của đơn hàng.
 * Khi đơn hàng đổi trạng thái, nó sẽ tự động nhận được thông báo
 * và thực hiện hành động gửi email giả lập.
 */
public class EmailNotificationObserver implements OrderObserver {

    private String emailKhachHang;

    public EmailNotificationObserver(String emailKhachHang) {
        this.emailKhachHang = emailKhachHang;
    }

    @Override
    public void update(Integer orderId, String status) {
        // Giả lập việc gửi email
        System.out.println("[Observer - Email] Đang gửi email tới: " + emailKhachHang);
        System.out.println("[Observer - Email] Nội dung: Đơn hàng #" + orderId + " của bạn đã chuyển sang trạng thái: " + status);
    }
}
