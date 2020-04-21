package stonks.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import stonks.domain.Goal;

public class GoalCell extends ListCell<Goal> {
    HBox hbox = new HBox();
    Label goalName = new Label("name");
    Label goalRoutine = new Label("routine");
    Label goalProgress = new Label("progress");
    Pane pane = new Pane();
    Button button = new Button("+");

    public GoalCell() {
        super();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(goalName, goalRoutine, goalProgress, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        button.setOnAction(event -> {
            Goal currentGoal = super.getItem();
            currentGoal.progress++;
            updateItem(currentGoal, false);
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
