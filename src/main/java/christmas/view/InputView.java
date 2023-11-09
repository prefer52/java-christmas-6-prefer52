package christmas.view;

import camp.nextstep.edu.missionutils.Console;
public class InputView {
    // 날짜 입력(날짜를 enum으로 선언하여 int가 아닌 enum으로 반환?)
    public int readInteger() {
        String date = Console.readLine();
        // 검증
        return Integer.parseInt(date);
    }
}
