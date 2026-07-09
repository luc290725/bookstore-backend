package com.bookstore.backend.dto;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutRequest {
    private Integer idTaiKhoan;
    private BigDecimal tongTien;
    private List<CartItemDTO> items;

    public CheckoutRequest() {}

    public CheckoutRequest(Integer idTaiKhoan, BigDecimal tongTien, List<CartItemDTO> items) {
        this.idTaiKhoan = idTaiKhoan;
        this.tongTien = tongTien;
        this.items = items;
    }

    public Integer getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(Integer idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
}
