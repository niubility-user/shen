package com.jingdong.sdk.perfmonitor.b;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.jingdong.sdk.perfmonitor.Reporter;
import com.jingdong.sdk.perfmonitor.a.c;
import com.jingdong.sdk.perfmonitor.c.b;
import com.jingdong.sdk.perfmonitor.c.c;
import com.jingdong.sdk.perfmonitor.c.d;
import com.jingdong.sdk.perfmonitor.d.f;
import java.util.HashMap;
import java.util.Set;

/* loaded from: classes12.dex */
public class f extends com.jingdong.sdk.perfmonitor.b.b<com.jingdong.sdk.perfmonitor.d.f> implements b.a, c.a, d.a {

    /* renamed from: g */
    private com.jingdong.sdk.perfmonitor.c.b f15391g;

    /* renamed from: h */
    private com.jingdong.sdk.perfmonitor.c.c f15392h;

    /* renamed from: i */
    private com.jingdong.sdk.perfmonitor.c.d f15393i;

    /* renamed from: j */
    private com.jingdong.sdk.perfmonitor.a.a f15394j;

    /* renamed from: k */
    private com.jingdong.sdk.perfmonitor.a.c f15395k;

    /* renamed from: l */
    private com.jingdong.sdk.perfmonitor.a.e f15396l;

    /* renamed from: m */
    private c f15397m;

    /* renamed from: n */
    private String f15398n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a implements f.b {
        final /* synthetic */ Context a;

        a(Context context) {
            f.this = r1;
            this.a = context;
        }

        @Override // com.jingdong.sdk.perfmonitor.d.f.b
        public void a(f.a aVar, f.a aVar2, f.a aVar3) {
            if (aVar != null && aVar.a) {
                f fVar = f.this;
                fVar.f15391g = new com.jingdong.sdk.perfmonitor.c.b(this.a, aVar.f15418c, aVar.b, fVar);
            }
            if (aVar2 != null && aVar2.a) {
                f fVar2 = f.this;
                fVar2.f15392h = new com.jingdong.sdk.perfmonitor.c.c(this.a, aVar2.f15418c, aVar2.b, fVar2);
            }
            if (aVar3 == null || !aVar3.a) {
                return;
            }
            f fVar3 = f.this;
            fVar3.f15393i = new com.jingdong.sdk.perfmonitor.c.d(this.a, aVar3.f15418c, aVar3.b, fVar3);
        }
    }

    /* loaded from: classes12.dex */
    class b implements c.a {
        b() {
            f.this = r1;
        }

        @Override // com.jingdong.sdk.perfmonitor.a.c.a
        public void a(long j2, long j3) {
        }

        @Override // com.jingdong.sdk.perfmonitor.a.c.a
        public void b(long j2, long j3) {
            if (f.this.f15397m != null) {
                f fVar = f.this;
                fVar.f15398n = fVar.f15397m.getUrl();
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface c {
        String getUrl();
    }

    public f(Context context, Reporter reporter, c cVar) {
        super(reporter);
        this.f15397m = cVar;
        this.b = new com.jingdong.sdk.perfmonitor.d.f(context, new a(context));
    }

    @Override // com.jingdong.sdk.perfmonitor.c.d.a
    public void a(Set<Thread> set) {
        if (this.f15396l == null) {
            this.f15396l = new com.jingdong.sdk.perfmonitor.a.e();
        }
        this.f15396l.b(set);
    }

    @Override // com.jingdong.sdk.perfmonitor.c.c.a
    @SuppressLint({"DefaultLocale"})
    public void b(long j2, long j3, long j4) {
        if (this.f15395k == null) {
            this.f15395k = new com.jingdong.sdk.perfmonitor.a.c(new b());
        }
        this.f15395k.a(j2, j3, j4);
    }

    @Override // com.jingdong.sdk.perfmonitor.c.b.a
    public void c(float f2) {
        if (this.f15394j == null) {
            this.f15394j = new com.jingdong.sdk.perfmonitor.a.a();
        }
        this.f15394j.a(Math.round(f2));
    }

    @Override // com.jingdong.sdk.perfmonitor.b.b
    public void j() {
        super.j();
        com.jingdong.sdk.perfmonitor.c.b bVar = this.f15391g;
        if (bVar != null) {
            bVar.h();
        }
        com.jingdong.sdk.perfmonitor.c.c cVar = this.f15392h;
        if (cVar != null) {
            cVar.h();
        }
        com.jingdong.sdk.perfmonitor.c.d dVar = this.f15393i;
        if (dVar != null) {
            dVar.h();
        }
    }

    public void p() {
        long j2 = this.d;
        if (j2 != 0) {
            long j3 = this.f15312e;
            if (j3 != 0 && j3 - j2 >= 1000) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("chId", "3");
                com.jingdong.sdk.perfmonitor.a.a aVar = this.f15394j;
                if (aVar != null) {
                    hashMap.put("cpuInfo", aVar.toString());
                }
                com.jingdong.sdk.perfmonitor.a.c cVar = this.f15395k;
                if (cVar != null) {
                    hashMap.put("memInfo", cVar.toString());
                }
                com.jingdong.sdk.perfmonitor.a.e eVar = this.f15396l;
                if (eVar != null) {
                    hashMap.put("threadInfo", eVar.toString());
                }
                String str = this.f15398n;
                if (str != null) {
                    hashMap.put("url", str);
                }
                f(hashMap);
                this.f15394j = null;
                this.f15396l = null;
                this.f15395k = null;
            }
        }
    }

    public boolean q(@NonNull Activity activity) {
        return h(com.jingdong.sdk.perfmonitor.b.b.d(activity));
    }

    public void r(@NonNull Activity activity) {
        super.i(com.jingdong.sdk.perfmonitor.b.b.d(activity));
        com.jingdong.sdk.perfmonitor.c.b bVar = this.f15391g;
        if (bVar != null) {
            bVar.g();
        }
        com.jingdong.sdk.perfmonitor.c.c cVar = this.f15392h;
        if (cVar != null) {
            cVar.g();
        }
        com.jingdong.sdk.perfmonitor.c.d dVar = this.f15393i;
        if (dVar != null) {
            dVar.g();
        }
    }
}
