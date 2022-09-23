package com.junwoo.objects.chapter05.step05;

import com.junwoo.objects.chapter05.Money;
import com.junwoo.objects.chapter05.Screening;
import com.junwoo.objects.chapter05.step01.DiscountCondition;
import com.junwoo.objects.chapter05.step05.DiscountPolicy;

public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
