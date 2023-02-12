package com.quanlythuphi.views;

import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.controllers.BanGhiController;
import com.quanlythuphi.controllers.HoKhauController;
import com.quanlythuphi.controllers.KhoanPhiController;
import com.quanlythuphi.models.BanGhi;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class BanGhiView implements Initializable  {

    public Text tenKhoanPhiExistAlert;
    private BanGhi current;
    @FXML
    private AnchorPane createBanGhiPage;

    @FXML
    private TableView<BanGhi> danhSachBanGhi;

    @FXML
    private AnchorPane detailBanGhiPage;

    @FXML
    private TextArea ghiChu;

    @FXML
    private TextArea ghiChuDetail;

    @FXML
    private AnchorPane listBanGhiPage;

    @FXML
    private TextField maHoKhau;

    @FXML
    private TableColumn<BanGhi, String> maHoKhauCol;

    @FXML
    private TextField maHoKhauDetail;

    @FXML
    private TextField maHoKhauSearch;

    @FXML
    private DatePicker ngayNop;

    @FXML
    private DatePicker ngayNopDetail;

    @FXML
    private TextField soTien;

    @FXML
    private TextField soTienDetail;

    @FXML
    private TableColumn<BanGhi, Integer> sttCol;

    @FXML
    private TextField tenKhoanPhiDetail;

    @FXML
    private TextField tenKhoanPhi;

    @FXML
    private TableColumn<BanGhi, String> tenKhoanPhiCol;

    @FXML
    private TextField tenKhoanPhiSearch;

    @FXML
    private ChoiceBox<String> trangThai;

    @FXML
    private TableColumn<BanGhi, String> trangThaiCol;
    @FXML
    private TableColumn<BanGhi, String> soTienCol;
    @FXML
    private ChoiceBox<String> trangThaiDetail;

    @FXML
    private ChoiceBox<String> trangThaiSearch;

    @FXML
    private Text tenKhoanPhiAlert;

    @FXML
    private Text createAlert;
    @FXML
    private Text maHoKhauAlert;
    @FXML
    private Text tenKhoanPhiUpdateAlert;
    @FXML
    private Text maHoKhauUpdateAlert;
    @FXML
    private Text successUpdate;
    @FXML
    private Text failUpdate;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        trangThai.getItems().addAll(Constants.DA_NOP, Constants.CHUA_NOP);
        trangThaiSearch.getItems().addAll(Constants.ALL, Constants.DA_NOP, Constants.CHUA_NOP);
        trangThaiDetail.getItems().addAll(Constants.DA_NOP, Constants.CHUA_NOP);
        sttCol.setCellValueFactory(banGhiIntegerStt -> new SimpleObjectProperty<>(
                banGhiIntegerStt.getTableView().getItems().indexOf(banGhiIntegerStt.getValue())
        ));
        tenKhoanPhiCol.setCellValueFactory(tenKhoanPhiString -> new SimpleObjectProperty<>(
                KhoanPhiController.getKhoanPhi(tenKhoanPhiString.getValue().getKhoanPhiId()).getTenKhoanPhi()
        ));
        trangThaiCol.setCellValueFactory(trangThaiColString -> new SimpleObjectProperty<>(
                Constants.mapLoaiPhi(trangThaiColString.getValue().getTrangThai())
        ));
        maHoKhauCol.setCellValueFactory(maHoKhauColString -> new SimpleObjectProperty<>(
                HoKhauController.getHoKhau(maHoKhauColString.getValue().getHoKhauId()).getMaHoKhau()
        ));
        soTienCol.setCellValueFactory(banGhiStringCellDataFeatures -> new SimpleObjectProperty<>(
                Constants.doiSoThanhTien(Long.valueOf(banGhiStringCellDataFeatures.getValue() != null
                        ? banGhiStringCellDataFeatures.getValue().getSoTien() : null))
        ));
        listBanGhiPage.setVisible(true);
        detailBanGhiPage.setVisible(false);
        createBanGhiPage.setVisible(false);
        tenKhoanPhiAlert.setVisible(false);
        maHoKhauAlert.setVisible(false);
        createAlert.setVisible(false);
        successUpdate.setVisible(false);
        failUpdate.setVisible(false);
        tenKhoanPhiUpdateAlert.setVisible(false);
        maHoKhauUpdateAlert.setVisible(false);
        tenKhoanPhiExistAlert.setVisible(false);
        danhSachBanGhi.setRowFactory(nhanKhauTableView -> {
            TableRow<BanGhi> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    openDetailBanGhiPage(event, row.getItem());
                }
            });
            return row;
        });
        ObservableList<BanGhi> banGhiList = null;
        try {
            banGhiList = FXCollections.observableArrayList(
                    BanGhiController.getListBanGhiByFilter(null, null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        danhSachBanGhi.setItems(banGhiList);
    }

    public boolean isCreateRequestValid(TextField tenKhoanPhi, TextField maHoKhau, ChoiceBox<String> trangThai, DatePicker ngayNop, TextField soTien) throws SQLException {
        tenKhoanPhiAlert.setVisible(false);
        maHoKhauAlert.setVisible(false);
        createAlert.setVisible(false);
        if (tenKhoanPhi.getText().equals("") || maHoKhau.getText().equals("") || trangThai.getValue() == null || ngayNop.getValue() == null || soTien.getText().equals("")) {
            createAlert.setVisible(true);
            return false;
        }
        else {
            createAlert.setVisible(false);
        }
        if (KhoanPhiController.getKhoanPhiByTen(tenKhoanPhi.getText()) == null)  {
            tenKhoanPhiAlert.setVisible(true);
            return false;
        }
        else {
            tenKhoanPhiAlert.setVisible(false);
        }
        if (HoKhauController.getHoKhauByMaHoKhau(maHoKhau.getText()) == null) {
            maHoKhauAlert.setVisible(true);
            return false;
        }
        else {
            maHoKhauAlert.setVisible(true);
        }
        return true;
    }

    public boolean isUpdateRequestValid(TextField tenKhoanPhi, TextField maHoKhau) throws SQLException {
        tenKhoanPhiUpdateAlert.setVisible(false);
        maHoKhauUpdateAlert.setVisible(false);
        boolean isValid = true;
        if (KhoanPhiController.getKhoanPhiByTen(tenKhoanPhi.getText()) == null) {
            tenKhoanPhiUpdateAlert.setVisible(true);
            isValid = false;
        }
        else {
            tenKhoanPhiUpdateAlert.setVisible(false);
        }
        if (HoKhauController.getHoKhauByMaHoKhau(maHoKhau.getText()) == null) {
            maHoKhauUpdateAlert.setVisible(true);
            isValid = false;
        }
        else {
            maHoKhauUpdateAlert.setVisible(false);
        }
        return isValid;
    }
    @FXML
    void createBanGhi(Event event) throws SQLException {
        if (!isCreateRequestValid(tenKhoanPhi, maHoKhau, trangThai, ngayNop, soTien))
            return;
        BanGhi newBanGhi = BanGhiController.newBanGhi(tenKhoanPhi.getText(), maHoKhau.getText(),
                Date.valueOf(ngayNop.getValue()), ghiChu.getText(), trangThai.getValue(), Integer.valueOf(soTien.getText()));
        if (BanGhiController.createBanGhi(newBanGhi)) {
            System.out.println("Thành công");
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }
        openDetailBanGhiPage(event,newBanGhi);
    }

    void openDetailBanGhiPage(Event event, BanGhi banGhi) {
        tenKhoanPhiAlert.setVisible(false);
        maHoKhauAlert.setVisible(false);
        listBanGhiPage.setVisible(false);
        detailBanGhiPage.setVisible(true);
        createBanGhiPage.setVisible(false);
        current = banGhi;
        tenKhoanPhiDetail.setText(KhoanPhiController.getKhoanPhi(banGhi.getKhoanPhiId()).getTenKhoanPhi());
        maHoKhauDetail.setText(HoKhauController.getHoKhau(banGhi.getHoKhauId()).getMaHoKhau());
        trangThaiDetail.setValue(Constants.mapLoaiPhi(banGhi.getTrangThai()));
        ghiChuDetail.setText(banGhi.getGhiChu());
        ngayNopDetail.setValue(banGhi.getNgayNop().toLocalDate());
        soTienDetail.setText(String.valueOf(banGhi.getSoTien()));
    }
    @FXML
    void deleteBanGhi(ActionEvent event) {
        if (BanGhiController.deleteBanGhi(current)) {
            System.out.println("Thành công");
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }
        openListBanGhiPage(event);
    }

    @FXML
    void openCreateBanGhiPage(ActionEvent event) {
        tenKhoanPhiAlert.setVisible(false);
        maHoKhauAlert.setVisible(false);
        createAlert.setVisible(false);
        listBanGhiPage.setVisible(false);
        detailBanGhiPage.setVisible(false);
        createBanGhiPage.setVisible(true);
        tenKhoanPhi.clear();
        maHoKhau.clear();
        soTien.clear();
    }

    @FXML
    void openListBanGhiPage(ActionEvent event) {
        listBanGhiPage.setVisible(true);
        detailBanGhiPage.setVisible(false);
        createBanGhiPage.setVisible(false);
        ObservableList<BanGhi> banGhiList = null;
        try {
            banGhiList = FXCollections.observableArrayList(
                    BanGhiController.getListBanGhiByFilter(null, null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        danhSachBanGhi.setItems(banGhiList);
    }

    @FXML
    void searchBanGhi(ActionEvent event) {
        ObservableList<BanGhi> banGhiList = null;
        try {
            banGhiList = FXCollections.observableArrayList(
                    BanGhiController.getListBanGhiByFilter(tenKhoanPhiSearch.getText(), maHoKhauSearch.getText(), trangThaiSearch.getValue()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        danhSachBanGhi.setItems(banGhiList);
    }

    @FXML
    void updateBanGhi(ActionEvent event) throws SQLException {
        successUpdate.setVisible(false);
        failUpdate.setVisible(false);
        if (!isUpdateRequestValid(tenKhoanPhiDetail, maHoKhauDetail)) {
            System.out.println(tenKhoanPhiDetail.getText() + maHoKhauDetail.getText());
            return;
        }
        Integer id = current.getId();
        current = BanGhiController.newBanGhi(tenKhoanPhiDetail.getText(), maHoKhauDetail.getText(), Date.valueOf(ngayNopDetail.getValue()), ghiChuDetail.getText(), trangThaiDetail.getValue(),  Integer.valueOf(soTienDetail.getText()));
        current.setId(id);
        if (BanGhiController.updateBanGhi(current)) {
            successUpdate.setVisible(true);
            System.out.println("Thành công");
        } else {
            failUpdate.setVisible(false);
            System.out.println("Hệ thống đang có lỗi");
        }
    }

}
