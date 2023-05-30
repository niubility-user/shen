package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executor;

@Beta
/* loaded from: classes12.dex */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(String str, Executor executor) {
        super(str, executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super("default", executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
    }

    public AsyncEventBus(Executor executor) {
        super("default", executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }
}
