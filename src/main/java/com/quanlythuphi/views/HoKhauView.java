package com.quanlythuphi.views;

import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.controllers.HoKhauController;
import com.quanlythuphi.models.HoKhau;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HoKhauView extends BaseView implements Initializable {

    private HoKhau currentHoKhau;
    @FXML
    private ChoiceBox<String> cheDo;
    @FXML
    private ChoiceBox<String> cheDoDetail;

    @FXML
    private TextField chuHoDetail;

    @FXML
    private TableView<HoKhau> danhSachHoKhau;

    @FXML
    private TableColumn<HoKhau, String> cheDoCol;

    @FXML
    private TableColumn<HoKhau, String> maHoKhauCol;

    @FXML
    private TableColumn<HoKhau, Integer> sttCol;

    @FXML
    private TextField diaChi;

    @FXML
    private TextField diaChiDetail;

    @FXML
    private AnchorPane listHoKhauPage;

    @FXML
    private AnchorPane createHoKhauPage;

    @FXML
    private AnchorPane detailHoKhauPage;

    @FXML
    private TextField maHoKhau;

    @FXML
    private TextField maHoKhauDetail;

    @FXML
    private TextField maHoKhauSearch;

    @FXML
    private DatePicker ngayLap;

    @FXML
    private DatePicker ngayLapDetail;

    @FXML
    private ChoiceBox<String> cheDoSearch;

    @FXML
    private TextField chuHo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize choicesbox
        cheDoSearch.getItems().addAll(Constants.ALL, Constants.HO_NGHEO, Constants.HO_CAN_NGHEO, Constants.HO_BINH_THUONG);
        cheDo.getItems().addAll(Constants.HO_NGHEO, Constants.HO_CAN_NGHEO, Constants.HO_BINH_THUONG);
        cheDoDetail.getItems().addAll(Constants.HO_NGHEO, Constants.HO_CAN_NGHEO, Constants.HO_BINH_THUONG);
        //____________________________________
        //chuHo.getItems().addAll()
        //____________________________________

        // setVisible to anchorpane
        listHoKhauPage.setVisible(true);
        createHoKhauPage.setVisible(false);
        detailHoKhauPage.setVisible(false);

        //initialize TableColumns
        sttCol.setCellValueFactory(
                hoKhauIntegerCellDataFeatures -> new SimpleObjectProperty<>(
                        hoKhauIntegerCellDataFeatures.getTableView().getItems().indexOf(
                                hoKhauIntegerCellDataFeatures.getValue())));
        maHoKhauCol.setCellValueFactory(new PropertyValueFactory<>("maHoKhau"));
        cheDoCol.setCellValueFactory(
                hoKhauStringCellDataFeatures -> new SimpleObjectProperty<>(
                        Constants.mapCheDo(hoKhauStringCellDataFeatures.getValue().getCheDo())));

        danhSachHoKhau.setRowFactory( tv -> {
            TableRow<HoKhau> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    openDetailHoKhauPage(event, row.getItem());
                }
            });
            return row ;
        });
        ObservableList<HoKhau> hoKhauList = null;
        try {
            hoKhauList = FXCollections.observableArrayList(
                    HoKhauController.getListHoKhauByFilter(null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        danhSachHoKhau.setItems(hoKhauList);

    }

    @FXML
    void createHoKhau(ActionEvent event) {
        if (maHoKhau.getText() == null || cheDo.getValue() == null || Constants.mapDateToString(Date.valueOf(ngayLap.getValue())) == null || diaChi.getText() == null || chuHo.getText() == null) {
            System.out.println("Trường thông tin bắt buộc không được bỏ trống");
            return;
        }

        // get Value to chuHo _____________Update________________

        HoKhau hoKhau = HoKhauController.newHoKhau(maHoKhau.getText(), Date.valueOf(ngayLap.getValue()), diaChi.getText(), "NULL", cheDo.getValue());
        if (HoKhauController.createHoKhau(hoKhau)) {
            System.out.println("Thành công");
            openDetailHoKhauPage(event, hoKhau);
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }
    }

    void openDetailHoKhauPage(Event event, HoKhau hoKhau) {
        listHoKhauPage.setVisible(false);
        createHoKhauPage.setVisible(false);
        detailHoKhauPage.setVisible(true);
        currentHoKhau = hoKhau;
        maHoKhauDetail.setText(hoKhau.getMaHoKhau());
        diaChiDetail.setText(hoKhau.getDiaChi());
        ngayLapDetail.setValue(hoKhau.getNgayLap().toLocalDate());
        cheDoDetail.setValue(Constants.mapCheDo(hoKhau.getCheDo()));
        if (hoKhau.getChuHoId() == null) {
            chuHoDetail.setText("Chưa có thông tin chủ hộ");
        }

        // __________________________________Update__________________________________
        /*else {
            chuHoDetail.setText();
        }*/
    }
    @FXML
    void openCreateHoKhau(ActionEvent event) {
        listHoKhauPage.setVisible(false);
        createHoKhauPage.setVisible(true);
        detailHoKhauPage.setVisible(false);
        maHoKhau.setText("");
        diaChi.setText("");
        chuHo.setText("");
        ngayLap.setValue(null);
        cheDo.setValue(null);
    }

    @FXML
    void openListHoKhauPage(ActionEvent event) throws SQLException {
        listHoKhauPage.setVisible(true);
        createHoKhauPage.setVisible(false);
        detailHoKhauPage.setVisible(false);
        searchHoKhau(event);
    }

    @FXML
    void openListThanhVienInHoKhauPage(ActionEvent event) {

    }

    @FXML
    void searchHoKhau(ActionEvent event) throws SQLException {
        Integer temp = null;
        if (cheDoSearch.getValue() != null && !cheDoSearch.getValue().equals(Constants.ALL))
            temp = Constants.mapCheDo(cheDoSearch.getValue());
        ObservableList<HoKhau> hoKhauList = FXCollections.observableArrayList(
                HoKhauController.getListHoKhauByFilter(maHoKhauSearch.getText(), temp));
        danhSachHoKhau.setItems(hoKhauList);
    }


    @FXML
    void updateHoKhau(ActionEvent event) {
        if (maHoKhauDetail.getText() == null || cheDoDetail.getValue() == null || ngayLapDetail.getValue() == null || diaChiDetail.getText() == null) {
            System.out.println("Trường thông tin bắt buộc không được bỏ trống");
            return;
        }
        HoKhau newHoKhau = HoKhauController.newHoKhau(maHoKhauDetail.getText(), Date.valueOf(ngayLapDetail.getValue()), diaChiDetail.getText(), "NULL", cheDoDetail.getValue());
        newHoKhau.setId(currentHoKhau.getId());
        if (HoKhauController.updateHoKhau(newHoKhau)) {
            System.out.println("Thành công");
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }

    }

}
