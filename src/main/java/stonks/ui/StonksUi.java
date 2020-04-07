package stonks.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import stonks.dao.UserDaoImpl;
import stonks.domain.StonksService;

public class StonksUi extends Application {
    private StonksService ss;
    private Stage stage;
    
    @Override
    public void init() {
        UserDaoImpl userDao = new UserDaoImpl();
        
        this.ss = new StonksService(userDao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        
        BorderPane bp = new BorderPane();
        
        if (ss.loadUser()) {
            bp.setCenter(new Label("Logged in as " + ss.getCurrentUser().name));
        } else {
            HBox loginGroup = new HBox();
            TextField usernameField = new TextField();
            Button loginButton = new Button("Login");

            loginButton.setOnAction((event) -> {
                if (ss.createUser(usernameField.getText())) {
                    if (ss.loadUser()) {
                        bp.getChildren().clear();
                        bp.setCenter(new Label("Logged in as " + ss.getCurrentUser().name));
                    }
                }
            });


            loginGroup.getChildren().add(usernameField);
            loginGroup.getChildren().add(loginButton);
            bp.setCenter(loginGroup);
            loginGroup.setAlignment(Pos.CENTER);
        }
        
        Scene scene = new Scene(bp);
        
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(640);
        primaryStage.setMinHeight(480);
        primaryStage.setTitle("Stonks");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
