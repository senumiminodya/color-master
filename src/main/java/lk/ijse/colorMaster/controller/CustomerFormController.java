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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.colorMaster.dao.CustomerDAOImpl;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.dto.tm.CustomerTm;
import lk.ijse.colorMaster.model.CustomerModel;
import lk.ijse.colorMaster.util.Regex;
import lk.ijse.colorMaster.util.TextFields;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

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
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton customerReportBtn;

    //private CustomerModel model = new CustomerModel();
    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    private AnchorPane root;

    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try {
            //CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            List<CustomerDto> allCustomerDto = customerDAO.getAllCustomer();
            for (CustomerDto dto : allCustomerDto) {
                obList.add(
                        new CustomerTm(
                            dto.getId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getPhoneNo()
                        )
                );
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
    }

    @FXML
    void customerSearchOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String telNo = txtPhoneNo.getText();

        try {
            //CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            CustomerDto dto = customerDAO.searchCustomer(id);

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
            //CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            boolean isDeleted = customerDAO.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted successfully.").show();
                setCellValueFactory();
            } else {
                new Alert(Alert.AlertType.ERROR,"Customer not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        if (!validate()){
            new Alert(Alert.AlertType.ERROR,"Invalid Input").show();
            return;
        }

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phoneNo = txtPhoneNo.getText();

        CustomerDto dto = new CustomerDto(id, name, address, phoneNo);

        try {
            //CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            boolean isSaved = customerDAO.saveCustomer(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer saved successfully.").show();
                clearCustomer();
                setCellValueFactory();
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
            //CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            boolean isUpdated = customerDAO.updateCustomer(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer updated successfully.").show();
                clearCustomer();
                setCellValueFactory();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    public void txtCustomerIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ID, txtId);
    }

    public void txtCustomerPhoneOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE, txtPhoneNo);
    }

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME, txtName);
    }

    public void txtCustomerAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }


    public boolean validate(){
        return Regex.setTextColor(TextFields.ID, txtId)
                && Regex.setTextColor(TextFields.NAME, txtName)
                && Regex.setTextColor(TextFields.PHONE, txtPhoneNo)
                && Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    @FXML
    void btnViewCustomerOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/customer_details.jrxml");
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


}
