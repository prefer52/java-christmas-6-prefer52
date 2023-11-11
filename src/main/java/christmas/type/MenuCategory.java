package christmas.type;

public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");
    private String name;

    MenuCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
