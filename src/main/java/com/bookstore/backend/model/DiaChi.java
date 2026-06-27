package com.bookstore.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Đánh dấu class này là Entity, ánh xạ với bảng trong database
@Entity

// Class DiaChi ánh xạ với bảng diachi trong MySQL
@Table(name = "diachi")
public class DiaChi {

    // Khóa chính của bảng diachi
    @Id

    // id tự động tăng giống AUTO_INCREMENT trong MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Khóa ngoại liên kết với bảng khachhang
    @NotNull(message = "Id khách hàng không được để trống")
    @Column(name = "idkhachhang", nullable = false)
    private Integer idKhachHang;

    // Ánh xạ với cột sonha trong database
    @NotBlank(message = "Số nhà không được để trống")
    @Size(max = 100, message = "Số nhà không được vượt quá 100 ký tự")
    @Column(name = "sonha", nullable = false, length = 100)
    private String soNha;

    // Ánh xạ với cột phuongxa trong database
    @NotBlank(message = "Phường/xã không được để trống")
    @Size(max = 100, message = "Phường/xã không được vượt quá 100 ký tự")
    @Column(name = "phuongxa", nullable = false, length = 100)
    private String phuongXa;

    // Ánh xạ với cột quanhuyen trong database
    @NotBlank(message = "Quận/huyện không được để trống")
    @Size(max = 100, message = "Quận/huyện không được vượt quá 100 ký tự")
    @Column(name = "quanhuyen", nullable = false, length = 100)
    private String quanHuyen;

    // Ánh xạ với cột tinhtp trong database
    @NotBlank(message = "Tỉnh/thành phố không được để trống")
    @Size(max = 100, message = "Tỉnh/thành phố không được vượt quá 100 ký tự")
    @Column(name = "tinhtp", nullable = false, length = 100)
    private String tinhTp;

    // Ánh xạ với cột macdinh trong database
    @Column(name = "macdinh")
    private Boolean macDinh;

    // Constructor rỗng
    public DiaChi() {
    }

    // Constructor đầy đủ tham số
    public DiaChi(Integer id, Integer idKhachHang, String soNha, String phuongXa,
                  String quanHuyen, String tinhTp, Boolean macDinh) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.soNha = soNha;
        this.phuongXa = phuongXa;
        this.quanHuyen = quanHuyen;
        this.tinhTp = tinhTp;
        this.macDinh = macDinh;
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

    // Getter và Setter cho soNha
    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    // Getter và Setter cho phuongXa
    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    // Getter và Setter cho quanHuyen
    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    // Getter và Setter cho tinhTp
    public String getTinhTp() {
        return tinhTp;
    }

    public void setTinhTp(String tinhTp) {
        this.tinhTp = tinhTp;
    }

    // Getter và Setter cho macDinh
    public Boolean getMacDinh() {
        return macDinh;
    }

    public void setMacDinh(Boolean macDinh) {
        this.macDinh = macDinh;
    }
}