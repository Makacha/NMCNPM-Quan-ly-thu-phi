package com.quanlythuphi.controllers;

import com.quanlythuphi.connection.DBConnection;
import com.quanlythuphi.models.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserController {
    public static List<User> getAllUser() {
        ResultSet rs = DBConnection.executeQuery("select * from user");
        if (rs == null)
            return null;
        List<User> userList = new ArrayList<>();
        try {
            while (rs.next()) {
                userList.add(
                    new User(rs)
                );
            }
        } catch (Exception ignored) {

        }
        return userList;
    }

    public static User getUserByUsername(String username) {
        ResultSet rs = DBConnection.executeQuery(String.format("select * from user where username = '%s'", username));
        if (rs == null)
            return null;
        try {
            rs.next();
            return new User(rs);
        } catch (Exception ignored) {

        }
        return null;
    }
}
