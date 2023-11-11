package christmas.controller;

import christmas.view.InputView;

import java.util.Arrays;

import static christmas.type.GuideMessage.*;
import static christmas.validate.Validator.*;
import static christmas.view.OutputView.*;

public class ChristmasPlaner {

    public void start() {
        readDate();
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
}
