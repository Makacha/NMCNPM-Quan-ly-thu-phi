package com.quanlythuphi.controllers;

import com.quanlythuphi.connection.DBConnection;
import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.controllers.HoKhauController;
import com.quanlythuphi.controllers.KhoanPhiController;
import com.quanlythuphi.models.BanGhi;
import com.quanlythuphi.models.HoKhau;
import com.quanlythuphi.views.HoKhauView;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BanGhiController {
    public static BanGhi getBanGhi(int id) {
        ResultSet rs = DBConnection.executeQuery(String.format("select * from ban_ghi_nop_phi where id = %d", id));
        if (rs == null)
            return null;
        try {
            rs.next();
            return new BanGhi(rs);
        } catch (Exception ignored) {

        }
        return null;
    }

    public static ArrayList<BanGhi> getListBanGhiByFilter(String tenKhoanPhi, String trangThai, String maHoKhau) throws SQLException {
        ArrayList<BanGhi> banGhiList = new ArrayList<BanGhi>();
        String query = "select * from ban_ghi_nop_phi where id is not null";
        if (tenKhoanPhi != null && !tenKhoanPhi.equals(""))
            query += String.format(" and khoan_phi_id = %d", KhoanPhiController.getListKhoanPhiByFilter(tenKhoanPhi, null).get(0).getId());
        if (maHoKhau != null && !maHoKhau.equals("")) {
            HoKhau hoKhau = HoKhauController.getHoKhauByMaHoKhau(maHoKhau);
            query += String.format(" and ho_khau_id = %d", hoKhau == null ? null : hoKhau.getId());
        }

        ResultSet rs = DBConnection.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                BanGhi newBanGhi = new BanGhi(rs);
                if (trangThai == null || trangThai.equals("")) banGhiList.add(new BanGhi(rs));
                else
                    if (newBanGhi.getTrangThai() == Constants.mapLoaiPhi(trangThai))
                        banGhiList.add(newBanGhi);
            }
        }
        return banGhiList;
    }

    public static boolean createBanGhi(BanGhi banGhi) {
        String query = String.format("insert into ban_ghi_nop_phi (khoan_phi_id, ho_khau_id, ngay_nop, so_tien, ghi_chu, trang_thai) " +
                        "values (%d, %d, '%s', %d, '%s', %b)",
                banGhi.getKhoanPhiId(),
                banGhi.getHoKhauId(),
                Constants.mapDateToString(banGhi.getNgayNop()),
                banGhi.getSoTien(),
                banGhi.getGhiChu(),
                banGhi.getTrangThai());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static boolean updateBanGhi(BanGhi banGhi) {
        String query = String.format("update ban_ghi_nop_phi " +
                        "set khoan_phi_id = %d, ho_khau_id = %d, ngay_nop = '%s', so_tien = %d, ghi_chu = '%s', trang_thai = %b " +
                        "where id = %d",
                banGhi.getKhoanPhiId(),
                banGhi.getHoKhauId(),
                Constants.mapDateToString(banGhi.getNgayNop()),
                banGhi.getSoTien(),
                banGhi.getGhiChu(),
                banGhi.getTrangThai(),
                banGhi.getId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static boolean deleteBanGhi(BanGhi banGhi) {
        String query = String.format("delete from ban_ghi_nop_phi where id = %d", banGhi.getId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static BanGhi newBanGhi(String tenKhoanPhi, String maHoKhau,  Date ngayNop, String ghiChu, String trangThai, Integer soTien) throws SQLException {
        Integer khoanPhiId = KhoanPhiController.getListKhoanPhiByFilter(tenKhoanPhi, null).get(0).getId();
        HoKhau hoKhau = HoKhauController.getHoKhauByMaHoKhau(maHoKhau);
        BanGhi banGhi = new BanGhi(khoanPhiId, hoKhau.getId(), ngayNop, ghiChu, Constants.mapLoaiPhi(trangThai), soTien);
        return banGhi;
    }

}
