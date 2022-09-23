package com.junwoo.objects.chapter05.step04;

import com.junwoo.objects.chapter05.Money;
import com.junwoo.objects.chapter05.step01.DiscountCondition;

import java.time.Duration;

public class NoneDiscountMovie extends Movie {
    public NoneDiscountMovie(String title, Duration runningTime, Money fee, DiscountCondition... discountConditions) {
        super(title, runningTime, fee, discountConditions);
    }

    @Override
    protected Money calculateDiscountAmount() {
        return Money.ZERO;
    }
}
