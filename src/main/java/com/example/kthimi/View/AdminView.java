package com.example.kthimi.View;

import com.example.kthimi.Main;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AdminView {
    private BorderPane adminPage;
    String usernamePage;
    MainView mainView;

    Text textSystem = new Text("System");

    public AdminView(String usernamePage, MainView mainView) {
        this.mainView = mainView;
        this.usernamePage = usernamePage;
        adminPage = createAdminMainPage();
    }

    public BorderPane createAdminMainPage() {
        BorderPane borderPane = new BorderPane();

        Text librarianText = new Text("Welcome " + usernamePage + "!");
        librarianText.setFont(new Font(20));

        borderPane.setCenter(librarianText);

        Button bttBack = new Button("Back");
        bttBack.setOnAction(event -> {
            mainView.getPrimaryStage().getScene().setRoot(mainView.mainPage());
        });
        borderPane.setBottom(bttBack);
        return borderPane;
    }

    public BorderPane getAdminMainPage() {
        // Return the BorderPane containing librarian view components
        return adminPage;
    }
}
