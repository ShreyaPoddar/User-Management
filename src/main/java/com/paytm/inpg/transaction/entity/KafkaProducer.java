package com.paytm.inpg.transaction.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,TransactionElastic> kafkaTemplate;
 @Autowired
//    private KafkaTemplate<String, List<TransactionElastic>> kafkaTemplatee;
    private static final String TOPIC="transactionSummary_byid";

    public void send(TransactionElastic transactionElastic)
    {
        //Printing the transaction don on the producer end
        System.out.println("Adding the transaction---->" +transactionElastic);
        kafkaTemplate.send(TOPIC,transactionElastic);
    }

//    public void sendtransactions(List<TransactionElastic> transactionElastic)
//    {
////        System.out.println("Adding the transaction" +transactionElastic);
//        kafkaTemplatee.send(TOPIC,transactionElastic);
//    }
}
