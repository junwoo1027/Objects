package com.junwoo.objects.chapter02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private final LocalDateTime given_월요일 = LocalDate.of(2022, Month.SEPTEMBER, 19).atStartOfDay();
    private final LocalDateTime given_화요일 = given_월요일.plusDays(1);
    private final LocalDateTime given_목요일 = given_월요일.plusDays(3);

    Movie given_아바타() {
        return new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(Money.wons(800),
                        new SequenceCondition(1),
                        new SequenceCondition(10),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11,59)),
                        new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10,0), LocalTime.of(20,59))));
    }

    Movie given_타이타닉() {
        return new Movie(
                "타이타닉",
                Duration.ofMinutes(100),
                Money.wons(11000),
                new PercentDiscountPolicy(0.1,
                        new PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(16,59)),
                        new SequenceCondition(2),
                        new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10,0), LocalTime.of(13,59))));
    }

    Movie given_스타워즈() {
        return new Movie(
                "스타워즈",
                Duration.ofMinutes(200),
                Money.wons(10000),
                new NonDiscountPolicy());
    }

    abstract class TestCalculateMovieFee {
        abstract Movie givenMovie();

        Money movieFee() {
            return givenMovie().getFee();
        }

        Money subject(Screening screening) {
            return givenMovie().calculateMovieFee(screening);
        }
    }

    @Nested
    @DisplayName("calculateMovieFee 메소드는")
    class calculateMovieFeeTest {
        @Nested
        @DisplayName("영화가 아바타일 때 (할인 조건: 상영 시작 시간, 할인 금액: 고정 금액")
        class withAvatar extends TestCalculateMovieFee {
            @Override
            Movie givenMovie() {
                return given_아바타();
            }

            @Nested
            @DisplayName("상영 시작 시간이 할인 조건에 맞으면")
            class withValidPeriod {
                final List<LocalDateTime> 할인_조건에_맞는_상영시간 = List.of(
                        given_월요일.withHour(10).withMinute(0),
                        given_월요일.withHour(11).withMinute(59),
                        given_월요일.withHour(10).withMinute(1),
                        given_월요일.withHour(11).withMinute(58),
                        given_목요일.withHour(10).withMinute(0),
                        given_목요일.withHour(20).withMinute(59),
                        given_목요일.withHour(10).withMinute(01),
                        given_목요일.withHour(20).withMinute(58)
                );

                List<Screening> givenScreening() {
                    return 할인_조건에_맞는_상영시간.stream()
                            .map((time) -> new Screening(givenMovie(), 0, time))
                            .collect(Collectors.toList());
                }

                @Test
                @DisplayName("고정할인 금액만큼 금액을 리턴한다")
                void itReturnsDiscountFee() {
                    for (Screening screening : givenScreening()) {
                        Money money = subject(screening);

                        assertEquals(movieFee().minus(Money.wons(800)).getAmount(), money.getAmount());
                    }
                }
            }

            @Nested
            @DisplayName("상영 시작 시간이 할인 조건에 맞지 않는다면")
            class withInvalidPeriod {
                final List<LocalDateTime> 할인_조건에_맞지않는_상영시간 = List.of(
                        given_월요일.withHour(9).withMinute(59),
                        given_월요일.withHour(12).withMinute(0),
                        given_목요일.withHour(9).withMinute(59),
                        given_목요일.withHour(21).withMinute(0),
                        given_화요일.withHour(10).withMinute(0),
                        given_화요일.withHour(11).withMinute(0)
                );

                List<Screening> givenScreening() {
                    return 할인_조건에_맞지않는_상영시간.stream()
                            .map((time) -> new Screening(givenMovie(), 0, time))
                            .collect(Collectors.toList());
                }

                @Test
                @DisplayName("할인되지 않은 금액을 리턴한다")
                void itReturnsNotDiscountFee() {
                    for (Screening screening : givenScreening()) {
                        Money money = subject(screening);

                        assertEquals(movieFee().getAmount(), money.getAmount());
                    }
                }
            }

            @Nested
            @DisplayName("상영 순번이 할인 조건에 맞는다면")
            class withValidSeq {
                List<Integer> screeningSeq = List.of(1, 10);

                List<Screening> givenScreening() {
                    return screeningSeq.stream()
                            .map(seq -> new Screening(given_아바타(), seq, given_화요일.withHour(10).withMinute(0)))
                            .collect(Collectors.toList());
                }

                @Test
                @DisplayName("고정할인 금액만큼 금액을 리턴한다")
                void itReturnsDiscountFee() {
                    for (Screening screening : givenScreening()) {
                        Money money = subject(screening);

                        assertEquals(movieFee().minus(Money.wons(800)).getAmount(), money.getAmount());
                    }
                }
            }

            @Nested
            @DisplayName("상영 순번이 할인 조건에 맞지 않는다면")
            class withInvalidSeq {
                List<Integer> screeningSeq = List.of(2, 9);

                List<Screening> givenScreening() {
                    return screeningSeq.stream()
                            .map(seq -> new Screening(given_아바타(), seq, given_화요일.withHour(10).withMinute(0)))
                            .collect(Collectors.toList());
                }

                @Test
                @DisplayName("할인되지 않은 금액을 리턴한다")
                void itReturnsNotDiscountFee() {
                    for (Screening screening : givenScreening()) {
                        Money money = subject(screening);

                        assertEquals(movieFee().getAmount(), money.getAmount());
                    }

                }
            }
        }

        @Nested
        @DisplayName("영화가 타이타닉일 때 (할인 조건: 상영 시작 시간, 상영 순번, 할인 금액: 퍼센트")
        class withTitanic extends TestCalculateMovieFee {
            @Override
            Movie givenMovie() {
                return given_타이타닉();
            }

            @Nested
            @DisplayName("상영 시작 시간이 할인 조건에 맞으면")
            class withValidPeriod {
                final List<LocalDateTime> 할인_조건에_맞는_상영시간 = List.of(
                        given_화요일.withHour(14).withMinute(0),
                        given_화요일.withHour(16).withMinute(59)
                );

                List<Screening> givenScreening() {
                    return 할인_조건에_맞는_상영시간.stream()
                            .map((time) -> new Screening(givenMovie(), 0, time))
                            .collect(Collectors.toList());
                }

                @Test
                @DisplayName("퍼센트만큼 할인 금액을 리턴한다")
                void itReturnsDiscountFee() {
                    for (Screening screening : givenScreening()) {
                        Money money = subject(screening);

                        assertEquals(movieFee().times(1 - 0.1).getAmount(), money.getAmount());
                    }
                }
            }

            @Nested
            @DisplayName("상영 시작 시간이 할인 조건에 맞지 않는다면")
            class withInvalidPeriod {
                final List<LocalDateTime> 할인_조건에_맞지않는_상영시간 = List.of(
                        given_월요일.withHour(9).withMinute(59),
                        given_월요일.withHour(12).withMinute(0)
                );

                List<Screening> givenScreening() {
                    return 할인_조건에_맞지않는_상영시간.stream()
                            .map((time) -> new Screening(givenMovie(), 0, time))
                            .collect(Collectors.toList());
                }

                @Test
                @DisplayName("할인되지 않은 금액을 리턴한다")
                void itReturnsDiscountFee() {
                    for (Screening screening : givenScreening()) {
                        Money money = subject(screening);

                        assertEquals(movieFee().getAmount(), money.getAmount());
                    }
                }
            }
        }

        @Nested
        @DisplayName("영화가 스타워즈 일 때 (할인없음")
        class withStarWars extends TestCalculateMovieFee {

            @Override
            Movie givenMovie() {
                return given_스타워즈();
            }

            @Test
            @DisplayName("할인되지 않은 금액을 리턴한다.")
            void itReturnsNotDiscountFee() {
                Screening screening = new Screening(givenMovie(), 0, given_월요일.withHour(10).withMinute(0));
                Money money = subject(screening);

                assertEquals(movieFee().getAmount(), money.getAmount());
            }
        }
    }

    @Nested
    @DisplayName("changeDiscountPolicy 메소드는")
    class changeDiscountPolicyTest {

        @Nested
        @DisplayName("영화가 스타워즈일 때")
        class withStarWars extends TestCalculateMovieFee {

            @Override
            Movie givenMovie() {
                return given_스타워즈();
            }

            @Nested
            @DisplayName("새로운 퍼센트 할인 정책이 주어지면")
            class withNewPercentDiscountPolicy {
                DiscountPolicy discountPolicy = new PercentDiscountPolicy(0.1, new SequenceCondition(1));

                @Test
                @DisplayName("주어진 할인 정책으로 교체한다")
                void itChangeDiscountPolicyTest() {
                    final Movie starWars = givenMovie();
                    final Money BasicFee = starWars.getFee();
                    starWars.changeDiscountPolicy(discountPolicy);

                    Screening screening = new Screening(starWars, 1, given_월요일.withHour(10).withMinute(0));
                    Money money = starWars.calculateMovieFee(screening);

                    assertEquals(BasicFee.times(1 - 0.1).getAmount(), money.getAmount());
                }
            }
        }
    }
}