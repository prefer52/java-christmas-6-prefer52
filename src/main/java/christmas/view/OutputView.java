package christmas.view;

import java.util.List;

public class OutputView {
    // 한 문장의 메시지 출력
    public static void printMessage(String message) {
        System.out.println(message);
    }

    // 여러 문장의 메시지 출력
    public static void printMessages(List<String> messages) {
        System.out.println(String.join("\n", messages));
    }
}
