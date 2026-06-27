package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class DonHang ánh xạ với bảng donhang trong MySQL
@Table(name = "donhang")
public class DonHang {

    // Khóa chính của bảng donhang
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Khóa ngoại liên kết với bảng khachhang
    @NotNull(message = "Id khách hàng không được để trống")
    @Column(name = "idkhachhang", nullable = false)
    private Integer idKhachHang;

    // Ánh xạ với cột ngaydat trong database
    @Column(name = "ngaydat")
    private LocalDate ngayDat;

    // Ánh xạ với cột tongtien trong database
    @DecimalMin(value = "0.0", inclusive = true, message = "Tổng tiền không được âm")
    @Column(name = "tongtien", precision = 12, scale = 2)
    private BigDecimal tongTien;

    // Ánh xạ với cột trangthai trong database
    @Size(max = 50, message = "Trạng thái không được vượt quá 50 ký tự")
    @Column(name = "trangthai", length = 50)
    private String trangThai;

    // Constructor rỗng
    public DonHang() {
    }

    // Constructor đầy đủ tham số
    public DonHang(Integer id, Integer idKhachHang, LocalDate ngayDat,
                   BigDecimal tongTien, String trangThai) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
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

    // Getter và Setter cho ngayDat
    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }

    // Getter và Setter cho tongTien
    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    // Getter và Setter cho trangThai
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}