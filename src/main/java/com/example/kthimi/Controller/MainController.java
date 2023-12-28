package com.example.kthimi.Controller;

import com.example.kthimi.Model.AuthenticationModel;
import com.example.kthimi.View.AdminView;
import com.example.kthimi.View.LibrarianView;
import com.example.kthimi.View.MainView;
import com.example.kthimi.View.ManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainController {
    private MainView view;
    private AuthenticationModel model;
    String usernamePage;

    public MainController(MainView view) {
        this.view = view;
        this.model = new AuthenticationModel();
        initSubmitButtonAction();

    }

    private void initSubmitButtonAction() {
        view.getBttSubmit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin();
            }
        });
    }

    public void handleLogin() {
        String user = view.getUsername().getText();
        String pass = view.getPassword().getText();

        //System.out.println(user); a for admin

        if (user.isEmpty() || pass.isEmpty()) {
            view.getMainLoginWarning().setText("Empty Fields");
        } else if (model.authenticateLibrarian(user, pass)) {
            // performing actions for authenticated librarian
            view.clearFields();
            //System.out.println("tek handle brenda");
            LibrarianView librarianView = new LibrarianView(user,view);
            view.getPrimaryStage().getScene().setRoot(librarianView.getLibrarianMainPage());
            //view.showLibrarianPage(); // Method in MainView to display librarian page
        } else if (model.authenticateManager(user, pass)) {
            view.clearFields();
            ManagerView managerView = new ManagerView(user,view);
            view.getPrimaryStage().getScene().setRoot(managerView.getManagerMainPage());

           // view.showManagerPage(); // Method in MainView to display manager page
        }else if(model.authenticateAdmin(user,pass)){
            view.clearFields();
            AdminView adminView = new AdminView(user,view);
            view.getPrimaryStage().getScene().setRoot(adminView.getAdminMainPage());

            //view.showAdminPage();
        } else {
            view.getMainLoginWarning().setText("Wrong Information");
            view.clearFields();
        }
    }
}
