package com.bookstore.backend.pattern.adapter;

import java.math.BigDecimal;

/**
 * ADAPTER cho cổng thanh toán VNPay
 *
 * Class này "bọc" (wrap) API của VNPay vào giao diện ThanhToanAdapter
 * để hệ thống có thể sử dụng một cách thống nhất.
 *
 * Trong thực tế, class này sẽ gọi API của VNPay.
 * Ở đây mình giả lập (simulate) để minh họa cho đồ án.
 */
public class VNPayAdapter implements ThanhToanAdapter {

    @Override
    public boolean thanhToan(BigDecimal soTien, String maGiaoDich) {
        // Giả lập quá trình kết nối và thanh toán qua VNPay
        System.out.println("[Adapter - VNPay] Đang kết nối API VNPay...");
        System.out.println("[Adapter - VNPay] Gửi yêu cầu thanh toán: " + soTien + " VNĐ");
        System.out.println("[Adapter - VNPay] Mã giao dịch: " + maGiaoDich);
        System.out.println("[Adapter - VNPay] Thanh toán thành công!");

        return true; // Giả lập luôn thành công
    }

    @Override
    public String getTenCongThanhToan() {
        return "VNPay";
    }
}
