package com.bookstore.backend.service;

import com.bookstore.backend.model.DiaChi;
import com.bookstore.backend.repository.DiaChiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaChiService {

    private final DiaChiRepository diaChiRepository;

    public DiaChiService(DiaChiRepository diaChiRepository) {
        this.diaChiRepository = diaChiRepository;
    }

    // Lấy tất cả địa chỉ
    public List<DiaChi> getAllAddresses() {
        return diaChiRepository.getAllAddresses();
    }

    // Lấy địa chỉ theo id
    public DiaChi getAddressById(Integer id) {
        DiaChi diaChi = diaChiRepository.getAddressById(id);

        if (diaChi == null) {
            throw new RuntimeException("Không tìm thấy địa chỉ có id: " + id);
        }

        return diaChi;
    }

    // Lấy danh sách địa chỉ theo id khách hàng
    public List<DiaChi> getAddressesByCustomerId(Integer idKhachHang) {
        return diaChiRepository.getAddressesByCustomerId(idKhachHang);
    }

    // Lấy địa chỉ mặc định của khách hàng
    public DiaChi getDefaultAddressByCustomerId(Integer idKhachHang) {
        DiaChi diaChi = diaChiRepository.getDefaultAddressByCustomerId(idKhachHang);

        if (diaChi == null) {
            throw new RuntimeException("Không tìm thấy địa chỉ mặc định của khách hàng có id: " + idKhachHang);
        }

        return diaChi;
    }

    // Thêm địa chỉ mới
    public String insertAddress(DiaChi diaChi) {
        if (diaChi.getMacDinh() == null) {
            diaChi.setMacDinh(false);
        }

        int result = diaChiRepository.insertAddress(
                diaChi.getIdKhachHang(),
                diaChi.getSoNha(),
                diaChi.getPhuongXa(),
                diaChi.getQuanHuyen(),
                diaChi.getTinhTp(),
                diaChi.getMacDinh()
        );

        if (result > 0) {
            return "Thêm địa chỉ thành công";
        }

        return "Thêm địa chỉ thất bại";
    }

    // Cập nhật địa chỉ
    public String updateAddress(Integer id, DiaChi diaChiMoi) {
        // Kiểm tra xem địa chỉ có tồn tại không
        getAddressById(id);

        if (diaChiMoi.getMacDinh() == null) {
            diaChiMoi.setMacDinh(false);
        }

        int result = diaChiRepository.updateAddress(
                id,
                diaChiMoi.getIdKhachHang(),
                diaChiMoi.getSoNha(),
                diaChiMoi.getPhuongXa(),
                diaChiMoi.getQuanHuyen(),
                diaChiMoi.getTinhTp(),
                diaChiMoi.getMacDinh()
        );

        if (result > 0) {
            return "Cập nhật địa chỉ thành công";
        }

        return "Cập nhật địa chỉ thất bại";
    }

    // Xóa địa chỉ
    public String deleteAddress(Integer id) {
        // Kiểm tra xem địa chỉ có tồn tại không
        getAddressById(id);

        int result = diaChiRepository.deleteAddress(id);

        if (result > 0) {
            return "Xóa địa chỉ thành công";
        }

        return "Xóa địa chỉ thất bại";
    }
}