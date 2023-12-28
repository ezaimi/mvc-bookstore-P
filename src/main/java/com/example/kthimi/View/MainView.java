package com.example.kthimi.View;

import com.example.kthimi.Controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView {

    private MainController controller;
    public void setController(MainController controller){
        this.controller = controller;
    }

    private Stage primaryStage; // Reference to the primary stage

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setSubmitAction(){
        bttSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.handleLogin();
            }
        });
    }

    TextField mainLoginWarning = new TextField();

    TextField username = new TextField();
    Text textUsername = new Text("Username");
    PasswordField password = new PasswordField();
    Text textPassword = new Text("Password");
    Text textSystem = new Text("System");
    Button bttSubmit = new Button("Submit");

    public Button getBttSubmit() {
        return bttSubmit;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public TextField getMainLoginWarning() {
        return mainLoginWarning;
    }

    public void clearFields() {
        // Method to clear input fields
        username.clear();
        password.clear();
        mainLoginWarning.clear();
    }
    /////////////////

    public void showLibrarianPage() {
        // Method to display librarian page
        System.out.println("tek show");
        BorderPane librarianPage = new BorderPane();
        Text librarianText = new Text("Welcome, Librarian!");
        librarianText.setFont(new Font(20));
        StackPane librarianPane = new StackPane(librarianText);
        librarianPage.setCenter(librarianPane);

        Scene librarianScene = new Scene(librarianPage, 400, 300);
        Stage librarianStage = new Stage();
        librarianStage.setScene(librarianScene);
        librarianStage.setTitle("Librarian Page");

        // Display librarian page in a new window
        librarianStage.show();
    }

    public void showManagerPage() {
        // Method to display manager page
        // Implement logic to show the manager page
    }

    public void showAdminPage(){

    }

    ///////////////////////
    public BorderPane mainPage() {

        BorderPane border = new BorderPane();
        border.setMinSize(500,300);

        StackPane stackText = new StackPane();
        Text text = new Text("Welcome");
        text.setFont(new Font(30));
        stackText.getChildren().add(text);
        stackText.setPadding(new Insets(20));
        border.setTop(stackText);

        mainLoginWarning.setEditable(false);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(textUsername,0,1);
        grid.add(username,1,1);
        grid.add(textPassword,0,2);
        grid.add(password,1,2);
        grid.add(textSystem, 0, 5);
        grid.add(mainLoginWarning, 1, 5);
        grid.add(bttSubmit, 6, 6);
        border.setCenter(grid);


        //Funksioni per event handler te butonit submit tek main page
        setSubmitAction();


        return border;
    }
}
