package christmas.domain;

import christmas.type.Badge;
import christmas.type.EventCategory;
import christmas.type.MenuCategory;
import christmas.type.Menus;

import java.util.List;

import static christmas.type.DateCategory.*;
import static christmas.type.EventCategory.*;

public class Event {
    private final int EVENT_NEED_AMOUNT = 10000;
    private final int COMPLIMENTARY_NEED_AMOUNT = 120000;
    private int date;
    private Order order;

    public Event(int date, Order order) {
        this.date = date;
        this.order = order;
    }

    // 혜택 내역을 문자열로 반환하는 함수
    public String getDiscountDetails() {
        Integer amount = order.getTotalAmount();
        if (amount < EVENT_NEED_AMOUNT) {
            return "없음";
        }
        List<Menus> menus = order.getMenus();

        return getDiscountDetail(CHRISTMAS_D_DAY_DISCOUNT, getChristmasDDayDiscount()) +
                getDiscountDetail(WEEKDAY_DISCOUNT, getWeekDayDiscount(menus)) +
                getDiscountDetail(WEEKEND_DISCOUNT, getWeekendDayDiscount(menus)) +
                getDiscountDetail(SPECIAL_DISCOUNT, getSpecialDayDiscount()) +
                getDiscountDetail(COMPLIMENTARY_EVENT, getComplimentaryEvent(amount));
    }

    // 혜택 내역과 금액을 문자열로 반환하는 함수
    public String getDiscountDetail(EventCategory eventCategory, int discount) {
        if (discount == 0) {
            return "";
        }
        return eventCategory.getDescription() + ": " + -discount + "원\n";
    }

    // 크리스마스 디데이 할인 금액을 반환하는 함수
    public int getChristmasDDayDiscount() {
        return ((date - 1) * CHRISTMAS_D_DAY_DISCOUNT.getDiscount() + CHRISTMAS_D_DAY_DISCOUNT.getDefaultBenefit());
    }

    // 평일 할인 금액을 반환하는 함수
    public int getWeekDayDiscount(List<Menus> menus) {
        if (!WEEK_DAY.getDates().contains(date)) {
            return 0;
        }

        int discount = 0;
        for (Menus menu : menus) {
            if (menu.getMenuCategory() == MenuCategory.DESSERT) {
                discount += WEEKDAY_DISCOUNT.getDiscount() * order.getQuantity(menu);
            }
        }
        return discount;
    }

    // 주말 할인 금액을 반환하는 함수
    public int getWeekendDayDiscount(List<Menus> menus) {
        if (!WEEKEND_DAY.getDates().contains(date)) {
            return 0;
        }

        int discount = 0;
        for (Menus menu : menus) {
            if (menu.getMenuCategory() == MenuCategory.MAIN) {
                discount += WEEKEND_DISCOUNT.getDiscount() * order.getQuantity(menu);
            }
        }
        return discount;
    }

    // 특별 할인 금액을 반환하는 함수
    public int getSpecialDayDiscount() {
        if (!SPECIAL_DAY.getDates().contains(date)) {
            return 0;
        }
        return SPECIAL_DISCOUNT.getDiscount();
    }

    // 증정 메뉴 가격을 반환하는 함수
    public int getComplimentaryEvent(int amount) {
        if (amount < COMPLIMENTARY_NEED_AMOUNT)
            return 0;
        return Menus.CHAMPAGNE.getPrice();
    }

    // 총혜택 금액을 문자열로 반환하는 함수
    public String getTotalBenefitAmount() {
        return -getSumOfBenefitAmount() + "원\n";
    }

    // 총혜택 금액을 정수로 반환하는 함수
    public int getSumOfBenefitAmount() {
        Integer amount = order.getTotalAmount();
        return getSumOfDiscountAmount() + getComplimentaryEvent(amount);
    }

    // 총 할인 금액을 정수로 반환하는 함수
    public int getSumOfDiscountAmount() {
        List<Menus> menus = order.getMenus();
        return getChristmasDDayDiscount() + getWeekDayDiscount(menus) +
                getWeekendDayDiscount(menus) + getSpecialDayDiscount();
    }

    // 12월 이벤트 배지 문자열을 반환하는 함수
    public String getDecemberEventBadge() {
        int benefitAmount = getSumOfBenefitAmount();
        if (benefitAmount > Badge.SANTA.getAmountNeed()) {
            return Badge.SANTA.getDescription() + "\n";
        }
        if (benefitAmount > Badge.TREE.getAmountNeed()) {
            return Badge.TREE.getDescription() + "\n";
        }
        if (benefitAmount > Badge.STAR.getAmountNeed()) {
            return Badge.STAR.getDescription() + "\n";
        }
        return "없음\n";
    }
}