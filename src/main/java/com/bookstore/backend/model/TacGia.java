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

// Class TacGia ánh xạ với bảng tacgia trong MySQL
@Table(name = "tacgia")
public class TacGia {

    // Khóa chính của bảng tacgia
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ánh xạ với cột tentacgia trong database
    @NotBlank(message = "Tên tác giả không được để trống")
    @Size(max = 100, message = "Tên tác giả không được vượt quá 100 ký tự")
    @Column(name = "tentacgia", nullable = false, length = 100)
    private String tenTacGia;

    // Ánh xạ với cột tieusu trong database
    @Column(name = "tieusu", columnDefinition = "TEXT")
    private String tieuSu;

    // Constructor rỗng
    public TacGia() {
    }

    // Constructor đầy đủ tham số
    public TacGia(Integer id, String tenTacGia, String tieuSu) {
        this.id = id;
        this.tenTacGia = tenTacGia;
        this.tieuSu = tieuSu;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho tenTacGia
    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    // Getter và Setter cho tieuSu
    public String getTieuSu() {
        return tieuSu;
    }

    public void setTieuSu(String tieuSu) {
        this.tieuSu = tieuSu;
    }
}