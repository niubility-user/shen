package com.jingdong.manto;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.hms.actions.SearchIntents;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.card.CardMode;
import com.jingdong.manto.card.MantoCardView;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.launch.UIConfig;
import com.jingdong.manto.launch.h;
import com.jingdong.manto.mainproc.MainProcMessage;
import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.n.f;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.ipc.MantoPkgUpdate;
import com.jingdong.manto.preload.b;
import com.jingdong.manto.q.l;
import com.jingdong.manto.q.s;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends AppLifeCycle.Listener implements MantoAcrossMessage.Listener {
    private static final String L = "f";
    private WeakReference<MantoAuthDialog> A;
    public boolean B;
    public boolean C;
    public boolean D;
    public b.d E;
    public com.jingdong.manto.r.e F;
    public com.jingdong.manto.n.f G;
    private MantoAcrossMessage H;
    private boolean I;
    private JSONArray K;
    private UIConfig a;
    private com.jingdong.manto.e b;

    /* renamed from: c */
    f f13012c;
    public FrameLayout d;

    /* renamed from: e */
    public com.jingdong.manto.g f13013e;

    /* renamed from: f */
    public com.jingdong.manto.q.l f13014f;

    /* renamed from: g */
    public com.jingdong.manto.h f13015g;

    /* renamed from: h */
    public PkgDetailEntity f13016h;

    /* renamed from: i */
    public String f13017i;

    /* renamed from: j */
    public String f13018j;

    /* renamed from: k */
    public String f13019k;

    /* renamed from: m */
    private List<com.jingdong.manto.launch.a> f13021m;

    /* renamed from: n */
    private boolean f13022n;
    public volatile boolean o;
    public PkgDetailEntity p;
    private volatile String q;
    public com.jingdong.manto.i.c r;
    public com.jingdong.manto.i.e s;
    public com.jingdong.manto.i.a t;
    private boolean u;
    private Bundle v;
    private List<com.jingdong.manto.m.n0.a> w;
    private boolean x;
    public boolean y;
    public boolean z;
    private com.jingdong.manto.message.a J = new i();

    /* renamed from: l */
    Handler f13020l = new Handler();

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ MantoAuthDialog a;

        a(MantoAuthDialog mantoAuthDialog) {
            f.this = r1;
            this.a = mantoAuthDialog;
        }

        @Override // java.lang.Runnable
        public final void run() {
            f.this.a(this.a);
        }
    }

    /* loaded from: classes15.dex */
    public class b implements Runnable {
        b() {
            f.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            long j2 = uptimeMillis + 20;
            float dMWidthPixels = (float) (MantoDensityUtils.getDMWidthPixels() - 4);
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, j2, 0, dMWidthPixels, 5.0f, 0);
            MotionEvent obtain2 = MotionEvent.obtain(uptimeMillis, j2 + 50, 3, dMWidthPixels, 5.0f, 0);
            if (f.this.b != null) {
                f.this.b.dispatchTouchEvent(obtain);
                f.this.b.dispatchTouchEvent(obtain2);
            }
            obtain.recycle();
            obtain2.recycle();
        }
    }

    /* loaded from: classes15.dex */
    public class c implements Runnable {
        c() {
            f.this = r1;
        }

        @Override // java.lang.Runnable
        public final void run() {
            f fVar = f.this;
            fVar.f13013e.b(fVar);
        }
    }

    /* loaded from: classes15.dex */
    public class d implements Runnable {
        final /* synthetic */ Dialog a;

        d(Dialog dialog) {
            f.this = r1;
            this.a = dialog;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.a(this.a);
        }
    }

    /* loaded from: classes15.dex */
    public class e implements Runnable {
        final /* synthetic */ f a;

        e(f fVar) {
            f.this = r1;
            this.a = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar = f.this;
            fVar.f13013e.b(fVar);
            f fVar2 = this.a;
            if (fVar2 != null) {
                fVar2.G();
            }
        }
    }

    /* renamed from: com.jingdong.manto.f$f */
    /* loaded from: classes15.dex */
    public class C0509f extends AnimatorListenerAdapter {
        final /* synthetic */ Runnable a;

        C0509f(f fVar, Runnable runnable) {
            this.a = runnable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            Runnable runnable = this.a;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class g implements Runnable {
        g() {
            f.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar = f.this;
            if (fVar.F == null) {
                com.jingdong.manto.r.d.e(fVar);
                MantoLog.d(f.L, "setPerformancePanel");
                f.this.F = new com.jingdong.manto.r.e(com.jingdong.a.g());
                f fVar2 = f.this;
                if (fVar2.F != null) {
                    com.jingdong.manto.r.d.d(fVar2);
                    f fVar3 = f.this;
                    fVar3.d.addView(fVar3.F);
                    f.this.F.setVisibility(0);
                    f.this.F.setAlpha(0.0f);
                    f.this.F.animate().alpha(1.0f).setDuration(500L).setStartDelay(500L).setListener(null);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class h implements h.e {
        final /* synthetic */ r a;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            final /* synthetic */ boolean a;

            a(boolean z) {
                h.this = r1;
                this.a = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (f.this.b != null) {
                    f.this.b.hideLoading();
                }
                h.this.a.a(this.a);
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            final /* synthetic */ MantoPreLaunchProcess.LaunchError a;

            b(MantoPreLaunchProcess.LaunchError launchError) {
                h.this = r1;
                this.a = launchError;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (f.this.b != null) {
                    f.this.b.hideLoading();
                }
                h.this.a.a(this.a);
            }
        }

        h(r rVar) {
            f.this = r1;
            this.a = rVar;
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(long j2, long j3, boolean z) {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(MantoPreLaunchProcess.LaunchError launchError) {
            MantoThreadUtils.runOnUIThread(new b(launchError));
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(PkgDetailEntity pkgDetailEntity) {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(boolean z) {
            if (this.a != null) {
                MantoThreadUtils.runOnUIThread(new a(z));
            }
        }
    }

    /* loaded from: classes15.dex */
    public class i implements com.jingdong.manto.message.a {
        i() {
            f.this = r1;
        }

        @Override // com.jingdong.manto.message.a
        public void a(String str) {
        }

        @Override // com.jingdong.manto.message.a
        public void b(String str) {
            f fVar = f.this;
            fVar.G.a(fVar.f13017i, fVar.r.f13082e);
        }
    }

    /* loaded from: classes15.dex */
    public class j implements h.e {
        final /* synthetic */ r a;
        final /* synthetic */ long b;

        j(r rVar, long j2) {
            f.this = r1;
            this.a = rVar;
            this.b = j2;
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(long j2, long j3, boolean z) {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(MantoPreLaunchProcess.LaunchError launchError) {
            f.this.a(launchError, this.a);
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(PkgDetailEntity pkgDetailEntity) {
            if (pkgDetailEntity != null) {
                f.this.o = true;
                f.this.p = pkgDetailEntity;
            }
            a(false);
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(boolean z) {
            f fVar;
            long currentTimeMillis = System.currentTimeMillis();
            f fVar2 = f.this;
            fVar2.t = com.jingdong.manto.i.a.a(fVar2);
            com.jingdong.manto.r.f.a(f.this, "readPkgConfigTime", System.currentTimeMillis() - currentTimeMillis);
            f fVar3 = f.this;
            if (fVar3.t == null) {
                fVar3.e(fVar3);
                MantoPreLaunchProcess.LaunchError launchError = new MantoPreLaunchProcess.LaunchError();
                launchError.errorCode = PkgDetailEntity.OPEN_ERROR;
                launchError.msg = "\u65e0\u6cd5\u6253\u5f00\u5c0f\u7a0b\u5e8f";
                launchError.word = "\u6253\u5f00\u5c0f\u7a0b\u5e8f\u51fa\u9519\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
                launchError.title = "\u8fd4\u56de";
                f.this.a(launchError, this.a);
                return;
            }
            r rVar = this.a;
            if (rVar != null) {
                rVar.a(z);
            }
            long currentTimeMillis2 = System.currentTimeMillis() - this.b;
            if (z) {
                fVar = f.this;
            } else {
                fVar = f.this;
                currentTimeMillis2 = 0;
            }
            com.jingdong.manto.r.d.a(fVar, 21, currentTimeMillis2);
        }
    }

    /* loaded from: classes15.dex */
    public class k implements h.f {
        k() {
            f.this = r1;
        }

        @Override // com.jingdong.manto.launch.h.f
        public void a(long j2) {
            com.jingdong.manto.r.f.a(f.this, "downloadTime", j2);
        }

        @Override // com.jingdong.manto.launch.h.f
        public void b(long j2) {
            com.jingdong.manto.r.f.a(f.this, "checkPkgTime", j2);
        }

        @Override // com.jingdong.manto.launch.h.f
        public void c(long j2) {
            com.jingdong.manto.r.f.a(f.this, "unZipTime", j2);
        }
    }

    /* loaded from: classes15.dex */
    public class l implements h.c {
        l() {
            f.this = r1;
        }

        @Override // com.jingdong.manto.launch.h.c
        public void a() {
            f.this.a(com.jingdong.manto.launch.b.b());
        }

        @Override // com.jingdong.manto.launch.h.c
        public void a(boolean z) {
            f.this.a(com.jingdong.manto.launch.b.a(true));
        }

        @Override // com.jingdong.manto.launch.h.c
        public void b() {
            f.this.a(com.jingdong.manto.launch.b.a());
        }
    }

    /* loaded from: classes15.dex */
    public class m implements Runnable {
        final /* synthetic */ int a;

        /* loaded from: classes15.dex */
        class a implements DialogInterface.OnClickListener {
            a() {
                m.this = r1;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (f.this.b != null) {
                    f.this.b.removeSplashView();
                }
                f.this.g();
            }
        }

        m(int i2) {
            f.this = r1;
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = new a();
            if (f.this.b != null) {
                com.jingdong.manto.widget.dialog.a.a(f.this.b.getActivity(), null, String.format(com.jingdong.a.g().getText(R.string.manto_open_error_msg_code).toString(), Integer.valueOf(this.a)), "\u786e\u5b9a", null, aVar, null, null, null, null).show();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class n implements b.e {
        n() {
            f.this = r1;
        }

        @Override // com.jingdong.manto.preload.b.e
        public void onReady() {
            f.this.D = true;
            com.jingdong.manto.r.f.a(f.this, "initEnvTime", System.currentTimeMillis() - com.jingdong.manto.r.d.f14141l);
            f.this.H();
        }
    }

    /* loaded from: classes15.dex */
    public class o implements s.c {
        final /* synthetic */ long a;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
                o.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                if (fVar.B) {
                    return;
                }
                fVar.d.addView(fVar.f13014f);
                String m2 = f.this.m();
                long currentTimeMillis = System.currentTimeMillis();
                com.jingdong.manto.q.l.c(f.this.f13014f, m2, com.jingdong.manto.q.m.APP_LAUNCH, null);
                com.jingdong.manto.r.f.a(f.this, "navigatePageTime", System.currentTimeMillis() - currentTimeMillis);
                com.jingdong.manto.r.f.a(f.this, "jsInjectTime", System.currentTimeMillis() - com.jingdong.manto.r.d.f14141l);
                com.jingdong.manto.r.d.f14142m = System.currentTimeMillis();
                com.jingdong.manto.r.d.b(f.this);
                f fVar2 = f.this;
                PkgDetailEntity pkgDetailEntity = fVar2.f13016h;
                com.jingdong.manto.i.c cVar = fVar2.r;
                com.jingdong.manto.r.f.a(pkgDetailEntity, cVar != null ? cVar.f13091n : "0", "", "appResourcePrepared");
                com.jingdong.manto.r.d.f14140k = System.currentTimeMillis();
                if (f.this.b != null) {
                    f.this.b.removeSplashView();
                }
                f.this.M();
            }
        }

        o(long j2) {
            f.this = r1;
            this.a = j2;
        }

        @Override // com.jingdong.manto.q.s.c
        public void a() {
            f.this.f13014f = new com.jingdong.manto.q.l(com.jingdong.a.g(), f.this);
            f.this.f13015g.z();
            com.jingdong.manto.r.f.a(f.this, "preparePageTime", System.currentTimeMillis() - this.a);
            MantoThreadUtils.runOnUIThread(new a());
            if (f.this.y() || f.this.x()) {
                return;
            }
            f fVar = f.this;
            fVar.G.a(fVar.o());
        }
    }

    /* loaded from: classes15.dex */
    public class p implements r {

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
                p.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.b();
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            final /* synthetic */ MantoPreLaunchProcess.LaunchError a;

            b(p pVar, MantoPreLaunchProcess.LaunchError launchError) {
                this.a = launchError;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.jingdong.manto.launch.c.a(this.a);
            }
        }

        p() {
            f.this = r1;
        }

        @Override // com.jingdong.manto.f.r
        public void a(MantoPreLaunchProcess.LaunchError launchError) {
            if (f.this.b == null || f.this.b.isFinishing() || f.this.u()) {
                return;
            }
            MantoThreadUtils.runOnUIThread(new b(this, launchError));
        }

        @Override // com.jingdong.manto.f.r
        public void a(boolean z) {
            f.this.n().post(new a());
        }
    }

    /* loaded from: classes15.dex */
    public class q implements Runnable {
        final /* synthetic */ com.jingdong.manto.widget.h.a a;

        q(com.jingdong.manto.widget.h.a aVar) {
            f.this = r1;
            this.a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.a(this.a);
        }
    }

    /* loaded from: classes15.dex */
    public interface r {
        void a(MantoPreLaunchProcess.LaunchError launchError);

        void a(boolean z);
    }

    /* loaded from: classes15.dex */
    public class s implements f.c {
        public s() {
            f.this = r1;
        }

        @Override // com.jingdong.manto.n.f.c
        public void a() {
            f.this.f();
        }
    }

    public f(com.jingdong.manto.e eVar, com.jingdong.manto.g gVar) {
        this.b = eVar;
        this.f13013e = gVar;
        FrameLayout frameLayout = new FrameLayout(com.jingdong.a.g());
        this.d = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        com.jingdong.manto.n.f fVar = new com.jingdong.manto.n.f();
        this.G = fVar;
        fVar.a(gVar.hashCode(), o(), eVar.getClass().getName(), new s(), gVar.e());
        this.H = new MantoAcrossMessage();
    }

    private void E() {
        String str;
        JSONObject a2;
        com.jingdong.manto.q.f fVar = new com.jingdong.manto.q.f();
        HashMap hashMap = new HashMap();
        com.jingdong.manto.i.d dVar = this.r.f13084g;
        if (dVar != null && (a2 = dVar.a()) != null) {
            hashMap.put("referrerInfo", a2);
        }
        try {
            hashMap.put("path", this.f13013e.g().f13014f.getFirstPage().j());
        } catch (Exception unused) {
            hashMap.put("path", "");
        }
        com.jingdong.manto.i.c cVar = this.r;
        if (cVar != null && (str = cVar.f13090m) != null) {
            hashMap.put(SearchIntents.EXTRA_QUERY, str);
        }
        hashMap.put(BaseEvent.SCENE, MantoStringUtils.isEmpty(this.r.f13091n) ? "0" : this.r.f13091n);
        if (!hashMap.containsKey("referrerInfo")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("appId", "");
                jSONObject.put("extraData", "");
                hashMap.put("referrerInfo", jSONObject);
            } catch (JSONException e2) {
                MantoLog.e(L, e2);
            }
        }
        if (!hashMap.containsKey("path")) {
            hashMap.put("path", "");
        }
        if (!hashMap.containsKey(SearchIntents.EXTRA_QUERY)) {
            hashMap.put(SearchIntents.EXTRA_QUERY, new JSONObject());
        }
        MantoUtils.mapToJson(hashMap);
        fVar.f13315c = new JSONObject(hashMap).toString();
        fVar.a(this.f13015g).a();
    }

    public void H() {
        com.jingdong.manto.e eVar;
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.B && this.D && this.C) {
            if (this.E == null) {
                this.E = com.jingdong.manto.preload.b.g();
            }
            if (this.E == null) {
                this.E = new b.d(new com.jingdong.manto.h(), new com.jingdong.manto.q.n());
            }
            com.jingdong.manto.h hVar = this.E.a;
            this.f13015g = hVar;
            hVar.f13035f = this;
            if (this.y || (eVar = this.b) == null) {
                return;
            }
            eVar.hideSplash(new o(currentTimeMillis));
            this.y = true;
            AppLifeCycle.notifyOnAppCreate(this.f13017i);
        }
    }

    public void M() {
        if (this.s.r) {
            com.jingdong.manto.sdk.thread.a.a(new g());
        }
    }

    private void Q() {
        com.jingdong.manto.q.j firstPage;
        com.jingdong.manto.q.n i2;
        com.jingdong.manto.q.l lVar = this.f13014f;
        if (lVar == null || (firstPage = lVar.getFirstPage()) == null || (i2 = firstPage.i()) == null) {
            return;
        }
        i2.O();
    }

    private void a(Animator animator, Runnable runnable) {
        animator.addListener(new C0509f(this, runnable));
        animator.start();
    }

    public void a(com.jingdong.manto.launch.a aVar) {
        boolean c2 = com.jingdong.manto.launch.b.c();
        boolean a2 = com.jingdong.manto.launch.b.a(this.f13016h);
        boolean x = x();
        boolean u = u();
        if (!c2 || !a2 || aVar == null || x || u) {
            return;
        }
        if (this.f13015g != null && this.f13022n) {
            h();
            this.f13015g.a(aVar.a, aVar.b, 0);
            return;
        }
        if (this.f13021m == null) {
            this.f13021m = new CopyOnWriteArrayList();
        }
        this.f13021m.add(aVar);
    }

    private void b(int i2) {
        MantoThreadUtils.runOnUIThread(new m(i2));
    }

    private void d() {
        com.jingdong.manto.r.f.a(this.f13017i);
        com.jingdong.manto.r.e eVar = this.F;
        if (eVar != null) {
            if (eVar.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.F.getParent()).removeView(this.F);
            }
            com.jingdong.manto.r.d.a(this.f13017i);
            this.F = null;
        }
    }

    public void e(f fVar) {
        try {
            if (com.jingdong.manto.i.a.a(fVar) == null) {
                String pkgPath = PkgManager.getPkgPath(this.f13016h);
                if (TextUtils.isEmpty(pkgPath)) {
                    return;
                }
                File file = new File(pkgPath);
                if (file.exists()) {
                    com.jingdong.manto.utils.s.b(file);
                }
            }
        } catch (Exception unused) {
        }
    }

    private void h() {
        List<com.jingdong.manto.launch.a> list;
        if (!this.f13022n || (list = this.f13021m) == null || list.isEmpty()) {
            return;
        }
        for (com.jingdong.manto.launch.a aVar : this.f13021m) {
            if (aVar != null) {
                this.f13015g.a(aVar.a, aVar.b, 0);
            }
        }
        this.f13021m.clear();
    }

    public int o() {
        if (u()) {
            return 2;
        }
        return y() ? 1 : 0;
    }

    private f q() {
        return this.f13013e.c(this);
    }

    public boolean A() {
        UIConfig uIConfig = this.a;
        return uIConfig == null || !uIConfig.isHideCapsule();
    }

    public boolean B() {
        UIConfig uIConfig = this.a;
        return uIConfig == null || !uIConfig.isHideNavigationBar();
    }

    public boolean C() {
        UIConfig uIConfig = this.a;
        return uIConfig == null || !uIConfig.isHideTabBar();
    }

    public void D() {
        com.jingdong.manto.q.l.c(this.f13014f, TextUtils.isEmpty(this.r.f13083f) ? this.t.a : this.r.f13083f, com.jingdong.manto.q.m.RE_LAUNCH, null);
    }

    public final void F() {
        if (this.y) {
            com.jingdong.manto.m.p0.e.b.a(this.f13015g, true);
            new com.jingdong.manto.q.e().a(this.f13015g).a();
            com.jingdong.manto.q.l lVar = this.f13014f;
            if (lVar != null && lVar.getFirstPage() != null) {
                this.f13014f.getFirstPage().e();
            }
            AppLifeCycle.notifyOnAppPause(this.f13017i);
        }
    }

    public final void G() {
        if (this.y) {
            if (this.B) {
                K();
            }
            com.jingdong.manto.m.p0.e.b.b(this.f13015g, true);
            E();
            this.G.a(this.f13017i, this.r.f13082e);
            com.jingdong.manto.q.l lVar = this.f13014f;
            if (lVar != null && lVar.getFirstPage() != null) {
                this.f13014f.getFirstPage().g();
            }
            AppLifeCycle.notifyOnAppResume(this.f13017i);
        }
        try {
            MantoThreadUtils.post(new b(), 20);
        } catch (Exception unused) {
        }
    }

    public void I() {
        com.jingdong.manto.q.j firstPage;
        com.jingdong.manto.q.n i2;
        com.jingdong.manto.q.l lVar = this.f13014f;
        if (lVar == null || (firstPage = lVar.getFirstPage()) == null || (i2 = firstPage.i()) == null) {
            return;
        }
        this.f13014f.a(i2.r(), (l.u) null);
    }

    public synchronized void J() {
        this.E = null;
    }

    public void K() {
        e();
        a(this.r, new p());
    }

    public void L() {
        this.f13022n = true;
        if (this.o) {
            h();
        }
    }

    public boolean N() {
        PkgDetailEntity pkgDetailEntity;
        return com.jingdong.manto.b.a || ((pkgDetailEntity = this.f13016h) != null && (TextUtils.equals(pkgDetailEntity.type, "2") || TextUtils.equals(this.f13016h.type, "3") || TextUtils.equals(this.f13016h.type, "13") || TextUtils.equals(this.f13016h.type, "5")));
    }

    public boolean O() {
        PkgDetailEntity pkgDetailEntity;
        return com.jingdong.manto.b.a || !((pkgDetailEntity = this.f13016h) == null || TextUtils.equals(pkgDetailEntity.type, "1"));
    }

    public boolean P() {
        com.jingdong.manto.i.a aVar = this.t;
        if (aVar == null) {
            return false;
        }
        return aVar.f13051i;
    }

    public String a(String str) {
        com.jingdong.manto.i.a aVar = this.t;
        if (aVar == null) {
            return null;
        }
        return aVar.a(str);
    }

    public void a(int i2) {
        this.f13014f.a(i2);
    }

    public void a(int i2, boolean z, boolean z2) {
        this.f13014f.a(i2, z, z2);
    }

    public final void a(Dialog dialog) {
        if (!com.jingdong.manto.sdk.thread.a.b()) {
            com.jingdong.manto.sdk.thread.a.a(new d(dialog));
            return;
        }
        com.jingdong.manto.e eVar = this.b;
        if (eVar == null || eVar.isFinishing() || dialog == null || this.B) {
            return;
        }
        dialog.show();
    }

    public void a(Bundle bundle) {
        this.v = bundle;
    }

    public void a(f fVar, JSONObject jSONObject) {
        f fVar2;
        com.jingdong.manto.g gVar = this.f13013e;
        if (gVar != null && gVar.f() == 1 && this.f13013e.a(this)) {
            if (u()) {
                return;
            }
            this.f13013e.d();
            return;
        }
        f q2 = q();
        if (q2 != null && (fVar2 = fVar.f13012c) != null && fVar2 == q2) {
            com.jingdong.manto.i.d dVar = new com.jingdong.manto.i.d();
            dVar.a = fVar.f13017i;
            dVar.d = 3;
            dVar.f13093e = jSONObject == null ? null : jSONObject.toString();
            com.jingdong.manto.i.c cVar = q2.r;
            com.jingdong.manto.i.d dVar2 = cVar.f13084g;
            if (dVar2 != null) {
                dVar2.a(dVar);
            } else {
                cVar.f13084g = dVar;
            }
        }
        e eVar = new e(q2);
        com.jingdong.manto.q.l lVar = fVar.f13014f;
        if (lVar != null && lVar.getFirstPage() != null && fVar.f13014f.getFirstPage().f14037c) {
            eVar.run();
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.d, "translationX", 0.0f, r11.getWidth());
        ofFloat.setDuration(250L);
        a(ofFloat, eVar);
        if (q2 != null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(q2.d, "translationX", -(r11.getWidth() * 0.25f), 0.0f);
            ofFloat2.setDuration(250L);
            q2.a(ofFloat2, (Runnable) null);
        }
    }

    public void a(com.jingdong.manto.i.c cVar, r rVar) {
        if (cVar == null) {
            return;
        }
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin != null) {
            this.z = iLogin.hasLogin();
        }
        if (!this.z) {
            this.z = com.jingdong.manto.utils.f.b(com.jingdong.manto.c.a());
        }
        this.C = true;
        this.B = false;
        this.r = cVar;
        this.a = cVar.f13086i;
        this.f13016h = cVar.f13085h;
        String str = cVar.a;
        this.f13017i = str;
        String str2 = cVar.f13082e;
        this.f13018j = str2;
        this.f13019k = MantoUtils.generateAppUniqueId(str, str2);
        this.s = r();
        AppLifeCycle.add(this.f13017i, this);
        this.H.a(this.f13017i);
        this.H.a((MantoAcrossMessage.Listener) this);
        com.jingdong.manto.r.d.a(this, x());
        long currentTimeMillis = System.currentTimeMillis();
        com.jingdong.manto.launch.h hVar = new com.jingdong.manto.launch.h(this.f13016h, cVar, u());
        hVar.f13254c = new j(rVar, currentTimeMillis);
        hVar.d = new k();
        hVar.f13255e = new l();
        com.jingdong.manto.v.b.a.a().a(hVar);
    }

    public void a(MantoPreLaunchProcess.LaunchError launchError, r rVar) {
        int i2;
        if ((!u()) == true && ((i2 = launchError.errorCode) == 50001 || i2 == 60001)) {
            b(i2);
        } else if (rVar != null) {
            rVar.a(launchError);
        }
    }

    public final void a(MantoAuthDialog mantoAuthDialog) {
        if (!com.jingdong.manto.sdk.thread.a.b()) {
            com.jingdong.manto.sdk.thread.a.a(new a(mantoAuthDialog));
            return;
        }
        com.jingdong.manto.e eVar = this.b;
        if (eVar == null || eVar.isFinishing()) {
            return;
        }
        WeakReference<MantoAuthDialog> weakReference = this.A;
        MantoAuthDialog mantoAuthDialog2 = weakReference != null ? weakReference.get() : null;
        if (mantoAuthDialog2 != null && mantoAuthDialog2.isShowing()) {
            mantoAuthDialog2.cancel();
        }
        if (mantoAuthDialog == null || this.B || mantoAuthDialog.isShowing()) {
            return;
        }
        this.A = new WeakReference<>(mantoAuthDialog);
        mantoAuthDialog.show();
    }

    public final void a(com.jingdong.manto.widget.h.a aVar) {
        if (!MantoThreadUtils.isMainThread()) {
            MantoThreadUtils.runOnUIThread(new q(aVar));
            return;
        }
        com.jingdong.manto.e eVar = this.b;
        if (eVar == null || eVar.isFinishing()) {
            return;
        }
        aVar.show();
    }

    public void a(String str, r rVar) {
        com.jingdong.manto.e eVar = this.b;
        if (eVar != null) {
            eVar.showLoading();
        }
        com.jingdong.manto.launch.h hVar = new com.jingdong.manto.launch.h(this.f13016h, this.r, str, u());
        hVar.f13254c = new h(rVar);
        com.jingdong.manto.v.b.a.a().a(hVar);
    }

    public void a(List<com.jingdong.manto.m.n0.a> list) {
        this.w = list;
    }

    public void a(boolean z) {
        this.I = z;
    }

    public void b() {
        if (this.B) {
            return;
        }
        com.jingdong.manto.r.d.f14141l = System.currentTimeMillis();
        com.jingdong.manto.preload.b.a(new n());
        com.jingdong.manto.message.b.a(this.J);
        this.G.a(this.f13017i);
        this.G.a(this.f13017i, this.r.f13082e);
    }

    public void b(boolean z) {
        this.u = z;
    }

    public boolean b(String str) {
        try {
            if (this.K == null && this.f13016h != null) {
                this.K = new JSONArray(this.f13016h.apiBlackList);
            }
            for (int i2 = 0; i2 < this.K.length(); i2++) {
                if (TextUtils.equals(str, this.K.getString(i2))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public void c(String str) {
        this.q = str;
        Q();
    }

    public boolean c() {
        if (!u() || TextUtils.equals(this.q, "inner")) {
            return true;
        }
        TextUtils.equals(this.q, "default");
        return false;
    }

    public void d(String str) {
        this.r.f13083f = str;
    }

    public final void e() {
        if (this.B) {
            return;
        }
        this.B = true;
        AppLifeCycle.notifyOnAppDestroy(this.f13017i);
        this.H.b(this);
        this.H.b(this.f13017i);
        com.jingdong.manto.message.b.b(this.H);
        com.jingdong.manto.q.l lVar = this.f13014f;
        if (lVar != null) {
            lVar.c();
        }
        com.jingdong.manto.h hVar = this.f13015g;
        if (hVar != null) {
            hVar.i();
        }
        com.jingdong.manto.p.a.b().a();
        this.G.b(this.f13017i);
        com.jingdong.manto.message.b.b(this.J);
        com.jingdong.manto.message.b.b(this.G);
        AppLifeCycle.remove(this.f13017i, this);
        com.jingdong.manto.p.g.a.b().b(this.f13019k);
        com.jingdong.manto.m.c1.l.c().a(this.f13019k);
        d();
    }

    public final void f() {
        if (this.f13013e.f() != 1) {
            g();
        } else {
            this.f13013e.c();
        }
    }

    public final void g() {
        MantoThreadUtils.runOnUIThread(new c());
    }

    public Activity i() {
        com.jingdong.manto.e eVar = this.b;
        if (eVar != null) {
            return eVar.getActivity();
        }
        return null;
    }

    public Bundle j() {
        return this.v;
    }

    public MantoCore k() {
        return this.b;
    }

    public List<com.jingdong.manto.m.n0.a> l() {
        return this.w;
    }

    public String m() {
        if (TextUtils.isEmpty(this.r.f13083f)) {
            if (!TextUtils.isEmpty(this.r.o)) {
                if (this.r.o.contains("?")) {
                    int indexOf = this.r.o.indexOf("?");
                    String substring = this.r.o.substring(0, indexOf);
                    String substring2 = this.r.o.substring(indexOf);
                    String str = this.t.f13049g.get(substring);
                    if (!TextUtils.isEmpty(str)) {
                        return str + substring2;
                    }
                } else {
                    String str2 = this.t.f13049g.get(this.r.o);
                    if (!TextUtils.isEmpty(str2)) {
                        return str2;
                    }
                }
            }
            return this.t.a;
        }
        return this.r.f13083f;
    }

    public Handler n() {
        return this.f13020l;
    }

    @Override // com.jingdong.manto.AppLifeCycle.Listener
    public void onAppCreate() {
        super.onAppCreate();
        this.x = true;
        com.jingdong.manto.t.c.c(this.f13019k);
    }

    @Override // com.jingdong.manto.AppLifeCycle.Listener
    public void onAppDestroy() {
        super.onAppDestroy();
        this.x = false;
    }

    @Override // com.jingdong.manto.AppLifeCycle.Listener
    public void onAppPause() {
        super.onAppPause();
        this.x = false;
    }

    @Override // com.jingdong.manto.AppLifeCycle.Listener
    public void onAppResume() {
        super.onAppResume();
        this.x = true;
    }

    @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
    public void onCalled(Object obj) {
        if (obj instanceof com.jingdong.manto.message.d) {
            if (((com.jingdong.manto.message.d) obj).a() == com.jingdong.manto.message.d.f13863c) {
                MantoLog.d(L, "ACROSSDATA_TYPE_NETWORK");
                new com.jingdong.manto.m.c1.k().a(this);
            }
        } else if (!(obj instanceof MantoPkgUpdate)) {
            if (obj instanceof MainProcMessage) {
                MainProcMessage mainProcMessage = (MainProcMessage) obj;
                if (this.f13015g != null) {
                    JSONObject formatBundleToJson = MantoUtils.formatBundleToJson(mainProcMessage.data);
                    try {
                        formatBundleToJson.put("MessageName", mainProcMessage.messageName);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    this.f13015g.a("onNativeNotification", formatBundleToJson.toString(), 0);
                }
            }
        } else {
            MantoPkgUpdate mantoPkgUpdate = (MantoPkgUpdate) obj;
            if (TextUtils.equals(mantoPkgUpdate.detailEntity.appId, this.f13016h.appId) && TextUtils.equals(mantoPkgUpdate.detailEntity.type, this.f13016h.type)) {
                this.f13016h.favorite = MantoPkgUpdate.UpdateAction.FAVO.equals(mantoPkgUpdate.action);
                MantoLog.d(L, "MantoRuntime MantoPkgUpdate Callback detailEntity.favorite " + this.f13016h.favorite);
            }
        }
    }

    public MantoAcrossMessage p() {
        return this.H;
    }

    com.jingdong.manto.i.e r() {
        com.jingdong.manto.i.e eVar = new com.jingdong.manto.i.e();
        eVar.a = this.f13017i;
        eVar.p = com.jingdong.manto.i.b.a();
        String str = this.f13017i;
        PkgDetailEntity pkgDetailEntity = this.f13016h;
        eVar.f13094c = com.jingdong.manto.m.w0.b.a(str, pkgDetailEntity == null ? "" : pkgDetailEntity.type);
        String str2 = this.f13017i;
        PkgDetailEntity pkgDetailEntity2 = this.f13016h;
        eVar.r = com.jingdong.manto.r.b.a(str2, pkgDetailEntity2 != null ? pkgDetailEntity2.type : "");
        return eVar;
    }

    public boolean s() {
        return this.I;
    }

    public boolean t() {
        return this.x;
    }

    public boolean u() {
        if (this.b == null || x()) {
            return false;
        }
        com.jingdong.manto.e eVar = this.b;
        return (eVar instanceof MantoCardView) && CardMode.LIMIT_API == ((MantoCardView) eVar).getCardMode();
    }

    public boolean v() {
        com.jingdong.manto.i.e eVar = this.s;
        return eVar != null && eVar.f13094c;
    }

    public boolean w() {
        com.jingdong.manto.i.c cVar = this.r;
        return cVar == null || cVar.b();
    }

    public boolean x() {
        com.jingdong.manto.i.c cVar;
        if (this.b == null || (cVar = this.r) == null) {
            return false;
        }
        return TextUtils.equals(cVar.q, "1") || TextUtils.equals(this.r.q, "2") || TextUtils.equals(this.r.q, "3");
    }

    public boolean y() {
        if (com.jingdong.manto.b.o()) {
            return false;
        }
        com.jingdong.manto.e eVar = this.b;
        if (eVar != null) {
            return eVar.isSameToHostTask();
        }
        return true;
    }

    public boolean z() {
        return this.u;
    }
}
