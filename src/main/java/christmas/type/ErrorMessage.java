package christmas.type;

public enum ErrorMessage {
    EMPTY_STRING("[ERROR] 빈 문자열입니다. 다시 입력해 주세요."),
    CONTAIN_SPACE("[ERROR] 공백 문자를 포함하고 있습니다. 다시 입려해 주세요."),
    NOT_INTEGER_IN("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    CONTAIN_INVALID_COMMA("[ERROR] 콤마가 부적절하게 사용되었습니다. 다시 입력해 주세요."),
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    TOO_MANY_ORDER("[ERROR] 메뉴는 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
    private String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
