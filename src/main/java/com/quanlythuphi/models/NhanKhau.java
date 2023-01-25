package com.quanlythuphi.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanKhau extends Base {
    private String hoTen;
    private String ngaySinh;
    private String danToc;
    private String soDinhDanh;
    private String tonGiao;
    private String queQuan;
    private String gioiTinh;
    private String diaChiThuongTru;
    private String loaiCuTru;
    private String soDienThoai;
    private String noiSinh;
    private String tinhTrang;
    private String quocTich;
    private String ngheNghiep;
    private Integer hoKhauId;

    public NhanKhau() {
    }

    public NhanKhau(String hoTen, String ngaySinh, String soDinhDanh) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.soDinhDanh = soDinhDanh;
    }

    public NhanKhau(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.hoTen = rs.getString("ho_ten");
        this.ngaySinh = rs.getString("ngay_sinh");
        this.danToc = rs.getString("dan_toc");
        this.soDinhDanh = rs.getString("so_dinh_danh");
        this.tonGiao = rs.getString("ton_giao");
        this.queQuan = rs.getString("que_quan");
        this.gioiTinh = rs.getString("gioi_tinh");
        this.diaChiThuongTru = rs.getString("dia_chi_thuong_tru");
        this.loaiCuTru = rs.getString("loai_cu_tru");
        this.soDienThoai = rs.getString("so_dien_thoai");
        this.noiSinh = rs.getString("noi_sinh");
        this.tinhTrang = rs.getString("tinh_trang");
        this.quocTich = rs.getString("quoc_tich");
        this.ngheNghiep = rs.getString("nghe_nghiep");
        this.hoKhauId = rs.getObject("ho_khau_id") == null ? null : rs.getInt("ho_khau_id");
    }

    public NhanKhau(String hoTen, String ngaySinh, String danToc, String soDinhDanh, String tonGiao, String queQuan,
                    String gioiTinh, String diaChiThuongTru, String loaiCuTru, String soDienThoai, String noiSinh,
                    String tinhTrang, String quocTich, String ngheNghiep, int hoKhauID) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.danToc = danToc;
        this.soDinhDanh = soDinhDanh;
        this.tonGiao = tonGiao;
        this.queQuan = queQuan;
        this.gioiTinh = gioiTinh;
        this.diaChiThuongTru = diaChiThuongTru;
        this.loaiCuTru = loaiCuTru;
        this.soDienThoai = soDienThoai;
        this.noiSinh = noiSinh;
        this.tinhTrang = tinhTrang;
        this.quocTich = quocTich;
        this.ngheNghiep = ngheNghiep;
        this.hoKhauId = hoKhauID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getSoDinhDanh() {
        return soDinhDanh;
    }

    public void setSoDinhDanh(String soDinhDanh) {
        this.soDinhDanh = soDinhDanh;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChiThuongTru() {
        return diaChiThuongTru;
    }

    public void setDiaChiThuongTru(String diaChiThuongTru) {
        this.diaChiThuongTru = diaChiThuongTru;
    }

    public String getLoaiCuTru() {
        return loaiCuTru;
    }

    public void setLoaiCuTru(String loaiCuTru) {
        this.loaiCuTru = loaiCuTru;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public Integer getHoKhauId() {
        return hoKhauId;
    }

    public void setHoKhauId(int hoKhauId) {
        this.hoKhauId = hoKhauId;
    }
}
