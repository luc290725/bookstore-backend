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

// Class NhaXuatBan ánh xạ với bảng nhaxuatban trong MySQL
@Table(name = "nhaxuatban")
public class NhaXuatBan {

    // Khóa chính của bảng nhaxuatban
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ánh xạ với cột tennxb trong database
    @NotBlank(message = "Tên nhà xuất bản không được để trống")
    @Size(max = 100, message = "Tên nhà xuất bản không được vượt quá 100 ký tự")
    @Column(name = "tennxb", nullable = false, length = 100)
    private String tenNxb;

    // Ánh xạ với cột diachi trong database
    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    @Column(name = "diachi", length = 255)
    private String diaChi;

    // Ánh xạ với cột sodienthoai trong database
    @Size(max = 20, message = "Số điện thoại không được vượt quá 20 ký tự")
    @Column(name = "sodienthoai", length = 20)
    private String soDienThoai;

    // Constructor rỗng
    public NhaXuatBan() {
    }

    // Constructor đầy đủ tham số
    public NhaXuatBan(Integer id, String tenNxb, String diaChi, String soDienThoai) {
        this.id = id;
        this.tenNxb = tenNxb;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho tenNxb
    public String getTenNxb() {
        return tenNxb;
    }

    public void setTenNxb(String tenNxb) {
        this.tenNxb = tenNxb;
    }

    // Getter và Setter cho diaChi
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // Getter và Setter cho soDienThoai
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}