package com.bookstore.backend.service;

import com.bookstore.backend.model.KhuyenMai;
import com.bookstore.backend.repository.KhuyenMaiRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KhuyenMaiService {

    private final KhuyenMaiRepository khuyenMaiRepository;

    public KhuyenMaiService(KhuyenMaiRepository khuyenMaiRepository) {
        this.khuyenMaiRepository = khuyenMaiRepository;
    }

    // Lấy tất cả khuyến mãi
    public List<KhuyenMai> getAllDiscounts() {
        return khuyenMaiRepository.getAllDiscounts();
    }

    // Lấy khuyến mãi theo id
    public KhuyenMai getDiscountById(Integer id) {
        KhuyenMai khuyenMai = khuyenMaiRepository.getDiscountById(id);

        if (khuyenMai == null) {
            throw new RuntimeException("Không tìm thấy khuyến mãi có id: " + id);
        }

        return khuyenMai;
    }

    // Lấy khuyến mãi theo mã
    public KhuyenMai getDiscountByCode(String maKhuyenMai) {
        KhuyenMai khuyenMai = khuyenMaiRepository.getDiscountByCode(maKhuyenMai);

        if (khuyenMai == null) {
            throw new RuntimeException("Không tìm thấy khuyến mãi có mã: " + maKhuyenMai);
        }

        return khuyenMai;
    }

    // Tìm kiếm khuyến mãi theo mã
    public List<KhuyenMai> searchDiscountsByCode(String keyword) {
        return khuyenMaiRepository.searchDiscountsByCode(keyword);
    }

    // Lấy danh sách khuyến mãi còn hiệu lực
    public List<KhuyenMai> getActiveDiscounts() {
        return khuyenMaiRepository.getActiveDiscounts(LocalDate.now());
    }

    // Thêm khuyến mãi mới
    public String insertDiscount(KhuyenMai khuyenMai) {
        validateDiscountDate(khuyenMai);

        KhuyenMai tonTai = khuyenMaiRepository.getDiscountByCode(khuyenMai.getMaKhuyenMai());

        if (tonTai != null) {
            throw new RuntimeException("Mã khuyến mãi đã tồn tại");
        }

        int result = khuyenMaiRepository.insertDiscount(
                khuyenMai.getMaKhuyenMai(),
                khuyenMai.getPhanTramGiam(),
                khuyenMai.getNgayBatDau(),
                khuyenMai.getNgayKetThuc(),
                khuyenMai.getDieuKien()
        );

        if (result > 0) {
            return "Thêm khuyến mãi thành công";
        }

        return "Thêm khuyến mãi thất bại";
    }

    // Cập nhật khuyến mãi
    public String updateDiscount(Integer id, KhuyenMai khuyenMaiMoi) {
        // Kiểm tra xem khuyến mãi có tồn tại không
        getDiscountById(id);

        validateDiscountDate(khuyenMaiMoi);

        int result = khuyenMaiRepository.updateDiscount(
                id,
                khuyenMaiMoi.getMaKhuyenMai(),
                khuyenMaiMoi.getPhanTramGiam(),
                khuyenMaiMoi.getNgayBatDau(),
                khuyenMaiMoi.getNgayKetThuc(),
                khuyenMaiMoi.getDieuKien()
        );

        if (result > 0) {
            return "Cập nhật khuyến mãi thành công";
        }

        return "Cập nhật khuyến mãi thất bại";
    }

    // Xóa khuyến mãi
    public String deleteDiscount(Integer id) {
        // Kiểm tra xem khuyến mãi có tồn tại không
        getDiscountById(id);

        int result = khuyenMaiRepository.deleteDiscount(id);

        if (result > 0) {
            return "Xóa khuyến mãi thành công";
        }

        return "Xóa khuyến mãi thất bại";
    }

    // Kiểm tra ngày bắt đầu và ngày kết thúc
    private void validateDiscountDate(KhuyenMai khuyenMai) {
        if (khuyenMai.getNgayBatDau() != null
                && khuyenMai.getNgayKetThuc() != null
                && khuyenMai.getNgayBatDau().isAfter(khuyenMai.getNgayKetThuc())) {
            throw new RuntimeException("Ngày bắt đầu không được lớn hơn ngày kết thúc");
        }
    }
}