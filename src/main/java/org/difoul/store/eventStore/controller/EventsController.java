package org.difoul.store.eventStore.controller;

import org.difoul.store.common.event.model.Event;
import org.difoul.store.eventStore.service.EventStoreService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EventsController {

    @Autowired
    EventStoreService eventStoreService;

    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Event> events(){
        return eventStoreService.events();
    }
}
