package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order = new Order(List.of("티본스테이크-2", "바비큐립-1", "초코케이크-2", "제로콜라-1"));

    @DisplayName("크리스마스 디데이 할인 금액이 잘못 될 시 실패")
    @ValueSource(strings = {"티본스테이크 2개", "바비큐립 1개", "초코케이크 2개", "제로콜라 1개"})
    @ParameterizedTest
    void getOrderMenuTest(String menu) {
        String orderMenu = order.getOrderMenu();
        assertTrue(orderMenu.contains(menu));
    }

    @Test
    void getTotalAmountTest() {
    }

    @Test
    void getMenusTest() {
    }

    @Test
    void getQuantityTest() {
    }

    @Test
    void getExpectedDiscountAmountTest() {
    }
}