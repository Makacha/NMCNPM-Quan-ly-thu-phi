package com.quanlythuphi.controllers;

import com.quanlythuphi.connection.DBConnection;
import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.models.HoKhau;
import com.quanlythuphi.models.NhanKhau;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanKhauController {
    public static NhanKhau getNhanKhau(int id) {
        ResultSet rs = DBConnection.executeQuery(String.format("select * from nhan_khau where id = %d", id));
        if (rs == null)
            return null;
        try {
            rs.next();
            return new NhanKhau(rs);
        } catch (Exception ignored) {

        }
        return null;
    }

    public static ArrayList<NhanKhau> getListNhanKhauByHoKhauId(Integer hoKhauId) throws SQLException {
        ArrayList<NhanKhau> nhanKhauList = new ArrayList<>();
        String query = String.format("select * from nhan_khau where ho_khau_id = %d", hoKhauId);
        ResultSet rs = DBConnection.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                nhanKhauList.add(new NhanKhau(rs));
            }
        }
        return nhanKhauList;
    }

    public static ArrayList<NhanKhau> getListNhanKhauByFilter(String hoTen, String soDinhDanh, String soDienThoai,
                                                              String diaChi) throws SQLException {
        ArrayList<NhanKhau> nhanKhauList = new ArrayList<>();
        StringBuilder query = new StringBuilder("select * from nhan_khau ");
        ArrayList<String> filters = new ArrayList<>();
        if (hoTen != null && !hoTen.equals(""))
            filters.add(String.format("lower(ho_ten) like '%%%s%%'", hoTen.toLowerCase()));
        if (soDinhDanh != null && !soDinhDanh.equals(""))
            filters.add(String.format("so_dinh_danh like '%%%s%%'", soDinhDanh));
        if (soDienThoai != null && !soDienThoai.equals(""))
            filters.add(String.format("so_dien_thoai like '%%%s%%'", soDienThoai));
        if (diaChi != null && !diaChi.equals(""))
            filters.add(String.format("dia_chi like '%%%s%%'", diaChi));
        if (filters.size() > 0)
            query.append("where id is not null");
        for (String filter : filters) {
            query.append(" and ").append(filter);
        }
        ResultSet rs = DBConnection.executeQuery(query.toString());
        if (rs != null) {
            while (rs.next()) {
                nhanKhauList.add(new NhanKhau(rs));
            }
        }
        return nhanKhauList;
    }

    public static boolean createNhanKhau(NhanKhau nhanKhau) {
        String query = String.format("insert into nhan_khau (ho_ten, ngay_sinh, dan_toc, so_dinh_danh, ton_giao, que_quan, " +
                "gioi_tinh, dia_chi_thuong_tru, loai_cu_tru, so_dien_thoai, noi_sinh, tinh_trang, quoc_tich, nghe_nghiep, ho_khau_id) " +
                "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)",
            nhanKhau.getHoTen(),
            Constants.mapDateToString(nhanKhau.getNgaySinh()),
            nhanKhau.getDanToc(),
            nhanKhau.getSoDinhDanh(),
            nhanKhau.getTonGiao(),
            nhanKhau.getQueQuan(),
            nhanKhau.getGioiTinh(),
            nhanKhau.getDiaChiThuongTru(),
            nhanKhau.getLoaiCuTru(),
            nhanKhau.getSoDienThoai(),
            nhanKhau.getNoiSinh(),
            nhanKhau.getTinhTrang(),
            nhanKhau.getQuocTich(),
            nhanKhau.getNgheNghiep(),
            nhanKhau.getHoKhauId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static boolean updateNhanKhau(NhanKhau nhanKhau) {
        String query = String.format("update nhan_khau " +
                "set ho_ten = '%s', ngay_sinh = '%s', dan_toc = '%s', so_dinh_danh = '%s', ton_giao = '%s', que_quan = '%s'" +
                "gioi_tinh = '%s', dia_chi_thuong_tru = '%s', loai_cu_tru = '%s', so_dien_thaoi = '%s', noi_sinh = '%s'" +
                "tinh_trang = '%s', quoc_tich = '%s', nghe_nghiep = '%s', ho_khau_id = %d" +
                "where id = %d",
            nhanKhau.getHoTen(),
            nhanKhau.getNgaySinh(),
            nhanKhau.getDanToc(),
            nhanKhau.getSoDinhDanh(),
            nhanKhau.getTonGiao(),
            nhanKhau.getQueQuan(),
            nhanKhau.getGioiTinh(),
            nhanKhau.getDiaChiThuongTru(),
            nhanKhau.getLoaiCuTru(),
            nhanKhau.getSoDienThoai(),
            nhanKhau.getNoiSinh(),
            nhanKhau.getTinhTrang(),
            nhanKhau.getQuocTich(),
            nhanKhau.getNgheNghiep(),
            nhanKhau.getHoKhauId(),
            nhanKhau.getId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static boolean deleteNhanKhau(NhanKhau nhanKhau) {
        String query = String.format("delete from nhan_khau where id = %d", nhanKhau.getId());
        System.out.println(query);
        return DBConnection.excuteUpdate(query) == 1;
    }

    public static NhanKhau newNhanKhau(String hoTen, Date ngaySinh, String danToc, String soDinhDanh, String tonGiao,
                                       String queQuan, String gioiTinh, String diaChiThuongTru, String loaiCuTru,
                                       String soDienThoai, String noiSinh, String tinhTrang, String quocTich,
                                       String ngheNghiep, String maHoKhau) {
        NhanKhau nhanKhau = new NhanKhau();
        HoKhau hoKhau = null;
        nhanKhau.setHoTen(hoTen);
        nhanKhau.setNgaySinh(ngaySinh);
        nhanKhau.setDanToc(danToc);
        nhanKhau.setSoDinhDanh(soDinhDanh);
        nhanKhau.setTonGiao(tonGiao);
        nhanKhau.setQueQuan(queQuan);
        nhanKhau.setGioiTinh(gioiTinh);
        nhanKhau.setDiaChiThuongTru(diaChiThuongTru);
        nhanKhau.setLoaiCuTru(loaiCuTru);
        nhanKhau.setSoDienThoai(soDienThoai);
        nhanKhau.setNoiSinh(noiSinh);
        nhanKhau.setTinhTrang(tinhTrang);
        nhanKhau.setQuocTich(quocTich);
        nhanKhau.setNgheNghiep(ngheNghiep);
        if (maHoKhau != null)
            hoKhau = HoKhauController.getHoKhauByMaHoKhau(maHoKhau);
        if (hoKhau != null)
            nhanKhau.setHoKhauId(hoKhau.getId());
        return nhanKhau;
    }

    public static NhanKhau newNhanKhau(int id, String hoTen, Date ngaySinh, String danToc, String soDinhDanh, String tonGiao,
                                       String queQuan, String gioiTinh, String diaChiThuongTru, String loaiCuTru,
                                       String soDienThoai, String noiSinh, String tinhTrang, String quocTich,
                                       String ngheNghiep, String maHoKhau) {
        NhanKhau nhanKhau = newNhanKhau(hoTen, ngaySinh, danToc, soDinhDanh, tonGiao, queQuan, gioiTinh,
            diaChiThuongTru, loaiCuTru, soDienThoai, noiSinh, tinhTrang, quocTich, ngheNghiep, maHoKhau);
        nhanKhau.setId(id);
        return nhanKhau;
    }

    public static NhanKhau getNhanKhauBySoDinhDanh(String soDinhDanh) throws SQLException {
        String query = String.format("select id from nhan_khau where so_dinh_danh = '%s'", soDinhDanh);
        ResultSet rs = DBConnection.executeQuery(query);
        if (rs == null) {
            return null;
        } else
            return new NhanKhau(rs);
    }
}
