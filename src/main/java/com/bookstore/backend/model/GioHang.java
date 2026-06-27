package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class GioHang ánh xạ với bảng giohang trong MySQL
@Table(name = "giohang")
public class GioHang {

    // Khóa chính của bảng giohang
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Khóa ngoại liên kết với bảng khachhang
    @NotNull(message = "Id khách hàng không được để trống")
    @Column(name = "idkhachhang", nullable = false)
    private Integer idKhachHang;

    // Ánh xạ với cột tongtien trong database
    @DecimalMin(value = "0.0", inclusive = true, message = "Tổng tiền không được âm")
    @Column(name = "tongtien", precision = 12, scale = 2)
    private BigDecimal tongTien;

    // Constructor rỗng
    public GioHang() {
    }

    // Constructor đầy đủ tham số
    public GioHang(Integer id, Integer idKhachHang, BigDecimal tongTien) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.tongTien = tongTien;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho idKhachHang
    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    // Getter và Setter cho tongTien
    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
}