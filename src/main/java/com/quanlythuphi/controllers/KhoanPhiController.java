package com.quanlythuphi.controllers;

import com.quanlythuphi.connection.DBConnection;
import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.models.KhoanPhi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhoanPhiController {
    public static KhoanPhi getKhoanPhi(int id) {
        ResultSet rs = DBConnection.executeQuery(String.format("select * from khoan_phi where id = %d", id));
        if (rs == null)
            return null;
        try {
            rs.next();
            return new KhoanPhi(rs);
        } catch (Exception ignored) {

        }
        return null;
    }

    public static ArrayList<KhoanPhi> getListKhoanPhiByFilter(String tenKhoanPhi,
                                                              Boolean loaiKhoanPhi) throws SQLException {
        ArrayList<KhoanPhi> khoanPhiList = new ArrayList<KhoanPhi>();
        String query = "select * from khoan_phi";
        if (tenKhoanPhi != null && loaiKhoanPhi != null) {
            query = String.format("select * from khoan_phi " +
                "where lower(ten_khoan_phi) like '%%%s%%' and the_loai = %b", tenKhoanPhi.toLowerCase(), loaiKhoanPhi);
        } else if (tenKhoanPhi != null) {
            query = String.format("select * from khoan_phi " +
                "where lower(ten_khoan_phi) like '%%%s%%'", tenKhoanPhi.toLowerCase());
        } else if (loaiKhoanPhi != null) {
            query = String.format("select * from khoan_phi " +
                "where the_loai = %b", loaiKhoanPhi);
        }
        ResultSet rs = DBConnection.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                khoanPhiList.add(new KhoanPhi(rs));
            }
        }
        return khoanPhiList;
    }

    public static boolean createKhoanPhi(KhoanPhi khoanPhi) {
        String query = String.format("insert into khoan_phi (ten_khoan_phi, the_loai, tu_tuoi, den_tuoi, che_do) " +
                "values ('%s', %b, %d, %d, %d)",
            khoanPhi.getTenKhoanPhi(),
            khoanPhi.getTheLoai(),
            khoanPhi.getTuTuoi(),
            khoanPhi.getDenTuoi(),
            khoanPhi.getCheDo());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static boolean updateKhoanPhi(KhoanPhi khoanPhi) {
        String query = String.format("update khoan_phi " +
                "set ten_khoan_phi = '%s', the_loai = %b, tu_tuoi = %d, den_tuoi = %d, che_do = %d " +
                "where id = %d",
            khoanPhi.getTenKhoanPhi(),
            khoanPhi.getTheLoai(),
            khoanPhi.getTuTuoi(),
            khoanPhi.getDenTuoi(),
            khoanPhi.getCheDo(),
            khoanPhi.getId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static boolean deleteKhoanPhi(KhoanPhi khoanPhi) {
        String query = String.format("delete from khoan_phi where id = %d", khoanPhi.getId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static KhoanPhi newKhoanPhi(String tenKhoanPhi, String loaiKhoanPhi, String tuTuoi, String denTuoi,
                                       String cheDo) {
        KhoanPhi khoanPhi = new KhoanPhi();

        khoanPhi.setTenKhoanPhi(tenKhoanPhi);
        khoanPhi.setTheLoai(loaiKhoanPhi.equals(Constants.PHI_BAT_BUOC));
        if (tuTuoi != null && Constants.validateNumber(tuTuoi))
            khoanPhi.setTuTuoi(Integer.parseInt(tuTuoi));
        if (denTuoi != null && Constants.validateNumber(denTuoi))
            khoanPhi.setDenTuoi(Integer.parseInt(denTuoi));
        if (cheDo != null)
            khoanPhi.setCheDo(Constants.mapCheDo(cheDo));
        return khoanPhi;
    }

    public static KhoanPhi newKhoanPhi(int id, String tenKhoanPhi, String loaiKhoanPhi, String tuTuoi, String denTuoi,
                                       String cheDo) {
        KhoanPhi khoanPhi = new KhoanPhi();
        khoanPhi.setId(id);
        khoanPhi.setTenKhoanPhi(tenKhoanPhi);
        khoanPhi.setTheLoai(loaiKhoanPhi.equals(Constants.PHI_BAT_BUOC));
        if (tuTuoi != null && Constants.validateNumber(tuTuoi))
            khoanPhi.setTuTuoi(Integer.parseInt(tuTuoi));
        if (denTuoi != null && Constants.validateNumber(denTuoi))
            khoanPhi.setDenTuoi(Integer.parseInt(denTuoi));
        if (cheDo != null)
            khoanPhi.setCheDo(Constants.mapCheDo(cheDo));
        return khoanPhi;
    }
}
