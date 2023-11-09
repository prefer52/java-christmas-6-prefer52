package christmas.controller;

import christmas.type.Message;
import christmas.validate.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Arrays;

import static christmas.type.Message.*;
import static christmas.validate.Validator.*;

public class ChristmasPlaner {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasPlaner() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        readDate();
    }

    private void readDate() {
        outputView.printMessages(
                Arrays.asList(new String[] {HELLO.getText(), CHOOSE_DATE.getText()})
        );
        int date = inputView.readInteger();
        validateIntegerIn(date, 1, 31);
    }
}
