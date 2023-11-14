package christmas.domain;

import christmas.type.Menus;

import java.util.*;
import java.util.List;

import static christmas.validate.Validator.*;

public class Order {
    private Map<Menus, Integer> menus;

    // 주문 목록 초기화 및 검증
    public Order(List<String> menus) {
        this.menus = new HashMap<>();
        for (String menu : menus) {
            String[] menuSplit = menu.split("-");
            validateExistMenu(menuSplit[0]);
            Menus menuType = Menus.getMenus(menuSplit[0]);
            if (menus.size() == 1) {
                validateSingleMenuIsBeverage(menuType);
            }
            validateQuantityIsInteger(menuSplit[1]);
            Integer quantity = Integer.valueOf(menuSplit[1]);
            validateDuplicatedMenu(this.menus.keySet(), menuType);
            validateIntegerIn(quantity, 1, 20);
            this.menus.put(menuType, quantity);
        }
        validateSumOver(20, this.menus.values().stream().toList());
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
            totalAmount += key.getPrice() * menus.get(key);
        }

        return totalAmount;
    }

    public String getComplimentaryMenu() {
        if (getTotalAmount() >= 120000)
            return Menus.CHAMPAGNE.getName() + " 1개\n";

        return "없음";
    }

    public List<Menus> getMenus() {
        return menus.keySet().stream().toList();
    }

    public Integer getQuantity(Menus menu) {
        return menus.get(menu);
    }

    public String getExpectedDiscountAmount(int discount) {
        return (getTotalAmount() - discount) + "원\n";
    }
}
