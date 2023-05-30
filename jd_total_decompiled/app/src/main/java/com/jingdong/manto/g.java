package com.jingdong.manto;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import com.jingdong.manto.card.CardLaunchCallback;
import com.jingdong.manto.f;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.m.z0.g;
import com.jingdong.manto.n.f;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.q.i;
import com.jingdong.manto.q.j;
import com.jingdong.manto.q.l;
import com.jingdong.manto.q.n;
import com.jingdong.manto.q.r;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.tencent.smtt.sdk.WebView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g {
    private FrameLayout b;

    /* renamed from: c */
    private com.jingdong.manto.e f13024c;
    private f.b d;

    /* renamed from: e */
    private i f13025e;

    /* renamed from: f */
    private i.c f13026f;
    private final String a = g.class.getSimpleName();

    /* renamed from: g */
    public LinkedList<com.jingdong.manto.f> f13027g = new LinkedList<>();

    /* renamed from: h */
    private volatile boolean f13028h = false;

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.f a;

        a(com.jingdong.manto.f fVar) {
            g.this = r1;
            this.a = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.f fVar = this.a;
            if (fVar != null) {
                fVar.e();
                this.a.d.setVisibility(8);
                if (g.this.b != null) {
                    g.this.b.removeView(this.a.d);
                }
                com.jingdong.manto.pkg.b.g.b(this.a);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ com.jingdong.manto.f a;
        final /* synthetic */ com.jingdong.manto.i.c b;

        /* renamed from: c */
        final /* synthetic */ CardLaunchCallback f13029c;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
                b.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                g.this.a(bVar.a, bVar.b, bVar.f13029c);
            }
        }

        /* renamed from: com.jingdong.manto.g$b$b */
        /* loaded from: classes15.dex */
        class RunnableC0510b implements Runnable {
            RunnableC0510b() {
                b.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                g.this.a(bVar.a, bVar.b, bVar.f13029c);
            }
        }

        b(com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar, CardLaunchCallback cardLaunchCallback) {
            g.this = r1;
            this.a = fVar;
            this.b = cVar;
            this.f13029c = cardLaunchCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (g.this.a) {
                try {
                    g.this.a.wait(800L);
                } catch (InterruptedException e2) {
                    MantoLog.e(g.this.a, e2);
                    if (g.this.f13024c != null && !g.this.f13024c.isFinishing()) {
                        MantoThreadUtils.runOnUIThread(new RunnableC0510b());
                    }
                }
                if (!g.this.f13028h) {
                    g.this.f13028h = false;
                    return;
                }
                if (g.this.f13024c != null && !g.this.f13024c.isFinishing()) {
                    MantoThreadUtils.runOnUIThread(new a());
                }
                g.this.f13028h = false;
            }
        }
    }

    /* loaded from: classes15.dex */
    public class c implements Runnable {
        final /* synthetic */ com.jingdong.manto.f a;
        final /* synthetic */ com.jingdong.manto.i.c b;

        /* renamed from: c */
        final /* synthetic */ CardLaunchCallback f13030c;

        c(com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar, CardLaunchCallback cardLaunchCallback) {
            g.this = r1;
            this.a = fVar;
            this.b = cVar;
            this.f13030c = cardLaunchCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.b(this.a, this.b, this.f13030c);
        }
    }

    /* loaded from: classes15.dex */
    public class d implements f.r {
        final /* synthetic */ CardLaunchCallback a;
        final /* synthetic */ com.jingdong.manto.f b;

        /* renamed from: c */
        final /* synthetic */ com.jingdong.manto.i.c f13031c;
        final /* synthetic */ com.jingdong.manto.f d;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
                d.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = d.this;
                g.this.f13027g.push(dVar.b);
                if (g.this.f13024c != null) {
                    g.this.f13024c.setTaskDescription();
                }
                CardLaunchCallback cardLaunchCallback = d.this.a;
                if (cardLaunchCallback != null) {
                    cardLaunchCallback.onLaunchSuccess();
                }
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            final /* synthetic */ MantoPreLaunchProcess.LaunchError a;

            b(MantoPreLaunchProcess.LaunchError launchError) {
                d.this = r1;
                this.a = launchError;
            }

            @Override // java.lang.Runnable
            public void run() {
                CardLaunchCallback cardLaunchCallback = d.this.a;
                if (cardLaunchCallback != null) {
                    cardLaunchCallback.onLaunchError(this.a);
                }
                if (d.this.b.u()) {
                    return;
                }
                com.jingdong.manto.launch.c.a(this.a);
            }
        }

        d(CardLaunchCallback cardLaunchCallback, com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar, com.jingdong.manto.f fVar2) {
            g.this = r1;
            this.a = cardLaunchCallback;
            this.b = fVar;
            this.f13031c = cVar;
            this.d = fVar2;
        }

        @Override // com.jingdong.manto.f.r
        public void a(MantoPreLaunchProcess.LaunchError launchError) {
            if (g.this.f13024c == null || g.this.f13024c.isFinishing()) {
                return;
            }
            MantoThreadUtils.runOnUIThreadImmediately(new b(launchError));
        }

        @Override // com.jingdong.manto.f.r
        public void a(boolean z) {
            CardLaunchCallback cardLaunchCallback = this.a;
            if (cardLaunchCallback != null) {
                cardLaunchCallback.onPrepareSuccess(z);
            }
            if (g.this.f13024c != null && !g.this.f13024c.isFinishing()) {
                g.this.a(this.b, this.f13031c);
                com.jingdong.manto.f fVar = this.b;
                fVar.f13012c = this.d;
                fVar.b();
                MantoThreadUtils.runOnUIThreadImmediately(new a());
            } else if (this.a != null) {
                MantoPreLaunchProcess.LaunchError launchError = new MantoPreLaunchProcess.LaunchError();
                launchError.errorCode = PkgDetailEntity.OPEN_ERROR;
                launchError.msg = "\u9875\u9762\u72b6\u6001\u5f02\u5e38";
                launchError.title = "\u8fd4\u56de";
                launchError.word = "\u8bf7\u91cd\u8bd5";
                this.a.onLaunchError(launchError);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class e implements Runnable {
        final /* synthetic */ com.jingdong.manto.f a;
        final /* synthetic */ com.jingdong.manto.i.c b;

        e(com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar) {
            g.this = r1;
            this.a = fVar;
            this.b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.b(this.a, this.b);
        }
    }

    /* loaded from: classes15.dex */
    public class f implements Runnable {
        final /* synthetic */ com.jingdong.manto.f a;

        f(com.jingdong.manto.f fVar) {
            g.this = r1;
            this.a = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.f fVar = this.a;
            if (fVar != null) {
                fVar.e();
                if (g.this.b != null) {
                    g.this.b.removeView(this.a.d);
                }
                g.this.f13027g.remove(this.a);
                com.jingdong.manto.pkg.b.g.b(this.a);
                if (g.this.f13027g.size() != 0 || this.a.u()) {
                    return;
                }
                g.this.d();
            }
        }
    }

    /* renamed from: com.jingdong.manto.g$g */
    /* loaded from: classes15.dex */
    public class C0511g implements i.c {
        C0511g() {
            g.this = r1;
        }

        @Override // com.jingdong.manto.q.i.c
        public void a(int i2) {
            int pixel2dip = MantoDensityUtils.pixel2dip(i2);
            try {
                com.jingdong.manto.f g2 = g.this.g();
                g.C0640g c0640g = new g.C0640g();
                HashMap hashMap = new HashMap();
                hashMap.put("height", Integer.valueOf(pixel2dip));
                c0640g.a(g2.f13015g).a(hashMap).a();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // com.jingdong.manto.q.i.c
        public void g() {
            try {
                g.this.f13025e.a(false);
                g.this.f13025e.b(g.this.f13026f);
                View currentFocus = g.this.f13024c.getActivity().getCurrentFocus();
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }
                com.jingdong.manto.f g2 = g.this.g();
                g.C0640g c0640g = new g.C0640g();
                HashMap hashMap = new HashMap();
                hashMap.put("height", 0);
                c0640g.a(g2.f13015g).a(hashMap).a();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar) {
        try {
            if (TextUtils.equals(cVar.f13082e, "14") && TextUtils.equals(cVar.f13091n, "native_debug")) {
                WebView.setWebContentsDebuggingEnabled(true);
                com.jingdong.manto.s.b.e().a(true);
                com.jingdong.manto.s.b.e().b(fVar, cVar.a);
            } else if (!TextUtils.equals(cVar.f13082e, "5")) {
                WebView.setWebContentsDebuggingEnabled(false);
                com.jingdong.manto.s.a.f().a(false);
            } else {
                com.jingdong.manto.s.a.f().a(false);
                JSONObject jSONObject = new JSONObject(cVar.f13090m);
                if (TextUtils.equals("1", jSONObject.optString("remoteDebug"))) {
                    WebView.setWebContentsDebuggingEnabled(true);
                    com.jingdong.manto.s.a.f().a(true);
                    com.jingdong.manto.s.a.f().b(fVar, cVar.a);
                } else if (TextUtils.equals("0", jSONObject.optString("remoteDebug"))) {
                    com.jingdong.manto.s.d.c().c(cVar.a);
                }
            }
        } catch (Exception unused) {
        }
    }

    public void a(com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar, CardLaunchCallback cardLaunchCallback) {
        if (this.f13024c == null) {
            if (cardLaunchCallback != null) {
                MantoPreLaunchProcess.LaunchError launchError = new MantoPreLaunchProcess.LaunchError();
                launchError.errorCode = PkgDetailEntity.OPEN_ERROR;
                launchError.msg = "\u65e0\u6cd5\u6253\u5f00\u5c0f\u7a0b\u5e8f";
                launchError.word = "\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
                launchError.title = "\u8fd4\u56de";
                cardLaunchCallback.onLaunchError(launchError);
                return;
            }
            return;
        }
        com.jingdong.manto.f fVar2 = new com.jingdong.manto.f(this.f13024c, this);
        if (cardLaunchCallback != null) {
            cardLaunchCallback.onCreateRuntime();
        }
        this.f13024c.showSplashView(cVar.b, TextUtils.isEmpty(cVar.r) ? cVar.f13081c : cVar.r, com.jingdong.manto.k.a.b().a());
        if (cardLaunchCallback != null) {
            cardLaunchCallback.onShowSplash();
        }
        fVar2.a(cVar, new d(cardLaunchCallback, fVar2, cVar, fVar));
        if (cardLaunchCallback != null) {
            cardLaunchCallback.onInitRuntime();
        }
        FrameLayout frameLayout = this.b;
        if (frameLayout != null) {
            frameLayout.addView(fVar2.d);
        }
    }

    public void b(com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar) {
        if (!MantoThreadUtils.isMainThread()) {
            MantoThreadUtils.runOnUIThread(new e(fVar, cVar));
            return;
        }
        com.jingdong.manto.f a2 = a(cVar.a);
        this.f13027g.remove(a2);
        this.f13027g.push(a2);
        a2.d.setVisibility(0);
        FrameLayout frameLayout = this.b;
        if (frameLayout != null) {
            frameLayout.bringChildToFront(a2.d);
        }
        a2.f13012c = fVar;
        if (a2.y) {
            a2.d(cVar.f13083f);
        }
        if (fVar != null) {
            fVar.F();
            a2.G();
        }
    }

    public com.jingdong.manto.f a(String str) {
        Iterator<com.jingdong.manto.f> it = this.f13027g.iterator();
        while (it.hasNext()) {
            com.jingdong.manto.f next = it.next();
            if (next.f13017i.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public final void a() {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(this.f13027g);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            b((com.jingdong.manto.f) it.next());
        }
        FrameLayout frameLayout = this.b;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            this.b = null;
        }
        i iVar = this.f13025e;
        if (iVar != null) {
            iVar.c();
            this.f13025e = null;
        }
    }

    public void a(com.jingdong.manto.e eVar, f.b bVar, FrameLayout frameLayout) {
        this.f13024c = eVar;
        this.b = frameLayout;
        this.d = bVar;
    }

    public void a(boolean z) {
        Activity activity;
        l lVar;
        j firstPage;
        n i2;
        r s;
        com.jingdong.manto.f g2 = g();
        if (g2 != null && (lVar = g2.f13014f) != null && lVar != null && (firstPage = lVar.getFirstPage()) != null && (i2 = firstPage.i()) != null && (s = i2.s()) != null) {
            s.setWebFocus(z);
        }
        if (this.f13025e == null) {
            com.jingdong.manto.e eVar = this.f13024c;
            if (eVar == null || (activity = eVar.getActivity()) == null) {
                return;
            }
            this.f13025e = new i(activity);
        }
        this.f13025e.a(true);
        if (this.f13026f == null) {
            this.f13026f = new C0511g();
        }
        this.f13025e.a(this.f13026f);
    }

    public boolean a(com.jingdong.manto.f fVar) {
        LinkedList<com.jingdong.manto.f> linkedList = this.f13027g;
        return linkedList != null && linkedList.contains(fVar);
    }

    public void b() {
        Iterator<com.jingdong.manto.f> it = this.f13027g.iterator();
        while (it.hasNext()) {
            MantoThreadUtils.runOnUIThread(new a(it.next()));
        }
        this.f13027g.clear();
    }

    public void b(com.jingdong.manto.f fVar) {
        MantoThreadUtils.runOnUIThread(new f(fVar));
    }

    public void b(com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar, CardLaunchCallback cardLaunchCallback) {
        com.jingdong.manto.r.d.a();
        if (!MantoThreadUtils.isMainThread()) {
            MantoThreadUtils.runOnUIThread(new c(fVar, cVar, cardLaunchCallback));
            return;
        }
        if (cVar.b()) {
            a();
        } else {
            Iterator<com.jingdong.manto.f> it = this.f13027g.iterator();
            while (it.hasNext()) {
                com.jingdong.manto.f next = it.next();
                if (next.w()) {
                    b(next);
                }
            }
        }
        if (fVar != null) {
            fVar.F();
        }
        if (this.f13028h) {
            com.jingdong.manto.b.d().networkIO().execute(new b(fVar, cVar, cardLaunchCallback));
        } else {
            a(fVar, cVar, cardLaunchCallback);
        }
    }

    public void b(boolean z) {
        this.f13028h = z;
        synchronized (this.a) {
            this.a.notifyAll();
        }
    }

    public com.jingdong.manto.f c(com.jingdong.manto.f fVar) {
        int indexOf = this.f13027g.indexOf(fVar);
        int size = this.f13027g.size() - 1;
        if (indexOf == -1 || indexOf >= size) {
            return null;
        }
        return this.f13027g.get(indexOf + 1);
    }

    public final void c() {
        com.jingdong.manto.e eVar = this.f13024c;
        if (eVar == null || eVar.getActivity() == null) {
            return;
        }
        if (com.jingdong.manto.b.o() || this.f13024c.isSameToHostTask() || (g() != null && g().x())) {
            this.f13024c.getActivity().finish();
        } else {
            try {
                this.f13024c.getActivity().moveTaskToBack(true);
            } catch (Exception unused) {
            }
        }
    }

    public void c(com.jingdong.manto.f fVar, com.jingdong.manto.i.c cVar, CardLaunchCallback cardLaunchCallback) {
        if (cVar != null) {
            if (fVar == null) {
                Iterator<com.jingdong.manto.f> it = this.f13027g.iterator();
                while (it.hasNext()) {
                    com.jingdong.manto.f next = it.next();
                    next.e();
                    next.d.setVisibility(8);
                    FrameLayout frameLayout = this.b;
                    if (frameLayout != null) {
                        frameLayout.removeView(next.d);
                    }
                    com.jingdong.manto.pkg.b.g.b(next);
                }
                this.f13027g.clear();
            }
            if (a(cVar.a) == null || cVar.c()) {
                b(fVar, cVar, cardLaunchCallback);
            } else {
                b(fVar, cVar);
            }
        }
    }

    public final void d() {
        com.jingdong.manto.e eVar = this.f13024c;
        if (eVar == null || eVar.getActivity() == null) {
            return;
        }
        this.f13024c.getActivity().finish();
    }

    public f.b e() {
        return this.d;
    }

    public int f() {
        return this.f13027g.size();
    }

    public com.jingdong.manto.f g() {
        return this.f13027g.peek();
    }
}
