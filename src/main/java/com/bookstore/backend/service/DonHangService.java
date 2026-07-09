package com.bookstore.backend.service;

import com.bookstore.backend.model.DonHang;
import com.bookstore.backend.repository.DonHangRepository;
import com.bookstore.backend.repository.ChiTietDonHangRepository;
import com.bookstore.backend.repository.HoaDonRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DonHangService {

    private final DonHangRepository donHangRepository;
    private final ChiTietDonHangRepository chiTietDonHangRepository;
    private final HoaDonRepository hoaDonRepository;
    private final com.bookstore.backend.repository.SachRepository sachRepository;
    private final com.bookstore.backend.repository.KhachHangRepository khachHangRepository;

    public DonHangService(DonHangRepository donHangRepository, 
                          ChiTietDonHangRepository chiTietDonHangRepository,
                          HoaDonRepository hoaDonRepository,
                          com.bookstore.backend.repository.SachRepository sachRepository,
                          com.bookstore.backend.repository.KhachHangRepository khachHangRepository) {
        this.donHangRepository = donHangRepository;
        this.chiTietDonHangRepository = chiTietDonHangRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.sachRepository = sachRepository;
        this.khachHangRepository = khachHangRepository;
    }

    // Lấy tất cả đơn hàng
    public List<DonHang> getAllOrders() {
        return donHangRepository.getAllOrders();
    }

    // Lấy đơn hàng theo id
    public DonHang getOrderById(Integer id) {
        DonHang donHang = donHangRepository.getOrderById(id);

        if (donHang == null) {
            throw new RuntimeException("Không tìm thấy đơn hàng có id: " + id);
        }

        return donHang;
    }

    // Lấy danh sách đơn hàng theo id khách hàng
    public List<DonHang> getOrdersByCustomerId(Integer idKhachHang) {
        return donHangRepository.getOrdersByCustomerId(idKhachHang);
    }

    // Lấy danh sách đơn hàng theo trạng thái
    public List<DonHang> getOrdersByStatus(String trangThai) {
        return donHangRepository.getOrdersByStatus(trangThai);
    }

    // Thêm đơn hàng mới
    public String insertOrder(DonHang donHang) {
        if (donHang.getNgayDat() == null) {
            donHang.setNgayDat(LocalDate.now());
        }

        if (donHang.getTongTien() == null) {
            donHang.setTongTien(BigDecimal.ZERO);
        }

        if (donHang.getTrangThai() == null || donHang.getTrangThai().trim().isEmpty()) {
            donHang.setTrangThai("CHO_XAC_NHAN");
        }

        int result = donHangRepository.insertOrder(
                donHang.getIdKhachHang(),
                donHang.getNgayDat(),
                donHang.getTongTien(),
                donHang.getTrangThai()
        );

        if (result > 0) {
            return "Thêm đơn hàng thành công";
        }

        return "Thêm đơn hàng thất bại";
    }

    // Cập nhật đơn hàng
    public String updateOrder(Integer id, DonHang donHangMoi) {
        // Kiểm tra xem đơn hàng có tồn tại không
        getOrderById(id);

        if (donHangMoi.getNgayDat() == null) {
            donHangMoi.setNgayDat(LocalDate.now());
        }

        if (donHangMoi.getTongTien() == null) {
            donHangMoi.setTongTien(BigDecimal.ZERO);
        }

        if (donHangMoi.getTrangThai() == null || donHangMoi.getTrangThai().trim().isEmpty()) {
            donHangMoi.setTrangThai("CHO_XAC_NHAN");
        }

        int result = donHangRepository.updateOrder(
                id,
                donHangMoi.getIdKhachHang(),
                donHangMoi.getNgayDat(),
                donHangMoi.getTongTien(),
                donHangMoi.getTrangThai()
        );

        if (result > 0) {
            return "Cập nhật đơn hàng thành công";
        }

        return "Cập nhật đơn hàng thất bại";
    }

    // Cập nhật trạng thái đơn hàng
    public String updateOrderStatus(Integer id, String trangThai) {
        // Kiểm tra xem đơn hàng có tồn tại không
        getOrderById(id);

        int result = donHangRepository.updateOrderStatus(id, trangThai);

        if (result > 0) {
            return "Cập nhật trạng thái đơn hàng thành công";
        }

        return "Cập nhật trạng thái đơn hàng thất bại";
    }

    // Xóa đơn hàng
    @Transactional
    public String deleteOrder(Integer id) {
        // Kiểm tra xem đơn hàng có tồn tại không
        getOrderById(id);

        // Xóa hóa đơn (nếu có) trước
        hoaDonRepository.deleteInvoiceByOrderId(id);

        // Xóa tất cả chi tiết đơn hàng trước khi xóa đơn hàng (để tránh lỗi khóa ngoại)
        chiTietDonHangRepository.deleteOrderDetailsByOrderId(id);

        int result = donHangRepository.deleteOrder(id);

        if (result > 0) {
            return "Xóa đơn hàng thành công";
        }

        return "Xóa đơn hàng thất bại";
    }

    // Thực hiện Checkout (Transactional để đảm bảo toàn vẹn dữ liệu)
    @Transactional
    public String checkout(com.bookstore.backend.dto.CheckoutRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống!");
        }

        // Lấy thông tin khách hàng từ idTaiKhoan
        com.bookstore.backend.model.KhachHang khachHang = khachHangRepository.getCustomerByAccountId(request.getIdTaiKhoan());
        if (khachHang == null) {
            throw new RuntimeException("Tài khoản chưa có thông tin khách hàng!");
        }

        // 1. Tạo đơn hàng
        DonHang donHang = new DonHang();
        donHang.setIdKhachHang(khachHang.getId());
        donHang.setTongTien(request.getTongTien());
        donHang.setNgayDat(LocalDate.now());
        donHang.setTrangThai("CHO_XAC_NHAN");
        
        DonHang savedDonHang = donHangRepository.save(donHang);
        Integer orderId = savedDonHang.getId();

        // 2. Lưu chi tiết đơn hàng & Trừ số lượng tồn kho
        for (com.bookstore.backend.dto.CartItemDTO item : request.getItems()) {
            com.bookstore.backend.model.Sach sach = sachRepository.getBookById(item.getId());
            if (sach == null) {
                throw new RuntimeException("Sách có ID " + item.getId() + " không tồn tại!");
            }
            if (sach.getSoLuongTon() < item.getSoLuong()) {
                throw new RuntimeException("Sách '" + sach.getTenSach() + "' không đủ số lượng tồn kho (Còn: " + sach.getSoLuongTon() + ").");
            }

            // Trừ số lượng
            int remain = sach.getSoLuongTon() - item.getSoLuong();
            sachRepository.updateStock(sach.getId(), remain);

            // Thêm chi tiết
            chiTietDonHangRepository.insertOrderDetail(orderId, item.getId(), item.getSoLuong(), BigDecimal.valueOf(item.getDonGia()));
        }

        return "Đặt hàng thành công!";
    }
}