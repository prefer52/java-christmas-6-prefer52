package christmas.type;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);
    private String description;
    private int amountNeed;

    Badge(String description, int amountNeed) {
        this.description = description;
        this.amountNeed = amountNeed;
    }

    public String getDescription() {
        return description;
    }

    public int getAmountNeed() {
        return amountNeed;
    }
}
