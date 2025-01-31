package com.teonasnetto.desafio.domain.model;

import com.teonasnetto.desafio.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private LocalDate date;
    private Double value;
    private String cpf;
    private String card;
    private LocalTime time;
    private String storeOwner;
    private String storeName;
}