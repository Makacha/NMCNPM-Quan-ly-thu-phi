package com.quanlythuphi.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoKhau extends Base{
    private String maHoKhau;
    private String diaChi;
    private Date ngayLap;
    private Integer chuHoId;
    private Integer cheDo;

    public HoKhau() {
    }

    public HoKhau(String maHoKhau, String diaChi, int chuHoId, int cheDo) {
        this.maHoKhau = maHoKhau;
        this.diaChi = diaChi;
        this.chuHoId = chuHoId;
        this.cheDo = cheDo;
    }

    public HoKhau(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.maHoKhau = rs.getString("ma_ho_khau");
        this.diaChi = rs.getString("dia_chi");
        this.ngayLap = rs.getDate("ngay_lap");
        this.chuHoId = rs.getObject("chu_ho_id") == null ? null : rs.getInt("chu_ho_id");
        this.cheDo = rs.getInt("che_do");
    }

    public HoKhau(String maHoKhau, String diaChi, Date ngayLap, int chuHoId, int cheDo) {
        this.maHoKhau = maHoKhau;
        this.diaChi = diaChi;
        this.ngayLap = ngayLap;
        this.chuHoId = chuHoId;
        this.cheDo = cheDo;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Integer getChuHoId() {
        return chuHoId;
    }

    public void setChuHoId(int chuHoId) {
        this.chuHoId = chuHoId;
    }

    public Integer getCheDo() {
        return cheDo;
    }

    public void setCheDo(int cheDo) {
        this.cheDo = cheDo;
    }
}
