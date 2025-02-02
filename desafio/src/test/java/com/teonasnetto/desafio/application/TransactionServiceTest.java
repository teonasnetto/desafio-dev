package com.teonasnetto.desafio.application;

import com.teonasnetto.desafio.domain.model.Transaction;
import com.teonasnetto.desafio.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTransactionsReturnsListOfTransactions() {
        Transaction transaction = new Transaction();
        when(transactionRepository.findAll()).thenReturn(List.of(transaction));

        List<Transaction> transactions = transactionService.getAllTransactions();

        assertEquals(1, transactions.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void getAllTransactionsReturnsEmptyListWhenNoTransactions() {
        when(transactionRepository.findAll()).thenReturn(Collections.emptyList());

        List<Transaction> transactions = transactionService.getAllTransactions();

        assertEquals(0, transactions.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void saveTransactionSavesTransaction() {
        Transaction transaction = new Transaction();

        transactionService.saveTransaction(transaction);

        verify(transactionRepository, times(1)).save(transaction);
    }
}