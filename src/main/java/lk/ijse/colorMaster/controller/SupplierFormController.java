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
import lk.ijse.colorMaster.dto.SupplierDto;
import lk.ijse.colorMaster.model.CustomerModel;
import lk.ijse.colorMaster.model.SupplierModel;

import java.io.IOException;
import java.sql.SQLException;


public class SupplierFormController {
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
    private JFXButton saveBtn;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtSupplierPhoneNo;

    @FXML
    private TextField txtSupplierProduct;

    @FXML
    private JFXButton updateBtn;

    private SupplierModel model = new SupplierModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)txtSupplierName.getScene().getWindow();
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
        clearSupplier();
    }

    private void clearSupplier() {
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtSupplierPhoneNo.clear();
        txtSupplierProduct.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        try {
            boolean isDeleted = model.deleteSupplier(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Supplier not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String address = txtSupplierPhoneNo.getText();
        String phoneNo = txtSupplierProduct.getText();

        SupplierDto dto = new SupplierDto(id, name, address, phoneNo);

        try {
            boolean isSaved = model.saveSupplier(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier saved successfully.").show();
                clearSupplier();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String address = txtSupplierPhoneNo.getText();
        String phoneNo = txtSupplierProduct.getText();

        SupplierDto dto = new SupplierDto(id, name, address, phoneNo);

        try {
            boolean isUpdated = model.updateSupplier(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier updated successfully.").show();
                clearSupplier();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void txtSupplierSearchOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String phoneNo = txtSupplierPhoneNo.getText();
        String product = txtSupplierProduct.getText();

        try {
            SupplierDto dto = model.searchSupplier(id);
            txtSupplierId.setText(dto.getId());
            txtSupplierName.setText(dto.getName());
            txtSupplierPhoneNo.setText(dto.getPhoneNo());
            txtSupplierProduct.setText(dto.getProduct());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }
}
