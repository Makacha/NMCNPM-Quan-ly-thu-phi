package com.quanlythuphi.views;

import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.controllers.KhoanPhiController;
import com.quanlythuphi.models.KhoanPhi;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KhoanPhiView extends BaseView implements Initializable {

    public Text khoanPhiExistAlert;
    public Text moneyAlert;
    private KhoanPhi current;

    public ChoiceBox<String> loaiKhoanPhi;
    public TextField tenKhoanPhi;
    public TextField tuTuoi;
    public TextField denTuoi;
    public TextField soTien;
    public ChoiceBox<String> cheDo;
    public AnchorPane createKhoanPhiPage;
    public AnchorPane listKhoanPhiPage;
    public AnchorPane detailKhoanPhiPage;
    public ChoiceBox<String> loaiKhoanPhiDetail;
    public TextField tenKhoanPhiDetail;
    public TextField tuTuoiDetail;
    public TextField denTuoiDetail;
    public TextField soTienDetail;
    public ChoiceBox<String> cheDoDetail;
    public ChoiceBox<String> loaiKhoanPhiSearch;
    public TextField tenKhoanPhiSearch;
    public TableView<KhoanPhi> danhSachKhoanPhi;
    public TableColumn<KhoanPhi, Integer> sttCol;
    public TableColumn<KhoanPhi, String> tenKhoanPhiCol;
    public TableColumn<KhoanPhi, String> loaiKhoanPhiCol;
    public Text createAlert;
    public Text updateSuccessAlert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaiKhoanPhi.getItems().addAll(Constants.PHI_BAT_BUOC, Constants.PHI_TU_NGUYEN);
        loaiKhoanPhiSearch.getItems().addAll(Constants.ALL, Constants.PHI_BAT_BUOC, Constants.PHI_TU_NGUYEN);
        loaiKhoanPhiSearch.setValue(Constants.ALL);
        cheDo.getItems().addAll(Constants.HO_NGHEO, Constants.HO_CAN_NGHEO, Constants.HO_BINH_THUONG);
        loaiKhoanPhiDetail.getItems().addAll(Constants.PHI_BAT_BUOC, Constants.PHI_TU_NGUYEN);
        cheDoDetail.getItems().addAll(Constants.HO_NGHEO, Constants.HO_CAN_NGHEO, Constants.HO_BINH_THUONG);
        listKhoanPhiPage.setVisible(true);
        createKhoanPhiPage.setVisible(false);
        detailKhoanPhiPage.setVisible(false);
        updateSuccessAlert.setVisible(false);
        khoanPhiExistAlert.setVisible(false);
        createAlert.setVisible(false);
        moneyAlert.setVisible(false);
        sttCol.setCellValueFactory(
            khoanPhiIntegerCellDataFeatures -> new SimpleObjectProperty<>(
                khoanPhiIntegerCellDataFeatures.getTableView().getItems().indexOf(
                    khoanPhiIntegerCellDataFeatures.getValue())));
        tenKhoanPhiCol.setCellValueFactory(new PropertyValueFactory<>("tenKhoanPhi"));
        loaiKhoanPhiCol.setCellValueFactory(
            khoanPhiStringCellDataFeatures -> new SimpleObjectProperty<>(
                khoanPhiStringCellDataFeatures.getValue().getTheLoai()
                    ? Constants.PHI_BAT_BUOC : Constants.PHI_TU_NGUYEN));
        danhSachKhoanPhi.setRowFactory( tv -> {
            TableRow<KhoanPhi> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    openDetailKhoanPhiPage(event, row.getItem());
                }
            });
            return row ;
        });
        ObservableList<KhoanPhi> khoanPhiList = null;
        try {
            khoanPhiList = FXCollections.observableArrayList(
                    KhoanPhiController.getListKhoanPhiByFilter(null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        danhSachKhoanPhi.setItems(khoanPhiList);
    }

    public void createKhoanPhi(ActionEvent actionEvent) throws SQLException {
        createAlert.setVisible(false);
        khoanPhiExistAlert.setVisible(false);
        if (tenKhoanPhi.getText() == null || loaiKhoanPhi.getValue() == null) {
            createAlert.setVisible(true);
            return;
        }
        else
            createAlert.setVisible(false);
        if (KhoanPhiController.getKhoanPhiByTen(tenKhoanPhi.getText()) != null) {
            khoanPhiExistAlert.setVisible(true);
            return;
        }
        else
            khoanPhiExistAlert.setVisible(false);
        System.out.println(soTien.getText());
        if (loaiKhoanPhi.getValue() == Constants.PHI_BAT_BUOC) {
            if (soTien.getText().equals("")) {
                moneyAlert.setVisible(true);
                return;
            }
            else
                moneyAlert.setVisible(false);
        }
        else
            moneyAlert.setVisible(false);
        KhoanPhi khoanPhi = KhoanPhiController.newKhoanPhi(tenKhoanPhi.getText(), loaiKhoanPhi.getValue(),
            tuTuoi.getText(), denTuoi.getText(), cheDo.getValue(), soTien.getText());
        if (KhoanPhiController.createKhoanPhi(khoanPhi)) {
            khoanPhi = KhoanPhiController.getKhoanPhiByTen(khoanPhi.getTenKhoanPhi());
            System.out.println("Thành công");
            openDetailKhoanPhiPage(actionEvent, khoanPhi);
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }
    }

    public void openCreateKhoanPhiPage(ActionEvent actionEvent) {
        danhSachKhoanPhi.getItems().clear();
        listKhoanPhiPage.setVisible(false);
        createKhoanPhiPage.setVisible(true);
        detailKhoanPhiPage.setVisible(false);
        tenKhoanPhi.setText(null);
        loaiKhoanPhi.setValue(null);
        tuTuoi.setText(null);
        denTuoi.setText(null);
        cheDo.setValue(null);
        khoanPhiExistAlert.setVisible(false);
    }

    public void openListKhoanPhiPage(ActionEvent actionEvent) throws SQLException {
        listKhoanPhiPage.setVisible(true);
        createKhoanPhiPage.setVisible(false);
        detailKhoanPhiPage.setVisible(false);
        searchKhoanPhi(actionEvent);
    }

    public void openDetailKhoanPhiPage(Event event, KhoanPhi khoanPhi) {
        updateSuccessAlert.setVisible(false);
        danhSachKhoanPhi.getItems().clear();
        listKhoanPhiPage.setVisible(false);
        createKhoanPhiPage.setVisible(false);
        detailKhoanPhiPage.setVisible(true);
        moneyAlert.setVisible(false);
        current = khoanPhi;
        tenKhoanPhiDetail.setText(current.getTenKhoanPhi());
        loaiKhoanPhiDetail.setValue(current.getTheLoai() ? Constants.PHI_BAT_BUOC : Constants.PHI_TU_NGUYEN);
        tuTuoiDetail.setText(current.getTuTuoi() == null ? null : String.valueOf(current.getTuTuoi()));
        denTuoiDetail.setText(current.getDenTuoi() == null ? null : String.valueOf(current.getDenTuoi()));
        cheDoDetail.setValue(Constants.mapCheDo(current.getCheDo()));
        soTienDetail.setText(current.getSoTien() == null ? null : String.valueOf(current.getSoTien()));
    }

    public void updateKhoanPhi(ActionEvent actionEvent) {
        if (tenKhoanPhiDetail.getText() == null || loaiKhoanPhiDetail.getValue() == null) {
                System.out.println("Trường thông tin bắt buộc không được bỏ trống");
            return;
        }
        current = KhoanPhiController.newKhoanPhi(current.getId(), tenKhoanPhiDetail.getText(),
            loaiKhoanPhiDetail.getValue(), tuTuoiDetail.getText(), denTuoiDetail.getText(), cheDoDetail.getValue(),
            soTienDetail.getText());
        if (KhoanPhiController.updateKhoanPhi(current)) {
            updateSuccessAlert.setVisible(true);
            System.out.println("Thành công");
        } else {
            updateSuccessAlert.setVisible(false);
            System.out.println("Hệ thống đang có lỗi");
        }
    }

    public void deleteKhoanPhi(ActionEvent actionEvent) throws SQLException {
        if (KhoanPhiController.deleteKhoanPhi(current)) {
            System.out.println("Thành công");
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }
        current = null;
        openListKhoanPhiPage(actionEvent);
    }

    public void searchKhoanPhi(ActionEvent actionEvent) throws SQLException {
        Boolean temp = null;
        if (loaiKhoanPhiSearch.getValue() != null && !loaiKhoanPhiSearch.getValue().equals(Constants.ALL))
            temp = loaiKhoanPhiSearch.getValue().equals(Constants.PHI_BAT_BUOC);
        ObservableList<KhoanPhi> khoanPhiList = FXCollections.observableArrayList(
            KhoanPhiController.getListKhoanPhiByFilter(tenKhoanPhiSearch.getText(), temp));
        danhSachKhoanPhi.setItems(khoanPhiList);
    }
}
