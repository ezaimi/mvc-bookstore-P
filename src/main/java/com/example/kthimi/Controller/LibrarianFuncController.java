package com.example.kthimi.Controller;

import com.example.kthimi.Model.BookModel;
import com.example.kthimi.Model.LibrarianModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

//permban funksionet e billit tek librarian view
//mund te emertohet dhe BillController til now
public class LibrarianFuncController {

    public static int billNumber=0;
    public static int totalBooksSold=0;
    public static double  totalIncome=0;
    private static double moneyMade=0;
    public static ArrayList<Date> datesSold;
    private static ArrayList<Double> moneyMadeDates;
    private static int numberOfBills=0;
    private static int booksSold=0;
    private static String STOCK_FILE_PATH = "Books.bin";



    public static void checkOutBooks(ArrayList<BookModel> books, ArrayList<Integer> quantities) throws  IOException{

        datesSold = new ArrayList<>();
        moneyMadeDates = new ArrayList<>();

        PrintWriter writer = new PrintWriter("Bill"+(++billNumber)+".txt");
        ArrayList<BookModel> stockbooks = BookController.getStockBooks();
        double totalPrice = 0;

        System.out.println("TEK LIBRARIAN STOCKU FILLESTAR");
        System.out.println(stockbooks.toString());

        removeDuplicatesSoldBooks(books,quantities);


        System.out.println("After removing duplicates");
        System.out.println(books.toString());

        for (int i=0;i<books.size();i++) {
            for (int j=0;j<stockbooks.size();j++) {

                if (books.get(i).getISBN().equals(stockbooks.get(j).getISBN()))
                {
                    writer.write("Title: \""+stockbooks.get(j).getTitle()+"\", Quantities: "+quantities.get(i)+", OriginalPrice "+
                            stockbooks.get(j).getSellingPrice() +", Price: "+stockbooks.get(j).getSellingPrice()*quantities.get(i)+"\n");

                    // Reduce stock quantity by the checkout amount
                    stockbooks.get(j).removeStock(quantities.get(i));

                    stockbooks.get(j).addDate(new Date());
                    stockbooks.get(j).addQuantity( quantities.get(i) );

                    totalPrice+=stockbooks.get(j).getSellingPrice()*quantities.get(i);

                    totalBooksSold+=quantities.get(i);
                    totalIncome += totalPrice;
                    booksSold += quantities.get(i);

                }

            }
        }


        updateBookQuantity(stockbooks);

        // Update the stock file here if needed

//		try (ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(STOCK_FILE_PATH))) {
//			objout.writeObject(stockbooks);
//		}

        System.out.println("AFter update");
        System.out.println(stockbooks.toString());


        moneyMade+=totalPrice;
        numberOfBills+=1;
        datesSold.add(new Date());
        moneyMadeDates.add(moneyMade);

        writer.write("\nTotal price: "+totalPrice+" Date: "+(new Date()).toString());
        writer.close();


    }



    public static void removeDuplicatesSoldBooks(ArrayList<BookModel> books, ArrayList<Integer> quantities) {
        if (books.isEmpty() || quantities.isEmpty()) {
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(i).getISBN().equals(books.get(j).getISBN())) {
                    quantities.set(i, quantities.get(i) + quantities.get(j));
                    books.remove(j); // Remove the duplicated book
                    quantities.remove(j); // Remove the corresponding quantity
                    j--; // Adjust the index after removal
                }
            }
        }


    }


    public static void updateBookQuantity(ArrayList<BookModel> updatedBooks) {
        ArrayList<BookModel> books = BookController.getStockBooks();
        for (BookModel updatedBook : updatedBooks) {
            for (int i = 0; i < books.size(); i++) {
                BookModel currentBook = books.get(i);
                if (currentBook.getISBN().equals(updatedBook.getISBN())) {
                    // Replace the book in the list with the updated book
                    books.set(i, updatedBook);
                    break;
                }
            }
        }
        // Save the updated books back to the file if needed
        saveBooksToFile(books);
    }

    public static void saveBooksToFile(ArrayList<BookModel> books) {
        try (ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(STOCK_FILE_PATH))) {
            objout.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
    }



    public static boolean checkPassword(String password) {
        return password.matches(".{8,}");
    }

    public static boolean checkPhone(String phone) {
        return phone.matches("\\(912\\)\\s\\d{3}\\-\\d{4}");
    }

    public static boolean checkEmail(String email) {
        return email.matches("[a-zA-Z]{1,}\\@[a-zA-z]{1,}\\.com");
    }

    public static boolean checkSalary(String salary) {
        return salary.matches("^[0-9]+\\.?[0-9]*$");
    }

    public static boolean checkName(String name) {
        return name.matches("[a-zA-Z]{1,}");
    }





}
