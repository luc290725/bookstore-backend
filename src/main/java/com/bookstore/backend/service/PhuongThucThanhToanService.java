package com.bookstore.backend.service;

import com.bookstore.backend.model.PhuongThucThanhToan;
import com.bookstore.backend.repository.PhuongThucThanhToanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhuongThucThanhToanService {

    private final PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    public PhuongThucThanhToanService(PhuongThucThanhToanRepository phuongThucThanhToanRepository) {
        this.phuongThucThanhToanRepository = phuongThucThanhToanRepository;
    }

    // Lấy tất cả phương thức thanh toán
    public List<PhuongThucThanhToan> getAllPaymentMethods() {
        return phuongThucThanhToanRepository.getAllPaymentMethods();
    }

    // Lấy phương thức thanh toán theo id
    public PhuongThucThanhToan getPaymentMethodById(Integer id) {
        PhuongThucThanhToan phuongThucThanhToan = phuongThucThanhToanRepository.getPaymentMethodById(id);

        if (phuongThucThanhToan == null) {
            throw new RuntimeException("Không tìm thấy phương thức thanh toán có id: " + id);
        }

        return phuongThucThanhToan;
    }

    // Tìm kiếm phương thức thanh toán theo tên
    public List<PhuongThucThanhToan> searchPaymentMethodsByName(String keyword) {
        return phuongThucThanhToanRepository.searchPaymentMethodsByName(keyword);
    }

    // Thêm phương thức thanh toán mới
    public String insertPaymentMethod(PhuongThucThanhToan phuongThucThanhToan) {
        int result = phuongThucThanhToanRepository.insertPaymentMethod(
                phuongThucThanhToan.getTenPt(),
                phuongThucThanhToan.getMoTa()
        );

        if (result > 0) {
            return "Thêm phương thức thanh toán thành công";
        }

        return "Thêm phương thức thanh toán thất bại";
    }

    // Cập nhật phương thức thanh toán
    public String updatePaymentMethod(Integer id, PhuongThucThanhToan phuongThucThanhToanMoi) {
        // Kiểm tra xem phương thức thanh toán có tồn tại không
        getPaymentMethodById(id);

        int result = phuongThucThanhToanRepository.updatePaymentMethod(
                id,
                phuongThucThanhToanMoi.getTenPt(),
                phuongThucThanhToanMoi.getMoTa()
        );

        if (result > 0) {
            return "Cập nhật phương thức thanh toán thành công";
        }

        return "Cập nhật phương thức thanh toán thất bại";
    }

    // Xóa phương thức thanh toán
    public String deletePaymentMethod(Integer id) {
        // Kiểm tra xem phương thức thanh toán có tồn tại không
        getPaymentMethodById(id);

        int result = phuongThucThanhToanRepository.deletePaymentMethod(id);

        if (result > 0) {
            return "Xóa phương thức thanh toán thành công";
        }

        return "Xóa phương thức thanh toán thất bại";
    }
}