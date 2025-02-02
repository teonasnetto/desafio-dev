package com.teonasnetto.desafio.infraestructure.util;

import com.teonasnetto.desafio.domain.enums.TransactionType;
import com.teonasnetto.desafio.domain.model.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TransactionParserTest {

    @Test
    void parseLineReturnsTransactionForValidInput() {
        String line = "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ";
        Transaction transaction = TransactionParser.parseLine(line);

        assertEquals(TransactionType.FINANCIAMENTO, transaction.getType());
        assertEquals(LocalDate.of(2019, 3, 1), transaction.getDate());
        assertEquals(142.00, transaction.getValue());
        assertEquals("09620676017", transaction.getCpf());
        assertEquals("4753****3153", transaction.getCard());
        assertEquals(LocalTime.of(15, 34, 53), transaction.getTime());
        assertEquals("JOÃO MACEDO", transaction.getStoreOwner());
        assertEquals("BAR DO JOÃO", transaction.getStoreName());
    }

    @Test
    void parseLineReturnsNullForShortLine() {
        String line = "3201903010000014";
        Transaction transaction = TransactionParser.parseLine(line);

        assertNull(transaction);
    }

    @Test
    void parseLineReturnsNullForInvalidTypeCode() {
        String line = "X201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ";
        Transaction transaction = TransactionParser.parseLine(line);

        assertNull(transaction);
    }

    @Test
    void parseLineReturnsNullForInvalidValue() {
        String line = "32019030100000X4200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ";
        Transaction transaction = TransactionParser.parseLine(line);

        assertNull(transaction);
    }
}