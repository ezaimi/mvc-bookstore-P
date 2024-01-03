//package controllerTest;
//
//import com.example.kthimi.Controller.MainController;
//import com.example.kthimi.Controller.Stubs.MainViewStub;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class MainControllerTest {
//
//    private MainViewStub viewStub;
//    private MainController mainController;
//
//    @BeforeEach
//    void setUp() {
//        viewStub = new MainViewStub();
//        mainController = new MainController(viewStub);
//    }
//
//    @Test
//    void testHandleLogin_EmptyFields() {
//        // Set up the view with empty fields
//        viewStub.setUsernameText("");
//        viewStub.setPasswordText("");
//
//        mainController.handleLogin();
//
//        // Assert that the warning message is set for empty fields
//        assertEquals("Empty Fields", viewStub.getMainLoginWarningText());
//        // Assert that clearFields() is called
//        assertTrue(viewStub.isClearFieldsCalled());
//        // Ensure other methods are not called
//        assertFalse(viewStub.isPrimaryStageCalled());
//    }
//
//    // Add more tests for other scenarios like authentication success for librarian, manager, admin, etc.
//}
