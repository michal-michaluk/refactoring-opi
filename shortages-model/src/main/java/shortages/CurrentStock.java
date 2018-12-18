package shortages;

public class CurrentStock {
    private long level;
    private long locked;

    public CurrentStock(long level) {
        this.level = level;
    }

    public long getLevel() {
        return level;
    }

    public long getLocked() {
        return locked;
    }
}
