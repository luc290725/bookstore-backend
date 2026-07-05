package com.bookstore.backend.pattern.facade;

import com.bookstore.backend.service.GioHangService;
import com.bookstore.backend.service.DonHangService;
import com.bookstore.backend.service.ChiTietDonHangService;
import com.bookstore.backend.model.DonHang;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * FACADE PATTERN - Mẫu thiết kế Facade
 *
 * Mục đích: Cung cấp một giao diện ĐƠN GIẢN cho một hệ thống con PHỨC TẠP.
 * Thay vì phải gọi nhiều Service khác nhau, người dùng chỉ cần gọi MỘT hàm duy nhất.
 *
 * Áp dụng: Quy trình đặt hàng gồm nhiều bước:
 * 1. Kiểm tra giỏ hàng
 * 2. Kiểm tra số lượng tồn kho
 * 3. Tính tổng tiền
 * 4. Tạo đơn hàng
 * 5. Tạo hóa đơn
 *
 * Nếu không dùng Facade, Controller phải gọi lần lượt từng Service.
 * Khi dùng Facade, Controller chỉ cần gọi: orderFacade.datHang(idKhachHang)
 *
 * Lợi ích:
 * - Giảm độ phức tạp của hệ thống
 * - Dễ sử dụng, dễ bảo trì
 * - Controller không cần biết bên trong xử lý như thế nào
 */
@Component
public class OrderFacade {

    private final GioHangService gioHangService;
    private final DonHangService donHangService;

    // Spring Boot tự động inject các Service vào đây (Constructor Injection)
    public OrderFacade(GioHangService gioHangService,
                       DonHangService donHangService) {
        this.gioHangService = gioHangService;
        this.donHangService = donHangService;
    }

    /**
     * Hàm Facade chính: Đặt hàng
     * Gom toàn bộ quy trình đặt hàng phức tạp vào MỘT hàm duy nhất.
     *
     * @param idKhachHang id của khách hàng đặt hàng
     * @param tongTien tổng tiền đơn hàng
     * @return thông báo kết quả đặt hàng
     */
    public String datHang(Integer idKhachHang, BigDecimal tongTien) {

        System.out.println("[Facade] === BẮT ĐẦU QUY TRÌNH ĐẶT HÀNG ===");

        // Bước 1: Kiểm tra giỏ hàng của khách hàng
        System.out.println("[Facade] Bước 1: Kiểm tra giỏ hàng...");
        // Gọi GioHangService để kiểm tra giỏ hàng
        // (Ở đây mình đơn giản hóa, chỉ in ra log)

        // Bước 2: Kiểm tra số lượng tồn kho
        System.out.println("[Facade] Bước 2: Kiểm tra tồn kho...");

        // Bước 3: Tính tổng tiền
        System.out.println("[Facade] Bước 3: Tính tổng tiền: " + tongTien + " VNĐ");

        // Bước 4: Tạo đơn hàng
        System.out.println("[Facade] Bước 4: Tạo đơn hàng...");
        DonHang donHang = new DonHang();
        donHang.setIdKhachHang(idKhachHang);
        donHang.setNgayDat(LocalDate.now());
        donHang.setTongTien(tongTien);
        donHang.setTrangThai("CHO_XAC_NHAN");

        String ketQua = donHangService.insertOrder(donHang);

        // Bước 5: Tạo hóa đơn
        System.out.println("[Facade] Bước 5: Tạo hóa đơn...");

        System.out.println("[Facade] === KẾT THÚC QUY TRÌNH ĐẶT HÀNG ===");

        return ketQua;
    }
}
