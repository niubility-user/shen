package com.jingdong.sdk.platform.base;

/* loaded from: classes9.dex */
public class PlatformEvent implements com.jingdong.sdk.aac.base.UnProguard {
    public Object data;
    public String key;

    public PlatformEvent() {
    }

    public PlatformEvent(String str, Object obj) {
        this.key = str;
        this.data = obj;
    }
}
