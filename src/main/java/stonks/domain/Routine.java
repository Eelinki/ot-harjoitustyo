package stonks.domain;

public enum Routine {
    DAILY,
    WEEKLY,
    MONTHLY;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
