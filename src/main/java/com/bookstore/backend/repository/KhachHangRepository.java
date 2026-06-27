package com.bookstore.backend.repository;

import com.bookstore.backend.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {

    // lấy tất cả khách hàng
    @Query(value = "SELECT * FROM khachhang", nativeQuery = true)
    List<KhachHang> getAllCustomers();

    // lấy khách hàng theo id
    @Query(value = "SELECT * FROM khachhang WHERE id = :id", nativeQuery = true)
    KhachHang getCustomerById(@Param("id") Integer id);

    // lấy khách hàng theo id tài khoản
    @Query(value = "SELECT * FROM khachhang WHERE idtaikhoan = :idTaiKhoan", nativeQuery = true)
    KhachHang getCustomerByAccountId(@Param("idTaiKhoan") Integer idTaiKhoan);

    // tìm kiếm khách hàng theo họ tên
    @Query(value = "SELECT * FROM khachhang WHERE hoten LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<KhachHang> searchCustomersByName(@Param("keyword") String keyword);

    // thêm khách hàng mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO khachhang(idtaikhoan, hoten, sodienthoai, email, ngaysinh, gioitinh, ngaydangky) " +
            "VALUES (:idTaiKhoan, :hoTen, :soDienThoai, :email, :ngaySinh, :gioiTinh, :ngayDangKy)",
            nativeQuery = true)
    int insertCustomer(@Param("idTaiKhoan") Integer idTaiKhoan,
                       @Param("hoTen") String hoTen,
                       @Param("soDienThoai") String soDienThoai,
                       @Param("email") String email,
                       @Param("ngaySinh") LocalDate ngaySinh,
                       @Param("gioiTinh") String gioiTinh,
                       @Param("ngayDangKy") LocalDate ngayDangKy);

    // cập nhật khách hàng
    @Modifying
    @Transactional
    @Query(value = "UPDATE khachhang SET idtaikhoan = :idTaiKhoan, hoten = :hoTen, sodienthoai = :soDienThoai, " +
            "email = :email, ngaysinh = :ngaySinh, gioitinh = :gioiTinh, ngaydangky = :ngayDangKy WHERE id = :id",
            nativeQuery = true)
    int updateCustomer(@Param("id") Integer id,
                       @Param("idTaiKhoan") Integer idTaiKhoan,
                       @Param("hoTen") String hoTen,
                       @Param("soDienThoai") String soDienThoai,
                       @Param("email") String email,
                       @Param("ngaySinh") LocalDate ngaySinh,
                       @Param("gioiTinh") String gioiTinh,
                       @Param("ngayDangKy") LocalDate ngayDangKy);

    // xóa khách hàng
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM khachhang WHERE id = :id", nativeQuery = true)
    int deleteCustomer(@Param("id") Integer id);
}