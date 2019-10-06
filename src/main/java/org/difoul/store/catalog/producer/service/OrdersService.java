package org.difoul.store.catalog.producer.service;


import org.difoul.store.catalog.model.AbstractProperty;
import org.difoul.store.catalog.model.BooleanProperty;
import org.difoul.store.catalog.model.Order;
import org.difoul.store.catalog.model.StringProperty;
import org.difoul.store.common.event.model.Event;
import org.difoul.store.common.event.service.EventService;
import org.json.JSONObject;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("catalog-exchange")
    Exchange ordersExchange;

    @Autowired
    EventService eventService;


    public void orderShoe(){
        List<AbstractProperty> properties = new ArrayList<AbstractProperty>();
        AbstractProperty p1 = new StringProperty("size" , "42,5");
        AbstractProperty p2 = new StringProperty("color" , "Blue");
        AbstractProperty p3 = new BooleanProperty("gift", false);
        properties.add(p1);
        properties.add(p2);
        properties.add(p3);
        Order order = new Order("shoe", properties);
        // Publish order
        publishOrder(order);
        // Publish event
        Event event = new Event("ORDERS", "PUBLISHED", order.getId(), new JSONObject(order).toString());
        eventService.publlishEvent(event);
    }


    public void orderBag(){
        List<AbstractProperty> properties = new ArrayList<AbstractProperty>();
        AbstractProperty p2 = new StringProperty("color" , "black");
        AbstractProperty p3 = new BooleanProperty("gift", true);
        properties.add(p2);
        properties.add(p3);
        Order order = new Order("bag", properties);
        // Publish order
        publishOrder(order);
        // Publish event
        Event event = new Event("ORDERS", "PUBLISHED", order.getId(), new JSONObject(order).toString());
        eventService.publlishEvent(event);
    }


    private void publishOrder(Order order){
        try {
            rabbitTemplate.convertAndSend(ordersExchange.getName(), order.getOrder_type(), new JSONObject(order).toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
