package com.junwoo.objects.chapter06.step01;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public void sellTo(Audience audience) {
        ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));
    }
}
