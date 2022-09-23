package com.junwoo.objects.chapter05.step02;

import com.junwoo.objects.chapter05.Screening;

public class SequenceCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    private boolean isSatisfiedBySequence(Screening screening) {
        return sequence == screening.getSequence();
    }
}
