package com.jingdong.app.mall.bundle.styleinfoview.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes3.dex */
public class OpenAppUtils {
    public static void openAppForInner(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(context);
    }

    public static void openWithContext(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }
}
