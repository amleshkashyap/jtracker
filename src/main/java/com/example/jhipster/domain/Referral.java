package com.example.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Referral.
 */
@Entity
@Table(name = "referral")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Referral implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "job_id")
    private Long job_id;

    @Column(name = "referrer_id")
    private Long referrer_id;

    @Column(name = "date")
    private LocalDate date;

    @JsonIgnoreProperties(value = { "referral" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Application application;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Referral id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJob_id() {
        return this.job_id;
    }

    public Referral job_id(Long job_id) {
        this.setJob_id(job_id);
        return this;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public Long getReferrer_id() {
        return this.referrer_id;
    }

    public Referral referrer_id(Long referrer_id) {
        this.setReferrer_id(referrer_id);
        return this;
    }

    public void setReferrer_id(Long referrer_id) {
        this.referrer_id = referrer_id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Referral date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Referral application(Application application) {
        this.setApplication(application);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Referral user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Referral)) {
            return false;
        }
        return getId() != null && getId().equals(((Referral) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Referral{" +
            "id=" + getId() +
            ", job_id=" + getJob_id() +
            ", referrer_id=" + getReferrer_id() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
