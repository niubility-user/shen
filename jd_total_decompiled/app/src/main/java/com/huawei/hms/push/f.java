package com.huawei.hms.push;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes12.dex */
public class f {
    private static int a(Context context) {
        int i2 = context.getApplicationInfo().icon;
        if (i2 == 0) {
            int identifier = context.getResources().getIdentifier("btn_star_big_on", "drawable", "android");
            HMSLog.d("PushSelfShowLog", "icon is btn_star_big_on ");
            if (identifier == 0) {
                HMSLog.d("PushSelfShowLog", "icon is sym_def_app_icon ");
                return 17301651;
            }
            return identifier;
        }
        return i2;
    }

    private static int b(Context context, m mVar) {
        int i2 = 0;
        if (context != null && mVar != null) {
            if (!TextUtils.isEmpty(mVar.m())) {
                String[] split = mVar.m().split("/");
                if (split.length == 3) {
                    i2 = q.a(context, split[1], split[2]);
                }
            }
            if (i2 == 0) {
                i2 = q.a(context, "com.huawei.messaging.default_notification_icon");
            }
            return i2 != 0 ? i2 : a(context);
        }
        HMSLog.i("PushSelfShowLog", "enter getSmallIconId, context or msg is null");
        return 0;
    }

    public static Bitmap a(Context context, m mVar) {
        if (context == null || mVar == null) {
            return null;
        }
        try {
            if (HwBuildEx.VERSION.EMUI_SDK_INT >= 11) {
                HMSLog.i("PushSelfShowLog", "huawei phone, and emui5.0, need not show large icon.");
                return null;
            } else if ("com.huawei.android.pushagent".equals(mVar.k())) {
                return null;
            } else {
                HMSLog.i("PushSelfShowLog", "get left bitmap from " + mVar.k());
                return ((BitmapDrawable) context.getPackageManager().getApplicationIcon(mVar.k())).getBitmap();
            }
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.e("PushSelfShowLog", "build left icon occur NameNotFoundException.");
            return null;
        } catch (Exception unused2) {
            HMSLog.e("PushSelfShowLog", "build left icon occur Exception.");
            return null;
        }
    }

    @TargetApi(23)
    public static void a(Context context, Notification.Builder builder, m mVar) {
        if (context != null && builder != null && mVar != null) {
            builder.setSmallIcon(b(context, mVar));
        } else {
            HMSLog.e("PushSelfShowLog", "msg is null");
        }
    }
}
