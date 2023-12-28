package com.example.kthimi.Controller;

import com.example.kthimi.Model.ManagerModel;
import com.example.kthimi.View.AddStockManagerView;
import com.example.kthimi.View.ManagerView;
import com.example.kthimi.View.NewCategoryManagerView;
import com.example.kthimi.View.SupplyManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddStockManagerController {

    ManagerModel managerModel;
    ManagerView managerView;
    SupplyManagerController supplyManagerController;
    SupplyManagerView supplyManagerView;
    AddStockManagerView view;
    AddStockManagerController controller;
    NewCategoryManagerView newCategoryManagerView;

//    private ArrayList<String> categ;
//
//    public void updateCateg(String category) {
//        categ.add(category);
//    }

    public AddStockManagerController(SupplyManagerView supplyManagerView, SupplyManagerController supplyManagerController,NewCategoryManagerView newCategoryManagerView){
       this.supplyManagerView = supplyManagerView;

       this.supplyManagerController = supplyManagerController;
        this.newCategoryManagerView = newCategoryManagerView;

        ArrayList<String> categories = newCategoryManagerView.getCategories(); // Get categories from NewCategoryManagerView


        // this.managerModel = new ManagerModel();
       this.view = new AddStockManagerView(supplyManagerView,newCategoryManagerView,categories);
       this.controller = this;

        view.setNewCategoryManagerView(newCategoryManagerView);


        initAddStockButtonAction();
    }

    private void initAddStockButtonAction() {
        supplyManagerView.getBttAddStock().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleAddStock();
            }
        });
    }

    public void handleAddStock(){
        BorderPane supplyPage = view.createAddToStock(); // Create the supply view content

        Stage currentStage = (Stage) supplyManagerView.getBttAddStock().getScene().getWindow();
        currentStage.setScene(new Scene(supplyPage, 800, 600));
        currentStage.show();

    }

}
