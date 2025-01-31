import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../transaction.service';
import { Transaction } from '../transaction.model';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {
  transactions: Transaction[] = [];
  total: number = 0;

  private transactionTypeSignMap: { [key: string]: number } = {
    'DEBITO': 1,
    'BOLETO': -1,
    'FINANCIAMENTO': -1,
    'CREDITO': 1,
    'RECEBIMENTO_EMPRESTIMO': 1,
    'VENDAS': 1,
    'RECEBIMENTO_TED': 1,
    'RECEBIMENTO_DOC': 1,
    'ALUGUEL': -1
  };

  constructor(private transactionService: TransactionService) {}

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.transactionService.getAllTransactions().subscribe({
      next: data => {
        this.transactions = data;
        this.calculateTotal();
      },
      error: error => {
        console.error('Failed to load transactions:', error);
      }
    });
  }

calculateTotal(): void {
    this.total = this.transactions.reduce((acc, transaction) => {
      const transactionType = transaction.type;
      if (transactionType) {
        const sign = this.transactionTypeSignMap[transactionType] || 1;
        const amount = transaction.value ?? 0;
        return acc + (amount * sign);
      }
      return acc;
    }, 0);
  }

  onUploadSuccess(): void {
    this.loadTransactions();
  }
}