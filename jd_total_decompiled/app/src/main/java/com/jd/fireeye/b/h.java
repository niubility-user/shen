package com.jd.fireeye.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class h extends BroadcastReceiver {

    /* renamed from: c  reason: collision with root package name */
    private static h f2611c;
    private boolean a = false;
    private JSONObject b = new JSONObject();

    public static h b() {
        if (f2611c == null) {
            synchronized (h.class) {
                if (f2611c == null) {
                    f2611c = new h();
                }
            }
        }
        return f2611c;
    }

    public JSONObject a() {
        return this.b;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.SCREEN_ON".equals(action)) {
            System.currentTimeMillis();
        }
        if ("android.intent.action.SCREEN_OFF".equals(action)) {
            System.currentTimeMillis();
        }
        if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
            int intExtra = intent.getIntExtra("level", 0);
            int intExtra2 = intent.getIntExtra("status", 0);
            int intExtra3 = intent.getIntExtra("plugged", 0);
            if (this.b == null) {
                this.b = new JSONObject();
            }
            try {
                this.b.put("remainingBatteryLevel", String.valueOf(intExtra));
                this.b.put("batteryStatus", String.valueOf(intExtra2));
                this.b.put("plugged", String.valueOf(intExtra3));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(Context context) {
        try {
            if (this.a || context == null) {
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            context.registerReceiver(this, intentFilter);
            this.a = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
