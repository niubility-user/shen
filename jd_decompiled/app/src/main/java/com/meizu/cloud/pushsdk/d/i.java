package com.meizu.cloud.pushsdk.d;

import com.jingdong.manto.sdk.api.IMantoServerRequester;

/* loaded from: classes14.dex */
public class i {
    public static String a(String str) {
        com.meizu.cloud.pushsdk.d.l.d b = com.meizu.cloud.pushsdk.d.l.a.b("android.os.SystemProperties").d(IMantoServerRequester.GET, String.class).b(str);
        if (b.a) {
            return (String) b.b;
        }
        return null;
    }
}
