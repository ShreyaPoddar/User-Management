package com.paytm.inpg.transaction.service;

import com.paytm.inpg.transaction.entity.Transaction;
import com.paytm.inpg.transaction.repository.TransactionRepository;
import com.paytm.inpg.user.repository.UserRepository;
import com.paytm.inpg.wallet.entity.Wallet;
import com.paytm.inpg.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void makeTransaction(Transaction transaction)
    {
        transactionrepository.save(transaction);
    }

    public Double getTransactionAmount(Transaction transaction)
    {
        return transaction.getAmount();
    }
    public List<Transaction> getTransaction() //Returning list of wallets
    {
        return transactionrepository.findAll();
    }
    public List<Transaction> findByTransactionid(Integer id) //Finding user data from wallet table
    {

        return transactionrepository.findByTransactionid(id);
    }
}
