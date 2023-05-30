package com.jingdong.sdk.jweb;

import android.net.Uri;
import java.util.Map;

/* loaded from: classes7.dex */
public interface JWebResourceRequest {
    String getMethod();

    Map<String, String> getRequestHeaders();

    Uri getUrl();

    boolean hasGesture();

    boolean isForMainFrame();

    boolean isRedirect();
}
