package com.junwoo.objects.chapter01.step03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AudienceTest {
    final Long ticketFee = 100L;
    final Ticket ticket = new Ticket(ticketFee);

    @Nested
    @DisplayName("buy 메소드는")
    class Describe_buy_method {

        @Nested
        @DisplayName("초대장이 있는 경우")
        class withInvitation {
            final Invitation invitation =  new Invitation();
            final Bag bag = new Bag(invitation, 1000000L);

            Audience haveInvitation() {
                return new Audience(bag);
            }

            @Test
            @DisplayName("관람객의 가방에 티켓을 추가하고, 0을 리턴한다")
            void buyTicket() {
                final Audience audience = haveInvitation();
                final Long paid = audience.buy(ticket);

                assertTrue(bag.hasTicket());
                assertEquals(paid, Long.valueOf(0L));
            }
        }

        @Nested
        @DisplayName("초대장이 없는 경우")
        class noInvitation {
            final Bag bag() {
                return new Bag(100000L);
            }

            Audience notHaveInvitation(Bag bag) {
                return new Audience(bag);
            }

            @Test
            @DisplayName("관람객의 가방에 티켓을 추가하고, 지불한 티겟 값을 리턴한다")
            void buyTicket() {
                final Bag bag = bag();
                Long paid = notHaveInvitation(bag).buy(ticket);

                assertTrue(bag.hasTicket());
                assertEquals(paid, ticketFee);
            }
        }
    }
}