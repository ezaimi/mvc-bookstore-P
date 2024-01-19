package unitTesting;

import com.example.kthimi.Controller.AdminFuncController;
import com.example.kthimi.Controller.BookController;
import com.example.kthimi.Controller.LibrarianFuncController;
import com.example.kthimi.Controller.Mockers.FileBasedStockBookRepository;
import com.example.kthimi.Model.AdministratorModel;
import com.example.kthimi.Model.BookModel;
import com.example.kthimi.Model.LibrarianModel;
import com.example.kthimi.Model.ManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    private static String TEMP_STOCK_FILE_PATH = "tempStockFile.bin";
    private static final String TEST_ISBN = "1234567890123";
    private static final String TEST_TITLE = "Test Book";

    ArrayList<ManagerModel> magList = new ArrayList<>();


    public static void setStockFilePath(String newPath) {
        try {
            // Change the STOCK_FILE_PATH field in BookController class
            changeField(BookController.class, "STOCK_FILE_PATH", newPath);
            changeField(LibrarianModel.class, "STOCK_FILE_PATH", newPath);
            changeField(LibrarianFuncController.class, "STOCK_FILE_PATH", newPath);
            changeField(FileBasedStockBookRepository.class, "STOCK_FILE_PATH", newPath);
            // Add more classes and fields to change here as necessary
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void changeField(Class<?> targetClass, String fieldName, String newValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = targetClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, newValue);
    }

    private BookModel createTestBook() {
        return new BookModel(TEST_ISBN, TEST_TITLE, "Category1", "Test Publisher", 20.00, 25.00, "Test Author", 10);
    }

    @BeforeEach
    public void setUp() {
        AdminTest.setStockFilePath(TEMP_STOCK_FILE_PATH);
        // Create a temporary file for testing
        createTemporaryFile();
    }

    private void createTemporaryFile() {
        try (ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(TEMP_STOCK_FILE_PATH))) {
            // Write initial data to the temporary file if needed for setup
            // For instance:
            BookModel book = createTestBook();
            objout.writeObject(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testInstantiateManagers() {
        AdministratorModel.getManagers().clear();
        AdministratorModel.InstantiateManagers();
        ArrayList<ManagerModel> managers = AdministratorModel.getManagers();


        assertNotNull(managers);
        assertEquals(3, managers.size());


        ManagerModel firstManager = managers.get(0);
        assertEquals("Calv1n", firstManager.getUsername());
        assertEquals("PQ532Ayba", firstManager.getPassword());
        assertEquals("Calvin", firstManager.getName());
        assertEquals(900, firstManager.getSalary());
        assertEquals("(912) 561-2628", firstManager.getPhone());
        assertEquals("calvl@manager.com", firstManager.getEmail());

    }

    @Test
    public void testAddManager() {
        ManagerModel newManager = new ManagerModel("NewManager", "NewPassword", "NewName", 1000, "(123) 456-7890", "new@example.com");
        AdministratorModel.InstantiateManagers();

        int initialSize = AdministratorModel.getManagers().size();

        AdministratorModel.AddManager(newManager);

        int updatedSize = AdministratorModel.getManagers().size();

        assertEquals(initialSize + 1, updatedSize);
        assertTrue(AdministratorModel.getManagers().contains(newManager));

    }

    @Test
    public void testManagerChecker() {
        AdministratorModel administrator = new AdministratorModel("adminUsername", "adminPassword");

        AdministratorModel.InstantiateManagers();

        assertTrue(AdminFuncController.ManagerChecker(new ManagerModel("Calv1n", "PQ532Ayba")));
        assertTrue(AdminFuncController.ManagerChecker(new ManagerModel("Lui54", "y@.3FYrn")));

        assertFalse(AdminFuncController.ManagerChecker(new ManagerModel("invalidUser", "invalidPassword")));
        assertFalse(AdminFuncController.ManagerChecker(new ManagerModel("Calv1n", "invalidPassword")));
        assertFalse(AdminFuncController.ManagerChecker(new ManagerModel("invalidUser", "PQ532Ayba")));
    }

    @Test
    void testManagerCheckerInvalid() {
        ManagerModel invalidManager = new ManagerModel("invalid", "password");

        assertFalse(AdminFuncController.ManagerChecker(invalidManager));
    }

    @Test
    public void testChecker() {
        AdminFuncController.InstantiateManagers();

        assertTrue(AdminFuncController.checker("J0sh", "&zsX6QVZ"));
        assertTrue(AdminFuncController.checker("1", "3"));

        assertFalse(AdminFuncController.checker("invalidUser", "invalidPassword"));
        assertFalse(AdminFuncController.checker("J0sh", "invalidPassword"));
        assertFalse(AdminFuncController.checker("invalidUser", "&zsX6QVZ"));
    }

    @Test
    public void testGetBackManager() {
        ArrayList<ManagerModel> managerList = new ArrayList<>();
        ArrayList<ManagerModel>emptyList=new ArrayList<>();

        ManagerModel mag1 = new ManagerModel("Calv1n", "PQ532Ayba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        managerList.add(mag1);
        ManagerModel mag2 = new ManagerModel("Lui54", "y@.3FYrn", "Lui", 900, "(912) 218-2594", "lu@manager.com");
        managerList.add(mag2);
        ManagerModel mag3 = new ManagerModel("1", "2", "TestManager", 900, "(912) 623-5353", "TestEmail@librarian.com");
        managerList.add(mag3);
        assertEquals(managerList.size(),3);

        AdministratorModel administrator = new AdministratorModel("1", "3");

        ManagerModel foundManager = AdminFuncController.getBackManager(mag2,managerList);


        assertEquals("Lui54", foundManager.getUsername());
        assertEquals("y@.3FYrn", foundManager.getPassword());
        assertNull(AdminFuncController.getBackManager(mag1,emptyList));
    }

    @Test
    public void testGetManagers() {

        AdministratorModel administrator = new AdministratorModel("1", "3");

        ArrayList<ManagerModel> returnedManagers = AdministratorModel.getManagers();
    }

    @Test
    public void testDeleteManager() {

        ArrayList<ManagerModel> managerList = new ArrayList<>();
        ManagerModel mag1 = new ManagerModel("Calv1n", "PQ532Ayba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        managerList.add(mag1);
        ManagerModel mag2 = new ManagerModel("Lui54", "y@.3FYrn", "Lui", 900, "(912) 218-2594", "lu@manager.com");
        managerList.add(mag2);
        ManagerModel mag3 = new ManagerModel("1", "2", "TestManager", 900, "(912) 623-5353", "TestEmail@librarian.com");
        managerList.add(mag3);

        AdministratorModel administrator = new AdministratorModel();

        AdminFuncController.deleteManager(mag2,managerList);
        //System.out.println(managerList);

        assertEquals(2,managerList.size());
        assertFalse(AdministratorModel.getManagers().contains(mag2));
    }

    @Test
    public void testUpdateManagers() {
        // Arrange
        ArrayList<ManagerModel> managerList = new ArrayList<>();
        ManagerModel mag1 = new ManagerModel("Calv1n", "PQ532Ayba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        managerList.add(mag1);
        ManagerModel mag2 = new ManagerModel("Lui54", "y@.3FYrn", "Lui", 900, "(912) 218-2594", "lu@manager.com");
        managerList.add(mag2);
        ManagerModel mag3 = new ManagerModel("1", "2", "TestManager", 900, "(912) 623-5353", "TestEmail@librarian.com");
        managerList.add(mag3);

        ManagerModel updatedManager = new ManagerModel("Lui54", "updatedPassword", "UpdatedLui", 1000, "(912) 999-9999", "updatedlu@manager.com");

        AdminFuncController.updateManagers(updatedManager, managerList);

        assertEquals("updatedPassword", mag2.getPassword());
        assertEquals(1000, mag2.getSalary());
        assertEquals("(912) 999-9999", mag2.getPhone());
        assertEquals("updatedlu@manager.com", mag2.getEmail());
    }




}
