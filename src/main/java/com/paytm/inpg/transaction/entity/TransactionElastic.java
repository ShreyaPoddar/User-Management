package com.paytm.inpg.transaction.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "transactions",shards = 2)
public class TransactionElastic {

    @Id
    @GeneratedValue
    private String transactionid;

    private String payerphonenumber;
    private String payeephonenumber;
    private Double amount;


}
