package christmas.validate;

import christmas.type.MenuCategory;
import christmas.type.Menus;

import java.util.List;
import java.util.Set;

import static christmas.type.ErrorMessage.*;

public class Validator {
    // 빈 문자열이거나 NULL인지 검증
    public static void validateNotEmptyString(String input) {
        if (input == null || input.isEmpty())
            throw new IllegalArgumentException(EMPTY_STRING.getError());
    }

    // 공백 문자를 포함하는지 검증
    public static void validateContainSpace(String input) {
        if (input.contains(" "))
            throw new IllegalArgumentException(CONTAIN_SPACE.getError());
    }

    // 날짜가 숫자인지 검증
    public static void validateDateIsInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_DATE.getError());
        }
    }

    // 날짜가 숫자인지 검증
    public static void validateQuantityIsInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getError());
        }
    }

    // start~end 사이의 숫자인지 검증
    public static void validateIntegerIn(Integer input, int start, int end) {
        if (input < start || input > end) {
            throw new IllegalArgumentException(NOT_DATE.getError());
        }
    }

    // ,,가 포함되어 있거나 ,로 시작하거나 끝나는지 검증
    public static void validateContainInvalidComma(String input) {
        if (input.contains(",,") || input.startsWith(",") || input.endsWith(",")) {
            throw new IllegalArgumentException(CONTAIN_INVALID_COMMA.getError());
        }
    }

    // --가 포함되어 있거나, -로 시작하거나 끝나는지 검증
    public static void validateContainInvalidDash(List<String> inputs) {
        for (String input : inputs) {
            if (input.contains("--") || input.startsWith("-") || input.endsWith("-")) {
                throw new IllegalArgumentException(INVALID_ORDER.getError());
            }
        }
    }

    // -를 기준으로 2개로 분리되는지 검증
    public static void validateSplitSizeIsTwo(List<String> inputs) {
        for (String input : inputs) {
            if (List.of(input.split("-")).size() != 2) {
                throw new IllegalArgumentException(INVALID_ORDER.getError());
            }
        }
    }

    // 문자열이 메뉴에 존재하는 문자열인지 검증
    public static void validateExistMenu(String input) {
        if (!Menus.inputInMenus(input)) {
            throw new IllegalArgumentException(INVALID_ORDER.getError());
        }
    }

    // 주문한 메뉴 중 중복되는 메뉴가 존재하는지 검증
    public static void validateDuplicatedMenu(Set<Menus> menus, Menus input) {
        if (menus.contains(input)) {
            throw new IllegalArgumentException(INVALID_ORDER.getError());
        }
    }

    // 주문한 메뉴가 단 하나일 시 그게 음료인지 검증
    public static void validateSingleMenuIsBeverage(Menus input) {
        if (input.getMenuCategory().equals(MenuCategory.BEVERAGE)) {
            throw new IllegalArgumentException(INVALID_ORDER.getError());
        }
    }

    // 전체 주문 메뉴 수가 20개를 초과하는지 검증
    public static void validateSumOver(int value, List<Integer> inputs) {
        int sum = 0;
        for (Integer number : inputs) {
            sum += number;
        }

        if (sum > value) {
            throw new IllegalArgumentException(TOO_MANY_ORDER.getError());
        }
    }
}
