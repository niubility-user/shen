package com.jingdong.cleanmvp.common;

import android.os.Bundle;

/* loaded from: classes.dex */
public class BaseEvent {
    protected Bundle bundle;
    protected String eventType;
    protected String message;

    public BaseEvent() {
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.eventType;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public BaseEvent(String str) {
        this.eventType = str;
    }

    public BaseEvent(String str, String str2) {
        this.eventType = str;
        this.message = str2;
    }
}
