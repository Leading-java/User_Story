package com.example.book.repositrory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}