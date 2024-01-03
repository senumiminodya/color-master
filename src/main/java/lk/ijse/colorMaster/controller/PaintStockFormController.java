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
import lk.ijse.colorMaster.dao.BaseStockDAOImpl;
import lk.ijse.colorMaster.dao.PaintStockDAOImpl;
import lk.ijse.colorMaster.dto.BaseStockDto;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.dto.PaintStockDto;
import lk.ijse.colorMaster.dto.cm.BaseCm;
import lk.ijse.colorMaster.dto.cm.SupplierCm;
import lk.ijse.colorMaster.dto.tm.CustomerTm;
import lk.ijse.colorMaster.dto.tm.ItemTm;
import lk.ijse.colorMaster.model.BaseStockModel;
import lk.ijse.colorMaster.model.PaintStockModel;
import lk.ijse.colorMaster.util.Regex;
import lk.ijse.colorMaster.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintStockFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private ComboBox<BaseCm> cmbBaseId;

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
    private TableView<ItemTm> tblPaintItems;

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

    //private PaintStockModel model = new PaintStockModel();
    private PaintStockDAOImpl paintStockDAO = new PaintStockDAOImpl();
    //private BaseStockModel baseStockModel = new BaseStockModel();
    private BaseStockDAOImpl baseStockDAO = new BaseStockDAOImpl();

    public void initialize() {
        cmbBaseId.setConverter(new StringConverter<BaseCm>() {
            @Override
            public String toString(BaseCm baseCm) {
                return baseCm==null ? "" : baseCm.getId();
            }

            @Override
            public BaseCm fromString(String s) {
                return null;
            }
        });
        setComboBox();
        setItemTypeComboBox();
        setItemSizeComboBox();
        loadAllBaseIds();
        loadAllItems();
        setCellValueFactory();
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        try {
            List<PaintStockDto> allPaintDto = paintStockDAO.getAllPaints();
            for (PaintStockDto dto : allPaintDto) {
                obList.add(
                        new ItemTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getType(),
                                dto.getSize(),
                                dto.getQty(),
                                dto.getPrice(),
                                dto.getBaseId()
                        )
                );
            }
            tblPaintItems.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        col_itemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_ItemType.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        col_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_baseId.setCellValueFactory(new PropertyValueFactory<>("baseId"));
    }

    private void loadAllBaseIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<BaseStockDto> idList = baseStockDAO.getAllBases();

            /*for (BaseStockDto dto : idList) {
                obList.add(dto.getId());
            }*/

            //cmbBaseId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
            boolean isDeleted = paintStockDAO.deletePaint(id);
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
        String baseId = cmbBaseId.getValue().getId();

        PaintStockDto dto = new PaintStockDto(id, name, type, baseId, size, qty, price);

        try {
            boolean isSaved = paintStockDAO.savePaint(dto);
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
        String baseId =  cmbBaseId.getValue().getId();

        PaintStockDto dto = new PaintStockDto(id, name, type, baseId, size, qty, price);

        try {
            boolean isUpdated = paintStockDAO.updatePaint(dto);
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
            PaintStockDto dto = paintStockDAO.searchPaint(id);

            if (dto != null) {
                txtItemId.setText(dto.getId());
                txtItemName.setText(dto.getName());
                cmbItemType.setValue(dto.getType());
                cmbItemSize.setValue(dto.getSize());
                txtItemQty.setText(String.valueOf(dto.getQty()));
                txtItemPrice.setText(String.valueOf(dto.getPrice()));
                //cmbBaseId.setValue(dto.getBaseId());
                for (int i = 0; i < cmbBaseId.getItems().size(); i++) {
                    if (cmbBaseId.getItems().get(i).getId().equals(dto.getBaseId())) {
                        cmbBaseId.getSelectionModel().select(i);
                        break;
                    }
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Paint not found!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    public void setComboBox() {
        try {
            List<BaseStockDto> allBases = baseStockDAO.getAllBases();
            ArrayList<BaseCm> objects = new ArrayList<>();
            for (BaseStockDto ob : allBases) {
                BaseCm baseCm = new BaseCm();
                baseCm.setId(ob.getId());
                baseCm.setType(ob.getType());
                baseCm.setSupName(ob.getSupName());
                baseCm.setSize(ob.getSize());
                baseCm.setQty(ob.getQty());
                baseCm.setPrice(ob.getPrice());
                objects.add(baseCm);
            }
            cmbBaseId.setItems(FXCollections.observableArrayList(objects));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setItemTypeComboBox() {
        String[] itemType = {"Pentalite", "Weathershield"};
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add(itemType[0]);
        obList.add(itemType[1]);
        cmbItemType.setItems(obList);
    }

    public void setItemSizeComboBox() {
        String[] itemType = {"1 L", "4 L", "10 L", "20 L"};
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add(itemType[0]);
        obList.add(itemType[1]);
        obList.add(itemType[2]);
        obList.add(itemType[3]);
        cmbItemSize.setItems(obList);
    }
    @FXML
    void cmbBaseIdOnAction(ActionEvent event) throws SQLException {

    }
    @FXML
    void txtItemIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.ID, txtItemId);
    }

    @FXML
    void txtItemNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.NAME, txtItemName);
    }

    @FXML
    void txtItemPriceOnkeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.DOUBLE, txtItemPrice);
    }

    @FXML
    void txtQtyOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.INTEGER, txtItemQty);
    }

}
