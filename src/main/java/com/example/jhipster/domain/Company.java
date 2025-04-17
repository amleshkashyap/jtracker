package com.example.jhipster.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "employees")
    private Integer employees;

    @Column(name = "glassdoor")
    private Float glassdoor;

    @Column(name = "headquarter")
    private String headquarter;

    @Column(name = "domains")
    private String domains;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Company id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Company name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployees() {
        return this.employees;
    }

    public Company employees(Integer employees) {
        this.setEmployees(employees);
        return this;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public Float getGlassdoor() {
        return this.glassdoor;
    }

    public Company glassdoor(Float glassdoor) {
        this.setGlassdoor(glassdoor);
        return this;
    }

    public void setGlassdoor(Float glassdoor) {
        this.glassdoor = glassdoor;
    }

    public String getHeadquarter() {
        return this.headquarter;
    }

    public Company headquarter(String headquarter) {
        this.setHeadquarter(headquarter);
        return this;
    }

    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
    }

    public String getDomains() {
        return this.domains;
    }

    public Company domains(String domains) {
        this.setDomains(domains);
        return this;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return getId() != null && getId().equals(((Company) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", employees=" + getEmployees() +
            ", glassdoor=" + getGlassdoor() +
            ", headquarter='" + getHeadquarter() + "'" +
            ", domains='" + getDomains() + "'" +
            "}";
    }
}
