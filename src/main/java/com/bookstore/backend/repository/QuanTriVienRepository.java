package com.bookstore.backend.repository;

import com.bookstore.backend.model.QuanTriVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface QuanTriVienRepository extends JpaRepository<QuanTriVien, Integer> {

    // lấy tất cả quản trị viên
    @Query(value = "SELECT * FROM quantrivien", nativeQuery = true)
    List<QuanTriVien> getAllAdmins();

    // lấy quản trị viên theo id
    @Query(value = "SELECT * FROM quantrivien WHERE id = :id", nativeQuery = true)
    QuanTriVien getAdminById(@Param("id") Integer id);

    // lấy quản trị viên theo id tài khoản
    @Query(value = "SELECT * FROM quantrivien WHERE idtaikhoan = :idTaiKhoan", nativeQuery = true)
    QuanTriVien getAdminByAccountId(@Param("idTaiKhoan") Integer idTaiKhoan);

    // tìm kiếm quản trị viên theo họ tên
    @Query(value = "SELECT * FROM quantrivien WHERE hoten LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<QuanTriVien> searchAdminsByName(@Param("keyword") String keyword);

    // thêm quản trị viên mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO quantrivien(idtaikhoan, hoten, email) VALUES (:idTaiKhoan, :hoTen, :email)",
            nativeQuery = true)
    int insertAdmin(@Param("idTaiKhoan") Integer idTaiKhoan,
                    @Param("hoTen") String hoTen,
                    @Param("email") String email);

    // cập nhật quản trị viên
    @Modifying
    @Transactional
    @Query(value = "UPDATE quantrivien SET idtaikhoan = :idTaiKhoan, hoten = :hoTen, email = :email WHERE id = :id",
            nativeQuery = true)
    int updateAdmin(@Param("id") Integer id,
                    @Param("idTaiKhoan") Integer idTaiKhoan,
                    @Param("hoTen") String hoTen,
                    @Param("email") String email);

    // xóa quản trị viên
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM quantrivien WHERE id = :id", nativeQuery = true)
    int deleteAdmin(@Param("id") Integer id);
}