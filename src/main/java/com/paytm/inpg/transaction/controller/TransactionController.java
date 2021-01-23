package com.paytm.inpg.transaction.controller;

import com.paytm.inpg.transaction.entity.Transaction;
import com.paytm.inpg.transaction.service.TransactionService;
import com.paytm.inpg.user.entity.User;
import com.paytm.inpg.wallet.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        //Checking if payer and payee wallet exists or not
        if (payee_phone_numbers.isEmpty() || payer_phone_numbers.isEmpty())
            return "Payee/Payer wallet doesn't exist";
        else if(transactionservice.getTransactionAmount(transaction)>payer_phone_numbers.get(0).getBalance())
            return "Payer doesn't have sufficient balance";

        //If both the payer and payee wallets ahave sufficient balance then making the transaction

        //Payer's balance gets debited
        Double debit=payer_phone_numbers.get(0).getBalance() - transactionservice.getTransactionAmount(transaction);
        payer_phone_numbers.get(0).setBalance(debit);

        //Payee's balance gets credited
        Double credit=payee_phone_numbers.get(0).getBalance() + transactionservice.getTransactionAmount(transaction);
        payee_phone_numbers.get(0).setBalance(credit);

        //adding the transaction to the transaction table
        transactionservice.makeTransaction(transaction);
        return "Transaction successful";
    }
    //Get method

    @GetMapping("/transactions")
    //Gives all the transactions
    public Page<Transaction> findAllTransactions(Pageable page)  {
    return transactionservice.getTransaction(page);
    }


    //Gets the status of a particular transaction id
    @GetMapping("/transaction/{id}")
    public String findTransactionByTransactionid(@PathVariable int id)
    {
        //Finding is the transaction has occured or not
        List<Transaction> transactions=transactionservice.findByTransactionid(id);
                if(!transactions.isEmpty())
                return "Successful";
                else
                    return "Failed";
    }

    //Give the transaction summary of a particular user
    @GetMapping("/transactionsummary/{id}")
    public List<Transaction> findByuserid(@PathVariable int id) {

        //finding the users with the given user id
        List<User> users=transactionservice.findByUserid(id);


        //storing the phone number of the user
        String number=users.get(0).getMobilenumber();

        //making the list of transactions having the user as payer
        List<Transaction> payer_details=transactionservice.findByPayerphonenumber(number);

        ////making the list of transactions having the user as payee
        List<Transaction> payee_details=transactionservice.findByPayeephonenumber(number);

        //Merging both the lists
        payee_details.addAll(payer_details);
        if(payee_details.isEmpty() || users.isEmpty())
        {
            List <Transaction> blank = new ArrayList<>();
            return blank;
        }
        else
            return payee_details;

    }


}

