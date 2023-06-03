package com.forus.picko.service;

import com.forus.picko.entity.Question;
import com.forus.picko.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> findOne(Long questionId) {
        return questionRepository.findById(questionId);
    }
}
