package com.quanlythuphi.views;

import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.controllers.HoKhauController;
import com.quanlythuphi.controllers.NhanKhauController;
import com.quanlythuphi.models.HoKhau;
import com.quanlythuphi.models.NhanKhau;
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
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HoKhauView extends BaseView implements Initializable {

    public Text maHoKhauExistAlert;
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

    public Text unExistChuHoAlert;
    public Text createAlert;
    public Text updateSuccesAlert;
    public TableView<NhanKhau> thanhVienOfHoKhau;
    public TableColumn<NhanKhau, String> hoTenThanhVienCol;
    public TableColumn<NhanKhau, String> soDienThoaiThanhVienCol;
    public TableColumn<NhanKhau, String> loaiCuTruThanhVIenCol;
    public TableColumn<NhanKhau, String> soDinhDanhThanhVienCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize choicesbox
        cheDoSearch.getItems().addAll(Constants.ALL, Constants.HO_NGHEO, Constants.HO_CAN_NGHEO, Constants.HO_BINH_THUONG);
        cheDo.getItems().addAll(Constants.HO_NGHEO, Constants.HO_CAN_NGHEO, Constants.HO_BINH_THUONG);
        cheDoDetail.getItems().addAll(Constants.HO_NGHEO, Constants.HO_CAN_NGHEO, Constants.HO_BINH_THUONG);
        listHoKhauPage.setVisible(true);
        createHoKhauPage.setVisible(false);
        detailHoKhauPage.setVisible(false);
        unExistChuHoAlert.setVisible(false);
        updateSuccesAlert.setVisible(false);
        createAlert.setVisible(false);
        maHoKhauExistAlert.setVisible(false);
        //initialize TableColumns
        sttCol.setCellValueFactory(
                hoKhauIntegerCellDataFeatures -> new SimpleObjectProperty<>(
                        hoKhauIntegerCellDataFeatures.getTableView().getItems().indexOf(
                                hoKhauIntegerCellDataFeatures.getValue())));
        maHoKhauCol.setCellValueFactory(new PropertyValueFactory<>("maHoKhau"));
        cheDoCol.setCellValueFactory(
                hoKhauStringCellDataFeatures -> new SimpleObjectProperty<>(
                        Constants.mapCheDo(hoKhauStringCellDataFeatures.getValue().getCheDo())));
        hoTenThanhVienCol.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        soDinhDanhThanhVienCol.setCellValueFactory(new PropertyValueFactory<>("soDinhDanh"));
        loaiCuTruThanhVIenCol.setCellValueFactory(
                nhanKhauStringCellDataFeatures -> new SimpleObjectProperty<>(
                        nhanKhauStringCellDataFeatures.getValue().getLoaiCuTru() != null ?
                                nhanKhauStringCellDataFeatures.getValue().getLoaiCuTru() : ""
                )
        );
        soDienThoaiThanhVienCol.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        danhSachHoKhau.setRowFactory(tv -> {
            TableRow<HoKhau> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        openDetailHoKhauPage(event, row.getItem());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
        ObservableList<HoKhau> hoKhauList;
        try {
            hoKhauList = FXCollections.observableArrayList(
                    HoKhauController.getListHoKhauByFilter(null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        danhSachHoKhau.setItems(hoKhauList);

    }

    @FXML
    void createHoKhau(ActionEvent event) throws SQLException {
        maHoKhauExistAlert.setVisible(false);
        if (maHoKhau.getText() == null || cheDo.getValue() == null || Constants.mapDateToString(Date.valueOf(ngayLap.getValue())) == null || diaChi.getText() == null) {
            createAlert.setVisible(true);
            return;
        }
        else
            createAlert.setVisible(false);
        if (!chuHo.getText().equals("")) {
            System.out.println(chuHo.getText());
            if (NhanKhauController.getNhanKhauBySoDinhDanh(chuHo.getText()) == null) {
                unExistChuHoAlert.setVisible(true);
                return;
            }
        }
        if (HoKhauController.getHoKhauByMaHoKhau(maHoKhau.getText()) != null) {
            maHoKhauExistAlert.setVisible(true);
            return;
        }
        else {
            maHoKhauExistAlert.setVisible(false);
        }
        NhanKhau nhanKhau = NhanKhauController.getNhanKhauBySoDinhDanh(chuHo.getText());
        HoKhau hoKhau = HoKhauController.newHoKhau(maHoKhau.getText(), Date.valueOf(ngayLap.getValue()),
                diaChi.getText(), nhanKhau != null ? nhanKhau.getId() : null, cheDo.getValue());
        if (HoKhauController.createHoKhau(hoKhau)) {
            if (nhanKhau != null) {
                nhanKhau.setHoKhauId(HoKhauController.getHoKhauByMaHoKhau(maHoKhau.getText()).getId());
                NhanKhauController.updateNhanKhau(nhanKhau);
            }
            System.out.println("Th??nh c??ng");
            openDetailHoKhauPage(event, hoKhau);
        } else {
            System.out.println("H??? th???ng ??ang c?? l???i");
        }
    }

    void openDetailHoKhauPage(Event event, HoKhau hoKhau) throws SQLException {
        listHoKhauPage.setVisible(false);
        createHoKhauPage.setVisible(false);
        detailHoKhauPage.setVisible(true);
        updateSuccesAlert.setVisible(false);
        currentHoKhau = hoKhau;
        maHoKhauDetail.setText(hoKhau.getMaHoKhau());
        diaChiDetail.setText(hoKhau.getDiaChi());
        ngayLapDetail.setValue(hoKhau.getNgayLap().toLocalDate());
        cheDoDetail.setValue(Constants.mapCheDo(hoKhau.getCheDo()));
        if (hoKhau.getChuHoId() == null) {
            chuHoDetail.setText("Ch??a c?? th??ng tin ch??? h???");
        } else {
            NhanKhau chuHo = NhanKhauController.getNhanKhau(hoKhau.getChuHoId());
            chuHoDetail.setText((chuHo != null ? chuHo.getSoDinhDanh() : null));
        }
        ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList(
                NhanKhauController.getListNhanKhauByHoKhauId(currentHoKhau.getId()));
        thanhVienOfHoKhau.setItems(nhanKhauList);

    }

    @FXML
    void openCreateHoKhau(ActionEvent event) {
        maHoKhauExistAlert.setVisible(false);
        unExistChuHoAlert.setVisible(false);
        createAlert.setVisible(false);
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
    void searchHoKhau(ActionEvent event) throws SQLException {
        Integer temp = null;
        if (cheDoSearch.getValue() != null && !cheDoSearch.getValue().equals(Constants.ALL))
            temp = Constants.mapCheDo(cheDoSearch.getValue());
        ObservableList<HoKhau> hoKhauList = FXCollections.observableArrayList(
                HoKhauController.getListHoKhauByFilter(maHoKhauSearch.getText(), temp));
        danhSachHoKhau.setItems(hoKhauList);
    }


    @FXML
    void updateHoKhau(ActionEvent event) throws SQLException {
        if (maHoKhauDetail.getText() == null || cheDoDetail.getValue() == null || ngayLapDetail.getValue() == null || diaChiDetail.getText() == null) {
            System.out.println("Tr?????ng th??ng tin b???t bu???c kh??ng ???????c b??? tr???ng");
            return;
        }
        NhanKhau nhanKhau = NhanKhauController.getNhanKhauBySoDinhDanh(chuHoDetail.getText());

        HoKhau newHoKhau = HoKhauController.newHoKhau(maHoKhauDetail.getText(), Date.valueOf(ngayLapDetail.getValue()),
                diaChiDetail.getText(), nhanKhau != null ? nhanKhau.getId() : null, cheDoDetail.getValue());
        newHoKhau.setId(currentHoKhau.getId());
        if (HoKhauController.updateHoKhau(newHoKhau)) {
            updateSuccesAlert.setVisible(true);
            if (nhanKhau != null) {
                nhanKhau.setHoKhauId(HoKhauController.getHoKhauByMaHoKhau(maHoKhauDetail.getText()).getId());
                NhanKhauController.updateNhanKhau(nhanKhau);
            }
        } else {
            System.out.println("H??? th???ng ??ang c?? l???i");
        }

    }

    public void deleteHoKhau(ActionEvent event) throws SQLException {
        if (HoKhauController.deleteHoKhau(currentHoKhau)) {
            System.out.println("Th??nh c??ng");
        } else {
            System.out.println("H??? th???ng ??ang c?? l???i");
        }
        currentHoKhau = null;
        openListHoKhauPage(event);
    }
}
