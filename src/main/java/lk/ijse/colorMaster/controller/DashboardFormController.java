package lk.ijse.colorMaster.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.colorMaster.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class DashboardFormController {
    @FXML
    private JFXButton baseStockBtn;

    @FXML
    private JFXButton customerBtn;

    @FXML
    private JFXButton deliveryBtn;

    @FXML
    private JFXButton driverBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private JFXButton ordersBtn;

    @FXML
    private JFXButton paintStockBtn;

    @FXML
    private JFXButton supplierBtn;

    @FXML
    void btnBaseStockOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)customerBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/baseStock_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Bases Form");
        stage.show();
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)customerBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Customer Form");
        stage.show();
    }

    @FXML
    void btnDeliveryOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)customerBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/delivery_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Delivery Form");
        stage.show();
    }

    @FXML
    void btnDriverOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)customerBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/driver_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Driver Form");
        stage.show();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)customerBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log In Form");
        stage.show();
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)customerBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/orders_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Order Form");
        stage.show();
    }

    @FXML
    void btnPaintStockOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)customerBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/paintStock_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Paint Item Form");
        stage.show();
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)customerBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Supplier Form");
        stage.show();
    }

    @FXML
    void viewCustomerBtnOnAction(ActionEvent event) throws JRException, SQLException {
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

    @FXML
    void viewDriversBtnOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/driver_details.jrxml");
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
    void viewOrdersBtnOnAction(ActionEvent event) throws JRException, SQLException {
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
    void viewPaintsBtnOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/paint_details.jrxml");
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
    void viewVehiclesBtnOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/vehicle_details.jrxml");
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
