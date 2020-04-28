package stonks.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import stonks.dao.UserDaoImpl;
import stonks.domain.Goal;
import stonks.domain.Routine;
import stonks.domain.StonksService;

public class StonksUi extends Application {
    private StonksService stonksService;
    private Stage stage;
    
    @Override
    public void init() {
        UserDaoImpl userDao = new UserDaoImpl();

        this.stonksService = new StonksService(userDao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        
        BorderPane bp = new BorderPane();
        
        if (stonksService.loadUser()) {
            bp.setTop(new Label("Hey " + stonksService.getCurrentUser().name + "! How was your day?"));

            ObservableList<Goal> list = FXCollections.observableArrayList(stonksService.getGoals());
            ListView<Goal> goalsListView = new ListView<>(list);
            goalsListView.setCellFactory(new Callback<ListView<Goal>, ListCell<Goal>>() {
                @Override
                public ListCell<Goal> call(ListView<Goal> param) {
                    return new GoalCell();
                }
            });

            bp.setCenter(goalsListView);

            Button addButton = new Button("Add a new goal");

            addButton.setOnAction((event) -> {
                Dialog dialog = new Dialog();
                dialog.setTitle("Add a new goal");
                dialog.setHeaderText("Add a new goal");

                VBox vbox = new VBox();
                vbox.setSpacing(10);

                TextField goalName = new TextField();
                goalName.setPromptText("Goal");
                TextField goalAmount = new TextField();
                goalAmount.setPromptText("amount");
                TextField goalUnit = new TextField();
                goalUnit.setPromptText("unit (kilograms, times, bottles etc.)");
                ComboBox goalRoutine = new ComboBox();
                goalRoutine.getItems().setAll((Object[]) Routine.values());
                goalRoutine.getSelectionModel().selectFirst();

                vbox.getChildren().addAll(goalName, goalAmount, goalUnit, goalRoutine);

                dialog.getDialogPane().contentProperty().set(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(
                        new ButtonType("Add", ButtonBar.ButtonData.OK_DONE),
                        new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE)
                );

                dialog.showAndWait();

                Goal addedGoal = new Goal(
                        goalName.getText(),
                        goalUnit.getText(),
                        Routine.valueOf(goalRoutine.getSelectionModel().getSelectedItem().toString().toUpperCase()),
                        Integer.parseInt(goalAmount.getText())
                );

                goalsListView.getItems().add(addedGoal);
            });

            bp.setBottom(addButton);
        } else {
            HBox loginGroup = new HBox();
            TextField usernameField = new TextField();
            Button loginButton = new Button("Login");

            loginButton.setOnAction((event) -> {
                if (stonksService.createUser(usernameField.getText())) {
                    if (stonksService.loadUser()) {
                        start(primaryStage);
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
