package stonks.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import stonks.dao.UserDaoImpl;
import stonks.domain.Goal;
import stonks.domain.Routine;
import stonks.domain.StonksService;

import java.util.Optional;

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
            bp.setTop(new Label("Hey " + ss.getCurrentUser().name + "! How was your day?"));

            ObservableList<Goal> alist = FXCollections.observableArrayList(
                    new Goal("Work out", "times", Routine.WEEKLY, 3),
                    new Goal("Drink beer", "bottles", Routine.DAILY, 6),
                    new Goal("Play CS", "times", Routine.DAILY, 2)
            );

            ObservableList<Goal> list = FXCollections.observableArrayList(ss.getGoals());
            ListView<Goal> lv = new ListView<>(list);
            lv.setCellFactory(new Callback<ListView<Goal>, ListCell<Goal>>() {
                @Override
                public ListCell<Goal> call(ListView<Goal> param) {
                    return new GoalCell();
                }
            });

            bp.setCenter(lv);

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
                goalRoutine.getItems().setAll(Routine.values());
                goalRoutine.getSelectionModel().selectFirst();

                vbox.getChildren().addAll(goalName, goalAmount, goalUnit, goalRoutine);

                dialog.getDialogPane().contentProperty().set(vbox);
                dialog.getDialogPane().getButtonTypes().addAll(
                        new ButtonType("Add", ButtonBar.ButtonData.OK_DONE),
                        new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE)
                );

                dialog.showAndWait();

                Goal addedGoal = ss.addGoal(
                        goalName.getText(),
                        goalUnit.getText(),
                        Routine.valueOf(goalRoutine.getSelectionModel().getSelectedItem().toString().toUpperCase()),
                        Integer.parseInt(goalAmount.getText())
                );

                lv.getItems().add(addedGoal);
            });

            bp.setBottom(addButton);
        } else {
            HBox loginGroup = new HBox();
            TextField usernameField = new TextField();
            Button loginButton = new Button("Login");

            loginButton.setOnAction((event) -> {
                if (ss.createUser(usernameField.getText())) {
                    if (ss.loadUser()) {
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
