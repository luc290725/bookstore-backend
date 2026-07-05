package com.bookstore.backend.pattern.adapter;

import java.math.BigDecimal;

/**
 * ADAPTER cho cổng thanh toán MoMo
 *
 * Class này "bọc" (wrap) API của MoMo vào giao diện ThanhToanAdapter.
 * Mặc dù bên trong cách xử lý khác VNPay,
 * nhưng từ bên ngoài hệ thống chỉ cần gọi: thanhToan()
 */
public class MoMoAdapter implements ThanhToanAdapter {

    @Override
    public boolean thanhToan(BigDecimal soTien, String maGiaoDich) {
        // Giả lập quá trình kết nối và thanh toán qua MoMo
        System.out.println("[Adapter - MoMo] Đang kết nối API MoMo...");
        System.out.println("[Adapter - MoMo] Gửi yêu cầu thanh toán: " + soTien + " VNĐ");
        System.out.println("[Adapter - MoMo] Mã giao dịch: " + maGiaoDich);
        System.out.println("[Adapter - MoMo] Thanh toán thành công!");

        return true; // Giả lập luôn thành công
    }

    @Override
    public String getTenCongThanhToan() {
        return "MoMo";
    }
}
