package com.forus.picko.repository;

import com.forus.picko.entity.JobPosition;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaJobPositionRepository implements JobPositionRepository {

    private final EntityManager em;

    public JpaJobPositionRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<JobPosition> findAll() {
        return em.createQuery("select m from JobPosition m", JobPosition.class)
                .getResultList();
    }

    @Override
    public Optional<JobPosition> findById(Long id) {
        JobPosition jobPosition = em.find(JobPosition.class, id);
        return Optional.ofNullable(jobPosition);
    }
}
