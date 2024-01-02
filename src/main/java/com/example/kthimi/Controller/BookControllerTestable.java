package com.example.kthimi.Controller;

import com.example.kthimi.Model.BookModel;

import java.util.ArrayList;

public class BookControllerTestable extends BookController {

    public ArrayList<BookModel> stockBooks = new ArrayList<>();

    @Override
    public ArrayList<BookModel> getStockBooks() {
        return stockBooks;
    }

    @Override
    public void saveBooksToFile(ArrayList<BookModel> books) {
        stockBooks = new ArrayList<>(books);
    }
}

