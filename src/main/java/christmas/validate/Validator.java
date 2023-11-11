package christmas.validate;

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

    // 숫자인지 검증
    public static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER.getError());
        }
    }

    // start~end 사이의 숫자인지 검증
    public static void validateIntegerIn(Integer input, int start, int end) {
        if (input < start || input > end) {
            throw new IllegalArgumentException(NOT_INTEGER_IN.getError());
        }
    }

    // ,,가 포함되어 있거나 ,로 시작하거나 끝나는지 검증
    public static void validateContainValidComma(String input) {
        if (input.contains(",,") || input.startsWith(",") || input.endsWith(",")) {
            throw new IllegalArgumentException(CONTAIN_INVALID_COMMA.getError());
        }
    }
}
