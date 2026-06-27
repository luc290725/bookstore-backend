package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class TaiKhoan ánh xạ với bảng taikhoan trong MySQL
@Table(name = "taikhoan")
public class TaiKhoan {

    // Khóa chính của bảng taikhoan
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ánh xạ với cột tendangnhap trong database
    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(max = 50, message = "Tên đăng nhập không được vượt quá 50 ký tự")
    @Column(name = "tendangnhap", nullable = false, length = 50)
    private String tenDangNhap;

    // Ánh xạ với cột matkhau trong database
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(max = 100, message = "Mật khẩu không được vượt quá 100 ký tự")
    @Column(name = "matkhau", nullable = false, length = 100)
    private String matKhau;

    // Ánh xạ với cột email trong database
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 100, message = "Email không được vượt quá 100 ký tự")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Ánh xạ với cột vaitro trong database
    @NotBlank(message = "Vai trò không được để trống")
    @Size(max = 20, message = "Vai trò không được vượt quá 20 ký tự")
    @Column(name = "vaitro", nullable = false, length = 20)
    private String vaiTro;

    // Constructor rỗng
    public TaiKhoan() {
    }

    // Constructor đầy đủ tham số
    public TaiKhoan(Integer id, String tenDangNhap, String matKhau, String email, String vaiTro) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
        this.vaiTro = vaiTro;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho tenDangNhap
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    // Getter và Setter cho matKhau
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và Setter cho vaiTro
    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
}