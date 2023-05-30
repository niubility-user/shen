package com.xiaomi.push.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.xiaomi.push.a5;
import com.xiaomi.push.a6;
import com.xiaomi.push.a7;
import com.xiaomi.push.a8;
import com.xiaomi.push.c4;
import com.xiaomi.push.c5;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.d4;
import com.xiaomi.push.d8;
import com.xiaomi.push.e5;
import com.xiaomi.push.e6;
import com.xiaomi.push.f6;
import com.xiaomi.push.g6;
import com.xiaomi.push.g7;
import com.xiaomi.push.h7;
import com.xiaomi.push.i4;
import com.xiaomi.push.i6;
import com.xiaomi.push.l5;
import com.xiaomi.push.l9;
import com.xiaomi.push.m8;
import com.xiaomi.push.m9;
import com.xiaomi.push.o5;
import com.xiaomi.push.p5;
import com.xiaomi.push.r3;
import com.xiaomi.push.r5;
import com.xiaomi.push.r9;
import com.xiaomi.push.s8;
import com.xiaomi.push.service.i0;
import com.xiaomi.push.service.o2;
import com.xiaomi.push.service.t0;
import com.xiaomi.push.t5;
import com.xiaomi.push.u5;
import com.xiaomi.push.u6;
import com.xiaomi.push.w6;
import com.xiaomi.push.y4;
import com.xiaomi.push.y7;
import com.xiaomi.push.z6;
import g.j.b.a.a;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public class XMPushService extends Service implements r5 {
    private static boolean b;

    /* renamed from: a */
    private ContentObserver f223a;

    /* renamed from: a */
    private l5 f225a;

    /* renamed from: a */
    private o5 f226a;

    /* renamed from: a */
    private p5 f227a;

    /* renamed from: a */
    private a f228a;

    /* renamed from: a */
    private f f229a;

    /* renamed from: a */
    private k f230a;

    /* renamed from: a */
    private r f231a;

    /* renamed from: a */
    private t f232a;

    /* renamed from: a */
    private h2 f234a;

    /* renamed from: a */
    private s0 f236a;

    /* renamed from: a */
    private boolean f241a = false;
    private int a = 0;

    /* renamed from: b */
    private int f242b = 0;

    /* renamed from: a */
    private long f222a = 0;

    /* renamed from: a */
    protected Class f238a = XMJobService.class;

    /* renamed from: c */
    private int f19029c = -1;

    /* renamed from: a */
    private g0 f233a = null;

    /* renamed from: a */
    private o2 f235a = null;

    /* renamed from: a */
    Messenger f224a = null;

    /* renamed from: a */
    private Collection<com.xiaomi.push.service.s> f240a = Collections.synchronizedCollection(new ArrayList());

    /* renamed from: a */
    private ArrayList<n> f239a = new ArrayList<>();

    /* renamed from: a */
    private t5 f237a = new n1(this);

    /* loaded from: classes11.dex */
    public class a extends BroadcastReceiver {
        private final Object a;

        private a(XMPushService xMPushService) {
            this.a = new Object();
        }

        /* synthetic */ a(XMPushService xMPushService, n1 n1Var) {
            this(xMPushService);
        }

        public void a() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                g.j.a.a.a.c.D("[Alarm] Cannot perform lock.notifyAll in the UI thread!");
                return;
            }
            synchronized (this.a) {
                try {
                    this.a.notifyAll();
                } catch (Exception e2) {
                    g.j.a.a.a.c.o("[Alarm] notify lock. " + e2);
                }
            }
        }

        private void b(long j2) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                g.j.a.a.a.c.D("[Alarm] Cannot perform lock.wait in the UI thread!");
                return;
            }
            synchronized (this.a) {
                try {
                    this.a.wait(j2);
                } catch (InterruptedException e2) {
                    g.j.a.a.a.c.o("[Alarm] interrupt from waiting state. " + e2);
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            long currentTimeMillis = System.currentTimeMillis();
            g.j.a.a.a.c.B("[Alarm] heartbeat alarm has been triggered.");
            if (!m0.o.equals(intent.getAction())) {
                g.j.a.a.a.c.o("[Alarm] cancel the old ping timer");
                i4.a();
            } else if (TextUtils.equals(context.getPackageName(), intent.getPackage())) {
                g.j.a.a.a.c.B("[Alarm] Ping XMChannelService on timer");
                try {
                    Intent intent2 = new Intent(context, XMPushService.class);
                    intent2.putExtra("time_stamp", System.currentTimeMillis());
                    intent2.setAction("com.xiaomi.push.timer");
                    com.xiaomi.push.service.a.h(context).i(intent2);
                    b(3000L);
                    g.j.a.a.a.c.o("[Alarm] heartbeat alarm finish in " + (System.currentTimeMillis() - currentTimeMillis));
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b extends j {

        /* renamed from: h */
        i0.b f19030h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(i0.b bVar) {
            super(9);
            XMPushService.this = r1;
            this.f19030h = null;
            this.f19030h = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            String str;
            try {
                if (!XMPushService.this.m159c()) {
                    g.j.a.a.a.c.D("trying bind while the connection is not created, quit!");
                    return;
                }
                i0 c2 = i0.c();
                i0.b bVar = this.f19030h;
                i0.b b = c2.b(bVar.f19100h, bVar.b);
                if (b == null) {
                    str = "ignore bind because the channel " + this.f19030h.f19100h + " is removed ";
                } else if (b.f19105m == i0.c.unbind) {
                    b.k(i0.c.binding, 0, 0, null, null);
                    XMPushService.this.f226a.m(b);
                    c5.f(XMPushService.this, b);
                    return;
                } else {
                    str = "trying duplicate bind, ingore! " + b.f19105m;
                }
                g.j.a.a.a.c.o(str);
            } catch (Exception e2) {
                g.j.a.a.a.c.D("Meet error when trying to bind. " + e2);
                XMPushService.this.a(10, e2);
            } catch (Throwable unused) {
            }
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "bind the client. " + this.f19030h.f19100h;
        }
    }

    /* loaded from: classes11.dex */
    public static class c extends j {

        /* renamed from: h */
        private final i0.b f19032h;

        public c(i0.b bVar) {
            super(12);
            this.f19032h = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            this.f19032h.k(i0.c.unbind, 1, 21, null, null);
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "bind time out. chid=" + this.f19032h.f19100h;
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                return TextUtils.equals(((c) obj).f19032h.f19100h, this.f19032h.f19100h);
            }
            return false;
        }

        public int hashCode() {
            return this.f19032h.f19100h.hashCode();
        }
    }

    /* loaded from: classes11.dex */
    class d extends j {

        /* renamed from: h */
        private e5 f19033h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(e5 e5Var) {
            super(8);
            XMPushService.this = r1;
            this.f19033h = null;
            this.f19033h = e5Var;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            XMPushService.this.f233a.a(this.f19033h);
            if (b2.a(this.f19033h)) {
                XMPushService.this.a(new t0.a(), 15000L);
            }
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "receive a message.";
        }
    }

    /* loaded from: classes11.dex */
    public class e extends j {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e() {
            super(1);
            XMPushService.this = r1;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            if (XMPushService.this.m154a()) {
                XMPushService xMPushService = XMPushService.this;
                if (xMPushService.a(xMPushService.getApplicationContext())) {
                    XMPushService.this.f();
                    return;
                }
            }
            g.j.a.a.a.c.o("should not connect. quit the job.");
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "do reconnect..";
        }
    }

    /* loaded from: classes11.dex */
    public class f extends BroadcastReceiver {
        f() {
            XMPushService.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            XMPushService.this.onStart(intent, 1);
        }
    }

    /* loaded from: classes11.dex */
    public class g extends j {

        /* renamed from: h */
        public int f19036h;

        /* renamed from: i */
        public Exception f19037i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(int i2, Exception exc) {
            super(2);
            XMPushService.this = r1;
            this.f19036h = i2;
            this.f19037i = exc;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            XMPushService.this.a(this.f19036h, this.f19037i);
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "disconnect the connection.";
        }
    }

    /* loaded from: classes11.dex */
    class h extends j {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h() {
            super(65535);
            XMPushService.this = r1;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            XMPushService.this.c();
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "Init Job";
        }
    }

    /* loaded from: classes11.dex */
    public class i extends j {

        /* renamed from: h */
        private Intent f19040h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(Intent intent) {
            super(15);
            XMPushService.this = r1;
            this.f19040h = null;
            this.f19040h = intent;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            XMPushService.this.d(this.f19040h);
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "Handle intent action = " + this.f19040h.getAction();
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class j extends o2.b {
        public j(int i2) {
            super(i2);
        }

        public abstract void a();

        public abstract String b();

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.f19161g;
            if (i2 != 4 && i2 != 8) {
                g.j.a.a.a.c.p(g.j.a.a.a.b.a, b());
            }
            a();
        }
    }

    /* loaded from: classes11.dex */
    class k extends BroadcastReceiver {
        k() {
            XMPushService.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g.j.a.a.a.c.o("[HB] hold short heartbeat, " + a8.e(intent));
            if (intent == null || intent.getExtras() == null) {
                return;
            }
            XMPushService.this.onStart(intent, 1);
        }
    }

    /* loaded from: classes11.dex */
    class l extends j {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l() {
            super(5);
            XMPushService.this = r1;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            XMPushService.this.f235a.b();
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "ask the job queue to quit";
        }
    }

    /* loaded from: classes11.dex */
    class m extends j {

        /* renamed from: h */
        private g6 f19043h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m(g6 g6Var) {
            super(8);
            XMPushService.this = r1;
            this.f19043h = null;
            this.f19043h = g6Var;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            XMPushService.this.f233a.c(this.f19043h);
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "receive a message.";
        }
    }

    /* loaded from: classes11.dex */
    public interface n {
        void a();
    }

    /* loaded from: classes11.dex */
    public class o extends j {

        /* renamed from: h */
        boolean f19045h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public o(boolean z) {
            super(4);
            XMPushService.this = r1;
            this.f19045h = z;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            if (XMPushService.this.m159c()) {
                try {
                    if (!this.f19045h) {
                        c5.a();
                    }
                    XMPushService.this.f226a.A(this.f19045h);
                } catch (a6 e2) {
                    g.j.a.a.a.c.s(e2);
                    XMPushService.this.a(10, e2);
                }
            }
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "send ping..";
        }
    }

    /* loaded from: classes11.dex */
    public class p extends j {

        /* renamed from: h */
        i0.b f19047h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p(i0.b bVar) {
            super(4);
            XMPushService.this = r1;
            this.f19047h = null;
            this.f19047h = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            try {
                this.f19047h.k(i0.c.unbind, 1, 16, null, null);
                o5 o5Var = XMPushService.this.f226a;
                i0.b bVar = this.f19047h;
                o5Var.o(bVar.f19100h, bVar.b);
                XMPushService xMPushService = XMPushService.this;
                xMPushService.a(new b(this.f19047h), 300L);
            } catch (a6 e2) {
                g.j.a.a.a.c.s(e2);
                XMPushService.this.a(10, e2);
            }
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "rebind the client. " + this.f19047h.f19100h;
        }
    }

    /* loaded from: classes11.dex */
    public class q extends j {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        q() {
            super(3);
            XMPushService.this = r1;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            XMPushService.this.a(11, (Exception) null);
            if (XMPushService.this.m154a()) {
                XMPushService xMPushService = XMPushService.this;
                if (xMPushService.a(xMPushService.getApplicationContext())) {
                    XMPushService.this.f();
                }
            }
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "reset the connection.";
        }
    }

    /* loaded from: classes11.dex */
    class r extends BroadcastReceiver {
        r() {
            XMPushService.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            XMPushService.this.onStart(intent, 1);
        }
    }

    /* loaded from: classes11.dex */
    public class s extends j {

        /* renamed from: h */
        i0.b f19050h;

        /* renamed from: i */
        int f19051i;

        /* renamed from: j */
        String f19052j;

        /* renamed from: k */
        String f19053k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s(i0.b bVar, int i2, String str, String str2) {
            super(9);
            XMPushService.this = r1;
            this.f19050h = null;
            this.f19050h = bVar;
            this.f19051i = i2;
            this.f19052j = str;
            this.f19053k = str2;
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public void a() {
            if (this.f19050h.f19105m != i0.c.unbind && XMPushService.this.f226a != null) {
                try {
                    o5 o5Var = XMPushService.this.f226a;
                    i0.b bVar = this.f19050h;
                    o5Var.o(bVar.f19100h, bVar.b);
                } catch (a6 e2) {
                    g.j.a.a.a.c.s(e2);
                    XMPushService.this.a(10, e2);
                }
            }
            this.f19050h.k(i0.c.unbind, this.f19051i, 0, this.f19053k, this.f19052j);
        }

        @Override // com.xiaomi.push.service.XMPushService.j
        public String b() {
            return "unbind the channel. " + this.f19050h.f19100h;
        }
    }

    /* loaded from: classes11.dex */
    class t extends BroadcastReceiver {
        t() {
            XMPushService.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!XMPushService.this.f241a) {
                XMPushService.this.f241a = true;
            }
            XMPushService.this.onStart(intent, 1);
        }
    }

    private g6 a(g6 g6Var, String str, String str2) {
        StringBuilder sb;
        String str3;
        i0 c2 = i0.c();
        List<String> g2 = c2.g(str);
        if (g2.isEmpty()) {
            sb = new StringBuilder();
            str3 = "open channel should be called first before sending a packet, pkg=";
        } else {
            g6Var.v(str);
            str = g6Var.m();
            if (TextUtils.isEmpty(str)) {
                str = g2.get(0);
                g6Var.p(str);
            }
            i0.b b2 = c2.b(str, g6Var.q());
            if (!m159c()) {
                sb = new StringBuilder();
                str3 = "drop a packet as the channel is not connected, chid=";
            } else if (b2 != null && b2.f19105m == i0.c.binded) {
                if (TextUtils.equals(str2, b2.f19102j)) {
                    return g6Var;
                }
                sb = new StringBuilder();
                sb.append("invalid session. ");
                sb.append(str2);
                g.j.a.a.a.c.o(sb.toString());
                return null;
            } else {
                sb = new StringBuilder();
                str3 = "drop a packet as the channel is not opened, chid=";
            }
        }
        sb.append(str3);
        sb.append(str);
        g.j.a.a.a.c.o(sb.toString());
        return null;
    }

    private i0.b a(String str, Intent intent) {
        i0.b b2 = i0.c().b(str, intent.getStringExtra(m0.p));
        if (b2 == null) {
            b2 = new i0.b(this);
        }
        b2.f19100h = intent.getStringExtra(m0.s);
        b2.b = intent.getStringExtra(m0.p);
        b2.f19096c = intent.getStringExtra(m0.v);
        b2.a = intent.getStringExtra(m0.B);
        b2.f19098f = intent.getStringExtra(m0.z);
        b2.f19099g = intent.getStringExtra(m0.A);
        b2.f19097e = intent.getBooleanExtra(m0.y, false);
        b2.f19101i = intent.getStringExtra(m0.x);
        b2.f19102j = intent.getStringExtra(m0.F);
        b2.d = intent.getStringExtra(m0.w);
        b2.f19103k = this.f234a;
        b2.h((Messenger) intent.getParcelableExtra(m0.J));
        b2.f19104l = getApplicationContext();
        i0.c().l(b2);
        return b2;
    }

    private String a() {
        String g2 = a8.g("ro.miui.region");
        return TextUtils.isEmpty(g2) ? a8.g("ro.product.locale.region") : g2;
    }

    private void a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e2) {
                g.j.a.a.a.c.s(e2);
            }
        }
    }

    private void a(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        String string = extras.getString("digest");
        n2.c(getApplicationContext()).g(string);
        com.xiaomi.push.p1.e(this, string);
    }

    private void a(Intent intent, int i2) {
        byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
        boolean booleanExtra = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
        c8 c8Var = new c8();
        try {
            m8.e(c8Var, byteArrayExtra);
            com.xiaomi.push.i.b(getApplicationContext()).j(new c0(c8Var, new WeakReference(this), booleanExtra), i2);
        } catch (s8 unused) {
            g.j.a.a.a.c.D("aw_ping : send help app ping  error");
        }
    }

    private static void a(String str) {
        if (m9.China.name().equals(str)) {
            com.xiaomi.push.f1.n("cn.app.chat.xiaomi.net", "cn.app.chat.xiaomi.net");
            com.xiaomi.push.f1.n("cn.app.chat.xiaomi.net", "111.13.141.211:443");
            com.xiaomi.push.f1.n("cn.app.chat.xiaomi.net", "39.156.81.172:443");
            com.xiaomi.push.f1.n("cn.app.chat.xiaomi.net", "111.202.1.250:443");
            com.xiaomi.push.f1.n("cn.app.chat.xiaomi.net", "123.125.102.213:443");
            com.xiaomi.push.f1.n("resolver.msg.xiaomi.net", "111.13.142.153:443");
            com.xiaomi.push.f1.n("resolver.msg.xiaomi.net", "111.202.1.252:443");
        }
    }

    private void a(String str, int i2) {
        Collection<i0.b> f2 = i0.c().f(str);
        if (f2 != null) {
            for (i0.b bVar : f2) {
                if (bVar != null) {
                    a(new s(bVar, i2, null, null));
                }
            }
        }
        i0.c().m(str);
    }

    public boolean a(Context context) {
        try {
            com.xiaomi.push.o.a();
            for (int i2 = 100; i2 > 0; i2--) {
                if (com.xiaomi.push.j0.q(context)) {
                    g.j.a.a.a.c.o("network connectivity ok.");
                    return true;
                }
                try {
                    Thread.sleep(100L);
                } catch (Exception unused) {
                }
            }
            return false;
        } catch (Exception unused2) {
            return true;
        }
    }

    /* renamed from: a */
    private boolean m142a(String str, Intent intent) {
        i0.b b2 = i0.c().b(str, intent.getStringExtra(m0.p));
        boolean z = false;
        if (b2 == null || str == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra(m0.F);
        String stringExtra2 = intent.getStringExtra(m0.x);
        if (!TextUtils.isEmpty(b2.f19102j) && !TextUtils.equals(stringExtra, b2.f19102j)) {
            g.j.a.a.a.c.o("session changed. old session=" + b2.f19102j + ", new session=" + stringExtra + " chid = " + str);
            z = true;
        }
        if (stringExtra2.equals(b2.f19101i)) {
            return z;
        }
        g.j.a.a.a.c.o("security changed. chid = " + str + " sechash = " + com.xiaomi.push.o0.b(stringExtra2));
        return true;
    }

    /* renamed from: a */
    private int[] m143a() {
        String[] split;
        String f2 = b0.d(getApplicationContext()).f(h7.FallDownTimeRange.a(), "");
        if (!TextUtils.isEmpty(f2) && (split = f2.split(DYConstants.DY_REGEX_COMMA)) != null && split.length >= 2) {
            int[] iArr = new int[2];
            try {
                iArr[0] = Integer.valueOf(split[0]).intValue();
                iArr[1] = Integer.valueOf(split[1]).intValue();
                if (iArr[0] >= 0 && iArr[0] <= 23 && iArr[1] >= 0 && iArr[1] <= 23) {
                    if (iArr[0] != iArr[1]) {
                        return iArr;
                    }
                }
            } catch (NumberFormatException e2) {
                g.j.a.a.a.c.D("parse falldown time range failure: " + e2);
            }
        }
        return null;
    }

    private String b() {
        String str;
        com.xiaomi.push.o.a();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Object obj = new Object();
        int i2 = 0;
        if ("com.xiaomi.xmsf".equals(getPackageName())) {
            p0 c2 = p0.c(this);
            String str2 = null;
            while (true) {
                if (!TextUtils.isEmpty(str2) && c2.a() != 0) {
                    break;
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = a();
                }
                try {
                    synchronized (obj) {
                        if (i2 < 30) {
                            obj.wait(1000L);
                        } else {
                            obj.wait(Final.HALF_MINUTE);
                        }
                    }
                } catch (InterruptedException unused) {
                }
                i2++;
            }
            str = a();
        } else {
            str = "CN";
        }
        g.j.a.a.a.c.o("wait coutrycode :" + str + " cost = " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " , count = " + i2);
        return str;
    }

    private void b(Intent intent) {
        long j2;
        String stringExtra = intent.getStringExtra(m0.B);
        String stringExtra2 = intent.getStringExtra(m0.F);
        Bundle bundleExtra = intent.getBundleExtra("ext_packet");
        i0 c2 = i0.c();
        e5 e5Var = null;
        if (bundleExtra != null) {
            f6 f6Var = (f6) a(new f6(bundleExtra), stringExtra, stringExtra2);
            if (f6Var == null) {
                return;
            }
            e5Var = e5.c(f6Var, c2.b(f6Var.m(), f6Var.q()).f19101i);
        } else {
            byte[] byteArrayExtra = intent.getByteArrayExtra("ext_raw_packet");
            if (byteArrayExtra != null) {
                try {
                    j2 = Long.parseLong(intent.getStringExtra(m0.p));
                } catch (NumberFormatException unused) {
                    j2 = 0;
                }
                String stringExtra3 = intent.getStringExtra(m0.q);
                String stringExtra4 = intent.getStringExtra(m0.r);
                String stringExtra5 = intent.getStringExtra("ext_chid");
                i0.b b2 = c2.b(stringExtra5, String.valueOf(j2));
                if (b2 != null) {
                    e5 e5Var2 = new e5();
                    try {
                        e5Var2.h(Integer.parseInt(stringExtra5));
                    } catch (NumberFormatException unused2) {
                    }
                    e5Var2.l("SECMSG", null);
                    if (TextUtils.isEmpty(stringExtra3)) {
                        stringExtra3 = "xiaomi.com";
                    }
                    e5Var2.j(j2, stringExtra3, stringExtra4);
                    e5Var2.k(intent.getStringExtra("ext_pkt_id"));
                    e5Var2.n(byteArrayExtra, b2.f19101i);
                    g.j.a.a.a.c.o("send a message: chid=" + stringExtra5 + ", packetId=" + intent.getStringExtra("ext_pkt_id"));
                    e5Var = e5Var2;
                }
            }
        }
        if (e5Var != null) {
            c(new x0(this, e5Var));
        }
    }

    private void b(boolean z) {
        this.f222a = SystemClock.elapsedRealtime();
        if (m159c()) {
            if (com.xiaomi.push.j0.p(this)) {
                c(new o(z));
                return;
            }
            c(new g(17, null));
        }
        a(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x00de A[Catch: Exception -> 0x00e4, TRY_LEAVE, TryCatch #0 {Exception -> 0x00e4, blocks: (B:100:0x00d8, B:102:0x00de), top: B:107:0x00d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void c() {
        String str;
        com.xiaomi.push.f1.c().z();
        n2.c(getApplicationContext()).d();
        com.xiaomi.push.service.b a2 = com.xiaomi.push.service.b.a(getApplicationContext());
        String b2 = a2.b();
        g.j.a.a.a.c.p("XMPushService", "region of cache is " + b2);
        String str2 = "";
        if (TextUtils.isEmpty(b2)) {
            String b3 = b();
            str = b3;
            b2 = a8.c(b3).name();
        } else {
            str = "";
        }
        String str3 = "CN";
        try {
            if (!TextUtils.isEmpty(b2) && m9.China.name().equals(b2)) {
                a2.e(b2, true);
                a2.g("CN", true);
            } else if (TextUtils.isEmpty(b2)) {
                b2 = m9.China.name();
                g.j.a.a.a.c.r("XMPushService", "after check, appRegion is ", b2, ", countryCode=", str);
                if (m9.China.name().equals(b2)) {
                    p5.c("cn.app.chat.xiaomi.net");
                }
                a(b2);
                if (m148h()) {
                    g.j.a.a.a.c.p("XMPushService", "-->postOnCreate(): try trigger connect now");
                    x1 x1Var = new x1(this, 11);
                    a(x1Var);
                    t2.j(new y1(this, x1Var));
                }
                if (r9.f()) {
                    return;
                }
                this.f234a.d(this);
                return;
            } else {
                if ("com.xiaomi.xmsf".equals(getPackageName())) {
                    str3 = "";
                } else {
                    str2 = m9.China.name();
                }
                a2.e(str2, true);
                a2.g(str3, true);
                b2 = str2;
            }
            if (r9.f()) {
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return;
        }
        str = str3;
        g.j.a.a.a.c.r("XMPushService", "after check, appRegion is ", b2, ", countryCode=", str);
        if (m9.China.name().equals(b2)) {
        }
        a(b2);
        if (m148h()) {
        }
    }

    private void c(Intent intent) {
        String stringExtra = intent.getStringExtra(m0.B);
        String stringExtra2 = intent.getStringExtra(m0.F);
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
        int length = parcelableArrayExtra.length;
        f6[] f6VarArr = new f6[length];
        intent.getBooleanExtra("ext_encrypt", true);
        for (int i2 = 0; i2 < parcelableArrayExtra.length; i2++) {
            f6VarArr[i2] = new f6((Bundle) parcelableArrayExtra[i2]);
            f6VarArr[i2] = (f6) a(f6VarArr[i2], stringExtra, stringExtra2);
            if (f6VarArr[i2] == null) {
                return;
            }
        }
        i0 c2 = i0.c();
        e5[] e5VarArr = new e5[length];
        for (int i3 = 0; i3 < length; i3++) {
            f6 f6Var = f6VarArr[i3];
            e5VarArr[i3] = e5.c(f6Var, c2.b(f6Var.m(), f6Var.q()).f19101i);
        }
        c(new d1(this, e5VarArr));
    }

    private void c(j jVar) {
        this.f235a.e(jVar);
    }

    private void c(boolean z) {
        try {
            if (r9.f()) {
                if (!z) {
                    sendBroadcast(new Intent("miui.intent.action.NETWORK_BLOCKED"));
                    return;
                }
                sendBroadcast(new Intent("miui.intent.action.NETWORK_CONNECTED"));
                for (com.xiaomi.push.service.s sVar : (com.xiaomi.push.service.s[]) this.f240a.toArray(new com.xiaomi.push.service.s[0])) {
                    sVar.a();
                }
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
        }
    }

    private void d() {
        NetworkInfo networkInfo;
        try {
            networkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            networkInfo = null;
        }
        n2.c(getApplicationContext()).f(networkInfo);
        if (networkInfo != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("network changed,");
            sb.append("[type: " + networkInfo.getTypeName() + "[" + networkInfo.getSubtypeName() + "], state: " + networkInfo.getState() + "/" + networkInfo.getDetailedState());
            g.j.a.a.a.c.p("XMPushService", sb.toString());
            NetworkInfo.State state = networkInfo.getState();
            if (state == NetworkInfo.State.SUSPENDED || state == NetworkInfo.State.UNKNOWN) {
                return;
            }
        } else {
            g.j.a.a.a.c.p("XMPushService", "network changed, no active network");
        }
        if (a5.e() != null) {
            a5.e().b();
        }
        u6.h(this);
        this.f225a.E();
        if (com.xiaomi.push.j0.p(this)) {
            if (m159c() && m146f()) {
                b(false);
            }
            if (!m159c() && !m160d()) {
                this.f235a.c(1);
                a(new e());
            }
            com.xiaomi.push.i2.b(this).d();
        } else {
            a(new g(2, null));
        }
        e();
    }

    /* JADX WARN: Removed duplicated region for block: B:1023:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:1029:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:1248:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void d(Intent intent) {
        String str;
        h2 h2Var;
        boolean z;
        int i2;
        String format;
        j pVar;
        int i3;
        String d2;
        SharedPreferences sharedPreferences;
        String str2;
        j z1Var;
        i0 c2 = i0.c();
        boolean z2 = true;
        int i4 = 0;
        if (m0.d.equalsIgnoreCase(intent.getAction()) || m0.f19132j.equalsIgnoreCase(intent.getAction())) {
            String stringExtra = intent.getStringExtra(m0.s);
            if (!TextUtils.isEmpty(intent.getStringExtra(m0.x))) {
                if (TextUtils.isEmpty(stringExtra)) {
                    str = "channel id is empty, do nothing!";
                    g.j.a.a.a.c.D(str);
                    return;
                }
                boolean m142a = m142a(stringExtra, intent);
                i0.b a2 = a(stringExtra, intent);
                if (com.xiaomi.push.j0.q(this)) {
                    if (m159c()) {
                        i0.c cVar = a2.f19105m;
                        if (cVar == i0.c.unbind) {
                            pVar = new b(a2);
                        } else if (m142a) {
                            pVar = new p(a2);
                        } else if (cVar == i0.c.binding) {
                            format = String.format("the client is binding. %1$s %2$s.", a2.f19100h, i0.b.e(a2.b));
                        } else if (cVar != i0.c.binded) {
                            return;
                        } else {
                            h2Var = this.f234a;
                            z = true;
                            i2 = 0;
                        }
                        c(pVar);
                    }
                    a(true);
                    return;
                }
                h2Var = this.f234a;
                z = false;
                i2 = 2;
                h2Var.h(this, a2, z, i2, null);
                return;
            }
            format = "security is empty. ignore.";
            g.j.a.a.a.c.o(format);
        } else if (m0.f19131i.equalsIgnoreCase(intent.getAction())) {
            String stringExtra2 = intent.getStringExtra(m0.B);
            String stringExtra3 = intent.getStringExtra(m0.s);
            String stringExtra4 = intent.getStringExtra(m0.p);
            g.j.a.a.a.c.o("Service called close channel chid = " + stringExtra3 + " res = " + i0.b.e(stringExtra4));
            if (TextUtils.isEmpty(stringExtra3)) {
                Iterator<String> it = c2.g(stringExtra2).iterator();
                while (it.hasNext()) {
                    a(it.next(), 2);
                }
            } else if (TextUtils.isEmpty(stringExtra4)) {
                a(stringExtra3, 2);
            } else {
                a(stringExtra3, stringExtra4, 2, null, null);
            }
        } else if (m0.f19127e.equalsIgnoreCase(intent.getAction())) {
            b(intent);
        } else if (m0.f19129g.equalsIgnoreCase(intent.getAction())) {
            c(intent);
        } else {
            if (m0.f19128f.equalsIgnoreCase(intent.getAction())) {
                g6 a3 = a(new e6(intent.getBundleExtra("ext_packet")), intent.getStringExtra(m0.B), intent.getStringExtra(m0.F));
                if (a3 == null) {
                    return;
                }
                pVar = new x0(this, e5.c(a3, c2.b(a3.m(), a3.q()).f19101i));
            } else if (!m0.f19130h.equalsIgnoreCase(intent.getAction())) {
                if (!m0.f19133k.equals(intent.getAction())) {
                    String str3 = null;
                    r3 = null;
                    i0.b b2 = null;
                    str3 = null;
                    if (m0.f19134l.equals(intent.getAction())) {
                        String stringExtra5 = intent.getStringExtra(m0.B);
                        List<String> g2 = c2.g(stringExtra5);
                        if (!g2.isEmpty()) {
                            String stringExtra6 = intent.getStringExtra(m0.s);
                            String stringExtra7 = intent.getStringExtra(m0.p);
                            if (TextUtils.isEmpty(stringExtra6)) {
                                stringExtra6 = g2.get(0);
                            }
                            if (TextUtils.isEmpty(stringExtra7)) {
                                Collection<i0.b> f2 = c2.f(stringExtra6);
                                if (f2 != null && !f2.isEmpty()) {
                                    b2 = f2.iterator().next();
                                }
                            } else {
                                b2 = c2.b(stringExtra6, stringExtra7);
                            }
                            if (b2 != null) {
                                if (intent.hasExtra(m0.z)) {
                                    b2.f19098f = intent.getStringExtra(m0.z);
                                }
                                if (intent.hasExtra(m0.A)) {
                                    b2.f19099g = intent.getStringExtra(m0.A);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        str2 = "open channel should be called first before update info, pkg=" + stringExtra5;
                    } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) || "android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                        if (!"android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction()) && m149i() && i4.e()) {
                                g.j.a.a.a.c.o("enter falldown mode, stop alarm.");
                                i4.a();
                                return;
                            }
                            return;
                        } else if (m149i()) {
                            return;
                        } else {
                            g.j.a.a.a.c.o("exit falldown mode, activate alarm.");
                            e();
                            if (m159c() || m160d()) {
                                return;
                            }
                            a(true);
                            return;
                        }
                    } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
                        if (p0.c(getApplicationContext()).d() && p0.c(getApplicationContext()).a() == 0) {
                            str2 = "register without being provisioned. " + intent.getStringExtra("mipush_app_package");
                        } else {
                            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                            String stringExtra8 = intent.getStringExtra("mipush_app_package");
                            boolean booleanExtra = intent.getBooleanExtra("mipush_env_chanage", false);
                            int intExtra = intent.getIntExtra("mipush_env_type", 1);
                            u2.a(this).h(stringExtra8);
                            if (!booleanExtra || "com.xiaomi.xmsf".equals(getPackageName())) {
                                a(byteArrayExtra, stringExtra8);
                                return;
                            }
                            z1Var = new z1(this, 14, intExtra, stringExtra8, byteArrayExtra);
                        }
                    } else if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                        String stringExtra9 = intent.getStringExtra("mipush_app_package");
                        byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                        boolean booleanExtra2 = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                        if (l2.b(byteArrayExtra2, stringExtra9)) {
                            format = "duplicate msg from: " + String.valueOf(stringExtra9);
                            g.j.a.a.a.c.o(format);
                        }
                        if ("com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                            u2.a(this).b(stringExtra9);
                            if (a8.j(getApplicationContext())) {
                                com.xiaomi.push.service.j.d(stringExtra9);
                            }
                        }
                        a(stringExtra9, byteArrayExtra2, booleanExtra2);
                        return;
                    } else if (!q0.a.equals(intent.getAction())) {
                        if (q0.b.equals(intent.getAction())) {
                            String stringExtra10 = intent.getStringExtra("data_cleared_pkg_name");
                            if (TextUtils.isEmpty(stringExtra10)) {
                                return;
                            }
                            try {
                                sharedPreferences = getSharedPreferences("pref_registered_pkg_names", 0);
                                if (sharedPreferences != null) {
                                    try {
                                        str3 = sharedPreferences.getString(stringExtra10, null);
                                    } catch (Throwable th) {
                                        th = th;
                                        g.j.a.a.a.c.o("Fail to get sp or appId : " + th);
                                        if (!TextUtils.isEmpty(str3)) {
                                        }
                                        u.f(this, stringExtra10);
                                        if (a8.j(getApplicationContext())) {
                                        }
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                sharedPreferences = null;
                            }
                            if (!TextUtils.isEmpty(str3)) {
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.remove(stringExtra10);
                                edit.commit();
                                if (com.xiaomi.push.service.n.U(this, stringExtra10)) {
                                    com.xiaomi.push.service.n.Y(this, stringExtra10);
                                }
                                com.xiaomi.push.service.n.x(this, stringExtra10);
                                a(stringExtra10, m8.f(com.xiaomi.push.service.k.m(stringExtra10, str3)), true);
                            }
                            u.f(this, stringExtra10);
                            if (a8.j(getApplicationContext())) {
                                return;
                            }
                            com.xiaomi.push.service.j.d(stringExtra10);
                            return;
                        } else if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(intent.getAction())) {
                            String stringExtra11 = intent.getStringExtra(m0.B);
                            int intExtra2 = intent.getIntExtra(m0.C, -2);
                            if (TextUtils.isEmpty(stringExtra11)) {
                                return;
                            }
                            if (intExtra2 >= -1) {
                                com.xiaomi.push.service.n.z(this, stringExtra11, intExtra2, intent.getIntExtra(m0.D, -1));
                                return;
                            } else {
                                com.xiaomi.push.service.n.B(this, stringExtra11, intent.getStringExtra(m0.H), intent.getStringExtra(m0.I));
                                return;
                            }
                        } else if ("com.xiaomi.mipush.CLEAR_HEADSUPNOTIFICATION".equals(intent.getAction())) {
                            String stringExtra12 = intent.getStringExtra(m0.B);
                            if (TextUtils.isEmpty(stringExtra12)) {
                                return;
                            }
                            com.xiaomi.push.service.n.R(this, stringExtra12);
                            return;
                        } else if ("com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(intent.getAction())) {
                            String stringExtra13 = intent.getStringExtra(m0.B);
                            String stringExtra14 = intent.getStringExtra(m0.G);
                            if (intent.hasExtra(m0.E)) {
                                int intExtra3 = intent.getIntExtra(m0.E, 0);
                                d2 = com.xiaomi.push.o0.d(stringExtra13 + intExtra3);
                                i4 = intExtra3;
                                z2 = false;
                            } else {
                                d2 = com.xiaomi.push.o0.d(stringExtra13);
                            }
                            if (!TextUtils.isEmpty(stringExtra13) && TextUtils.equals(stringExtra14, d2)) {
                                if (z2) {
                                    com.xiaomi.push.service.n.Y(this, stringExtra13);
                                    return;
                                } else {
                                    com.xiaomi.push.service.n.S(this, stringExtra13, i4);
                                    return;
                                }
                            }
                            str = "invalid notification for " + stringExtra13;
                            g.j.a.a.a.c.D(str);
                            return;
                        } else if ("com.xiaomi.mipush.DISABLE_PUSH".equals(intent.getAction())) {
                            String stringExtra15 = intent.getStringExtra("mipush_app_package");
                            if (!TextUtils.isEmpty(stringExtra15)) {
                                u2.a(this).d(stringExtra15);
                            }
                            if ("com.xiaomi.xmsf".equals(getPackageName())) {
                                return;
                            }
                            a(19, (Exception) null);
                            e();
                            stopSelf();
                            return;
                        } else if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                            String stringExtra16 = intent.getStringExtra("mipush_app_package");
                            byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
                            String stringExtra17 = intent.getStringExtra("mipush_app_id");
                            String stringExtra18 = intent.getStringExtra("mipush_app_token");
                            if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                                u2.a(this).f(stringExtra16);
                            }
                            if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                                u2.a(this).i(stringExtra16);
                                u2.a(this).j(stringExtra16);
                            }
                            if (byteArrayExtra3 == null) {
                                w2.b(this, stringExtra16, byteArrayExtra3, 70000003, "null payload");
                                return;
                            }
                            w2.f(stringExtra16, byteArrayExtra3);
                            a(new v2(this, stringExtra16, stringExtra17, stringExtra18, byteArrayExtra3));
                            if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction()) && this.f229a == null) {
                                this.f229a = new f();
                                registerReceiver(this.f229a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                                return;
                            }
                            return;
                        } else if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                            String stringExtra19 = intent.getStringExtra("mipush_app_package");
                            byte[] byteArrayExtra4 = intent.getByteArrayExtra("mipush_payload");
                            g7 g7Var = new g7();
                            try {
                                m8.e(g7Var, byteArrayExtra4);
                                a7.a(this).e(g7Var, stringExtra19);
                                return;
                            } catch (s8 e2) {
                                g.j.a.a.a.c.s(e2);
                                return;
                            }
                        } else if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
                            g.j.a.a.a.c.o("[Alarm] Service called on timer");
                            if (!m149i()) {
                                i4.d(false);
                                if (m146f()) {
                                    b(false);
                                }
                            } else if (i4.e()) {
                                g.j.a.a.a.c.o("enter falldown mode, stop alarm");
                                i4.a();
                            }
                            a aVar = this.f228a;
                            if (aVar != null) {
                                aVar.a();
                                return;
                            }
                            return;
                        } else if ("com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                            g.j.a.a.a.c.o("Service called on check alive.");
                            if (m146f()) {
                                b(false);
                                return;
                            }
                            return;
                        } else if ("com.xiaomi.mipush.thirdparty".equals(intent.getAction())) {
                            g.j.a.a.a.c.o("on thirdpart push :" + intent.getStringExtra("com.xiaomi.mipush.thirdparty_DESC"));
                            i4.c(this, intent.getIntExtra("com.xiaomi.mipush.thirdparty_LEVEL", 0));
                            return;
                        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                            d();
                            return;
                        } else if ("miui.net.wifi.DIGEST_INFORMATION_CHANGED".equals(intent.getAction())) {
                            a(intent);
                            return;
                        } else if ("com.xiaomi.xmsf.USE_INTELLIGENT_HB".equals(intent.getAction())) {
                            if (intent.getExtras() == null || (i3 = intent.getExtras().getInt("effectivePeriod", 0)) <= 0 || i3 > 604800) {
                                return;
                            }
                            n2.c(getApplicationContext()).e(i3);
                            return;
                        } else if ("action_cr_config".equals(intent.getAction())) {
                            boolean booleanExtra3 = intent.getBooleanExtra("action_cr_event_switch", false);
                            long longExtra = intent.getLongExtra("action_cr_event_frequency", TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC);
                            boolean booleanExtra4 = intent.getBooleanExtra("action_cr_perf_switch", false);
                            long longExtra2 = intent.getLongExtra("action_cr_perf_frequency", TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC);
                            boolean booleanExtra5 = intent.getBooleanExtra("action_cr_event_en", true);
                            long longExtra3 = intent.getLongExtra("action_cr_max_file_size", 1048576L);
                            a.C0842a b3 = g.j.b.a.a.b();
                            b3.l(booleanExtra3);
                            b3.k(longExtra);
                            b3.o(booleanExtra4);
                            b3.n(longExtra2);
                            b3.i(com.xiaomi.push.t0.b(getApplicationContext()));
                            b3.j(booleanExtra5);
                            b3.m(longExtra3);
                            g.j.b.a.a h2 = b3.h(getApplicationContext());
                            if ("com.xiaomi.xmsf".equals(getPackageName()) || longExtra <= 0 || longExtra2 <= 0 || longExtra3 <= 0) {
                                return;
                            }
                            c4.l(getApplicationContext(), h2);
                            return;
                        } else if (!"action_help_ping".equals(intent.getAction())) {
                            if ("action_aw_app_logic".equals(intent.getAction())) {
                                e(intent);
                                return;
                            } else if (m0.f19135m.equals(intent.getAction())) {
                                m2.b(getApplicationContext(), intent);
                                return;
                            } else if (m0.f19136n.equals(intent.getAction())) {
                                String stringExtra20 = intent.getStringExtra("ext_downward_pkt_id");
                                if (TextUtils.isEmpty(stringExtra20)) {
                                    return;
                                }
                                t0.a().f(stringExtra20, intent.getLongExtra("ext_app_receive_time", 0L));
                                return;
                            } else {
                                return;
                            }
                        } else {
                            boolean booleanExtra6 = intent.getBooleanExtra("extra_help_ping_switch", false);
                            int intExtra4 = intent.getIntExtra("extra_help_ping_frequency", 0);
                            if (intExtra4 >= 0 && intExtra4 < 30) {
                                g.j.a.a.a.c.B("aw_ping: frquency need > 30s.");
                                intExtra4 = 30;
                            }
                            boolean z3 = intExtra4 >= 0 ? booleanExtra6 : false;
                            g.j.a.a.a.c.o("aw_ping: receive a aw_ping message. switch: " + z3 + " frequency: " + intExtra4);
                            if (!z3 || intExtra4 <= 0 || "com.xiaomi.xmsf".equals(getPackageName())) {
                                return;
                            }
                            a(intent, intExtra4);
                            return;
                        }
                    } else {
                        String stringExtra21 = intent.getStringExtra("uninstall_pkg_name");
                        if (stringExtra21 == null || TextUtils.isEmpty(stringExtra21.trim())) {
                            return;
                        }
                        try {
                            PackageInfo packageInfo = getPackageManager().getPackageInfo(stringExtra21, 0);
                            if (packageInfo == null || packageInfo.applicationInfo == null || !z6.k(this, packageInfo.packageName)) {
                                z2 = false;
                            } else {
                                g.j.a.a.a.c.o("dual space's app uninstalled " + stringExtra21);
                            }
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                        if (!"com.xiaomi.channel".equals(stringExtra21) || i0.c().f("1").isEmpty() || !z2) {
                            SharedPreferences sharedPreferences2 = getSharedPreferences("pref_registered_pkg_names", 0);
                            String string = sharedPreferences2.getString(stringExtra21, null);
                            if (TextUtils.isEmpty(string) || !z2) {
                                return;
                            }
                            SharedPreferences.Editor edit2 = sharedPreferences2.edit();
                            edit2.remove(stringExtra21);
                            edit2.commit();
                            if (com.xiaomi.push.service.n.U(this, stringExtra21)) {
                                com.xiaomi.push.service.n.Y(this, stringExtra21);
                            }
                            com.xiaomi.push.service.n.x(this, stringExtra21);
                            u.f(getApplicationContext(), stringExtra21);
                            if (!m159c() || string == null) {
                                return;
                            }
                            try {
                                com.xiaomi.push.service.k.i(this, com.xiaomi.push.service.k.c(stringExtra21, string));
                                g.j.a.a.a.c.o("uninstall " + stringExtra21 + " msg sent");
                                return;
                            } catch (a6 e3) {
                                g.j.a.a.a.c.D("Fail to send Message: " + e3.getMessage());
                                a(10, e3);
                                return;
                            }
                        }
                        a("1", 0);
                        str2 = "close the miliao channel as the app is uninstalled.";
                    }
                    g.j.a.a.a.c.o(str2);
                    return;
                }
                String stringExtra22 = intent.getStringExtra(m0.s);
                String stringExtra23 = intent.getStringExtra(m0.p);
                if (stringExtra22 == null) {
                    return;
                }
                g.j.a.a.a.c.o("request reset connection from chid = " + stringExtra22);
                i0.b b4 = i0.c().b(stringExtra22, stringExtra23);
                if (b4 == null || !b4.f19101i.equals(intent.getStringExtra(m0.x)) || b4.f19105m != i0.c.binded) {
                    return;
                }
                o5 m151a = m151a();
                if (m151a != null && m151a.r(SystemClock.elapsedRealtime() - 15000)) {
                    return;
                }
                z1Var = new q();
                c(z1Var);
                return;
            } else {
                g6 a4 = a(new i6(intent.getBundleExtra("ext_packet")), intent.getStringExtra(m0.B), intent.getStringExtra(m0.F));
                if (a4 == null) {
                    return;
                }
                pVar = new x0(this, e5.c(a4, c2.b(a4.m(), a4.q()).f19101i));
            }
            c(pVar);
        }
    }

    public void e() {
        if (!m154a()) {
            i4.a();
        } else if (i4.e()) {
        } else {
            i4.d(true);
        }
    }

    private void e(Intent intent) {
        int i2;
        try {
            r3.b(getApplicationContext()).j(new o0());
            String stringExtra = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra == null) {
                return;
            }
            c8 c8Var = new c8();
            m8.e(c8Var, byteArrayExtra);
            String b2 = c8Var.b();
            Map<String, String> m35a = c8Var.m35a();
            if (m35a != null) {
                String str = m35a.get("extra_help_aw_info");
                String str2 = m35a.get("extra_aw_app_online_cmd");
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                try {
                    i2 = Integer.parseInt(str2);
                } catch (NumberFormatException unused) {
                    i2 = 0;
                }
                if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(b2) || TextUtils.isEmpty(str)) {
                    return;
                }
                r3.b(getApplicationContext()).f(this, str, i2, stringExtra, b2);
            }
        } catch (s8 e2) {
            g.j.a.a.a.c.D("aw_logic: translate fail. " + e2.getMessage());
        }
    }

    /* renamed from: e */
    public static boolean m145e() {
        return b;
    }

    public void f() {
        String str;
        o5 o5Var = this.f226a;
        if (o5Var == null || !o5Var.B()) {
            o5 o5Var2 = this.f226a;
            if (o5Var2 == null || !o5Var2.D()) {
                this.f227a.i(com.xiaomi.push.j0.g(this));
                g();
                if (this.f226a == null) {
                    i0.c().i(this);
                    c(false);
                    return;
                }
                return;
            }
            str = "try to connect while is connected.";
        } else {
            str = "try to connect while connecting.";
        }
        g.j.a.a.a.c.D(str);
    }

    /* renamed from: f */
    private boolean m146f() {
        if (SystemClock.elapsedRealtime() - this.f222a < Final.HALF_MINUTE) {
            return false;
        }
        return com.xiaomi.push.j0.r(this);
    }

    private void g() {
        try {
            this.f225a.k(this.f237a, new q1(this));
            this.f225a.R();
            this.f226a = this.f225a;
        } catch (a6 e2) {
            g.j.a.a.a.c.q("fail to create Slim connection", e2);
            this.f225a.v(3, e2);
        }
    }

    /* renamed from: g */
    public boolean m147g() {
        return "com.xiaomi.xmsf".equals(getPackageName()) && Settings.System.getInt(getContentResolver(), "power_supersave_mode_open", 0) == 1;
    }

    private void h() {
    }

    /* renamed from: h */
    private boolean m148h() {
        boolean z;
        String packageName = getPackageName();
        if ("com.xiaomi.xmsf".equals(packageName)) {
            g.j.a.a.a.c.o("current sdk expect region is cn");
            z = m9.China.name().equals(com.xiaomi.push.service.b.a(getApplicationContext()).b());
        } else {
            z = !u2.a(this).e(packageName);
        }
        if (!z) {
            g.j.a.a.a.c.r("XMPushService", "-->isPushEnabled(): isEnabled=", Boolean.valueOf(z), ", package=", packageName, ", region=", com.xiaomi.push.service.b.a(getApplicationContext()).b());
        }
        return z;
    }

    private void i() {
        synchronized (this.f239a) {
            this.f239a.clear();
        }
    }

    /* renamed from: i */
    private boolean m149i() {
        return getApplicationContext().getPackageName().equals("com.xiaomi.xmsf") && j() && !z6.p(this) && !z6.j(getApplicationContext());
    }

    private boolean j() {
        int intValue = Integer.valueOf(String.format("%tH", new Date())).intValue();
        int i2 = this.a;
        int i3 = this.f242b;
        if (i2 > i3) {
            if (intValue >= i2 || intValue < i3) {
                return true;
            }
        } else if (i2 < i3 && intValue >= i2 && intValue < i3) {
            return true;
        }
        return false;
    }

    private boolean k() {
        if (TextUtils.equals(getPackageName(), "com.xiaomi.xmsf")) {
            return false;
        }
        return b0.d(this).m(h7.ForegroundServiceSwitch.a(), false);
    }

    /* renamed from: a */
    public int m150a() {
        if (this.f19029c < 0) {
            this.f19029c = y4.b(this, "com.xiaomi.xmsf");
        }
        return this.f19029c;
    }

    /* renamed from: a */
    public o5 m151a() {
        return this.f226a;
    }

    /* renamed from: a */
    public h2 m152a() {
        return new h2();
    }

    /* renamed from: a */
    public void m153a() {
        if (SystemClock.elapsedRealtime() - this.f222a >= u5.a() && com.xiaomi.push.j0.r(this)) {
            b(true);
        }
    }

    public void a(int i2) {
        this.f235a.c(i2);
    }

    public void a(int i2, Exception exc) {
        StringBuilder sb = new StringBuilder();
        sb.append("disconnect ");
        sb.append(hashCode());
        sb.append(", ");
        o5 o5Var = this.f226a;
        sb.append(o5Var == null ? null : Integer.valueOf(o5Var.hashCode()));
        g.j.a.a.a.c.o(sb.toString());
        o5 o5Var2 = this.f226a;
        if (o5Var2 != null) {
            o5Var2.v(i2, exc);
            this.f226a = null;
        }
        a(7);
        a(4);
        i0.c().j(this, i2);
    }

    public void a(e5 e5Var) {
        o5 o5Var = this.f226a;
        if (o5Var == null) {
            throw new a6("try send msg while connection is null.");
        }
        o5Var.w(e5Var);
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var) {
        g.j.a.a.a.c.B("begin to connect...");
        a5.e().a(o5Var);
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var, int i2, Exception exc) {
        a5.e().a(o5Var, i2, exc);
        if (m149i()) {
            return;
        }
        a(false);
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var, Exception exc) {
        a5.e().a(o5Var, exc);
        c(false);
        if (m149i()) {
            return;
        }
        a(false);
    }

    public void a(j jVar) {
        a(jVar, 0L);
    }

    public void a(j jVar, long j2) {
        try {
            this.f235a.f(jVar, j2);
        } catch (IllegalStateException e2) {
            g.j.a.a.a.c.o("can't execute job err = " + e2.getMessage());
        }
    }

    public void a(n nVar) {
        synchronized (this.f239a) {
            this.f239a.add(nVar);
        }
    }

    public void a(i0.b bVar) {
        if (bVar != null) {
            long a2 = bVar.a();
            g.j.a.a.a.c.o("schedule rebind job in " + (a2 / 1000));
            a(new b(bVar), a2);
        }
    }

    public void a(String str, String str2, int i2, String str3, String str4) {
        i0.b b2 = i0.c().b(str, str2);
        if (b2 != null) {
            a(new s(b2, i2, str4, str3));
        }
        i0.c().n(str, str2);
    }

    public void a(String str, byte[] bArr, boolean z) {
        Collection<i0.b> f2 = i0.c().f("5");
        if (f2.isEmpty()) {
            if (!z) {
                return;
            }
        } else if (f2.iterator().next().f19105m == i0.c.binded) {
            a(new o1(this, 4, str, bArr));
            return;
        } else if (!z) {
            return;
        }
        w2.f(str, bArr);
    }

    public void a(boolean z) {
        this.f236a.c(z);
    }

    public void a(byte[] bArr, String str) {
        if (bArr == null) {
            w2.b(this, str, bArr, 70000003, "null payload");
            g.j.a.a.a.c.o("register request without payload");
            return;
        }
        y7 y7Var = new y7();
        try {
            m8.e(y7Var, bArr);
            if (y7Var.a == c7.Registration) {
                d8 d8Var = new d8();
                try {
                    m8.e(d8Var, y7Var.m191a());
                    a(new v2(this, y7Var.b(), d8Var.b(), d8Var.c(), bArr));
                    d4.a(getApplicationContext()).g(y7Var.b(), "E100003", d8Var.a(), 6002, null);
                } catch (s8 e2) {
                    g.j.a.a.a.c.D("app register error. " + e2);
                    w2.b(this, str, bArr, 70000003, " data action error.");
                }
            } else {
                w2.b(this, str, bArr, 70000003, " registration action required.");
                g.j.a.a.a.c.o("register request with invalid payload");
            }
        } catch (s8 e3) {
            g.j.a.a.a.c.D("app register fail. " + e3);
            w2.b(this, str, bArr, 70000003, " data container error.");
        }
    }

    public void a(e5[] e5VarArr) {
        o5 o5Var = this.f226a;
        if (o5Var == null) {
            throw new a6("try send msg while connection is null.");
        }
        o5Var.p(e5VarArr);
    }

    /* renamed from: a */
    public boolean m154a() {
        boolean p2 = com.xiaomi.push.j0.p(this);
        boolean z = i0.c().a() > 0;
        boolean z2 = !m158b();
        boolean m148h = m148h();
        boolean z3 = !m147g();
        boolean z4 = p2 && z && z2 && m148h && z3;
        if (!z4) {
            g.j.a.a.a.c.E(String.format("not conn, net=%s;cnt=%s;!dis=%s;enb=%s;!spm=%s;", Boolean.valueOf(p2), Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(m148h), Boolean.valueOf(z3)));
        }
        return z4;
    }

    /* renamed from: a */
    public boolean m155a(int i2) {
        return this.f235a.h(i2);
    }

    /* renamed from: b */
    public h2 m156b() {
        return this.f234a;
    }

    /* renamed from: b */
    public void m157b() {
        n2.c(getApplicationContext()).u();
        Iterator it = new ArrayList(this.f239a).iterator();
        while (it.hasNext()) {
            ((n) it.next()).a();
        }
    }

    @Override // com.xiaomi.push.r5
    public void b(o5 o5Var) {
        a5.e().b(o5Var);
        c(true);
        this.f236a.b();
        if (!i4.e() && !m149i()) {
            g.j.a.a.a.c.o("reconnection successful, reactivate alarm.");
            i4.d(true);
        }
        Iterator<i0.b> it = i0.c().e().iterator();
        while (it.hasNext()) {
            a(new b(it.next()));
        }
        if (this.f241a || !a8.j(getApplicationContext())) {
            return;
        }
        com.xiaomi.push.i.b(getApplicationContext()).g(new r1(this));
    }

    public void b(j jVar) {
        this.f235a.d(jVar.f19161g, jVar);
    }

    /* renamed from: b */
    public boolean m158b() {
        try {
            Class<?> c2 = r9.c(this, "miui.os.Build");
            Field field = c2.getField("IS_CM_CUSTOMIZATION_TEST");
            Field field2 = c2.getField("IS_CU_CUSTOMIZATION_TEST");
            Field field3 = c2.getField("IS_CT_CUSTOMIZATION_TEST");
            if (!field.getBoolean(null) && !field2.getBoolean(null)) {
                if (!field3.getBoolean(null)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: c */
    public boolean m159c() {
        o5 o5Var = this.f226a;
        return o5Var != null && o5Var.D();
    }

    /* renamed from: d */
    public boolean m160d() {
        o5 o5Var = this.f226a;
        return o5Var != null && o5Var.B();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f224a.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        String[] split;
        super.onCreate();
        g.j.a.a.a.c.l(getApplicationContext());
        r9.e(this);
        s2 b2 = t2.b(this);
        if (b2 != null) {
            com.xiaomi.push.b.b(b2.f19190g);
        }
        if (a8.j(getApplicationContext())) {
            HandlerThread handlerThread = new HandlerThread("hb-alarm");
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper());
            this.f228a = new a(this, null);
            l9.c(this, this.f228a, new IntentFilter(m0.o), "com.xiaomi.xmsf.permission.MIPUSH_RECEIVE", handler, 4);
            b = true;
            handler.post(new s1(this));
        }
        this.f224a = new Messenger(new t1(this));
        n0.d(this);
        u1 u1Var = new u1(this, null, R2.dimen.abc_button_padding_horizontal_material, "xiaomi.com", null);
        this.f227a = u1Var;
        u1Var.e(true);
        this.f225a = new l5(this, this.f227a);
        this.f234a = m152a();
        i4.b(this);
        this.f225a.i(this);
        this.f233a = new g0(this);
        this.f236a = new s0(this);
        new i2().b();
        a5.f().j(this);
        this.f235a = new o2("Connection Controller Thread");
        i0 c2 = i0.c();
        c2.o();
        c2.k(new v1(this));
        if (k()) {
            h();
        }
        a7.a(this).d(new q2(this), "UPLOADER_PUSH_CHANNEL");
        a(new w6(this));
        a(new l1(this));
        if (a8.j(this)) {
            a(new h0());
        }
        a(new h());
        this.f240a.add(b1.c(this));
        if (m148h()) {
            this.f229a = new f();
            registerReceiver(this.f229a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (a8.j(getApplicationContext())) {
            this.f232a = new t();
            l9.c(this, this.f232a, new IntentFilter("miui.net.wifi.DIGEST_INFORMATION_CHANGED"), "miui.net.wifi.permission.ACCESS_WIFI_DIGEST_INFO", null, 2);
            k kVar = new k();
            this.f230a = kVar;
            l9.c(this, kVar, new IntentFilter("com.xiaomi.xmsf.USE_INTELLIGENT_HB"), "com.xiaomi.xmsf.permission.INTELLIGENT_HB", null, 2);
        }
        if ("com.xiaomi.xmsf".equals(getPackageName())) {
            Uri uriFor = Settings.System.getUriFor("power_supersave_mode_open");
            if (uriFor != null) {
                this.f223a = new w1(this, new Handler(Looper.getMainLooper()));
                try {
                    getContentResolver().registerContentObserver(uriFor, false, this.f223a);
                } catch (Throwable th) {
                    g.j.a.a.a.c.D("register super-power-mode observer err:" + th.getMessage());
                }
            }
            int[] m143a = m143a();
            if (m143a != null) {
                this.f231a = new r();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                registerReceiver(this.f231a, intentFilter);
                this.a = m143a[0];
                this.f242b = m143a[1];
                g.j.a.a.a.c.o("falldown initialized: " + this.a + DYConstants.DY_REGEX_COMMA + this.f242b);
            }
        }
        com.xiaomi.push.p1.d(this, this.f225a);
        com.xiaomi.push.y1.b(this, this.f225a);
        String str = "";
        if (b2 != null) {
            try {
                if (!TextUtils.isEmpty(b2.a) && (split = b2.a.split(DYConstants.DY_REGEX_AT)) != null && split.length > 0) {
                    str = split[0];
                }
            } catch (Exception unused) {
            }
        }
        com.xiaomi.push.g2.a(this);
        g.j.a.a.a.c.E("XMPushService created. pid=" + Process.myPid() + ", uid=" + Process.myUid() + ", vc=" + y4.b(getApplicationContext(), getPackageName()) + ", uuid=" + str);
    }

    @Override // android.app.Service
    public void onDestroy() {
        f fVar = this.f229a;
        if (fVar != null) {
            a(fVar);
            this.f229a = null;
        }
        t tVar = this.f232a;
        if (tVar != null) {
            a(tVar);
            this.f232a = null;
        }
        k kVar = this.f230a;
        if (kVar != null) {
            a(kVar);
            this.f230a = null;
        }
        r rVar = this.f231a;
        if (rVar != null) {
            a(rVar);
            this.f231a = null;
        }
        a aVar = this.f228a;
        if (aVar != null) {
            a(aVar);
            this.f228a = null;
        }
        if ("com.xiaomi.xmsf".equals(getPackageName()) && this.f223a != null) {
            try {
                getContentResolver().unregisterContentObserver(this.f223a);
            } catch (Throwable th) {
                g.j.a.a.a.c.D("unregister super-power-mode err:" + th.getMessage());
            }
        }
        this.f240a.clear();
        this.f235a.i();
        a(new p1(this, 2));
        a(new l());
        i0.c().o();
        i0.c().j(this, 15);
        i0.c().h();
        this.f225a.x(this);
        z0.f().i();
        i4.a();
        i();
        com.xiaomi.push.p1.i(this, this.f225a);
        com.xiaomi.push.y1.f(this, this.f225a);
        super.onDestroy();
        g.j.a.a.a.c.o("Service destroyed");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i2) {
        String format;
        i iVar;
        long currentTimeMillis = System.currentTimeMillis();
        if (intent == null) {
            g.j.a.a.a.c.D("onStart() with intent NULL");
        } else {
            try {
                String stringExtra = intent.getStringExtra(m0.s);
                String stringExtra2 = intent.getStringExtra(m0.B);
                String stringExtra3 = intent.getStringExtra("mipush_app_package");
                if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && !"miui.net.wifi.DIGEST_INFORMATION_CHANGED".equals(intent.getAction())) {
                    format = String.format("onStart() with intent.Action = %s, chid = %s, pkg = %s|%s", intent.getAction(), stringExtra, stringExtra2, stringExtra3);
                    g.j.a.a.a.c.p("XMPushService", format);
                }
                format = String.format("onStart() with intent.Action = %s, chid = %s, pkg = %s|%s, intent = %s", intent.getAction(), stringExtra, stringExtra2, stringExtra3, a8.e(intent));
                g.j.a.a.a.c.p("XMPushService", format);
            } catch (Throwable th) {
                g.j.a.a.a.c.D("onStart() cause error: " + th.getMessage());
                return;
            }
        }
        if (intent != null && intent.getAction() != null) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                if (this.f235a.g()) {
                    g.j.a.a.a.c.D("ERROR, the job controller is blocked.");
                    i0.c().j(this, 14);
                    stopSelf();
                } else {
                    iVar = new i(intent);
                    a(iVar);
                }
            } else if (!"com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                iVar = new i(intent);
                a(iVar);
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 50) {
            g.j.a.a.a.c.B("[Prefs] spend " + currentTimeMillis2 + " ms, too more times.");
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        onStart(intent, i3);
        return 1;
    }
}
