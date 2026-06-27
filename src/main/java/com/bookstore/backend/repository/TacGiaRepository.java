package com.bookstore.backend.repository;

import com.bookstore.backend.model.TacGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface TacGiaRepository extends JpaRepository<TacGia, Integer> {

    // lấy tất cả tác giả
    @Query(value = "SELECT * FROM tacgia", nativeQuery = true)
    List<TacGia> getAllAuthors();

    // lấy tác giả theo id
    @Query(value = "SELECT * FROM tacgia WHERE id = :id", nativeQuery = true)
    TacGia getAuthorById(@Param("id") Integer id);

    // tìm kiếm tác giả theo tên
    @Query(value = "SELECT * FROM tacgia WHERE tentacgia LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<TacGia> searchAuthorsByName(@Param("keyword") String keyword);

    // thêm tác giả mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tacgia(tentacgia, tieusu) VALUES (:tenTacGia, :tieuSu)", nativeQuery = true)
    int insertAuthor(@Param("tenTacGia") String tenTacGia,
                     @Param("tieuSu") String tieuSu);

    // cập nhật tác giả
    @Modifying
    @Transactional
    @Query(value = "UPDATE tacgia SET tentacgia = :tenTacGia, tieusu = :tieuSu WHERE id = :id", nativeQuery = true)
    int updateAuthor(@Param("id") Integer id,
                     @Param("tenTacGia") String tenTacGia,
                     @Param("tieuSu") String tieuSu);

    // xóa tác giả
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tacgia WHERE id = :id", nativeQuery = true)
    int deleteAuthor(@Param("id") Integer id);
}