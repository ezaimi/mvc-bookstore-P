package com.example.kthimi.Model;

import com.example.kthimi.Controller.BookController;

import java.util.ArrayList;

public class ManagerModel extends LibrarianModel{

    public ManagerModel(){

    }


    ManagerModel(String username, String password) {
        super(username, password);

    }

    ManagerModel(String username,String password,String name,double salary,String phone,String email){
        super(username,password,name,salary,phone,email);
    }

    //KETE METODEN FUTE TE NJE KONTROLLER I VECANT BASHK ME GETALLCATEGORIES
    //QE ESHTE TEK MANAGER.JAVA SEPSE KANE LIDHJE ME VEPRIMET E LIBRAVE TEK MANAGERVIEW
    public static ArrayList<BookModel> getLowStock(){

        ArrayList<BookModel> stockbooks = BookController.getStockBooks();
        ArrayList<BookModel> ans = new ArrayList<>();

        for (int i=0;i<stockbooks.size();i++) {

            if (stockbooks.get(i).getStock() < 5 ) {
                ans.add(stockbooks.get(i));
            }

        }

        return ans;

    }


    public static ArrayList<String> getAllCategories(){

        ArrayList<String> ans = new ArrayList<>();
        ans.add("Modernist");
        ans.add("Fiction");
        ans.add("Novel");
        ans.add("Magic Realism");
        ans.add("Tragedy");
        ans.add("Adventure Fiction");
        ans.add("Historical Novel");
        ans.add("Epic");
        ans.add("War");
        ans.add("Autobiography and memoir");
        ans.add("Biography");
        ans.add("Non-fiction novel");
        ans.add("Self-help");
        ans.add("Short stories");
        ans.add("Horror");
        ans.add("Mystery");
        ans.add("Romance");
        ans.add("Thriller");
        return ans;
    }



}
