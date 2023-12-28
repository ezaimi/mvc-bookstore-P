package com.example.kthimi.View;

import com.example.kthimi.Controller.BookController;
import com.example.kthimi.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LibrarianView {

    private ComboBox<String> comboBoxLibrarian;
   private TextField warningsLibrarian = new TextField();;
    private Button bttBack = new Button("Back");
    private Button bttAdd = new Button("Add");
    private Button bttBill =  new Button("Bill");
    private TextField quantity =  new TextField();
    private Text textQuantity = new Text("Quantity");
    private BorderPane librarianPage;
    MainView mainView;
    String usernamePage;

    Stage primaryStage;

    Text textSystem = new Text("System");


    public Button getBttBack() {
        return bttBack;
    }

    public LibrarianView(String usernamePage, MainView mainView) {
        this.usernamePage = usernamePage;
        this.mainView = mainView;
        librarianPage = createLibrarianMainPage();
    }

    public BorderPane createLibrarianMainPage() {
        BorderPane borderPane = new BorderPane();

        //comboBoxLibrarian = new ComboBox(FXCollections.observableArrayList(BookController.getISBNName()));


//        Text librarianText = new Text("Welcome " + usernamePage + "!");
//        librarianText.setFont(new Font(20));


        bttBack.setOnAction(event -> {
            mainView.getPrimaryStage().getScene().setRoot(mainView.mainPage());
        });
//        borderPane.setBottom(bttBack);
//        borderPane.setCenter(comboBoxLibrarian);
//        borderPane.setCenter(librarianText);

        comboBoxLibrarian = new ComboBox(FXCollections.observableArrayList(BookController.getISBNName()));

        Text textHeaderLibrarian = new Text("Welcome "+usernamePage);
        StackPane stackHeader = new StackPane();
        textHeaderLibrarian.setFont(new Font(30));
        stackHeader.getChildren().add(textHeaderLibrarian);
        stackHeader.setPadding(new Insets(20));
        borderPane.setTop(stackHeader);

        GridPane gridLibrarianMain = new GridPane();
        gridLibrarianMain.setAlignment(Pos.CENTER);
        gridLibrarianMain.setHgap(5);
        gridLibrarianMain.setVgap(5);
        gridLibrarianMain.add(comboBoxLibrarian,1,0);
        gridLibrarianMain.add(textQuantity, 0, 2);
        gridLibrarianMain.add(quantity, 1, 2);
        gridLibrarianMain.add(textSystem, 0, 5);
        gridLibrarianMain.add(warningsLibrarian, 1, 5);
        warningsLibrarian.setEditable(false);
        borderPane.setCenter(gridLibrarianMain);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(bttBack,bttAdd,bttBill);
        hbox.setPadding(new Insets(40));
        hbox.setSpacing(30);
        borderPane.setBottom(hbox);
        //borderPane.setBottom(bttBack);

        //borderPane.setCenter(librarianText);


        return borderPane;
    }

    public BorderPane getLibrarianMainPage() {
        // Return the BorderPane containing librarian view components
        return librarianPage;
    }

}
