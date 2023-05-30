package com.meizu.cloud.pushsdk.handler.f.l;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSettingEx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    private Context a;
    private List<Intent> b;

    /* renamed from: c  reason: collision with root package name */
    private BroadcastReceiver f15989c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.meizu.cloud.pushsdk.handler.f.l.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class RunnableC0770a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Intent f15990g;

        RunnableC0770a(Intent intent) {
            this.f15990g = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(1000L);
                DebugLogger.d("BrightNotification", "start bright notification service " + this.f15990g);
                a.this.a.startService(this.f15990g);
            } catch (Exception e2) {
                DebugLogger.e("BrightNotification", "send bright notification error " + e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b extends BroadcastReceiver {
        b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equalsIgnoreCase(intent.getAction())) {
                a.this.e();
            }
        }
    }

    public a(Context context) {
        this.a = context.getApplicationContext();
        b();
    }

    private void b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        if (this.f15989c == null) {
            this.f15989c = new b();
        }
        this.a.registerReceiver(this.f15989c, intentFilter);
    }

    private void c(Intent intent) {
        MessageV3 messageV3 = (MessageV3) intent.getParcelableExtra(PushConstants.EXTRA_APP_PUSH_BRIGHT_NOTIFICATION_MESSAGE);
        if (messageV3 == null) {
            return;
        }
        AdvanceSetting advanceSetting = messageV3.getAdvanceSetting();
        AdvanceSettingEx advanceSettingEx = messageV3.getAdvanceSettingEx();
        if (advanceSetting == null || advanceSettingEx == null) {
            return;
        }
        advanceSettingEx.setSoundTitle(null);
        advanceSetting.getNotifyType().setSound(false);
        advanceSetting.getNotifyType().setLights(false);
        advanceSetting.getNotifyType().setVibrate(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        List<Intent> list = this.b;
        if (list == null || list.size() == 0) {
            return;
        }
        int size = this.b.size();
        int i2 = 0;
        Iterator<Intent> it = this.b.iterator();
        while (it.hasNext()) {
            Intent next = it.next();
            if (i2 != size - 1) {
                c(next);
            }
            f(next);
            it.remove();
            i2++;
        }
    }

    private void f(Intent intent) {
        com.meizu.cloud.pushsdk.d.m.c.a().execute(new RunnableC0770a(intent));
    }

    public void d(Intent intent, String str) {
        if (intent == null || TextUtils.isEmpty(str)) {
            return;
        }
        List<Intent> list = this.b;
        if (list != null) {
            Iterator<Intent> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Intent next = it.next();
                MessageV3 messageV3 = (MessageV3) next.getParcelableExtra(PushConstants.EXTRA_APP_PUSH_BRIGHT_NOTIFICATION_MESSAGE);
                if (messageV3 != null && messageV3.getUploadDataPackageName() != null && str.equalsIgnoreCase(messageV3.getUploadDataPackageName())) {
                    this.b.remove(next);
                    break;
                }
            }
        } else {
            this.b = new ArrayList();
        }
        this.b.add(intent);
        DebugLogger.d("BrightNotification", "add bright notification intent, intent list: " + this.b);
    }
}
