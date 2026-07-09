package com.bookstore.backend.dto;

public class CartItemDTO {
    private Integer id; // ID Sách
    private Integer soLuong;
    private Double donGia;

    public CartItemDTO() {}

    public CartItemDTO(Integer id, Integer soLuong, Double donGia) {
        this.id = id;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }
}
