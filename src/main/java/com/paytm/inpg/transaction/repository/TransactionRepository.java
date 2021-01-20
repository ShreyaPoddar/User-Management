package com.paytm.inpg.transaction.repository;

import com.paytm.inpg.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

     List<Transaction> findByTransactionid(Integer transactionid);
    List<Transaction> findByPayerphone(String payerphonenumber);
    List<Transaction> findByPayeephone(String payeephonenumber);

}
