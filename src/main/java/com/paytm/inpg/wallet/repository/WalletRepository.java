package com.paytm.inpg.wallet.repository;

import com.paytm.inpg.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Integer> {
}
