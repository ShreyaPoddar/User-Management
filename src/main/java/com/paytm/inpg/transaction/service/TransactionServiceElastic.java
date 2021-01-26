package com.paytm.inpg.transaction.service;

import com.paytm.inpg.transaction.entity.TransactionElastic;
import com.paytm.inpg.transaction.repository.TransactionRepositoryElastic;
import com.paytm.inpg.wallet.entity.Wallet;
import com.paytm.inpg.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceElastic {

    @Autowired
    private WalletRepository walletrepository;
    @Autowired
    private TransactionRepositoryElastic transactionRepositoryElastic;

    public void makeTransaction(TransactionElastic transactionElastic) {
        transactionRepositoryElastic.save(transactionElastic);

    }
    public void makeTransactiontowallet(Wallet wallet) {
        walletrepository.save(wallet);

    }
    public List<Wallet> findByPhonenumber(String phonenumber) //Finding user data from wallet table
    {

        return walletrepository.findByPhonenumber(phonenumber);
    }
    public Double getTransactionAmount(TransactionElastic transactionElastic) {
        return transactionElastic.getAmount();
    }



}
