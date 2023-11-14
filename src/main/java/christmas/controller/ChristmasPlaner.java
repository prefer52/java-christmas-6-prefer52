package christmas.controller;

import christmas.domain.Event;
import christmas.domain.Order;
import christmas.view.InputView;

import java.util.Arrays;
import java.util.List;

import static christmas.type.GuideMessage.*;
import static christmas.validate.Validator.*;
import static christmas.view.OutputView.*;

public class ChristmasPlaner {
    private Order order;
    private Event event;
    private int date;

    // 금액 출력 형식 맞추기 https://jamesdreaming.tistory.com/203
    public void start() {
        readDate();
        readMenu();
        showBenefitPreview();
    }

    private void readDate() {
        printMessages(Arrays.asList(new String[]{HELLO.getText(), CHOOSE_DATE.getText()}));
        while (true) {
            try {
                date = InputView.readDate();
                validateIntegerIn(date, 1, 31);
            } catch (IllegalArgumentException e) {
                printMessage(e.getMessage());
                continue;
            }
            break;
        }
    }

    private void readMenu() {
        printMessage(CHOOSE_MENU.getText());
        while (true) {
            try {
                List<String> menus = InputView.readMenus();
                validateContainValidDash(menus);
                validateSplitSizeIsTwo(menus);
                order = new Order(menus);
                event = new Event(date, order);
            } catch (IllegalArgumentException e) {
                printMessage(e.getMessage());
                continue;
            }
            break;
        }
    }

    private void showBenefitPreview() {
        printMessage(PREVIEW_BENEFIT.getText() + "\n");
        showOrderMenu();
        showTotalAmountBeforeDiscount();
        showComplimentaryMenu();
        showBenefitDetail();
        showTotalBenefitAmount();
        showExpectedDiscountAmount();
    }

    private void showOrderMenu() {
        printMessage(ORDERED_MENU.getText());
        printMessage(order.getOrderMenu());
    }

    private void showTotalAmountBeforeDiscount() {
        printMessage(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUND.getText());
        printMessage(order.getTotalAmount() + "\n");
    }

    private void showComplimentaryMenu() {
        printMessage(COMPLIMENTARY_ITEMS.getText());
        printMessage(order.getComplimentaryMenu());
    }

    private void showBenefitDetail() {
        printMessage(BENEFIT_DETAIL.getText());
        printMessage(event.getDiscountDetails());
    }

    private void showTotalBenefitAmount() {
        printMessage(TOTAL_BENEFIT_AMOUNT.getText());
        printMessage(event.getTotalBenefitAmount());
    }

    private void showExpectedDiscountAmount() {
        printMessage(EXPECTED_DISCOUNT_AMOUNT.getText());
        printMessage(order.getExpectedDiscountAmount(event.getSumOfDiscountAmount()));
    }
}
