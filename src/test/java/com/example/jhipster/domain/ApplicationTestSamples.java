package com.example.jhipster.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ApplicationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Application getApplicationSample1() {
        return new Application().id(1L).job_id("job_id1").status("status1").referral_id(1);
    }

    public static Application getApplicationSample2() {
        return new Application().id(2L).job_id("job_id2").status("status2").referral_id(2);
    }

    public static Application getApplicationRandomSampleGenerator() {
        return new Application()
            .id(longCount.incrementAndGet())
            .job_id(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .referral_id(intCount.incrementAndGet());
    }
}
