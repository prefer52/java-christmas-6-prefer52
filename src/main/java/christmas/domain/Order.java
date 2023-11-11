package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.validate.Validator.*;

public class Order {
    private Map<String, Integer> menus;

    public Order(List<String> menus) {
        this.menus = new HashMap<>();
        if (menus.size() == 1) {
            validateSingleMenuIsBeverage(menus.get(0));
        }
        for (String menu : menus) {
            String[] menuSplit = menu.split(",");
            validateExistMenu(menuSplit[0]);
            validateDuplicatedMenu(this.menus, menuSplit[0]);
            validateInteger(menuSplit[1]);
            validateIntegerIn(Integer.valueOf(menuSplit[1]), 1, 20);
        }
        validateSumOver(20, (List<Integer>) this.menus.values());
    }
}
