package com.junwoo.objects.chapter05.step05;

import com.junwoo.objects.chapter05.Money;
import com.junwoo.objects.chapter05.Screening;
import com.junwoo.objects.chapter05.step05.DiscountPolicy;

public class NonDiscountPolicy extends DiscountPolicy {
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
