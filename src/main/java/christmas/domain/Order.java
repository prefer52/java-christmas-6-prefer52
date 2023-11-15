package christmas.domain;

import christmas.type.Menus;

import java.util.*;
import java.util.List;

import static christmas.validate.Validator.*;

public class Order {
    private final int COMPLIMENTARY_NEED_AMOUNT = 120000;
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

    // 주문한 메뉴를 문자열로 반환하는 메서드
    public String getOrderMenu() {
        String orderMenu = "";
        Set<Menus> keys = menus.keySet();
        for (Menus key : keys) {
            orderMenu += key.getMenuName() + " " + menus.get(key) + "개\n";
        }

        return orderMenu;
    }

    // 총주문 금액을 반환하는 메서드
    public int getTotalAmount() {
        int totalAmount = 0;
        Set<Menus> keys = menus.keySet();
        for (Menus key : keys) {
            totalAmount += key.getPrice() * menus.get(key);
        }

        return totalAmount;
    }

    // 메뉴 종류를 리스트로 반환하는 메서드
    public List<Menus> getMenus() {
        return menus.keySet().stream().toList();
    }

    // 각 메뉴마다의 주문 개수를 반환하는 메서드
    public Integer getQuantity(Menus menu) {
        return menus.get(menu);
    }

    // 할인 금액을 뺀 총 주문 금액을 반환하는 메서드
    public String getExpectedDiscountAmount(int discount) {
        return (getTotalAmount() - discount) + "원\n";
    }
}
