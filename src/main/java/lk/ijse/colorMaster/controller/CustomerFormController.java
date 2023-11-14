package lk.ijse.colorMaster.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.model.CustomerModel;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerFormController {
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
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton updateBtn;

    private CustomerModel model = new CustomerModel();
    private AnchorPane root;

    @FXML
    void customerSearchOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String telNo = txtPhoneNo.getText();

        try {
            CustomerDto dto = model.searchCustomer(id);

            if (dto != null) {
                txtId.setText(dto.getId());
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtPhoneNo.setText(dto.getPhoneNo());
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Customer not found!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        System.out.println("BAck On Customer Form");
        Stage window = (Stage)txtName.getScene().getWindow();
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
        clearCustomer();
    }

    private void clearCustomer() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhoneNo.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            boolean isDeleted = model.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Customer not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phoneNo = txtPhoneNo.getText();

        CustomerDto dto = new CustomerDto(id, name, address, phoneNo);

        try {
            boolean isSaved = model.saveCustomer(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer saved successfully.").show();
                clearCustomer();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phoneNo = txtPhoneNo.getText();

        CustomerDto dto = new CustomerDto(id, name, address, phoneNo);

        try {
            boolean isUpdated = model.updateCustomer(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer updated successfully.").show();
                clearCustomer();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }
}
