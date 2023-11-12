package christmas.type;

public enum GuideMessage {
    HELLO("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    CHOOSE_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    CHOOSE_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PREVIEW_BENEFIT("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUND("<할인 전 총주문 금액>"),
    COMPLIMENTRAY_ITEMS("<증정 메뉴>"),
    BENEFIT_DETAIL("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    EXPECTED_DISCOUNT_AMOUNT("<할인 후 예상 결제 금액>"),
    DECEMBER_EVENT_BADGE("<12월 이벤트 배지>");
    private String text;

    GuideMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
