package com.example.jhipster.service;

import com.example.jhipster.domain.Referral;
import com.example.jhipster.repository.ReferralRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.example.jhipster.domain.Referral}.
 */
@Service
@Transactional
public class ReferralService {

    private static final Logger LOG = LoggerFactory.getLogger(ReferralService.class);

    private final ReferralRepository referralRepository;

    public ReferralService(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;
    }

    /**
     * Save a referral.
     *
     * @param referral the entity to save.
     * @return the persisted entity.
     */
    public Referral save(Referral referral) {
        LOG.debug("Request to save Referral : {}", referral);
        return referralRepository.save(referral);
    }

    /**
     * Update a referral.
     *
     * @param referral the entity to save.
     * @return the persisted entity.
     */
    public Referral update(Referral referral) {
        LOG.debug("Request to update Referral : {}", referral);
        return referralRepository.save(referral);
    }

    /**
     * Partially update a referral.
     *
     * @param referral the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Referral> partialUpdate(Referral referral) {
        LOG.debug("Request to partially update Referral : {}", referral);

        return referralRepository
            .findById(referral.getId())
            .map(existingReferral -> {
                if (referral.getJob_id() != null) {
                    existingReferral.setJob_id(referral.getJob_id());
                }
                if (referral.getReferrer_id() != null) {
                    existingReferral.setReferrer_id(referral.getReferrer_id());
                }
                if (referral.getDate() != null) {
                    existingReferral.setDate(referral.getDate());
                }

                return existingReferral;
            })
            .map(referralRepository::save);
    }

    /**
     * Get all the referrals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Referral> findAll(Pageable pageable) {
        LOG.debug("Request to get all Referrals");
        return referralRepository.findAll(pageable);
    }

    /**
     * Get one referral by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Referral> findOne(Long id) {
        LOG.debug("Request to get Referral : {}", id);
        return referralRepository.findById(id);
    }

    /**
     * Delete the referral by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Referral : {}", id);
        referralRepository.deleteById(id);
    }
}
