package lk.ijse.colorMaster.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.colorMaster.dao.custom.CustomerDAOImpl;
import lk.ijse.colorMaster.dao.custom.OrdersDAOImpl;
import lk.ijse.colorMaster.dao.custom.PaintStockDAOImpl;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.dto.OrderDto;
import lk.ijse.colorMaster.dto.OrderPaintDetailsDTO;
import lk.ijse.colorMaster.dto.PaintStockDto;
import lk.ijse.colorMaster.dto.tm.CartTm;
//import lk.ijse.colorMaster.model.CustomerModel;
//import lk.ijse.colorMaster.model.OrderModel;
//import lk.ijse.colorMaster.model.PaintStockModel;
import lk.ijse.colorMaster.util.Regex;
import lk.ijse.colorMaster.util.TextFields;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersFormController {
    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXComboBox<CustomerDto> cmbCustomerId;

    @FXML
    private JFXComboBox<PaintStockDto> cmbItemCode;

    @FXML
    private TableColumn<CartTm,Button> col_action;

    @FXML
    private TableColumn<CartTm,String> col_description;

    @FXML
    private TableColumn<CartTm,String> col_item_code;

    @FXML
    private TableColumn<CartTm,Integer> col_qty;

    @FXML
    private TableColumn<CartTm,Double> col_total;

    @FXML
    private TableColumn<CartTm,Double> col_unit_price;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private JFXButton placeOrderBtn;

    @FXML
    private TableView<CartTm> tblOrder;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton viewOrderBtn;

    //private CustomerModel customerModel = new CustomerModel();
    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    //private PaintStockModel itemModel = new PaintStockModel();
    private PaintStockDAOImpl paintStockDAO = new PaintStockDAOImpl();
    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        generateNextOrderId();
        setDate();
        loadCustomerIds();
        loadItemIds();
        setCellValues();

        cmbCustomerId.setConverter(new StringConverter<CustomerDto>() {
            @Override
            public String toString(CustomerDto dto) {
                return dto==null?"":dto.getId();
            }

            @Override
            public CustomerDto fromString(String s) {
                return null;
            }
        });

        cmbItemCode.setConverter(new StringConverter<PaintStockDto>() {
            @Override
            public String toString(PaintStockDto dto) {
                return dto==null?"":dto.getId();
            }

            @Override
            public PaintStockDto fromString(String s) {
                return null;
            }
        });

    }

    private void loadCustomerIds() {
        ObservableList<CustomerDto> obList = FXCollections.observableArrayList();

        try {
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            List<CustomerDto> idList = customerDAO.getAllCustomer();

            for (CustomerDto dto : idList) {
                obList.add(dto);
            }

            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadItemIds() {
        ObservableList<PaintStockDto> obList = FXCollections.observableArrayList();

        try {
            List<PaintStockDto> idList = paintStockDAO.getAllPaints();

            for (PaintStockDto dto : idList) {
                obList.add(dto);
            }

            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //private OrderModel orderModel = new OrderModel();
    private OrdersDAOImpl ordersDAO = new OrdersDAOImpl();
    private void generateNextOrderId() {
        try {
            String orderId = ordersDAO.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setDate() {
          //LocalDate now = LocalDate.now();
          lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Customer Form");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)lblOrderId.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.show();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String customerId = cmbCustomerId.getValue().getId();
        String orderId = lblOrderId.getText();
        List<OrderPaintDetailsDTO> list = new ArrayList<>();

        for (CartTm item : tblOrder.getItems()) {
            OrderPaintDetailsDTO orderPaintDetailsDTO = new OrderPaintDetailsDTO();
            orderPaintDetailsDTO.setOrderNo(orderId);
            orderPaintDetailsDTO.setPaintId(item.getId());
            orderPaintDetailsDTO.setQty(item.getQty());
            list.add(orderPaintDetailsDTO);
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(orderId);
        orderDto.setCustomerId(customerId);
        orderDto.setDate(Date.valueOf(LocalDate.now()));
        orderDto.setCartTmList(list);
        orderDto.setTotal(calculateTotal());

        try {
            boolean isOrderPlaced = ordersDAO.saveOrderDetails(orderDto);
            if (isOrderPlaced) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully").show();
                clearFields();
                generateNextOrderId();
                loadItemIds();
                loadCustomerIds();
                cmbCustomerId.getSelectionModel().clearSelection();
                lblCustomerName.setText("");
                tblOrder.getItems().clear();
            }else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
        //setRemoveBtnOnAction(removeBtn);
    }

    private void setRemoveBtnOnAction(Button btn,CartTm ob) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                tblOrder.getItems().remove(ob);
                tblOrder.refresh();
                for (PaintStockDto item : cmbItemCode.getItems()) {
                    if (item.getId().equals(ob.getId())) {
                        item.setQty(item.getQty() + ob.getQty());
                    }
                }
                calculateTotal();
            }
        });
    }

    private double calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            total += (double) col_total.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
        return total;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CartTm cartTm = new CartTm();
        PaintStockDto selectedItem = cmbItemCode.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.ERROR, "Please select an item").show();
            return;
        }
        if (selectedItem.getQty()-Integer.parseInt(txtQty.getText())<0) {
            new Alert(Alert.AlertType.ERROR, "Out of Stock").show();
            return;
        }
        selectedItem.setQty(selectedItem.getQty()-Integer.parseInt(txtQty.getText()));
        if (selectedItem == null) {
            return;
        }

        for (CartTm item : tblOrder.getItems()) {
            if (item.getId().equals(selectedItem.getId())) {
                item.setQty(item.getQty()+Integer.parseInt(txtQty.getText()));
                item.setTotal(item.getPrice()*item.getQty());
                clearFields();
                calculateTotal();
                tblOrder.refresh();
                return;
            }
        }

        cartTm.setId(selectedItem.getId());
        cartTm.setName(selectedItem.getName());
        cartTm.setPrice(selectedItem.getPrice());
        cartTm.setQty(Integer.parseInt(txtQty.getText()));
        cartTm.setTotal(cartTm.getPrice() * cartTm.getQty());
        cartTm.setAction(new Button("Remove"));
        setRemoveBtnOnAction(cartTm.getAction(),cartTm);
        tblOrder.getItems().add(cartTm);
        calculateTotal();
        clearFields();

    }

    public void clearFields(){
        lblDescription.setText("");
        lblUnitPrice.setText("");
        lblQtyOnHand.setText("");
        txtQty.setText("");
        cmbItemCode.getSelectionModel().clearSelection();

    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        CustomerDto selectedItem = cmbCustomerId.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        lblCustomerName.setText(selectedItem.getName());

    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        PaintStockDto selectedItem = cmbItemCode.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        lblDescription.setText(selectedItem.getName());
        lblUnitPrice.setText(String.valueOf(selectedItem.getPrice()));
        lblQtyOnHand.setText(String.valueOf(selectedItem.getQty()));
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    public void setCellValues(){
        col_item_code.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_unit_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_action.setCellValueFactory(new PropertyValueFactory<>("action"));
    }
    @FXML
    void btnViewOrdersOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/order_details.jrxml");
        JasperDesign load;
        load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport, //compiled report
                null,
                DbConnection.getInstance().getConnection() //database connection
        );

        JasperViewer.viewReport(jasperPrint, false);
    }

    @FXML
    void txtQtyOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.INTEGER, txtQty);
    }

}
