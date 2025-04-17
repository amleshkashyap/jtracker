package com.example.jhipster.domain;

import static com.example.jhipster.domain.ApplicationTestSamples.*;
import static com.example.jhipster.domain.ReferralTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.jhipster.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ApplicationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Application.class);
        Application application1 = getApplicationSample1();
        Application application2 = new Application();
        assertThat(application1).isNotEqualTo(application2);

        application2.setId(application1.getId());
        assertThat(application1).isEqualTo(application2);

        application2 = getApplicationSample2();
        assertThat(application1).isNotEqualTo(application2);
    }

    @Test
    void referralTest() {
        Application application = getApplicationRandomSampleGenerator();
        Referral referralBack = getReferralRandomSampleGenerator();

        application.setReferral(referralBack);
        assertThat(application.getReferral()).isEqualTo(referralBack);
        assertThat(referralBack.getApplication()).isEqualTo(application);

        application.referral(null);
        assertThat(application.getReferral()).isNull();
        assertThat(referralBack.getApplication()).isNull();
    }
}
