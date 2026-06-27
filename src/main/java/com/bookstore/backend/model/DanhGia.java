package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class DanhGia ánh xạ với bảng danhgia trong MySQL
@Table(name = "danhgia")
public class DanhGia {

    // Khóa chính của bảng danhgia
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Khóa ngoại liên kết với bảng khachhang
    @NotNull(message = "Id khách hàng không được để trống")
    @Column(name = "idkhachhang", nullable = false)
    private Integer idKhachHang;

    // Khóa ngoại liên kết với bảng sach
    @NotNull(message = "Id sách không được để trống")
    @Column(name = "idsach", nullable = false)
    private Integer idSach;

    // Ánh xạ với cột sosao trong database
    @NotNull(message = "Số sao không được để trống")
    @Min(value = 1, message = "Số sao phải từ 1 đến 5")
    @Max(value = 5, message = "Số sao phải từ 1 đến 5")
    @Column(name = "sosao", nullable = false)
    private Integer soSao;

    // Ánh xạ với cột binhluan trong database
    @Size(max = 1000, message = "Bình luận không được vượt quá 1000 ký tự")
    @Column(name = "binhluan", columnDefinition = "TEXT")
    private String binhLuan;

    // Ánh xạ với cột ngaydanhgia trong database
    @PastOrPresent(message = "Ngày đánh giá không được lớn hơn ngày hiện tại")
    @Column(name = "ngaydanhgia")
    private LocalDate ngayDanhGia;

    // Constructor rỗng
    public DanhGia() {
    }

    // Constructor đầy đủ tham số
    public DanhGia(Integer id, Integer idKhachHang, Integer idSach,
                   Integer soSao, String binhLuan, LocalDate ngayDanhGia) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.idSach = idSach;
        this.soSao = soSao;
        this.binhLuan = binhLuan;
        this.ngayDanhGia = ngayDanhGia;
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

    // Getter và Setter cho idSach
    public Integer getIdSach() {
        return idSach;
    }

    public void setIdSach(Integer idSach) {
        this.idSach = idSach;
    }

    // Getter và Setter cho soSao
    public Integer getSoSao() {
        return soSao;
    }

    public void setSoSao(Integer soSao) {
        this.soSao = soSao;
    }

    // Getter và Setter cho binhLuan
    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    // Getter và Setter cho ngayDanhGia
    public LocalDate getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(LocalDate ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }
}