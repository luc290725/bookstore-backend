package com.bookstore.backend.service;

import com.bookstore.backend.model.HoaDon;
import com.bookstore.backend.repository.HoaDonRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class HoaDonService {

    private final HoaDonRepository hoaDonRepository;

    public HoaDonService(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    // Lấy tất cả hóa đơn
    public List<HoaDon> getAllInvoices() {
        return hoaDonRepository.getAllInvoices();
    }

    // Lấy hóa đơn theo id
    public HoaDon getInvoiceById(Integer id) {
        HoaDon hoaDon = hoaDonRepository.getInvoiceById(id);

        if (hoaDon == null) {
            throw new RuntimeException("Không tìm thấy hóa đơn có id: " + id);
        }

        return hoaDon;
    }

    // Lấy hóa đơn theo id đơn hàng
    public HoaDon getInvoiceByOrderId(Integer idDonHang) {
        HoaDon hoaDon = hoaDonRepository.getInvoiceByOrderId(idDonHang);

        if (hoaDon == null) {
            throw new RuntimeException("Không tìm thấy hóa đơn của đơn hàng có id: " + idDonHang);
        }

        return hoaDon;
    }

    // Lấy danh sách hóa đơn theo ngày lập
    public List<HoaDon> getInvoicesByDate(LocalDate ngayLap) {
        return hoaDonRepository.getInvoicesByDate(ngayLap);
    }

    // Thêm hóa đơn mới
    public String insertInvoice(HoaDon hoaDon) {
        if (hoaDon.getNgayLap() == null) {
            hoaDon.setNgayLap(LocalDate.now());
        }

        if (hoaDon.getTongTien() == null) {
            hoaDon.setTongTien(BigDecimal.ZERO);
        }

        HoaDon tonTai = hoaDonRepository.getInvoiceByOrderId(hoaDon.getIdDonHang());

        if (tonTai != null) {
            throw new RuntimeException("Đơn hàng này đã có hóa đơn");
        }

        int result = hoaDonRepository.insertInvoice(
                hoaDon.getIdDonHang(),
                hoaDon.getNgayLap(),
                hoaDon.getTongTien()
        );

        if (result > 0) {
            return "Thêm hóa đơn thành công";
        }

        return "Thêm hóa đơn thất bại";
    }

    // Cập nhật hóa đơn
    public String updateInvoice(Integer id, HoaDon hoaDonMoi) {
        // Kiểm tra xem hóa đơn có tồn tại không
        getInvoiceById(id);

        if (hoaDonMoi.getNgayLap() == null) {
            hoaDonMoi.setNgayLap(LocalDate.now());
        }

        if (hoaDonMoi.getTongTien() == null) {
            hoaDonMoi.setTongTien(BigDecimal.ZERO);
        }

        int result = hoaDonRepository.updateInvoice(
                id,
                hoaDonMoi.getIdDonHang(),
                hoaDonMoi.getNgayLap(),
                hoaDonMoi.getTongTien()
        );

        if (result > 0) {
            return "Cập nhật hóa đơn thành công";
        }

        return "Cập nhật hóa đơn thất bại";
    }

    // Xóa hóa đơn
    public String deleteInvoice(Integer id) {
        // Kiểm tra xem hóa đơn có tồn tại không
        getInvoiceById(id);

        int result = hoaDonRepository.deleteInvoice(id);

        if (result > 0) {
            return "Xóa hóa đơn thành công";
        }

        return "Xóa hóa đơn thất bại";
    }
}