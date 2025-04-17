package com.example.jhipster.service;

import com.example.jhipster.domain.Job;
import com.example.jhipster.repository.JobRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.example.jhipster.domain.Job}.
 */
@Service
@Transactional
public class JobService {

    private static final Logger LOG = LoggerFactory.getLogger(JobService.class);

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Save a job.
     *
     * @param job the entity to save.
     * @return the persisted entity.
     */
    public Job save(Job job) {
        LOG.debug("Request to save Job : {}", job);
        return jobRepository.save(job);
    }

    /**
     * Update a job.
     *
     * @param job the entity to save.
     * @return the persisted entity.
     */
    public Job update(Job job) {
        LOG.debug("Request to update Job : {}", job);
        return jobRepository.save(job);
    }

    /**
     * Partially update a job.
     *
     * @param job the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Job> partialUpdate(Job job) {
        LOG.debug("Request to partially update Job : {}", job);

        return jobRepository
            .findById(job.getId())
            .map(existingJob -> {
                if (job.getCompany_id() != null) {
                    existingJob.setCompany_id(job.getCompany_id());
                }
                if (job.getJob_id() != null) {
                    existingJob.setJob_id(job.getJob_id());
                }
                if (job.getStatus() != null) {
                    existingJob.setStatus(job.getStatus());
                }
                if (job.getLocation() != null) {
                    existingJob.setLocation(job.getLocation());
                }
                if (job.getUrl() != null) {
                    existingJob.setUrl(job.getUrl());
                }
                if (job.getRole_id() != null) {
                    existingJob.setRole_id(job.getRole_id());
                }
                if (job.getMin_experience() != null) {
                    existingJob.setMin_experience(job.getMin_experience());
                }
                if (job.getLanguages() != null) {
                    existingJob.setLanguages(job.getLanguages());
                }

                return existingJob;
            })
            .map(jobRepository::save);
    }

    /**
     * Get all the jobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Job> findAll(Pageable pageable) {
        LOG.debug("Request to get all Jobs");
        return jobRepository.findAll(pageable);
    }

    /**
     * Get one job by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Job> findOne(Long id) {
        LOG.debug("Request to get Job : {}", id);
        return jobRepository.findById(id);
    }

    /**
     * Delete the job by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Job : {}", id);
        jobRepository.deleteById(id);
    }
}
