package com.bookstore.backend.pattern.strategy;

import java.math.BigDecimal;

/**
 * STRATEGY PATTERN - Interface Chiến lược Giảm giá
 *
 * Mục đích: Định nghĩa một "hợp đồng" chung cho tất cả các thuật toán tính giảm giá.
 *
 * Áp dụng: Hệ thống có nhiều cách khuyến mãi khác nhau:
 * 1. Giảm theo phần trăm (ví dụ: giảm 10%)
 * 2. Giảm số tiền cố định (ví dụ: giảm 20.000đ)
 * 3. Không giảm giá
 *
 * Thay vì dùng một đống lệnh "if-else" trong code tính tiền, ta tách mỗi
 * cách giảm giá thành một Class riêng (Strategy).
 */
public interface GiamGiaStrategy {

    /**
     * Tính số tiền cuối cùng sau khi đã áp dụng giảm giá
     *
     * @param tongTienGoc tổng tiền ban đầu của đơn hàng
     * @return số tiền sau khi giảm
     */
    BigDecimal tinhTienSauGiamGia(BigDecimal tongTienGoc);
}
