package com.paytm.inpg.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name="TRANSACTION_TABLE")
    public class Transaction {
        @Id
        @GeneratedValue()
        private int transactiionid;
        private String payerphonenumber;
        private String payeephonenumber;
        private Double amount;

    }

}

