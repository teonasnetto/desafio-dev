package com.teonasnetto.desafio.infraestructure.controller;

import com.teonasnetto.desafio.application.TransactionService;
import com.teonasnetto.desafio.domain.model.Transaction;
import com.teonasnetto.desafio.infraestructure.util.TransactionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void uploadFileThrowsExceptionForEmptyFile() {
        when(file.isEmpty()).thenReturn(true);

        assertThrows(MultipartException.class, () -> transactionController.uploadFile(file));
    }

    @Test
    void uploadFileProcessesValidFile() throws Exception {
        String content = "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));

        when(file.getInputStream()).thenReturn(inputStream);
        when(file.isEmpty()).thenReturn(false);

        Transaction mockTransaction = new Transaction();

        try (MockedStatic<TransactionParser> mockedParser = Mockito.mockStatic(TransactionParser.class)) {
            mockedParser.when(() -> TransactionParser.parseLine(any(String.class))).thenReturn(mockTransaction);

            transactionController.uploadFile(file);

            verify(transactionService, times(1)).saveTransaction(mockTransaction);
        }
    }

    @Test
    void uploadFileThrowsExceptionForInvalidFileContent() throws Exception {
        String content = "INVALID CONTENT";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));

        when(file.getInputStream()).thenReturn(inputStream);
        when(file.isEmpty()).thenReturn(false);

        try (MockedStatic<TransactionParser> mockedParser = Mockito.mockStatic(TransactionParser.class)) {
            mockedParser.when(() -> TransactionParser.parseLine(any(String.class))).thenReturn(null);

            assertThrows(RuntimeException.class, () -> transactionController.uploadFile(file));
        }
    }

    @Test
    void getAllTransactionsReturnsTransactions() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        List<Transaction> result = transactionController.getAllTransactions();

        assertEquals(transactions, result);
    }
}