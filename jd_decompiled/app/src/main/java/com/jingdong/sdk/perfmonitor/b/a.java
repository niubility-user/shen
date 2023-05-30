package com.jingdong.sdk.perfmonitor.b;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jingdong.sdk.perfmonitor.Reporter;
import com.jingdong.sdk.perfmonitor.b.e;
import java.lang.ref.SoftReference;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a extends e<com.jingdong.sdk.perfmonitor.d.a> {

    /* renamed from: j */
    private SoftReference<Activity> f15303j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.perfmonitor.b.a$a */
    /* loaded from: classes12.dex */
    public class RunnableC0740a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ SoftReference f15304g;

        RunnableC0740a(SoftReference softReference) {
            a.this = r1;
            this.f15304g = softReference;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f15303j = this.f15304g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class b implements Runnable {
        b() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f15303j = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class c implements Runnable {
        c() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            e<T>.o oVar;
            a aVar = a.this;
            if (aVar.f15351g == null || (oVar = aVar.f15352h) == null) {
                return;
            }
            if (!TextUtils.isEmpty(oVar.b)) {
                try {
                    com.jingdong.sdk.perfmonitor.a.b bVar = a.this.f15351g;
                    if (bVar.f15286h == null) {
                        bVar.f15286h = new JSONObject();
                    }
                    a aVar2 = a.this;
                    aVar2.f15351g.f15286h.put("prePageExt", aVar2.f15352h.b);
                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
            a aVar3 = a.this;
            com.jingdong.sdk.perfmonitor.a.b bVar2 = aVar3.f15351g;
            e<T>.o oVar2 = aVar3.f15352h;
            bVar2.f15285g = oVar2.a;
            bVar2.f15283e = oVar2.f15389c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class d implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Context f15308g;

        d(Context context) {
            a.this = r1;
            this.f15308g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.G() == this.f15308g) {
                a.this.v(e.p.STARTUP);
            }
        }
    }

    public a(Context context, Reporter reporter) {
        super(reporter);
        this.b = new com.jingdong.sdk.perfmonitor.d.a(context);
    }

    public Activity G() {
        SoftReference<Activity> softReference = this.f15303j;
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    public e.p H(@NonNull Activity activity) {
        T t = this.b;
        if (t == 0) {
            return null;
        }
        return ((com.jingdong.sdk.perfmonitor.d.a) t).c(com.jingdong.sdk.perfmonitor.b.b.d(activity));
    }

    public void I(Context context) {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new d(context));
    }

    public boolean J(@NonNull Activity activity) {
        return h(com.jingdong.sdk.perfmonitor.b.b.d(activity));
    }

    public void K(@NonNull Activity activity) {
        i(com.jingdong.sdk.perfmonitor.b.b.d(activity));
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new RunnableC0740a(new SoftReference(activity)));
    }

    @Override // com.jingdong.sdk.perfmonitor.b.e
    public void s() {
        super.s();
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new c());
    }

    @Override // com.jingdong.sdk.perfmonitor.b.e
    public void u() {
        super.u();
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new b());
    }
}
