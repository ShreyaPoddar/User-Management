package com.paytm.inpg.transaction.controller;

import com.paytm.inpg.transaction.entity.KafkaProducer;
import com.paytm.inpg.transaction.entity.Transaction;
import com.paytm.inpg.transaction.entity.TransactionElastic;
import com.paytm.inpg.transaction.service.TransactionService;
import com.paytm.inpg.transaction.service.TransactionServiceElastic;
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

    @Autowired
    TransactionServiceElastic service;

    @Autowired
    KafkaProducer kafkaProducer;

//    @Autowired
//    private KafkaTemplate<String,TransactionElastic> kafkaTemplate;
//
//    private static final String TOPIC="transactionSummary_byid";

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

        //If both the payer and payee wallets have sufficient balance then making the transaction

        //Payer's balance gets debited
        Double debit=payer_phone_numbers.get(0).getBalance() - transactionservice.getTransactionAmount(transaction);
        payer_phone_numbers.get(0).setBalance(debit);

        //Payee's balance gets credited
        Double credit=payee_phone_numbers.get(0).getBalance() + transactionservice.getTransactionAmount(transaction);
        payee_phone_numbers.get(0).setBalance(credit);

        transactionservice.makeTransaction( payer_phone_numbers.get(0));
        transactionservice.makeTransaction( payee_phone_numbers.get(0));

        //adding the transaction to the transaction table
        transactionservice.makeTransaction(transaction);
        return "Transaction successful";
    }

    //Post method for Elastic Search
    @PostMapping("/transaction1")
    public String addtransactionelastic(@RequestBody TransactionElastic transactionElastic) {
        //Method to make transaction from payer to payee's account
        List<Wallet> payer_phone_numbers = service.findByPhonenumber(transactionElastic.getPayerphonenumber());
        List<Wallet> payee_phone_numbers = service.findByPhonenumber(transactionElastic.getPayeephonenumber());

        //Checking if payer and payee wallet exists or not
        if (payee_phone_numbers.isEmpty() || payer_phone_numbers.isEmpty())
            return "Payee/Payer wallet doesn't exist";
        else if(service.getTransactionAmount(transactionElastic)>payer_phone_numbers.get(0).getBalance())
            return "Payer doesn't have sufficient balance";

        //If both the payer and payee wallets ahave sufficient balance then making the transaction

        //Payer's balance gets debited
        Double debit=payer_phone_numbers.get(0).getBalance() - service.getTransactionAmount(transactionElastic);
        payer_phone_numbers.get(0).setBalance(debit);

        //Payee's balance gets credited
        Double credit=payee_phone_numbers.get(0).getBalance() + service.getTransactionAmount(transactionElastic);
        payee_phone_numbers.get(0).setBalance(credit);

        service.makeTransactiontowallet(payer_phone_numbers.get(0));
        service.makeTransactiontowallet(payee_phone_numbers.get(0));
        //adding the transaction to the elastic search db
        service.makeTransaction(transactionElastic);
        //adding the transaction to kafka db
        kafkaProducer.send(transactionElastic);
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
        List <Transaction> alltransactions = new ArrayList<>();
        if(!users.isEmpty())
        {
        //storing the phone number of the user
        String number=users.get(0).getMobilenumber();

        //making the list of transactions having the user as payer
        List<Transaction> payer_details=transactionservice.findByPayerphonenumber(number);

        ////making the list of transactions having the user as payee
        List<Transaction> payee_details=transactionservice.findByPayeephonenumber(number);

        //Merging both the lists


            alltransactions.addAll(payer_details);
            alltransactions.addAll(payee_details);
        }
            return alltransactions;


    }

    //Give the transaction summary of a particular user from ElasticSearch db
    @GetMapping("/transactionsummary1/{id}")
    public List<TransactionElastic> findByuseridelastic(@PathVariable int id) {

        //finding the users with the given user id
        List<User> users=service.findByUserid(id);
        List <TransactionElastic> alltransactions = new ArrayList<>();
        if(!users.isEmpty())
        {
            //storing the phone number of the user
            String number=users.get(0).getMobilenumber();

            //making the list of transactions having the user as payer
            List<TransactionElastic> payer_details=service.findByPayerphonenumber(number);

            ////making the list of transactions having the user as payee
            List<TransactionElastic> payee_details=service.findByPayeephonenumber(number);

            //Merging both the lists
            alltransactions.addAll(payer_details);
            alltransactions.addAll(payee_details);
        }
//        kafkaProducer.sendtransactions(alltransactions);

        return alltransactions;
    }
    }

