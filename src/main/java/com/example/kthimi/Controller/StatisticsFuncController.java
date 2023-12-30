package com.example.kthimi.Controller;

import java.util.ArrayList;
import java.util.Date;

public class StatisticsFuncController {

    //te dyja keto jane perdorur tek checkouti
    public ArrayList<Date> datesSold;//gjeje ku eshte new Array...
    public ArrayList<Double> moneyMadeDates;

    public StatisticsFuncController(){
        datesSold = new ArrayList<>();
        moneyMadeDates = new ArrayList<>();
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
}
