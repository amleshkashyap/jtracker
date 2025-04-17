package com.example.jhipster.repository;

import com.example.jhipster.domain.Referral;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Referral entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {}
