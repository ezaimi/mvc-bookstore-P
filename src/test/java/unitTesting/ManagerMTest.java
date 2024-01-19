package unitTesting;

import com.example.kthimi.Controller.BookController;
import com.example.kthimi.Controller.ManagerFuncController;
import com.example.kthimi.Controller.Mockers.MockStockBookRepository;
import com.example.kthimi.Model.BookModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerMTest {

    @Test
    public void testGetLowStock() {
        // Create a mock repository
        MockStockBookRepository mockRepository = new MockStockBookRepository();

        // Create a BookController instance with the mock repository
        BookController mockBookController = new BookController(mockRepository);

        // Create a ManagerFuncController instance with the mock BookController
        ManagerFuncController managerFuncController = new ManagerFuncController(mockBookController);

        // Prepare test data
        ArrayList<BookModel> testBooks = new ArrayList<>();
        testBooks.add(new BookModel("ISBN1", "Book1", "Category1", "Supplier1", 20.0, 15.0, "Author1", 4));
        testBooks.add(new BookModel("ISBN2", "Book2", "Category2", "Supplier2", 30.0, 25.0, "Author2", 8));
        testBooks.add(new BookModel("ISBN3", "Book3", "Category3", "Supplier3", 10.0, 5.0, "Author3", 2));

        // Set the test data in the mock repository
        mockRepository.setStockBooks(testBooks);

        // Call the method to test
        ArrayList<BookModel> result = managerFuncController.getLowStock();

        // Assertions
        assertEquals(2, result.size());
    }

    @Test
    public void testGetLowStock_NoLowStock() {
        // Create a mock repository with sufficient stock
        MockStockBookRepository mockRepository = new MockStockBookRepository();
        mockRepository.setStockBooks(new ArrayList<>( Arrays.asList(
                new BookModel("ISBN1", "Book1", "Category1", "Supplier1", 20.0, 15.0, "Author1", 10),
                new BookModel("ISBN2", "Book2", "Category2", "Supplier2", 30.0, 25.0, "Author2", 15),
                new BookModel("ISBN3", "Book3", "Category3", "Supplier3", 10.0, 5.0, "Author3", 20)
        )));

        // Create a BookController instance with the mock repository
        BookController mockBookController = new BookController(mockRepository);

        // Create a ManagerFuncController instance with the mock BookController
        ManagerFuncController managerFuncController = new ManagerFuncController(mockBookController);

        // Call the method to test
        ArrayList<BookModel> result = managerFuncController.getLowStock();

        // Assertions
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetLowStock_SingleLowStock() {
        // Create a mock repository with one book in low stock
        MockStockBookRepository mockRepository = new MockStockBookRepository();
        mockRepository.setStockBooks(new ArrayList<>( Arrays.asList(
                new BookModel("ISBN1", "Book1", "Category1", "Supplier1", 20.0, 15.0, "Author1", 10),
                new BookModel("ISBN2", "Book2", "Category2", "Supplier2", 30.0, 25.0, "Author2", 15),
                new BookModel("ISBN3", "Book3", "Category3", "Supplier3", 10.0, 5.0, "Author3", 2)
        )));

        // Create a BookController instance with the mock repository
        BookController mockBookController = new BookController(mockRepository);

        // Create a ManagerFuncController instance with the mock BookController
        ManagerFuncController managerFuncController = new ManagerFuncController(mockBookController);

        // Call the method to test
        ArrayList<BookModel> result = managerFuncController.getLowStock();

        // Assertions
        assertEquals(1, result.size());
        assertTrue(result.contains(new BookModel("ISBN3", "Book3", "Category3", "Supplier3", 10.0, 5.0, "Author3", 2)));
    }

    @Test
    public void testGetLowStock_EmptyStock() {
        MockStockBookRepository mockRepository = new MockStockBookRepository();

        BookController mockBookController = new BookController(mockRepository);

        ManagerFuncController managerFuncController = new ManagerFuncController(mockBookController);

        ArrayList<BookModel> result = managerFuncController.getLowStock();

        assertTrue(result.isEmpty());
    }





}
