package com.jd.stat.common;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.jd.stat.security.jma.JMA;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlinx.coroutines.DebugKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class MonitorService extends BroadcastReceiver {
    private static MonitorService b;

    /* renamed from: h  reason: collision with root package name */
    private static int f6973h;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private long f6975e;
    private boolean a = false;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f6974c = new JSONObject();

    /* renamed from: f  reason: collision with root package name */
    private final String f6976f = "ap_first";

    /* renamed from: g  reason: collision with root package name */
    private String f6977g = com.jingdong.jdsdk.a.a.a;

    public static MonitorService f() {
        if (b == null) {
            synchronized (MonitorService.class) {
                if (b == null) {
                    MonitorService monitorService = new MonitorService();
                    b = monitorService;
                    if (monitorService.g()) {
                        MonitorService monitorService2 = b;
                        com.jd.stat.common.b.f.a("ap_rec", monitorService2.a(monitorService2.h(), true));
                    }
                }
            }
        }
        return b;
    }

    private boolean g() {
        boolean b2 = com.jd.stat.common.b.f.b("ap_first", true);
        if (b2) {
            com.jd.stat.common.b.f.a("ap_first", false);
        }
        return b2;
    }

    private boolean h() {
        try {
            return Build.VERSION.SDK_INT < 17 ? Settings.System.getInt(com.jd.stat.security.c.a.getContentResolver(), "airplane_mode_on", 0) != 0 : Settings.Global.getInt(com.jd.stat.security.c.a.getContentResolver(), "airplane_mode_on", 0) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public JSONObject a() {
        try {
            BatteryManager batteryManager = (BatteryManager) com.jd.stat.security.c.a.getSystemService("batterymanager");
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    this.f6974c.put("remainingBatteryLevel", String.valueOf(batteryManager.getIntProperty(4)));
                    this.f6974c.put("batteryStatus", String.valueOf(batteryManager.getIntProperty(6)));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("JDMob.MonitorService", th);
        }
        return this.f6974c;
    }

    public String b() {
        String str = this.d;
        return str == null ? "" : str;
    }

    public String c() {
        return TextUtils.isEmpty(this.f6977g) ? com.jingdong.jdsdk.a.a.a : this.f6977g;
    }

    public String d() {
        return this.f6975e == 0 ? "" : String.valueOf(System.currentTimeMillis() - this.f6975e);
    }

    public String e() {
        String b2 = com.jd.stat.common.b.f.b("ap_rec", "");
        if (b2.length() > 3) {
            b2 = b2.substring(0, b2.length() - 3);
        }
        com.jd.stat.common.b.f.a("ap_rec", "");
        return b2;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                com.jd.stat.security.c.a(context);
                com.jd.stat.common.b.f.a("ap_rec", a(h(), g()));
                b(context);
            }
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                this.f6975e = System.currentTimeMillis();
                b(context);
            }
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                this.f6975e = System.currentTimeMillis();
                b(context);
            }
            if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                int intExtra = intent.getIntExtra("level", 0);
                if (f6973h == 0) {
                    f6973h = intExtra;
                }
                if (intExtra - f6973h != 0) {
                    b(context);
                    f6973h = intExtra;
                }
                int intExtra2 = intent.getIntExtra("status", 0);
                boolean booleanExtra = intent.getBooleanExtra("present", false);
                int intExtra3 = intent.getIntExtra("voltage", 0);
                int intExtra4 = intent.getIntExtra("temperature", 0);
                this.d = intent.getStringExtra("technology");
                int intExtra5 = intent.getIntExtra("plugged", 0);
                if (intExtra5 == 0) {
                    this.f6977g = "0";
                } else if (intExtra5 == 1) {
                    this.f6977g = "1";
                } else if (intExtra5 == 2) {
                    this.f6977g = "2";
                } else if (intExtra5 == 4) {
                    this.f6977g = "4";
                }
                int intExtra6 = intent.getIntExtra("health", 0);
                if (this.f6974c == null) {
                    this.f6974c = new JSONObject();
                }
                try {
                    this.f6974c.put("remainingBatteryLevel", String.valueOf(intExtra));
                    this.f6974c.put("batteryStatus", String.valueOf(intExtra2));
                    this.f6974c.put("present", booleanExtra);
                    this.f6974c.put("voltage", String.valueOf(intExtra3));
                    this.f6974c.put("temperature", String.valueOf(intExtra4));
                    this.f6974c.put("plugged", String.valueOf(intExtra5));
                    this.f6974c.put("health", String.valueOf(intExtra6));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("JDMob.MonitorService", th);
        }
    }

    private void b(Context context) {
        if (context == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", "AutoReport");
            JMA.report(context, jSONObject);
        } catch (Exception unused) {
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
            if (Build.VERSION.SDK_INT >= 26) {
                intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            }
            context.registerReceiver(this, intentFilter);
            this.a = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    private String a(boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append(com.jd.stat.common.b.f.b("ap_rec", ""));
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        sb.append(";");
        sb.append(z2 ? "start" : "change");
        sb.append(";");
        sb.append(z ? "on" : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        sb.append("###");
        return sb.toString();
    }
}
