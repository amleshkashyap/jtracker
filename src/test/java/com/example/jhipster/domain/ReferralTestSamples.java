package com.example.jhipster.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ReferralTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Referral getReferralSample1() {
        return new Referral().id(1L).job_id(1L).referrer_id(1L);
    }

    public static Referral getReferralSample2() {
        return new Referral().id(2L).job_id(2L).referrer_id(2L);
    }

    public static Referral getReferralRandomSampleGenerator() {
        return new Referral().id(longCount.incrementAndGet()).job_id(longCount.incrementAndGet()).referrer_id(longCount.incrementAndGet());
    }
}
