package com.bookstore.backend.repository;

import com.bookstore.backend.model.ChiTietDonHang;
import com.bookstore.backend.model.ChiTietDonHangId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, ChiTietDonHangId> {

    // lấy tất cả chi tiết đơn hàng
    @Query(value = "SELECT * FROM chitietdonhang", nativeQuery = true)
    List<ChiTietDonHang> getAllOrderDetails();

    // lấy danh sách chi tiết theo id đơn hàng
    @Query(value = "SELECT * FROM chitietdonhang WHERE iddonhang = :idDonHang", nativeQuery = true)
    List<ChiTietDonHang> getOrderDetailsByOrderId(@Param("idDonHang") Integer idDonHang);

    // lấy một chi tiết đơn hàng theo id đơn hàng và id sách
    @Query(value = "SELECT * FROM chitietdonhang WHERE iddonhang = :idDonHang AND idsach = :idSach", nativeQuery = true)
    ChiTietDonHang getOrderDetail(@Param("idDonHang") Integer idDonHang,
                                  @Param("idSach") Integer idSach);

    // thêm chi tiết đơn hàng mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO chitietdonhang(iddonhang, idsach, soluong, dongia) " +
            "VALUES (:idDonHang, :idSach, :soLuong, :donGia)",
            nativeQuery = true)
    int insertOrderDetail(@Param("idDonHang") Integer idDonHang,
                          @Param("idSach") Integer idSach,
                          @Param("soLuong") Integer soLuong,
                          @Param("donGia") BigDecimal donGia);

    // cập nhật chi tiết đơn hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE chitietdonhang SET soluong = :soLuong, dongia = :donGia " +
            "WHERE iddonhang = :idDonHang AND idsach = :idSach",
            nativeQuery = true)
    int updateOrderDetail(@Param("idDonHang") Integer idDonHang,
                          @Param("idSach") Integer idSach,
                          @Param("soLuong") Integer soLuong,
                          @Param("donGia") BigDecimal donGia);

    // cập nhật số lượng sách trong đơn hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE chitietdonhang SET soluong = :soLuong " +
            "WHERE iddonhang = :idDonHang AND idsach = :idSach",
            nativeQuery = true)
    int updateQuantity(@Param("idDonHang") Integer idDonHang,
                       @Param("idSach") Integer idSach,
                       @Param("soLuong") Integer soLuong);

    // xóa một chi tiết đơn hàng
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chitietdonhang WHERE iddonhang = :idDonHang AND idsach = :idSach",
            nativeQuery = true)
    int deleteOrderDetail(@Param("idDonHang") Integer idDonHang,
                          @Param("idSach") Integer idSach);

    // xóa toàn bộ chi tiết của một đơn hàng
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chitietdonhang WHERE iddonhang = :idDonHang",
            nativeQuery = true)
    int deleteOrderDetailsByOrderId(@Param("idDonHang") Integer idDonHang);
}