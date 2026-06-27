package com.bookstore.backend.repository;

import com.bookstore.backend.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {

    // lấy tất cả tài khoản
    @Query(value = "SELECT * FROM taikhoan", nativeQuery = true)
    List<TaiKhoan> getAllAccounts();

    // lấy tài khoản theo id
    @Query(value = "SELECT * FROM taikhoan WHERE id = :id", nativeQuery = true)
    TaiKhoan getAccountById(@Param("id") Integer id);

    // lấy tài khoản theo tên đăng nhập
    @Query(value = "SELECT * FROM taikhoan WHERE tendangnhap = :tenDangNhap", nativeQuery = true)
    TaiKhoan getAccountByUsername(@Param("tenDangNhap") String tenDangNhap);

    // tìm kiếm tài khoản theo tên đăng nhập
    @Query(value = "SELECT * FROM taikhoan WHERE tendangnhap LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<TaiKhoan> searchAccountsByUsername(@Param("keyword") String keyword);

    // đăng nhập
    @Query(value = "SELECT * FROM taikhoan WHERE tendangnhap = :tenDangNhap AND matkhau = :matKhau", nativeQuery = true)
    TaiKhoan login(@Param("tenDangNhap") String tenDangNhap,
                   @Param("matKhau") String matKhau);

    // thêm tài khoản mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO taikhoan(tendangnhap, matkhau, email, vaitro) VALUES (:tenDangNhap, :matKhau, :email, :vaiTro)", nativeQuery = true)
    int insertAccount(@Param("tenDangNhap") String tenDangNhap,
                      @Param("matKhau") String matKhau,
                      @Param("email") String email,
                      @Param("vaiTro") String vaiTro);

    // cập nhật tài khoản
    @Modifying
    @Transactional
    @Query(value = "UPDATE taikhoan SET tendangnhap = :tenDangNhap, matkhau = :matKhau, email = :email, vaitro = :vaiTro WHERE id = :id", nativeQuery = true)
    int updateAccount(@Param("id") Integer id,
                      @Param("tenDangNhap") String tenDangNhap,
                      @Param("matKhau") String matKhau,
                      @Param("email") String email,
                      @Param("vaiTro") String vaiTro);

    // xóa tài khoản
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM taikhoan WHERE id = :id", nativeQuery = true)
    int deleteAccount(@Param("id") Integer id);
}