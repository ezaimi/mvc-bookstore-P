package com.example.kthimi.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;



public class BookModel implements Serializable {

    private static final long serialVersionUID = -277127502423542483L; // You can choose any long value


    private String ISBN;
    private String title;
    private String category;
    private String supplier;
    private ArrayList<Date> dates;
    private ArrayList<Integer> purchasedAmount;
    private ArrayList<Date> purchasedDates;
    private ArrayList<Integer> quantitiesPurchased;
    private double sellingPrice;
    private double originalPrice;
    private String author;
    private int stock = 0;

    // Constructors, getters, and setters for the attributes...
    public BookModel(String ISBN, String title, String category, String supplier, double originalPrice, double sellingPrice, String author, int stock){
        this.setISBN(ISBN);
        this.setTitle(title);
        this.setCategory(category);
        this.setSupplier(supplier);
        this.setOriginalPrice(originalPrice);
        this.setSellingPrice(sellingPrice);
        this.setAuthor(author);
        this.AddStock(stock);
        dates = new ArrayList<>();
        purchasedDates = new ArrayList<>();
        quantitiesPurchased = new ArrayList<>();
        purchasedAmount = new ArrayList<>();
    }

    public BookModel(String ISBN){
        quantitiesPurchased = new ArrayList<>();
        dates = new ArrayList<>();
        purchasedDates = new ArrayList<>();
        purchasedAmount = new ArrayList<>();
        this.setISBN(ISBN);
    }

    public BookModel() {

    }


    public ArrayList<Date>getPurchasedDates(){
        return purchasedDates;
    }


    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public double getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
    public String getAuthor() { return author;}
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getStock() {
        return stock;
    }
    public void AddStock(int stock) {
        this.stock += stock;
    }
    public void RemoveStock(int stock) {
        this.stock -= stock;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void addDate(Date date) {
        this.dates.add(date);
    }
    public void setDates(ArrayList<Date> dates) {
        this.dates = dates;
    }
    public ArrayList<Date> getDates(){
        return dates;
    }
    public void addPurchasedDate(Date date) {
        this.purchasedDates.add(date);
    }
    public void addQuantity(int quan) { this.purchasedAmount.add( quan ); }
    public void addQuantitiesPurchased(int quan) {
        this.quantitiesPurchased.add(quan);
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setPurchasedAmount(ArrayList<Integer> purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }


    // Methods related to book data manipulation...

    public void addPurchase(Date date) {
        // Ensure the ArrayLists are initialized
        if (purchasedDates == null) {
            purchasedDates = new ArrayList<>();
        }
        if (quantitiesPurchased == null) {
            quantitiesPurchased = new ArrayList<>();
        }

        // Add the purchase date to the list of purchased dates
        purchasedDates.add(date);

        // For simplicity, assume each purchase is for one quantity; adjust accordingly if needed
        quantitiesPurchased.add(1);
    }



    @Override
    public String toString() {
        return "BookModel{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", supplier='" + supplier + '\'' +
                ", dates=" + dates +
                ", purchasedAmount=" + purchasedAmount +
                ", purchasedDates=" + purchasedDates +
                ", quantitiesPurchased=" + quantitiesPurchased +
                ", sellingPrice=" + sellingPrice +
                ", originalPrice=" + originalPrice +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                '}';
    }
}
