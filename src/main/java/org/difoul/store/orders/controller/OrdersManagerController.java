package org.difoul.store.orders.controller;

import org.difoul.store.orders.model.Order;
import org.difoul.store.orders.service.OrdersManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrdersManagerController {

    @Autowired
    OrdersManagerService ordersManagerService;

    @RequestMapping(path = "/orders", method = RequestMethod.GET)
    public Map<String, Order> events(){
        return ordersManagerService.orders();
    }
}
