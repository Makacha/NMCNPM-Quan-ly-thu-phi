package com.quanlythuphi.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Base {
    private String username;
    private String password;
    private Integer permission;

    public User() {
    }

    public User(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
        this.permission = rs.getObject("permission") == null ? null : rs.getInt("permission");
    }

    public User(String username, String password, int permission) {
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public User(int id, String username, String password, int permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
