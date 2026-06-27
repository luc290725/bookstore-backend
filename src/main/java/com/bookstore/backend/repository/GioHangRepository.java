package com.bookstore.backend.repository;

import com.bookstore.backend.model.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

    // lấy tất cả giỏ hàng
    @Query(value = "SELECT * FROM giohang", nativeQuery = true)
    List<GioHang> getAllCarts();

    // lấy giỏ hàng theo id
    @Query(value = "SELECT * FROM giohang WHERE id = :id", nativeQuery = true)
    GioHang getCartById(@Param("id") Integer id);

    // lấy giỏ hàng theo id khách hàng
    @Query(value = "SELECT * FROM giohang WHERE idkhachhang = :idKhachHang", nativeQuery = true)
    GioHang getCartByCustomerId(@Param("idKhachHang") Integer idKhachHang);

    // thêm giỏ hàng mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO giohang(idkhachhang, tongtien) VALUES (:idKhachHang, :tongTien)",
            nativeQuery = true)
    int insertCart(@Param("idKhachHang") Integer idKhachHang,
                   @Param("tongTien") BigDecimal tongTien);

    // cập nhật giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE giohang SET idkhachhang = :idKhachHang, tongtien = :tongTien WHERE id = :id",
            nativeQuery = true)
    int updateCart(@Param("id") Integer id,
                   @Param("idKhachHang") Integer idKhachHang,
                   @Param("tongTien") BigDecimal tongTien);

    // cập nhật tổng tiền giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE giohang SET tongtien = :tongTien WHERE id = :id",
            nativeQuery = true)
    int updateCartTotal(@Param("id") Integer id,
                        @Param("tongTien") BigDecimal tongTien);

    // xóa giỏ hàng
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM giohang WHERE id = :id", nativeQuery = true)
    int deleteCart(@Param("id") Integer id);
}