//package controllerTest;
//import com.example.kthimi.Controller.AddLibrarianController;
//import com.example.kthimi.View.Librarian.LibrarianView;
//import com.example.kthimi.View.MainView;
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextField;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AddLibrarianControllerTest {
//
//    private AddLibrarianController librarianController;
//    private LibrarianView librarianView;
//
//    @BeforeEach
//    public void setUp() {
//        librarianView = new LibrarianView("TestUser", new MainView());
//        librarianController = new AddLibrarianController(librarianView);
//    }
//
//    @Test
//    public void testEmptyFieldsScenario() {
//        // Set up the view fields to be empty
//// Assuming you have a LibrarianView instance called librarianView
//        librarianView.comboBoxLibrarian.setItems(FXCollections.observableArrayList("ISBN1 - Title1", "ISBN2 - Title2", "ISBN3 - Title3"));
//        librarianView.quantity.setText(""); // Set an empty quantity field
//
//        // Trigger handleAdd()
//        librarianView.getBttAdd().fire();
//
//        // Validate the expected warning text
//        assertEquals("Failed, Empty fields", librarianView.warningsLibrarian.getText());
//    }
//
//    // Similar test methods for other scenarios (Empty ISBN, Empty Quantity, Invalid Quantity, Insufficient Stock)
//
//    // Implement the other test scenarios using librarianView to manipulate fields and trigger handleAdd()
//
//    // Additional test methods as needed
//}
