package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.SqliteUserDAO;
import com.example.cab302project.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;

public class RegisterController {

    private SqliteUserDAO Connection = new SqliteUserDAO();

    @FXML
    private Text registerTitle;

    @FXML
    private Button submitButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button loginButton;

    @FXML
    private Text warningLabel;

    @FXML
    private void Submit()
    {
        try{
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String userName = usernameField.getText();
            String password = passwordField.getText();

<<<<<<< Updated upstream
                User user = new User(firstName, lastName, userName, password);
                Connection.addUser(user);

            if(!Connection.userUnique)
            {
                warningLabel.setText("Username must be Unique.");
            }
            else{
                warningLabel.setText("Successfully Registered.");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
=======
        if(userName.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || password.isEmpty())
        {
            warningLabel.setText("All fields must be filled.");
        }

        else
        {
            User user = new User(firstName, lastName, userName, password);

            Connection.addUser(user);

            if(Connection.userUnique)
            {
                warningLabel.setText("Successfully Registered.");
            }
            else
            {
                warningLabel.setText("User already exists.");
            }
>>>>>>> Stashed changes
        }

    }
    @FXML
    private void Login() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-ui.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) loginButton.getScene().getWindow();

            currentStage.setScene(newScene);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}