package com.forus.picko.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "COMPANY")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
