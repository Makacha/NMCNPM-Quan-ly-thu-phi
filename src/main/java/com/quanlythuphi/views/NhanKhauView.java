package com.quanlythuphi.views;

import com.quanlythuphi.constants.Constants;
import com.quanlythuphi.controllers.HoKhauController;
import com.quanlythuphi.controllers.NhanKhauController;
import com.quanlythuphi.models.NhanKhau;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NhanKhauView extends BaseView implements Initializable {
    private NhanKhau current;

    public AnchorPane createNhanKhauPage;
    public AnchorPane detailKhoanPhiPage;
    public AnchorPane listNhanKhauPage;
    public TextField tenNhanKhau;
    public DatePicker ngaySinh;
    public TextField danToc;
    public TextField queQuan;
    public TextField noiSinh;
    public TextField soDinhDanh;
    public TextField soDienThoai;
    public TextField tonGiao;
    public TextField quocTich;
    public TextField diaChi;
    public TextField ngheNghiep;
    public TextField maHoKhau;
    public ChoiceBox<String> gioiTinh;
    public ChoiceBox<String> tinhTrang;
    public ChoiceBox<String> loaiCuTru;
    public TextField tenNhanKhauSearch;
    public TableView<NhanKhau> danhSachNhanKhau;
    public TableColumn<NhanKhau, Integer> sttCol;
    public TableColumn<NhanKhau, String> tenNhanKhauCol;
    public TableColumn<NhanKhau, String> gioiTinhCol;
    public TableColumn<NhanKhau, String> soDinhDanhCol;
    public TableColumn<NhanKhau, String> soDienThoaiCol;
    public TableColumn<NhanKhau, String> diaChiCol;
    public TextField soDinhDanhSearch;
    public TextField soDienThoaiSearch;
    public TextField diaChiSearch;
    public TextField tenNhanKhauDetail;
    public DatePicker ngaySinhDetail;
    public TextField danTocDetail;
    public TextField queQuanDetail;
    public TextField noiSinhDetail;
    public TextField soDinhDanhDetail;
    public TextField soDienThoaiDetail;
    public TextField tonGiaoDetail;
    public TextField quocTichDetail;
    public TextField diaChiDetail;
    public TextField ngheNghiepDetail;
    public TextField maHoKhauDetail;
    public ChoiceBox<String> gioiTinhDetail;
    public ChoiceBox<String> tinhTrangDetail;
    public ChoiceBox<String> loaiCuTruDetail;
    public Text createAlert;
    public Text unExistMaHoKhauAlert;
    public Text unExistUpdaetHoKhau;
    public Text updateSuccess;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gioiTinh.getItems().addAll(Constants.GIOI_TINH_NAM, Constants.GIOI_TINH_NU, Constants.GIOI_TINH_KHAC);
        tinhTrang.getItems().addAll(Constants.DOC_THAN, Constants.KET_HON, Constants.LY_HON);
        loaiCuTru.getItems().addAll(Constants.THUONG_TRU, Constants.TAM_TRU);
        gioiTinhDetail.getItems().addAll(Constants.GIOI_TINH_NAM, Constants.GIOI_TINH_NU, Constants.GIOI_TINH_KHAC);
        tinhTrangDetail.getItems().addAll(Constants.DOC_THAN, Constants.KET_HON, Constants.LY_HON);
        loaiCuTruDetail.getItems().addAll(Constants.THUONG_TRU, Constants.TAM_TRU);
        listNhanKhauPage.setVisible(true);
        createNhanKhauPage.setVisible(false);
        detailKhoanPhiPage.setVisible(false);
        createAlert.setVisible(false);
        unExistMaHoKhauAlert.setVisible(false);
        unExistUpdaetHoKhau.setVisible(false);
        updateSuccess.setVisible(false);
        sttCol.setCellValueFactory(nhanKhauIntegerCellDataFeatures -> new SimpleObjectProperty<>(
            nhanKhauIntegerCellDataFeatures.getTableView().getItems()
                .indexOf(nhanKhauIntegerCellDataFeatures.getValue())
        ));
        tenNhanKhauCol.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        gioiTinhCol.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        soDinhDanhCol.setCellValueFactory(new PropertyValueFactory<>("soDinhDanh"));
        soDienThoaiCol.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        diaChiCol.setCellValueFactory(new PropertyValueFactory<>("diaChiThuongTru"));
        danhSachNhanKhau.setRowFactory(nhanKhauTableView -> {
            TableRow<NhanKhau> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    openDetailNhanKhauPage(event, row.getItem());
                }
            });
            return row;
        });
        ObservableList<NhanKhau> nhanKhauList = null;
        try {
            nhanKhauList = FXCollections.observableArrayList(
                    NhanKhauController.getListNhanKhauByFilter(null, null, null, null));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        danhSachNhanKhau.setItems(nhanKhauList);
    }

    public void openListNhanKhauPage(Event event) throws SQLException {
        listNhanKhauPage.setVisible(true);
        createNhanKhauPage.setVisible(false);
        detailKhoanPhiPage.setVisible(false);
        searchNhanKhau((ActionEvent) event);
    }

    public void openCreateNhanKhauPage(ActionEvent actionEvent) {
        listNhanKhauPage.setVisible(false);
        createNhanKhauPage.setVisible(true);
        detailKhoanPhiPage.setVisible(false);
        createAlert.setVisible(false);
        unExistMaHoKhauAlert.setVisible(false);
    }

    public void openDetailNhanKhauPage(Event event, NhanKhau nhanKhau) {
        unExistUpdaetHoKhau.setVisible(false);
        updateSuccess.setVisible(false);
        listNhanKhauPage.setVisible(false);
        createNhanKhauPage.setVisible(false);
        detailKhoanPhiPage.setVisible(true);
        current = nhanKhau;
        tenNhanKhauDetail.setText(current.getHoTen());
        ngaySinhDetail.setValue(current.getNgaySinh().toLocalDate());
        danTocDetail.setText(current.getDanToc());
        queQuanDetail.setText(current.getQueQuan());
        noiSinhDetail.setText(current.getNoiSinh());
        soDinhDanhDetail.setText(current.getSoDinhDanh());
        soDienThoaiDetail.setText(current.getSoDienThoai());
        tonGiaoDetail.setText(current.getTonGiao());
        quocTichDetail.setText(current.getQuocTich());
        diaChiDetail.setText(current.getDiaChiThuongTru());
        ngheNghiepDetail.setText(current.getNgheNghiep());
        gioiTinhDetail.setValue(current.getGioiTinh());
        tinhTrangDetail.setValue(current.getTinhTrang());
        loaiCuTruDetail.setValue(current.getLoaiCuTru());
        if (current.getHoKhauId() != null)
            maHoKhauDetail.setText(HoKhauController.getHoKhau(current.getHoKhauId()).getMaHoKhau());
    }

    public void searchNhanKhau(ActionEvent actionEvent) throws SQLException {
        ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList(
            NhanKhauController.getListNhanKhauByFilter(tenNhanKhauSearch.getText(), soDinhDanhSearch.getText(),
                soDinhDanhSearch.getText(), diaChiSearch.getText()));
        danhSachNhanKhau.setItems(nhanKhauList);
    }

    public void createNhanKhau(ActionEvent actionEvent) throws SQLException {
        if (tenNhanKhau.getText().equals("") || soDinhDanh.getText().equals("") || ngaySinh.getValue() == null ||
                soDienThoai.getText().equals("") || loaiCuTru.getValue() == null || tinhTrang.getValue() == null ||
                gioiTinh.getValue() == null) {
            createAlert.setVisible(true);
            return;
        }
        else
           createAlert.setVisible(false);
        if (!maHoKhau.getText().equals("")) {
            if (HoKhauController.getHoKhauByMaHoKhau(maHoKhau.getText()) == null) {
                unExistMaHoKhauAlert.setVisible(true);
                return;
            } else
                unExistMaHoKhauAlert.setVisible(false);
        }
        NhanKhau nhanKhau = NhanKhauController.newNhanKhau(tenNhanKhau.getText(),
            ngaySinh.getValue() != null ? Date.valueOf(ngaySinh.getValue()) : null, danToc.getText(),
            soDinhDanh.getText(), tonGiao.getText(), queQuan.getText(), gioiTinh.getValue(), diaChi.getText(),
            loaiCuTru.getValue(), soDienThoai.getText(), noiSinh.getText(), tinhTrang.getValue(), quocTich.getText(),
            ngheNghiep.getText(), maHoKhau.getText()
        );
        if (NhanKhauController.createNhanKhau(nhanKhau)) {
            nhanKhau = NhanKhauController.getNhanKhauBySoDinhDanh(nhanKhau.getSoDinhDanh());
            System.out.println("Thành công");
            openDetailNhanKhauPage(actionEvent, nhanKhau);
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }
    }

    public void updateNhanKhau(ActionEvent actionEvent) {
        if (HoKhauController.getHoKhauByMaHoKhau(maHoKhauDetail.getText()) == null) {
            unExistUpdaetHoKhau.setVisible(true);
            return;
        }
        else
            unExistUpdaetHoKhau.setVisible(false);
        current = NhanKhauController.newNhanKhau(current.getId(), tenNhanKhauDetail.getText(),
            ngaySinhDetail.getValue() != null ? Date.valueOf(ngaySinhDetail.getValue()) : null, danTocDetail.getText(),
            soDinhDanhDetail.getText(), tonGiaoDetail.getText(), queQuanDetail.getText(), gioiTinhDetail.getValue(),
            diaChiDetail.getText(), loaiCuTruDetail.getValue(), soDienThoaiDetail.getText(), noiSinhDetail.getText(),
            tinhTrangDetail.getValue(), quocTichDetail.getText(), ngheNghiepDetail.getText(), maHoKhauDetail.getText()
        );
        if (NhanKhauController.updateNhanKhau(current)) {
            updateSuccess.setVisible(true);
            System.out.println("Thành công");
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }
    }

    public void deleteNhanKhau(ActionEvent actionEvent) throws SQLException {
        if (NhanKhauController.deleteNhanKhau(current)) {
            System.out.println("Thành công");
        } else {
            System.out.println("Hệ thống đang có lỗi");
        }
        current = null;
        openListNhanKhauPage(actionEvent);
    }
}
