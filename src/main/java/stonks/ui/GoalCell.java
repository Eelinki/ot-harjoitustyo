package stonks.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import stonks.domain.Goal;
import stonks.domain.StonksService;

public class GoalCell extends ListCell<Goal> {
    HBox hbox = new HBox();
    Label goalName = new Label("name");
    Label goalRoutine = new Label("routine");
    Label goalProgress = new Label("progress");
    Pane pane = new Pane();
    Button decrementButton = new Button("-");
    Button incrementButton = new Button("+");

    public GoalCell(StonksService stonksService) {
        super();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(goalName, goalRoutine, goalProgress, pane, decrementButton, incrementButton);
        HBox.setHgrow(pane, Priority.ALWAYS);
        decrementButton.setOnAction(event -> {
            Goal currentGoal = super.getItem();
            currentGoal.progress--;
            updateItem(currentGoal, false);
            stonksService.updateUser();
        });
        incrementButton.setOnAction(event -> {
            Goal currentGoal = super.getItem();
            currentGoal.progress++;
            updateItem(currentGoal, false);
            stonksService.updateUser();
        });
    }

    @Override
    protected void updateItem(Goal item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(hbox);
            goalName.setText(item.name);
            goalRoutine.setText(item.routine.toString());
            goalProgress.setText(item.progress + "/" + item.goal + " " + item.unit);
        }
    }
}
