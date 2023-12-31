package com.example.kthimi.Controller;

import com.example.kthimi.Model.BookModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class StatisticsFuncController {

    //te dyja keto jane perdorur tek checkouti
    public ArrayList<Date> datesSold;//gjeje ku eshte new Array...
    public ArrayList<Double> moneyMadeDates;


//
//    private ArrayList<Integer> purchasedAmount;
//    private ArrayList<Integer> quantitiesPurchased;

    public StatisticsFuncController(){
        datesSold = new ArrayList<>();
        moneyMadeDates = new ArrayList<>();
//        quantitiesPurchased = new ArrayList<>();
//        purchasedAmount = new ArrayList<>();
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

//DUHET TE JENE TEK KJO KLASE, Problemi eshte tek SoldStatisticsManagerView
//
//    public int getPurchasedAmount(){
//        int sum=0;
//        for (int i=0;i<purchasedAmount.size();i++) {
//            sum+=purchasedAmount.get(i);
//        }
//        return sum;
//    }
//    public int getQuantitiesPurchased(){
//        int sum=0;
//        for (int i=0;i<quantitiesPurchased.size();i++) {
//            sum+=quantitiesPurchased.get(i);
//        }
//        return sum;
//    }



    public static void removeDuplicatesSoldTitles(ArrayList<String> titles, ArrayList<Integer> quantities) {
        for (int k = 0; k < 2; k++) {
            Iterator<String> titleIterator = titles.iterator();
            Iterator<Integer> quantityIterator = quantities.iterator();

            while (titleIterator.hasNext() && quantityIterator.hasNext()) {
                String currentTitle = titleIterator.next();

                for (int j = titles.indexOf(currentTitle) + 1; j < titles.size(); j++) {
                    if (currentTitle.equals(titles.get(j))) {
                        quantities.set(titles.indexOf(currentTitle), quantities.get(titles.indexOf(currentTitle)) + quantities.get(j));
                        quantityIterator.next(); // Move the quantityIterator to the correct position
                        quantityIterator.remove(); // Use iterator to remove the element safely

                        titleIterator.next(); // Move the titleIterator to the correct position
                        titleIterator.remove(); // Use iterator to remove the element safely
                    }
                }
            }

            // Check if there are elements to compare
            if (!titles.isEmpty()) {
                int n = titles.size() - 1;

                // Check if there are at least two elements to compare
                if (n >= 1 && titles.get(n).equals(titles.get(n - 1))) {
                    quantities.set(n - 1, quantities.get(n) + quantities.get(n - 1));
                    quantities.remove(n);
                    titles.remove(n);
                }
            }
        }
    }




    public static String getBooksSoldDay() {

        String ans = "For Books Sold Today We Have:\n\n";

        ArrayList<BookModel> array = BookController.getStockBooks();

        for (int i=0;i<array.size();i++) {
            ans = ans.concat(array.get(i).getSoldDatesQuantitiesDay());
        }
        //System.out.println("TekBilli");
        //System.out.println(ans);
        return ans;

    }

    public static String getBooksSoldMonth() {

        String ans = "For Books Sold In A Month We Have\n\n";

        ArrayList<BookModel> arr = BookController.getStockBooks();
        for (int i=0;i<arr.size();i++) {
            ans = ans.concat(arr.get(i).getSoldDatesQuantitiesMonth());
        }
        return ans;
    }

    public static String getBooksSoldYear() {

        String ans = "For Books Sold In A Year We Have\n\n";

        ArrayList<BookModel> arr = BookController.getStockBooks();
        for (int i=0;i<arr.size();i++) {
            ans = ans.concat(arr.get(i).getSoldDatesQuantitiesYear());
        }
        return ans;
    }


    public static String getBooksBoughtDay() {

        String ans = "For Books Bought Today We Have\n\n";

        ArrayList<BookModel> array = BookController.getStockBooks();
        for (int i=0;i<array.size();i++) {

            ans = ans.concat(array.get(i).getBoughtDatesQuantitiesDay());}
        return ans;
    }

    public static String getBooksBoughtMonth() {

        String ans = "For Books Bought In A Month We Have\n\n";

        ArrayList<BookModel> array = BookController.getStockBooks();

        for (int i=0;i<array.size();i++) {
            ans = ans.concat(array.get(i).getBoughtDatesQuantitiesMonth());
        }
        return ans;

    }

    public static String getBooksBoughtYear() {

        String ans = "For Books Bought In A Year We Have\n\n";

        ArrayList<BookModel> array = BookController.getStockBooks();

        for (int i=0;i<array.size();i++) {
            ans = ans.concat(array.get(i).getBoughtDatesQuantitiesYear());
        }
        return ans;

    }



}
