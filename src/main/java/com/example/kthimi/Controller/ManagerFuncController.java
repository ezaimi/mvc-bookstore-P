package com.example.kthimi.Controller;

import com.example.kthimi.Model.BookModel;
import com.example.kthimi.Model.LibrarianModel;
import com.example.kthimi.Model.ManagerModel;

import java.util.ArrayList;

public class ManagerFuncController extends ManagerModel {

BookController bookController = new BookController();
    static ArrayList<LibrarianModel> librarians = ManagerModel.getLibrarians();


    public static void deleteLibrarian(LibrarianModel lib) {
        for (int i=0;i<librarians.size();i++) {
            if (librarians.get(i).getUsername().equals(lib.getUsername())) {
                librarians.remove(i);
                return;
            }
        }
    }

    public static void updateLibrarians(LibrarianModel lib) {

        for (int i=0;i<librarians.size();i++){
            if (librarians.get(i).getUsername().equals(lib.getUsername())) {
                librarians.get(i).setEmail( lib.getEmail() );
                librarians.get(i).setPhone( lib.getPhone() );
                librarians.get(i).setSalary( lib.getSalary() );
                librarians.get(i).setPassword( lib.getPassword() );
                librarians.get(i).setUsername( lib.getUsername() );
                return;
            }

        }


    }

    public ArrayList<BookModel> getLowStock(){

        ArrayList<BookModel> stockbooks = bookController.getStockBooks();
        ArrayList<BookModel> ans = new ArrayList<>();

        for (int i=0;i<stockbooks.size();i++) {

            if (stockbooks.get(i).getStock() < 5 ) {
                ans.add(stockbooks.get(i));
            }

        }

        return ans;

    }

}
