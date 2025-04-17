package com.example.jhipster.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CompanyTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Company getCompanySample1() {
        return new Company().id(1L).name("name1").employees(1).headquarter("headquarter1").domains("domains1");
    }

    public static Company getCompanySample2() {
        return new Company().id(2L).name("name2").employees(2).headquarter("headquarter2").domains("domains2");
    }

    public static Company getCompanyRandomSampleGenerator() {
        return new Company()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .employees(intCount.incrementAndGet())
            .headquarter(UUID.randomUUID().toString())
            .domains(UUID.randomUUID().toString());
    }
}
