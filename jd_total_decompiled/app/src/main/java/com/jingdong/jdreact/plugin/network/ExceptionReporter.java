package com.jingdong.jdreact.plugin.network;

import com.jingdong.jdreact.plugin.network.ApiUrl;

/* loaded from: classes13.dex */
public class ExceptionReporter {
    public static void reportToBugly(String str, ApiUrl.RequestCallback requestCallback) {
        new ApiUrl().post().functionId("newcrash").body(str).request(requestCallback);
    }
}
