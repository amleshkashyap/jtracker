package com.example.jhipster.domain;

import static com.example.jhipster.domain.CompanyTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.jhipster.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CompanyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Company.class);
        Company company1 = getCompanySample1();
        Company company2 = new Company();
        assertThat(company1).isNotEqualTo(company2);

        company2.setId(company1.getId());
        assertThat(company1).isEqualTo(company2);

        company2 = getCompanySample2();
        assertThat(company1).isNotEqualTo(company2);
    }
}
