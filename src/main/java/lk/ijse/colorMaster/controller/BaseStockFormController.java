package lk.ijse.colorMaster.controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.BaseStockDto;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.model.BaseStockModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseStockFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private ComboBox<String> cmbSupplierName;

    @FXML
    private TableColumn<?, ?> col_SupplierName;

    @FXML
    private TableColumn<?, ?> col_baseType;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_price;

    @FXML
    private TableColumn<?, ?> col_qty;

    @FXML
    private TableColumn<?, ?> col_size;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<?> tblBases;

    @FXML
    private TextField txtBaseId;

    @FXML
    private TextField txtBasePrice;

    @FXML
    private TextField txtBaseSize;

    @FXML
    private TextField txtBaseType;

    @FXML
    private TextField txtBaseqty;

    @FXML
    private JFXButton updateBtn;

    private BaseStockModel model = new BaseStockModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)txtBaseId.getScene().getWindow();
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
        clearBases();
    }

    private void clearBases() {
        txtBaseId.clear();
        txtBaseType.clear();
        cmbSupplierName.getSelectionModel().clearSelection();
        txtBaseSize.clear();
        txtBaseqty.clear();
        txtBasePrice.clear();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String id = txtBaseId.getText();
        try {
            boolean isDeleted = model.deleteBase(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Base deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Base not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtBaseId.getText();
        String type = txtBaseType.getText();
        String name = (String) cmbSupplierName.getValue();
        String size = txtBaseSize.getText();
        int qty = Integer.parseInt(txtBaseqty.getText());
        double price = Double.parseDouble(txtBasePrice.getText());

        BaseStockDto dto = new BaseStockDto(id, type, name, size, qty, price);

        try {
            boolean isSaved = model.saveBase(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Base saved successfully.").show();
                clearBases();
            } else {
                new Alert(Alert.AlertType.ERROR, "Base not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtBaseId.getText();
        String type = txtBaseType.getText();
        String name = (String) cmbSupplierName.getValue();
        String size = txtBaseSize.getText();
        int qty = Integer.parseInt(txtBaseqty.getText());
        double price = Double.parseDouble(txtBasePrice.getText());

        BaseStockDto dto = new BaseStockDto(id, type, name, size, qty, price);

        try {
            boolean isUpdated = model.updateBase(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Base updated successfully.").show();
                clearBases();
            } else {
                new Alert(Alert.AlertType.ERROR, "Base not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void txtBaseSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtBaseId.getText();

        try {
            BaseStockDto dto = model.searchBase(id);

            if (dto != null) {
                txtBaseId.setText(dto.getId());
                txtBaseType.setText(dto.getType());
                cmbSupplierName.setValue(dto.getSupName());
                txtBaseSize.setText(dto.getSize());
                txtBaseqty.setText(String.valueOf(dto.getQty()));
                txtBasePrice.setText(String.valueOf(dto.getPrice()));
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Base not found!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

}
