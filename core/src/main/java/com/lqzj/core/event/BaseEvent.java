package com.lqzj.core.event;

import org.springframework.context.ApplicationEvent;

public abstract class BaseEvent extends ApplicationEvent {
    private static final long serialVersionUID = 7941163200045360463L;

    public BaseEvent(Object source) {
        super(source);
    }
}
