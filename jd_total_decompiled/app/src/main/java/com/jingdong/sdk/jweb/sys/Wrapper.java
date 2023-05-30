package com.jingdong.sdk.jweb.sys;

import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.jingdong.sdk.jweb.JWebResourceRequest;
import com.jingdong.sdk.jweb.JWebResourceResponse;
import java.util.Map;

/* loaded from: classes7.dex */
public class Wrapper {

    /* loaded from: classes7.dex */
    static class a implements JWebResourceRequest {
        WebResourceRequest a;

        public a(WebResourceRequest webResourceRequest) {
            this.a = webResourceRequest;
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public String getMethod() {
            return this.a.getMethod();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public Map<String, String> getRequestHeaders() {
            return this.a.getRequestHeaders();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public Uri getUrl() {
            return this.a.getUrl();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public boolean hasGesture() {
            return this.a.hasGesture();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public boolean isForMainFrame() {
            return this.a.isForMainFrame();
        }

        @Override // com.jingdong.sdk.jweb.JWebResourceRequest
        public boolean isRedirect() {
            return this.a.isRedirect();
        }
    }

    public static WebResourceResponse convert(JWebResourceResponse jWebResourceResponse) {
        if (jWebResourceResponse == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 21 ? new WebResourceResponse(jWebResourceResponse.getMimeType(), jWebResourceResponse.getEncoding(), jWebResourceResponse.getStatusCode(), jWebResourceResponse.getReasonPhrase(), jWebResourceResponse.getResponseHeaders(), jWebResourceResponse.getData()) : new WebResourceResponse(jWebResourceResponse.getMimeType(), jWebResourceResponse.getEncoding(), jWebResourceResponse.getData());
    }
}
