package com.quanlythuphi.controllers;

import com.quanlythuphi.connection.DBConnection;
import com.quanlythuphi.models.NhanKhau;

import java.sql.ResultSet;

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
}
