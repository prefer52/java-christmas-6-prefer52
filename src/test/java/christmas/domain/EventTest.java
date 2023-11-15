package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private final Order order = new Order(List.of("티본스테이크-2", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
    private Event event;

    @DisplayName("크리스마스 디데이 할인 금액이 잘못 될 시 실패")
    @CsvSource({"1, 1000", "2, 1100", "25, 3400", "26, 0"})
    @ParameterizedTest
    void getChristmasDDayDiscountTest(int date, int discount) {
        event = new Event(date);
        assertEquals(discount, event.getChristmasDDayDiscount());
    }

    @DisplayName("평일 할인 금액이 잘못될 시 실패")
    @CsvSource({"3, 4046", "7, 4046", "8, 0", "9, 0"})
    @ParameterizedTest
    void getWeekDayDiscountTest(int date, int discount) {
        event = new Event(date);
        assertEquals(discount, event.getWeekDayDiscount(order.getMenus(), order));
    }

    @DisplayName("주말 할인 금액이 잘못될 시 실패")
    @CsvSource({"3, 0", "7, 0", "8, 6069", "9, 6069"})
    @ParameterizedTest
    void getWeekendDayDiscountTest(int date, int discount) {
        event = new Event(date);
        assertEquals(discount, event.getWeekendDayDiscount(order.getMenus(), order));
    }

    @Test
    void getSpecialDayDiscountTest() {
    }

    @Test
    void getComplimentaryMenuTest() {
    }

    @Test
    void getComplimentaryEventTest() {
    }

    @Test
    void getTotalBenefitAmountTest() {
    }

    @Test
    void getSumOfBenefitAmountTest() {
    }

    @Test
    void getSumOfDiscountAmountTest() {
    }

    @Test
    void getDecemberEventBadgeTest() {
    }
}