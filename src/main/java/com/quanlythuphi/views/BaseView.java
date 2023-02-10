package com.quanlythuphi.views;

import com.quanlythuphi.Main;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseView {

    public void changePage(Event event, String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
            Scene scene = new Scene(fxmlLoader.load(), 1300, 700);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ignore) {

        }
    }

    public void logout(MouseEvent mouseEvent) {
        this.changePage(mouseEvent, "login-view.fxml");
    }

    public void viewKhoanPhi(MouseEvent mouseEvent) {
        this.changePage(mouseEvent, "khoan-phi-view.fxml");
    }

    public void viewHoKhau(MouseEvent mouseEvent) {
        this.changePage(mouseEvent, "ho-khau-view.fxml");
    }
    public void returnHomePage(MouseEvent mouseEvent) {
        this.changePage(mouseEvent, "base-view.fxml");
    }

    public void viewNhanKhau(MouseEvent mouseEvent) {
        this.changePage(mouseEvent, "nhan-khau-view.fxml");
    }

    public void viewBanGhi(MouseEvent mouseEvent) {
        this.changePage(mouseEvent, "ban-ghi-view.fxml");
    }


    public void viewThongKe(MouseEvent mouseEvent) {
        this.changePage(mouseEvent, "thong-ke-view.fxml");
    }
}
