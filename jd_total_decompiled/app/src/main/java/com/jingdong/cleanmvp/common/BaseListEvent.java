package com.jingdong.cleanmvp.common;

/* loaded from: classes4.dex */
public class BaseListEvent extends BaseEvent {
    public static final String SHOW_DATA = "SHOW_DATA";
    public static final String SHOW_NET_ERROR = "SHOW_NET_ERROR";

    public BaseListEvent() {
    }

    public BaseListEvent(String str) {
        super(str);
    }
}
