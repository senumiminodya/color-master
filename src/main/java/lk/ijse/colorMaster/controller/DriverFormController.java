package lk.ijse.colorMaster.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.colorMaster.dao.custom.impl.DriverDAOImpl;
import lk.ijse.colorMaster.dto.DriverDto;
import lk.ijse.colorMaster.dto.tm.DriverTm;
//import lk.ijse.colorMaster.model.DriverModel;
import lk.ijse.colorMaster.util.Regex;
import lk.ijse.colorMaster.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DriverFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_phoneNo;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private TextField txtDriverAddress;

    @FXML
    private TextField txtDriverId;

    @FXML
    private TextField txtDriverName;

    @FXML
    private TextField txtDriverPhoneNo;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<DriverTm> tblDriver;

    @FXML
    private JFXButton updateBtn;
    //private DriverModel model = new DriverModel();
    private DriverDAOImpl driverDAO = new DriverDAOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllDrivers();
    }

    private void loadAllDrivers() {
        ObservableList<DriverTm> obList = FXCollections.observableArrayList();
        try {
            List<DriverDto> allDriverDto = driverDAO.getAll();
            for (DriverDto dto : allDriverDto) {
                obList.add(
                        new DriverTm(
                                dto.getDriverId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getPhoneNo()
                        )
                );
            }
            tblDriver.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
    }

    @FXML
    void txtDriverSearchOnAction(ActionEvent event) {
        String id = txtDriverId.getText();
        String name = txtDriverName.getText();
        String address = txtDriverAddress.getText();
        String phoneNo = txtDriverPhoneNo.getText();

        try {
            DriverDto dto = driverDAO.search(id);
            txtDriverId.setText(dto.getDriverId());
            txtDriverName.setText(dto.getName());
            txtDriverAddress.setText(dto.getAddress());
            txtDriverPhoneNo.setText(dto.getPhoneNo());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)txtDriverName.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.show();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearDriver();
    }

    private void clearDriver() {
        txtDriverId.clear();
        txtDriverName.clear();
        txtDriverAddress.clear();
        txtDriverPhoneNo.clear();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtDriverId.getText();
        try {
            boolean isDeleted = driverDAO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Driver deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Driver not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtDriverId.getText();
        String name = txtDriverName.getText();
        String address = txtDriverAddress.getText();
        String phoneNo = txtDriverPhoneNo.getText();

        DriverDto dto = new DriverDto(id, name, address, phoneNo);

        try {
            boolean isSaved = driverDAO.save(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Driver saved successfully.").show();
                clearDriver();
            } else {
                new Alert(Alert.AlertType.ERROR, "Driver not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtDriverId.getText();
        String name = txtDriverName.getText();
        String address = txtDriverAddress.getText();
        String phoneNo = txtDriverPhoneNo.getText();

        DriverDto dto = new DriverDto(id, name, address, phoneNo);

        try {
            boolean isUpdated = driverDAO.update(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Driver updated successfully.").show();
                clearDriver();
            } else {
                new Alert(Alert.AlertType.ERROR, "Driver not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }
    @FXML
    void txtDriverIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.ID, txtDriverId);
    }

    @FXML
    void txtDriverPhoneNoOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.PHONE, txtDriverPhoneNo);
    }
}
