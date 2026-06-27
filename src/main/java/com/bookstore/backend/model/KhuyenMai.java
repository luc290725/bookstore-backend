package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class KhuyenMai ánh xạ với bảng khuyenmai trong MySQL
@Table(name = "khuyenmai")
public class KhuyenMai {

    // Khóa chính của bảng khuyenmai
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ánh xạ với cột makhuyenmai trong database
    @NotBlank(message = "Mã khuyến mãi không được để trống")
    @Size(max = 50, message = "Mã khuyến mãi không được vượt quá 50 ký tự")
    @Column(name = "makhuyenmai", nullable = false, length = 50)
    private String maKhuyenMai;

    // Ánh xạ với cột phantramgiam trong database
    @NotNull(message = "Phần trăm giảm không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Phần trăm giảm phải lớn hơn 0")
    @DecimalMax(value = "100.0", message = "Phần trăm giảm không được vượt quá 100")
    @Column(name = "phantramgiam", nullable = false, precision = 5, scale = 2)
    private BigDecimal phanTramGiam;

    // Ánh xạ với cột ngaybatdau trong database
    @NotNull(message = "Ngày bắt đầu không được để trống")
    @Column(name = "ngaybatdau", nullable = false)
    private LocalDate ngayBatDau;

    // Ánh xạ với cột ngayketthuc trong database
    @NotNull(message = "Ngày kết thúc không được để trống")
    @Column(name = "ngayketthuc", nullable = false)
    private LocalDate ngayKetThuc;

    // Ánh xạ với cột dieukien trong database
    @Column(name = "dieukien", columnDefinition = "TEXT")
    private String dieuKien;

    // Constructor rỗng
    public KhuyenMai() {
    }

    // Constructor đầy đủ tham số
    public KhuyenMai(Integer id, String maKhuyenMai, BigDecimal phanTramGiam,
                     LocalDate ngayBatDau, LocalDate ngayKetThuc, String dieuKien) {
        this.id = id;
        this.maKhuyenMai = maKhuyenMai;
        this.phanTramGiam = phanTramGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.dieuKien = dieuKien;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho maKhuyenMai
    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    // Getter và Setter cho phanTramGiam
    public BigDecimal getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(BigDecimal phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    // Getter và Setter cho ngayBatDau
    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    // Getter và Setter cho ngayKetThuc
    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    // Getter và Setter cho dieuKien
    public String getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(String dieuKien) {
        this.dieuKien = dieuKien;
    }
}