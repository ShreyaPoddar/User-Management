package com.paytm.inpg.transaction.service;

import com.paytm.inpg.transaction.entity.Transaction;
import com.paytm.inpg.transaction.repository.TransactionRepository;
import com.paytm.inpg.user.entity.User;
import com.paytm.inpg.user.repository.UserRepository;
import com.paytm.inpg.wallet.entity.Wallet;
import com.paytm.inpg.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userrepository;
    @Autowired
    private WalletRepository walletrepository;
    @Autowired
    private TransactionRepository transactionrepository;

    public List<Wallet> findByPhonenumber(String phonenumber) //Finding user data from wallet table
    {

        return walletrepository.findByPhonenumber(phonenumber);
    }

    public void makeTransaction(Transaction transaction) {
        transactionrepository.save(transaction);

    }

    public Double getTransactionAmount(Transaction transaction) {
        return transaction.getAmount();
    }

    public List<Transaction> getTransaction() //Returning list of wallets
    {
        return transactionrepository.findAll();
    }

    public List<Transaction> findByTransactionid(int id) //Finding transaction data of particular id
    {

        return transactionrepository.findByTransactionid(id);
    }

    public List<User> findByUserid(int id) //Finding user data from user table
    {

        return userrepository.findByUserid(id);
    }

    public List<Transaction> findByPayerphonenumber(String payerphonenumber) {
        return transactionrepository.findByPayerphonenumber(payerphonenumber);
    }

    public List<Transaction> findByPayeephonenumber(String payeephonenumber) {
        return transactionrepository.findByPayeephonenumber(payeephonenumber);
    }

    public Page<Transaction> getTransaction(Pageable pageable) {
        return transactionrepository.findAll(pageable);
    }
    public Transaction saveTransaction(Transaction transaction) {
        return transactionrepository.save(transaction);

    }
}

