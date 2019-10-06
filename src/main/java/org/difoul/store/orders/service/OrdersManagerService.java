package org.difoul.store.orders.service;


import com.rabbitmq.client.Channel;
import org.difoul.store.common.event.model.Event;
import org.difoul.store.orders.model.Order;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrdersManagerService {

    private Map<String, Order> orders;

    @PostConstruct
    private void initOrdersMap(){
        orders = new ConcurrentHashMap<String, Order>();
    }

    @RabbitListener(queues = "#{ordersStoreQueue.name}", concurrency = "1")
    public void listen(Event message, Channel channel){
        JSONObject jsonObject = new JSONObject(message.getPayload());
        Order order = new Order();
        order.setStatus(message.getStatus());
        order.setId(message.getAggregateId());
        order.setOrder_type(jsonObject.getString("order_type"));
        this.orders.put(message.getAggregateId(), order);
    }

    public Map<String, Order> orders(){
        return orders;
    }
}
