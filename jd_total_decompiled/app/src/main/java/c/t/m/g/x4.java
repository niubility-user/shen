package c.t.m.g;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class x4 extends BroadcastReceiver {
    public static boolean p;
    public volatile boolean a;
    public final y4 b;

    /* renamed from: c  reason: collision with root package name */
    public final WifiManager f746c;
    public long d;

    /* renamed from: e  reason: collision with root package name */
    public HashSet<String> f747e;

    /* renamed from: f  reason: collision with root package name */
    public volatile Handler f748f;

    /* renamed from: g  reason: collision with root package name */
    public volatile Handler f749g;

    /* renamed from: h  reason: collision with root package name */
    public volatile c f750h;

    /* renamed from: i  reason: collision with root package name */
    public volatile List<ScanResult> f751i;

    /* renamed from: j  reason: collision with root package name */
    public volatile List<ScanResult> f752j;

    /* renamed from: k  reason: collision with root package name */
    public Runnable f753k;

    /* renamed from: l  reason: collision with root package name */
    public Runnable f754l;

    /* renamed from: m  reason: collision with root package name */
    public String f755m;

    /* renamed from: n  reason: collision with root package name */
    public long f756n = Final.HALF_MINUTE;
    public final byte[] o = new byte[0];

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean t = x4.this.t();
            if (x4.this.f756n > 0) {
                x4 x4Var = x4.this;
                x4Var.e(x4Var.f756n);
            }
            StringBuilder sb = new StringBuilder("schedule scan. interval:");
            sb.append(x4.this.f756n);
            sb.append(", success:");
            sb.append(t);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            try {
                if (x4.this.f749g == null) {
                    x4.this.b.a.registerReceiver(x4.this, intentFilter);
                    return;
                }
                Context context = x4.this.b.a;
                x4 x4Var = x4.this;
                context.registerReceiver(x4Var, intentFilter, null, x4Var.f749g);
            } catch (Exception unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        public final void a() {
            try {
                List<ScanResult> list = x4.this.f751i;
                if (list == null || list.size() <= 0) {
                    x4.this.b.f(b1.d);
                    x4.this.f755m = "";
                    return;
                }
                if (x4.this.f752j == null) {
                    x4.this.f752j = new ArrayList();
                }
                try {
                    x4.this.f755m = "";
                    int i2 = 1;
                    for (ScanResult scanResult : list) {
                        if (i2 <= 20) {
                            x4.this.f755m = x4.this.f755m + scanResult.SSID + DYConstants.DY_REGEX_COMMA + scanResult.BSSID + "|";
                            i2++;
                        }
                    }
                } catch (Throwable unused) {
                }
                x4.this.f752j.clear();
                x4.this.f752j.addAll(list);
                o6.a(x4.this.f752j);
                if (x4.this.f752j == null || x4.this.f752j.size() <= 0) {
                    return;
                }
                x4.this.c();
            } catch (Throwable unused2) {
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (x4.this.o) {
                switch (message.what) {
                    case R2.attr.layout_constraintCircleRadius /* 1201 */:
                        x4.this.l();
                        break;
                    case R2.attr.layout_constraintDimensionRatio /* 1202 */:
                        a();
                        break;
                    case R2.attr.layout_constraintEnd_toEndOf /* 1203 */:
                        x4.this.f((Intent) message.obj);
                        break;
                }
            }
        }
    }

    public x4(y4 y4Var) {
        this.b = y4Var;
        this.f746c = y4Var.n();
        u0.a = 0L;
        this.f747e = new HashSet<>();
        this.f753k = new a();
        this.f754l = new b();
    }

    public final void c() {
        List<ScanResult> list = this.f752j;
        if (this.f747e == null) {
            this.f747e = new HashSet<>();
        }
        if (list == null) {
            return;
        }
        if (this.f747e.size() == 0) {
            for (ScanResult scanResult : list) {
                this.f747e.add(scanResult.BSSID + scanResult.level);
            }
        } else {
            int size = this.f747e.size();
            if (size == list.size()) {
                for (ScanResult scanResult2 : list) {
                    this.f747e.add(scanResult2.BSSID + scanResult2.level);
                }
                if (size != this.f747e.size()) {
                    this.f747e.clear();
                    for (ScanResult scanResult3 : list) {
                        this.f747e.add(scanResult3.BSSID + scanResult3.level);
                    }
                    this.d = System.currentTimeMillis();
                    n(list);
                    return;
                }
                return;
            }
            this.f747e.clear();
            for (ScanResult scanResult4 : list) {
                this.f747e.add(scanResult4.BSSID + scanResult4.level);
            }
        }
        this.d = System.currentTimeMillis();
        n(list);
    }

    public final void d(int i2) {
        if (this.f750h != null) {
            t.k(this.f750h, i2);
        }
    }

    public final void e(long j2) {
        Handler handler = this.f748f;
        Runnable runnable = this.f753k;
        Looper looper = handler == null ? null : handler.getLooper();
        if (looper == null || !looper.getThread().isAlive()) {
            return;
        }
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, j2);
    }

    public final void f(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            String action = intent.getAction();
            boolean equals = "android.net.wifi.WIFI_STATE_CHANGED".equals(action);
            if (equals) {
                d(R2.attr.layout_constraintCircleRadius);
            }
            boolean equals2 = "android.net.wifi.SCAN_RESULTS".equals(action);
            if (equals2 || equals) {
                StringBuilder sb = new StringBuilder("onRecive,start to getWfifcanresult,systemapi,isAvailableAction=");
                sb.append(equals2);
                sb.append(",isStatusAction=");
                sb.append(equals);
                this.f751i = k1.c(this.f746c, equals2);
                d(R2.attr.layout_constraintDimensionRatio);
            }
        } catch (Throwable unused) {
        }
    }

    public final void g(Handler handler, Handler handler2, Handler handler3, boolean z) {
        synchronized (this.o) {
            if (this.a) {
                return;
            }
            this.a = true;
            u0.a = 0L;
            p = z;
            this.f748f = handler;
            this.f749g = handler3;
            if (this.f750h == null || this.f750h.getLooper() != handler.getLooper()) {
                if (this.f750h != null) {
                    this.f750h.removeCallbacksAndMessages(null);
                }
                if (handler != null) {
                    this.f750h = new c(handler.getLooper());
                }
            }
            handler2.post(this.f754l);
            if (!p) {
                e(0L);
            }
        }
    }

    public final boolean j(List<ScanResult> list) {
        if (this.f746c != null && !t2.d(list)) {
            try {
                if (!this.f746c.isWifiEnabled() && Build.VERSION.SDK_INT >= 18 && !this.f746c.isScanAlwaysAvailable()) {
                    long j2 = 0;
                    Iterator<ScanResult> it = list.iterator();
                    while (it.hasNext()) {
                        long j3 = it.next().timestamp;
                        if (j3 > j2) {
                            j2 = j3;
                        }
                    }
                    long elapsedRealtime = SystemClock.elapsedRealtime() - (j2 / 1000);
                    r1 = elapsedRealtime <= 60000;
                    o4.o("WIFI", "wifi closed,list v=" + r1 + ",d_t=" + elapsedRealtime + "ms");
                }
            } catch (Throwable unused) {
            }
        }
        return r1;
    }

    public final void l() {
        try {
            int a2 = k1.a(this.f746c);
            int i2 = 1;
            if (a2 == 3) {
                e(0L);
            } else if (a2 == 1) {
                i2 = 0;
                if (!k1.f(this.b)) {
                    if (this.f752j != null) {
                        this.f752j.clear();
                    }
                    if (this.f748f != null) {
                        t.k(this.f748f, R2.attr.chainUseRtl);
                    }
                }
            } else {
                i2 = -1;
            }
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (Settings.Secure.getInt(this.b.a.getContentResolver(), "location_mode") == 0) {
                        i2 = 5;
                    }
                }
            } catch (Throwable unused) {
            }
            Message message = new Message();
            message.what = R2.id.decode_failed;
            message.arg1 = R2.drawable.wxa_trading_guarantee_icon_green;
            message.arg2 = i2;
            this.b.f(message);
        } catch (Throwable unused2) {
        }
    }

    public final void m(long j2) {
        this.f756n = j2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void n(List<ScanResult> list) {
        if (list != null && list.size() != 0) {
            if (k1.a) {
                k1.a = false;
            }
            if (j(list)) {
                return;
            }
            this.b.f(new b1(list, this.d, k1.a(this.f746c)));
            return;
        }
        l();
        if (j(list)) {
        }
    }

    public final int o() {
        return !t();
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        c cVar = this.f750h;
        if (cVar != null) {
            Message obtainMessage = cVar.obtainMessage();
            obtainMessage.obj = intent;
            obtainMessage.what = R2.attr.layout_constraintEnd_toEndOf;
            t.e(cVar, obtainMessage);
        }
    }

    public final void r() {
        synchronized (this.o) {
            if (this.a) {
                this.a = false;
                u0.a = 0L;
                try {
                    this.b.a.unregisterReceiver(this);
                } catch (Exception unused) {
                }
                this.f747e = null;
                if (this.f752j != null) {
                    this.f752j.clear();
                }
                HashSet<String> hashSet = this.f747e;
                if (hashSet != null) {
                    hashSet.clear();
                }
                if (this.f750h != null) {
                    this.f750h.removeCallbacksAndMessages(null);
                    this.f750h = null;
                }
            }
        }
    }

    public final boolean t() {
        if (!k1.f(this.b) || p) {
            return false;
        }
        boolean d = k1.d(this.f746c);
        StringBuilder sb = new StringBuilder("fs:");
        t2.a(d);
        sb.append(d ? 1 : 0);
        o4.o("WIFI", sb.toString());
        return d;
    }
}
