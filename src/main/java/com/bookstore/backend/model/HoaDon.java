package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class HoaDon ánh xạ với bảng hoadon trong MySQL
@Table(name = "hoadon")
public class HoaDon {

    // Khóa chính của bảng hoadon
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Khóa ngoại liên kết với bảng donhang
    @NotNull(message = "Id đơn hàng không được để trống")
    @Column(name = "iddonhang", nullable = false)
    private Integer idDonHang;

    // Ánh xạ với cột ngaylap trong database
    @PastOrPresent(message = "Ngày lập hóa đơn không được lớn hơn ngày hiện tại")
    @Column(name = "ngaylap")
    private LocalDate ngayLap;

    // Ánh xạ với cột tongtien trong database
    @NotNull(message = "Tổng tiền không được để trống")
    @DecimalMin(value = "0.0", inclusive = true, message = "Tổng tiền không được âm")
    @Column(name = "tongtien", nullable = false, precision = 12, scale = 2)
    private BigDecimal tongTien;

    // Constructor rỗng
    public HoaDon() {
    }

    // Constructor đầy đủ tham số
    public HoaDon(Integer id, Integer idDonHang, LocalDate ngayLap, BigDecimal tongTien) {
        this.id = id;
        this.idDonHang = idDonHang;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho idDonHang
    public Integer getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(Integer idDonHang) {
        this.idDonHang = idDonHang;
    }

    // Getter và Setter cho ngayLap
    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    // Getter và Setter cho tongTien
    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
}