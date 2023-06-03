package com.forus.picko.service;

import com.forus.picko.entity.Company;
import com.forus.picko.entity.Member;
import com.forus.picko.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> findOne(Long companyId) {
        return companyRepository.findById(companyId);
    }
}
