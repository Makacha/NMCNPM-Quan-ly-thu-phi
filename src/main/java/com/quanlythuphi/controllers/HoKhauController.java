package com.quanlythuphi.controllers;

import com.quanlythuphi.connection.DBConnection;
import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.models.HoKhau;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoKhauController {
    public static HoKhau getHoKhau(int id) {
        ResultSet rs = DBConnection.executeQuery(String.format("select * from ho_khau where id = %d", id));
        if (rs == null)
            return null;
        try {
            rs.next();
            return new HoKhau(rs);
        } catch (Exception ignored) {

        }
        return null;
    }

    public static HoKhau getHoKhauByMaHoKhau(String maHoKhau) {
        ResultSet rs = DBConnection.executeQuery(String.format("select * from ho_khau where ma_ho_khau = '%s'", maHoKhau));
        if (rs == null)
            return null;
        try {
            rs.next();
            return new HoKhau(rs);
        } catch (Exception ignored) {

        }
        return null;
    }

    public static ArrayList<HoKhau> getListHoKhauByFilter(String maHoKhau,
                                                          Integer cheDo) throws SQLException {
        ArrayList<HoKhau> hoKhauList = new ArrayList<>();
        String query = "select * from ho_khau";
        if (maHoKhau != null && cheDo != null) {
            query = String.format("select * from ho_khau " +
                "where lower(ma_ho_khau) like '%%%s%%' and che_do = %d", maHoKhau.toLowerCase(), cheDo);
        } else if (maHoKhau != null) {
            query = String.format("select * from ho_khau " +
                "where lower(ma_ho_khau) like '%%%s%%'", maHoKhau.toLowerCase());
        } else if (cheDo != null) {
            query = String.format("select * from ho_khau " +
                "where che_do = %d", cheDo);
        }
        ResultSet rs = DBConnection.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                hoKhauList.add(new HoKhau(rs));
            }
        }
        return hoKhauList;
    }

    public static ArrayList<HoKhau> getListHoKhauByFilter(Integer cheDo, Integer tuTuoi, Integer denTuoi) throws SQLException {

        ArrayList<HoKhau> hoKhauList = new ArrayList<>();
        StringBuilder query = new StringBuilder("select * from ho_khau ");
        ArrayList<String> filters = new ArrayList<>();
        if (cheDo != null)
            filters.add(String.format("che_do <= %d", cheDo));
        if (tuTuoi != null && denTuoi != null)
            filters.add(String.format("id in (select ho_khau_id from nhan_khau " +
                "where TIMESTAMPDIFF(year, ngay_sinh, now()) between %d and %d)", tuTuoi, denTuoi));
        else if (tuTuoi != null)
            filters.add(String.format("id in (select ho_khau_id from nhan_khau " +
                "where TIMESTAMPDIFF(year, ngay_sinh, now()) >= %d)", tuTuoi));
        else if (denTuoi != null)
            filters.add(String.format("id in (select ho_khau_id from nhan_khau " +
                "where TIMESTAMPDIFF(year, ngay_sinh, now()) <= %d)", denTuoi));
        if (filters.size() > 0)
            query.append("where id is not null");
        for (String filter : filters) {
            query.append(" and ").append(filter);
        }
        System.out.println(query);
        ResultSet rs = DBConnection.executeQuery(query.toString());
        if (rs != null) {
            while (rs.next()) {
                hoKhauList.add(new HoKhau(rs));
            }
        }
        return hoKhauList;
    }

    public static boolean createHoKhau(HoKhau hoKhau) {
        String query = String.format("insert into ho_khau (ma_ho_khau, dia_chi, ngay_lap, chu_ho_id, che_do) " +
                "values ('%s', '%s', '%s', %d, %d)",
            hoKhau.getMaHoKhau(),
            hoKhau.getDiaChi(),
            Constants.mapDateToString(hoKhau.getNgayLap()),
            hoKhau.getChuHoId(),
            hoKhau.getCheDo());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static boolean updateHoKhau(HoKhau hoKhau) {
        String query = String.format("update ho_khau " +
                "set ma_ho_khau = '%s', ngay_lap = '%s', dia_chi = '%s', chu_ho_id = %d, che_do = %d " +
                "where id = %d",
            hoKhau.getMaHoKhau(),
            Constants.mapDateToString(hoKhau.getNgayLap()),
            hoKhau.getDiaChi(),
            hoKhau.getChuHoId(),
            hoKhau.getCheDo(),
            hoKhau.getId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static boolean deleteHoKhau(HoKhau hoKhau) {
        String query = String.format("delete from ho_khau where id = %d", hoKhau.getId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static HoKhau newHoKhau(String maHoKhau, Date ngayLap, String diaChi, Integer chuHoId, String cheDo) {
        HoKhau hoKhau = new HoKhau();

        hoKhau.setMaHoKhau(maHoKhau);
        hoKhau.setNgayLap(ngayLap);
        hoKhau.setDiaChi(diaChi);
        hoKhau.setCheDo(Constants.mapCheDo(cheDo));
        if (chuHoId != null)
            hoKhau.setChuHoId(chuHoId);

        return hoKhau;
    }

}
