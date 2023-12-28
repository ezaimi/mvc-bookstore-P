package com.example.kthimi;

import com.example.kthimi.Controller.MainController;
import com.example.kthimi.View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookstoreApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create an instance of MainView to access the mainPage method
        MainView mainView = new MainView();


        mainView.setPrimaryStage(primaryStage);


        MainController mainController = new MainController(mainView);
        mainView.setController(mainController);
        // Call mainPage to get the initial UI layout
        Scene scene = new Scene(mainView.mainPage(), 800, 600);

        //mainView.setPrimaryStage(mainView.mainPage());

        // Set the initial scene for the primaryStage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bookstore Application");

        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }

}
