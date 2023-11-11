package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

import static christmas.validate.Validator.*;

public class InputView {
    // 숫자 입력
    public static int readDate() {
        String date = Console.readLine();
        validateNotEmptyString(date);
        validateContainSpace(date);
        validateInteger(date);
        return Integer.parseInt(date);
    }

    public static List<String> readMenus() {
        String menu = Console.readLine();
        validateNotEmptyString(menu);
        validateContainSpace(menu);
        validateContainValidComma(menu);
        return List.of(menu.split(","));
    }
}
