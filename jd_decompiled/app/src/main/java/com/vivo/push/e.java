package com.vivo.push;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.vivo.push.sdk.PushMessageCallback;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.VivoPushException;
import com.vivo.push.util.t;
import com.vivo.push.util.w;
import com.vivo.push.util.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes11.dex */
public final class e {
    private static volatile e a;

    /* renamed from: h */
    private Context f18273h;

    /* renamed from: j */
    private com.vivo.push.util.b f18275j;

    /* renamed from: k */
    private String f18276k;

    /* renamed from: l */
    private String f18277l;
    private Boolean o;
    private Long p;
    private boolean q;
    private int s;
    private long b = -1;

    /* renamed from: c */
    private long f18269c = -1;
    private long d = -1;

    /* renamed from: e */
    private long f18270e = -1;

    /* renamed from: f */
    private long f18271f = -1;

    /* renamed from: g */
    private long f18272g = -1;

    /* renamed from: i */
    private boolean f18274i = true;

    /* renamed from: m */
    private SparseArray<a> f18278m = new SparseArray<>();

    /* renamed from: n */
    private int f18279n = 0;
    private IPushClientFactory r = new d();

    private e() {
    }

    public void m() {
        this.f18277l = null;
        this.f18275j.b("APP_ALIAS");
    }

    private boolean n() {
        if (this.o == null) {
            this.o = Boolean.valueOf(l() >= 1230 && z.d(this.f18273h));
        }
        return this.o.booleanValue();
    }

    public final boolean d() {
        if (this.f18273h == null) {
            com.vivo.push.util.p.d("PushClientManager", "support:context is null");
            return false;
        }
        Boolean valueOf = Boolean.valueOf(n());
        this.o = valueOf;
        return valueOf.booleanValue();
    }

    public final boolean e() {
        return this.q;
    }

    public final String f() {
        if (!TextUtils.isEmpty(this.f18276k)) {
            return this.f18276k;
        }
        com.vivo.push.util.b bVar = this.f18275j;
        String b = bVar != null ? bVar.b("APP_TOKEN", (String) null) : "";
        c(b);
        return b;
    }

    public final boolean g() {
        return this.f18274i;
    }

    public final Context h() {
        return this.f18273h;
    }

    public final void i() {
        this.f18275j.a();
    }

    public final String j() {
        return this.f18277l;
    }

    public final int k() {
        return this.s;
    }

    public final long l() {
        Context context = this.f18273h;
        if (context == null) {
            return -1L;
        }
        if (this.p == null) {
            this.p = Long.valueOf(z.a(context));
        }
        return this.p.longValue();
    }

    public void e(String str) {
        m.a(new k(this, str));
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
    }

    public final void b() throws VivoPushException {
        Context context = this.f18273h;
        if (context != null) {
            z.b(context);
        }
    }

    public final List<String> c() {
        String b = this.f18275j.b("APP_TAGS", (String) null);
        ArrayList arrayList = new ArrayList();
        try {
        } catch (JSONException unused) {
            this.f18275j.b("APP_TAGS");
            arrayList.clear();
            com.vivo.push.util.p.d("PushClientManager", "getTags error");
        }
        if (TextUtils.isEmpty(b)) {
            return arrayList;
        }
        Iterator<String> keys = new JSONObject(b).keys();
        while (keys.hasNext()) {
            arrayList.add(keys.next());
        }
        return arrayList;
    }

    /* loaded from: classes11.dex */
    public static class a {
        private IPushActionListener a;
        private com.vivo.push.b.c b;

        /* renamed from: c */
        private IPushActionListener f18280c;
        private Runnable d;

        /* renamed from: e */
        private Object[] f18281e;

        public a(com.vivo.push.b.c cVar, IPushActionListener iPushActionListener) {
            this.b = cVar;
            this.a = iPushActionListener;
        }

        public final void a(int i2, Object... objArr) {
            this.f18281e = objArr;
            IPushActionListener iPushActionListener = this.f18280c;
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(i2);
            }
            IPushActionListener iPushActionListener2 = this.a;
            if (iPushActionListener2 != null) {
                iPushActionListener2.onStateChanged(i2);
            }
        }

        public final Object[] b() {
            return this.f18281e;
        }

        public final void a(Runnable runnable) {
            this.d = runnable;
        }

        public final void a() {
            Runnable runnable = this.d;
            if (runnable == null) {
                com.vivo.push.util.p.a("PushClientManager", "task is null");
            } else {
                runnable.run();
            }
        }

