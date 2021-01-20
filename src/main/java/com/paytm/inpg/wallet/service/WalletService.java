package com.paytm.inpg.wallet.service;


import com.paytm.inpg.user.repository.UserRepository;
import com.paytm.inpg.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private UserRepository userrepository;
    private WalletRepository walletrepository;
}
