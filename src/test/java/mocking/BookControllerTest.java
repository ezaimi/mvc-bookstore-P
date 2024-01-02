package mocking;

import com.example.kthimi.Controller.BookController;
import com.example.kthimi.Controller.Mockers.BookControllerTestable;
import com.example.kthimi.Controller.Mockers.MockStockBookRepository;
import com.example.kthimi.Model.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookControllerTest {

//    BookController bookController;
//
//    @BeforeEach
//    void setUp() {
//        // Path to the test data file
//        String testFilePath = "TestBooks.bin";
//
//        // Use the test file path when initializing the BookController
//        System.out.println("hi");
//        bookController = new BookController(testFilePath);
//    }
//
//
//
//    @Test
//    public void testUpdateBooks() throws IOException {
//        BookControllerTestable controller = new BookControllerTestable();
//
//        ArrayList<BookModel> existingBooks = new ArrayList<>();
//        existingBooks.add(new BookModel("existingISBN", "Title", "Category", "Supplier", 20.0, 15.0, "Author", 50));
//        controller.stockBooks = existingBooks;
//
//        ArrayList<BookModel> newBooks = new ArrayList<>();
//        newBooks.add(new BookModel("existingISBN", "Title", "Category", "Supplier", 20.0, 15.0, "Author", 50));
//        newBooks.add(new BookModel("newISBN", "New Title", "New Category", "New Supplier", 15.0, 10.0, "New Author", 20));
//
//        controller.updateBooks(newBooks);
//
//        assertEquals(2, controller.stockBooks.size());
//        assertEquals("existingISBN", controller.stockBooks.get(0).getISBN());
//        assertEquals("newISBN", controller.stockBooks.get(1).getISBN());
//    }

    @Test
    public void testAddBookToStock() throws IOException, ClassNotFoundException {
        // Create a temporary file for testing
        File tempFile = File.createTempFile("testBooks", ".bin");

        // Create a mock repository
        MockStockBookRepository mockRepository = new MockStockBookRepository();
        ArrayList<BookModel> testData = new ArrayList<>();
        mockRepository.setStockBooks(testData);

        // Create a BookController instance with the mock repository
        BookController bookController = new BookController(mockRepository);
        BookController.STOCK_FILE_PATH = tempFile.getAbsolutePath(); // Set the temporary file path

        // Prepare a book to add
        BookModel bookToAdd = new BookModel("newISBN", "saLot", "New Category", "New Supplier", 15.0, 10.0, "New Author", 20);

        // Call the method to add the book to the mock repository
        bookController.addBookToStock(bookToAdd);

        // Get the updated books from the temporary file
        ArrayList<BookModel> updatedBooks;
        try (ObjectInputStream objis = new ObjectInputStream(new FileInputStream(tempFile))) {
            Object obj = objis.readObject();
            if (obj instanceof ArrayList) {
                updatedBooks = (ArrayList<BookModel>) obj;
            } else {
                updatedBooks = new ArrayList<>();
            }
        }

        // Assertions to verify the behavior
        assertEquals(1, updatedBooks.size()); // Only one book should be added
        assertEquals(bookToAdd, updatedBooks.get(0)); // The added book should match the bookToAdd

        // Clean up the temporary file
        tempFile.delete();
    }

    @Test
    public void testGetISBNName() {
        // Create a mock repository
        MockStockBookRepository mockRepository = new MockStockBookRepository();
        ArrayList<BookModel> testData = new ArrayList<>();
        testData.add(new BookModel("1", "mos1", "New Category", "New Supplier", 15.0, 10.0, "New Author", 20));
        testData.add(new BookModel("2", "mos2", "New Category", "New Supplier", 15.0, 10.0, "New Author", 20));
        mockRepository.setStockBooks(testData);

        // Create a BookController instance with the mock repository
        BookController bookController = new BookController(mockRepository);

        // Call the method to test
        ArrayList<String> result = bookController.getISBNName();

        // Verify the output
        ArrayList<String> expected = new ArrayList<>();
        expected.add("1 - mos1");
        expected.add("2 - mos2");

        assertEquals(expected, result);
    }












}