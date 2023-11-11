package christmas.validate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
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

    @DisplayName(",,가 포함되어 있거나 ,로 시작하거나 끝나는 것에 대한 예외 처리")
    @ValueSource(strings = {"menu,,", ",menu", "menu,"})
    @ParameterizedTest
    void validateContainValidCommaTest(String input) {
        assertThatThrownBy(() ->
                validateContainValidComma(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("--가 포함되어 있거나, -로 시작하거나 끝나는 것에 대한 예외 처리")
    @Test
    void validateContainValidDashTest() {
        List<String> inputs = List.of(new String[]{"--menu-1", "-menu-1", "menu-1-"});
        assertThatThrownBy(() ->
                validateContainValidDash(inputs))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("-를 기준으로 2개로 분리되지 않는 것에 대한 예외 처리")
    @Test
    void validateSplitSizeIsTwoTest() {
        List<String> inputs = List.of(new String[]{"menu-1-2", "menu-2-good-3"});
        assertThatThrownBy(() ->
                validateSplitSizeIsTwo(inputs))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("문자열이 메뉴에 존재하는 않는 것에 대한 예외 처리")
    @ValueSource(strings = {"김치찌개", "된장찌개", "스테이크"})
    @ParameterizedTest
    void validateExistMenuTest(String input) {
        assertThatThrownBy(() ->
                validateExistMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 중 중복되는 메뉴가 존재하는 것에 대한 예외 처리")
    @ValueSource(strings = {"초코케이크", "아이스크림"})
    @ParameterizedTest
    void validateDuplicatedMenuTest(String input) {
        Map<String, Integer> orders = Map.of("초코케이크", 1, "아이스크림", 2);
        assertThatThrownBy(() ->
                validateDuplicatedMenu(orders, input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴가 단 하나일 시 그게 음료인것에 대한 예외 처리")
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    @ParameterizedTest
    void validateSingleMenuIsBeverageTest(String input) {
        assertThatThrownBy(() ->
                validateSingleMenuIsBeverage(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("전체 주문 메뉴 수가 20개를 초과하는 것에 대한 예외 처리")
    @Test
    void validateSumOverTest() {
        List<Integer> inputs = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() ->
                validateSumOver(20, inputs))
                .isInstanceOf(IllegalArgumentException.class);
    }

}