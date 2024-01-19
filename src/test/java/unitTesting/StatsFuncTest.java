package unitTesting;

import com.example.kthimi.Controller.BookController;
import com.example.kthimi.Controller.LibrarianFuncController;
import com.example.kthimi.Controller.Mockers.FileBasedStockBookRepository;
import com.example.kthimi.Controller.StatisticsFuncController;
import com.example.kthimi.Model.BookModel;
import com.example.kthimi.Model.LibrarianModel;
import integrationTest.StatsOtherTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatsFuncTest {

    StatisticsFuncController statisticsFuncController = new StatisticsFuncController();
    BookController bookController = new BookController();
    private static String TEMP_STOCK_FILE_PATH = "tempStockFile.bin";
    private static final String TEST_ISBN = "1234567890123";
    private static final String TEST_TITLE = "Test Book";


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
        StatsOtherTest.setStockFilePath(TEMP_STOCK_FILE_PATH);
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

    private ArrayList<BookModel> saveBooksToTemporaryFile(ArrayList<BookModel> books) {
        try (ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(TEMP_STOCK_FILE_PATH))) {
            objout.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }


    @Test
    void testRemoveDuplicatesSoldTitles() {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();

        titles.add("Title1");
        titles.add("Title2");
        titles.add("Title1");
        titles.add("Title3");
        quantities.add(10);
        quantities.add(5);
        quantities.add(8);
        quantities.add(3);

        statisticsFuncController.removeDuplicatesSoldTitles(titles, quantities);
        System.out.println(titles);
        System.out.println(titles.size());

        System.out.println(quantities);
        assertEquals(1, titles.size());

    }

    @Test
    void testRemoveDuplicatesSoldTitlesEmptyLists() {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();

        statisticsFuncController.removeDuplicatesSoldTitles(titles, quantities);

        assertTrue(titles.isEmpty(), "Removing duplicates from empty lists should result in empty lists");
        assertTrue(quantities.isEmpty(), "Removing duplicates from empty lists should result in empty lists");
    }

    @Test
    void testRemoveDuplicatesSoldTitlesNoDuplicates() {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();

        titles.add("Title1");
        titles.add("Title2");
        titles.add("Title3");
        quantities.add(10);
        quantities.add(5);
        quantities.add(3);

        statisticsFuncController.removeDuplicatesSoldTitles(titles, quantities);

        assertEquals(3, titles.size(), "List with no duplicates should remain unchanged");
        assertEquals(3, quantities.size(), "List with no duplicates should remain unchanged");
    }

    @Test
    void testRemoveDuplicatesSoldTitlesCaseInsensitive() {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();

        titles.add("Title1");
        titles.add("title1");
        titles.add("Title2");
        titles.add("Title2");
        quantities.add(10);
        quantities.add(5);
        quantities.add(8);
        quantities.add(3);

        statisticsFuncController.removeDuplicatesSoldTitles(titles, quantities);

        assertEquals(3, titles.size(), "Duplicates should be removed case-insensitively");
        assertEquals(3, quantities.size(), "Duplicates should be removed case-insensitively");
    }

    @Test
    void testRemoveDuplicatesSoldTitlesWithNulls() {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();

        titles.add("Title1");
        titles.add(null);
        titles.add("Title1");
        titles.add(null);
        quantities.add(10);
        quantities.add(5);
        quantities.add(8);
        quantities.add(3);

        statisticsFuncController.removeDuplicatesSoldTitles(titles, quantities);

        assertEquals(1, titles.size(), "Duplicates including nulls should be removed");
        assertEquals(1, quantities.size(), "Duplicates including nulls should be removed");
    }


}
