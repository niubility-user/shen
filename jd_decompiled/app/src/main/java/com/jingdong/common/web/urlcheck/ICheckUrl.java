package com.jingdong.common.web.urlcheck;

import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public interface ICheckUrl {
    boolean checkReturn();

    boolean checkUrl(WebView webView, String str);

    String getKey();
}
