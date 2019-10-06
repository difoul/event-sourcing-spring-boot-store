package org.difoul.store.common.event.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventRabbitConfiguration {


    @Bean(name = "events-exchange")
    TopicExchange eventsExchange(){
        return  new TopicExchange("events");
    }



    @Bean(name = "eventsStoreQueue")
    Queue eventStoreQueue(){
        Queue queue = new Queue("event_store_queue");
        return queue;
    }

    @Bean(name = "eventsStoreBinding")
    Binding eventsStoreBinding(){
        return BindingBuilder.bind(eventStoreQueue()).to(eventsExchange()).with("#");
    }


    @Bean(name = "ordersStoreQueue")
    Queue ordersStoreQueue(){
        Queue queue = new Queue("orders_store_queue");
        return queue;
    }

    @Bean(name = "ordersStoreBinding")
    Binding ordersStoreBinding(){
        return BindingBuilder.bind(ordersStoreQueue()).to(eventsExchange()).with("#");
    }




    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
