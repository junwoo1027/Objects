package com.junwoo.objects.chapter11.step01;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    // 일반 요금제에 세금 정책
    Phone taxableRegularPolicyPhone() {
        return new Phone(
                new TaxablePolicy(0.05,
                        new RegularPolicy(
                                Money.wons(10),
                                Duration.ofSeconds(10))));
    }

    Phone regularRateDiscountTaxablePhone() {
        return new Phone(
                new TaxablePolicy(0.05,
                        new RateDiscountablePolicy(Money.wons(1000),
                                new RegularPolicy(
                                        Money.wons(10),
                                        Duration.ofSeconds(10)))));
    }

    Phone nightRateDiscountTaxablePhone() {
        return new Phone(
                new TaxablePolicy(0.05,
                        new RateDiscountablePolicy(Money.wons(1000),
                                new NightlyDiscountPolicy(
                                        Money.wons(5),
                                        Money.wons(10),
                                        Duration.ofSeconds(10)))));
    }
}