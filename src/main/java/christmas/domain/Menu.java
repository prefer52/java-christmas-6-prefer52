package christmas.domain;

import christmas.type.Menus;
import christmas.validate.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.validate.Validator.*;

public class Menu {
    private Map<String, Integer> menus;

    public Menu(List<String> menus) {
        this.menus = new HashMap<>();
        for (String menu : menus) {
            String[] menuSplit = menu.split(",");
            validateExistMenu(menuSplit[0]);
            validateDuplicatedMenu(this.menus, menuSplit[0]);
            validateInteger(menuSplit[1]);
            validateIntegerIn(Integer.valueOf(menuSplit[1]), 1, 20);
        }
    }
}
