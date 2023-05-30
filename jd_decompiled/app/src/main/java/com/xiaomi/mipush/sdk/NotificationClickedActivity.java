package com.xiaomi.mipush.sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.xiaomi.push.l9;

/* loaded from: classes11.dex */
public final class NotificationClickedActivity extends Activity {
    private BroadcastReceiver a;

    /* renamed from: a  reason: collision with other field name */
    private Handler f103a;

    private void a(Intent intent) {
        String str;
        if (intent != null) {
            try {
                Intent intent2 = (Intent) intent.getParcelableExtra("mipush_serviceIntent");
                if (intent2 != null) {
                    intent2.setComponent(new ComponentName(getPackageName(), "com.xiaomi.mipush.sdk.PushMessageHandler"));
                    intent2.putExtra("is_clicked_activity_call", true);
                    g.j.a.a.a.c.p("PushClickedActivity", "clicked activity start service.");
                    startService(intent2);
                }
                str = "clicked activity start service, newIntent is null";
            } catch (Exception e2) {
                g.j.a.a.a.c.s(e2);
                return;
            }
        } else {
            str = "clicked activity start service, missing intent";
        }
        g.j.a.a.a.c.C("PushClickedActivity", str);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = 1;
        attributes.width = 1;
        attributes.gravity = 8388659;
        window.setAttributes(attributes);
        Handler handler = new Handler();
        this.f103a = handler;
        handler.postDelayed(new u(this), 3000L);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_clicked_activity_finish");
        v vVar = new v(this);
        this.a = vVar;
        try {
            l9.c(this, vVar, intentFilter, q0.a(this), null, 4);
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.f103a.removeCallbacksAndMessages(null);
        try {
            unregisterReceiver(this.a);
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        a(getIntent());
    }
}
