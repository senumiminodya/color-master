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
import lk.ijse.colorMaster.bo.custom.DeliveryBO;
import lk.ijse.colorMaster.bo.custom.impl.DeliveryFormBOImpl;
import lk.ijse.colorMaster.dto.DeliveryDto;
import lk.ijse.colorMaster.dto.tm.DeliveryTm;
//import lk.ijse.colorMaster.model.DeliveryModel;
import lk.ijse.colorMaster.util.Regex;
import lk.ijse.colorMaster.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
    private TableView<DeliveryTm> tblDelivery;

    @FXML
    private TextField txtOwnerName;

    @FXML
    private TextField txtOwnerPhoneNo;

    @FXML
    private TextField txtVehicleId;

    @FXML
    private JFXButton updateBtn;

    //private DeliveryModel model = new DeliveryModel();
    //private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
    private DeliveryBO vehicleBO = new DeliveryFormBOImpl();

    public void initialize() {
        loadAllVehicles();
        setCellValueFactory();
    }

    private void loadAllVehicles() {
        ObservableList<DeliveryTm> obList = FXCollections.observableArrayList();
        try {
            List<DeliveryDto> allVehicleDto = vehicleBO.getAllVehicle();
            for (DeliveryDto dto : allVehicleDto) {
                obList.add(
                        new DeliveryTm(
                                dto.getId(),
                                dto.getOwnerName(),
                                dto.getOwnerPhoneNo()
                        )
                );
            }
            tblDelivery.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        col_vehicleId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ownerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_ownerPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
    }

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
            boolean isDeleted = vehicleBO.deleteVehicle(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Vehicle not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtVehicleId.getText();
        String name = txtOwnerName.getText();
        String phoneNo = txtOwnerPhoneNo.getText();

        DeliveryDto dto = new DeliveryDto(id, name, phoneNo);

        try {
            boolean isSaved = vehicleBO.saveVehicle(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle saved successfully.").show();
                clearVehicles();
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtVehicleId.getText();
        String name = txtOwnerName.getText();
        String phoneNo = txtOwnerPhoneNo.getText();

        DeliveryDto dto = new DeliveryDto(id, name, phoneNo);

        try {
            boolean isUpdated = vehicleBO.updateVehicle(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle updated successfully.").show();
                clearVehicles();
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtVehicleSearchOnAction(ActionEvent event) {
        String id = txtVehicleId.getText();
        String name = txtOwnerName.getText();
        String phoneNo = txtOwnerPhoneNo.getText();
        DeliveryDto dto = new DeliveryDto();

        try {
            List<DeliveryDto> vehicleDtos = vehicleBO.searchVehicle(id);
            for (DeliveryDto dtos: vehicleDtos) {
                dto = new DeliveryDto(dtos.getId(), dtos.getOwnerName(), dtos.getOwnerPhoneNo());
            }
            txtVehicleId.setText(dto.getId());
            txtOwnerName.setText(dto.getOwnerName());
            txtOwnerPhoneNo.setText(dto.getOwnerPhoneNo());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtOwnerPhoneNoOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.PHONE, txtOwnerPhoneNo);
    }

    @FXML
    void txtVehicleIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.ID, txtVehicleId);
    }

}
