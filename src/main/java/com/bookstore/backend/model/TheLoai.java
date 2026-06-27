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

// Class TheLoai ánh xạ với bảng theloai trong MySQL
@Table(name = "theloai")
public class TheLoai {

    // Khóa chính của bảng theloai
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ánh xạ với cột tentheloai trong database
    @NotBlank(message = "Tên thể loại không được để trống")
    @Size(max = 100, message = "Tên thể loại không được vượt quá 100 ký tự")
    @Column(name = "tentheloai", nullable = false, length = 100)
    private String tenTheLoai;

    // Ánh xạ với cột mota trong database
    @Column(name = "mota", columnDefinition = "TEXT")
    private String moTa;

    // Constructor rỗng
    public TheLoai() {
    }

    // Constructor đầy đủ tham số
    public TheLoai(Integer id, String tenTheLoai, String moTa) {
        this.id = id;
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho tenTheLoai
    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    // Getter và Setter cho moTa
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
