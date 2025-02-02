package com.teonasnetto.desafio.infraestructure.controller;

import com.teonasnetto.desafio.application.TransactionService;
import com.teonasnetto.desafio.domain.model.Transaction;
import com.teonasnetto.desafio.infraestructure.util.TransactionParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController implements ITransactionControllerSwagger {

    private final TransactionService transactionService;

    @PostMapping("/upload")
    @Transactional
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new MultipartException("File is empty");
        }
        log.info("Received file: {}", file.getOriginalFilename());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            List<Transaction> transactions = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                Transaction transaction = TransactionParser.parseLine(line);
                if (transaction != null) {
                    transactions.add(transaction);
                }
            }
            if (transactions.isEmpty()) {
                log.info("No valid transactions found in file");
                throw new RuntimeException("No valid transactions found in file");
            }
            transactions.forEach(transactionService::saveTransaction);
        } catch (Exception e) {
            log.error("Error processing file", e);
            throw new RuntimeException("Error processing file", e);
        }
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}