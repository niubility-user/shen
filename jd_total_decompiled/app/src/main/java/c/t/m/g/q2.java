package c.t.m.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.jingdong.common.web.managers.WebPerfManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimerTask;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class q2 extends i0 {

    /* renamed from: g  reason: collision with root package name */
    public static volatile q2 f615g;

    /* renamed from: h  reason: collision with root package name */
    public static Context f616h;

    /* renamed from: c  reason: collision with root package name */
    public volatile boolean f617c = true;
    public volatile e5 d = e5.a;

    /* renamed from: e  reason: collision with root package name */
    public Handler f618e;

    /* renamed from: f  reason: collision with root package name */
    public volatile String f619f;

    /* loaded from: classes.dex */
    public static class a extends Thread {
        public a(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                q2.q();
                g3.a();
                y2.f();
                r3.f(g3.a().c(), "last_pull_time", "0");
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends TimerTask {

        /* renamed from: g  reason: collision with root package name */
        public final JSONObject f620g = new JSONObject();

        /* renamed from: h  reason: collision with root package name */
        public y2 f621h;

        /* renamed from: i  reason: collision with root package name */
        public g3 f622i;

        public b() {
            this.f621h = null;
            this.f622i = null;
            this.f621h = y2.f();
            this.f622i = g3.a();
        }

        public final void a() {
            boolean b = q2.this.b();
            if (b) {
                try {
                    this.f622i.d();
                } catch (Throwable unused) {
                    this.f621h.o();
                    if (!b) {
                        return;
                    }
                }
            }
            JSONObject d = d();
            if (d != this.f620g) {
                int parseInt = Integer.parseInt(d.optString("status", "-5"));
                new StringBuilder("status:").append(parseInt);
                if (parseInt == 0 && d.has("version")) {
                    c(d);
                }
                r3.f(this.f622i.c(), "last_pull_time", String.valueOf(System.currentTimeMillis()));
                Thread.sleep(1000L);
            }
            this.f621h.o();
            if (!b) {
                return;
            }
            this.f622i.e();
        }

        public final void b(SharedPreferences.Editor editor, String str, String str2, JSONObject jSONObject) {
            try {
                String n2 = this.f621h.n(str);
                if (n2 == null) {
                    return;
                }
                editor.putString(str, jSONObject.optString(str2, n2));
            } catch (Exception unused) {
            }
        }

        public final void c(JSONObject jSONObject) {
            int i2;
            SharedPreferences c2 = this.f622i.c();
            if (c2 == null) {
                return;
            }
            int j2 = this.f621h.j(WebPerfManager.HYBRID_SHARED_FILE_VERSION);
            try {
                i2 = Integer.parseInt(jSONObject.optString("version", this.f621h.n(WebPerfManager.HYBRID_SHARED_FILE_VERSION)));
            } catch (Throwable unused) {
                i2 = j2;
            }
            StringBuilder sb = new StringBuilder("locVer:");
            sb.append(j2);
            sb.append(",serVer:");
            sb.append(i2);
            if (i2 == j2) {
                return;
            }
            c2.edit().clear().apply();
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject == null) {
                optJSONObject = this.f620g;
            }
            SharedPreferences.Editor edit = c2.edit();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                b(edit, next, next, optJSONObject);
            }
            edit.putString(WebPerfManager.HYBRID_SHARED_FILE_VERSION, String.valueOf(i2));
            edit.putString(ApplicationUpgradeHelper.APP_VERSION, y2.f774i);
            try {
                long parseLong = Long.parseLong(optJSONObject.optString("cc_req_interval", this.f621h.n("cc_req_interval")));
                if (parseLong < 1800000) {
                    parseLong = 1800000;
                } else if (parseLong > 86400000) {
                    parseLong = 86400000;
                }
                edit.putString("cc_req_interval", String.valueOf(parseLong));
            } catch (Throwable unused2) {
            }
            edit.apply();
        }

        public final JSONObject d() {
            if (q2.this.d == null) {
                return this.f620g;
            }
            String replace = q2.this.f617c ? "https://cc.map.qq.com/?get_c3" : "https://cc.map.qq.com/?get_c3".replace("https:", "http:");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(WebPerfManager.HYBRID_SHARED_FILE_VERSION, this.f621h.n(WebPerfManager.HYBRID_SHARED_FILE_VERSION));
                jSONObject.put("m_module", y2.f772g);
                jSONObject.put("m_channel", y2.f773h);
                jSONObject.put("m_version", y2.f774i);
                String str = q2.this.f619f;
                if (t2.c(str) || "0123456789ABCDEF".equals(str)) {
                    str = z3.a();
                }
                if (t2.c(str) || "0123456789ABCDEF".equals(str)) {
                    str = z3.p();
                }
                jSONObject.put("imei", str);
                String jSONObject2 = jSONObject.toString();
                z0.d("TAG", "cc request: ".concat(String.valueOf(jSONObject2)));
                String a = o5.a("cc");
                Bundle a2 = q2.this.d.a(replace, o5.i(jSONObject2, a).getBytes(), null);
                String string = a2.getString("msg_suc", "");
                if (!TextUtils.isEmpty(string)) {
                    String jSONObject3 = this.f620g.toString();
                    if (!jSONObject3.equals(string)) {
                        jSONObject3 = o5.b(string, a);
                    }
                    return TextUtils.isEmpty(jSONObject3) ? this.f620g : new JSONObject(jSONObject3);
                }
                String string2 = a2.getString("msg_fail", "");
                StringBuilder sb = new StringBuilder("net work error! res = [");
                sb.append(string2);
                sb.append("]");
                return this.f620g;
            } catch (Throwable unused) {
                return this.f620g;
            }
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                if (Math.abs(System.currentTimeMillis() - this.f621h.l("last_pull_time")) >= q2.this.p()) {
                    a();
                }
                if (q2.this.b()) {
                    q2.this.r();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public q2() {
        Context context = f616h;
        if (context == null || context.getApplicationContext() == null) {
            throw new IllegalStateException("Please invoke initial(context,...) first when app started!");
        }
    }

    public static void i(Context context, String str, String str2) {
        if (context == null || context.getApplicationContext() == null) {
            throw new NullPointerException("context cannot be null!");
        }
        Context applicationContext = context.getApplicationContext();
        f616h = applicationContext;
        y3.b(applicationContext);
        g3.b(str);
        y2.d(str, str2);
        new a("th_loc_tmp").start();
    }

    public static void j(String str) {
        y2.q(str);
    }

    public static void k(HashMap<String, String> hashMap) {
        y2.e(hashMap);
    }

    public static synchronized q2 q() {
        q2 q2Var;
        synchronized (q2.class) {
            if (f615g == null) {
                synchronized (q2.class) {
                    if (f615g == null) {
                        f615g = new q2();
                    }
                }
            }
            q2Var = f615g;
        }
        return q2Var;
    }

    @Override // c.t.m.g.i0
    public String a() {
        return "TxCC";
    }

    @Override // c.t.m.g.i0
    public void d() {
        try {
            y2.f().p();
            t.j(this.f618e);
            h(0L);
            d.c("th_loc_task_t_consume", 100L);
            this.f618e = null;
        } catch (Throwable unused) {
        }
    }

    @Override // c.t.m.g.i0
    public int f() {
        this.f618e = new Handler(d.e("th_loc_task_t_consume").getLooper());
        h(Final.FIVE_SECOND);
        return 0;
    }

    public final void h(long j2) {
        Handler handler = this.f618e;
        b bVar = new b();
        if (j2 < 0) {
            j2 = 0;
        }
        t.h(handler, bVar, j2);
    }

    public final long p() {
        long l2 = y2.f().l("cc_req_interval");
        if (l2 > 86400000) {
            l2 = 86400000;
        }
        if (l2 < 1800000) {
            return 1800000L;
        }
        return l2;
    }

    public final void r() {
        try {
            int j2 = y2.f().j(WebPerfManager.HYBRID_SHARED_FILE_VERSION);
            long p = j2 == -1 ? 10800000L : p();
            StringBuilder sb = new StringBuilder("schedule : locVer[");
            sb.append(j2);
            sb.append("],delayTime[");
            sb.append(p);
            sb.append("]");
            h(p);
        } catch (Throwable unused) {
        }
    }
}
