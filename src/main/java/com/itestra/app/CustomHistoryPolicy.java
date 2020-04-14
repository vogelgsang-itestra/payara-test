package com.itestra.app;

import org.eclipse.persistence.history.HistoryPolicy;
import org.eclipse.persistence.internal.sessions.AbstractSession;

import javax.enterprise.inject.spi.CDI;

public class CustomHistoryPolicy extends HistoryPolicy {

    private static final long serialVersionUID = -5950334160374896409L;

    @Override
    public Object getCurrentTime(AbstractSession session) {
        CustomRequestContext context = CDI.current().select(CustomRequestContext.class).get();
        return context.getCurrentTimestamp();
    }
}
