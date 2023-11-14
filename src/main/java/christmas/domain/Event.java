package christmas.domain;

import christmas.type.EventCategory;
import christmas.type.MenuCategory;
import christmas.type.Menus;

import java.util.List;

import static christmas.type.DateCategory.*;
import static christmas.type.EventCategory.*;

public class Event {
    private int date;
    private Order order;

    public Event(int date, Order order) {
        this.date = date;
        this.order = order;
    }

    public String getDiscountDetails() {
        Integer amount = order.getTotalAmount();
        if (amount < 10000) {
            return "없음";
        }
        List<Menus> menus = order.getMenus();

        return getDiscountDetail(CHRISTMAS_D_DAY_DISCOUNT, getChristmasDDayDiscount()) +
                getDiscountDetail(WEEKDAY_DISCOUNT, getWeekDayDiscount(menus)) +
                getDiscountDetail(WEEKEND_DISCOUNT, getWeekendDayDiscount(menus)) +
                getDiscountDetail(SPECIAL_DISCOUNT, getSpecialDayDiscount(menus)) +
                getDiscountDetail(COMPLIMENTARY_EVENT, getComplimentaryEvent(amount));
    }

    public String getDiscountDetail(EventCategory eventCategory, int discount) {
        if (discount == 0) {
            return "";
        }
        return eventCategory.getDescription() + ": " + discount + "원\n";
    }

    public int getChristmasDDayDiscount() {
        return -((date - 1) * CHRISTMAS_D_DAY_DISCOUNT.getDiscount() + 1000);
    }

    public int getWeekDayDiscount(List<Menus> menus) {
        if (!WEEK_DAY.getDates().contains(date)) {
            return 0;
        }

        int discount = 0;
        for (Menus menu : menus) {
            if (menu.getMenuCategory() == MenuCategory.DESSERT) {
                discount += WEEKDAY_DISCOUNT.getDiscount()*order.getQuantity(menu);
            }
        }
        return -discount;
    }

    public int getWeekendDayDiscount(List<Menus> menus) {
        if (!WEEKEND_DAY.getDates().contains(date)) {
            return 0;
        }

        int discount = 0;
        for (Menus menu : menus) {
            if (menu.getMenuCategory() == MenuCategory.MAIN) {
                discount += WEEKEND_DISCOUNT.getDiscount()*order.getQuantity(menu);
            }
        }
        return -discount;
    }

    public int getSpecialDayDiscount(List<Menus> menus) {
        if (!SPECIAL_DAY.getDates().contains(date)) {
            return 0;
        }
        return -SPECIAL_DISCOUNT.getDiscount();
    }

    public int getComplimentaryEvent(int amount) {
        if (amount < 120000)
            return 0;
        return -Menus.CHAMPAGNE.getPrice();
    }

    public String getTotalBenefitAmount() {
        return getSumOfBenefitAmount(order.getTotalAmount(), order.getMenus()) + "원\n";
    }

    public int getSumOfBenefitAmount(int amount, List<Menus> menus) {
        return getChristmasDDayDiscount() + getWeekDayDiscount(menus) +
                getWeekendDayDiscount(menus) + getSpecialDayDiscount(menus) +
                getComplimentaryEvent(amount);
    }
}