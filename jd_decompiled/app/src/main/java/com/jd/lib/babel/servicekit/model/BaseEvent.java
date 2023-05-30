package com.jd.lib.babel.servicekit.model;

import android.os.Bundle;

/* loaded from: classes13.dex */
public class BaseEvent {
    protected Bundle bundle;
    protected String eventType;
    protected String message;

    public BaseEvent(String str) {
        this.eventType = str;
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

    public BaseEvent(String str, String str2) {
        this.eventType = str;
        this.message = str2;
    }

    public BaseEvent(String str, String str2, Bundle bundle) {
        this.eventType = str;
        this.message = str2;
        this.bundle = bundle;
    }
}
