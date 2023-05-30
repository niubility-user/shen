package com.jingdong.manto.q;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.jingdong.manto.i.a;
import com.jingdong.manto.q.l;
import com.jingdong.manto.q.n;
import com.jingdong.manto.widget.d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes16.dex */
public class q extends j {

    /* renamed from: f  reason: collision with root package name */
    private String f14089f;

    /* renamed from: g  reason: collision with root package name */
    private String f14090g;

    /* renamed from: h  reason: collision with root package name */
    private LinearLayout f14091h;

    /* renamed from: i  reason: collision with root package name */
    public com.jingdong.manto.widget.d f14092i;

    /* renamed from: j  reason: collision with root package name */
    private FrameLayout f14093j;

    /* renamed from: k  reason: collision with root package name */
    private n f14094k;

    /* renamed from: l  reason: collision with root package name */
    private Map<String, n> f14095l;

    /* renamed from: m  reason: collision with root package name */
    private LinkedList<e> f14096m;

    /* renamed from: n  reason: collision with root package name */
    public d f14097n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements d.h {
        final /* synthetic */ a.h a;

        /* renamed from: com.jingdong.manto.q.q$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class C0661a implements l.u {
            final /* synthetic */ String a;
            final /* synthetic */ int b;

            C0661a(String str, int i2) {
                this.a = str;
                this.b = i2;
            }

            @Override // com.jingdong.manto.q.l.u
            public void onFail() {
            }

            @Override // com.jingdong.manto.q.l.u
            public void onSuccess() {
                HashMap hashMap = new HashMap();
                hashMap.put("pagePath", this.a);
                hashMap.put("text", a.this.a.f13061g.get(this.b).b);
                hashMap.put("index", Integer.valueOf(this.b));
                q qVar = q.this;
                new f(qVar).a(qVar.i()).a(hashMap).a();
            }
        }

        a(a.h hVar) {
            this.a = hVar;
        }

