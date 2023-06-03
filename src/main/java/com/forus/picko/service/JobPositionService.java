package com.forus.picko.service;

import com.forus.picko.entity.JobPosition;
import com.forus.picko.repository.JobPositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobPositionService {
    private final JobPositionRepository jobPositionRepository;

    @Autowired
    public JobPositionService(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    public List<JobPosition> findPositions() {
        return jobPositionRepository.findAll();
    }

    public Optional<JobPosition> findOne(Long positionId) {
        return jobPositionRepository.findById(positionId);
    }
}
