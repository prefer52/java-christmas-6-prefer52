package christmas.validate;

public class Validator {
    // 빈 문자열이거나 NULL인지 검증
    public static void validateNotEmptyString(String input) {
        if (input == null || input.isEmpty())
            throw new IllegalArgumentException("");
        // 에러 메시지를 enum으로 구현
    }

    public static void validateContainSpace(String input) {
        if (input.contains(" "))
            throw new IllegalArgumentException("");
        // 에러 메시지를 enum으로 구현
    }

    public static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("");
            // 에러 메시지를 enum으로 구현
        }
    }
}
