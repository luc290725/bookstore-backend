package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class VanChuyen ánh xạ với bảng vanchuyen trong MySQL
@Table(name = "vanchuyen")
public class VanChuyen {

    // Khóa chính của bảng vanchuyen
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ánh xạ với cột donvi trong database
    @NotBlank(message = "Đơn vị vận chuyển không được để trống")
    @Size(max = 100, message = "Đơn vị vận chuyển không được vượt quá 100 ký tự")
    @Column(name = "donvi", nullable = false, length = 100)
    private String donVi;

    // Ánh xạ với cột phivanchuyen trong database
    @NotNull(message = "Phí vận chuyển không được để trống")
    @DecimalMin(value = "0.0", inclusive = true, message = "Phí vận chuyển không được âm")
    @Column(name = "phivanchuyen", nullable = false, precision = 12, scale = 2)
    private BigDecimal phiVanChuyen;

    // Ánh xạ với cột thoigian trong database
    @Size(max = 100, message = "Thời gian giao hàng không được vượt quá 100 ký tự")
    @Column(name = "thoigian", length = 100)
    private String thoiGian;

    // Constructor rỗng
    public VanChuyen() {
    }

    // Constructor đầy đủ tham số
    public VanChuyen(Integer id, String donVi, BigDecimal phiVanChuyen, String thoiGian) {
        this.id = id;
        this.donVi = donVi;
        this.phiVanChuyen = phiVanChuyen;
        this.thoiGian = thoiGian;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho donVi
    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    // Getter và Setter cho phiVanChuyen
    public BigDecimal getPhiVanChuyen() {
        return phiVanChuyen;
    }

    public void setPhiVanChuyen(BigDecimal phiVanChuyen) {
        this.phiVanChuyen = phiVanChuyen;
    }

    // Getter và Setter cho thoiGian
    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}