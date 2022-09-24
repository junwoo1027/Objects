package com.junwoo.objects.chapter06.step01;

public class Audience {
    private Bag bag;

    public Long buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}
