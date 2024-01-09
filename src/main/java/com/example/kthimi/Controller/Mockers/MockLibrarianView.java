package com.example.kthimi.Controller.Mockers;
//
//import com.example.kthimi.Model.BookModel;
//import com.example.kthimi.View.Librarian.LibrarianView;
//
//import java.util.ArrayList;
//
//public class MockLibrarianView extends LibrarianView {
//    public MockLibrarianView() {
//        super(new MockStockBookRepository()); // Use your custom repository
//    }
//
//
//}


import com.example.kthimi.Controller.Mockers.ILibrarianView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MockLibrarianView implements ILibrarianView {
    private ComboBox<String> comboBoxLibrarian;
    private TextField quantityField;
    private Button bttAdd;
    private TextField warningsLibrarian;
    private BorderPane librarianMainPage;

    public MockLibrarianView() {
        // Initialize mock components
        comboBoxLibrarian = new ComboBox<>();
        quantityField = new TextField();
        bttAdd = new Button();
        warningsLibrarian = new TextField();
        librarianMainPage = new BorderPane();
        // Other necessary initialization
    }

    @Override
    public ComboBox<String> getComboBoxLibrarian() {
        return comboBoxLibrarian;
    }

    @Override
    public TextField getQuantityField() {
        return quantityField;
    }

    @Override
    public Button getBttAdd() {
        return bttAdd;
    }

    @Override
    public TextField getWarningsLibrarian() {
        return warningsLibrarian;
    }

    @Override
    public BorderPane getLibrarianMainPage() {
        return librarianMainPage;
    }

    // Implement other methods as needed for testing
}

