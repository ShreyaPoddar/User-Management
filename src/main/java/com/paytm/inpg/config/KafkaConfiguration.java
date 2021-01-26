package com.paytm.inpg.config;

import com.paytm.inpg.transaction.entity.Transaction;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaConfiguration {

    @Bean
    public ProducerFactory producerFactory()
    {
        Map<String,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
//
    }

    @Bean
    public KafkaTemplate<String, List<Transaction>> kafkaTemplate()
    {
        return new KafkaTemplate<>(producerFactory());
    }


//    @Bean
//    public KafkaTemplate<String,String> kafkaTemplate1()
//    {
//        return new KafkaTemplate<>(producerFactory());
//    }

}