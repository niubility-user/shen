package com.jingdong.common.web.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class IntentUtils {
    public static void jumpDial(Context context, String str) {
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.DIAL");
            intent.setData(Uri.parse(WebView.SCHEME_TEL + str));
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }
}
