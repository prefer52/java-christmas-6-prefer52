package christmas.controller;

import christmas.domain.Event;
import christmas.domain.Order;
import christmas.view.InputView;

import java.util.List;

import static christmas.type.GuideMessage.*;
import static christmas.validate.Validator.*;
import static christmas.view.OutputView.*;

public class ChristmasPlaner {
    private Order order;
    private Event event;
    private int date;

    // 전체적인 프로그램 시작
    public void start() {
        readDate();
        readMenu();
        showBenefitPreview();
    }

    // 날짜를 읽는 메서드
    private void readDate() {
        printMessages(List.of(HELLO.getText(), CHOOSE_DATE.getText()));
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

    // 메뉴를 읽는 메서드
    private void readMenu() {
        printMessage(CHOOSE_MENU.getText());
        while (true) {
            try {
                List<String> menus = InputView.readMenus();
                validateContainInvalidDash(menus);
                validateSplitSizeIsTwo(menus);
                order = new Order(menus);
                event = new Event(date);
            } catch (IllegalArgumentException e) {
                printMessage(e.getMessage());
                continue;
            }
            break;
        }
    }

    // 혜택 미리보기를 보여주는 메서드
    private void showBenefitPreview() {
        printMessage(PREVIEW_BENEFIT.getText() + "\n");
        showOrderMenu();
        showTotalAmountBeforeDiscount();
        showComplimentaryMenu();
        showBenefitDetail();
        showTotalBenefitAmount();
        showExpectedDiscountAmount();
        showDecemberEventBadge();
    }

    // 주문한 메뉴를 보여주는 메서드
    private void showOrderMenu() {
        printMessage(ORDERED_MENU.getText());
        printMessage(order.getOrderMenu());
    }

    // 할인 전 총 주문 금액을 보여주는 메서드
    private void showTotalAmountBeforeDiscount() {
        printMessage(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getText());
        printMessage(order.getTotalAmount() + "원\n");
    }

    // 증정 메뉴를 보여주는 메서드
    private void showComplimentaryMenu() {
        printMessage(COMPLIMENTARY_ITEMS.getText());
        printMessage(event.getComplimentaryMenu(order.getTotalAmount()));
    }

    // 혜택 내역을 보여주는 메서드
    private void showBenefitDetail() {
        printMessage(BENEFIT_DETAIL.getText());
        printMessage(event.getDiscountDetails(order));
    }

    // 총혜택금액을 보여주는 메서드
    private void showTotalBenefitAmount() {
        printMessage(TOTAL_BENEFIT_AMOUNT.getText());
        printMessage(event.getTotalBenefitAmount(order));
    }

    // 할인 후 예상 결제 금액을 보여주는 메서드
    private void showExpectedDiscountAmount() {
        printMessage(EXPECTED_DISCOUNT_AMOUNT.getText());
        printMessage(order.getExpectedDiscountAmount(event.getSumOfDiscountAmount(order)));
    }

    // 받은 12월 이벤트 배지를 보여주는 메서드
    private void showDecemberEventBadge() {
        printMessage(DECEMBER_EVENT_BADGE.getText());
        printMessage(event.getDecemberEventBadge(order));
    }
}
