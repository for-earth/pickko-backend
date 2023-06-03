package com.forus.picko.repository;

import com.forus.picko.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    List<Question> findAll();
    Optional<Question> findById(Long id);
}
