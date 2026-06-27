package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Khai báo bảng này dùng khóa chính kép
@IdClass(ChiTietGioHangId.class)

// Class ChiTietGioHang ánh xạ với bảng chitietgiohang trong MySQL
@Table(name = "chitietgiohang")
public class ChiTietGioHang {

    // Khóa chính kép: idgiohang
    @Id
    @NotNull(message = "Id giỏ hàng không được để trống")
    @Column(name = "idgiohang", nullable = false)
    private Integer idGioHang;

    // Khóa chính kép: idsach
    @Id
    @NotNull(message = "Id sách không được để trống")
    @Column(name = "idsach", nullable = false)
    private Integer idSach;

    // Ánh xạ với cột soluong trong database
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    @Column(name = "soluong", nullable = false)
    private Integer soLuong;

    // Ánh xạ với cột dongia trong database
    @NotNull(message = "Đơn giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Đơn giá phải lớn hơn 0")
    @Column(name = "dongia", nullable = false, precision = 12, scale = 2)
    private BigDecimal donGia;

    // Constructor rỗng
    public ChiTietGioHang() {
    }

    // Constructor đầy đủ tham số
    public ChiTietGioHang(Integer idGioHang, Integer idSach, Integer soLuong, BigDecimal donGia) {
        this.idGioHang = idGioHang;
        this.idSach = idSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getter và Setter cho idGioHang
    public Integer getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(Integer idGioHang) {
        this.idGioHang = idGioHang;
    }

    // Getter và Setter cho idSach
    public Integer getIdSach() {
        return idSach;
    }

    public void setIdSach(Integer idSach) {
        this.idSach = idSach;
    }

    // Getter và Setter cho soLuong
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    // Getter và Setter cho donGia
    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }
}