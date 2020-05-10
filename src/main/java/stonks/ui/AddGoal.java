package stonks.ui;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import stonks.domain.Routine;

public final class AddGoal extends Dialog {
    private TextField goalName = new TextField();
    private TextField goalAmount = new TextField();
    private TextField goalUnit = new TextField();
    private ComboBox goalRoutine = new ComboBox();

    public AddGoal() {
        setTitle("Add a new goal");
        setHeaderText("Add a new goal");

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        goalName.setPromptText("Goal");
        goalAmount.setPromptText("amount");
        goalUnit.setPromptText("unit (kilograms, times, bottles etc.)");
        goalRoutine.getItems().setAll((Object[]) Routine.values());
        goalRoutine.getSelectionModel().selectFirst();

        vbox.getChildren().addAll(goalName, goalAmount, goalUnit, goalRoutine);

        getDialogPane().contentProperty().set(vbox);
        getDialogPane().getButtonTypes().addAll(
            new ButtonType("Add", ButtonBar.ButtonData.OK_DONE),
            new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE)
        );
    }

    public String getGoal() {
        return goalName.getText();
    }

    public String getAmount() {
        return goalAmount.getText();
    }

    public String getUnit() {
        return goalUnit.getText();
    }

    public Routine getRoutine() {
        return Routine.valueOf(goalRoutine.getSelectionModel().getSelectedItem().toString().toUpperCase());
    }
}
