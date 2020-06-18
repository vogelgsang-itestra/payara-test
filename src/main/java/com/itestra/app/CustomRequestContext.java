package com.itestra.app;

import javax.enterprise.context.RequestScoped;
import java.time.Clock;
import java.time.LocalDateTime;

@RequestScoped
public class CustomRequestContext {
    private LocalDateTime currentTimestamp = LocalDateTime.now(Clock.systemUTC());

    public LocalDateTime getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(LocalDateTime currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }
}
