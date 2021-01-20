package com.paytm.inpg.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



public class Wallet {



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name="USER_WALLET")
    public class User {
        @Id
        private String mobilenumber;
        private Double balance;

        @PrePersist
        public void setBalance(){
            this.balance = new Double(0);
        }
    }



}
