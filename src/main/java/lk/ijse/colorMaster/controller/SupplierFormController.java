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
import lk.ijse.colorMaster.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.colorMaster.dto.SupplierDto;
import lk.ijse.colorMaster.dto.tm.SupplierTm;
//import lk.ijse.colorMaster.model.SupplierModel;
import lk.ijse.colorMaster.util.Regex;
import lk.ijse.colorMaster.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class SupplierFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_phoneNo;

    @FXML
    private TableColumn<?, ?> col_product;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<SupplierTm> tblSupplier;

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

    //private SupplierModel model = new SupplierModel();
    private SupplierDAOImpl supplierDAO = new SupplierDAOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllSuppliers();
    }

    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDto> allSupplierDto = supplierDAO.getAll();
            for (SupplierDto dto : allSupplierDto) {
                obList.add(
                        new SupplierTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getPhoneNo(),
                                dto.getProduct()
                        )
                );
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        col_product.setCellValueFactory(new PropertyValueFactory<>("product"));
    }

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
            boolean isDeleted = supplierDAO.delete(id);
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
            boolean isSaved = supplierDAO.save(dto);
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
            boolean isUpdated = supplierDAO.update(dto);
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
            SupplierDto dto = supplierDAO.search(id);
            txtSupplierId.setText(dto.getId());
            txtSupplierName.setText(dto.getName());
            txtSupplierPhoneNo.setText(dto.getPhoneNo());
            txtSupplierProduct.setText(dto.getProduct());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }
    @FXML
    void txtSupplierIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.INTEGER, txtSupplierId);
    }

    @FXML
    void txtSupplierPhoneNoOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.PHONE, txtSupplierPhoneNo);
    }
}
