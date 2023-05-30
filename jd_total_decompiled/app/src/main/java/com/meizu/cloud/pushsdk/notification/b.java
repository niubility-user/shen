package com.meizu.cloud.pushsdk.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.model.styleenum.InnerStyleLayout;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;

/* loaded from: classes14.dex */
public class b extends com.meizu.cloud.pushsdk.notification.f.c {
    public b(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    @Override // com.meizu.cloud.pushsdk.notification.a
    protected void j(Notification notification, MessageV3 messageV3, PendingIntent pendingIntent) {
        if (MinSdkChecker.isSupportNotificationBuild()) {
            Bitmap e2 = e(messageV3.getNotificationStyle().getBannerImageUrl());
            if (k() || e2 == null) {
                return;
            }
            RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), com.meizu.cloud.pushsdk.notification.g.c.l(this.a));
            remoteViews.setImageViewBitmap(com.meizu.cloud.pushsdk.notification.g.c.i(this.a), e2);
            remoteViews.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.j(this.a), 8);
            remoteViews.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.i(this.a), 0);
            notification.contentView = remoteViews;
            if (messageV3.getNotificationStyle().getInnerStyle() == InnerStyleLayout.EXPANDABLE_PIC.getCode()) {
                Bitmap e3 = e(messageV3.getNotificationStyle().getExpandableImageUrl());
                if (k() || e3 == null) {
                    return;
                }
                RemoteViews remoteViews2 = new RemoteViews(this.a.getPackageName(), com.meizu.cloud.pushsdk.notification.g.c.l(this.a));
                remoteViews2.setImageViewBitmap(com.meizu.cloud.pushsdk.notification.g.c.j(this.a), e3);
                remoteViews2.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.j(this.a), 0);
                remoteViews2.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.i(this.a), 8);
                notification.bigContentView = remoteViews2;
                if (messageV3.getAdvertisementOption() == null || TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdInstallPackage())) {
                    return;
                }
                remoteViews2.setViewVisibility(com.meizu.cloud.pushsdk.notification.g.c.k(this.a), 0);
                remoteViews2.setOnClickPendingIntent(com.meizu.cloud.pushsdk.notification.g.c.k(this.a), pendingIntent);
            }
        }
    }
}
