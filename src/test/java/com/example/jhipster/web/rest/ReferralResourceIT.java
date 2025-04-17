package com.example.jhipster.web.rest;

import static com.example.jhipster.domain.ReferralAsserts.*;
import static com.example.jhipster.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.jhipster.IntegrationTest;
import com.example.jhipster.domain.Referral;
import com.example.jhipster.repository.ReferralRepository;
import com.example.jhipster.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ReferralResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReferralResourceIT {

    private static final Long DEFAULT_JOB_ID = 1L;
    private static final Long UPDATED_JOB_ID = 2L;

    private static final Long DEFAULT_REFERRER_ID = 1L;
    private static final Long UPDATED_REFERRER_ID = 2L;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/referrals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ReferralRepository referralRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReferralMockMvc;

    private Referral referral;

    private Referral insertedReferral;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Referral createEntity() {
        return new Referral().job_id(DEFAULT_JOB_ID).referrer_id(DEFAULT_REFERRER_ID).date(DEFAULT_DATE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Referral createUpdatedEntity() {
        return new Referral().job_id(UPDATED_JOB_ID).referrer_id(UPDATED_REFERRER_ID).date(UPDATED_DATE);
    }

    @BeforeEach
    void initTest() {
        referral = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedReferral != null) {
            referralRepository.delete(insertedReferral);
            insertedReferral = null;
        }
    }

    @Test
    @Transactional
    void createReferral() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Referral
        var returnedReferral = om.readValue(
            restReferralMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(referral)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Referral.class
        );

        // Validate the Referral in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertReferralUpdatableFieldsEquals(returnedReferral, getPersistedReferral(returnedReferral));

        insertedReferral = returnedReferral;
    }

    @Test
    @Transactional
    void createReferralWithExistingId() throws Exception {
        // Create the Referral with an existing ID
        referral.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReferralMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(referral)))
            .andExpect(status().isBadRequest());

        // Validate the Referral in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReferrals() throws Exception {
        // Initialize the database
        insertedReferral = referralRepository.saveAndFlush(referral);

        // Get all the referralList
        restReferralMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(referral.getId().intValue())))
            .andExpect(jsonPath("$.[*].job_id").value(hasItem(DEFAULT_JOB_ID.intValue())))
            .andExpect(jsonPath("$.[*].referrer_id").value(hasItem(DEFAULT_REFERRER_ID.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    void getReferral() throws Exception {
        // Initialize the database
        insertedReferral = referralRepository.saveAndFlush(referral);

        // Get the referral
        restReferralMockMvc
            .perform(get(ENTITY_API_URL_ID, referral.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(referral.getId().intValue()))
            .andExpect(jsonPath("$.job_id").value(DEFAULT_JOB_ID.intValue()))
            .andExpect(jsonPath("$.referrer_id").value(DEFAULT_REFERRER_ID.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingReferral() throws Exception {
        // Get the referral
        restReferralMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReferral() throws Exception {
        // Initialize the database
        insertedReferral = referralRepository.saveAndFlush(referral);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the referral
        Referral updatedReferral = referralRepository.findById(referral.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedReferral are not directly saved in db
        em.detach(updatedReferral);
        updatedReferral.job_id(UPDATED_JOB_ID).referrer_id(UPDATED_REFERRER_ID).date(UPDATED_DATE);

        restReferralMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReferral.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedReferral))
            )
            .andExpect(status().isOk());

        // Validate the Referral in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedReferralToMatchAllProperties(updatedReferral);
    }

    @Test
    @Transactional
    void putNonExistingReferral() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        referral.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReferralMockMvc
            .perform(
                put(ENTITY_API_URL_ID, referral.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(referral))
            )
            .andExpect(status().isBadRequest());

        // Validate the Referral in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReferral() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        referral.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReferralMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(referral))
            )
            .andExpect(status().isBadRequest());

        // Validate the Referral in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReferral() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        referral.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReferralMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(referral)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Referral in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReferralWithPatch() throws Exception {
        // Initialize the database
        insertedReferral = referralRepository.saveAndFlush(referral);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the referral using partial update
        Referral partialUpdatedReferral = new Referral();
        partialUpdatedReferral.setId(referral.getId());

        partialUpdatedReferral.job_id(UPDATED_JOB_ID).referrer_id(UPDATED_REFERRER_ID);

        restReferralMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReferral.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReferral))
            )
            .andExpect(status().isOk());

        // Validate the Referral in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReferralUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedReferral, referral), getPersistedReferral(referral));
    }

    @Test
    @Transactional
    void fullUpdateReferralWithPatch() throws Exception {
        // Initialize the database
        insertedReferral = referralRepository.saveAndFlush(referral);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the referral using partial update
        Referral partialUpdatedReferral = new Referral();
        partialUpdatedReferral.setId(referral.getId());

        partialUpdatedReferral.job_id(UPDATED_JOB_ID).referrer_id(UPDATED_REFERRER_ID).date(UPDATED_DATE);

        restReferralMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReferral.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReferral))
            )
            .andExpect(status().isOk());

        // Validate the Referral in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReferralUpdatableFieldsEquals(partialUpdatedReferral, getPersistedReferral(partialUpdatedReferral));
    }

    @Test
    @Transactional
    void patchNonExistingReferral() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        referral.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReferralMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, referral.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(referral))
            )
            .andExpect(status().isBadRequest());

        // Validate the Referral in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReferral() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        referral.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReferralMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(referral))
            )
            .andExpect(status().isBadRequest());

        // Validate the Referral in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReferral() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        referral.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReferralMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(referral)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Referral in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReferral() throws Exception {
        // Initialize the database
        insertedReferral = referralRepository.saveAndFlush(referral);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the referral
        restReferralMockMvc
            .perform(delete(ENTITY_API_URL_ID, referral.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return referralRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Referral getPersistedReferral(Referral referral) {
        return referralRepository.findById(referral.getId()).orElseThrow();
    }

    protected void assertPersistedReferralToMatchAllProperties(Referral expectedReferral) {
        assertReferralAllPropertiesEquals(expectedReferral, getPersistedReferral(expectedReferral));
    }

    protected void assertPersistedReferralToMatchUpdatableProperties(Referral expectedReferral) {
        assertReferralAllUpdatablePropertiesEquals(expectedReferral, getPersistedReferral(expectedReferral));
    }
}
