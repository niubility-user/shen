package com.jd.lib.babel.servicekit.iservice;

import java.util.Map;

/* loaded from: classes13.dex */
public interface BabelLoginCallback {
    public static final String KEY_COOKIES = "cookies";

    void onError();

    void onSuccess(Map<String, String> map);
}
