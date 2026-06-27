package com.bookstore.backend.model;

import java.io.Serializable;
import java.util.Objects;

// Class dùng để định nghĩa khóa chính kép cho bảng chitietdonhang
public class ChiTietDonHangId implements Serializable {

    private Integer idDonHang;
    private Integer idSach;

    // Constructor rỗng
    public ChiTietDonHangId() {
    }

    // Constructor đầy đủ tham số
    public ChiTietDonHangId(Integer idDonHang, Integer idSach) {
        this.idDonHang = idDonHang;
        this.idSach = idSach;
    }

    public Integer getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(Integer idDonHang) {
        this.idDonHang = idDonHang;
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
        if (!(o instanceof ChiTietDonHangId)) return false;
        ChiTietDonHangId that = (ChiTietDonHangId) o;
        return Objects.equals(idDonHang, that.idDonHang)
                && Objects.equals(idSach, that.idSach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDonHang, idSach);
    }
}