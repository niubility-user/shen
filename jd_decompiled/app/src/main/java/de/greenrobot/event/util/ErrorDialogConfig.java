package de.greenrobot.event.util;

import android.content.res.Resources;
import de.greenrobot.event.EventBus;

/* loaded from: classes11.dex */
public class ErrorDialogConfig {
    int defaultDialogIconId;
    final int defaultErrorMsgId;
    Class<?> defaultEventTypeOnDialogClosed;
    final int defaultTitleId;
    EventBus eventBus;
    boolean logExceptions = true;
    final ExceptionToResourceMapping mapping = new ExceptionToResourceMapping();
    final Resources resources;
    String tagForLoggingExceptions;

    public ErrorDialogConfig(Resources resources, int i2, int i3) {
        this.resources = resources;
        this.defaultTitleId = i2;
        this.defaultErrorMsgId = i3;
    }

    public ErrorDialogConfig addMapping(Class<? extends Throwable> cls, int i2) {
        this.mapping.addMapping(cls, i2);
        return this;
    }

    public void disableExceptionLogging() {
        this.logExceptions = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventBus getEventBus() {
        EventBus eventBus = this.eventBus;
        return eventBus != null ? eventBus : EventBus.getDefault();
    }

    public int getMessageIdForThrowable(Throwable th) {
        Integer mapThrowable = this.mapping.mapThrowable(th);
        if (mapThrowable != null) {
            return mapThrowable.intValue();
        }
        String str = EventBus.TAG;
        String str2 = "No specific message ressource ID found for " + th;
        return this.defaultErrorMsgId;
    }

    public void setDefaultDialogIconId(int i2) {
        this.defaultDialogIconId = i2;
    }

    public void setDefaultEventTypeOnDialogClosed(Class<?> cls) {
        this.defaultEventTypeOnDialogClosed = cls;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void setTagForLoggingExceptions(String str) {
        this.tagForLoggingExceptions = str;
    }
}
