package christmas.type;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);
    // 배지 이름
    private String badgeName;
    // 배지를 받기 위한 최소 주문 금액
    private int amountNeed;

    Badge(String badgeName, int amountNeed) {
        this.badgeName = badgeName;
        this.amountNeed = amountNeed;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getAmountNeed() {
        return amountNeed;
    }
}
