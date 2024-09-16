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

import java.io.IOException;


public class LoginController {
    private SqliteUserDAO Connection = new SqliteUserDAO();

    @FXML
    private TextField usernameField;

    @FXML
    private Text warningLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private void Submit()
    {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        boolean userExists = Connection.userExists(userName.toLowerCase());
        boolean passwordCorrect = Connection.passwordCorrect(userName, password);
        if(userExists)
        {
            if(passwordCorrect)
            {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home.fxml"));
                    Scene newScene = new Scene(fxmlLoader.load());
                    Stage currentStage = (Stage) submitButton.getScene().getWindow();

                    currentStage.setScene(newScene);
                    currentStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                warningLabel.setText("Password Is Incorrect.");
            }
        }
        else
        {
            warningLabel.setText("User Does Not Exist. Please Register First.");
        }
    }

}