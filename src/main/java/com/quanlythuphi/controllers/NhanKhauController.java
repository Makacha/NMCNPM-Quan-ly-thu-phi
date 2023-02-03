package com.quanlythuphi.controllers;

import com.quanlythuphi.connection.DBConnection;
import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.models.HoKhau;
import com.quanlythuphi.models.NhanKhau;
import javafx.scene.Parent;

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

    public static ArrayList<NhanKhau> getListNhanKhauByFilter(String ho_ten, Integer hoKhauId, String danToc, String gioiTinh, String loaiCuTru, String quocTich) throws SQLException {
        ArrayList<NhanKhau> nhanKhauList = new ArrayList<NhanKhau>();
        String query;
        if (ho_ten != null)
            query = "select * from nhan_khau";
        else
            query = String.format("select * from nhan_khau where where lower(ho_ten) like '%%%s%%'", ho_ten.toLowerCase());
        ResultSet rs = DBConnection.executeQuery(query);
        if (rs != null) {
            while (rs.next()) {
                for (NhanKhau c : nhanKhauList) {
                    boolean validNhanKhau = true;
                    if (hoKhauId != null)
                        if (c.getHoKhauId() != hoKhauId) {
                            validNhanKhau = false;
                        }
                    if (danToc != null) {
                        if (c.getDanToc() != danToc) {
                            validNhanKhau = false;
                        }
                    }
                    if (gioiTinh != null) {
                        if (c.getGioiTinh() != gioiTinh) {
                            validNhanKhau = false;
                        }
                    }
                    if (loaiCuTru != null) {
                        if (c.getLoaiCuTru() != loaiCuTru) {
                            validNhanKhau = false;
                        }
                    }
                    if (quocTich != null) {
                        if (c.getQuocTich() != quocTich) {
                            validNhanKhau = false;
                        }
                    }
                    if (validNhanKhau)
                        nhanKhauList.add(c);
                }
            }
        }
        return nhanKhauList;
    }

    public static boolean createNhanKhau(NhanKhau nhanKhau) {
        String query = String.format("insert into nhan_khau (ho_ten, ngay_sinh, dan_toc, so_dinh_danh, ton_giao, que_quan, " +
                        "gioi_tinh, dia_chi_thuong_tru, loai_cu_tru, so_dien_thoai, noi_sinh, tinh_trang, quoc_tich, nghe_nghiep, ho_khau_id) " +
                        "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)",
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

    public static NhanKhau newNhanKhau(String ho_Ten, String ngay_sinh, String dan_Toc, String so_dinh_danh, String ton_giao,
                                       String que_quan, String gioi_tinh, String dia_chi_thuong_tru, String loai_cu_tru,
                                       String so_dien_thoai, String noi_sinh, String tinh_trang, String quoc_tich, String nghe_nghiep,
                                       String maHoKhau) throws SQLException {
        NhanKhau nhanKhau = new NhanKhau();
        Integer hoKhauId = HoKhauController.getListHoKhauByFilter(maHoKhau, null).get(0).getId();
        nhanKhau.setHoTen(ho_Ten);
        nhanKhau.setNgaySinh(ngay_sinh);
        nhanKhau.setDanToc(dan_Toc);
        nhanKhau.setSoDinhDanh(so_dinh_danh);
        nhanKhau.setTonGiao(ton_giao);
        nhanKhau.setQueQuan(que_quan);
        nhanKhau.setGioiTinh(gioi_tinh);
        nhanKhau.setDiaChiThuongTru(dia_chi_thuong_tru);
        nhanKhau.setLoaiCuTru(loai_cu_tru);
        nhanKhau.setSoDienThoai(so_dien_thoai);
        nhanKhau.setNoiSinh(noi_sinh);
        nhanKhau.setTinhTrang(tinh_trang);
        nhanKhau.setQuocTich(quoc_tich);
        nhanKhau.setNgheNghiep(nghe_nghiep);
        nhanKhau.setHoKhauId(hoKhauId);
        return nhanKhau;
    }

    public static Integer getIdNhanKhauBySoDinhDanh(String soDinhDanh) throws SQLException {
        String query = String.format("Select id from nhan_khau where nhan_khau.so_dinh_danh = '%s'", soDinhDanh);
        ResultSet rs = DBConnection.executeQuery(query);
        if (rs == null) {
            return null;
        }
        else
            return rs.getInt("id");
    }
}
