package org.difoul.store.catalog.producer.controller;


import org.difoul.store.catalog.producer.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {


    @Autowired
    OrdersService ordersService;

    @RequestMapping(path = "/shoes/order", method = RequestMethod.GET)
    public String orderShoe(){
        ordersService.orderShoe();
        return "[SHOES] order sent";
    }

    @RequestMapping(path = "/bags/order", method = RequestMethod.GET)
    public String orderBag(){
        ordersService.orderBag();
        return "[BAGS] order sent";
    }
}
