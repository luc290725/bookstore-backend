package com.bookstore.backend.repository;

import com.bookstore.backend.model.ChiTietGioHang;
import com.bookstore.backend.model.ChiTietGioHangId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, ChiTietGioHangId> {

    // lấy tất cả chi tiết giỏ hàng
    @Query(value = "SELECT * FROM chitietgiohang", nativeQuery = true)
    List<ChiTietGioHang> getAllCartDetails();

    // lấy danh sách chi tiết theo id giỏ hàng
    @Query(value = "SELECT * FROM chitietgiohang WHERE idgiohang = :idGioHang", nativeQuery = true)
    List<ChiTietGioHang> getCartDetailsByCartId(@Param("idGioHang") Integer idGioHang);

    // lấy một chi tiết giỏ hàng theo id giỏ hàng và id sách
    @Query(value = "SELECT * FROM chitietgiohang WHERE idgiohang = :idGioHang AND idsach = :idSach", nativeQuery = true)
    ChiTietGioHang getCartDetail(@Param("idGioHang") Integer idGioHang,
                                 @Param("idSach") Integer idSach);

    // thêm chi tiết giỏ hàng mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO chitietgiohang(idgiohang, idsach, soluong, dongia) " +
            "VALUES (:idGioHang, :idSach, :soLuong, :donGia)",
            nativeQuery = true)
    int insertCartDetail(@Param("idGioHang") Integer idGioHang,
                         @Param("idSach") Integer idSach,
                         @Param("soLuong") Integer soLuong,
                         @Param("donGia") BigDecimal donGia);

    // cập nhật chi tiết giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE chitietgiohang SET soluong = :soLuong, dongia = :donGia " +
            "WHERE idgiohang = :idGioHang AND idsach = :idSach",
            nativeQuery = true)
    int updateCartDetail(@Param("idGioHang") Integer idGioHang,
                         @Param("idSach") Integer idSach,
                         @Param("soLuong") Integer soLuong,
                         @Param("donGia") BigDecimal donGia);

    // cập nhật số lượng sách trong giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE chitietgiohang SET soluong = :soLuong " +
            "WHERE idgiohang = :idGioHang AND idsach = :idSach",
            nativeQuery = true)
    int updateQuantity(@Param("idGioHang") Integer idGioHang,
                       @Param("idSach") Integer idSach,
                       @Param("soLuong") Integer soLuong);

    // xóa một sách khỏi giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chitietgiohang WHERE idgiohang = :idGioHang AND idsach = :idSach",
            nativeQuery = true)
    int deleteCartDetail(@Param("idGioHang") Integer idGioHang,
                         @Param("idSach") Integer idSach);

    // xóa toàn bộ chi tiết của một giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chitietgiohang WHERE idgiohang = :idGioHang",
            nativeQuery = true)
    int deleteCartDetailsByCartId(@Param("idGioHang") Integer idGioHang);
}