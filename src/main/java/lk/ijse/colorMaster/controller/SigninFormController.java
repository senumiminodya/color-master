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

public class SigninFormController {
    @FXML
    private JFXButton signInBtn;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label txtLogIn;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPw;

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

    @FXML
    void txtLogInOnAction(MouseEvent event) throws IOException {
        Stage window = (Stage)txtLogIn.getScene().getWindow();
        window.close();

        Parent load = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log In Form");
        stage.show();
    }

}
