package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class KhachHang ánh xạ với bảng khachhang trong MySQL
@Table(name = "khachhang")
public class KhachHang {

    // Khóa chính của bảng khachhang
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Khóa ngoại liên kết với bảng taikhoan
    @NotNull(message = "Id tài khoản không được để trống")
    @Column(name = "idtaikhoan", nullable = false)
    private Integer idTaiKhoan;

    // Ánh xạ với cột hoten trong database
    @NotBlank(message = "Họ tên khách hàng không được để trống")
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    @Column(name = "hoten", nullable = false, length = 100)
    private String hoTen;

    // Ánh xạ với cột sodienthoai trong database
    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    @Column(name = "sodienthoai", nullable = false, length = 20)
    private String soDienThoai;

    // Ánh xạ với cột email trong database
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 100, message = "Email không được vượt quá 100 ký tự")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Ánh xạ với cột ngaysinh trong database
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;

    // Ánh xạ với cột gioitinh trong database
    @Size(max = 10, message = "Giới tính không được vượt quá 10 ký tự")
    @Column(name = "gioitinh", length = 10)
    private String gioiTinh;

    // Ánh xạ với cột ngaydangky trong database
    @PastOrPresent(message = "Ngày đăng ký không được lớn hơn ngày hiện tại")
    @Column(name = "ngaydangky")
    private LocalDate ngayDangKy;

    // Constructor rỗng
    public KhachHang() {
    }

    // Constructor đầy đủ tham số
    public KhachHang(Integer id, Integer idTaiKhoan, String hoTen, String soDienThoai,
                     String email, LocalDate ngaySinh, String gioiTinh, LocalDate ngayDangKy) {
        this.id = id;
        this.idTaiKhoan = idTaiKhoan;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.ngayDangKy = ngayDangKy;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho idTaiKhoan
    public Integer getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(Integer idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    // Getter và Setter cho hoTen
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    // Getter và Setter cho soDienThoai
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và Setter cho ngaySinh
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    // Getter và Setter cho gioiTinh
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    // Getter và Setter cho ngayDangKy
    public LocalDate getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(LocalDate ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }
}