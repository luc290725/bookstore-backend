package com.bookstore.backend.pattern.adapter;

import java.math.BigDecimal;

/**
 * ADAPTER PATTERN - Interface chung cho thanh toán
 *
 * Mục đích: Tạo một giao diện THỐNG NHẤT cho tất cả các cổng thanh toán.
 * Dù mỗi cổng thanh toán (VNPay, MoMo, ZaloPay) có API khác nhau,
 * nhưng hệ thống chỉ cần gọi qua interface này.
 *
 * Lợi ích:
 * - Dễ tích hợp hệ thống bên ngoài
 * - Không ảnh hưởng đến mã nguồn hiện có
 * - Dễ mở rộng khi thêm cổng thanh toán mới
 */
public interface ThanhToanAdapter {

    /**
     * Thực hiện thanh toán
     * @param soTien số tiền cần thanh toán
     * @param maGiaoDich mã giao dịch
     * @return true nếu thanh toán thành công, false nếu thất bại
     */
    boolean thanhToan(BigDecimal soTien, String maGiaoDich);

    /**
     * Lấy tên cổng thanh toán
     * @return tên cổng thanh toán
     */
    String getTenCongThanhToan();
}
