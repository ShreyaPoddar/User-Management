package com.paytm.inpg.wallet.controller;


import com.paytm.inpg.user.entity.User;
import com.paytm.inpg.wallet.entity.Wallet;
import com.paytm.inpg.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletController {

    @Autowired
    private WalletService service;

    //Post method
    @PostMapping("/addUserWallet/{phonenumber}")
    public String addUser(@RequestBody Wallet wallet, @PathVariable(value = "phonenumber") String mobilenumber) {
        List<User> mobile_numbers = service.findByMobilenumber(wallet.getPhonenumber());
        List<Wallet> phone_numbers = service.findByPhonenumber(wallet.getPhonenumber());
        if (mobile_numbers.isEmpty())
            return "User data doesn't exist in user table";
        else if (!phone_numbers.isEmpty())
            return "User with same mobile number already exists";
        else {
            service.createWallet(wallet);
            return "User's wallet is successfully created";
        }
    }
}
