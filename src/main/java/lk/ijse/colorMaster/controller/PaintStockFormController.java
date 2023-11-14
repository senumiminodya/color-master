package lk.ijse.colorMaster.controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.colorMaster.dto.BaseStockDto;
import lk.ijse.colorMaster.dto.PaintStockDto;
import lk.ijse.colorMaster.model.PaintStockModel;

import java.io.IOException;
import java.sql.SQLException;

public class PaintStockFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private ComboBox<String> cmbBaseId;

    @FXML
    private ComboBox<String> cmbItemSize;

    @FXML
    private ComboBox<String> cmbItemType;

    @FXML
    private TableColumn<?, ?> col_ItemName;

    @FXML
    private TableColumn<?, ?> col_ItemType;

    @FXML
    private TableColumn<?, ?> col_baseId;

    @FXML
    private TableColumn<?, ?> col_itemId;

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
    private TableView<?> tblPaintItems;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtItemPrice;

    @FXML
    private TextField txtItemQty;

    @FXML
    private JFXButton updateBtn;

    private PaintStockModel model = new PaintStockModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)txtItemId.getScene().getWindow();
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
        clearPaints();
    }

    private void clearPaints() {
        txtItemId.clear();
        txtItemName.clear();
        cmbItemType.getSelectionModel().clearSelection();
        cmbItemSize.getSelectionModel().clearSelection();
        txtItemQty.clear();
        txtItemPrice.clear();
        cmbBaseId.getSelectionModel().clearSelection();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        try {
            boolean isDeleted = model.deletePaint(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Paint deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"Paint not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        String name = txtItemName.getText();
        String type = (String) cmbItemType.getValue();
        String size = (String) cmbItemSize.getValue();
        int qty = Integer.parseInt(txtItemQty.getText());
        double price = Double.parseDouble(txtItemPrice.getText());
        String baseId = (String) cmbBaseId.getValue();

        PaintStockDto dto = new PaintStockDto(id, name, type, size, qty, price, baseId);

        try {
            boolean isSaved = model.savePaint(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Paint saved successfully.").show();
                clearPaints();
            } else {
                new Alert(Alert.AlertType.ERROR, "Paint not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        String name = txtItemName.getText();
        String type = (String) cmbItemType.getValue();
        String size = (String) cmbItemSize.getValue();
        int qty = Integer.parseInt(txtItemQty.getText());
        double price = Double.parseDouble(txtItemPrice.getText());
        String baseId = (String) cmbBaseId.getValue();

        PaintStockDto dto = new PaintStockDto(id, name, type, size, qty, price, baseId);

        try {
            boolean isUpdated = model.updatePaint(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Paint updated successfully.").show();
                clearPaints();
            } else {
                new Alert(Alert.AlertType.ERROR, "Paint not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }


    @FXML
    void itemIdSearchOnAction(ActionEvent event) {
        String id = txtItemId.getText();

        try {
            PaintStockDto dto = model.searchPaint(id);

            if (dto != null) {
                txtItemId.setText(dto.getId());
                txtItemName.setText(dto.getName());
                cmbItemType.setValue(dto.getType());
                cmbItemSize.setValue(dto.getType());
                txtItemQty.setText(String.valueOf(dto.getQty()));
                txtItemPrice.setText(String.valueOf(dto.getPrice()));
                cmbBaseId.setValue(dto.getBaseId());
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Paint not found!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }
}
