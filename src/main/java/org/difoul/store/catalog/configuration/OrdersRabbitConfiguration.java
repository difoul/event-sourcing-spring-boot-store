package org.difoul.store.catalog.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersRabbitConfiguration {

    @Bean(name = "catalog-exchange")
    DirectExchange ordersExchange(){
        return  new DirectExchange("catalog");
    }



    @Bean(name = "shoesQueue")
    Queue shoesQueue(){
        Queue queue = new Queue("shoes_queue");
        return queue;
    }

    @Bean(name = "shoesBinding")
    Binding shoesBinding(){
        return BindingBuilder.bind(shoesQueue()).to(ordersExchange()).with("shoe");
    }

    @Bean(name = "bagsQueue")
    Queue bagsQueue(){
        Queue queue = new Queue("bags_queue");
        return queue;
    }

    @Bean(name = "bagsBinding")
    Binding bagsBinding(){
        return BindingBuilder.bind(bagsQueue()).to(ordersExchange()).with("bag");
    }

}
