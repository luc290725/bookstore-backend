package com.bookstore.backend.repository;

import com.bookstore.backend.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

    // lấy tất cả hóa đơn
    @Query(value = "SELECT * FROM hoadon", nativeQuery = true)
    List<HoaDon> getAllInvoices();

    // lấy hóa đơn theo id
    @Query(value = "SELECT * FROM hoadon WHERE id = :id", nativeQuery = true)
    HoaDon getInvoiceById(@Param("id") Integer id);

    // lấy hóa đơn theo id đơn hàng
    @Query(value = "SELECT * FROM hoadon WHERE iddonhang = :idDonHang", nativeQuery = true)
    HoaDon getInvoiceByOrderId(@Param("idDonHang") Integer idDonHang);

    // lấy danh sách hóa đơn theo ngày lập
    @Query(value = "SELECT * FROM hoadon WHERE ngaylap = :ngayLap", nativeQuery = true)
    List<HoaDon> getInvoicesByDate(@Param("ngayLap") LocalDate ngayLap);

    // thêm hóa đơn mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hoadon(iddonhang, ngaylap, tongtien) " +
            "VALUES (:idDonHang, :ngayLap, :tongTien)",
            nativeQuery = true)
    int insertInvoice(@Param("idDonHang") Integer idDonHang,
                      @Param("ngayLap") LocalDate ngayLap,
                      @Param("tongTien") BigDecimal tongTien);

    // cập nhật hóa đơn
    @Modifying
    @Transactional
    @Query(value = "UPDATE hoadon SET iddonhang = :idDonHang, ngaylap = :ngayLap, tongtien = :tongTien WHERE id = :id",
            nativeQuery = true)
    int updateInvoice(@Param("id") Integer id,
                      @Param("idDonHang") Integer idDonHang,
                      @Param("ngayLap") LocalDate ngayLap,
                      @Param("tongTien") BigDecimal tongTien);

    // xóa hóa đơn
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM hoadon WHERE id = :id", nativeQuery = true)
    int deleteInvoice(@Param("id") Integer id);
}