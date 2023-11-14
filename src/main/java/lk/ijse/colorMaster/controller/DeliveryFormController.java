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
import lk.ijse.colorMaster.dto.DeliveryDto;
import lk.ijse.colorMaster.dto.DriverDto;
import lk.ijse.colorMaster.model.DeliveryModel;

import java.io.IOException;
import java.sql.SQLException;

public class DeliveryFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private TableColumn<?, ?> col_ownerName;

    @FXML
    private TableColumn<?, ?> col_ownerPhoneNo;

    @FXML
    private TableColumn<?, ?> col_vehicleId;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<?> tblDelivery;

    @FXML
    private TextField txtOwnerName;

    @FXML
    private TextField txtOwnerPhoneNo;

    @FXML
    private TextField txtVehicleId;

    @FXML
    private JFXButton updateBtn;

    private DeliveryModel model = new DeliveryModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)txtVehicleId.getScene().getWindow();
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
        clearVehicles();
    }

    private void clearVehicles() {
        txtVehicleId.clear();
        txtOwnerName.clear();
        txtOwnerPhoneNo.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtVehicleId.getText();
        try {
            boolean isDeleted = model.deleteVehicle(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Vehicle not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtVehicleId.getText();
        String name = txtOwnerName.getText();
        String phoneNo = txtOwnerPhoneNo.getText();

        DeliveryDto dto = new DeliveryDto(id, name, phoneNo);

        try {
            boolean isSaved = model.saveVehicle(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle saved successfully.").show();
                clearVehicles();
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtVehicleId.getText();
        String name = txtOwnerName.getText();
        String phoneNo = txtOwnerPhoneNo.getText();

        DeliveryDto dto = new DeliveryDto(id, name, phoneNo);

        try {
            boolean isUpdated = model.updateVehicle(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle updated successfully.").show();
                clearVehicles();
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void txtVehicleSearchOnAction(ActionEvent event) {
        String id = txtVehicleId.getText();
        String name = txtOwnerName.getText();
        String phoneNo = txtOwnerPhoneNo.getText();

        try {
            DeliveryDto dto = model.searchVehicle(id);
            txtVehicleId.setText(dto.getId());
            txtOwnerName.setText(dto.getOwnerName());
            txtOwnerPhoneNo.setText(dto.getOwnerPhoneNo());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }
}
