package christmas.type;

public enum EventCategory {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 100, 1000),
    COMPLIMENTARY_EVENT("증정 이벤트", 0, 0),
    SPECIAL_DISCOUNT("특별 할인", 1000 ,0),
    WEEKDAY_DISCOUNT("평일 할인", 2023, 0),
    WEEKEND_DISCOUNT("주말 할인", 2023, 0);
    private String description;
    private int discount;
    private int defaultBenefit;

    EventCategory(String description, int discount, int defaultBenefit) {
        this.description = description;
        this.discount = discount;
        this.defaultBenefit = defaultBenefit;
    }

    public String getDescription() {
        return description;
    }

    public int getDiscount() {
        return discount;
    }

    public int getDefaultBenefit() {
        return defaultBenefit;
    }
}
