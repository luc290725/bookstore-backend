package com.bookstore.backend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Repository chuyên dùng cho các tác vụ thống kê, báo cáo.
 * Sử dụng EntityManager để chạy Native SQL (phù hợp với các câu query phức tạp
 * như SUM, COUNT, GROUP BY mà JpaRepository có sẵn khó hỗ trợ trực tiếp).
 */
@Repository
public class ThongKeRepository {

    // Quản lý các phiên làm việc với Database
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Hàm lấy tổng doanh thu của toàn bộ hệ thống
     * Lấy từ bảng donhang, nơi trangthai = 'DA_GIAO' hoặc 'HOAN_THANH'
     */
    public BigDecimal getTongDoanhThu() {
        String sql = "SELECT SUM(tongtien) FROM donhang WHERE trangthai = 'DA_GIAO'";
        Query query = entityManager.createNativeQuery(sql);
        
        Object result = query.getSingleResult();
        if (result == null) {
            return BigDecimal.ZERO;
        }
        
        return new BigDecimal(result.toString());
    }

    /**
     * Hàm lấy tổng số đơn hàng đã bán thành công
     */
    public Long getTongSoDonHangThanhCong() {
        String sql = "SELECT COUNT(*) FROM donhang WHERE trangthai = 'DA_GIAO'";
        Query query = entityManager.createNativeQuery(sql);
        
        Object result = query.getSingleResult();
        if (result == null) {
            return 0L;
        }
        
        return Long.parseLong(result.toString());
    }
}
