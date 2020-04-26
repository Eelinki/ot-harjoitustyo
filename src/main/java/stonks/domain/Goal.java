package stonks.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Goal {
    public String name;
    public String unit;
    public Routine routine;
    public int goal;
    public int progress;

    @JsonCreator
    public Goal(@JsonProperty("name") String name, @JsonProperty("unit") String unit, @JsonProperty("routine") Routine routine, @JsonProperty("goal") int goal, @JsonProperty("progress") int progress) {
        this.name = name;
        this.unit = unit;
        this.routine = routine;
        this.goal = goal;
        this.progress = progress;
    }

    public Goal(String name, String unit, Routine routine, int goal) {
        this.name = name;
        this.unit = unit;
        this.routine = routine;
        this.goal = goal;
        this.progress = 0;
    }
}
