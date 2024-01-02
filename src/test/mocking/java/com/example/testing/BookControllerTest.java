package com.example.testing;

import com.example.kthimi.Controller.BookController;
import com.example.kthimi.Controller.BookControllerTestable;
import com.example.kthimi.Model.BookModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BookControllerTest {

    @Test
    public void testUpdateBooks() throws IOException {
        BookControllerTestable controller = new BookControllerTestable();

        ArrayList<BookModel> existingBooks = new ArrayList<>();
        existingBooks.add(new BookModel("existingISBN", "Title", "Category", "Supplier", 20.0, 15.0, "Author", 50)); // Updated constructor
        controller.stockBooks = existingBooks;

        ArrayList<BookModel> newBooks = new ArrayList<>();
        newBooks.add(new BookModel("existingISBN", "Title", "Category", "Supplier", 20.0, 15.0, "Author", 50)); // Same book
        newBooks.add(new BookModel("newISBN", "New Title", "New Category", "New Supplier", 15.0, 10.0, "New Author", 20)); // New book

        controller.updateBooks(newBooks);

        assertEquals(2, controller.stockBooks.size()); // Ensure both books are in the stock
        assertEquals("existingISBN", controller.stockBooks.get(0).getISBN()); // Check the existing book
        assertEquals("newISBN", controller.stockBooks.get(1).getISBN()); // Check the newly added book
    }
}
