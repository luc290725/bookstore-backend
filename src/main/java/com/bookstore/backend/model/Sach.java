package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class Sach ánh xạ với bảng sach trong MySQL
@Table(name = "sach")
public class Sach {

    // Khóa chính của bảng sach
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ánh xạ với cột tensach trong database
    @NotBlank(message = "Tên sách không được để trống")
    @Size(max = 200, message = "Tên sách không được vượt quá 200 ký tự")
    @Column(name = "tensach", nullable = false, length = 200)
    private String tenSach;

    // Ánh xạ với cột giaban trong database
    @NotNull(message = "Giá bán không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá bán phải lớn hơn 0")
    @Column(name = "giaban", nullable = false, precision = 12, scale = 2)
    private BigDecimal giaBan;

    // Ánh xạ với cột mota trong database
    @Column(name = "mota", columnDefinition = "TEXT")
    private String moTa;

    // Ánh xạ với cột namxuatban trong database
    @Min(value = 0, message = "Năm xuất bản không hợp lệ")
    @Column(name = "namxuatban")
    private Integer namXuatBan;

    // Ánh xạ với cột sotrang trong database
    @Min(value = 1, message = "Số trang phải lớn hơn 0")
    @Column(name = "sotrang")
    private Integer soTrang;

    // Ánh xạ với cột hinhanh trong database
    @Size(max = 255, message = "Đường dẫn hình ảnh không được vượt quá 255 ký tự")
    @Column(name = "hinhanh", length = 255)
    private String hinhAnh;

    // Ánh xạ với cột soluongton trong database
    @Min(value = 0, message = "Số lượng tồn không được âm")
    @Column(name = "soluongton")
    private Integer soLuongTon;

    // Khóa ngoại liên kết với bảng theloai
    @NotNull(message = "Id thể loại không được để trống")
    @Column(name = "idtheloai", nullable = false)
    private Integer idTheLoai;

    // Khóa ngoại liên kết với bảng tacgia
    @NotNull(message = "Id tác giả không được để trống")
    @Column(name = "idtacgia", nullable = false)
    private Integer idTacGia;

    // Khóa ngoại liên kết với bảng nhaxuatban
    @NotNull(message = "Id nhà xuất bản không được để trống")
    @Column(name = "idnxb", nullable = false)
    private Integer idNxb;

    // Constructor rỗng
    public Sach() {
    }

    // Constructor đầy đủ tham số
    public Sach(Integer id, String tenSach, BigDecimal giaBan, String moTa,
                Integer namXuatBan, Integer soTrang, String hinhAnh,
                Integer soLuongTon, Integer idTheLoai, Integer idTacGia, Integer idNxb) {
        this.id = id;
        this.tenSach = tenSach;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.namXuatBan = namXuatBan;
        this.soTrang = soTrang;
        this.hinhAnh = hinhAnh;
        this.soLuongTon = soLuongTon;
        this.idTheLoai = idTheLoai;
        this.idTacGia = idTacGia;
        this.idNxb = idNxb;
    }

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho tenSach
    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    // Getter và Setter cho giaBan
    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    // Getter và Setter cho moTa
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Getter và Setter cho namXuatBan
    public Integer getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(Integer namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    // Getter và Setter cho soTrang
    public Integer getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(Integer soTrang) {
        this.soTrang = soTrang;
    }

    // Getter và Setter cho hinhAnh
    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    // Getter và Setter cho soLuongTon
    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    // Getter và Setter cho idTheLoai
    public Integer getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Integer idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    // Getter và Setter cho idTacGia
    public Integer getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(Integer idTacGia) {
        this.idTacGia = idTacGia;
    }

    // Getter và Setter cho idNxb
    public Integer getIdNxb() {
        return idNxb;
    }

    public void setIdNxb(Integer idNxb) {
        this.idNxb = idNxb;
    }
}