package com.junwoo.objects.chapter05.step05;

import com.junwoo.objects.chapter05.Screening;

public interface DiscountCondition {
    boolean isSatisfied(Screening screening);
}
