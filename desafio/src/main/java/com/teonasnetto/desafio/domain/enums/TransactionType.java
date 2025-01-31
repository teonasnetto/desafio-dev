package com.teonasnetto.desafio.domain.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEBIT(1, "Débito", "Entrada", "+"),
    BOLETO(2, "Boleto", "Saída", "-"),
    FINANCIAMENTO(3, "Financiamento", "Saída", "-"),
    CREDITO(4, "Crédito", "Entrada", "+"),
    RECEBIMENTO_EMPRESTIMO(5, "Recebimento Empréstimo", "Entrada", "+"),
    VENDAS(6, "Vendas", "Entrada", "+"),
    RECEBIMENTO_TED(7, "Recebimento TED", "Entrada", "+"),
    RECEBIMENTO_DOC(8, "Recebimento DOC", "Entrada", "+"),
    ALUGUEL(9, "Aluguel", "Saída", "-");

    private final int code;
    private final String description;
    private final String nature;
    private final String sign;

    TransactionType(int code, String description, String nature, String sign) {
        this.code = code;
        this.description = description;
        this.nature = nature;
        this.sign = sign;
    }

    public static TransactionType fromCode(int code) {
        for (TransactionType type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid transaction type code: " + code);
    }
}
