package com.lqzj.core.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReloadMergeEvent extends BaseEvent {

    private int reloadNum;

    public ReloadMergeEvent(Object source) {
        super(source);
    }

    public ReloadMergeEvent(Object source, int reloadNum) {
        super(source);

        this.reloadNum = reloadNum;
    }
}
