package christmas.domain;

import java.util.*;

import static christmas.validate.Validator.*;

public class Order {
    private Map<String, Integer> menus;

    public Order(List<String> menus) {
        this.menus = new HashMap<>();
        for (String menu : menus) {
            String[] menuSplit = menu.split("-");
            validateExistMenu(menuSplit[0]);
            if (menus.size() == 1) {
                validateSingleMenuIsBeverage(menuSplit[0]);
            }
            validateDuplicatedMenu(this.menus, menuSplit[0]);
            validateInteger(menuSplit[1]);
            validateIntegerIn(Integer.valueOf(menuSplit[1]), 1, 20);
            this.menus.put(menuSplit[0], Integer.valueOf(menuSplit[1]));
        }
        validateSumOver(20, new ArrayList<>(this.menus.values()));
    }

    public String getOrderMenu() {
        String orderMenu = "";

        Set<String> keys = menus.keySet();
        for (String key : keys) {
            orderMenu += key + " " + menus.get(key) + "개\n";
        }

        return orderMenu;
    }
}
