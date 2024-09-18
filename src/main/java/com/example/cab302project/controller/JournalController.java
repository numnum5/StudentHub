package com.example.cab302project.controller;


import com.example.cab302project.model.JournalDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JournalController implements IController
{
    private JournalDAO connection = new JournalDAO();

    @FXML
    private Button viewEntry;

    private MainController mainController;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }

    @FXML
    public TextArea MoodText;

    @FXML
    public TextArea JournalText;

    @FXML
    private ListView<String> entriesListView;


    public void Submit() {
        String moodString = MoodText.getText();
        String entryString = JournalText.getText();
        String userName = mainController.getUsername();
        // All Inputs are X.
        assertEquals("X", moodString);
        assertEquals("X", entryString);
        connection.addEntry(entryString, moodString,userName);
    }

    @FXML
    public void viewEntry() {
        String userName = mainController.getUsername();

        ObservableList<String> entries = FXCollections.observableArrayList(connection.getAllEntries(userName));
        entriesListView.setItems(entries);
    }
}
