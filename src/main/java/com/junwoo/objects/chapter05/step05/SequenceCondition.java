package com.junwoo.objects.chapter05.step05;

import com.junwoo.objects.chapter05.Screening;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfied(Screening screening) {
        return sequence == screening.getSequence();
    }
}
