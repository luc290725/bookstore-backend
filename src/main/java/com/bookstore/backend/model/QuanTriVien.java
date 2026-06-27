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
import jakarta.validation.constraints.Size;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class QuanTriVien ánh xạ với bảng quantrivien trong MySQL
@Table(name = "quantrivien")
public class QuanTriVien {

    // Khóa chính của bảng quantrivien
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Khóa ngoại liên kết với bảng taikhoan
    @NotNull(message = "Id tài khoản không được để trống")
    @Column(name = "idtaikhoan", nullable = false)
    private Integer idTaiKhoan;

    // Ánh xạ với cột hoten trong database
    @NotBlank(message = "Họ tên quản trị viên không được để trống")
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    @Column(name = "hoten", nullable = false, length = 100)
    private String hoTen;

    // Ánh xạ với cột email trong database
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 100, message = "Email không được vượt quá 100 ký tự")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // Constructor rỗng
    public QuanTriVien() {
    }

    // Constructor đầy đủ tham số
    public QuanTriVien(Integer id, Integer idTaiKhoan, String hoTen, String email) {
        this.id = id;
        this.idTaiKhoan = idTaiKhoan;
        this.hoTen = hoTen;
        this.email = email;
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

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}