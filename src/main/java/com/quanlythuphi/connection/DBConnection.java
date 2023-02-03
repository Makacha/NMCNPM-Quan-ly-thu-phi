package com.quanlythuphi.connection;

import java.sql.*;

public class DBConnection {

    public static DBConnection connection = new DBConnection();

    private Connection con;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String DBUrl = "jdbc:mysql://localhost:3306/quan_ly_khoan_phi";
            String DBUser = "root";
            String DBPassword = "minanonihong0";
            this.con = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
        } catch (Exception ignored) {
            System.out.println("Connect database failed!");
            System.exit(0);
        }
    }


    public static ResultSet executeQuery(String query) {
        try {
            Statement stm = connection.con.createStatement();
            return stm.executeQuery(query);
        } catch (Exception ignored) {

        }
        return null;
    }

    public static int excuteUpdate(String query) {
        try {
            Statement stm = connection.con.createStatement();
            return stm.executeUpdate(query);
        } catch (Exception ignored) {

        }
        return 0;
    }
}
