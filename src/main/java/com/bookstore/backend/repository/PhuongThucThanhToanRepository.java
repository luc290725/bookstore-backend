package com.bookstore.backend.repository;

import com.bookstore.backend.model.PhuongThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface PhuongThucThanhToanRepository extends JpaRepository<PhuongThucThanhToan, Integer> {

    // lấy tất cả phương thức thanh toán
    @Query(value = "SELECT * FROM phuongthucthanhtoan", nativeQuery = true)
    List<PhuongThucThanhToan> getAllPaymentMethods();

    // lấy phương thức thanh toán theo id
    @Query(value = "SELECT * FROM phuongthucthanhtoan WHERE id = :id", nativeQuery = true)
    PhuongThucThanhToan getPaymentMethodById(@Param("id") Integer id);

    // tìm kiếm phương thức thanh toán theo tên
    @Query(value = "SELECT * FROM phuongthucthanhtoan WHERE tenpt LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<PhuongThucThanhToan> searchPaymentMethodsByName(@Param("keyword") String keyword);

    // thêm phương thức thanh toán mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO phuongthucthanhtoan(tenpt, mota) VALUES (:tenPt, :moTa)", nativeQuery = true)
    int insertPaymentMethod(@Param("tenPt") String tenPt,
                            @Param("moTa") String moTa);

    // cập nhật phương thức thanh toán
    @Modifying
    @Transactional
    @Query(value = "UPDATE phuongthucthanhtoan SET tenpt = :tenPt, mota = :moTa WHERE id = :id", nativeQuery = true)
    int updatePaymentMethod(@Param("id") Integer id,
                            @Param("tenPt") String tenPt,
                            @Param("moTa") String moTa);

    // xóa phương thức thanh toán
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM phuongthucthanhtoan WHERE id = :id", nativeQuery = true)
    int deletePaymentMethod(@Param("id") Integer id);
}