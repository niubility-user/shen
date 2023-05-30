package com.meizu.cloud.pushsdk.handler.f.b;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSettingEx;

/* loaded from: classes14.dex */
public class a {
    private Context a;
    private com.meizu.cloud.pushsdk.d.k.a b;

    /* renamed from: c  reason: collision with root package name */
    private int f15983c;
    private Notification d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.meizu.cloud.pushsdk.handler.f.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class RunnableC0768a implements Runnable {
        RunnableC0768a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DebugLogger.d("AdNotification", "ad priority valid time out");
            a.this.a();
        }
    }

    public a(Context context) {
        this.a = context;
    }

    private void c(int i2, Notification notification) {
        this.f15983c = i2;
        this.d = notification;
    }

    private void f() {
        this.f15983c = 0;
        this.d = null;
        com.meizu.cloud.pushsdk.d.k.a aVar = this.b;
        if (aVar != null) {
            try {
                try {
                    aVar.b();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                this.b = null;
            }
        }
    }

    private void g(int i2) {
        if (i2 <= 0) {
            return;
        }
        com.meizu.cloud.pushsdk.d.k.a aVar = this.b;
        if (aVar != null) {
            try {
                try {
                    aVar.b();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                this.b = null;
            }
        }
        com.meizu.cloud.pushsdk.d.k.a aVar2 = new com.meizu.cloud.pushsdk.d.k.a(this.a, new RunnableC0768a(), i2 * 60 * 1000);
        this.b = aVar2;
        aVar2.g();
    }

    public void a() {
        if (this.f15983c <= 0 || this.d == null) {
            return;
        }
        try {
            ((NotificationManager) this.a.getSystemService(RemoteMessageConst.NOTIFICATION)).notify(this.f15983c, this.d);
            DebugLogger.d("AdNotification", "again show old ad notification, notifyId:" + this.f15983c);
        } catch (Exception e2) {
            e2.printStackTrace();
            DebugLogger.e("AdNotification", "again show old ad notification error:" + e2.getMessage());
        }
        f();
    }

    public void b(int i2) {
        int i3;
        if (i2 <= 0 || (i3 = this.f15983c) <= 0 || i2 != i3) {
            return;
        }
        f();
        DebugLogger.d("AdNotification", "clean ad notification, notifyId:" + i2);
    }

    public void d(int i2, Notification notification, int i3) {
        if (i2 <= 0 || notification == null) {
            return;
        }
        c(i2, notification);
        g(i3);
        DebugLogger.d("AdNotification", "save ad notification, notifyId:" + i2);
    }

    public void e(MessageV3 messageV3) {
        AdvanceSetting advanceSetting = messageV3.getAdvanceSetting();
        if (advanceSetting != null) {
            advanceSetting.getNotifyType().setSound(false);
            advanceSetting.getNotifyType().setLights(false);
            advanceSetting.getNotifyType().setVibrate(false);
        }
        AdvanceSettingEx advanceSettingEx = messageV3.getAdvanceSettingEx();
        if (advanceSettingEx != null) {
            advanceSettingEx.setSoundTitle(null);
            if (Build.VERSION.SDK_INT < 29 || advanceSetting == null || !advanceSetting.isHeadUpNotification()) {
                advanceSettingEx.setPriorityDisplay(0);
            } else {
                advanceSettingEx.setPriorityDisplay(1);
            }
        }
    }
}
