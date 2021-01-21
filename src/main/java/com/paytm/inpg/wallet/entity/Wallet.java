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
        @GeneratedValue(strategy= GenerationType.AUTO)
        int id;
        private String phonenumber;
        private Double balance;


//        public void changeBalance(Double balance) {
//            this.balance += balance;
//        }
    }
