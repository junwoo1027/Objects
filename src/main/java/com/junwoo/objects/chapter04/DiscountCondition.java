package com.junwoo.objects.chapter04;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    private DisCountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public DisCountConditionType getType() {
        return type;
    }

    public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
        if (type != DisCountConditionType.PERIOD) {
            throw new IllegalArgumentException();
        }

        return this.dayOfWeek.equals(dayOfWeek) &&
                this.startTime.compareTo(time) <= 0 &&
                this.endTime.compareTo(time) >= 0;
    }

    public boolean isDiscountable(int sequence) {
        if (type != DisCountConditionType.SEQUENCE) {
            throw new IllegalArgumentException();
        }

        return this.sequence == sequence;
    }
}
