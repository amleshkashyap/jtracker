package com.example.jhipster.domain;

import static com.example.jhipster.domain.ApplicationTestSamples.*;
import static com.example.jhipster.domain.ReferralTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.jhipster.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReferralTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Referral.class);
        Referral referral1 = getReferralSample1();
        Referral referral2 = new Referral();
        assertThat(referral1).isNotEqualTo(referral2);

        referral2.setId(referral1.getId());
        assertThat(referral1).isEqualTo(referral2);

        referral2 = getReferralSample2();
        assertThat(referral1).isNotEqualTo(referral2);
    }

    @Test
    void applicationTest() {
        Referral referral = getReferralRandomSampleGenerator();
        Application applicationBack = getApplicationRandomSampleGenerator();

        referral.setApplication(applicationBack);
        assertThat(referral.getApplication()).isEqualTo(applicationBack);

        referral.application(null);
        assertThat(referral.getApplication()).isNull();
    }
}
