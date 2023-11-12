package christmas.type;

import static christmas.type.MenuCategory.*;

public enum Menus {
    MUSHROOM_SOUP("양송이수프", APPETIZER, 6000),
    TAPAS("타파스", APPETIZER, 5500),
    CAESAR_SALAD("시저샐러드", APPETIZER, 8000),
    T_BONE_STEAK("티본스테이크", MAIN, 55000),
    BBQ_RIBS("바비큐립", MAIN, 54000),
    SEAFOOD_PASTA("해산물파스타", MAIN, 35000),
    CHRISTMAS_PAST("크리스마스파스타", MAIN, 25000),
    CHOCOLATE_CAKE("초코케이크", DESSERT, 15000),
    ICE_CREAM("아이스크림", DESSERT, 5000),
    ZERO_COLA("제로콜라", BEVERAGE, 3000),
    RED_WINE("레드와인", BEVERAGE, 60000),
    CHAMPAGNE("샴페인", BEVERAGE, 25000);
    private String name;
    private MenuCategory menuCategory;
    private int price;

    Menus(String name, MenuCategory menuCategory, int price) {
        this.name = name;
        this.menuCategory = menuCategory;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public int getPrice() {
        return price;
    }

    public static boolean inputInMenus(String input) {
        for (Menus menu : Menus.values()) {
            if (menu.getName().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static Menus getMenus(String input) {
        for (Menus menu : Menus.values()) {
            if (menu.getName().equals(input)) {
                return menu;
            }
        }
        return null;
    }
}
