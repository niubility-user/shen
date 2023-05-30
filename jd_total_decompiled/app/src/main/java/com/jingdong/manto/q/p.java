package com.jingdong.manto.q;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.manto.i.a;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.launch.h;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class p {

    /* renamed from: k  reason: collision with root package name */
    private static final Integer f14080k = 1;

    /* renamed from: l  reason: collision with root package name */
    private static final Integer f14081l = 0;
    private Handler a;
    private HandlerThread b;

    /* renamed from: e  reason: collision with root package name */
    private l f14083e;

    /* renamed from: f  reason: collision with root package name */
    private String f14084f;

    /* renamed from: g  reason: collision with root package name */
    private String f14085g;

    /* renamed from: h  reason: collision with root package name */
    private a.g f14086h;

    /* renamed from: i  reason: collision with root package name */
    private d f14087i;

    /* renamed from: c  reason: collision with root package name */
    private boolean f14082c = false;
    private ArrayBlockingQueue<String> d = new ArrayBlockingQueue<>(64);

    /* renamed from: j  reason: collision with root package name */
    private Map<String, Integer> f14088j = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            if (p.this.d.size() <= 0) {
                p.this.e();
                return;
            }
            p pVar = p.this;
            pVar.b((String) pVar.d.poll());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements h.e {
        b() {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(long j2, long j3, boolean z) {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(MantoPreLaunchProcess.LaunchError launchError) {
            if (p.this.a != null) {
                p.this.f14088j.put(p.this.f14084f, p.f14081l);
                p.this.a.obtainMessage(0).sendToTarget();
                p.this.f14084f = null;
            }
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(PkgDetailEntity pkgDetailEntity) {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(boolean z) {
            if (p.this.a != null) {
                p.this.f14088j.put(p.this.f14084f, p.f14080k);
                p.this.a.obtainMessage(0).sendToTarget();
                p pVar = p.this;
                pVar.a(pVar.f14084f);
                p.this.f14084f = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static class c extends com.jingdong.manto.m.d {
        private c() {
        }

        /* synthetic */ c(a aVar) {
            this();
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onPreloadSubpackage";
        }
    }

    /* loaded from: classes16.dex */
    public interface d {
        void a(String str);
    }

    public p(l lVar, String str, a.g gVar, d dVar) {
        this.f14085g = str;
        this.f14086h = gVar;
        this.f14083e = lVar;
        this.f14087i = dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        com.jingdong.manto.f fVar;
        com.jingdong.manto.h hVar;
        l lVar = this.f14083e;
        if (lVar == null || (fVar = lVar.a) == null || (hVar = fVar.f13015g) == null) {
            return;
        }
        hVar.a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        com.jingdong.manto.f fVar;
        l lVar = this.f14083e;
        if (lVar == null || (fVar = lVar.a) == null) {
            return;
        }
        com.jingdong.manto.launch.h hVar = new com.jingdong.manto.launch.h(fVar.f13016h, fVar.r, str, fVar.u());
        hVar.f13254c = new b();
        Handler handler = this.a;
        if (handler != null) {
            this.f14084f = str;
            handler.post(hVar);
        }
    }

    private void c(String str) {
        if (c()) {
            c cVar = new c(null);
            HashMap hashMap = new HashMap();
            hashMap.put("root", this.f14085g);
            hashMap.put("message", str);
            cVar.a(this.f14083e.a, 0).a(hashMap).a();
        }
    }

    private boolean c() {
        l lVar = this.f14083e;
        return (lVar == null || lVar.a == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        g();
        d dVar = this.f14087i;
        if (dVar != null) {
            dVar.a(this.f14085g);
        }
        HandlerThread handlerThread = this.b;
        if (handlerThread != null) {
            handlerThread.quit();
            this.b = null;
        }
        this.f14082c = false;
    }

    private void f() {
        try {
            if (this.d.size() <= 0 || this.f14082c) {
                return;
            }
            HandlerThread handlerThread = new HandlerThread("MantoSubPreDownloadThread", 10);
            this.b = handlerThread;
            handlerThread.start();
            this.a = new a(this.b.getLooper());
            b(this.d.poll());
            this.f14082c = true;
        } catch (Throwable unused) {
        }
    }

    private void g() {
        if (c()) {
            c cVar = new c(null);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("root", this.f14085g);
                jSONObject.put("message", "complete");
                JSONArray jSONArray = new JSONArray();
                for (String str : this.f14088j.keySet()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("packageName", str);
                    jSONObject2.put("result", this.f14088j.get(str));
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("detail", jSONArray);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            cVar.a(this.f14083e.a, 0).a(jSONObject.toString()).a();
        }
    }

    public void d() {
        this.d.clear();
        HandlerThread handlerThread = this.b;
        if (handlerThread != null) {
            handlerThread.quit();
            this.b = null;
        }
        this.f14082c = false;
    }

    public void h() {
        c("start");
        if (TextUtils.equals(this.f14086h.a, "wifi") && !TextUtils.equals(MantoUtils.getNetworkType(com.jingdong.a.g()), "wifi")) {
            c("current network is not wifi");
            d dVar = this.f14087i;
            if (dVar != null) {
                dVar.a(this.f14085g);
                return;
            }
            return;
        }
        ArrayList<String> arrayList = this.f14086h.b;
        if (arrayList == null) {
            d dVar2 = this.f14087i;
            if (dVar2 != null) {
                dVar2.a(this.f14085g);
                return;
            }
            return;
        }
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!this.d.contains(next)) {
                this.d.add(next);
            }
        }
        if (this.f14082c) {
            return;
        }
        f();
    }
}
