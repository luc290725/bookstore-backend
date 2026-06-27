package com.bookstore.backend.repository;

import com.bookstore.backend.model.DonHang;
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
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {

    // lấy tất cả đơn hàng
    @Query(value = "SELECT * FROM donhang", nativeQuery = true)
    List<DonHang> getAllOrders();

    // lấy đơn hàng theo id
    @Query(value = "SELECT * FROM donhang WHERE id = :id", nativeQuery = true)
    DonHang getOrderById(@Param("id") Integer id);

    // lấy danh sách đơn hàng theo id khách hàng
    @Query(value = "SELECT * FROM donhang WHERE idkhachhang = :idKhachHang", nativeQuery = true)
    List<DonHang> getOrdersByCustomerId(@Param("idKhachHang") Integer idKhachHang);

    // lấy danh sách đơn hàng theo trạng thái
    @Query(value = "SELECT * FROM donhang WHERE trangthai = :trangThai", nativeQuery = true)
    List<DonHang> getOrdersByStatus(@Param("trangThai") String trangThai);

    // thêm đơn hàng mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO donhang(idkhachhang, ngaydat, tongtien, trangthai) " +
            "VALUES (:idKhachHang, :ngayDat, :tongTien, :trangThai)",
            nativeQuery = true)
    int insertOrder(@Param("idKhachHang") Integer idKhachHang,
                    @Param("ngayDat") LocalDate ngayDat,
                    @Param("tongTien") BigDecimal tongTien,
                    @Param("trangThai") String trangThai);

    // cập nhật đơn hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE donhang SET idkhachhang = :idKhachHang, ngaydat = :ngayDat, " +
            "tongtien = :tongTien, trangthai = :trangThai WHERE id = :id",
            nativeQuery = true)
    int updateOrder(@Param("id") Integer id,
                    @Param("idKhachHang") Integer idKhachHang,
                    @Param("ngayDat") LocalDate ngayDat,
                    @Param("tongTien") BigDecimal tongTien,
                    @Param("trangThai") String trangThai);

    // cập nhật trạng thái đơn hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE donhang SET trangthai = :trangThai WHERE id = :id",
            nativeQuery = true)
    int updateOrderStatus(@Param("id") Integer id,
                          @Param("trangThai") String trangThai);

    // xóa đơn hàng
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM donhang WHERE id = :id", nativeQuery = true)
    int deleteOrder(@Param("id") Integer id);
}