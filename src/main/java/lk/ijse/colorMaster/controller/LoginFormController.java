package lk.ijse.colorMaster.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.colorMaster.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;

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
    void btnLoginOnAction(ActionEvent event) throws IOException, SQLException {
        String userName = txtName.getText();
        String password = txtPw.getText();

        if (userName.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Fill All Fields").show();
            txtName.requestFocus();
            return;
        }

        // Check if the user exists in the database
        if (LoginModel.validateUser(userName, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login successful!");

            // Event handler for the default "OK" button
            alert.setOnHidden(e -> {
                try {
                    // Load the dashboard content
                    Parent dashboard = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
                    Scene dashboardScene = new Scene(dashboard);

                    // Get the current stage (login window)
                    Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                    currentStage.setTitle("Dash Board Form");
                    currentStage.setScene(dashboardScene);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            alert.show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            txtName.clear();
            txtPw.clear();
            txtName.requestFocus();
        }
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
