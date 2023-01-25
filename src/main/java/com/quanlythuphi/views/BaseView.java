package com.quanlythuphi.views;

import com.quanlythuphi.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseView {

    public void changePage(Event event, String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 700);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        this.changePage(mouseEvent, "login-view.fxml");
    }

    public void viewKhoanPhi(MouseEvent mouseEvent) throws IOException {
        this.changePage(mouseEvent, "khoan-phi-view.fxml");
    }

    public void returnHomePage(MouseEvent mouseEvent) throws IOException {
        this.changePage(mouseEvent, "base-view.fxml");
    }
}
