package com.fistkim.studyreviewapp.type;

public enum ReviewPeriod {
    FIRST(1), SECOND(4), THIRD(7), FOURTH(14), FIFTH(30);

    private final int days;

    ReviewPeriod(int days) {
        this.days = days;
    }

    public int getDays() {
        return this.days;
    }

    public ReviewPeriod next() {
        if (this == FIRST) return SECOND;
        if (this == SECOND) return THIRD;
        if (this == THIRD) return FOURTH;
        if (this == FOURTH) return FIFTH;
        if (this == FIFTH) return FIFTH;
        return FIRST;
    }
}
