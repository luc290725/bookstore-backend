package com.bookstore.backend.service;

import com.bookstore.backend.model.VanChuyen;
import com.bookstore.backend.repository.VanChuyenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VanChuyenService {

    private final VanChuyenRepository vanChuyenRepository;

    public VanChuyenService(VanChuyenRepository vanChuyenRepository) {
        this.vanChuyenRepository = vanChuyenRepository;
    }

    // Lấy tất cả vận chuyển
    public List<VanChuyen> getAllShippings() {
        return vanChuyenRepository.getAllShippings();
    }

    // Lấy vận chuyển theo id
    public VanChuyen getShippingById(Integer id) {
        VanChuyen vanChuyen = vanChuyenRepository.getShippingById(id);

        if (vanChuyen == null) {
            throw new RuntimeException("Không tìm thấy vận chuyển có id: " + id);
        }

        return vanChuyen;
    }

    // Tìm kiếm vận chuyển theo đơn vị
    public List<VanChuyen> searchShippingsByUnit(String keyword) {
        return vanChuyenRepository.searchShippingsByUnit(keyword);
    }

    // Thêm vận chuyển mới
    public String insertShipping(VanChuyen vanChuyen) {
        int result = vanChuyenRepository.insertShipping(
                vanChuyen.getDonVi(),
                vanChuyen.getPhiVanChuyen(),
                vanChuyen.getThoiGian()
        );

        if (result > 0) {
            return "Thêm vận chuyển thành công";
        }

        return "Thêm vận chuyển thất bại";
    }

    // Cập nhật vận chuyển
    public String updateShipping(Integer id, VanChuyen vanChuyenMoi) {
        // Kiểm tra xem vận chuyển có tồn tại không
        getShippingById(id);

        int result = vanChuyenRepository.updateShipping(
                id,
                vanChuyenMoi.getDonVi(),
                vanChuyenMoi.getPhiVanChuyen(),
                vanChuyenMoi.getThoiGian()
        );

        if (result > 0) {
            return "Cập nhật vận chuyển thành công";
        }

        return "Cập nhật vận chuyển thất bại";
    }

    // Xóa vận chuyển
    public String deleteShipping(Integer id) {
        // Kiểm tra xem vận chuyển có tồn tại không
        getShippingById(id);

        int result = vanChuyenRepository.deleteShipping(id);

        if (result > 0) {
            return "Xóa vận chuyển thành công";
        }

        return "Xóa vận chuyển thất bại";
    }
}