package com.aybuke.tokentransaction.service;

import com.aybuke.tokentransaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITransactionService {

    Transaction saveTransaction(Transaction transaction);

    void deleteTransaction(Long id);

    List<Transaction> getAllTransactions(Long userId);
}
