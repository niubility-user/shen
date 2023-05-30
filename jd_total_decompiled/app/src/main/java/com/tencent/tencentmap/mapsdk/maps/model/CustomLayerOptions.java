package com.tencent.tencentmap.mapsdk.maps.model;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public class CustomLayerOptions {
    private String mLayerId;
    private String mLayerVersion;

    public String getLayerId() {
        return this.mLayerId;
    }

    public CustomLayerOptions layerId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mLayerId = str;
        }
        return this;
    }
}
