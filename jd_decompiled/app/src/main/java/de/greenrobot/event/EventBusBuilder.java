package de.greenrobot.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes11.dex */
public class EventBusBuilder {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    List<Class<?>> skipMethodVerificationForClasses;
    boolean throwSubscriberException;
    boolean logSubscriberExceptions = true;
    boolean logNoSubscriberMessages = true;
    boolean sendSubscriberExceptionEvent = true;
    boolean sendNoSubscriberEvent = true;
    boolean eventInheritance = true;
    ExecutorService executorService = DEFAULT_EXECUTOR_SERVICE;

    public EventBus build() {
        return new EventBus(this);
    }

    public EventBusBuilder eventInheritance(boolean z) {
        this.eventInheritance = z;
        return this;
    }

    public EventBusBuilder executorService(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    public EventBus installDefaultEventBus() {
        EventBus eventBus;
        synchronized (EventBus.class) {
            if (EventBus.defaultInstance == null) {
                EventBus.defaultInstance = build();
                eventBus = EventBus.defaultInstance;
            } else {
                throw new EventBusException("Default instance already exists. It may be only set once before it's used the first time to ensure consistent behavior.");
            }
        }
        return eventBus;
    }

    public EventBusBuilder logNoSubscriberMessages(boolean z) {
        this.logNoSubscriberMessages = z;
        return this;
    }

    public EventBusBuilder logSubscriberExceptions(boolean z) {
        this.logSubscriberExceptions = z;
        return this;
    }

    public EventBusBuilder sendNoSubscriberEvent(boolean z) {
        this.sendNoSubscriberEvent = z;
        return this;
    }

    public EventBusBuilder sendSubscriberExceptionEvent(boolean z) {
        this.sendSubscriberExceptionEvent = z;
        return this;
    }

    public EventBusBuilder skipMethodVerificationFor(Class<?> cls) {
        if (this.skipMethodVerificationForClasses == null) {
            this.skipMethodVerificationForClasses = new ArrayList();
        }
        this.skipMethodVerificationForClasses.add(cls);
        return this;
    }

    public EventBusBuilder throwSubscriberException(boolean z) {
        this.throwSubscriberException = z;
        return this;
    }
}
