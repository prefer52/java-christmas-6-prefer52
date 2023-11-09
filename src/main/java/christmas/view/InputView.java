package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validate.Validator;

import static christmas.validate.Validator.*;

public class InputView {
    // 숫자 입력
    public int readInteger() {
        String date = Console.readLine();
        validateNotEmptyString(date);
        validateContainSpace(date);
        validateInteger(date);
        return Integer.parseInt(date);
    }
}
