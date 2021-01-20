package com.paytm.inpg.transaction.controller;

import com.paytm.inpg.transaction.entity.Transaction;
import com.paytm.inpg.transaction.service.TransactionService;
import com.paytm.inpg.wallet.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionservice;

    //Post method
    @PostMapping("/transaction")
    public String addtransaction(@RequestBody Transaction transaction) {
        //Method to make transaction from payer to payee's account
        List<Wallet> payer_phone_numbers = transactionservice.findByPhonenumber(transaction.getPayerphonenumber());
        List<Wallet> payee_phone_numbers = transactionservice.findByPhonenumber(transaction.getPayeephonenumber());
        if (payee_phone_numbers.isEmpty() || payer_phone_numbers.isEmpty())
            return "Payee/Payer wallet doesn't exist";
        else if(transactionservice.getTransactionAmount(transaction)>payer_phone_numbers.get(0).getBalance())
            return "Payer doesn't have sufficient balance";
        payer_phone_numbers.get(0).changeBalance(-transactionservice.getTransactionAmount(transaction));
        payee_phone_numbers.get(0).changeBalance(transactionservice.getTransactionAmount(transaction));
        transactionservice.makeTransaction(transaction);
        return "Transaction successful";
    }
    //Get method

    @GetMapping("/transactions")
    //Gives all the transactions
    public List<Transaction> findAllTransactions() {
        return transactionservice.getTransaction();
    }
    //Gets the status of a particular transaction id
    @GetMapping("/transaction/{id}")
    public String findTransactionByTransactionid(@PathVariable int id)
    {
        List<Transaction> transactions=transactionservice.findByTransactionid(id);
                if(!transactions.isEmpty())
                return "Successful";
                else
                    return "Failed";
    }
    //Give the transaction summary of a particular user
    @GetMapping("/transactionsummary")
    public List<Transaction> findByuserid() {
        return transactionservice.getTransaction();

    }


}

