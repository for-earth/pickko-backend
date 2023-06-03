package com.forus.picko.repository;

import com.forus.picko.entity.Company;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCompanyRepository implements CompanyRepository {

    private final EntityManager em;

    public JpaCompanyRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Company> findAll() {
        return em.createQuery("select m from Company m", Company.class)
                .getResultList();
    }

    @Override
    public Optional<Company> findById(Long id) {
        Company company = em.find(Company.class, id);
        return Optional.ofNullable(company);
    }
}
