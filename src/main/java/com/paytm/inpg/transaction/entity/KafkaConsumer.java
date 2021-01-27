package com.paytm.inpg.transaction.entity;

import com.paytm.inpg.transaction.repository.TransactionRepositoryElastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    @Autowired
    private TransactionRepositoryElastic repositoryElastic;

    @KafkaListener(topics = "transactionSummary_byid", groupId = "group_json", containerFactory = "kafkaListenerContainerFactory")
    public void consume(TransactionElastic transactionElastic) throws IOException {
//     repositoryElastic.save(transactionElastic);
        //Printing the transaction on the consumer end
        System.out.println("Received transaction---> " + transactionElastic);
    }

//    @KafkaListener(topics = "transactionSummary_byid", groupId = "group_json", containerFactory = "kafkaListenerContainerFactory")
//    public void consumetransactions(List<TransactionElastic> transactionElastic) throws IOException {
////     repositoryElastic.save(transactionElastic);
//        System.out.println("Received  message " + transactionElastic);
//
//    }
}
