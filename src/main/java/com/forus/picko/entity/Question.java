package com.forus.picko.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
}
