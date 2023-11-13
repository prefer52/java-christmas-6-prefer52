package christmas.type;

import java.util.List;

public enum DateCategory {
    CHRISTMAS_DAY(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
            13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25)),
    WEEK_DAY(List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18,
            19, 20, 21, 24, 25, 26, 27, 28, 31)),
    WEEKEND_DAY(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    SPECIAL_DAY(List.of(3, 10, 17, 24, 25, 31));
    private List<Integer> dates;

    DateCategory(List<Integer> dates) {
        this.dates = dates;
    }

    public List<Integer> getDates() {
        return dates;
    }
}
