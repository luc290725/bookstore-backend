package com.bookstore.backend.model;

import java.io.Serializable;
import java.util.Objects;

// Class dùng để định nghĩa khóa chính kép cho bảng chitietgiohang
public class ChiTietGioHangId implements Serializable {

    private Integer idGioHang;
    private Integer idSach;

    // Constructor rỗng
    public ChiTietGioHangId() {
    }

    // Constructor đầy đủ tham số
    public ChiTietGioHangId(Integer idGioHang, Integer idSach) {
        this.idGioHang = idGioHang;
        this.idSach = idSach;
    }

    public Integer getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(Integer idGioHang) {
        this.idGioHang = idGioHang;
    }

    public Integer getIdSach() {
        return idSach;
    }

    public void setIdSach(Integer idSach) {
        this.idSach = idSach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietGioHangId)) return false;
        ChiTietGioHangId that = (ChiTietGioHangId) o;
        return Objects.equals(idGioHang, that.idGioHang)
                && Objects.equals(idSach, that.idSach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGioHang, idSach);
    }
}