        public final void a(IPushActionListener iPushActionListener) {
            this.f18280c = iPushActionListener;
        }
    }

    public synchronized a d(String str) {
        if (str != null) {
            try {
                int parseInt = Integer.parseInt(str);
                a aVar = this.f18278m.get(parseInt);
                this.f18278m.delete(parseInt);
                return aVar;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final void b(List<String> list) {
        JSONObject jSONObject;
        try {
            if (list.size() <= 0) {
                return;
            }
            String b = this.f18275j.b("APP_TAGS", (String) null);
            if (TextUtils.isEmpty(b)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(b);
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONObject.remove(it.next());
            }
            String jSONObject2 = jSONObject.toString();
            if (TextUtils.isEmpty(jSONObject2)) {
                this.f18275j.b("APP_TAGS");
            } else {
                this.f18275j.a("APP_TAGS", jSONObject2);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.f18275j.b("APP_TAGS");
        }
    }

    public final synchronized void a(Context context) {
        if (this.f18273h == null) {
            this.f18273h = ContextDelegate.getContext(context);
            this.q = t.c(context, context.getPackageName());
            w.b().a(this.f18273h);
            a(new com.vivo.push.b.g());
            com.vivo.push.util.b bVar = new com.vivo.push.util.b();
            this.f18275j = bVar;
            bVar.a(this.f18273h, "com.vivo.push_preferences.appconfig_v1");
            this.f18276k = f();
            this.f18277l = this.f18275j.b("APP_ALIAS", (String) null);
        }
    }

    public final void c(List<String> list) {
        if (list.contains(this.f18277l)) {
            m();
        }
    }

    private void c(String str) {
        m.c(new f(this, str));
    }

    public final void a(List<String> list) {
        JSONObject jSONObject;
        try {
            if (list.size() <= 0) {
                return;
            }
            String b = this.f18275j.b("APP_TAGS", (String) null);
            if (TextUtils.isEmpty(b)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(b);
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONObject.put(it.next(), System.currentTimeMillis());
            }
            String jSONObject2 = jSONObject.toString();
            if (TextUtils.isEmpty(jSONObject2)) {
                this.f18275j.b("APP_TAGS");
            } else {
                this.f18275j.a("APP_TAGS", jSONObject2);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.f18275j.b("APP_TAGS");
        }
    }

    public final void b(IPushActionListener iPushActionListener) {
        if (this.f18273h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if ("".equals(this.f18276k)) {
            iPushActionListener.onStateChanged(0);
        } else if (!a(this.f18269c)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
        } else {
            this.f18269c = SystemClock.elapsedRealtime();
            String packageName = this.f18273h.getPackageName();
            a aVar = null;
            if (this.f18273h != null) {
                com.vivo.push.b.b bVar = new com.vivo.push.b.b(false, packageName);
                bVar.d();
                bVar.e();
                bVar.g();
                bVar.a(100);
                if (this.q) {
                    if (n()) {
                        aVar = new a(bVar, iPushActionListener);
                        String a2 = a(aVar);
                        bVar.b(a2);
                        aVar.a(new j(this, bVar, a2));
                    } else if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                    }
                } else if (bVar.a(this.f18273h) == 2) {
                    aVar = a(bVar, iPushActionListener);
                } else {
                    a(bVar);
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(0);
                    }
                }
            } else if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            if (aVar == null) {
                return;
            }
            aVar.a(new i(this));
            aVar.a();
        }
    }

    public final void a(String str) {
        this.f18276k = str;
        this.f18275j.a("APP_TOKEN", str);
    }

    public final void a(boolean z) {
        this.f18274i = z;
    }

    public final void a(IPushActionListener iPushActionListener) {
        if (this.f18273h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        String f2 = f();
        this.f18276k = f2;
        if (!TextUtils.isEmpty(f2)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
        } else if (!a(this.b)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
        } else {
            this.b = SystemClock.elapsedRealtime();
            String packageName = this.f18273h.getPackageName();
            a aVar = null;
            if (this.f18273h != null) {
                com.vivo.push.b.b bVar = new com.vivo.push.b.b(true, packageName);
                bVar.g();
                bVar.d();
                bVar.e();
                bVar.a(100);
                if (this.q) {
                    if (n()) {
                        aVar = a(bVar, iPushActionListener);
                    } else if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                    }
                } else if (bVar.a(this.f18273h) == 2) {
                    aVar = a(bVar, iPushActionListener);
                } else {
                    a(bVar);
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(0);
                    }
                }
            } else if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            if (aVar == null) {
                return;
            }
            aVar.a(new g(this, aVar));
            aVar.a();
        }
    }

    public final void b(String str, IPushActionListener iPushActionListener) {
        if (this.f18273h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if (TextUtils.isEmpty(this.f18277l)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            com.vivo.push.b.a aVar = new com.vivo.push.b.a(false, this.f18273h.getPackageName(), arrayList);
            aVar.a(100);
            if (this.q) {
                if (!n()) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                        return;
                    }
                    return;
                } else if (!a(this.f18270e)) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(1002);
                        return;
                    }
                    return;
                } else {
                    this.f18270e = SystemClock.elapsedRealtime();
                    String a2 = a(new a(aVar, iPushActionListener));
                    aVar.b(a2);
                    if (TextUtils.isEmpty(this.f18276k)) {
                        a(a2, 30001);
                        return;
                    } else if (TextUtils.isEmpty(str)) {
                        a(a2, 30002);
                        return;
                    } else if (str.length() > 70) {
                        a(a2, IMediaPlayer.MEDIA_INFO_JD_LOOP_COMPLETED);
                        return;
                    } else {
                        a(aVar);
                        e(a2);
                        return;
                    }
                }
            }
            a(aVar);
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
        }
    }

    private a a(com.vivo.push.b.b bVar, IPushActionListener iPushActionListener) {
        a aVar = new a(bVar, iPushActionListener);
        String a2 = a(aVar);
        bVar.b(a2);
        aVar.a(new h(this, bVar, a2));
        return aVar;
    }

    public final void a(String str, int i2, Object... objArr) {
        a d = d(str);
        if (d != null) {
            d.a(i2, objArr);
        } else {
            com.vivo.push.util.p.d("PushClientManager", "notifyApp token is null");
        }
    }

    public final void a(String str, IPushActionListener iPushActionListener) {
        if (this.f18273h == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
        } else if (!TextUtils.isEmpty(this.f18277l) && this.f18277l.equals(str)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            com.vivo.push.b.a aVar = new com.vivo.push.b.a(true, this.f18273h.getPackageName(), arrayList);
            aVar.a(100);
            if (this.q) {
                if (!n()) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(101);
                        return;
                    }
                    return;
                } else if (!a(this.d)) {
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(1002);
                        return;
                    }
                    return;
                } else {
                    this.d = SystemClock.elapsedRealtime();
                    String a2 = a(new a(aVar, iPushActionListener));
                    aVar.b(a2);
                    if (TextUtils.isEmpty(this.f18276k)) {
                        a(a2, 30001);
                        return;
                    } else if (TextUtils.isEmpty(str)) {
                        a(a2, 30002);
                        return;
                    } else if (str.length() > 70) {
                        a(a2, IMediaPlayer.MEDIA_INFO_JD_LOOP_COMPLETED);
                        return;
                    } else {
                        a(aVar);
                        e(a2);
                        return;
                    }
                }
            }
            a(aVar);
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
        }
    }

    public final void b(String str) {
        this.f18277l = str;
        this.f18275j.a("APP_ALIAS", str);
    }

    public final void b(ArrayList<String> arrayList, IPushActionListener iPushActionListener) {
        Context context = this.f18273h;
        if (context == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        com.vivo.push.b.z zVar = new com.vivo.push.b.z(false, context.getPackageName(), arrayList);
        zVar.a(500);
        if (this.q) {
            if (!n()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            } else if (!a(this.f18272g)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            } else {
                this.f18272g = SystemClock.elapsedRealtime();
                String a2 = a(new a(zVar, iPushActionListener));
                zVar.b(a2);
                if (TextUtils.isEmpty(this.f18276k)) {
                    a(a2, 20001);
                    return;
                } else if (arrayList.size() < 0) {
                    a(a2, 20002);
                    return;
                } else if (arrayList.size() > 500) {
                    a(a2, 20004);
                    return;
                } else {
                    Iterator<String> it = arrayList.iterator();
                    while (it.hasNext()) {
                        if (it.next().length() > 70) {
                            a(a2, 20003);
                            return;
                        }
                    }
                    a(zVar);
                    e(a2);
                    return;
                }
            }
        }
        a(zVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    private static boolean a(long j2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        return j2 == -1 || elapsedRealtime <= j2 || elapsedRealtime >= j2 + 2000;
    }

    public final void a(String str, int i2) {
        a d = d(str);
        if (d != null) {
            d.a(i2, new Object[0]);
        } else {
            com.vivo.push.util.p.d("PushClientManager", "notifyStatusChanged token is null");
        }
    }

    private synchronized String a(a aVar) {
        int i2;
        this.f18278m.put(this.f18279n, aVar);
        i2 = this.f18279n;
        this.f18279n = i2 + 1;
        return Integer.toString(i2);
    }

    public final void a(ArrayList<String> arrayList, IPushActionListener iPushActionListener) {
        Context context = this.f18273h;
        if (context == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
                return;
            }
            return;
        }
        com.vivo.push.b.z zVar = new com.vivo.push.b.z(true, context.getPackageName(), arrayList);
        zVar.a(500);
        if (this.q) {
            if (!n()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                    return;
                }
                return;
            } else if (!a(this.f18271f)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                    return;
                }
                return;
            } else {
                this.f18271f = SystemClock.elapsedRealtime();
                String a2 = a(new a(zVar, iPushActionListener));
                zVar.b(a2);
                if (TextUtils.isEmpty(this.f18276k)) {
                    a(a2, 20001);
                    return;
                } else if (arrayList.size() < 0) {
                    a(a2, 20002);
                    return;
                } else {
                    if (arrayList.size() + c().size() > 500) {
                        a(a2, 20004);
                        return;
                    }
                    Iterator<String> it = arrayList.iterator();
                    while (it.hasNext()) {
                        if (it.next().length() > 70) {
                            a(a2, 20003);
                            return;
                        }
                    }
                    a(zVar);
                    e(a2);
                    return;
                }
            }
        }
        a(zVar);
        if (iPushActionListener != null) {
            iPushActionListener.onStateChanged(0);
        }
    }

    public final void a(Intent intent, PushMessageCallback pushMessageCallback) {
        o createReceiverCommand = this.r.createReceiverCommand(intent);
        Context context = a().f18273h;
        if (createReceiverCommand == null) {
            com.vivo.push.util.p.a("PushClientManager", "sendCommand, null command!");
            if (context != null) {
                com.vivo.push.util.p.c(context, "[\u6267\u884c\u6307\u4ee4\u5931\u8d25]\u6307\u4ee4\u7a7a\uff01");
                return;
            }
            return;
        }
        com.vivo.push.d.z createReceiveTask = this.r.createReceiveTask(createReceiverCommand);
        if (createReceiveTask == null) {
            com.vivo.push.util.p.a("PushClientManager", "sendCommand, null command task! pushCommand = ".concat(String.valueOf(createReceiverCommand)));
            if (context != null) {
                com.vivo.push.util.p.c(context, "[\u6267\u884c\u6307\u4ee4\u5931\u8d25]\u6307\u4ee4" + createReceiverCommand + "\u4efb\u52a1\u7a7a\uff01");
                return;
            }
            return;
        }
        if (context != null && !(createReceiverCommand instanceof com.vivo.push.b.n)) {
            com.vivo.push.util.p.a(context, "[\u63a5\u6536\u6307\u4ee4]".concat(String.valueOf(createReceiverCommand)));
        }
        createReceiveTask.a(pushMessageCallback);
        m.a((l) createReceiveTask);
    }

    public final void a(o oVar) {
        Context context = a().f18273h;
        if (oVar == null) {
            com.vivo.push.util.p.a("PushClientManager", "sendCommand, null command!");
            if (context != null) {
                com.vivo.push.util.p.c(context, "[\u6267\u884c\u6307\u4ee4\u5931\u8d25]\u6307\u4ee4\u7a7a\uff01");
                return;
            }
            return;
        }
        l createTask = this.r.createTask(oVar);
        if (createTask == null) {
            com.vivo.push.util.p.a("PushClientManager", "sendCommand, null command task! pushCommand = ".concat(String.valueOf(oVar)));
            if (context != null) {
                com.vivo.push.util.p.c(context, "[\u6267\u884c\u6307\u4ee4\u5931\u8d25]\u6307\u4ee4" + oVar + "\u4efb\u52a1\u7a7a\uff01");
                return;
            }
            return;
        }
        com.vivo.push.util.p.d("PushClientManager", "client--sendCommand, command = ".concat(String.valueOf(oVar)));
        m.a(createTask);
    }
}
