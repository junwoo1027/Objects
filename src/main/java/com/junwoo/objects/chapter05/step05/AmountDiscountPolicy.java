package com.junwoo.objects.chapter05.step05;

import com.junwoo.objects.chapter05.Money;
import com.junwoo.objects.chapter05.Screening;
import com.junwoo.objects.chapter05.step01.DiscountCondition;

public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
