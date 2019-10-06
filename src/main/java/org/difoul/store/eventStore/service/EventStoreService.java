package org.difoul.store.eventStore.service;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.rabbitmq.client.Channel;
import org.difoul.store.catalog.model.Order;
import org.difoul.store.common.event.model.Event;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventStoreService {

    private List<Event> events;

    @PostConstruct
    private void initEventsStore(){
        events = Collections.synchronizedList( new ArrayList<Event>());
    }

    @RabbitListener(queues = "#{eventsStoreQueue.name}", concurrency = "1")
    public void listen(Event event, Channel channel){
        System.out.println(event.getStatus());
        this.events.add(event);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        try{
            Order o = objectMapper.readValue(event.getPayload(), Order.class);
            System.out.println(o);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<Event> events(){
        return events;
    }
}
