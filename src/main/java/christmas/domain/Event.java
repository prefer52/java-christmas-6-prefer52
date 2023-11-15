package christmas.domain;

import christmas.type.Badge;
import christmas.type.EventCategory;
import christmas.type.MenuCategory;
import christmas.type.Menus;

import java.util.List;

import static christmas.type.DateCategory.*;
import static christmas.type.EventCategory.*;

public class Event {
    // 이벤트를 적용받기 위한 최소 금액
    private final int EVENT_NEED_AMOUNT = 10000;
    // 증정 메뉴를 증정받기 위한 최소 금액
    private final int COMPLIMENTARY_NEED_AMOUNT = 120000;
    private int date;

    public Event(int date) {
        this.date = date;
    }

    // 혜택 내역을 문자열로 반환하는 메서드
    public String getDiscountDetails(Order order) {
        int amount = order.getTotalAmount();
        if (amount < EVENT_NEED_AMOUNT) {
            return "없음";
        }

        List<Menus> menus = order.getMenus();
        return getDiscountDetail(CHRISTMAS_D_DAY_DISCOUNT, getChristmasDDayDiscount()) +
                getDiscountDetail(WEEKDAY_DISCOUNT, getWeekDayDiscount(menus, order)) +
                getDiscountDetail(WEEKEND_DISCOUNT, getWeekendDayDiscount(menus, order)) +
                getDiscountDetail(SPECIAL_DISCOUNT, getSpecialDayDiscount()) +
                getDiscountDetail(COMPLIMENTARY_EVENT, getComplimentaryEvent(amount));
    }

    // 혜택 내역과 금액을 문자열로 반환하는 메서드
    public String getDiscountDetail(EventCategory eventCategory, int discount) {
        if (discount == 0) {
            return "";
        }
        return eventCategory.getDescription() + ": " + -discount + "원\n";
    }

    // 크리스마스 디데이 할인 금액을 반환하는 메서드
    public int getChristmasDDayDiscount() {
        return ((date - 1) * CHRISTMAS_D_DAY_DISCOUNT.getDiscount() + CHRISTMAS_D_DAY_DISCOUNT.getDefaultBenefit());
    }

    // 평일 할인 금액을 반환하는 메서드
    public int getWeekDayDiscount(List<Menus> menus, Order order) {
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

    // 주말 할인 금액을 반환하는 메서드
    public int getWeekendDayDiscount(List<Menus> menus, Order order) {
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

    // 특별 할인 금액을 반환하는 메서드
    public int getSpecialDayDiscount() {
        if (!SPECIAL_DAY.getDates().contains(date)) {
            return 0;
        }
        return SPECIAL_DISCOUNT.getDiscount();
    }

    // 증정 메뉴 문자열을 반환하는 메서드
    public String getComplimentaryMenu(int amount) {
        if (getComplimentaryEvent(amount) >= 0)
            return Menus.CHAMPAGNE.getMenuName() + " 1개\n";

        return "없음";
    }

    // 증정 메뉴 가격을 반환하는 메서드
    public int getComplimentaryEvent(int amount) {
        if (amount < COMPLIMENTARY_NEED_AMOUNT)
            return 0;
        return Menus.CHAMPAGNE.getPrice();
    }

    // 총혜택 금액을 문자열로 반환하는 메서드
    public String getTotalBenefitAmount(Order order) {
        return -getSumOfBenefitAmount(order) + "원\n";
    }

    // 총혜택 금액을 정수로 반환하는 메서드
    public int getSumOfBenefitAmount(Order order) {
        int amount = order.getTotalAmount();
        List<Menus> menus = order.getMenus();
        return getSumOfDiscountAmount(order) + getComplimentaryEvent(amount);
    }

    // 총 할인 금액을 정수로 반환하는 메서드
    public int getSumOfDiscountAmount(Order order) {
        List<Menus> menus = order.getMenus();
        return getChristmasDDayDiscount() + getWeekDayDiscount(menus, order) +
                getWeekendDayDiscount(menus, order) + getSpecialDayDiscount();
    }

    // 12월 이벤트 배지 문자열을 반환하는 메서드
    public String getDecemberEventBadge(Order order) {
        int benefitAmount = getSumOfBenefitAmount(order);
        if (benefitAmount > Badge.SANTA.getAmountNeed()) {
            return Badge.SANTA.getBadgeName() + "\n";
        }
        if (benefitAmount > Badge.TREE.getAmountNeed()) {
            return Badge.TREE.getBadgeName() + "\n";
        }
        if (benefitAmount > Badge.STAR.getAmountNeed()) {
            return Badge.STAR.getBadgeName() + "\n";
        }
        return "없음\n";
    }
}