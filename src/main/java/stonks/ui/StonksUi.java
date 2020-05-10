package stonks.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import stonks.dao.UserDaoImpl;
import stonks.domain.Goal;
import stonks.domain.StonksService;

import java.util.Optional;

public class StonksUi extends Application {
    private StonksService stonksService;
    
    @Override
    public void init() {
        UserDaoImpl userDao = new UserDaoImpl();

        this.stonksService = new StonksService(userDao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        
        if (stonksService.loadUser()) {
            bp.setTop(new Label("Hey " + stonksService.getCurrentUser().name + "! How was your day?"));

            ObservableList<Goal> list = FXCollections.observableArrayList(stonksService.getGoals());
            ListView<Goal> goalsListView = new ListView<>(list);
            goalsListView.setCellFactory(param -> new GoalCell(stonksService));

            bp.setCenter(goalsListView);

            Button addButton = new Button("Add a new goal");
            Button removeButton = new Button("Remove selected goal");

            addButton.setOnAction((event) -> {
                AddGoal dialog = new AddGoal();

                Optional<ButtonType> result = dialog.showAndWait();

                if(result.isPresent() && result.get() == dialog.getDialogPane().getButtonTypes().get(0)) {
                    if(!validateGoal(dialog.getGoal(), dialog.getUnit(), dialog.getAmount())) {
                        return;
                    }

                    Goal addedGoal = new Goal(
                            dialog.getGoal(),
                            dialog.getUnit(),
                            dialog.getRoutine(),
                            Integer.parseInt(dialog.getAmount())
                    );

                    stonksService.addGoal(addedGoal);

                    goalsListView.getItems().add(addedGoal);
                }
            });

            removeButton.setOnAction((event) -> {
                if(goalsListView.getSelectionModel().getSelectedItem() == null) {
                    return;
                }

                stonksService.removeGoal(goalsListView.getSelectionModel().getSelectedItem());

                goalsListView.getItems().remove(goalsListView.getSelectionModel().getSelectedIndex());
            });

            HBox buttons = new HBox();
            buttons.getChildren().addAll(addButton, removeButton);

            bp.setBottom(buttons);
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

    public boolean validateGoal(String name, String unit, String amount) {
        Alert validationAlert = new Alert(Alert.AlertType.ERROR);
        validationAlert.setHeaderText("Input not valid");

        if(name.length() < 1 || name.length() > 100) {
            validationAlert.setContentText("The length of the goal name must be between 1 and 100 characters");
            validationAlert.showAndWait();
            return false;
        }

        if(unit.length() < 1 || unit.length() > 100) {
            validationAlert.setContentText("The length of the unit must be between 1 and 100 characters");
            validationAlert.showAndWait();
            return false;
        }

        try {
            if(Integer.parseInt(amount) < 1) {
                validationAlert.setContentText("The goal amount must be larger than 0");
                validationAlert.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            validationAlert.setContentText("The goal amount must be a numeric value larger than 0");
            validationAlert.showAndWait();
            return false;
        }

        return true;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
