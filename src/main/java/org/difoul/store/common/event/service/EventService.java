package org.difoul.store.common.event.service;


import org.difoul.store.common.event.model.Event;
import org.json.JSONObject;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("events-exchange")
    Exchange eventsExcghange;

    public void publlishEvent(Event event) {
        try {
            //JSONObject j = new JSONObject(event);
            rabbitTemplate.convertAndSend(eventsExcghange.getName(), "", event);
        } catch (Exception e) {
            e.printStackTrace(); //TODO : Handle correctly the error
        }
    }
}
