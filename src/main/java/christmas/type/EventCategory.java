package christmas.type;

public enum EventCategory {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 100),
    COMPLIMENTARY_EVENT("증정 이벤트", 0),
    SPECIAL_DISCOUNT("특별 할인", 1000),
    WEEKDAY_DISCOUNT("평일 할인", 2023),
    WEEKEND_DISCOUNT("주말 할인", 2023);
    private String description;
    private int discount;

    EventCategory(String description, int discount) {
        this.description = description;
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public int getDiscount() {
        return discount;
    }
}
