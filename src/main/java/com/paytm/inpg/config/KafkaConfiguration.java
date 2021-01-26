package com.paytm.inpg.config;

import com.paytm.inpg.transaction.entity.TransactionElastic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    //To create messages, first, we need to configure a ProducerFactory which sets the strategy for creating Kafka Producer instances
    @Bean
    public ProducerFactory<String,TransactionElastic> producerFactory()
    {
        Map<String,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
//
    }

    @Bean
    public KafkaTemplate<String,TransactionElastic> kafkaTemplate()
    {

        return new KafkaTemplate<>(producerFactory());
    }

    //For consuming messages, we need to configure a ConsumerFactory and a KafkaListenerContainerFactory.
    @Bean
    public ConsumerFactory<String,TransactionElastic> consumerFactory()
    {
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new JsonDeserializer<>(TransactionElastic.class));
//
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,TransactionElastic> kafkaListenerContainerFactory(){

        ConcurrentKafkaListenerContainerFactory<String,TransactionElastic> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}