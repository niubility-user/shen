package c.t.m.g;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.map.geolocation.TencentGeofence;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.fence.TxGeofenceManagerState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class a4 extends BroadcastReceiver implements PendingIntent.OnFinished, TencentLocationListener {

    /* renamed from: g  reason: collision with root package name */
    public final Context f298g;

    /* renamed from: h  reason: collision with root package name */
    public final s3 f299h;

    /* renamed from: i  reason: collision with root package name */
    public final PowerManager.WakeLock f300i;

    /* renamed from: j  reason: collision with root package name */
    public final PowerManager.WakeLock f301j;

    /* renamed from: k  reason: collision with root package name */
    public final b f302k;

    /* renamed from: l  reason: collision with root package name */
    public final d f303l;

    /* renamed from: m  reason: collision with root package name */
    public final c f304m;

    /* renamed from: n  reason: collision with root package name */
    public boolean f305n;
    public boolean o;
    public PendingIntent p;
    public final TencentLocationRequest q;
    public boolean r;
    public double s;

    /* loaded from: classes.dex */
    public final class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                a4.this.k(true);
            } else if (i2 == 2) {
                a4.p("handleMessage: mock alarm --> wakeup");
                a4.this.f298g.sendBroadcast(a4.this.m());
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c {
        public final List<g6> a = new LinkedList();
        public boolean b = false;

        /* renamed from: c  reason: collision with root package name */
        public long f306c = 60000;
        public Location d = null;

        /* renamed from: e  reason: collision with root package name */
        public boolean f307e = false;

        /* renamed from: f  reason: collision with root package name */
        public final float[] f308f = {-1.0f, -1.0f};

        public void a() {
            this.a.clear();
            this.b = false;
            this.f306c = 60000L;
            this.d = null;
            this.f307e = false;
        }
    }

    /* loaded from: classes.dex */
    public final class d implements TxGeofenceManagerState {
        public LinkedList<TencentLocation> a;
        public List<Map<String, String>> b;

        public d() {
            this.a = new LinkedList<>();
            this.b = new ArrayList();
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final void add(int i2, TencentLocation tencentLocation) {
            LinkedList<TencentLocation> linkedList;
            q5 q5Var;
            if (i2 == 0) {
                linkedList = this.a;
                q5Var = tencentLocation;
            } else {
                linkedList = this.a;
                q5 q5Var2 = q5.w;
                q5Var2.f(System.currentTimeMillis());
                q5Var = q5Var2;
            }
            linkedList.add(q5Var);
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final long getLastInterval() {
            return a4.this.f304m.f306c;
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final TencentLocation getLastLocation() {
            return this.a.isEmpty() ? q5.w : this.a.getLast();
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final long getLastLocationTime() {
            if (this.a.isEmpty()) {
                return 0L;
            }
            return this.a.getLast().getTime();
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final Map<String, String> getLastSummary() {
            if (this.b.isEmpty()) {
                return Collections.emptyMap();
            }
            return this.b.get(r0.size() - 1);
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final String getLocationTimes() {
            int size = this.a.size();
            Iterator<TencentLocation> it = this.a.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                if (it.next() == q5.w) {
                    i2++;
                }
            }
            return size + "/" + i2;
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final List<TencentLocation> getLocations() {
            return new ArrayList(this.a);
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final long getNextLocationTime() {
            return getLastLocationTime() + a4.this.f304m.f306c;
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final double getSpeed() {
            return a4.this.x();
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final List<Map<String, String>> getSummary() {
            return this.b;
        }

        @Override // com.tencent.map.geolocation.fence.TxGeofenceManagerState
        public final void reset() {
        }
    }

    public a4(Context context) {
        this(context, Looper.myLooper());
    }

    public a4(Context context, Looper looper) {
        this.f304m = new c();
        this.f305n = false;
        this.o = false;
        this.q = TencentLocationRequest.create().setRequestLevel(0).setInterval(0L);
        this.s = 1.0d;
        this.f298g = context;
        this.f299h = new s3(y4.b(context));
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.f300i = powerManager.newWakeLock(1, "GeofenceManager");
        PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, "tencent_location");
        this.f301j = newWakeLock;
        newWakeLock.setReferenceCounted(false);
        this.f302k = new b(looper);
        this.f303l = new d();
        y();
    }

    public static void p(String str) {
    }

    public final void A() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Iterator<g6> it = this.f304m.a.iterator();
        while (it.hasNext()) {
            if (it.next().f456c < elapsedRealtime) {
                it.remove();
            }
        }
    }

    public final void B() {
        this.f304m.a();
        this.f303l.reset();
    }

    public final void C() {
        b(-1L);
        this.f302k.removeMessages(2);
        this.f299h.A(this);
    }

    public float a(List<Float> list) {
        float f2;
        if (list.size() > 0) {
            Collections.sort(list);
            Collections.reverse(list);
            f2 = list.get(0).floatValue();
        } else {
            f2 = 25.0f;
        }
        float[] fArr = this.f304m.f308f;
        if (fArr[0] > 0.0f) {
            fArr[0] = fArr[0] + f2;
            double d2 = fArr[0];
            Double.isNaN(d2);
            fArr[0] = (float) (d2 * 0.5d);
        } else {
            fArr[0] = f2;
        }
        return fArr[0];
    }

    public final PendingIntent b(long j2) {
        AlarmManager alarmManager = (AlarmManager) this.f298g.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent pendingIntent = null;
        if (alarmManager == null) {
            return null;
        }
        String deviceManufacture = BaseInfo.getDeviceManufacture();
        boolean contains = deviceManufacture != null ? deviceManufacture.toLowerCase(Locale.US).contains("xiaomi") : false;
        PendingIntent pendingIntent2 = this.p;
        if (pendingIntent2 != null) {
            alarmManager.cancel(pendingIntent2);
            this.p = null;
            if (contains) {
                this.f302k.removeMessages(2);
            }
        }
        if (j2 > 0) {
            pendingIntent = r();
            this.p = pendingIntent;
            alarmManager.setRepeating(2, SystemClock.elapsedRealtime() + j2, j2, pendingIntent);
            if (contains) {
                this.f302k.sendEmptyMessageDelayed(2, 10000 + j2);
            }
            p("setLocationAlarm: will triggered after " + j2 + " ms, isXiaomi=" + contains);
        }
        return pendingIntent;
    }

    public final void d() {
        if (this.f305n) {
            throw new IllegalStateException("this object has been destroyed!");
        }
    }

    public final void e(PendingIntent pendingIntent) {
        new StringBuilder("sendIntentEnter: pendingIntent=").append(pendingIntent);
        Intent intent = new Intent();
        intent.putExtra("entering", true);
        f(pendingIntent, intent);
    }

    @SuppressLint({"Wakelock"})
    public final void f(PendingIntent pendingIntent, Intent intent) {
        this.f300i.acquire();
        try {
            pendingIntent.send(this.f298g, 0, intent, this, null);
        } catch (PendingIntent.CanceledException unused) {
            o(null, pendingIntent);
            this.f300i.release();
        }
    }

    public void h(TencentGeofence tencentGeofence) {
        d();
        if (tencentGeofence == null) {
            return;
        }
        new StringBuilder("removeFence: fence=").append(tencentGeofence);
        synchronized (this.f304m) {
            Iterator<g6> it = this.f304m.a.iterator();
            while (it.hasNext()) {
                if (tencentGeofence.equals(it.next().a)) {
                    it.remove();
                }
            }
            w("removeFence: --> schedule update fence");
        }
    }

    public void i(TencentGeofence tencentGeofence, PendingIntent pendingIntent) {
        d();
        if (tencentGeofence == null || pendingIntent == null) {
            throw null;
        }
        StringBuilder sb = new StringBuilder("addFence: , geofence=");
        sb.append(tencentGeofence);
        sb.append(", intent=");
        sb.append(pendingIntent);
        g6 g6Var = new g6(tencentGeofence, tencentGeofence.getExpireAt(), "packageName", pendingIntent);
        c cVar = this.f304m;
        List<g6> list = cVar.a;
        synchronized (cVar) {
            int size = list.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                g6 g6Var2 = list.get(size);
                if (tencentGeofence.equals(g6Var2.a) && pendingIntent.equals(g6Var2.d)) {
                    list.remove(size);
                    break;
                }
                size--;
            }
            list.add(g6Var);
            w("addFence: --> schedule update fence");
        }
    }

    public final void k(boolean z) {
        double d2;
        long j2;
        s3 s3Var;
        TencentLocationRequest tencentLocationRequest;
        Looper looper;
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        synchronized (this.f304m) {
            this.f304m.f307e = false;
            A();
            Location v = v();
            p("updateFences: fresh_location=".concat(String.valueOf(v)));
            ArrayList arrayList = new ArrayList();
            List<g6> list = this.f304m.a;
            boolean z2 = !list.isEmpty();
            if (v != null) {
                n6.c(v);
                d2 = Double.MAX_VALUE;
                for (g6 g6Var : list) {
                    int c2 = g6Var.c(v);
                    if ((c2 & 1) != 0) {
                        linkedList.add(g6Var.d);
                    }
                    if ((c2 & 2) != 0) {
                        linkedList2.add(g6Var.d);
                    }
                    double a2 = g6Var.a();
                    if (a2 < d2) {
                        d2 = a2;
                    }
                    if (g6Var.e()) {
                        arrayList.add(Float.valueOf(g6Var.d()));
                    }
                }
                a(arrayList);
            } else {
                d2 = Double.MAX_VALUE;
            }
            if (z2) {
                double x = x();
                if (v == null || Double.compare(d2, Double.MAX_VALUE) == 0) {
                    j2 = 60000;
                } else {
                    j2 = (long) Math.min(900000.0d, Math.max(60000.0d, (d2 * 1000.0d) / x));
                    if (d2 < 1000.0d && j2 > 305000) {
                        j2 = 305000;
                    }
                }
                if (x >= 5.0d || d2 <= 800.0d) {
                    this.s = 1.0d;
                } else {
                    double d3 = this.s * 1.02d;
                    this.s = d3;
                    j2 = (long) (d3 * 2.0d * 60000.0d);
                    if (j2 > 305000) {
                        j2 = 305000;
                    }
                }
                this.f304m.f306c = j2;
                boolean z3 = z && v == null;
                p(String.format(Locale.getDefault(), "updateFences: needUpdates=%s, interval=%d, minDist=%5g, speed=%.2f, reschedule=%s, rate=%.2f", Boolean.valueOf(z2), Long.valueOf(j2), Double.valueOf(d2), Double.valueOf(x), Boolean.valueOf(z3), Double.valueOf(this.s)));
                c cVar = this.f304m;
                if (!cVar.b) {
                    cVar.b = true;
                    this.f301j.acquire(RequestWorker.JOB_TIMEOUT);
                    s3Var = this.f299h;
                    tencentLocationRequest = this.q;
                    looper = this.f302k.getLooper();
                } else if (z3) {
                    b(-1L);
                    this.f304m.b = true;
                    this.f301j.acquire(RequestWorker.JOB_TIMEOUT);
                    s3Var = this.f299h;
                    tencentLocationRequest = this.q;
                    looper = this.f302k.getLooper();
                }
                s3Var.f(tencentLocationRequest, this, looper);
            } else {
                c cVar2 = this.f304m;
                if (cVar2.b) {
                    cVar2.b = false;
                    B();
                    C();
                }
            }
            HashMap hashMap = new HashMap();
            for (g6 g6Var2 : list) {
                hashMap.put(g6Var2.a.getTag(), g6Var2.toString());
            }
            this.f303l.b.add(hashMap);
        }
        Iterator it = linkedList2.iterator();
        while (it.hasNext()) {
            n((PendingIntent) it.next());
        }
        Iterator it2 = linkedList.iterator();
        while (it2.hasNext()) {
            e((PendingIntent) it2.next());
        }
    }

    public final Intent m() {
        Intent intent = new Intent("com.tencent.map.geolocation.wakeup");
        intent.putExtra("com.tencent.map.geolocation.from_alarm", true);
        return intent;
    }

    public final void n(PendingIntent pendingIntent) {
        new StringBuilder("sendIntentExit: pendingIntent=").append(pendingIntent);
        Intent intent = new Intent();
        intent.putExtra("entering", false);
        f(pendingIntent, intent);
    }

    public final void o(TencentGeofence tencentGeofence, PendingIntent pendingIntent) {
        StringBuilder sb = new StringBuilder("removeFence: fence=");
        sb.append(tencentGeofence);
        sb.append(", intent=");
        sb.append(pendingIntent);
        synchronized (this.f304m) {
            Iterator<g6> it = this.f304m.a.iterator();
            while (it.hasNext()) {
                g6 next = it.next();
                if (next.d.equals(pendingIntent)) {
                    if (tencentGeofence != null && !tencentGeofence.equals(next.a)) {
                    }
                    it.remove();
                }
            }
            w("_removeFence: --> schedule update fence");
        }
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onLocationChanged(TencentLocation tencentLocation, int i2, String str) {
        Location a2 = n6.a(tencentLocation, this.o);
        p(n6.b(tencentLocation, i2));
        if (!this.o || i2 == 0 || "gps".equals(tencentLocation.getProvider())) {
            if (!this.r) {
                this.f299h.A(this);
            }
            synchronized (this.f304m) {
                if (i2 == 0) {
                    this.f303l.add(i2, tencentLocation);
                    c cVar = this.f304m;
                    if (cVar.b) {
                        cVar.d = a2;
                    }
                    if (cVar.f307e) {
                        this.f302k.removeMessages(1);
                    } else {
                        cVar.f307e = true;
                    }
                    p("onLocationChanged: fresh location got --> update fences");
                    k(false);
                } else {
                    this.f304m.f306c = 60000L;
                    this.f303l.add(i2, tencentLocation);
                }
                if (this.f304m.b) {
                    p("onLocationChanged: set a new repeat alarm, interval=" + this.f304m.f306c);
                    b(this.f304m.f306c);
                }
            }
            if (this.f301j.isHeld()) {
                this.f301j.release();
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        String action = intent.getAction();
        synchronized (this.f304m) {
            boolean z = v() == null;
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                if (z) {
                    str = "onReceive: screen_on and no_fresh_location --> schedule update fence";
                    u(str);
                }
            } else if ("com.tencent.map.geolocation.wakeup".equals(action)) {
                try {
                    m.b(this.f298g);
                    this.f302k.removeMessages(2);
                    w("onReceive: alarm --> schedule update fence");
                } catch (Exception unused) {
                } catch (Throwable th) {
                    m.a();
                    throw th;
                }
                m.a();
            } else if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
                if (z) {
                    str = "onReceive: power_disconnected --> schedule update fence";
                    u(str);
                }
            } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                if (!g.c(this.f298g)) {
                    p("onReceive: disconnected and stop location updates temporaryly");
                    c cVar = this.f304m;
                    cVar.b = false;
                    cVar.f306c = 60000L;
                    C();
                }
                str = "onReceive: connected and no_fresh_location --> schedule update fence";
                u(str);
            }
        }
    }

    @Override // android.app.PendingIntent.OnFinished
    public void onSendFinished(PendingIntent pendingIntent, Intent intent, int i2, String str, Bundle bundle) {
        this.f300i.release();
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onStatusUpdate(String str, int i2, String str2) {
    }

    public final PendingIntent r() {
        Context context;
        Intent m2;
        int i2;
        if (Build.VERSION.SDK_INT >= 31) {
            context = this.f298g;
            m2 = m();
            i2 = 201326592;
        } else {
            context = this.f298g;
            m2 = m();
            i2 = 134217728;
        }
        return PendingIntent.getBroadcast(context, 0, m2, i2);
    }

    public void s(String str) {
        d();
        synchronized (this.f304m) {
            Iterator<g6> it = this.f304m.a.iterator();
            while (it.hasNext()) {
                TencentGeofence tencentGeofence = it.next().a;
                if (tencentGeofence == null || TextUtils.equals(tencentGeofence.getTag(), str)) {
                    it.remove();
                }
            }
            w("removeFence: " + str + " removed --> schedule update fence");
        }
    }

    public void t() {
        if (this.f305n) {
            return;
        }
        C();
        this.f298g.unregisterReceiver(this);
        synchronized (this.f304m) {
            Arrays.fill(this.f304m.f308f, -1.0f);
            B();
        }
        this.o = false;
        this.f305n = true;
    }

    public final void u(String str) {
        if (!g.c(this.f298g)) {
            p("no data conn. skip [" + str + "]");
        } else if (this.f304m.f307e) {
        } else {
            p(str);
            this.f304m.f307e = true;
            this.f302k.sendEmptyMessage(1);
        }
    }

    public final Location v() {
        c cVar = this.f304m;
        Location location = cVar.d;
        List<g6> list = cVar.a;
        if (location == null && !list.isEmpty()) {
            location = n6.a(this.f299h.k0(), this.o);
        }
        if (location != null && System.currentTimeMillis() - location.getTime() < 60000) {
            return location;
        }
        return null;
    }

    public final void w(String str) {
        if (this.f304m.f307e) {
            return;
        }
        p(str);
        this.f304m.f307e = true;
        this.f302k.sendEmptyMessage(1);
    }

    public final double x() {
        if (v6.a(this.f298g)) {
            return 1.0d;
        }
        float f2 = 1.0f;
        float f3 = 25.0f;
        if (k1.e(y4.b(this.f298g))) {
            f3 = 15.0f;
        } else {
            f2 = 3.0f;
        }
        float f4 = this.f304m.f308f[0];
        if (f4 < f2) {
            if (g.d(this.f298g)) {
                double d2 = f3;
                Double.isNaN(d2);
                return d2 * 0.3d;
            }
            return f3;
        }
        double min = Math.min(Math.max(f2, f4), 10.0f + f3);
        Double.isNaN(min);
        double d3 = f3 + f2;
        Double.isNaN(d3);
        double d4 = (min * 0.8d) + (d3 * 0.1d);
        double d5 = f2;
        return d4 >= d5 ? d4 : d5;
    }

    public final void y() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("com.tencent.map.geolocation.wakeup");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.f298g.registerReceiver(this, intentFilter, null, this.f302k);
    }

    public void z() {
        d();
        synchronized (this.f304m) {
            this.f299h.A(this);
            B();
        }
    }
}
