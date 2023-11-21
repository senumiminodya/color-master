package lk.ijse.colorMaster.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private JFXButton loginBtn;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPw;

    @FXML
    private Label txtSignIn;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage window = (Stage)loginBtn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dash Board Form");
        stage.show();
    }

    @FXML
    void txtSignInOnAction(MouseEvent event) throws IOException {
        Stage window = (Stage)txtSignIn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/signin_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign In Form");
        stage.show();
    }
}
