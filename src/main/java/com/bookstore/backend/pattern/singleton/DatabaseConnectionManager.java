package com.bookstore.backend.pattern.singleton;

/**
 * SINGLETON PATTERN - Mẫu thiết kế Singleton
 *
 * Mục đích: Đảm bảo rằng một lớp chỉ có DUY NHẤT MỘT đối tượng (instance)
 * được tạo ra trong suốt quá trình hoạt động của hệ thống.
 *
 * Áp dụng: Quản lý kết nối cơ sở dữ liệu MySQL.
 * Tất cả các chức năng (quản lý sách, đơn hàng, khách hàng...) đều dùng chung
 * một đối tượng kết nối này thay vì mỗi chức năng tự tạo kết nối riêng.
 *
 * Lợi ích:
 * - Tiết kiệm tài nguyên hệ thống
 * - Hạn chế việc tạo nhiều kết nối không cần thiết
 * - Đảm bảo tính nhất quán khi truy xuất dữ liệu
 *
 * GHI CHÚ: Trong Spring Boot, tất cả các Bean (@Service, @Repository, @Controller)
 * mặc định đã là Singleton do Spring IoC Container quản lý.
 * Class này minh họa cách viết Singleton truyền thống bằng tay.
 */
public class DatabaseConnectionManager {

    // Biến static giữ đối tượng duy nhất của class này
    // volatile đảm bảo biến được đọc/ghi chính xác trong môi trường đa luồng
    private static volatile DatabaseConnectionManager instance;

    // Thông tin kết nối cơ sở dữ liệu
    private String url;
    private String username;
    private String password;

    // Constructor được đặt là private để KHÔNG cho phép tạo đối tượng từ bên ngoài
    private DatabaseConnectionManager() {
        // Thông tin kết nối mặc định đến MySQL
        this.url = "jdbc:mysql://localhost:3306/web_ban_sach";
        this.username = "root";
        this.password = "123456";

        System.out.println("[Singleton] Đã tạo kết nối đến cơ sở dữ liệu: " + this.url);
    }

    /**
     * Phương thức static duy nhất để lấy đối tượng DatabaseConnectionManager.
     * Nếu đối tượng chưa tồn tại thì tạo mới.
     * Nếu đã tồn tại thì trả về đối tượng cũ (không tạo thêm).
     *
     * Sử dụng Double-Checked Locking để đảm bảo an toàn trong đa luồng.
     */
    public static DatabaseConnectionManager getInstance() {
        // Kiểm tra lần 1: Nếu đã có instance thì trả về luôn (không cần vào synchronized)
        if (instance == null) {
            // synchronized đảm bảo chỉ có 1 luồng được vào đoạn code này tại một thời điểm
            synchronized (DatabaseConnectionManager.class) {
                // Kiểm tra lần 2: Đề phòng trường hợp 2 luồng cùng vượt qua kiểm tra lần 1
                if (instance == null) {
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    // Getter cho URL kết nối
    public String getUrl() {
        return url;
    }

    // Getter cho username
    public String getUsername() {
        return username;
    }

    // Getter cho password
    public String getPassword() {
        return password;
    }
}
