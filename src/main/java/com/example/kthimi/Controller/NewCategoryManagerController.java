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

public class NewCategoryManagerController {

    ManagerModel managerModel;
    ManagerView managerView;
    SupplyManagerController supplyManagerController;
    SupplyManagerView supplyManagerView;
    NewCategoryManagerView view;
    NewCategoryManagerController controller;

   // AddStockManagerView addStockManagerView;

    public NewCategoryManagerController(SupplyManagerView supplyManagerView, SupplyManagerController supplyManagerController){
        this.supplyManagerView = supplyManagerView;

        this.supplyManagerController = supplyManagerController;

        // this.managerModel = new ManagerModel();
        this.view = new NewCategoryManagerView(supplyManagerView,supplyManagerView.getCategories());
        this.controller = this;
        initNewCategoryButtonAction();
    }

    private void initNewCategoryButtonAction() {
        supplyManagerView.getBttNewCategory().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleNewCategory();
            }
        });
    }

    public void handleNewCategory(){
        BorderPane supplyPage = view.createNewCategory(); // Create the supply view content

        Stage currentStage = (Stage) supplyManagerView.getBttNewCategory().getScene().getWindow();
        currentStage.setScene(new Scene(supplyPage, 800, 600));
        currentStage.show();

    }
}
