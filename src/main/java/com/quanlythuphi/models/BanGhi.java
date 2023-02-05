package com.quanlythuphi.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BanGhi extends Base{
    private Integer khoanPhiId;
    private Integer hoKhauId;
    private Date ngayNop;
    private String ghiChu;
    private Boolean trangThai;
    private Integer soTien;

    public BanGhi() {
    }

    public BanGhi(Integer khoanPhiId, Integer hoKhauId, Date ngayNop, String ghiChu, Boolean trangThai, Integer soTien) {
        this.khoanPhiId = khoanPhiId;
        this.hoKhauId = hoKhauId;
        this.ngayNop = ngayNop;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.soTien = soTien;
    }

    public BanGhi(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.khoanPhiId = rs.getInt("khoan_phi_id");
        this.hoKhauId = rs.getInt("ho_khau_id");
        this.ngayNop = rs.getDate("ngay_nop");
        this.soTien = rs.getInt("so_tien");
        this.trangThai = rs.getBoolean("trang_thai");
        this.ghiChu = rs.getString("ghi_chu");
    }

    public Integer getKhoanPhiId() {
        return khoanPhiId;
    }

    public void setKhoanPhiId(Integer khoanPhiId) {
        this.khoanPhiId = khoanPhiId;
    }

    public Integer getHoKhauId() {
        return hoKhauId;
    }

    public void setHoKhauId(Integer hoKhauId) {
        this.hoKhauId = hoKhauId;
    }

    public Date getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(Date ngayNop) {
        this.ngayNop = ngayNop;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoTien() {
        return soTien;
    }

    public void setSoTien(Integer soTien) {
        this.soTien = soTien;
    }
}
