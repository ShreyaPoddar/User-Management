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
        //finds the user with same mobile number as given in the wallet input
        List<User> mobile_numbers = service.findByMobilenumber(wallet.getPhonenumber());
        //finds the wallet with same mobile number as given in the wallet input
        List<Wallet> phone_numbers = service.findByPhonenumber(wallet.getPhonenumber());
        //Checks if the list with the user having same mobile number exists in the user table or not
        if (mobile_numbers.isEmpty())
            return "User data doesn't exist in user table";
        //if the user wallet is already created
        else if (!phone_numbers.isEmpty())
            return "User with same mobile number already exists";
        //if the user data exists in the user table and wallet is not created then creating user's wallet
        else {
            service.createWallet(wallet);
            return "User's wallet is successfully created";
        }
    }
}
