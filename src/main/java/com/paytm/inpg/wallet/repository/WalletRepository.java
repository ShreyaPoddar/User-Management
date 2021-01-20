package com.paytm.inpg.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paytm.inpg.wallet.entity.Wallet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Integer>
{
    List<Wallet> findByPhonenumber(String phonenumber);

}
