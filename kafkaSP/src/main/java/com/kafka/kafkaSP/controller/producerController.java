package com.kafka.kafkaSP.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/Producer")
public class producerController {
    private static final String TOPIC_NAME = "testy";

    private static final String BOOTSTRAP_SERVICE = "127.0.0.1:9092";

    private final Producer<String ,String > kafkaProducer ;

    public producerController(){
        Properties prop =new Properties();
        prop.put("bootstrap.servers" , "127.0.0.1:9092");
        prop.put("key.serializer" , "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer" , "org.apache.kafka.common.serialization.StringSerializer");
        this.kafkaProducer= new KafkaProducer<>(prop);
    }

    @PostMapping("/event")
    public void sendEventToKafka(@RequestBody String evenData){
        ProducerRecord<String,String > record = new ProducerRecord<>(TOPIC_NAME , "userevent " , evenData);
        kafkaProducer.send(record,(metadata ,exception)->{
            if (exception!=null){
                System.err.println("error sending message to kafka " + exception.getMessage());
            }else {
                System.err.println("Message send to kafka , offset :" +metadata.offset());
            }
        });
    }

}
