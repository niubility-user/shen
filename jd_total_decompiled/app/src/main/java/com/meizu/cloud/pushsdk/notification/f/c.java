package com.meizu.cloud.pushsdk.notification.f;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;

/* loaded from: classes14.dex */
public class c extends com.meizu.cloud.pushsdk.notification.a {
    public c(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.notification.a
    public void i(Notification notification, MessageV3 messageV3) {
        if (MinSdkChecker.isSupportNotificationBuild()) {
            RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), com.meizu.cloud.pushsdk.notification.g.c.g(this.a));
            remoteViews.setTextViewText(com.meizu.cloud.pushsdk.notification.g.c.f(this.a), messageV3.getTitle());
            remoteViews.setTextViewText(com.meizu.cloud.pushsdk.notification.g.c.c(this.a), messageV3.getContent());
            remoteViews.setLong(com.meizu.cloud.pushsdk.notification.g.c.d(this.a), "setTime", System.currentTimeMillis());
            y(remoteViews, messageV3);
            remoteViews.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.b(this.a), 8);
            remoteViews.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.a(this.a), 8);
            notification.contentView = remoteViews;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void y(RemoteViews remoteViews, MessageV3 messageV3) {
        Bitmap e2;
        if (messageV3.getAppIconSetting() == null || k() || messageV3.getAppIconSetting().isDefaultLargeIcon() || (e2 = e(messageV3.getAppIconSetting().getLargeIconUrl())) == null) {
            remoteViews.setImageViewBitmap(com.meizu.cloud.pushsdk.notification.g.c.e(this.a), d(this.a, messageV3.getUploadDataPackageName()));
        } else {
            remoteViews.setImageViewBitmap(com.meizu.cloud.pushsdk.notification.g.c.e(this.a), e2);
        }
    }
}
