package christmas.domain;

import christmas.type.Menus;

import java.awt.*;
import java.util.*;
import java.util.List;

import static christmas.validate.Validator.*;

public class Order {
    private Map<Menus, Integer> menus;

    public Order(List<String> menus) {
        this.menus = new HashMap<>();
        for (String menu : menus) {
            String[] menuSplit = menu.split("-");
            validateExistMenu(menuSplit[0]);
            if (menus.size() == 1) {
                validateSingleMenuIsBeverage(menuSplit[0]);
            }
            validateDuplicatedMenu(this.menus.keySet(), menuSplit[0]);
            validateInteger(menuSplit[1]);
            validateIntegerIn(Integer.valueOf(menuSplit[1]), 1, 20);
            this.menus.put(Menus.getMenus(menuSplit[0]), Integer.valueOf(menuSplit[1]));
        }
        validateSumOver(20, new ArrayList<>(this.menus.values()));
    }

    public String getOrderMenu() {
        String orderMenu = "";
        Set<Menus> keys = menus.keySet();
        for (Menus key : keys) {
            orderMenu += key.getName() + " " + menus.get(key) + "개\n";
        }

        return orderMenu;
    }

    public Integer getTotalAmount() {
        Integer totalAmount = 0;
        Set<Menus> keys = menus.keySet();
        for (Menus key : keys) {
            totalAmount += key.getPrice()*menus.get(key);
        }

        return totalAmount;
    }
}
