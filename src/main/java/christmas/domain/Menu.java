package christmas.domain;

import christmas.type.Menus;
import christmas.validate.Validator;

import java.util.List;
import java.util.Map;

public class Menu {
    private Map<String, Integer> menus;

    public Menu(List<String> menus) {
        for (String menu : menus) {
            String[] menuSplit = menu.split(",");
            Validator.validateExistMenu(menuSplit[0]);
        }
    }
}
