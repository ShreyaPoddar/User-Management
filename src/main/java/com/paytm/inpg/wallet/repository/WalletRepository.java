package com.paytm.inpg.wallet.repository;

import com.paytm.inpg.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet,Integer> {
    public List<Wallet> findByPhonenumber(String phonenumber);
}
