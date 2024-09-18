package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.example.cab302project.model.User;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


public class LoginController {
    private SqliteUserDAO Connection = new SqliteUserDAO();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button registerButton;

    @FXML
    private Text warningLabel;

    @FXML
    private void Register()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("register-ui.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) registerButton.getScene().getWindow();

            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Submit()
    {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        assertEquals("X", userName);
        assertEquals("X", password);
        if(userName.isEmpty() || password.isEmpty())
        {
            warningLabel.setText("All fields must be filled.");
        }
        else
        {
            boolean userExists = Connection.userExists(userName.toLowerCase());
            if(userExists)
            {
                boolean passwordCorrect = Connection.passwordCorrect(userName, password);
                if(passwordCorrect)
                {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
                        Scene newScene = new Scene(fxmlLoader.load(), 800, 600);
                        Stage currentStage = (Stage) submitButton.getScene().getWindow();
                        MainController controller = fxmlLoader.getController();

                        controller.setUsername(userName);
                        controller.setNavBar();
                        controller.loadPage("home.fxml");

                        currentStage.setScene(newScene);
                        currentStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    warningLabel.setText("The password you have entered is wrong.");
                }
            }
            else
            {
                warningLabel.setText("user " + userName + " does not exist.");
            }
        }
    }

}