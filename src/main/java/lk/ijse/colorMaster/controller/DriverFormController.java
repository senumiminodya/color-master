package lk.ijse.colorMaster.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.dto.DriverDto;
import lk.ijse.colorMaster.model.DriverModel;

import java.io.IOException;
import java.sql.SQLException;

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
    private TableView<?> tblDriver;

    @FXML
    private JFXButton updateBtn;
    private DriverModel model = new DriverModel();

    @FXML
    void txtDriverSearchOnAction(ActionEvent event) {
        String id = txtDriverId.getText();
        String name = txtDriverName.getText();
        String address = txtDriverAddress.getText();
        String phoneNo = txtDriverPhoneNo.getText();

        try {
            DriverDto dto = model.searchDriver(id);
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
            boolean isDeleted = model.deleteDriver(id);
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
            boolean isSaved = model.saveDriver(dto);
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
            boolean isUpdated = model.updateDriver(dto);
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
}
