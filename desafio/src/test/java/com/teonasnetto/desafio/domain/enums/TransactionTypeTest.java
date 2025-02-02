package com.teonasnetto.desafio.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTypeTest {

    @Test
    void fromCodeReturnsCorrectTransactionType() {
        assertEquals(TransactionType.DEBITO, TransactionType.fromCode(1));
        assertEquals(TransactionType.BOLETO, TransactionType.fromCode(2));
        assertEquals(TransactionType.FINANCIAMENTO, TransactionType.fromCode(3));
        assertEquals(TransactionType.CREDITO, TransactionType.fromCode(4));
        assertEquals(TransactionType.RECEBIMENTO_EMPRESTIMO, TransactionType.fromCode(5));
        assertEquals(TransactionType.VENDAS, TransactionType.fromCode(6));
        assertEquals(TransactionType.RECEBIMENTO_TED, TransactionType.fromCode(7));
        assertEquals(TransactionType.RECEBIMENTO_DOC, TransactionType.fromCode(8));
        assertEquals(TransactionType.ALUGUEL, TransactionType.fromCode(9));
    }

    @Test
    void fromCodeThrowsExceptionForInvalidCode() {
        assertThrows(IllegalArgumentException.class, () -> TransactionType.fromCode(0));
        assertThrows(IllegalArgumentException.class, () -> TransactionType.fromCode(10));
    }
}