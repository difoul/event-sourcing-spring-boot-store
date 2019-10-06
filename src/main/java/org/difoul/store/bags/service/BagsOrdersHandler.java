package org.difoul.store.bags.service;

import com.rabbitmq.client.Channel;
import org.difoul.store.catalog.model.Order;
import org.difoul.store.common.event.model.Event;
import org.difoul.store.common.event.service.EventService;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagsOrdersHandler {

    @Autowired
    EventService eventService;

    @RabbitListener(queues = "#{bagsQueue.name}", concurrency = "1")
    public void listen(String order, Channel channel){
        JSONObject orderJsonObject = new JSONObject(order);
        Event event = new Event("SHOES-PROVIDER", "IN_PROGESS", orderJsonObject.getString("id"), orderJsonObject.toString());
        eventService.publlishEvent(event);
        try {
            System.out.println("Processing order : "+order);
            Thread.sleep(16000);
            System.out.println("Order delivered: "+order);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        Event eventDeliverd = new Event("BAGS-PROVIDER", "DELIVERED", orderJsonObject.getString("id"), orderJsonObject.toString());
        eventService.publlishEvent(eventDeliverd);

    }
}
