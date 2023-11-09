package christmas.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static christmas.validate.Validator.*;

class ValidatorTest {

    @DisplayName("빈 문자열이거나 NULL일 경우에 대한 예외 처리")
    @NullSource
    @EmptySource
    @ParameterizedTest
    void validateNotEmptyStringTest(String input) {
        assertThatThrownBy(() ->
                validateNotEmptyString(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("공백 문자를 포함하는 경우에 대한 예외 처리")
    @ValueSource(strings = {" ", "1 ", " 1", "1 2"})
    @ParameterizedTest
    void validateContainSpaceTest(String input) {
        assertThatThrownBy(() ->
                validateContainSpace(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자가 아니거나 Integer보다 큰 수에 대한 예외 처리")
    @ValueSource(strings = {"a", "!", "1a", "2147483648"})
    @ParameterizedTest
    void validateIntegerTest(String input) {
        assertThatThrownBy(() ->
                validateInteger(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자가 1~31 범위를 벗어나는 것에 대한 예외 처리")
    @ValueSource(ints = {0, 32})
    @ParameterizedTest
    void validateIntegerInTest(Integer input) {
        assertThatThrownBy(() ->
                validateIntegerIn(input, 1, 31))
                .isInstanceOf(IllegalArgumentException.class);

    }
}