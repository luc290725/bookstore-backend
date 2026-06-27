package com.bookstore.backend.repository;

import com.bookstore.backend.model.KhuyenMai;
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
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Integer> {

    // lấy tất cả khuyến mãi
    @Query(value = "SELECT * FROM khuyenmai", nativeQuery = true)
    List<KhuyenMai> getAllDiscounts();

    // lấy khuyến mãi theo id
    @Query(value = "SELECT * FROM khuyenmai WHERE id = :id", nativeQuery = true)
    KhuyenMai getDiscountById(@Param("id") Integer id);

    // lấy khuyến mãi theo mã
    @Query(value = "SELECT * FROM khuyenmai WHERE makhuyenmai = :maKhuyenMai", nativeQuery = true)
    KhuyenMai getDiscountByCode(@Param("maKhuyenMai") String maKhuyenMai);

    // tìm kiếm khuyến mãi theo mã
    @Query(value = "SELECT * FROM khuyenmai WHERE makhuyenmai LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<KhuyenMai> searchDiscountsByCode(@Param("keyword") String keyword);

    // lấy danh sách khuyến mãi còn hiệu lực
    @Query(value = "SELECT * FROM khuyenmai WHERE ngaybatdau <= :ngayHienTai AND ngayketthuc >= :ngayHienTai", nativeQuery = true)
    List<KhuyenMai> getActiveDiscounts(@Param("ngayHienTai") LocalDate ngayHienTai);

    // thêm khuyến mãi mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO khuyenmai(makhuyenmai, phantramgiam, ngaybatdau, ngayketthuc, dieukien) " +
            "VALUES (:maKhuyenMai, :phanTramGiam, :ngayBatDau, :ngayKetThuc, :dieuKien)",
            nativeQuery = true)
    int insertDiscount(@Param("maKhuyenMai") String maKhuyenMai,
                       @Param("phanTramGiam") BigDecimal phanTramGiam,
                       @Param("ngayBatDau") LocalDate ngayBatDau,
                       @Param("ngayKetThuc") LocalDate ngayKetThuc,
                       @Param("dieuKien") String dieuKien);

    // cập nhật khuyến mãi
    @Modifying
    @Transactional
    @Query(value = "UPDATE khuyenmai SET makhuyenmai = :maKhuyenMai, phantramgiam = :phanTramGiam, " +
            "ngaybatdau = :ngayBatDau, ngayketthuc = :ngayKetThuc, dieukien = :dieuKien WHERE id = :id",
            nativeQuery = true)
    int updateDiscount(@Param("id") Integer id,
                       @Param("maKhuyenMai") String maKhuyenMai,
                       @Param("phanTramGiam") BigDecimal phanTramGiam,
                       @Param("ngayBatDau") LocalDate ngayBatDau,
                       @Param("ngayKetThuc") LocalDate ngayKetThuc,
                       @Param("dieuKien") String dieuKien);

    // xóa khuyến mãi
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM khuyenmai WHERE id = :id", nativeQuery = true)
    int deleteDiscount(@Param("id") Integer id);
}