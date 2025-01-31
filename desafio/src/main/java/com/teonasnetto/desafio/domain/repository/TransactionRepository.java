package com.teonasnetto.desafio.domain.repository;

import com.teonasnetto.desafio.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}