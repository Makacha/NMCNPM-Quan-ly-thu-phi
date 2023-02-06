package com.quanlythuphi.models;

public class ThongTinThongKe {
    String maHoKhau;
    String tenChuHo;
    String soDTChuHo;
    String diaChi;
    Long soTien;

    public ThongTinThongKe(String maHoKhau, String tenChuHo, String soDTChuHo, String diaChi, Integer soTien) {
        this.maHoKhau = maHoKhau;
        this.tenChuHo = tenChuHo != null ? tenChuHo : "Không có thông tin chủ hộ";
        this.soDTChuHo = soDTChuHo != null ? soDTChuHo : "";
        this.diaChi = diaChi;
        this.soTien = Long.valueOf(soTien);
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public String getTenChuHo() {
        return tenChuHo;
    }

    public String getSoDTChuHo() {
        return soDTChuHo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public Long getSoTien() {
        return soTien;
    }
}
