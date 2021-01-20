package com.paytm.inpg.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name="USER_WALLET")
    public class Wallet {
        @Id
        @GeneratedValue()
        int id;
        private String phonenumber;
        private Double balance;

        @PrePersist
        public void setBalance() {
            this.balance = new Double(0);
        }
    }
