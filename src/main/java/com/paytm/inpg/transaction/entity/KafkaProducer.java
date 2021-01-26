package com.paytm.inpg.transaction.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,TransactionElastic> kafkaTemplate;

    private static final String TOPIC="transactionSummary_byid";

    public void send(TransactionElastic transactionElastic)
    {
        kafkaTemplate.send(TOPIC,transactionElastic);
    }

}
