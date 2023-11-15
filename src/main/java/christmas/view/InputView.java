package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

import static christmas.validate.Validator.*;

public class InputView {
    // 날짜 입력
    public static int readDate() {
        String date = Console.readLine();
        validateNotEmptyString(date);
        validateContainSpace(date);
        validateDateIsInteger(date);
        return Integer.parseInt(date);
    }

    // 메뉴 입력
    public static List<String> readMenus() {
        String menu = Console.readLine();
        validateNotEmptyString(menu);
        validateContainSpace(menu);
        validateContainInvalidComma(menu);
        return new ArrayList<>(List.of(menu.split(",")));
    }
}
