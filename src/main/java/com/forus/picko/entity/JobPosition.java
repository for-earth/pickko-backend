package com.forus.picko.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "JOB_POSITION")
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
}
