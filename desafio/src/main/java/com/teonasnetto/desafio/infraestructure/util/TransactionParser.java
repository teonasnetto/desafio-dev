package com.teonasnetto.desafio.infraestructure.util;

import com.teonasnetto.desafio.domain.enums.TransactionType;
import com.teonasnetto.desafio.domain.model.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class TransactionParser {

    public static Transaction parseLine(String line) {
        try {
            if (line.length() < 80) {
                log.error("Line is too short: {}", line);
                return null;
            }
            Transaction transaction = new Transaction();
            int typeCode = Integer.parseInt(line.substring(0, 1));
            transaction.setType(TransactionType.fromCode(typeCode));
            transaction.setDate(LocalDate.parse(line.substring(1, 9), DateTimeFormatter.ofPattern("yyyyMMdd")));
            transaction.setValue(Double.parseDouble(line.substring(9, 19)) / 100.0);
            transaction.setCpf(line.substring(19, 30));
            transaction.setCard(line.substring(30, 42));
            transaction.setTime(LocalTime.parse(line.substring(42, 48), DateTimeFormatter.ofPattern("HHmmss")));
            transaction.setStoreOwner(line.substring(48, 62).trim());
            transaction.setStoreName(line.substring(62, 80).trim());
            log.info("Parsed line: {}", transaction);
            return transaction;
        } catch (NumberFormatException e) {
            log.error("Error parsing line: {}", line, e);
            return null;
        }
    }
}
