package com.simplepayment.simplepayment.repositories;

import com.simplepayment.simplepayment.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
