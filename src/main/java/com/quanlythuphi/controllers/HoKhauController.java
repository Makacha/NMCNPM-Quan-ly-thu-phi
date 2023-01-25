package com.quanlythuphi.controllers;

import com.quanlythuphi.connection.DBConnection;
import com.quanlythuphi.models.HoKhau;

import java.sql.ResultSet;

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
}
