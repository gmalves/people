package com.masson.people.messaging;

import com.masson.people.business.repository.PeopleProducer;
import com.masson.people.business.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PeopleProducerImp implements PeopleProducer {

    private static Logger logger = LoggerFactory.getLogger(PeopleProducerImp.class);
    @Value("${people.kafka.topic.people-created}")
    private String topic;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info("Sending message to topic " + topic);
        this.kafkaTemplate.send(topic, message);
    }
}
