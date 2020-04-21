package stonks.domain;

public class Goal {
    public String name;
    public String unit;
    public Routine routine;
    public int goal;
    public int progress;

    public Goal(String name, String unit, Routine routine, int goal) {
        this.name = name;
        this.unit = unit;
        this.routine = routine;
        this.goal = goal;
        this.progress = 0;
    }
}
