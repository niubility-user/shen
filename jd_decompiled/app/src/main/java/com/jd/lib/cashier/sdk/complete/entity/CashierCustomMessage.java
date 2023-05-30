package com.jd.lib.cashier.sdk.complete.entity;

import java.util.Map;

/* loaded from: classes14.dex */
public class CashierCustomMessage {
    public String businessChannel;
    public Map<String, String> message;

    /* loaded from: classes14.dex */
    public interface KEY {
        public static final String CHANNEL_CLIP_BOARD = "clipboard";
        public static final String CHANNEL_JS_ON_ERROR = "jsOnErrorTrigger";
        public static final String MAP_KEY_COPY = "copyText";
    }
}
