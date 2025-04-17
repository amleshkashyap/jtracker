package com.example.jhipster.service.impl;

import com.example.jhipster.domain.Application;
import com.example.jhipster.repository.ApplicationRepository;
import com.example.jhipster.service.ApplicationService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.example.jhipster.domain.Application}.
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application save(Application application) {
        LOG.debug("Request to save Application : {}", application);
        return applicationRepository.save(application);
    }

    @Override
    public Application update(Application application) {
        LOG.debug("Request to update Application : {}", application);
        return applicationRepository.save(application);
    }

    @Override
    public Optional<Application> partialUpdate(Application application) {
        LOG.debug("Request to partially update Application : {}", application);

        return applicationRepository
            .findById(application.getId())
            .map(existingApplication -> {
                if (application.getJob_id() != null) {
                    existingApplication.setJob_id(application.getJob_id());
                }
                if (application.getStatus() != null) {
                    existingApplication.setStatus(application.getStatus());
                }
                if (application.getDate() != null) {
                    existingApplication.setDate(application.getDate());
                }
                if (application.getReferral_id() != null) {
                    existingApplication.setReferral_id(application.getReferral_id());
                }

                return existingApplication;
            })
            .map(applicationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Application> findAll(Pageable pageable) {
        LOG.debug("Request to get all Applications");
        return applicationRepository.findAll(pageable);
    }

    /**
     *  Get all the applications where Referral is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Application> findAllWhereReferralIsNull() {
        LOG.debug("Request to get all applications where Referral is null");
        return StreamSupport.stream(applicationRepository.findAll().spliterator(), false)
            .filter(application -> application.getReferral() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Application> findOne(Long id) {
        LOG.debug("Request to get Application : {}", id);
        return applicationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Application : {}", id);
        applicationRepository.deleteById(id);
    }
}
