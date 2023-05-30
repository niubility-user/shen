package com.jingdong.jdsdk.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class a {
    private static String a = "";

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        Bundle a2 = com.jingdong.sdk.utils.a.a(context);
        String string = a2 != null ? a2.getString("client.region") : null;
        if (!TextUtils.isEmpty(string)) {
            a = string;
        }
        return string;
    }
}
