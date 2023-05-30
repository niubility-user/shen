package com.jd.lib.babel.servicekit.iservice;

import java.util.Map;

/* loaded from: classes13.dex */
public interface BabelCartCallback {
    void onError();

    void onFull();

    void onSuccess(Map<String, String> map);
}
