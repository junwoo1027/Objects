package com.junwoo.objects.chapter06.step01;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public void setTicket(Audience audience) {
        ticketOffice.plusAmount(audience.setTicket(ticketOffice.getTicket()));
    }
}
