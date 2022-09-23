package com.junwoo.objects.chapter05.step04;

import com.junwoo.objects.chapter05.Money;
import com.junwoo.objects.chapter05.step01.DiscountCondition;

import java.time.Duration;
import java.util.List;

public class AmountDiscountMovie extends Movie {
    private Money discountAmount;

    public AmountDiscountMovie(String title, Duration runningTime, Money fee, Money discountAmount, DiscountCondition... discountConditions) {
        super(title, runningTime, fee, discountConditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return discountAmount;
    }
}