        @Override // com.jingdong.manto.widget.d.h
        public final void a(int i2, String str) {
            q.this.a.c(str, new C0661a(str, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b extends e {
        b() {
            super(q.this);
        }

        @Override // com.jingdong.manto.q.q.e
        void a() {
            q.this.a.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements n.g0 {
        final /* synthetic */ n a;
        final /* synthetic */ e b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ long f14100c;

        c(n nVar, e eVar, long j2) {
            this.a = nVar;
            this.b = eVar;
            this.f14100c = j2;
        }

        @Override // com.jingdong.manto.q.n.g0
        public void onReady() {
            d dVar = q.this.f14097n;
            if (dVar != null) {
                dVar.a();
                q.this.f14097n = null;
            }
            this.a.b(this);
            this.b.run();
            System.currentTimeMillis();
        }
    }

    /* loaded from: classes16.dex */
    public interface d {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public abstract class e implements Runnable {
        private boolean a = false;
        boolean b = false;

        e(q qVar) {
        }

        abstract void a();

        @Override // java.lang.Runnable
        public void run() {
            if ((this.a || this.b) ? false : true) {
                this.a = true;
                a();
            }
        }
    }

    /* loaded from: classes16.dex */
    final class f extends com.jingdong.manto.m.d {
        f(q qVar) {
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onTabItemTap";
        }
    }

    public q(Context context, l lVar) {
        super(context, lVar);
        this.f14094k = this.a.e();
        this.f14095l = new HashMap();
        this.f14096m = new LinkedList<>();
    }

    private com.jingdong.manto.widget.d a(a.h hVar) {
        com.jingdong.manto.widget.d dVar = new com.jingdong.manto.widget.d(getContext(), hVar, this);
        dVar.f14349h = new a(hVar);
        return dVar;
    }

    private n c(String str) {
        n nVar = this.f14094k;
        if (nVar != null) {
            this.f14094k = null;
        } else {
            nVar = this.a.e();
        }
        this.f14095l.put(str, nVar);
        this.f14093j.addView(nVar.l());
        return nVar;
    }

    private void e(String str) {
        n nVar = this.f14095l.get(str);
        nVar.l().setVisibility(4);
        n nVar2 = null;
        for (n nVar3 : this.f14095l.values()) {
            if (nVar3.l().getVisibility() == 0) {
                nVar2 = nVar3;
            }
        }
        nVar.l().setVisibility(0);
        if (nVar2 != null) {
            nVar2.l().setVisibility(8);
        }
        nVar.G();
        if (nVar2 != null) {
            nVar2.E();
        }
    }

    private void n() {
        Iterator<e> it = this.f14096m.iterator();
        while (it.hasNext()) {
            e next = it.next();
            removeCallbacks(next);
            next.b = true;
        }
        this.f14096m.clear();
    }

    @Override // com.jingdong.manto.q.j
    public final void a() {
        super.a();
        n nVar = this.f14094k;
        if (nVar != null) {
            nVar.i();
        }
        Iterator<n> it = this.f14095l.values().iterator();
        while (it.hasNext()) {
            it.next().i();
        }
        n();
    }

    @Override // com.jingdong.manto.q.j
    public void a(String str, String str2, int[] iArr) {
        n nVar = this.f14094k;
        if (nVar != null && j.a(iArr, nVar.hashCode())) {
            this.f14094k.a(str, str2, 0);
        }
        for (n nVar2 : this.f14095l.values()) {
            if (j.a(iArr, nVar2.hashCode())) {
                nVar2.a(str, str2, 0);
            }
        }
    }

    @Override // com.jingdong.manto.q.j
    public boolean a(String str) {
        com.jingdong.manto.widget.d dVar = this.f14092i;
        return dVar != null && dVar.a(str) >= 0;
    }

    @Override // com.jingdong.manto.q.j
    public void b(String str) {
        String b2 = com.jingdong.manto.utils.t.b(str);
        if (TextUtils.equals(this.f14089f, b2)) {
            return;
        }
        com.jingdong.manto.widget.d dVar = this.f14092i;
        int a2 = dVar != null ? dVar.a(b2) : 0;
        if (a2 >= 0) {
            this.f14089f = b2;
            this.f14090g = str;
            com.jingdong.manto.widget.d dVar2 = this.f14092i;
            if (dVar2 != null) {
                dVar2.a(a2);
            }
            if (this.f14095l.get(b2) == null) {
                n c2 = c(b2);
                c2.b(b2);
                m();
                c2.a(new c(c2, new b(), System.currentTimeMillis()));
            }
            n();
            e(b2);
        }
    }

    public boolean d(String str) {
        return this.f14095l.get(com.jingdong.manto.utils.t.b(str)) != null;
    }

    @Override // com.jingdong.manto.q.j
    public final void e() {
        super.e();
        i().E();
    }

    @Override // com.jingdong.manto.q.j
    public final void f() {
        super.f();
        n nVar = this.f14094k;
        if (nVar != null) {
            nVar.F();
        }
        Iterator<n> it = this.f14095l.values().iterator();
        while (it.hasNext()) {
            it.next().F();
        }
    }

    @Override // com.jingdong.manto.q.j
    public final void g() {
        super.g();
        i().G();
    }

    @Override // com.jingdong.manto.q.j
    public View getContentView() {
        View view;
        if (this.f14091h == null) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.f14093j = new FrameLayout(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
            layoutParams.weight = 1.0f;
            this.f14093j.setLayoutParams(layoutParams);
            boolean C = this.a.a.C();
            if (C) {
                com.jingdong.manto.widget.d a2 = a(this.a.a.t.b);
                this.f14092i = a2;
                if (this.a.a.t.b.a) {
                    a2.setVisibility(8);
                }
            }
            if ("top".equals(this.a.a.t.b.b)) {
                if (C) {
                    linearLayout.addView(this.f14092i);
                }
                view = this.f14093j;
            } else {
                linearLayout.addView(this.f14093j);
                if (C) {
                    view = this.f14092i;
                }
                this.f14091h = linearLayout;
            }
            linearLayout.addView(view);
            this.f14091h = linearLayout;
        }
        return this.f14091h;
    }

    @Override // com.jingdong.manto.q.j
    public n i() {
        n nVar = this.f14094k;
        return nVar == null ? this.f14095l.get(this.f14089f) : nVar;
    }

    @Override // com.jingdong.manto.q.j
    public String j() {
        return this.f14090g;
    }

    @Override // com.jingdong.manto.q.j
    public void l() {
        com.jingdong.manto.widget.d dVar = this.f14092i;
        if (dVar != null) {
            dVar.a(this.a.a.t.b);
        }
        a.j windowConfig = getWindowConfig();
        n nVar = this.f14095l.get(com.jingdong.manto.utils.t.b(j()));
        if (nVar != null) {
            nVar.a(windowConfig.f13064e, windowConfig.f13066g);
            nVar.e(windowConfig.d);
            nVar.f(windowConfig.b);
        }
    }
}
