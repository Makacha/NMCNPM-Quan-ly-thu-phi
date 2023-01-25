package com.quanlythuphi.views;

import com.quanlythuphi.Main;
import com.quanlythuphi.controllers.UserController;
import com.quanlythuphi.models.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {
    @FXML
    public TextField tfUsername;
    @FXML
    public TextField tfPassword;
    @FXML
    public Button login;
    @FXML
    public Label lbInvalidUser;

    @FXML
    public void login(Event event) throws IOException {
        lbInvalidUser.setVisible(false);
        boolean actionLogin = true;
        if (event instanceof KeyEvent keyEvent) {
            if (keyEvent.getCode() != KeyCode.ENTER) {
                actionLogin = false;
            }
        }
        if (actionLogin) {
            User user = UserController.getUserByUsername(tfUsername.getText());
            if (user == null || !user.getPassword().equals(tfPassword.getText())) {
                lbInvalidUser.setVisible(true);
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("base-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1300, 700);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
