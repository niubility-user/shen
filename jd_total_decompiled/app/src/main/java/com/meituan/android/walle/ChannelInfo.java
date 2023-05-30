package com.meituan.android.walle;

import java.util.Map;

/* loaded from: classes.dex */
public class ChannelInfo {
    private final String channel;
    private final Map<String, String> extraInfo;

    public ChannelInfo(String str, Map<String, String> map) {
        this.channel = str;
        this.extraInfo = map;
    }

    public String getChannel() {
        return this.channel;
    }

    public Map<String, String> getExtraInfo() {
        return this.extraInfo;
    }
}
