package com.forus.picko.repository;

import com.forus.picko.entity.Company;
import com.forus.picko.entity.Question;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaQuestionRepository implements QuestionRepository {

    private final EntityManager em;

    public JpaQuestionRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Question> findAll() {
        return em.createQuery("select q from Question q", Question.class)
                .getResultList();
    }

    @Override
    public Optional<Question> findById(Long id) {
        Question question = em.find(Question.class, id);
        return Optional.ofNullable(question);
    }
}
