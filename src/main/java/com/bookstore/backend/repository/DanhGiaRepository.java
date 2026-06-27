package com.bookstore.backend.repository;

import com.bookstore.backend.model.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {

    // lấy tất cả đánh giá
    @Query(value = "SELECT * FROM danhgia", nativeQuery = true)
    List<DanhGia> getAllReviews();

    // lấy đánh giá theo id
    @Query(value = "SELECT * FROM danhgia WHERE id = :id", nativeQuery = true)
    DanhGia getReviewById(@Param("id") Integer id);

    // lấy danh sách đánh giá theo id sách
    @Query(value = "SELECT * FROM danhgia WHERE idsach = :idSach", nativeQuery = true)
    List<DanhGia> getReviewsByBookId(@Param("idSach") Integer idSach);

    // lấy danh sách đánh giá theo id khách hàng
    @Query(value = "SELECT * FROM danhgia WHERE idkhachhang = :idKhachHang", nativeQuery = true)
    List<DanhGia> getReviewsByCustomerId(@Param("idKhachHang") Integer idKhachHang);

    // lấy đánh giá của một khách hàng cho một sách
    @Query(value = "SELECT * FROM danhgia WHERE idkhachhang = :idKhachHang AND idsach = :idSach", nativeQuery = true)
    DanhGia getReviewByCustomerAndBook(@Param("idKhachHang") Integer idKhachHang,
                                       @Param("idSach") Integer idSach);

    // lấy danh sách đánh giá theo số sao
    @Query(value = "SELECT * FROM danhgia WHERE sosao = :soSao", nativeQuery = true)
    List<DanhGia> getReviewsByRating(@Param("soSao") Integer soSao);

    // thêm đánh giá mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO danhgia(idkhachhang, idsach, sosao, binhluan, ngaydanhgia) " +
            "VALUES (:idKhachHang, :idSach, :soSao, :binhLuan, :ngayDanhGia)",
            nativeQuery = true)
    int insertReview(@Param("idKhachHang") Integer idKhachHang,
                     @Param("idSach") Integer idSach,
                     @Param("soSao") Integer soSao,
                     @Param("binhLuan") String binhLuan,
                     @Param("ngayDanhGia") LocalDate ngayDanhGia);

    // cập nhật đánh giá
    @Modifying
    @Transactional
    @Query(value = "UPDATE danhgia SET idkhachhang = :idKhachHang, idsach = :idSach, " +
            "sosao = :soSao, binhluan = :binhLuan, ngaydanhgia = :ngayDanhGia WHERE id = :id",
            nativeQuery = true)
    int updateReview(@Param("id") Integer id,
                     @Param("idKhachHang") Integer idKhachHang,
                     @Param("idSach") Integer idSach,
                     @Param("soSao") Integer soSao,
                     @Param("binhLuan") String binhLuan,
                     @Param("ngayDanhGia") LocalDate ngayDanhGia);

    // xóa đánh giá
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM danhgia WHERE id = :id", nativeQuery = true)
    int deleteReview(@Param("id") Integer id);
}