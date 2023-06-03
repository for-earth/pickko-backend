package com.forus.picko.repository;

import com.forus.picko.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    List<Company> findAll();
    Optional<Company> findById(Long id);
}
