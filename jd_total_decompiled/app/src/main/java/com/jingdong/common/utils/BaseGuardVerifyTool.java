package com.jingdong.common.utils;

import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes6.dex */
public class BaseGuardVerifyTool {
    public boolean checkAndHandleEvent(HttpResponse httpResponse, HttpSetting httpSetting, boolean z) {
        return false;
    }

    public boolean shouldInterceptRequest(HttpResponse httpResponse) {
        return false;
    }
}
