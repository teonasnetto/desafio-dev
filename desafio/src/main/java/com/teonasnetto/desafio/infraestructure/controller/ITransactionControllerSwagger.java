package com.teonasnetto.desafio.infraestructure.controller;

import com.teonasnetto.desafio.domain.model.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Transactions", description = "Endpoints for managing transactions")
public interface ITransactionControllerSwagger {

    @Transactional
    @Operation(summary = "Upload a file with transactions", description = "Uploads a file containing transactions and processes it")
    void uploadFile(MultipartFile file);

    @Operation(summary = "Get all transactions", description = "Retrieves a list of all transactions")
    List<Transaction> getAllTransactions();
}