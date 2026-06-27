package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class PhuongThucThanhToan ánh xạ với bảng phuongthucthanhtoan trong MySQL
@Table(name = "phuongthucthanhtoan")
public class PhuongThucThanhToan {

    // Khóa chính của bảng phuongthucthanhtoan
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ánh xạ với cột tenpt trong database
    @NotBlank(message = "Tên phương thức thanh toán không được để trống")
    @Size(max = 100, message = "Tên phương thức thanh toán không được vượt quá 100 ký tự")
    @Column(name = "tenpt", nullable = false, length = 100)
    private String tenPt;

    // Ánh xạ với cột mota trong database
    @Column(name = "mota", columnDefinition = "TEXT")
    private String moTa;

    // Constructor rỗng
    public PhuongThucThanhToan() {
    }

    // Constructor đầy đủ tham số
    public PhuongThucThanhToan(Integer id, String tenPt, String moTa) {
        this.id = id;
        this.tenPt = tenPt;
        this.moTa = moTa;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho tenPt
    public String getTenPt() {
        return tenPt;
    }

    public void setTenPt(String tenPt) {
        this.tenPt = tenPt;
    }

    // Getter và Setter cho moTa
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}