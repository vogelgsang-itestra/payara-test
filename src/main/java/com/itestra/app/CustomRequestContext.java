package com.itestra.app;

import javax.enterprise.context.RequestScoped;
import java.time.Clock;
import java.time.LocalDateTime;

@RequestScoped
public class CustomRequestContext {
    private final LocalDateTime currentTimestamp = LocalDateTime.now(Clock.systemUTC());

    public LocalDateTime getCurrentTimestamp() {
        return currentTimestamp;
    }
}
