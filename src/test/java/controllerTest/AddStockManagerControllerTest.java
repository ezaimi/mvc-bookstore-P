//package controllerTest;
//
//package com.example.kthimi.Controller;
//
//import com.example.kthimi.Controller.AddStockManagerController;
//import com.example.kthimi.Controller.SupplyManagerController;
//import com.example.kthimi.View.Manager.AddStockManagerView;
//import com.example.kthimi.View.Manager.ManagerView;
//import com.example.kthimi.View.Manager.NewCategoryManagerView;
//import com.example.kthimi.View.Manager.SupplyManagerView;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class AddStockManagerControllerTest {
//
//    @BeforeAll
//    public static void init() {
//        // Initialize JavaFX Toolkit for UI Testing
//        new JFXPanel();
//    }
//
//    @Test
//    public void testAddStockManagerController() {
//        SupplyManagerView supplyManagerView = new SupplyManagerView(new ManagerView());
//        SupplyManagerController supplyManagerController = new SupplyManagerController();
//        NewCategoryManagerView newCategoryManagerView = new NewCategoryManagerView(supplyManagerView, supplyManagerController);
//        AddStockManagerController addStockManagerController = new AddStockManagerController(supplyManagerView, supplyManagerController, newCategoryManagerView);
//
//        assertNotNull(addStockManagerController);
//
//        // You might want to include more specific tests for various functionalities of AddStockManagerController
//    }
//}
