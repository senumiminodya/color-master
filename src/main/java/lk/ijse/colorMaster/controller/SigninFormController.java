package lk.ijse.colorMaster.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.colorMaster.bo.custom.impl.SigninFormBOImpl;
import lk.ijse.colorMaster.bo.custom.UserBO;
//import lk.ijse.colorMaster.model.SignInModel;
import lk.ijse.colorMaster.dto.UserDto;
import lk.ijse.colorMaster.util.Regex;
import lk.ijse.colorMaster.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;

//import static org.graalvm.compiler.options.OptionType.User;

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
    void btnSignInOnAction(ActionEvent event) throws SQLException {
        String userName = txtName.getText();
        String password = txtPw.getText();
        String email = txtEmail.getText();

        if (userName.isEmpty() || password.isEmpty() || email.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Fill All Fields").show();
            txtName.requestFocus();
            return;
        } else {
            // Save user in the database
            //UserDAOImpl userDAO = new UserDAOImpl();
            UserBO userBO = new SigninFormBOImpl();
            UserDto userDto = new UserDto(userName, password, email);
            try {
                if (userBO.saveUser(userDto)) {
                    new Alert(Alert.AlertType.INFORMATION, "User saved successfully!").show();
                    txtName.clear();
                    txtPw.clear();
                    txtEmail.clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error saving user to the database").show();
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
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

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.EMAIL, txtEmail);
    }

}
