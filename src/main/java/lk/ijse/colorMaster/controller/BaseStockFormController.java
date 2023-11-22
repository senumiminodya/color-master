package lk.ijse.colorMaster.controller;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.BaseStockDto;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.dto.SupplierDto;
import lk.ijse.colorMaster.dto.cm.BaseCm;
import lk.ijse.colorMaster.dto.cm.SupplierCm;
import lk.ijse.colorMaster.dto.tm.BaseStockTm;
import lk.ijse.colorMaster.dto.tm.CustomerTm;
import lk.ijse.colorMaster.dto.tm.SupplierTm;
import lk.ijse.colorMaster.model.BaseStockModel;
import lk.ijse.colorMaster.model.SupplierModel;
import lk.ijse.colorMaster.util.Regex;
import lk.ijse.colorMaster.util.TextFields;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseStockFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private ComboBox<SupplierCm> cmbSupplierName;

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
    private TableView<BaseStockTm> tblBases;

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
    private SupplierModel supModel = new SupplierModel();

    public void initialize() {
        cmbSupplierName.setConverter(new StringConverter<SupplierCm>() {
            @Override
            public String toString(SupplierCm supplierCm) {
                return supplierCm==null ? "" : supplierCm.getId()+" : "+supplierCm.getName();
            }

            @Override
            public SupplierCm fromString(String s) {
                return null;
            }
        });
        setComboBox();
        loadAllBases();
        setCellValueFactory();
    }

    private void loadAllBases() {
        ObservableList<BaseStockTm> obList = FXCollections.observableArrayList();
        try {
            List<BaseStockDto> allBaseDto = model.getAllBases();
            for (BaseStockDto dto : allBaseDto) {
                obList.add(
                        new BaseStockTm(
                                dto.getId(),
                                dto.getType(),
                                dto.getSupName(),
                                dto.getSize(),
                                dto.getQty(),
                                dto.getPrice()
                        )
                );
            }
            tblBases.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_baseType.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_SupplierName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        col_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        col_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

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
        String name = (String) cmbSupplierName.getValue().getId();
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
        String name = (String) cmbSupplierName.getValue().getId();
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
                //cmbSupplierName.setValue(dto.getSupName());
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

    public void setComboBox() {
        try {
            List<SupplierDto> allSup = supModel.getAllSupplier();
            System.out.println(allSup.size());
            ArrayList<SupplierCm> objects = new ArrayList<>();
            for (SupplierDto sup : allSup) {
                SupplierCm supCm = new SupplierCm();
                supCm.setId(sup.getId());
                supCm.setName(sup.getName());
                supCm.setPhoneNo(sup.getPhoneNo());
                supCm.setProduct(sup.getProduct());
                objects.add(supCm);
            }
            cmbSupplierName.setItems(FXCollections.observableArrayList(objects));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void cmbSupplierOnAction(ActionEvent event) {
        //String supName = cmbSupplierName.getValue();

    }

    @FXML
    void txtBaseIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.ID, txtBaseId);
    }

    @FXML
    void txtBasePriceOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.DOUBLE, txtBasePrice);
    }

    @FXML
    void txtBaseQtyOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.INTEGER, txtBaseqty);
    }
    @FXML
    void txtBaseSizeOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.INTEGER_SPACE_CHARACTER, txtBaseSize);
    }

    @FXML
    void txtBaseTypeOnKeyReleased(KeyEvent event) {

    }

}
