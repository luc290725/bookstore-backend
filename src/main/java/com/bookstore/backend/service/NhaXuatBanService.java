package com.bookstore.backend.service;

import com.bookstore.backend.model.NhaXuatBan;
import com.bookstore.backend.repository.NhaXuatBanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhaXuatBanService {

    private final NhaXuatBanRepository nhaXuatBanRepository;

    public NhaXuatBanService(NhaXuatBanRepository nhaXuatBanRepository) {
        this.nhaXuatBanRepository = nhaXuatBanRepository;
    }

    // Lấy tất cả nhà xuất bản
    public List<NhaXuatBan> getAllPublishers() {
        return nhaXuatBanRepository.getAllPublishers();
    }

    // Lấy nhà xuất bản theo id
    public NhaXuatBan getPublisherById(Integer id) {
        NhaXuatBan nhaXuatBan = nhaXuatBanRepository.getPublisherById(id);

        if (nhaXuatBan == null) {
            throw new RuntimeException("Không tìm thấy nhà xuất bản có id: " + id);
        }

        return nhaXuatBan;
    }

    // Tìm kiếm nhà xuất bản theo tên
    public List<NhaXuatBan> searchPublishersByName(String keyword) {
        return nhaXuatBanRepository.searchPublishersByName(keyword);
    }

    // Thêm nhà xuất bản mới
    public String insertPublisher(NhaXuatBan nhaXuatBan) {
        int result = nhaXuatBanRepository.insertPublisher(
                nhaXuatBan.getTenNxb(),
                nhaXuatBan.getDiaChi(),
                nhaXuatBan.getSoDienThoai()
        );

        if (result > 0) {
            return "Thêm nhà xuất bản thành công";
        }

        return "Thêm nhà xuất bản thất bại";
    }

    // Cập nhật nhà xuất bản
    public String updatePublisher(Integer id, NhaXuatBan nhaXuatBanMoi) {
        // Kiểm tra xem nhà xuất bản có tồn tại không
        getPublisherById(id);

        int result = nhaXuatBanRepository.updatePublisher(
                id,
                nhaXuatBanMoi.getTenNxb(),
                nhaXuatBanMoi.getDiaChi(),
                nhaXuatBanMoi.getSoDienThoai()
        );

        if (result > 0) {
            return "Cập nhật nhà xuất bản thành công";
        }

        return "Cập nhật nhà xuất bản thất bại";
    }

    // Xóa nhà xuất bản
    public String deletePublisher(Integer id) {
        // Kiểm tra xem nhà xuất bản có tồn tại không
        getPublisherById(id);

        int result = nhaXuatBanRepository.deletePublisher(id);

        if (result > 0) {
            return "Xóa nhà xuất bản thành công";
        }

        return "Xóa nhà xuất bản thất bại";
    }
}