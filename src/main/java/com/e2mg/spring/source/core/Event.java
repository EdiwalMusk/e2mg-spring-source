package com.e2mg.spring.source.core;

import org.springframework.context.ApplicationEvent;

public class Event extends ApplicationEvent {

    public Event(Object source) {
        super(source);
    }
}
