package com.jingdong.app.mall.safemode;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

/* loaded from: classes4.dex */
public class a {
    public static void a(Context context, Intent intent, String str) {
        if (context == null || intent == null) {
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Toast.makeText(context, str, 0).show();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
