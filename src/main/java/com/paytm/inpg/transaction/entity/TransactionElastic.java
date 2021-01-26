package com.paytm.inpg.transaction.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "transactions",shards = 2)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionElastic {
    @Id
    int transactionid;
    String payerphonenumber;
    String payeephonenumber;
    Double amount;


}
