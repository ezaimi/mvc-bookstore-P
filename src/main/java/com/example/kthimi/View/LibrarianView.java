package com.example.kthimi.View;

import com.example.kthimi.Controller.AddLibrarianController;
import com.example.kthimi.Controller.BillLibrarianController;
import com.example.kthimi.Controller.BookController;
import com.example.kthimi.Controller.LibrarianFuncController;
import com.example.kthimi.Main;
import com.example.kthimi.Model.BookModel;
import com.example.kthimi.Model.LibrarianModel;
import com.example.kthimi.Model.SharedDataManager;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class LibrarianView {

    Date date;

    ArrayList<Integer> bookQuantities = new ArrayList<>();
    ArrayList<String> booksSoldTitles = new ArrayList<>();
    TextField bookISBN = new TextField();
    public TextField quantity = new TextField();
    public TextField warningsLibrarian = new TextField();


    AddStockManagerView addStockManagerView;
    ArrayList<BookModel> books;
    String category;
    LibrarianView librarianView;


    ///////////////////

    AddLibrarianController addLibrarianController;
    BillLibrarianController billLibrarianController;

    //////////////////

    public ComboBox<String> comboBoxLibrarian;
    public Button bttBack = new Button("Back");
    public Button bttAdd = new Button("Add");
    public Button bttBill =  new Button("Bill");
    public Text textQuantity = new Text("Quantity");
    public BorderPane librarianPage;
    MainView mainView;
    String usernamePage;

    Stage primaryStage;

    Text textSystem = new Text("System");

    AddStockManagerView.ButtonHandler buttonHandler;
    LibrarianFuncController librarianFuncController;

    public Button getBttBack() {
        return bttBack;
    }

    public LibrarianView(){

    }

    public LibrarianView(String usernamePage, MainView mainView) {
        this.usernamePage = usernamePage;
        this.mainView = mainView;
        librarianPage = createLibrarianMainPage();

//        this.addStockManagerView = new AddStockManagerView();
        this.books = SharedDataManager.getInstance().getBooks();

        //per butonin Add tek Librarian
       // initializeAddController();
        this.addLibrarianController = new AddLibrarianController(this);
        this.billLibrarianController = new BillLibrarianController(this,addLibrarianController,books);
        //bttAdd.setOnAction(new AddButtonHandler());

        setAddAction();
        setBillAction();
    }

    public LibrarianView(AddStockManagerView addStockManagerView) {
        this.addStockManagerView = addStockManagerView;
    }

    public void setAddAction(){
        bttAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("addi po funksionon");
                addLibrarianController.handleAdd();
            }
        });
    }

    public void setBillAction(){
        bttBill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("billi u printua");
                billLibrarianController.handleBill();
            }
        });
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

        //bttAdd.setOnAction();
        //setAddAction();

        //bttBill.setOnAction(this);
        //setBillAction();


        return borderPane;
    }

    public BorderPane getLibrarianMainPage() {
        // Return the BorderPane containing librarian view components
        return librarianPage;
    }


    public Button getBttAdd() {
        return bttAdd;
    }
    public Button getBttBill() {
        return bttBill;
    }


    private class AddButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            if (comboBoxLibrarian.getValue() == null && quantity.getCharacters().toString().isEmpty()) {
                warningsLibrarian.setText("Failed, Empty fields");
                return;
            }
            else if (comboBoxLibrarian.getValue() == null) {
                warningsLibrarian.setText("Failed to add,Empty ISBN");
                return;
            }
            else if (quantity.getCharacters().toString().isEmpty()) {
                warningsLibrarian.setText("Failed, Empty Quantity");
                return;
            }

            else if(!quantity.getCharacters().toString().matches("\\d{1,}") || Integer.parseInt(quantity.getCharacters().toString()) == 0) {
                warningsLibrarian.setText("Failed, Invalid Quantity");
                return;
            }


            if (!LibrarianModel.EnoughStock(comboBoxLibrarian.getValue().toString().substring(0,13), Integer.parseInt(quantity.getCharacters().toString())) ) {
                warningsLibrarian.setText("Failed,not enough stock");
                return;
            }

            String isbn = comboBoxLibrarian.getValue().toString().substring(0,13);
            int quan = Integer.parseInt(quantity.getCharacters().toString());

            String Title = comboBoxLibrarian.getValue().toString().substring(16);
            date = new Date();

            ArrayList<BookModel> stockbooks = BookController.getStockBooks();
            for (int i=0;i<stockbooks.size();i++) {
                if (stockbooks.get(i).getISBN().equals(isbn)) {

                    stockbooks.get(i).RemoveStock(quan);
                }
            }
            try {
                BookController.updateBooks(stockbooks);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            BookModel book = new BookModel(isbn);

            // books = addStockManagerView.getBooks();
            books.add(book);
            bookQuantities.add(quan);
            booksSoldTitles.add(Title);
            bookISBN.clear();
            quantity.clear();
            warningsLibrarian.setText("Added");

        }

//            if (e.getSource()==bttBill)  {
//
//
//                if (books.isEmpty() || bookQuantities.isEmpty()) {
//                    warningsLibrarian.setText("Failed, No Books to add");
//                    return;
//                }
//
//                try {
//                    librarian.checkOutBooks(books, bookQuantities);
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//
//
//                bookISBN.clear();
//                booksSoldTitles.clear();
//                quantity.clear();
//                books.clear();
//                bookQuantities.clear();
//                warningsLibrarian.setText("Bill File Created!");
//            }

//
//            if (e.getSource()==bttSupply) {
//                bttSupply.getScene().setRoot(ManagerSupplyCickPage());
//
//            }
//
//            if (e.getSource()==bttAddStock) {
//                bttAddStock.getScene().setRoot(chooseAddCurretStock());
//            }
//
//            if (e.getSource()==bttCheckStock) {
//                bttCheckStock.getScene().setRoot(checkStoragePage());
//            }
//            if (e.getSource()==bttNewCategory) {
//                bttNewCategory.getScene().setRoot(chooseNewCategoryAddStock());
//            }
//
//            if (e.getSource()==bttManageLibrarians) {
//                bttManageLibrarians.getScene().setRoot( administratorManageLibrariansPage() );
//            }


    }

//    public void accessBooksArray() {
//        AddStockManagerView addStockManagerView = new AddStockManagerView();
//        ArrayList<BookModel> books = addStockManagerView.getBooksFromSharedManager();
//        // Use the 'books' array as needed within LibrarianView
//        // For example:
//        for (BookModel book : books) {
//            System.out.println(book.getTitle());
//        }
//    }

//    public void someMethod() {
//        // Create an instance of AddStockManagerView
//        AddStockManagerView addStockManagerView = new AddStockManagerView();
//
//        // Access the books array using the method from AddStockManagerView
//        ArrayList<BookModel> books = addStockManagerView.getBooksFromSharedManager();
//
//        // Use the 'books' array as needed within LibrarianView
//        // For example:
//        for (BookModel book : books) {
//            System.out.println(book.getTitle());
//            // Do other operations with the book details
//        }
//    }




}
