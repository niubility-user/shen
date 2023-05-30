package com.meizu.cloud.pushsdk.notification.f;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;

/* loaded from: classes14.dex */
public class a extends c {
    public a(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    private void z(RemoteViews remoteViews, MessageV3 messageV3) {
        Bitmap e2;
        if (messageV3.getNotificationStyle() == null || k()) {
            return;
        }
        if (TextUtils.isEmpty(messageV3.getNotificationStyle().getExpandableImageUrl()) || (e2 = e(messageV3.getNotificationStyle().getExpandableImageUrl())) == null) {
            remoteViews.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.b(this.a), 8);
            return;
        }
        remoteViews.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.b(this.a), 0);
        remoteViews.setImageViewBitmap(com.meizu.cloud.pushsdk.notification.g.c.b(this.a), e2);
    }

    @Override // com.meizu.cloud.pushsdk.notification.a
    protected void j(Notification notification, MessageV3 messageV3, PendingIntent pendingIntent) {
        if (MinSdkChecker.isSupportNotificationBuild()) {
            RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), com.meizu.cloud.pushsdk.notification.g.c.g(this.a));
            remoteViews.setTextViewText(com.meizu.cloud.pushsdk.notification.g.c.f(this.a), messageV3.getTitle());
            remoteViews.setTextViewText(com.meizu.cloud.pushsdk.notification.g.c.c(this.a), messageV3.getContent());
            remoteViews.setLong(com.meizu.cloud.pushsdk.notification.g.c.d(this.a), "setTime", System.currentTimeMillis());
            y(remoteViews, messageV3);
            z(remoteViews, messageV3);
            notification.bigContentView = remoteViews;
        }
    }
}
