package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for handling adding a new subject to the
 *  database and UI for add subject fxml page
 */
public class AddSubjectController {

    private SubjectManager manager;

    @FXML
    private TextField unitCode;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ComboBox<String> semesterComboBox;

    @FXML
    private void initialize() {
        loadSemesters();
    }

    // Constructor for initialising subjectManager
    public AddSubjectController(){
        this.manager = new SubjectManager(new SqliteSubjectDAO());
    }

    // Method for loading semester values into combobox
    private void loadSemesters() {
        semesterComboBox.setItems(FXCollections.observableArrayList(getAvailableSemesters()));
    }

    private List<String> getAvailableSemesters() {
        List<String> subjectNames = new ArrayList<>();
        subjectNames.add("Semester 1");
        subjectNames.add("Semester 2");
        subjectNames.add("Summer Semester");
        return subjectNames;
    }

    // Method for handling when user submits information to create a new subject
    @FXML
    private void onSubmit() {
        try {
            Subject subject = createSubjectFromInput();
            manager.addSubject(subject);
            closeWindow();
        } catch (Exception error) {
            error.printStackTrace();
            showErrorPopup(error.getMessage());
        }
    }

    private Subject createSubjectFromInput() throws Exception {
        String subjectName = nameField.getText();
        String subjectDescription = descriptionArea.getText();
        String semester = semesterComboBox.getValue();
        String unitCodeString = unitCode.getText();

        if (subjectName.isEmpty() || subjectDescription.isEmpty() || semester == null) {
            throw new Exception("Please fill all the input fields");
        }

        int semesterNumber = mapSemesterToNumber(semester);
        return new Subject(semesterNumber, unitCodeString, subjectName, subjectDescription);
    }

    private int mapSemesterToNumber(String semester) {
        switch (semester) {
            case "Semester 1":
                return 1;
            case "Semester 2":
                return 2;
            default:
                return 3;
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    // Method for displaing an error messsage
    private void showErrorPopup(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("error.fxml"));
            Parent root = loader.load();
            ErrorController controller = loader.getController();
            controller.setErrorMessage(message);
            Stage stage = new Stage();
            // Set modality to application modal to block any inputs during error popup
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
