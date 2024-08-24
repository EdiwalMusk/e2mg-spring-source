package com.e2mg.spring.source.core;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventComponent {

    @EventListener
    public void acceptEvent(Event event) {
        System.out.println(event);
    }
}
