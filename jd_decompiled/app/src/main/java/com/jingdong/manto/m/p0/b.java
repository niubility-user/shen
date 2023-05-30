package com.jingdong.manto.m.p0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes15.dex */
public class b {

    /* renamed from: j  reason: collision with root package name */
    private static b f13489j;
    private volatile boolean a;
    private volatile boolean b;

    /* renamed from: c  reason: collision with root package name */
    private WeakReference<com.jingdong.manto.m.p0.a> f13490c;
    private BroadcastReceiver d;

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f13491e;

    /* renamed from: f  reason: collision with root package name */
    private List<String> f13492f;

    /* renamed from: g  reason: collision with root package name */
    private List<String> f13493g;

    /* renamed from: h  reason: collision with root package name */
    private PhoneStateListener f13494h;

    /* renamed from: i  reason: collision with root package name */
    private volatile boolean f13495i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (b.this.f13492f.contains(action)) {
                b.this.f13491e = true;
                b.this.d();
            } else if (b.this.f13493g.contains(action) && b.this.f13491e) {
                b.this.f13491e = false;
                b.this.e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.p0.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class C0589b extends PhoneStateListener {
        C0589b() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i2, String str) {
            super.onCallStateChanged(i2, str);
            if (i2 != 0) {
                if (i2 != 1) {
                    return;
                }
                b.this.f13495i = true;
                b.this.d();
            } else if (b.this.f13495i) {
                b.this.f13495i = false;
                b.this.e();
            }
        }
    }

    private b() {
        ArrayList arrayList = new ArrayList();
        this.f13492f = arrayList;
        arrayList.add("com.android.deskclock.ALARM_ALERT");
        this.f13492f.add("com.nubia.deskclock.ALARM_ALERT");
        this.f13492f.add("com.android.alarmclock.ALARM_ALERT");
        this.f13492f.add("com.lge.clock.alarmclock.ALARM_ALERT");
        this.f13492f.add("com.samsung.sec.android.clockpackage.alarm.ALARM_ALERT");
        this.f13492f.add("com.sonyericsson.alarm.ALARM_ALERT");
        this.f13492f.add("com.htc.android.worldclock.ALARM_ALERT");
        this.f13492f.add("com.htc.worldclock.ALARM_ALERT");
        this.f13492f.add("com.lenovomobile.deskclock.ALARM_ALERT");
        this.f13492f.add("com.cn.google.AlertClock.ALARM_ALERT");
        this.f13492f.add("com.htc.android.worldclock.intent.action.ALARM_ALERT");
        this.f13492f.add("com.lenovo.deskclock.ALARM_ALERT");
        this.f13492f.add("com.oppo.alarmclock.alarmclock.ALARM_ALERT");
        this.f13492f.add("com.zdworks.android.zdclock.ACTION_ALARM_ALERT");
        ArrayList arrayList2 = new ArrayList();
        this.f13493g = arrayList2;
        arrayList2.add("com.android.deskclock.ALARM_DONE");
        this.f13493g.add("com.android.deskclock.ALARM_SNOOZE");
        this.f13493g.add("com.android.deskclock.ALARM_DISMISS");
        this.f13493g.add("com.nubia.deskclock.ALARM_DONE");
        this.f13493g.add("com.android.alarmclock.ALARM_DONE");
        this.f13493g.add("com.lge.clock.alarmclock.ALARM_DONE");
        this.f13493g.add("com.samsung.sec.android.clockpackage.alarm.ALARM_DONE");
        this.f13493g.add("com.sonyericsson.alarm.ALARM_DONE");
        this.f13493g.add("com.htc.android.worldclock.ALARM_DONE");
        this.f13493g.add("com.htc.worldclock.ALARM_DONE");
        this.f13493g.add("com.lenovomobile.deskclock.ALARM_DONE");
        this.f13493g.add("com.cn.google.AlertClock.ALARM_DONE");
        this.f13493g.add("com.htc.android.worldclock.intent.action.ALARM_DONE");
        this.f13493g.add("com.lenovo.deskclock.ALARM_DONE");
        this.f13493g.add("com.oppo.alarmclock.alarmclock.ALARM_DONE");
        this.f13493g.add("com.zdworks.android.zdclock.ACTION_ALARM_DONE");
        this.f13493g.add("com.android.alarmclock.alarm_killed");
        this.f13493g.add("alarm_killed");
    }

    public static b a() {
        if (f13489j == null) {
            synchronized (b.class) {
                if (f13489j == null) {
                    f13489j = new b();
                }
            }
        }
        return f13489j;
    }

    private void c() {
        if (this.f13494h == null) {
            this.f13494h = new C0589b();
            TelephonyManager telephonyManager = (TelephonyManager) com.jingdong.manto.c.a().getSystemService(SignUpTable.TB_COLUMN_PHONE);
            if (telephonyManager != null) {
                try {
                    telephonyManager.listen(this.f13494h, 32);
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.a) {
            this.b = true;
            WeakReference<com.jingdong.manto.m.p0.a> weakReference = this.f13490c;
            com.jingdong.manto.m.p0.a aVar = weakReference != null ? weakReference.get() : null;
            if (aVar != null) {
                aVar.onAudioInterruptionBegin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.b) {
            this.b = false;
            WeakReference<com.jingdong.manto.m.p0.a> weakReference = this.f13490c;
            com.jingdong.manto.m.p0.a aVar = weakReference != null ? weakReference.get() : null;
            if (aVar != null) {
                aVar.onAudioInterruptionEnd();
            }
        }
    }

    private void f() {
        if (this.d == null) {
            IntentFilter intentFilter = new IntentFilter();
            Iterator<String> it = this.f13492f.iterator();
            while (it.hasNext()) {
                intentFilter.addAction(it.next());
            }
            Iterator<String> it2 = this.f13493g.iterator();
            while (it2.hasNext()) {
                intentFilter.addAction(it2.next());
            }
            intentFilter.setPriority(1000);
            this.d = new a();
            com.jingdong.manto.c.a().registerReceiver(this.d, intentFilter);
        }
    }

    public void a(com.jingdong.manto.m.p0.a aVar) {
        this.f13490c = new WeakReference<>(aVar);
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void b() {
        f();
        c();
    }

    public void g() {
        if (this.d != null) {
            com.jingdong.manto.c.a().unregisterReceiver(this.d);
        }
        this.d = null;
        this.f13494h = null;
    }
}
