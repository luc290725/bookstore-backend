package com.bookstore.backend.repository;

import com.bookstore.backend.model.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, Integer> {

    // lấy tất cả địa chỉ
    @Query(value = "SELECT * FROM diachi", nativeQuery = true)
    List<DiaChi> getAllAddresses();

    // lấy địa chỉ theo id
    @Query(value = "SELECT * FROM diachi WHERE id = :id", nativeQuery = true)
    DiaChi getAddressById(@Param("id") Integer id);

    // lấy danh sách địa chỉ theo id khách hàng
    @Query(value = "SELECT * FROM diachi WHERE idkhachhang = :idKhachHang", nativeQuery = true)
    List<DiaChi> getAddressesByCustomerId(@Param("idKhachHang") Integer idKhachHang);

    // lấy địa chỉ mặc định của khách hàng
    @Query(value = "SELECT * FROM diachi WHERE idkhachhang = :idKhachHang AND macdinh = true", nativeQuery = true)
    DiaChi getDefaultAddressByCustomerId(@Param("idKhachHang") Integer idKhachHang);

    // thêm địa chỉ mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO diachi(idkhachhang, sonha, phuongxa, quanhuyen, tinhtp, macdinh) " +
            "VALUES (:idKhachHang, :soNha, :phuongXa, :quanHuyen, :tinhTp, :macDinh)",
            nativeQuery = true)
    int insertAddress(@Param("idKhachHang") Integer idKhachHang,
                      @Param("soNha") String soNha,
                      @Param("phuongXa") String phuongXa,
                      @Param("quanHuyen") String quanHuyen,
                      @Param("tinhTp") String tinhTp,
                      @Param("macDinh") Boolean macDinh);

    // cập nhật địa chỉ
    @Modifying
    @Transactional
    @Query(value = "UPDATE diachi SET idkhachhang = :idKhachHang, sonha = :soNha, phuongxa = :phuongXa, " +
            "quanhuyen = :quanHuyen, tinhtp = :tinhTp, macdinh = :macDinh WHERE id = :id",
            nativeQuery = true)
    int updateAddress(@Param("id") Integer id,
                      @Param("idKhachHang") Integer idKhachHang,
                      @Param("soNha") String soNha,
                      @Param("phuongXa") String phuongXa,
                      @Param("quanHuyen") String quanHuyen,
                      @Param("tinhTp") String tinhTp,
                      @Param("macDinh") Boolean macDinh);

    // xóa địa chỉ
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM diachi WHERE id = :id", nativeQuery = true)
    int deleteAddress(@Param("id") Integer id);
}