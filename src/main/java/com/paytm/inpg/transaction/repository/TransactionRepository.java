package com.paytm.inpg.transaction.repository;

import com.paytm.inpg.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
