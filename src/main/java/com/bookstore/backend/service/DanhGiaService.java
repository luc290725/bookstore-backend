package com.bookstore.backend.service;

import com.bookstore.backend.model.DanhGia;
import com.bookstore.backend.repository.DanhGiaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DanhGiaService {

    private final DanhGiaRepository danhGiaRepository;

    public DanhGiaService(DanhGiaRepository danhGiaRepository) {
        this.danhGiaRepository = danhGiaRepository;
    }

    // Lấy tất cả đánh giá
    public List<DanhGia> getAllReviews() {
        return danhGiaRepository.getAllReviews();
    }

    // Lấy đánh giá theo id
    public DanhGia getReviewById(Integer id) {
        DanhGia danhGia = danhGiaRepository.getReviewById(id);

        if (danhGia == null) {
            throw new RuntimeException("Không tìm thấy đánh giá có id: " + id);
        }

        return danhGia;
    }

    // Lấy danh sách đánh giá theo id sách
    public List<DanhGia> getReviewsByBookId(Integer idSach) {
        return danhGiaRepository.getReviewsByBookId(idSach);
    }

    // Lấy danh sách đánh giá theo id khách hàng
    public List<DanhGia> getReviewsByCustomerId(Integer idKhachHang) {
        return danhGiaRepository.getReviewsByCustomerId(idKhachHang);
    }

    // Lấy đánh giá của một khách hàng cho một sách
    public DanhGia getReviewByCustomerAndBook(Integer idKhachHang, Integer idSach) {
        DanhGia danhGia = danhGiaRepository.getReviewByCustomerAndBook(idKhachHang, idSach);

        if (danhGia == null) {
            throw new RuntimeException("Không tìm thấy đánh giá của khách hàng cho sách này");
        }

        return danhGia;
    }

    // Lấy danh sách đánh giá theo số sao
    public List<DanhGia> getReviewsByRating(Integer soSao) {
        return danhGiaRepository.getReviewsByRating(soSao);
    }

    // Thêm đánh giá mới
    public String insertReview(DanhGia danhGia) {
        if (danhGia.getNgayDanhGia() == null) {
            danhGia.setNgayDanhGia(LocalDate.now());
        }

        DanhGia tonTai = danhGiaRepository.getReviewByCustomerAndBook(
                danhGia.getIdKhachHang(),
                danhGia.getIdSach()
        );

        if (tonTai != null) {
            throw new RuntimeException("Khách hàng đã đánh giá sách này rồi");
        }

        int result = danhGiaRepository.insertReview(
                danhGia.getIdKhachHang(),
                danhGia.getIdSach(),
                danhGia.getSoSao(),
                danhGia.getBinhLuan(),
                danhGia.getNgayDanhGia()
        );

        if (result > 0) {
            return "Thêm đánh giá thành công";
        }

        return "Thêm đánh giá thất bại";
    }

    // Cập nhật đánh giá
    public String updateReview(Integer id, DanhGia danhGiaMoi) {
        // Kiểm tra xem đánh giá có tồn tại không
        getReviewById(id);

        if (danhGiaMoi.getNgayDanhGia() == null) {
            danhGiaMoi.setNgayDanhGia(LocalDate.now());
        }

        int result = danhGiaRepository.updateReview(
                id,
                danhGiaMoi.getIdKhachHang(),
                danhGiaMoi.getIdSach(),
                danhGiaMoi.getSoSao(),
                danhGiaMoi.getBinhLuan(),
                danhGiaMoi.getNgayDanhGia()
        );

        if (result > 0) {
            return "Cập nhật đánh giá thành công";
        }

        return "Cập nhật đánh giá thất bại";
    }

    // Xóa đánh giá
    public String deleteReview(Integer id) {
        // Kiểm tra xem đánh giá có tồn tại không
        getReviewById(id);

        int result = danhGiaRepository.deleteReview(id);

        if (result > 0) {
            return "Xóa đánh giá thành công";
        }

        return "Xóa đánh giá thất bại";
    }
}