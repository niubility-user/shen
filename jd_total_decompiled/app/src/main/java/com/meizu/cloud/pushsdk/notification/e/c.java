package com.meizu.cloud.pushsdk.notification.e;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;

/* loaded from: classes14.dex */
public class c extends com.meizu.cloud.pushsdk.notification.a {
    public c(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    @Override // com.meizu.cloud.pushsdk.notification.a
    protected void o(Notification.Builder builder, MessageV3 messageV3) {
        AppIconSetting appIconSetting;
        Bitmap d;
        String str;
        if ((!MzSystemUtils.isInternational() || MzSystemUtils.isMeizuAndFlyme()) && (appIconSetting = messageV3.getAppIconSetting()) != null) {
            if (appIconSetting.isDefaultLargeIcon()) {
                PushNotificationBuilder pushNotificationBuilder = this.b;
                if (pushNotificationBuilder == null || pushNotificationBuilder.getLargeIcon() == 0) {
                    PushNotificationBuilder pushNotificationBuilder2 = this.b;
                    if (pushNotificationBuilder2 == null || pushNotificationBuilder2.getAppLargeIcon() == null) {
                        d = d(this.a, messageV3.getUploadDataPackageName());
                        str = "set largeIcon by package default large icon";
                    } else {
                        d = this.b.getAppLargeIcon();
                        str = "set largeIcon by bitmap provided by user setting";
                    }
                } else {
                    d = BitmapFactory.decodeResource(this.a.getResources(), this.b.getLargeIcon());
                    str = "set largeIcon by resource id";
                }
                DebugLogger.i("AbstractPushNotification", str);
            } else if (Thread.currentThread() == this.a.getMainLooper().getThread()) {
                return;
            } else {
                Bitmap e2 = e(appIconSetting.getLargeIconUrl());
                if (e2 != null) {
                    DebugLogger.i("AbstractPushNotification", "On other Thread down load largeIcon image success");
                    builder.setLargeIcon(e2);
                    return;
                }
                d = d(this.a, messageV3.getUploadDataPackageName());
            }
            builder.setLargeIcon(d);
        }
    }
}
