package christmas.domain;

import christmas.type.Menus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order = new Order(List.of("티본스테이크-2", "바비큐립-1", "초코케이크-2", "제로콜라-1"));

    @DisplayName("메뉴 목록에 아래 케이스가 포함되지 않을 시 실패")
    @ValueSource(strings = {"티본스테이크 2개", "바비큐립 1개", "초코케이크 2개", "제로콜라 1개"})
    @ParameterizedTest
    void getOrderMenuTest(String menu) {
        String orderMenu = order.getOrderMenu();
        assertTrue(orderMenu.contains(menu));
    }

    @DisplayName("총주문 금액이 잘못되었을 시 실패")
    @Test
    void getTotalAmountTest() {
        assertEquals(197000, order.getTotalAmount());
    }

    @DisplayName("메뉴 리스트에 테스트 케이스 메뉴가 포함되지 않았을 시 실패")
    @EnumSource(value = Menus.class, names = {"T_BONE_STEAK", "BBQ_RIBS", "CHOCOLATE_CAKE", "ZERO_COLA"})
    @ParameterizedTest
    void getMenusTest(Menus menu) {
        List<Menus> menus = order.getMenus();
        assertTrue(menus.contains(menu));
    }

    @DisplayName("메뉴마다 주문 개수가 맞지 않을시 실패")
    @EnumSource(value = Menus.class, names = {"T_BONE_STEAK", "BBQ_RIBS", "CHOCOLATE_CAKE", "ZERO_COLA"})
    @ParameterizedTest
    void getQuantityTest(Menus menu) {
        if (menu == Menus.T_BONE_STEAK)
            assertEquals(2, order.getQuantity(menu));
        if (menu == Menus.BBQ_RIBS)
            assertEquals(1, order.getQuantity(menu));
        if (menu == Menus.CHOCOLATE_CAKE)
            assertEquals(2, order.getQuantity(menu));
        if (menu == Menus.ZERO_COLA)
            assertEquals(1, order.getQuantity(menu));
    }

    @DisplayName("할인 금액을 뺀 총 주문 금액이 잘못될 시 실패")
    @ValueSource(ints = {1000, 2000, 3000, 4000, 5000})
    @ParameterizedTest
    void getExpectedDiscountAmountTest(int discount) {
        assertEquals(order.getTotalAmount() - discount + "원\n", order.getExpectedDiscountAmount(discount));
    }
}