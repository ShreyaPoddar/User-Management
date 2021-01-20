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
    private WalletRepository walletrepository;

    //Post method
    public List<User> findByMobilenumber(String mobilenumber)
    {

        return userrepository.findByMobilenumber(mobilenumber);
    }
    public List<Wallet> findByPhonenumber(String mobilenumber)
    {

        return walletrepository.findByPhonenumber(mobilenumber);
    }
    public void createWallet(Wallet wallet)
    {
        walletrepository.save(wallet);
    }
}
