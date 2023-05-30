package com.jd.libs.hybrid.preload.okhttp;

import android.text.TextUtils;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;

/* loaded from: classes16.dex */
public class OKHttpResponse {
    private String a;
    private HttpResponse b;

    public OKHttpResponse(String str, HttpResponse httpResponse) {
        this.a = "";
        this.b = httpResponse;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.a = str;
    }

    public String getString() {
        return this.a;
    }

    public HttpResponse getmHttpResponse() {
        return this.b;
    }

    public void setString(String str) {
        this.a = str;
    }

    public void setmHttpResponse(HttpResponse httpResponse) {
        this.b = httpResponse;
    }
}
