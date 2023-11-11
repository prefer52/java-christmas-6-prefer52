package christmas.controller;

import christmas.domain.Order;
import christmas.view.InputView;

import java.util.Arrays;
import java.util.List;

import static christmas.type.GuideMessage.*;
import static christmas.validate.Validator.*;
import static christmas.view.OutputView.*;

public class ChristmasPlaner {
    private Order order;

    public void start() {
        readDate();
        readMenu();
    }

    private void readDate() {
        printMessages(
                Arrays.asList(new String[]{HELLO.getText(), CHOOSE_DATE.getText()})
        );
        while (true) {
            try {
                int date = InputView.readDate();
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
            } catch (IllegalArgumentException e) {
                printMessage(e.getMessage());
                continue;
            }
            break;
        }
    }
}
