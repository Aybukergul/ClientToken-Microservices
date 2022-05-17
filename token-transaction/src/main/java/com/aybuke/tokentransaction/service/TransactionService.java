package com.aybuke.tokentransaction.service;

import com.aybuke.tokentransaction.model.Transaction;
import com.aybuke.tokentransaction.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService implements ITransactionService{
    @Autowired
    private ITransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction){
        transaction.setTransactionTime(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getAllTransactions(Long userId){
        return transactionRepository.findAllByUserId(userId);
    }

}
