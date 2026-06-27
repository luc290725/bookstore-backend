package com.bookstore.backend.repository;

import com.bookstore.backend.model.NhaXuatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface NhaXuatBanRepository extends JpaRepository<NhaXuatBan, Integer> {

    // lấy tất cả nhà xuất bản
    @Query(value = "SELECT * FROM nhaxuatban", nativeQuery = true)
    List<NhaXuatBan> getAllPublishers();

    // lấy nhà xuất bản theo id
    @Query(value = "SELECT * FROM nhaxuatban WHERE id = :id", nativeQuery = true)
    NhaXuatBan getPublisherById(@Param("id") Integer id);

    // tìm kiếm nhà xuất bản theo tên
    @Query(value = "SELECT * FROM nhaxuatban WHERE tennxb LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<NhaXuatBan> searchPublishersByName(@Param("keyword") String keyword);

    // thêm nhà xuất bản mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO nhaxuatban(tennxb, diachi, sodienthoai) VALUES (:tenNxb, :diaChi, :soDienThoai)", nativeQuery = true)
    int insertPublisher(@Param("tenNxb") String tenNxb,
                        @Param("diaChi") String diaChi,
                        @Param("soDienThoai") String soDienThoai);

    // cập nhật nhà xuất bản
    @Modifying
    @Transactional
    @Query(value = "UPDATE nhaxuatban SET tennxb = :tenNxb, diachi = :diaChi, sodienthoai = :soDienThoai WHERE id = :id", nativeQuery = true)
    int updatePublisher(@Param("id") Integer id,
                        @Param("tenNxb") String tenNxb,
                        @Param("diaChi") String diaChi,
                        @Param("soDienThoai") String soDienThoai);

    // xóa nhà xuất bản
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nhaxuatban WHERE id = :id", nativeQuery = true)
    int deletePublisher(@Param("id") Integer id);
}