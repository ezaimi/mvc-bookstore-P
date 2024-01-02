package com.example.kthimi.Controller;

import com.example.kthimi.Model.BookModel;
import com.example.kthimi.Model.LibrarianModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

//permban funksionet e billit tek librarian view
//mund te emertohet dhe BillController til now
public class LibrarianFuncController {
    BookController bookController = new BookController();
    BillLibrarianController billLibrarianController;
    LibrarianModel librarianModel;
    public static int totalBooksSold=0;
    public double  totalIncome=0;
    private double moneyMade=0;
    public ArrayList<Date> datesSold;
    private ArrayList<Double> moneyMadeDates;
    private int numberOfBills;
    private int booksSold=0;
    private static String STOCK_FILE_PATH = "Books.bin";
    private static final String BILL_COUNTER_FILE_PATH = "bill_counter.txt";

    public LibrarianFuncController(){

    }

    public LibrarianFuncController(BillLibrarianController billLibrarianController) {
        this.billLibrarianController = billLibrarianController;

        datesSold = new ArrayList<>();
        moneyMadeDates = new ArrayList<>();

        this.numberOfBills = loadNumberOfBills();
    }

    private int loadNumberOfBills() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BILL_COUNTER_FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle file reading or parsing errors
        }
        return 0; // Default value if the file doesn't exist or has invalid content
    }

    private void saveNumberOfBills() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BILL_COUNTER_FILE_PATH))) {
            writer.println(numberOfBills);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file writing errors
        }
    }

    public void incrementNumberOfBills() {
        numberOfBills++;
        saveNumberOfBills(); // Save the incremented value to the file
    }

    public void checkOutBooks(ArrayList<BookModel> books, ArrayList<Integer> quantities) throws  IOException{

        PrintWriter writer = new PrintWriter("Bill"+(++numberOfBills)+".txt");
        ArrayList<BookModel> stockbooks = bookController.getStockBooks();
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
        //numberOfBills+=1;
        incrementNumberOfBills();
        datesSold.add(new Date());
        moneyMadeDates.add(moneyMade);

        writer.write("\nTotal price: "+totalPrice+" Date: "+(new Date()).toString());
        writer.close();


    }
    public ArrayList<Date> getDatesSold() {return datesSold;}
    public void setDatesSold(ArrayList<Date> datesSold){this.datesSold = datesSold;}

    public int getNumberOfBills() {
        return numberOfBills;
    }

    public void setNumberOfBills(int numberOfBills){ this.numberOfBills = numberOfBills;}




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


    public void updateBookQuantity(ArrayList<BookModel> updatedBooks) {
        ArrayList<BookModel> books = bookController.getStockBooks();
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


    public double moneyMadeInDay() {

        if (this.datesSold==null) {
            return 0;
        }

        double ans=0;
        Date today = new Date();

        for (int i=0;i<this.datesSold.size();i++) {

            if ( datesSold.get(i).getYear()==today.getYear() && datesSold.get(i).getMonth()==today.getMonth() && datesSold.get(i).getDay() == today.getDay()) {
                System.out.println("here");
                ans+=moneyMadeDates.get(i);
            }

        }

        return ans;

    }

    public double moneyMadeInMonth() {

        if (this.datesSold==null) {
            return 0;
        }

        double ans=0;
        Date today = new Date();


        for (int i=0;i<datesSold.size();i++) {

            if (datesSold.get(i).getYear() == today.getYear() && datesSold.get(i).getMonth()==today.getMonth()) {
                ans+=moneyMadeDates.get(i);
            }
        }

        return ans;

    }

    public double moneyMadeInYear() {

        if (this.datesSold==null) {
            return 0;
        }

        double ans=0;
        Date today = new Date();

        for (int i=0;i<datesSold.size();i++) {

            if (datesSold.get(i).getYear() == today.getYear()) {
                ans+=moneyMadeDates.get(i);
            }
        }

        return ans;

    }



    public boolean EnoughStock(String ISBN, int quantity) {

        ArrayList<BookModel> stockbooks = bookController.getStockBooks();

        for (int i=0;i<stockbooks.size();i++) {
            if (stockbooks.get(i).getISBN().equals(ISBN))
                if (stockbooks.get(i).getStock() - quantity >= 0)
                    return true;
        }

        return false;


    }



}
