package com.jingdong.common.navutils;

import android.net.Uri;
import java.util.Map;

/* loaded from: classes5.dex */
public class NavUri {
    private Uri.Builder uriBuilder = new Uri.Builder();

    public NavUri appendQueryParameter(String str, String str2) {
        this.uriBuilder.appendQueryParameter(str, str2);
        return this;
    }

    public NavUri appendQueryParameters(Map<String, String> map) {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            return this;
        }
        throw new IllegalArgumentException("paramsMap is null!!");
    }

    public Uri build() {
        return this.uriBuilder.build();
    }

    public NavUri host(String str) {
        this.uriBuilder.authority(str);
        return this;
    }

    public NavUri path(String str) {
        this.uriBuilder.path(str);
        return this;
    }

    public NavUri scheme(String str) {
        this.uriBuilder.scheme(str);
        return this;
    }
}
