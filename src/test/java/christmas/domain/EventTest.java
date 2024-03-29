package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        assertEquals(discount, event.getChristmasDDayDiscount(order.getTotalAmount()));
    }

    @DisplayName("평일 할인 금액이 잘못될 시 실패")
    @CsvSource({"3, 4046", "7, 4046", "8, 0", "9, 0"})
    @ParameterizedTest
    void getWeekDayDiscountTest(int date, int discount) {
        event = new Event(date);
        assertEquals(discount, event.getWeekDayDiscount(order.getMenus(), order, order.getTotalAmount()));
    }

    @DisplayName("주말 할인 금액이 잘못될 시 실패")
    @CsvSource({"3, 0", "7, 0", "8, 6069", "9, 6069"})
    @ParameterizedTest
    void getWeekendDayDiscountTest(int date, int discount) {
        event = new Event(date);
        assertEquals(discount, event.getWeekendDayDiscount(order.getMenus(), order, order.getTotalAmount()));
    }

    @DisplayName("특별 할인 금액이 잘못될 시 실패")
    @CsvSource({"3, 1000", "10, 1000", "25, 1000", "31, 1000"})
    @ParameterizedTest
    void getSpecialDayDiscountTest(int date, int discount) {
        event = new Event(date);
        assertEquals(discount, event.getSpecialDayDiscount(order.getTotalAmount()));
    }

    @DisplayName("총주문 금액에 반환되는 증정 메뉴의 가격이 잘못될 시 실패")
    @CsvSource({"119999, 0", "120000, 25000", "120001, 25000"})
    @ParameterizedTest
    void getComplimentaryEventTest(int amount, int complimentaryAmount) {
        event = new Event(3);
        assertEquals(complimentaryAmount, event.getComplimentaryEvent(amount));
    }

    @DisplayName("총 혜택 금액이 잘못될 시 실패")
    @CsvSource({"3, 31246", "25, 33446", "26, 29046"})
    @ParameterizedTest
    void getSumOfBenefitAmountTest(int date, int benefitAmount) {
        event = new Event(date);
        assertEquals(benefitAmount, event.getSumOfBenefitAmount(order));
    }

    @DisplayName("총 할인 금액이 잘못될 시 실패")
    @CsvSource({"3, 6246", "25, 8446", "26, 4046"})
    @ParameterizedTest
    void getSumOfDiscountAmountTest(int date, int discount) {
        event = new Event(date);
        assertEquals(discount, event.getSumOfDiscountAmount(order));
    }

    @DisplayName("총 할인 금액이 잘못될 시 실패")
    @CsvSource({"3, '산타\n'", "25, '산타\n'", "26, '산타\n'"})
    @ParameterizedTest
    void getDecemberEventBadgeTest(int date, String badge) {
        event = new Event(date);
        assertEquals(badge, event.getDecemberEventBadge(order));
    }
}