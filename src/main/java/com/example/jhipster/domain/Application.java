package com.example.jhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Application.
 */
@Entity
@Table(name = "application")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "job_id")
    private String job_id;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private LocalTime date;

    @Column(name = "referral_id")
    private Integer referral_id;

    @JsonIgnoreProperties(value = { "application", "user" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "application")
    private Referral referral;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Application id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob_id() {
        return this.job_id;
    }

    public Application job_id(String job_id) {
        this.setJob_id(job_id);
        return this;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getStatus() {
        return this.status;
    }

    public Application status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getDate() {
        return this.date;
    }

    public Application date(LocalTime date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }

    public Integer getReferral_id() {
        return this.referral_id;
    }

    public Application referral_id(Integer referral_id) {
        this.setReferral_id(referral_id);
        return this;
    }

    public void setReferral_id(Integer referral_id) {
        this.referral_id = referral_id;
    }

    public Referral getReferral() {
        return this.referral;
    }

    public void setReferral(Referral referral) {
        if (this.referral != null) {
            this.referral.setApplication(null);
        }
        if (referral != null) {
            referral.setApplication(this);
        }
        this.referral = referral;
    }

    public Application referral(Referral referral) {
        this.setReferral(referral);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Application)) {
            return false;
        }
        return getId() != null && getId().equals(((Application) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Application{" +
            "id=" + getId() +
            ", job_id='" + getJob_id() + "'" +
            ", status='" + getStatus() + "'" +
            ", date='" + getDate() + "'" +
            ", referral_id=" + getReferral_id() +
            "}";
    }
}
