package com.example.jhipster.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class JobTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Job getJobSample1() {
        return new Job()
            .id(1L)
            .company_id(1)
            .job_id("job_id1")
            .status("status1")
            .location("location1")
            .url("url1")
            .role_id(1)
            .min_experience(1)
            .languages("languages1");
    }

    public static Job getJobSample2() {
        return new Job()
            .id(2L)
            .company_id(2)
            .job_id("job_id2")
            .status("status2")
            .location("location2")
            .url("url2")
            .role_id(2)
            .min_experience(2)
            .languages("languages2");
    }

    public static Job getJobRandomSampleGenerator() {
        return new Job()
            .id(longCount.incrementAndGet())
            .company_id(intCount.incrementAndGet())
            .job_id(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString())
            .url(UUID.randomUUID().toString())
            .role_id(intCount.incrementAndGet())
            .min_experience(intCount.incrementAndGet())
            .languages(UUID.randomUUID().toString());
    }
}
