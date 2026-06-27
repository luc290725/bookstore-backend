package com.bookstore.backend.repository;

import com.bookstore.backend.model.VanChuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VanChuyenRepository extends JpaRepository<VanChuyen, Integer> {

    // lấy tất cả vận chuyển
    @Query(value = "SELECT * FROM vanchuyen", nativeQuery = true)
    List<VanChuyen> getAllShippings();

    // lấy vận chuyển theo id
    @Query(value = "SELECT * FROM vanchuyen WHERE id = :id", nativeQuery = true)
    VanChuyen getShippingById(@Param("id") Integer id);

    // tìm kiếm vận chuyển theo đơn vị
    @Query(value = "SELECT * FROM vanchuyen WHERE donvi LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<VanChuyen> searchShippingsByUnit(@Param("keyword") String keyword);

    // thêm vận chuyển mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO vanchuyen(donvi, phivanchuyen, thoigian) VALUES (:donVi, :phiVanChuyen, :thoiGian)",
            nativeQuery = true)
    int insertShipping(@Param("donVi") String donVi,
                       @Param("phiVanChuyen") BigDecimal phiVanChuyen,
                       @Param("thoiGian") String thoiGian);

    // cập nhật vận chuyển
    @Modifying
    @Transactional
    @Query(value = "UPDATE vanchuyen SET donvi = :donVi, phivanchuyen = :phiVanChuyen, thoigian = :thoiGian WHERE id = :id",
            nativeQuery = true)
    int updateShipping(@Param("id") Integer id,
                       @Param("donVi") String donVi,
                       @Param("phiVanChuyen") BigDecimal phiVanChuyen,
                       @Param("thoiGian") String thoiGian);

    // xóa vận chuyển
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM vanchuyen WHERE id = :id", nativeQuery = true)
    int deleteShipping(@Param("id") Integer id);
}