package com.jingdong.jdma.f;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.common.utils.n;
import com.jingdong.jdma.common.utils.o;
import com.jingdong.jdma.f.d;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes12.dex */
public class a {
    private com.jingdong.jdma.e.a a;
    private boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f12722c = false;
    private volatile int d = 0;

    /* renamed from: e  reason: collision with root package name */
    private int f12723e = 0;

    /* renamed from: f  reason: collision with root package name */
    private Context f12724f;

    /* renamed from: com.jingdong.jdma.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    class RunnableC0491a implements Runnable {
        RunnableC0491a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            aVar.b(com.jingdong.jdma.b.a.a(aVar.f12724f).a("failure_log"));
        }
    }

    /* loaded from: classes12.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            List<com.jingdong.jdma.bean.c.a> a = com.jingdong.jdma.b.a.a(a.this.f12724f).a("failure_log", "", 1L);
            if (a.isEmpty()) {
                a.this.a(0);
                a.this.f12723e = 0;
                a.this.f12722c = false;
                return;
            }
            com.jingdong.jdma.bean.c.a aVar = a.get(0);
            String[] strArr = {aVar.a() + ""};
            com.jingdong.jdma.bean.d.a aVar2 = new com.jingdong.jdma.bean.d.a();
            aVar2.a("failure_log");
            aVar2.a(strArr);
            String b = aVar.b();
            a.this.a((TextUtils.isEmpty(b) || !"h5".equals(b)) ? d.a.d : d.a.f12731g, aVar, aVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class c implements com.jingdong.jdma.f.b {
        c() {
        }

        @Override // com.jingdong.jdma.f.b
        public void a() {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(int i2) {
        }

        @Override // com.jingdong.jdma.f.b
        public void a(com.jingdong.jdma.bean.d.a aVar) {
            a.this.a(aVar);
        }

        @Override // com.jingdong.jdma.f.b
        public void a(com.jingdong.jdma.bean.d.a aVar, com.jingdong.jdma.bean.b.c.a aVar2) {
            a.this.a(aVar, aVar2);
        }
    }

    public a(Context context, com.jingdong.jdma.e.a aVar) {
        this.f12724f = context;
        this.a = aVar;
        o.a().a(new RunnableC0491a());
    }

    public void d() {
        this.b = true;
    }

    public synchronized int b(com.jingdong.jdma.bean.d.a aVar) {
        int i2 = 0;
        if (aVar == null) {
            return 0;
        }
        int i3 = n.a().c() ? 20000 : 10000;
        if (this.d > i3) {
            if (LogUtil.isDebug()) {
                LogUtil.e("FailureDataHandleManager", String.format("'%s' table's data total count = %s and reach %s max limit,then lost data.", "failure_log", Integer.valueOf(this.d), Integer.valueOf(i3)));
            }
        } else {
            List<com.jingdong.jdma.bean.c.a> a = com.jingdong.jdma.b.a.a(this.f12724f).a(aVar.c(), aVar.b(), (String) null);
            JSONArray jSONArray = new JSONArray();
            int size = a.size();
            for (int i4 = 0; i4 < size; i4++) {
                jSONArray.put(a.get(i4).c());
            }
            b(1);
            if (com.jingdong.jdma.b.a.a(this.f12724f).a("failure_log", jSONArray.toString(), "m_log".equals(aVar.c()) ? "h5" : "native")) {
                i2 = com.jingdong.jdma.b.a.a(this.f12724f).a(aVar.c(), aVar.b());
            }
        }
        return i2;
    }

    public void c() {
        this.b = false;
        if (this.f12722c || this.d <= 0) {
            return;
        }
        this.a.a(0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d.a aVar, com.jingdong.jdma.bean.c.a aVar2, com.jingdong.jdma.bean.d.a aVar3) {
        try {
            c cVar = new c();
            if (aVar.a() == d.a.f12731g.a()) {
                g.a(d.b(aVar.a()), new JSONArray(aVar2.c()), 1, aVar3, (com.jingdong.jdma.f.b) cVar, true);
            } else {
                g.a(d.b(aVar.a()), new JSONArray(aVar2.c()), 1, (com.jingdong.jdma.f.b) cVar, aVar3, true);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            this.f12722c = false;
        }
    }

    public synchronized void b() {
        if (this.d == 0) {
            return;
        }
        if (this.f12722c) {
            return;
        }
        this.f12722c = true;
        o.a().a(new b());
    }

    public boolean a() {
        return com.jingdong.jdma.h.d.e().a();
    }

    private synchronized void a(Context context, com.jingdong.jdma.bean.d.a aVar) {
        if (aVar != null) {
            int a = com.jingdong.jdma.b.a.a(context).a(aVar.c(), aVar.b());
            if (a < 0) {
                a = 0;
            }
            b(-a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(int i2) {
        this.d = i2;
        com.jingdong.jdma.common.utils.c.f12677g = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(int i2) {
        this.d += i2;
        if (this.d < 0) {
            this.d = 0;
        }
        com.jingdong.jdma.common.utils.c.f12677g = this.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.jingdong.jdma.bean.d.a aVar) {
        a(this.f12724f, aVar);
        a("retrySuccess", 200);
        this.f12723e = 0;
        this.f12722c = false;
        if (this.b || this.d <= 0) {
            return;
        }
        this.a.a(0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.jingdong.jdma.bean.d.a aVar, com.jingdong.jdma.bean.b.c.a aVar2) {
        int a = com.jingdong.jdma.h.d.e().b().a();
        if (n.a().c()) {
            a = 1;
        }
        int b2 = com.jingdong.jdma.h.d.e().b().b();
        int b3 = aVar2 instanceof com.jingdong.jdma.bean.b.c.b ? ((com.jingdong.jdma.bean.b.c.b) aVar2).b() : 0;
        if (aVar2 instanceof com.jingdong.jdma.bean.b.c.d) {
            if (!this.b && this.d > 0) {
                this.a.a(b2 * 1000);
            }
            this.f12722c = false;
            return;
        }
        int i2 = this.f12723e + 1;
        this.f12723e = i2;
        if (i2 >= a) {
            if (LogUtil.isDebug()) {
                LogUtil.d("FailureDataHandleManager", String.format("the try count=%s and reach limit,finally delete data\u3002", Integer.valueOf(this.f12723e)));
            }
            a(this.f12724f, aVar);
            a("retryFailure", b3);
            this.f12723e = 0;
        }
        if (!this.b && this.d > 0) {
            if (LogUtil.isDebug()) {
                LogUtil.d("FailureDataHandleManager", String.format("the try count=%s and delay %d seconds start message", Integer.valueOf(this.f12723e), Integer.valueOf(b2)));
            }
            this.a.a(b2 * 1000);
        }
        this.f12722c = false;
    }

    private void a(String str, int i2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("commonNum", com.jingdong.jdma.common.utils.c.f12674c + "");
        hashMap.put("quickNum", com.jingdong.jdma.common.utils.c.d + "");
        hashMap.put("dauNum", com.jingdong.jdma.common.utils.c.f12675e + "");
        hashMap.put("failureNum", com.jingdong.jdma.common.utils.c.f12677g + "");
        hashMap.put("retryCount", this.f12723e + "");
        if (i2 != 0) {
            hashMap.put("responseCode", i2 + "");
        }
        com.jingdong.jdma.f.c.a().b(str, hashMap);
    }
}
