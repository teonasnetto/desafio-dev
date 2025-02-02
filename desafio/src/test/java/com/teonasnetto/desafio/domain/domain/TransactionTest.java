package com.teonasnetto.desafio.domain.domain;

import com.teonasnetto.desafio.domain.enums.TransactionType;
import com.teonasnetto.desafio.domain.model.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    @Test
    void transactionFieldsAreSetCorrectly() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setType(TransactionType.DEBITO);
        transaction.setDate(LocalDate.of(2023, 10, 1));
        transaction.setValue(100.0);
        transaction.setCpf("12345678901");
        transaction.setCard("1234-5678-9012-3456");
        transaction.setTime(LocalTime.of(12, 0));
        transaction.setStoreOwner("John Doe");
        transaction.setStoreName("John's Store");

        assertEquals(1L, transaction.getId());
        assertEquals(TransactionType.DEBITO, transaction.getType());
        assertEquals(LocalDate.of(2023, 10, 1), transaction.getDate());
        assertEquals(100.0, transaction.getValue());
        assertEquals("12345678901", transaction.getCpf());
        assertEquals("1234-5678-9012-3456", transaction.getCard());
        assertEquals(LocalTime.of(12, 0), transaction.getTime());
        assertEquals("John Doe", transaction.getStoreOwner());
        assertEquals("John's Store", transaction.getStoreName());
    }
}