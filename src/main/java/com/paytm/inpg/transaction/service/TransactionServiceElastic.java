package com.paytm.inpg.transaction.service;

import com.paytm.inpg.transaction.entity.TransactionElastic;
import com.paytm.inpg.transaction.repository.TransactionRepositoryElastic;
import com.paytm.inpg.user.entity.User;
import com.paytm.inpg.user.repository.UserRepository;
import com.paytm.inpg.wallet.entity.Wallet;
import com.paytm.inpg.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceElastic {

    @Autowired
    private UserRepository userrepository;
    @Autowired
    private WalletRepository walletrepository;
    @Autowired
    private TransactionRepositoryElastic repository;


    //saving transaction to the transaction repo
    public void makeTransaction(TransactionElastic transactionElastic) {
        repository.save(transactionElastic);

    }

    //updating wallet balance
    public void makeTransactiontowallet(Wallet wallet) {
        walletrepository.save(wallet);

    }
    public List<Wallet> findByPhonenumber(String phonenumber) //Finding user data from wallet table
    {

        return walletrepository.findByPhonenumber(phonenumber);
    }

    //getting the amount for which transaction is to be made
    public Double getTransactionAmount(TransactionElastic transactionElastic) {
        return transactionElastic.getAmount();
    }
    public List<User> findByUserid(int id) //Finding user data from user table
    {

        return userrepository.findByUserid(id);
    }

    public List<TransactionElastic> findByPayerphonenumber(String payerphonenumber) {
        return repository.findByPayerphonenumber(payerphonenumber);
    }

    public List<TransactionElastic> findByPayeephonenumber(String payeephonenumber) {
        return repository.findByPayeephonenumber(payeephonenumber);
    }



}
