package com.jingdong.sdk.perfmonitor.b;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.Reporter;
import com.jingdong.sdk.perfmonitor.d.b;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class e<T extends com.jingdong.sdk.perfmonitor.d.b> extends com.jingdong.sdk.perfmonitor.b.b<T> {

    /* renamed from: g  reason: collision with root package name */
    com.jingdong.sdk.perfmonitor.a.b f15351g;

    /* renamed from: h  reason: collision with root package name */
    e<T>.o f15352h;

    /* renamed from: i  reason: collision with root package name */
    Handler f15353i;

    /* loaded from: classes12.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f15354g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ p f15355h;

        a(long j2, p pVar) {
            this.f15354g = j2;
            this.f15355h = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = e.this;
            com.jingdong.sdk.perfmonitor.a.b bVar = eVar.f15351g;
            if (bVar != null) {
                bVar.f15284f = this.f15354g;
            }
            eVar.A(this.f15355h);
            e.this.j();
        }
    }

    /* loaded from: classes12.dex */
    class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15357g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ long f15358h;

        b(String str, long j2) {
            this.f15357g = str;
            this.f15358h = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ConcurrentHashMap<String, com.jingdong.sdk.perfmonitor.a.d> concurrentHashMap;
            com.jingdong.sdk.perfmonitor.a.b bVar = e.this.f15351g;
            if (bVar == null || (concurrentHashMap = bVar.f15287i) == null || concurrentHashMap.get(this.f15357g) != null) {
                return;
            }
            com.jingdong.sdk.perfmonitor.a.d dVar = new com.jingdong.sdk.perfmonitor.a.d();
            String str = this.f15357g;
            dVar.a = str;
            dVar.b = this.f15358h;
            e.this.f15351g.f15287i.put(str, dVar);
        }
    }

    /* loaded from: classes12.dex */
    class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15360g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ long f15361h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f15362i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f15363j;

        c(String str, long j2, int i2, String str2) {
            this.f15360g = str;
            this.f15361h = j2;
            this.f15362i = i2;
            this.f15363j = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ConcurrentHashMap<String, com.jingdong.sdk.perfmonitor.a.d> concurrentHashMap;
            com.jingdong.sdk.perfmonitor.a.d dVar;
            com.jingdong.sdk.perfmonitor.a.b bVar = e.this.f15351g;
            if (bVar == null || (concurrentHashMap = bVar.f15287i) == null || (dVar = concurrentHashMap.get(this.f15360g)) == null || dVar.f15300c != 0) {
                return;
            }
            dVar.f15300c = this.f15361h;
            dVar.d = this.f15362i;
            dVar.f15301e = this.f15363j;
        }
    }

    /* loaded from: classes12.dex */
    class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15365g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ long f15366h;

        d(String str, long j2) {
            this.f15365g = str;
            this.f15366h = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Map<String, Long> map;
            com.jingdong.sdk.perfmonitor.a.b bVar = e.this.f15351g;
            if (bVar == null || (map = bVar.f15288j) == null) {
                return;
            }
            map.put(this.f15365g, Long.valueOf(this.f15366h));
        }
    }

    /* renamed from: com.jingdong.sdk.perfmonitor.b.e$e  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    class RunnableC0742e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15368g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f15369h;

        RunnableC0742e(String str, String str2) {
            this.f15368g = str;
            this.f15369h = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.perfmonitor.a.b bVar = e.this.f15351g;
            if (bVar != null) {
                if (bVar.f15286h == null) {
                    bVar.f15286h = new JSONObject();
                }
                try {
                    e.this.f15351g.f15286h.put(this.f15368g, this.f15369h);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.f15351g = new com.jingdong.sdk.perfmonitor.a.b();
        }
    }

    /* loaded from: classes12.dex */
    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.j();
        }
    }

    /* loaded from: classes12.dex */
    class h implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f15373g;

        h(long j2) {
            this.f15373g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.perfmonitor.a.b bVar = e.this.f15351g;
            if (bVar != null) {
                bVar.a = this.f15373g;
            }
        }
    }

    /* loaded from: classes12.dex */
    class i implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f15375g;

        i(long j2) {
            this.f15375g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.perfmonitor.a.b bVar = e.this.f15351g;
            if (bVar != null) {
                bVar.b = this.f15375g;
            }
        }
    }

    /* loaded from: classes12.dex */
    class j implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f15377g;

        j(long j2) {
            this.f15377g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.perfmonitor.a.b bVar = e.this.f15351g;
            if (bVar != null) {
                bVar.f15282c = this.f15377g;
            }
        }
    }

    /* loaded from: classes12.dex */
    class k implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15379g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ long f15380h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f15381i;

        k(String str, long j2, String str2) {
            this.f15379g = str;
            this.f15380h = j2;
            this.f15381i = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = e.this;
            eVar.f15352h = new o(eVar, this.f15379g, this.f15380h, this.f15381i);
        }
    }

    /* loaded from: classes12.dex */
    class l implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15383g;

        l(String str) {
            this.f15383g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.perfmonitor.a.b bVar = e.this.f15351g;
            if (bVar == null || bVar.f15289k != null) {
                return;
            }
            bVar.f15289k = this.f15383g;
        }
    }

    /* loaded from: classes12.dex */
    class m implements Runnable {
        m() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.f15352h = null;
        }
    }

    /* loaded from: classes12.dex */
    class n implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f15386g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ p f15387h;

        n(long j2, p pVar) {
            this.f15386g = j2;
            this.f15387h = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = e.this;
            com.jingdong.sdk.perfmonitor.a.b bVar = eVar.f15351g;
            if (bVar != null) {
                bVar.d = this.f15386g;
                eVar.A(this.f15387h);
                e.this.j();
            }
        }
    }

    /* loaded from: classes12.dex */
    class o {
        String a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        long f15389c;

        o(e eVar, String str, long j2, String str2) {
            this.a = str;
            this.f15389c = j2;
            this.b = str2;
        }
    }

    /* loaded from: classes12.dex */
    public enum p {
        AUTO("auto"),
        STARTUP("startup"),
        BUSINESS("business");
        
        private final String value;

        p(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(Reporter reporter) {
        super(reporter);
        this.f15353i = new Handler(com.jingdong.sdk.perfmonitor.b.b.f15310f.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(p pVar) {
        com.jingdong.sdk.perfmonitor.a.b bVar = this.f15351g;
        if (bVar == null) {
            return;
        }
        try {
            long j2 = bVar.f15283e;
            if (j2 == 0 || bVar.a - j2 <= 1000) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("chId", "1");
                String str = this.f15351g.f15285g;
                if (str != null) {
                    hashMap.put("prePageName", str);
                }
                com.jingdong.sdk.perfmonitor.a.b bVar2 = this.f15351g;
                long j3 = bVar2.f15283e;
                if (j3 == 0) {
                    j3 = bVar2.a;
                }
                hashMap.put("start", String.valueOf(j3));
                long j4 = this.f15351g.f15284f;
                if (j4 != 0) {
                    hashMap.put("stop", String.valueOf(j4));
                }
                long j5 = this.f15351g.d;
                if (j5 != 0) {
                    hashMap.put("renderFinish", String.valueOf(j5));
                }
                hashMap.put("rtype", pVar.getValue());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("onCreate", String.valueOf(this.f15351g.a));
                jSONObject.put("onStart", String.valueOf(this.f15351g.b));
                jSONObject.put("onResume", String.valueOf(this.f15351g.f15282c));
                hashMap.put("initInfo", jSONObject.toString());
                JSONObject jSONObject2 = this.f15351g.f15286h;
                if (jSONObject2 != null) {
                    hashMap.put("extraInfo", jSONObject2.toString());
                }
                String str2 = this.f15351g.f15289k;
                if (str2 != null) {
                    hashMap.put(XView2Constants.FRAGMENT, str2);
                }
                ConcurrentHashMap<String, com.jingdong.sdk.perfmonitor.a.d> concurrentHashMap = this.f15351g.f15287i;
                if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<String> it = this.f15351g.f15287i.keySet().iterator();
                    while (it.hasNext()) {
                        com.jingdong.sdk.perfmonitor.a.d dVar = this.f15351g.f15287i.get(it.next());
                        if (dVar != null) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("functionID", dVar.a);
                            jSONObject3.put("startTime", String.valueOf(dVar.b));
                            jSONObject3.put("endTime", String.valueOf(dVar.f15300c));
                            jSONObject3.put("errCode", String.valueOf(dVar.d));
                            if (!TextUtils.isEmpty(dVar.f15301e)) {
                                jSONObject3.put("errMsg", dVar.f15301e);
                            }
                            jSONArray.put(jSONObject3);
                        }
                    }
                    hashMap.put("requestInfo", jSONArray.toString());
                }
                Map<String, Long> map = this.f15351g.f15288j;
                if (map != null && map.size() > 0) {
                    JSONObject jSONObject4 = new JSONObject();
                    for (Map.Entry<String, Long> entry : this.f15351g.f15288j.entrySet()) {
                        jSONObject4.put(entry.getKey(), entry.getValue());
                    }
                    hashMap.put("extraTime", jSONObject4.toString());
                }
                f(hashMap);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private String p(Activity activity) {
        if (activity == null) {
            return null;
        }
        if ("com.jingdong.common.jdreactFramework.activities.JDReactNativeCommonActivity".equals(com.jingdong.sdk.perfmonitor.b.b.d(activity))) {
            return q(activity.getIntent());
        }
        if (ActivityNumController.WebActivity.equals(com.jingdong.sdk.perfmonitor.b.b.d(activity))) {
            return r(activity.getIntent());
        }
        return null;
    }

    private String q(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra(JDReactConstant.IntentConstant.MODULE_NAME);
    }

    private String r(Intent intent) {
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra("url");
        if (stringExtra == null) {
            try {
                return ((SerializableContainer) intent.getSerializableExtra("urlParamMap")).getMap().get((Object) RemoteMessageConst.TO);
            } catch (Exception unused) {
                return stringExtra;
            }
        }
        return stringExtra;
    }

    public void B(String str) {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new l(str));
    }

    public void C(Activity activity, long j2) {
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new k(com.jingdong.sdk.perfmonitor.b.b.d(activity), j2, p(activity)));
    }

    public boolean D(String str) {
        T t = this.b;
        if (t == null) {
            return false;
        }
        return t.a(str, e());
    }

    @Override // com.jingdong.sdk.perfmonitor.b.b
    public void i(String str) {
        super.i(str);
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new f());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.perfmonitor.b.b
    public void j() {
        this.f15351g = null;
    }

    public void l(String str, String str2) {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new RunnableC0742e(str, str2));
    }

    @SuppressLint({"DefaultLocale"})
    public void m(String str, long j2) {
        OKLog.d("PerfMonitor", String.format("add custom trace: key = %s, value = %d", str, Long.valueOf(j2)));
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new d(str, j2));
    }

    public void n() {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new g());
    }

    public void o() {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new m());
    }

    public void s() {
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new h(System.currentTimeMillis()));
    }

    public void t(p pVar) {
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new a(System.currentTimeMillis(), pVar));
    }

    public void u() {
    }

    public void v(p pVar) {
        OKLog.d("PerfMonitor", "onRender");
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new n(System.currentTimeMillis(), pVar));
    }

    public void w(String str) {
        OKLog.d("PerfMonitor", "onRequest: " + str);
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new b(str, System.currentTimeMillis()));
    }

    public void x(String str, int i2, String str2) {
        OKLog.d("PerfMonitor", "onResponse: " + str);
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new c(str, System.currentTimeMillis(), i2, str2));
    }

    public void y() {
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new j(System.currentTimeMillis()));
    }

    public void z() {
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new i(System.currentTimeMillis()));
    }
}
