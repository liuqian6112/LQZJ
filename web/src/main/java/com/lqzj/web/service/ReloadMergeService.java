package com.lqzj.web.service;

import com.lqzj.common.event.ReloadMergeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ReloadMergeService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void reloadMerge(int num) {
        eventPublisher.publishEvent(new ReloadMergeEvent(ReloadMergeService.this, num));
    }
}
