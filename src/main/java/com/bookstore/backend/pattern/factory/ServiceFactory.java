package com.bookstore.backend.pattern.factory;

/**
 * FACTORY METHOD PATTERN - Mẫu thiết kế Factory Method
 *
 * Mục đích: Tạo đối tượng thông qua một lớp trung gian (Factory)
 * thay vì khởi tạo trực tiếp bằng từ khóa "new".
 *
 * Áp dụng: Hệ thống có nhiều dịch vụ nghiệp vụ khác nhau:
 * - Quản lý sách
 * - Quản lý đơn hàng
 * - Quản lý tài khoản
 * - Quản lý thanh toán
 *
 * Khi cần sử dụng dịch vụ nào, chỉ cần gọi:
 *   ServiceFactory.taoDichVu("SACH")
 * thay vì phải viết: new DichVuQuanLySach()
 *
 * Lợi ích:
 * - Giảm sự phụ thuộc giữa các lớp
 * - Dễ dàng bổ sung các dịch vụ mới
 * - Mã nguồn dễ bảo trì và mở rộng
 */
public class ServiceFactory {

    /**
     * Phương thức Factory tạo đối tượng dịch vụ dựa trên tên loại dịch vụ.
     *
     * @param loaiDichVu tên loại dịch vụ cần tạo ("SACH", "DONHANG", "TAIKHOAN")
     * @return đối tượng DichVu tương ứng
     * @throws IllegalArgumentException nếu loại dịch vụ không hợp lệ
     */
    public static DichVu taoDichVu(String loaiDichVu) {

        // Dựa vào tên loại dịch vụ để tạo đối tượng phù hợp
        switch (loaiDichVu.toUpperCase()) {

            case "SACH":
                System.out.println("[Factory] Đã tạo dịch vụ: Quản lý Sách");
                return new DichVuQuanLySach();

            case "DONHANG":
                System.out.println("[Factory] Đã tạo dịch vụ: Quản lý Đơn hàng");
                return new DichVuQuanLyDonHang();

            case "TAIKHOAN":
                System.out.println("[Factory] Đã tạo dịch vụ: Quản lý Tài khoản");
                return new DichVuQuanLyTaiKhoan();

            default:
                throw new IllegalArgumentException("Loại dịch vụ không hợp lệ: " + loaiDichVu);
        }
    }
}
