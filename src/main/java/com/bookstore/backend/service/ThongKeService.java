package com.bookstore.backend.service;

import com.bookstore.backend.repository.ThongKeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Service xử lý các nghiệp vụ liên quan đến thống kê doanh thu
 */
@Service
public class ThongKeService {

    private final ThongKeRepository thongKeRepository;

    public ThongKeService(ThongKeRepository thongKeRepository) {
        this.thongKeRepository = thongKeRepository;
    }

    /**
     * Lấy báo cáo tổng quan (Tổng doanh thu và tổng số đơn hàng)
     */
    public Map<String, Object> getBaoCaoTongQuan() {
        BigDecimal tongDoanhThu = thongKeRepository.getTongDoanhThu();
        Long tongDonHang = thongKeRepository.getTongSoDonHangThanhCong();

        // Gom dữ liệu vào một Map để trả về cho Controller
        Map<String, Object> baoCao = new HashMap<>();
        baoCao.put("tongDoanhThu", tongDoanhThu);
        baoCao.put("tongDonHangThanhCong", tongDonHang);
        baoCao.put("trangThai", "DA_GIAO");

        return baoCao;
    }
}
