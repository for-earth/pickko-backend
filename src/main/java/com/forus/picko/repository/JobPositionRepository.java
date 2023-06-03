package com.forus.picko.repository;

import com.forus.picko.entity.JobPosition;

import java.util.List;
import java.util.Optional;

public interface JobPositionRepository {
    List<JobPosition> findAll();
    Optional<JobPosition> findById(Long id);
}
