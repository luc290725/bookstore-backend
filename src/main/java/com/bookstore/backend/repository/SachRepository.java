package com.bookstore.backend.repository;

import com.bookstore.backend.model.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SachRepository extends JpaRepository<Sach, Integer> {

    // lấy tất cả sách
    @Query(value = "SELECT * FROM sach", nativeQuery = true)
    List<Sach> getAllBooks();

    // lấy sách theo id
    @Query(value = "SELECT * FROM sach WHERE id = :id", nativeQuery = true)
    Sach getBookById(@Param("id") Integer id);

    // tìm kiếm sách theo tên
    @Query(value = "SELECT * FROM sach WHERE tensach LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    List<Sach> searchBooksByName(@Param("keyword") String keyword);

    // lấy sách theo thể loại
    @Query(value = "SELECT * FROM sach WHERE idtheloai = :idTheLoai", nativeQuery = true)
    List<Sach> getBooksByCategory(@Param("idTheLoai") Integer idTheLoai);

    // lấy sách theo tác giả
    @Query(value = "SELECT * FROM sach WHERE idtacgia = :idTacGia", nativeQuery = true)
    List<Sach> getBooksByAuthor(@Param("idTacGia") Integer idTacGia);

    // lấy sách theo nhà xuất bản
    @Query(value = "SELECT * FROM sach WHERE idnxb = :idNxb", nativeQuery = true)
    List<Sach> getBooksByPublisher(@Param("idNxb") Integer idNxb);

    // thêm sách mới
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sach(tensach, giaban, mota, namxuatban, sotrang, hinhanh, soluongton, idtheloai, idtacgia, idnxb) " +
            "VALUES (:tenSach, :giaBan, :moTa, :namXuatBan, :soTrang, :hinhAnh, :soLuongTon, :idTheLoai, :idTacGia, :idNxb)",
            nativeQuery = true)
    int insertBook(@Param("tenSach") String tenSach,
                   @Param("giaBan") BigDecimal giaBan,
                   @Param("moTa") String moTa,
                   @Param("namXuatBan") Integer namXuatBan,
                   @Param("soTrang") Integer soTrang,
                   @Param("hinhAnh") String hinhAnh,
                   @Param("soLuongTon") Integer soLuongTon,
                   @Param("idTheLoai") Integer idTheLoai,
                   @Param("idTacGia") Integer idTacGia,
                   @Param("idNxb") Integer idNxb);

    // cập nhật sách
    @Modifying
    @Transactional
    @Query(value = "UPDATE sach SET tensach = :tenSach, giaban = :giaBan, mota = :moTa, " +
            "namxuatban = :namXuatBan, sotrang = :soTrang, hinhanh = :hinhAnh, soluongton = :soLuongTon, " +
            "idtheloai = :idTheLoai, idtacgia = :idTacGia, idnxb = :idNxb WHERE id = :id",
            nativeQuery = true)
    int updateBook(@Param("id") Integer id,
                   @Param("tenSach") String tenSach,
                   @Param("giaBan") BigDecimal giaBan,
                   @Param("moTa") String moTa,
                   @Param("namXuatBan") Integer namXuatBan,
                   @Param("soTrang") Integer soTrang,
                   @Param("hinhAnh") String hinhAnh,
                   @Param("soLuongTon") Integer soLuongTon,
                   @Param("idTheLoai") Integer idTheLoai,
                   @Param("idTacGia") Integer idTacGia,
                   @Param("idNxb") Integer idNxb);

    // xóa sách
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sach WHERE id = :id", nativeQuery = true)
    int deleteBook(@Param("id") Integer id);
}