package com.example.jhipster.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Job.
 */
@Entity
@Table(name = "job")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_id")
    private Integer company_id;

    @Column(name = "job_id")
    private String job_id;

    @Column(name = "status")
    private String status;

    @Column(name = "location")
    private String location;

    @Column(name = "url")
    private String url;

    @Column(name = "role_id")
    private Integer role_id;

    @Column(name = "min_experience")
    private Integer min_experience;

    @Column(name = "languages")
    private String languages;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Job id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCompany_id() {
        return this.company_id;
    }

    public Job company_id(Integer company_id) {
        this.setCompany_id(company_id);
        return this;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getJob_id() {
        return this.job_id;
    }

    public Job job_id(String job_id) {
        this.setJob_id(job_id);
        return this;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getStatus() {
        return this.status;
    }

    public Job status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return this.location;
    }

    public Job location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return this.url;
    }

    public Job url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRole_id() {
        return this.role_id;
    }

    public Job role_id(Integer role_id) {
        this.setRole_id(role_id);
        return this;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getMin_experience() {
        return this.min_experience;
    }

    public Job min_experience(Integer min_experience) {
        this.setMin_experience(min_experience);
        return this;
    }

    public void setMin_experience(Integer min_experience) {
        this.min_experience = min_experience;
    }

    public String getLanguages() {
        return this.languages;
    }

    public Job languages(String languages) {
        this.setLanguages(languages);
        return this;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        return getId() != null && getId().equals(((Job) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Job{" +
            "id=" + getId() +
            ", company_id=" + getCompany_id() +
            ", job_id='" + getJob_id() + "'" +
            ", status='" + getStatus() + "'" +
            ", location='" + getLocation() + "'" +
            ", url='" + getUrl() + "'" +
            ", role_id=" + getRole_id() +
            ", min_experience=" + getMin_experience() +
            ", languages='" + getLanguages() + "'" +
            "}";
    }
}
