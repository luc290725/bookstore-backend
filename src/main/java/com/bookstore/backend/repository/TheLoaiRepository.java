package com.bookstore.backend.repository;

import com.bookstore.backend.model.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer> {

    // lấy tất cả thể loại
    @Query(value = "SELECT * FROM theloai", nativeQuery = true)
    List<TheLoai> getAllCategories();

    // lấy thể loại theo id
    @Query(value = "SELECT * FROM theloai WHERE id = :id", nativeQuery = true)
    TheLoai getCategoryById(@Param("id") Integer id);

    // tìm kiếm thể loại theo tên
    @Query(value = "SELECT * FROM theloai WHERE tentheloai LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<TheLoai> searchCategoriesByName(@Param("keyword") String keyword);

    // thêm thể loại mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO theloai(tentheloai, mota) VALUES (:tenTheLoai, :moTa)", nativeQuery = true)
    int insertCategory(@Param("tenTheLoai") String tenTheLoai,
                       @Param("moTa") String moTa);

    // cập nhật thể loại
    @Modifying
    @Transactional
    @Query(value = "UPDATE theloai SET tentheloai = :tenTheLoai, mota = :moTa WHERE id = :id", nativeQuery = true)
    int updateCategory(@Param("id") Integer id,
                       @Param("tenTheLoai") String tenTheLoai,
                       @Param("moTa") String moTa);

    // xóa thể loại
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM theloai WHERE id = :id", nativeQuery = true)
    int deleteCategory(@Param("id") Integer id);
}