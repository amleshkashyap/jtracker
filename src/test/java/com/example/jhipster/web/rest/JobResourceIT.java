package com.example.jhipster.web.rest;

import static com.example.jhipster.domain.JobAsserts.*;
import static com.example.jhipster.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.jhipster.IntegrationTest;
import com.example.jhipster.domain.Job;
import com.example.jhipster.repository.JobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link JobResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JobResourceIT {

    private static final Integer DEFAULT_COMPANY_ID = 1;
    private static final Integer UPDATED_COMPANY_ID = 2;

    private static final String DEFAULT_JOB_ID = "AAAAAAAAAA";
    private static final String UPDATED_JOB_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ROLE_ID = 1;
    private static final Integer UPDATED_ROLE_ID = 2;

    private static final Integer DEFAULT_MIN_EXPERIENCE = 1;
    private static final Integer UPDATED_MIN_EXPERIENCE = 2;

    private static final String DEFAULT_LANGUAGES = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/jobs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJobMockMvc;

    private Job job;

    private Job insertedJob;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Job createEntity() {
        return new Job()
            .company_id(DEFAULT_COMPANY_ID)
            .job_id(DEFAULT_JOB_ID)
            .status(DEFAULT_STATUS)
            .location(DEFAULT_LOCATION)
            .url(DEFAULT_URL)
            .role_id(DEFAULT_ROLE_ID)
            .min_experience(DEFAULT_MIN_EXPERIENCE)
            .languages(DEFAULT_LANGUAGES);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Job createUpdatedEntity() {
        return new Job()
            .company_id(UPDATED_COMPANY_ID)
            .job_id(UPDATED_JOB_ID)
            .status(UPDATED_STATUS)
            .location(UPDATED_LOCATION)
            .url(UPDATED_URL)
            .role_id(UPDATED_ROLE_ID)
            .min_experience(UPDATED_MIN_EXPERIENCE)
            .languages(UPDATED_LANGUAGES);
    }

    @BeforeEach
    void initTest() {
        job = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedJob != null) {
            jobRepository.delete(insertedJob);
            insertedJob = null;
        }
    }

    @Test
    @Transactional
    void createJob() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Job
        var returnedJob = om.readValue(
            restJobMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(job)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Job.class
        );

        // Validate the Job in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertJobUpdatableFieldsEquals(returnedJob, getPersistedJob(returnedJob));

        insertedJob = returnedJob;
    }

    @Test
    @Transactional
    void createJobWithExistingId() throws Exception {
        // Create the Job with an existing ID
        job.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(job)))
            .andExpect(status().isBadRequest());

        // Validate the Job in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJobs() throws Exception {
        // Initialize the database
        insertedJob = jobRepository.saveAndFlush(job);

        // Get all the jobList
        restJobMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(job.getId().intValue())))
            .andExpect(jsonPath("$.[*].company_id").value(hasItem(DEFAULT_COMPANY_ID)))
            .andExpect(jsonPath("$.[*].job_id").value(hasItem(DEFAULT_JOB_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].role_id").value(hasItem(DEFAULT_ROLE_ID)))
            .andExpect(jsonPath("$.[*].min_experience").value(hasItem(DEFAULT_MIN_EXPERIENCE)))
            .andExpect(jsonPath("$.[*].languages").value(hasItem(DEFAULT_LANGUAGES)));
    }

    @Test
    @Transactional
    void getJob() throws Exception {
        // Initialize the database
        insertedJob = jobRepository.saveAndFlush(job);

        // Get the job
        restJobMockMvc
            .perform(get(ENTITY_API_URL_ID, job.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(job.getId().intValue()))
            .andExpect(jsonPath("$.company_id").value(DEFAULT_COMPANY_ID))
            .andExpect(jsonPath("$.job_id").value(DEFAULT_JOB_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.role_id").value(DEFAULT_ROLE_ID))
            .andExpect(jsonPath("$.min_experience").value(DEFAULT_MIN_EXPERIENCE))
            .andExpect(jsonPath("$.languages").value(DEFAULT_LANGUAGES));
    }

    @Test
    @Transactional
    void getNonExistingJob() throws Exception {
        // Get the job
        restJobMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingJob() throws Exception {
        // Initialize the database
        insertedJob = jobRepository.saveAndFlush(job);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the job
        Job updatedJob = jobRepository.findById(job.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedJob are not directly saved in db
        em.detach(updatedJob);
        updatedJob
            .company_id(UPDATED_COMPANY_ID)
            .job_id(UPDATED_JOB_ID)
            .status(UPDATED_STATUS)
            .location(UPDATED_LOCATION)
            .url(UPDATED_URL)
            .role_id(UPDATED_ROLE_ID)
            .min_experience(UPDATED_MIN_EXPERIENCE)
            .languages(UPDATED_LANGUAGES);

        restJobMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJob.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(updatedJob))
            )
            .andExpect(status().isOk());

        // Validate the Job in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedJobToMatchAllProperties(updatedJob);
    }

    @Test
    @Transactional
    void putNonExistingJob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        job.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobMockMvc
            .perform(put(ENTITY_API_URL_ID, job.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(job)))
            .andExpect(status().isBadRequest());

        // Validate the Job in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        job.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJobMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(job))
            )
            .andExpect(status().isBadRequest());

        // Validate the Job in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        job.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJobMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(job)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Job in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJobWithPatch() throws Exception {
        // Initialize the database
        insertedJob = jobRepository.saveAndFlush(job);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the job using partial update
        Job partialUpdatedJob = new Job();
        partialUpdatedJob.setId(job.getId());

        partialUpdatedJob
            .company_id(UPDATED_COMPANY_ID)
            .status(UPDATED_STATUS)
            .location(UPDATED_LOCATION)
            .url(UPDATED_URL)
            .role_id(UPDATED_ROLE_ID);

        restJobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJob.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedJob))
            )
            .andExpect(status().isOk());

        // Validate the Job in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertJobUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedJob, job), getPersistedJob(job));
    }

    @Test
    @Transactional
    void fullUpdateJobWithPatch() throws Exception {
        // Initialize the database
        insertedJob = jobRepository.saveAndFlush(job);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the job using partial update
        Job partialUpdatedJob = new Job();
        partialUpdatedJob.setId(job.getId());

        partialUpdatedJob
            .company_id(UPDATED_COMPANY_ID)
            .job_id(UPDATED_JOB_ID)
            .status(UPDATED_STATUS)
            .location(UPDATED_LOCATION)
            .url(UPDATED_URL)
            .role_id(UPDATED_ROLE_ID)
            .min_experience(UPDATED_MIN_EXPERIENCE)
            .languages(UPDATED_LANGUAGES);

        restJobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJob.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedJob))
            )
            .andExpect(status().isOk());

        // Validate the Job in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertJobUpdatableFieldsEquals(partialUpdatedJob, getPersistedJob(partialUpdatedJob));
    }

    @Test
    @Transactional
    void patchNonExistingJob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        job.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobMockMvc
            .perform(patch(ENTITY_API_URL_ID, job.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(job)))
            .andExpect(status().isBadRequest());

        // Validate the Job in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        job.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(job))
            )
            .andExpect(status().isBadRequest());

        // Validate the Job in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        job.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJobMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(job)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Job in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJob() throws Exception {
        // Initialize the database
        insertedJob = jobRepository.saveAndFlush(job);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the job
        restJobMockMvc.perform(delete(ENTITY_API_URL_ID, job.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return jobRepository.count();
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

    protected Job getPersistedJob(Job job) {
        return jobRepository.findById(job.getId()).orElseThrow();
    }

    protected void assertPersistedJobToMatchAllProperties(Job expectedJob) {
        assertJobAllPropertiesEquals(expectedJob, getPersistedJob(expectedJob));
    }

    protected void assertPersistedJobToMatchUpdatableProperties(Job expectedJob) {
        assertJobAllUpdatablePropertiesEquals(expectedJob, getPersistedJob(expectedJob));
    }
}
