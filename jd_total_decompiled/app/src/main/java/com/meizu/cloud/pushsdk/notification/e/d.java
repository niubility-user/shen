package com.meizu.cloud.pushsdk.notification.e;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MessageV4;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.g.e;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.io.File;

/* loaded from: classes14.dex */
public class d extends c {

    /* loaded from: classes14.dex */
    class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15993g;

        a(d dVar, String str) {
            this.f15993g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (File file : com.meizu.cloud.pushsdk.notification.g.a.d(this.f15993g, String.valueOf(System.currentTimeMillis() - 86400000))) {
                com.meizu.cloud.pushsdk.notification.g.a.b(file.getPath());
                DebugLogger.i("AbstractPushNotification", "Delete file directory " + file.getName() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
    }

    public d(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    @Override // com.meizu.cloud.pushsdk.notification.a
    public void i(Notification notification, MessageV3 messageV3) {
        super.i(notification, messageV3);
        MessageV4 parse = MessageV4.parse(messageV3);
        if (parse.getActVideoSetting() == null) {
            DebugLogger.e("AbstractPushNotification", "only wifi can download act");
        } else if (parse.getActVideoSetting().isWifiDisplay() && !com.meizu.cloud.pushsdk.util.a.b(this.a)) {
            DebugLogger.e("AbstractPushNotification", "only wifi can download act");
        } else {
            String str = MzSystemUtils.getDocumentsPath(this.a) + "/pushSdkAct/" + messageV3.getUploadDataPackageName();
            String valueOf = String.valueOf(System.currentTimeMillis());
            String actUrl = parse.getActVideoSetting().getActUrl();
            if (!TextUtils.isEmpty(actUrl) && com.meizu.cloud.pushsdk.e.a.a(actUrl, str, valueOf).a().h().f()) {
                DebugLogger.i("AbstractPushNotification", "down load " + actUrl + " success");
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                String str2 = File.separator;
                sb.append(str2);
                sb.append("ACT-");
                sb.append(valueOf);
                String sb2 = sb.toString();
                boolean c2 = new e(str + str2 + valueOf, sb2).c();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("zip file ");
                sb3.append(c2);
                DebugLogger.i("AbstractPushNotification", sb3.toString());
                if (c2) {
                    Bundle bundle = new Bundle();
                    bundle.putString("path", sb2);
                    Bundle bundle2 = new Bundle();
                    bundle2.putBundle("big", bundle);
                    if (MinSdkChecker.isSupportVideoNotification()) {
                        notification.extras.putBundle("flyme.active", bundle2);
                    }
                }
            }
            com.meizu.cloud.pushsdk.f.c.h.b.d(new a(this, str));
        }
    }

    @Override // com.meizu.cloud.pushsdk.notification.a
    protected void q(Notification.Builder builder, MessageV3 messageV3) {
        if (MinSdkChecker.isSupportNotificationBuild()) {
            Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
            bigTextStyle.setBigContentTitle(messageV3.getTitle());
            bigTextStyle.bigText(messageV3.getNotificationStyle().getExpandableText());
            builder.setStyle(bigTextStyle);
        }
    }
}
