package com.junwoo.objects.chapter05.step03;

import com.junwoo.objects.chapter05.Screening;

public interface DiscountCondition {
    boolean isSatisfied(Screening screening);
}
