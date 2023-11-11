package christmas.type;

import static christmas.type.MenuCategory.*;

public enum Menus {
    MUSHROOM_SOUP("양송이수프", APPETIZER),
    TAPAS("타파스", APPETIZER),
    CAESAR_SALAD("시저샐러드", APPETIZER),
    T_BONE_STEAK("티본스테이크", MAIN),
    BBQ_RIBS("바비큐립", MAIN),
    SEAFOOD_PASTA("해산물파스타", MAIN),
    CHRISTMAS_PAST("크리스마스파스타", MAIN),
    CHOCOLATE_CAKE("초코케이크", DESSERT),
    ICE_CREAM("아이스크림", DESSERT),
    ZERO_COLA("제로콜라", BEVERAGE),
    RED_WINE("레드와인", BEVERAGE),
    CHAMPAGNE("샴페인", BEVERAGE);
    private String name;
    private MenuCategory menuCategory;

    Menus(String name, MenuCategory appetizer) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public static boolean inputInMenus(String input) {
        for (Menus menu : Menus.values()) {
            if (menu.getName().equals(input)) {
                return true;
            }
        }
        return false;
    }
}
