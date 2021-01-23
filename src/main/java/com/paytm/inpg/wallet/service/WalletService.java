package com.paytm.inpg.wallet.service;


import com.paytm.inpg.user.entity.User;
import com.paytm.inpg.user.repository.UserRepository;
import com.paytm.inpg.wallet.entity.Wallet;
import com.paytm.inpg.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
     private UserRepository userrepository;
    @Autowired
     private WalletRepository walletrepository;

    //Post method
    public List<User> findByMobilenumber(String mobilenumber) //Finding user data from user table
    {

        return userrepository.findByMobilenumber(mobilenumber);
    }
    public List<Wallet> findByPhonenumber(String phonenumber) //Finding user data from wallet table
    {

        return walletrepository.findByPhonenumber(phonenumber);
    }
    public void createWallet(Wallet wallet)//Creating user wallet
    {
        walletrepository.save(wallet);
    }
    //GET METHOD
    public List<Wallet> getWallet() //Returning list of wallets
    {
        return walletrepository.findAll();
    }
    public Wallet getUserByPhonenumber(String phonenumber) //Returning particular wallet
    {
        return (Wallet) walletrepository.findByPhonenumber(phonenumber);
    }
    public Wallet updateWallet(Wallet wallet,Double balance)
    {
        Wallet existingWallet= (Wallet) walletrepository.findByPhonenumber(wallet.getPhonenumber());
        existingWallet.setBalance(wallet.getBalance()+balance);
        return walletrepository.save(existingWallet);

    }
}
