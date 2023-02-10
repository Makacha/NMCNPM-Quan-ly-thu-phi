package com.quanlythuphi.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KhoanPhi extends Base {
    private String tenKhoanPhi;
    private boolean theLoai;
    private Integer soTien;
    private Integer tuTuoi;
    private Integer denTuoi;
    private Integer cheDo;

    public KhoanPhi() {
    }

    public KhoanPhi(String tenKhoanPhi, boolean theLoai) {
        this.tenKhoanPhi = tenKhoanPhi;
        this.theLoai = theLoai;
    }

    public KhoanPhi(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.tenKhoanPhi = rs.getString("ten_khoan_phi");
        this.theLoai = rs.getBoolean("the_loai");
        this.soTien =  rs.getObject("so_tien") == null ? null : rs.getInt("so_tien");
        this.tuTuoi =  rs.getObject("tu_tuoi") == null ? null : rs.getInt("tu_tuoi");
        this.denTuoi =  rs.getObject("den_tuoi") == null ? null : rs.getInt("den_tuoi");
        this.cheDo =  rs.getObject("che_do") == null ? null : rs.getInt("che_do");
    }

    public Integer getSoTien() {
        return soTien;
    }

    public void setSoTien(Integer soTien) {
        this.soTien = soTien;
    }

    public String getTenKhoanPhi() {
        return tenKhoanPhi;
    }

    public void setTenKhoanPhi(String tenKhoanPhi) {
        this.tenKhoanPhi = tenKhoanPhi;
    }

    public boolean getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(boolean theLoai) {
        this.theLoai = theLoai;
    }

    public Integer getTuTuoi() {
        return tuTuoi;
    }

    public void setTuTuoi(int tuTuoi) {
        this.tuTuoi = tuTuoi;
    }

    public Integer getDenTuoi() {
        return denTuoi;
    }

    public void setDenTuoi(int denTuoi) {
        this.denTuoi = denTuoi;
    }

    public Integer getCheDo() {
        return cheDo;
    }

    public void setCheDo(int cheDo) {
        this.cheDo = cheDo;
    }
}
