package com.paytm.inpg.transaction.repository;

import com.paytm.inpg.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

     List<Transaction> findByTransactionid(Integer transactionid);
    List<Transaction> findByPayerphonenumber(String payerphonenumber);
    List<Transaction> findByPayeephonenumber(String payeephonenumber);

}
