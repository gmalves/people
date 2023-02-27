package com.masson.people.messaging;

import com.masson.people.business.repository.PeopleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PeopleProducerImp implements PeopleProducer {

    @Value("${people.topic.people-created}")
    private String topic;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        this.kafkaTemplate.send(topic, message);
    }
}
