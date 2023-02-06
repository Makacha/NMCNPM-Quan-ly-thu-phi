package com.quanlythuphi.views;

import com.quanlythuphi.constants.ChuyenTienRaChu;
import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.controllers.BanGhiController;
import com.quanlythuphi.controllers.HoKhauController;
import com.quanlythuphi.controllers.KhoanPhiController;
import com.quanlythuphi.controllers.NhanKhauController;
import com.quanlythuphi.models.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ThongKeView extends BaseView implements Initializable {

    public TableView<ThongTinThongKe> listHoKhauChuaNop;
    public TableColumn<ThongTinThongKe, Integer> sttChuaNop;
    public TableColumn<ThongTinThongKe, String> maHoKhauChuaNop;
    public TableColumn<ThongTinThongKe, String> tenChuHoChuaNop;
    public TableColumn<ThongTinThongKe, String> sdtChuaNop;
    public TableColumn<ThongTinThongKe, String> soTienChuaNop;
    public TableColumn<ThongTinThongKe, String> diaChiChuaNop;
    public TableView<ThongTinThongKe> listHoKhauDaNop;
    public TableColumn<ThongTinThongKe, Integer> sttDaNop;
    public TableColumn<ThongTinThongKe, String> maHoKhauDaNop;
    public TableColumn<ThongTinThongKe, String> tenChuHoDaNop;
    public TableColumn<ThongTinThongKe, String> sdtDaNop;
    public TableColumn<ThongTinThongKe, String> soTienDaNop;
    public TableColumn<ThongTinThongKe, String> diaChiDaNop;
    private String tenKhoanPhi;
    @FXML
    private ComboBox<String> thongKeListChuaNopKhoanPhi;

    @FXML
    private ComboBox<String> thongKeListDaNopKhoanPhi;

    @FXML
    private ComboBox<String> thongKeSumKhoanPhi;

    @FXML
    private Label tongTienBangChu;

    @FXML
    private Label tongTienBangSo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thongKeSumKhoanPhi.setEditable(true);
        thongKeListDaNopKhoanPhi.setEditable(true);
        thongKeListChuaNopKhoanPhi.setEditable(true);
        tongTienBangChu.setFont(new Font("Arial", 24));
        tongTienBangSo.setFont(new Font("Arial", 24));
        thongKeSumKhoanPhi.getEditor().setOnKeyTyped(this::searchKhoanPhiThongKeSumKhoanPhi);
        thongKeListDaNopKhoanPhi.getEditor().setOnKeyTyped(this::searchKhoanPhiThongKeListDaNopKhoanPhi);
        thongKeListChuaNopKhoanPhi.getEditor().setOnKeyTyped(this::searchKhoanPhiThongKeListChuaNopKhoanPhi);
        thongKeSumKhoanPhi.setOnAction(event -> {
            tenKhoanPhi = thongKeSumKhoanPhi.getSelectionModel().getSelectedItem();
        });
        thongKeListDaNopKhoanPhi.setOnAction(event -> {
            tenKhoanPhi = thongKeListDaNopKhoanPhi.getSelectionModel().getSelectedItem();
        });
        thongKeListChuaNopKhoanPhi.setOnAction(event -> {
            tenKhoanPhi = thongKeListChuaNopKhoanPhi.getSelectionModel().getSelectedItem();
        });

        sttDaNop.setCellValueFactory(hoKhauStringCellDataFeatures -> new SimpleObjectProperty<>(
                hoKhauStringCellDataFeatures.getTableView().getItems().indexOf(hoKhauStringCellDataFeatures.getValue())
        ));
        sttChuaNop.setCellValueFactory(hoKhauStringCellDataFeatures -> new SimpleObjectProperty<>(
                hoKhauStringCellDataFeatures.getTableView().getItems().indexOf(hoKhauStringCellDataFeatures.getValue())
        ));

        maHoKhauChuaNop.setCellValueFactory(new PropertyValueFactory<>("maHoKhau"));
        maHoKhauDaNop.setCellValueFactory(new PropertyValueFactory<>("maHoKhau"));

        tenChuHoDaNop.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));
        tenChuHoChuaNop.setCellValueFactory(new PropertyValueFactory<>("tenChuHo"));

        sdtDaNop.setCellValueFactory(new PropertyValueFactory<>("sDTChuHo"));
        sdtChuaNop.setCellValueFactory(new PropertyValueFactory<>("sDTChuHo"));

        soTienDaNop.setCellValueFactory(new PropertyValueFactory<>("soTien"));
        soTienChuaNop.setCellValueFactory(new PropertyValueFactory<>("soTien"));

        diaChiDaNop.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        diaChiChuaNop.setCellValueFactory(new PropertyValueFactory<>("diaChi"));

    }

    @FXML
    void timKiemCacHoChuaNop(ActionEvent event) {
        ArrayList<ThongTinThongKe> list = new ArrayList<>();
        try {
            ArrayList<BanGhi> banGhis = BanGhiController.getListBanGhiByFilter(tenKhoanPhi, Constants.CHUA_NOP, null);
            for (BanGhi banGhi: banGhis) {
                HoKhau hoKhau = HoKhauController.getHoKhau(banGhi.getHoKhauId());
                NhanKhau chuHo = null;
                if (hoKhau.getChuHoId() != null)
                    chuHo = NhanKhauController.getNhanKhau(hoKhau.getChuHoId());
                list.add(new ThongTinThongKe(hoKhau.getMaHoKhau(), chuHo != null ? chuHo.getHoTen() : null,
                        chuHo != null ? chuHo.getSoDienThoai() : null, hoKhau.getDiaChi(), banGhi.getSoTien()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listHoKhauChuaNop.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    void timKiemCacHoDaNop(ActionEvent event) {
        ArrayList<ThongTinThongKe> list = new ArrayList<>();
        try {
            ArrayList<BanGhi> banGhis = BanGhiController.getListBanGhiByFilter(tenKhoanPhi, Constants.DA_NOP, null);
            for (BanGhi banGhi: banGhis) {
                HoKhau hoKhau = HoKhauController.getHoKhau(banGhi.getHoKhauId());
                NhanKhau chuHo = null;
                if (hoKhau.getChuHoId() != null)
                    chuHo = NhanKhauController.getNhanKhau(hoKhau.getChuHoId());
                list.add(new ThongTinThongKe(hoKhau.getMaHoKhau(), chuHo != null ? chuHo.getHoTen() : null,
                        chuHo != null ? chuHo.getSoDienThoai() : null, hoKhau.getDiaChi(), banGhi.getSoTien()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listHoKhauDaNop.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    void tinhTongKhoanPhi(ActionEvent event) throws SQLException {
        if (tenKhoanPhi == null)
            return;
        KhoanPhi khoanPhi = KhoanPhiController.getKhoanPhiByTen(tenKhoanPhi);
        Long soTien = BanGhiController.getSumByKhoanPhiId(khoanPhi.getId());
        tongTienBangSo.setText(Constants.doiSoThanhTien(soTien));
        tongTienBangChu.setText(ChuyenTienRaChu.ChuyenSangChu(String.valueOf(soTien)));
    }

    public void searchKhoanPhiThongKeSumKhoanPhi(KeyEvent keyEvent) {
        if (thongKeSumKhoanPhi.getEditor().getText().equals(""))
            return;
        ObservableList<String> tenKhoanPhiList = null;

        try {
            tenKhoanPhiList = FXCollections.observableArrayList(
                    KhoanPhiController.getListTenKhoanPhiByTen(thongKeSumKhoanPhi.getEditor().getText()));
            thongKeSumKhoanPhi.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        thongKeSumKhoanPhi.setItems(tenKhoanPhiList);
    }
    public void searchKhoanPhiThongKeListDaNopKhoanPhi(KeyEvent keyEvent) {
        if (thongKeListDaNopKhoanPhi.getEditor().getText().equals(""))
            return;
        ObservableList<String> tenKhoanPhiList = null;

        try {
            tenKhoanPhiList = FXCollections.observableArrayList(
                    KhoanPhiController.getListTenKhoanPhiByTen(thongKeListDaNopKhoanPhi.getEditor().getText()));
            thongKeListDaNopKhoanPhi.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        thongKeListDaNopKhoanPhi.setItems(tenKhoanPhiList);
    }
    public void searchKhoanPhiThongKeListChuaNopKhoanPhi(KeyEvent keyEvent) {
        if (thongKeListChuaNopKhoanPhi.getEditor().getText().equals(""))
            return;
        ObservableList<String> tenKhoanPhiList = null;

        try {
            tenKhoanPhiList = FXCollections.observableArrayList(
                    KhoanPhiController.getListTenKhoanPhiByTen(thongKeListChuaNopKhoanPhi.getEditor().getText()));
            thongKeListChuaNopKhoanPhi.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        thongKeListChuaNopKhoanPhi.setItems(tenKhoanPhiList);
    }

    public void choiceKhoanPhi(MouseEvent mouseEvent) {

    }
}
