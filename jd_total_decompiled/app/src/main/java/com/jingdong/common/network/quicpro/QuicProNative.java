package com.jingdong.common.network.quicpro;

import java.util.Map;

/* loaded from: classes5.dex */
public abstract class QuicProNative {
    public static QuicProNative quicProNativeImpl;

    public static QuicProNative getQuicProNative() {
        return quicProNativeImpl;
    }

    public abstract int HttpGet(String str, String str2, String str3, Map<String, String> map, QPEventListener qPEventListener, int i2, int i3);

    public abstract int HttpPost(String str, String str2, String str3, Map<String, String> map, String str4, QPEventListener qPEventListener, int i2, int i3);

    public abstract int HttpUrlGet(String str, String str2, Map<String, String> map, QPEventListener qPEventListener, int i2, int i3);

    public abstract int HttpUrlPost(String str, String str2, Map<String, String> map, String str3, QPEventListener qPEventListener, int i2, int i3);

    public abstract int SetLogLevel(int i2);
}
