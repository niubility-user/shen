package com.jingdong.manto.q;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.jingdong.common.jdmiaosha.view.WebContainerUtil;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.f;
import com.jingdong.manto.i.a;
import com.jingdong.manto.k.a;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.preload.b;
import com.jingdong.manto.q.n;
import com.jingdong.manto.q.p;
import com.jingdong.manto.q.q;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoTrack;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class l extends FrameLayout implements a.b {

    /* renamed from: h */
    private static final String f14041h = l.class.getSimpleName();
    com.jingdong.manto.f a;
    com.jingdong.manto.q.n b;

    /* renamed from: c */
    public LinkedList<com.jingdong.manto.q.j> f14042c;
    private LinkedList<com.jingdong.manto.q.j> d;

    /* renamed from: e */
    private com.jingdong.manto.q.o f14043e;

    /* renamed from: f */
    private Map<String, com.jingdong.manto.q.p> f14044f;

    /* renamed from: g */
    public String f14045g;

    /* loaded from: classes16.dex */
    public class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.j a;

        a(com.jingdong.manto.q.j jVar) {
            l.this = r1;
            this.a = jVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            l.this.b(this.a, false);
        }
    }

    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.j a;
        final /* synthetic */ u b;

        b(com.jingdong.manto.q.j jVar, u uVar) {
            l.this = r1;
            this.a = jVar;
            this.b = uVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.a.d();
            l.this.h();
            u uVar = this.b;
            if (uVar != null) {
                uVar.onSuccess();
            }
        }
    }

    /* loaded from: classes16.dex */
    public class c implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.j a;

        c(com.jingdong.manto.q.j jVar) {
            l.this = r1;
            this.a = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.d();
            l.this.h();
        }
    }

    /* loaded from: classes16.dex */
    public class d implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.j a;
        final /* synthetic */ boolean b;

        /* renamed from: c */
        final /* synthetic */ l f14047c;

        d(com.jingdong.manto.q.j jVar, boolean z, l lVar) {
            this.a = jVar;
            this.b = z;
            this.f14047c = lVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.a.c();
            if (this.b) {
                this.f14047c.b(this.a, false);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class e extends AnimatorListenerAdapter {
        final /* synthetic */ Runnable a;

        e(l lVar, Runnable runnable) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class f implements Runnable {
        f() {
            l.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (l.this.b == null) {
                System.currentTimeMillis();
                com.jingdong.manto.q.n nVar = new com.jingdong.manto.q.n();
                if (!TextUtils.isEmpty(l.this.f14045g)) {
                    nVar.g(l.this.f14045g);
                }
                nVar.a(l.this.getContext(), l.this.a);
                l.this.b = nVar;
            }
        }
    }

    /* loaded from: classes16.dex */
    public class g implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ int[] f14048c;

        g(String str, String str2, int[] iArr) {
            l.this = r1;
            this.a = str;
            this.b = str2;
            this.f14048c = iArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.a(l.this, this.a, this.b, this.f14048c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class h implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        /* renamed from: c */
        final /* synthetic */ u f14049c;

        h(String str, boolean z, u uVar) {
            l.this = r1;
            this.a = str;
            this.b = z;
            this.f14049c = uVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            l.c(l.this, this.a, this.b ? com.jingdong.manto.q.m.AUTO_RE_LAUNCH : com.jingdong.manto.q.m.RE_LAUNCH, this.f14049c);
        }
    }

    /* loaded from: classes16.dex */
    public class i implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ u b;

        i(String str, u uVar) {
            l.this = r1;
            this.a = str;
            this.b = uVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            l.c(l.this, this.a, com.jingdong.manto.q.m.REDIRECT_TO, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class j implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ u b;

        j(String str, u uVar) {
            l.this = r1;
            this.a = str;
            this.b = uVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            l.c(l.this, this.a, com.jingdong.manto.q.m.SWITCH_TAB, this.b);
        }
    }

    /* loaded from: classes16.dex */
    public class k implements f.r {
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f14052c;
        final /* synthetic */ com.jingdong.manto.q.m d;

        /* renamed from: e */
        final /* synthetic */ u f14053e;

        k(String str, String str2, com.jingdong.manto.q.m mVar, u uVar) {
            l.this = r1;
            this.b = str;
            this.f14052c = str2;
            this.d = mVar;
            this.f14053e = uVar;
        }

        @Override // com.jingdong.manto.f.r
        public void a(MantoPreLaunchProcess.LaunchError launchError) {
            l.this.b(this.f14052c, this.d, this.f14053e);
        }

        @Override // com.jingdong.manto.f.r
        public void a(boolean z) {
            l.this.a.f13015g.a(this.b);
            l.d(l.this, this.f14052c, this.d, this.f14053e);
        }
    }

    /* renamed from: com.jingdong.manto.q.l$l */
    /* loaded from: classes16.dex */
    public class C0657l implements p.d {
        C0657l() {
            l.this = r1;
        }

        @Override // com.jingdong.manto.q.p.d
        public void a(String str) {
            l.this.f14044f.remove(str);
        }
    }

    /* loaded from: classes16.dex */
    public class m implements w {
        final /* synthetic */ String a;
        final /* synthetic */ com.jingdong.manto.q.m b;

        /* renamed from: c */
        final /* synthetic */ u f14054c;

        m(String str, com.jingdong.manto.q.m mVar, u uVar) {
            l.this = r1;
            this.a = str;
            this.b = mVar;
            this.f14054c = uVar;
        }

        @Override // com.jingdong.manto.q.l.w
        public void a() {
            l.c(l.this, this.a, this.b, this.f14054c);
        }

        @Override // com.jingdong.manto.q.l.w
        public String b() {
            return null;
        }
    }

    /* loaded from: classes16.dex */
    public class n implements Runnable {
        final /* synthetic */ boolean[] a;
        final /* synthetic */ l b;

        /* renamed from: c */
        final /* synthetic */ com.jingdong.manto.q.m f14055c;
        final /* synthetic */ com.jingdong.manto.q.j d;

        n(boolean[] zArr, l lVar, com.jingdong.manto.q.m mVar, com.jingdong.manto.q.j jVar) {
            l.this = r1;
            this.a = zArr;
            this.b = lVar;
            this.f14055c = mVar;
            this.d = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a[0] = true;
            if (this.b.getPageCount() == 0) {
                return;
            }
            com.jingdong.manto.q.j jVar = l.this.d.isEmpty() ? null : (com.jingdong.manto.q.j) l.this.d.getFirst();
            com.jingdong.manto.q.m mVar = this.f14055c;
            if (mVar == com.jingdong.manto.q.m.SWITCH_TAB || mVar == com.jingdong.manto.q.m.RE_LAUNCH || mVar == com.jingdong.manto.q.m.AUTO_RE_LAUNCH) {
                l.this.a(jVar, (com.jingdong.manto.q.j) null);
            }
            boolean a = l.a(this.f14055c);
            l.a(this.b, jVar, a, (jVar == null || !TextUtils.equals("manto/subpkg/downfail.html", jVar.j())) ? l.b(this.f14055c) : true);
            l.this.a(this.d, a);
        }
    }

    /* loaded from: classes16.dex */
    public class o implements n.g0 {
        final /* synthetic */ com.jingdong.manto.q.j a;
        final /* synthetic */ boolean[] b;

        /* renamed from: c */
        final /* synthetic */ Runnable f14057c;

        o(com.jingdong.manto.q.j jVar, boolean[] zArr, Runnable runnable) {
            l.this = r1;
            this.a = jVar;
            this.b = zArr;
            this.f14057c = runnable;
        }

        @Override // com.jingdong.manto.q.n.g0
        public void onReady() {
            com.jingdong.manto.q.j jVar = this.a;
            if (jVar != null && jVar.i() != null) {
                this.a.i().b(this);
            }
            if (this.b[0]) {
                return;
            }
            l.this.removeCallbacks(this.f14057c);
            l.this.post(this.f14057c);
        }
    }

    /* loaded from: classes16.dex */
    public class p implements Runnable {
        final /* synthetic */ com.jingdong.manto.q.q a;
        final /* synthetic */ u b;

        p(com.jingdong.manto.q.q qVar, u uVar) {
            l.this = r1;
            this.a = qVar;
            this.b = uVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.d();
            l.this.h();
            u uVar = this.b;
            if (uVar != null) {
                uVar.onSuccess();
            }
        }
    }

    /* loaded from: classes16.dex */
    public class q implements q.d {
        final /* synthetic */ Runnable a;

        q(Runnable runnable) {
            l.this = r1;
            this.a = runnable;
        }

        @Override // com.jingdong.manto.q.q.d
        public void a() {
            l.this.post(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class r implements Runnable {
        r() {
            l.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.a(l.this, 1, false, (u) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class s implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ boolean b;

        /* renamed from: c */
        final /* synthetic */ u f14059c;

        s(int i2, boolean z, u uVar) {
            l.this = r1;
            this.a = i2;
            this.b = z;
            this.f14059c = uVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.a(l.this, this.a, this.b, this.f14059c);
        }
    }

    /* loaded from: classes16.dex */
    public class t implements n.c0 {
        final /* synthetic */ com.jingdong.manto.q.n a;

        t(com.jingdong.manto.q.n nVar) {
            this.a = nVar;
        }

        @Override // com.jingdong.manto.q.n.c0
        public void onDestroy() {
            this.a.b(this);
        }
    }

    /* loaded from: classes16.dex */
    public interface u {
        void onFail();

        void onSuccess();
    }

    /* loaded from: classes16.dex */
    public static class v implements Runnable {
        private l a;
        private String b;

        /* renamed from: c */
        private u f14060c;

        public v(l lVar, String str, u uVar) {
            this.a = lVar;
            this.b = str;
            this.f14060c = uVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.c(this.a, this.b, com.jingdong.manto.q.m.NAVIGATE_TO, this.f14060c);
        }
    }

    /* loaded from: classes16.dex */
    public interface w {
        void a();

        String b();
    }

    public l(Context context, com.jingdong.manto.f fVar) {
        super(context);
        this.f14042c = new LinkedList<>();
        this.d = new LinkedList<>();
        this.f14043e = new com.jingdong.manto.q.o();
        this.f14044f = new HashMap();
        this.a = fVar;
        com.jingdong.manto.k.a.b().a(this);
    }

    private com.jingdong.manto.q.q a(String str) {
        if (this.d.size() < 2) {
            return null;
        }
        for (int i2 = 1; i2 < this.d.size(); i2++) {
            if ((this.d.get(i2) instanceof com.jingdong.manto.q.q) && this.d.get(i2).a(str)) {
                return (com.jingdong.manto.q.q) this.d.get(i2);
            }
        }
        return null;
    }

    private void a() {
        Iterator<com.jingdong.manto.q.j> it = this.d.iterator();
        while (it.hasNext()) {
            b(it.next(), true);
        }
        this.d.clear();
        com.jingdong.manto.q.n nVar = this.b;
        if (nVar != null) {
            nVar.i();
            this.b = null;
        }
        com.jingdong.manto.q.o oVar = this.f14043e;
        if (oVar != null) {
            oVar.a();
        }
    }

    private void a(Animator animator, Runnable runnable) {
        animator.addListener(new e(this, runnable));
        animator.start();
    }

    public void a(com.jingdong.manto.q.j jVar, com.jingdong.manto.q.j jVar2) {
        Iterator<com.jingdong.manto.q.j> it = this.d.iterator();
        boolean z = false;
        while (it.hasNext()) {
            com.jingdong.manto.q.j next = it.next();
            if (next == jVar) {
                z = true;
            } else if (next == jVar2) {
                return;
            } else {
                if (z) {
                    b(next, false);
                    it.remove();
                }
            }
        }
    }

    static void a(l lVar, int i2, boolean z, u uVar) {
        com.jingdong.manto.q.n i3;
        if (lVar.d.size() <= 1) {
            com.jingdong.manto.f fVar = lVar.a;
            if (!z) {
                fVar.f();
            }
            if (uVar != null) {
                uVar.onSuccess();
                return;
            }
            return;
        }
        if (i2 <= 0) {
            i2 = 1;
        }
        if (i2 >= lVar.d.size()) {
            i2 = lVar.d.size() - 1;
        }
        com.jingdong.manto.q.j first = lVar.d.getFirst();
        com.jingdong.manto.q.j jVar = lVar.d.get(i2);
        if (jVar != null && (i3 = jVar.i()) != null) {
            i3.a(new t(i3));
        }
        lVar.a(first, jVar);
        lVar.a(jVar, first, com.jingdong.manto.q.m.NAVIGATE_BACK, uVar);
    }

    static void a(l lVar, com.jingdong.manto.q.j jVar, boolean z, boolean z2) {
        if (jVar != null) {
            if (z2) {
                lVar.d.remove(jVar);
            }
            jVar.e();
            Runnable dVar = new d(jVar, z2, lVar);
            if (!z) {
                dVar.run();
                return;
            }
            ObjectAnimator duration = ObjectAnimator.ofFloat(jVar, "translationX", 0.0f, -(jVar.getWidth() * 0.25f)).setDuration(250L);
            ObjectAnimator duration2 = ObjectAnimator.ofFloat(jVar, "translationX", 0.0f).setDuration(0L);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(duration, duration2);
            lVar.a(animatorSet, dVar);
        }
    }

    private static void a(l lVar, String str) {
        com.jingdong.manto.f fVar;
        a.g gVar;
        if (lVar == null || (fVar = lVar.a) == null || fVar.t == null || TextUtils.isEmpty(str)) {
            return;
        }
        String b2 = com.jingdong.manto.utils.t.b(str);
        if (b2.endsWith(".html")) {
            b2 = b2.replace(".html", "");
        }
        Map<String, a.g> map = lVar.a.t.f13055m;
        if (map == null || map.isEmpty() || (gVar = map.get(b2)) == null) {
            return;
        }
        com.jingdong.manto.q.p pVar = new com.jingdong.manto.q.p(lVar, b2, gVar, new C0657l());
        pVar.h();
        lVar.f14044f.put(b2, pVar);
    }

    public static void a(l lVar, String str, String str2, int[] iArr) {
        Iterator<com.jingdong.manto.q.j> it = lVar.d.iterator();
        while (it.hasNext()) {
            it.next().a(str, str2, iArr);
        }
        Iterator<com.jingdong.manto.q.j> it2 = lVar.f14042c.iterator();
        while (it2.hasNext()) {
            it2.next().a(str, str2, iArr);
        }
    }

    private void a(String str, com.jingdong.manto.q.m mVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appId", this.a.f13017i);
            PkgDetailEntity pkgDetailEntity = this.a.f13016h;
            if (pkgDetailEntity != null) {
                jSONObject.put("vapp_type", pkgDetailEntity.type);
                jSONObject.put("version", String.valueOf(this.a.f13016h.build));
            }
            com.jingdong.manto.i.c cVar = this.a.r;
            if (cVar != null) {
                String str2 = cVar.f13091n;
                if (MantoStringUtils.isEmpty(str2)) {
                    str2 = "0";
                }
                jSONObject.put(BaseEvent.SCENE, str2);
            }
        } catch (JSONException e2) {
            MantoLog.e(f14041h, e2);
        }
        MantoTrack.sendPagePv(com.jingdong.manto.c.a(), str, jSONObject.toString(), "applets_pages", null);
    }

    static boolean a(com.jingdong.manto.q.m mVar) {
        return (mVar == com.jingdong.manto.q.m.APP_LAUNCH || mVar == com.jingdong.manto.q.m.REDIRECT_TO || mVar == com.jingdong.manto.q.m.RE_LAUNCH || mVar == com.jingdong.manto.q.m.AUTO_RE_LAUNCH) ? false : true;
    }

    private com.jingdong.manto.q.q b(String str) {
        if (this.d.size() != 0 && (this.d.getFirst() instanceof com.jingdong.manto.q.q) && this.d.getFirst().a(str)) {
            return (com.jingdong.manto.q.q) this.d.getFirst();
        }
        return null;
    }

    private void b() {
        if (this.f14044f.isEmpty()) {
            return;
        }
        for (com.jingdong.manto.q.p pVar : this.f14044f.values()) {
            if (pVar != null) {
                pVar.d();
            }
        }
    }

    private void b(String str, u uVar) {
        com.jingdong.manto.q.q b2 = b(str);
        if (b2 == null) {
            com.jingdong.manto.q.q a2 = a(str);
            if (a2 == null) {
                if (uVar != null) {
                    uVar.onFail();
                    return;
                }
                return;
            }
            a2.b(str);
            com.jingdong.manto.q.j first = this.d.getFirst();
            a(first, a2);
            a(a2, first, com.jingdong.manto.q.m.SWITCH_TAB, uVar);
            return;
        }
        if (!TextUtils.equals(com.jingdong.manto.utils.m.a(WebContainerUtil.EVENT_SWITCH_TAB, "1"), "1")) {
            b2.b(str);
            b2.a(com.jingdong.manto.q.m.SWITCH_TAB, (String) null);
            b2.d();
            h();
            if (uVar == null) {
                return;
            }
        } else if (!b2.d(str)) {
            b2.f14097n = new q(new p(b2, uVar));
            b2.b(str);
            b2.a(com.jingdong.manto.q.m.SWITCH_TAB, (String) null);
            return;
        } else {
            b2.b(str);
            b2.a(com.jingdong.manto.q.m.SWITCH_TAB, (String) null);
            b2.d();
            h();
            if (uVar == null) {
                return;
            }
        }
        uVar.onSuccess();
    }

    public void b(String str, com.jingdong.manto.q.m mVar, u uVar) {
        com.jingdong.manto.q.j first = this.d.isEmpty() ? null : this.d.getFirst();
        if (first != null && TextUtils.equals("manto/subpkg/downfail.html", first.j())) {
            if (uVar != null) {
                uVar.onFail();
                return;
            }
            return;
        }
        com.jingdong.manto.q.j kVar = new com.jingdong.manto.q.k(getContext(), this);
        ((ViewGroup) kVar.i().o()).addView(kVar.a(new m(str, mVar, uVar)));
        addView(kVar, 0);
        a(this, first, true, false);
        a(kVar, true);
        kVar.b("manto/subpkg/downfail.html");
        kVar.a(com.jingdong.manto.q.m.NAVIGATE_TO, (String) null);
        if (uVar != null) {
            uVar.onFail();
        }
    }

    static boolean b(com.jingdong.manto.q.m mVar) {
        return mVar == com.jingdong.manto.q.m.REDIRECT_TO || mVar == com.jingdong.manto.q.m.SWITCH_TAB || mVar == com.jingdong.manto.q.m.RE_LAUNCH || mVar == com.jingdong.manto.q.m.AUTO_RE_LAUNCH;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0064, code lost:
        if (r8.a.f13015g.b(r9) == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a2, code lost:
        if (r8.a.f13015g.b(r9) == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00a4, code lost:
        r8.a.f13015g.a(r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(com.jingdong.manto.q.l r8, java.lang.String r9, com.jingdong.manto.q.m r10, com.jingdong.manto.q.l.u r11) {
        /*
            boolean r0 = com.jingdong.manto.utils.MantoStringUtils.isEmpty(r9)
            if (r0 == 0) goto Le
            com.jingdong.manto.f r9 = r8.a
            com.jingdong.manto.i.a r9 = r9.t
            java.lang.String r9 = r9.a()
        Le:
            java.lang.String r0 = "?"
            boolean r0 = r9.startsWith(r0)
            if (r0 == 0) goto L2d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.jingdong.manto.f r1 = r8.a
            com.jingdong.manto.i.a r1 = r1.t
            java.lang.String r1 = r1.a()
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
        L2d:
            r3 = r9
            com.jingdong.manto.f r9 = r8.a
            java.lang.String r9 = r9.a(r3)
            com.jingdong.manto.f r0 = r8.a
            com.jingdong.manto.i.c r0 = r0.r
            java.lang.String r0 = r0.f13082e
            java.lang.String r1 = "14"
            boolean r0 = android.text.TextUtils.equals(r1, r0)
            if (r0 != 0) goto L53
            com.jingdong.manto.f r0 = r8.a
            com.jingdong.manto.i.c r0 = r0.r
            java.lang.String r0 = r0.f13082e
            java.lang.String r1 = "13"
            boolean r0 = android.text.TextUtils.equals(r1, r0)
            if (r0 == 0) goto L51
            goto L53
        L51:
            r0 = 0
            goto L54
        L53:
            r0 = 1
        L54:
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            if (r1 != 0) goto Lab
            if (r0 == 0) goto L67
            com.jingdong.manto.f r0 = r8.a
            com.jingdong.manto.h r0 = r0.f13015g
            boolean r0 = r0.b(r9)
            if (r0 != 0) goto Lab
            goto La4
        L67:
            com.jingdong.manto.f r0 = r8.a
            com.jingdong.manto.pkg.db.entity.PkgDetailEntity r0 = r0.f13016h
            com.jingdong.manto.pkg.PkgManager$l r0 = com.jingdong.manto.pkg.PkgManager.getSubPkg(r0, r9)
            if (r0 != 0) goto L75
            r8.b(r3, r10, r11)
            return
        L75:
            com.jingdong.manto.f r1 = r8.a
            com.jingdong.manto.pkg.db.entity.PkgDetailEntity r1 = r1.f13016h
            java.lang.String r0 = r0.a
            java.lang.String r0 = com.jingdong.manto.pkg.PkgManager.getPkgPath(r1, r0)
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r0 = r1.exists()
            if (r0 != 0) goto L9a
            com.jingdong.manto.f r6 = r8.a
            com.jingdong.manto.q.l$k r7 = new com.jingdong.manto.q.l$k
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r11
            r0.<init>(r2, r3, r4, r5)
            r6.a(r9, r7)
            goto Lae
        L9a:
            com.jingdong.manto.f r0 = r8.a
            com.jingdong.manto.h r0 = r0.f13015g
            boolean r0 = r0.b(r9)
            if (r0 != 0) goto Lab
        La4:
            com.jingdong.manto.f r0 = r8.a
            com.jingdong.manto.h r0 = r0.f13015g
            r0.a(r9)
        Lab:
            d(r8, r3, r10, r11)
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.q.l.c(com.jingdong.manto.q.l, java.lang.String, com.jingdong.manto.q.m, com.jingdong.manto.q.l$u):void");
    }

    public static void d(l lVar, String str, com.jingdong.manto.q.m mVar, u uVar) {
        lVar.setCurrentSubPackageRoot(str);
        com.jingdong.manto.q.m mVar2 = com.jingdong.manto.q.m.SWITCH_TAB;
        if (mVar != mVar2) {
            lVar.a(str, mVar, uVar);
        } else if (lVar.b(str) == null && lVar.a(str) == null) {
            lVar.a(str, mVar2, uVar);
        } else {
            lVar.b(str, uVar);
        }
        lVar.a(str, mVar);
        a(lVar, str);
    }

    public void h() {
        long currentTimeMillis = System.currentTimeMillis() - com.jingdong.manto.r.d.f14135f;
        com.jingdong.manto.r.f.b(this.a, "appRouteTime", currentTimeMillis);
        com.jingdong.manto.r.d.a(this.a, 22, currentTimeMillis);
    }

    private void i() {
        com.jingdong.manto.i.a aVar;
        com.jingdong.manto.i.a a2;
        com.jingdong.manto.f fVar = this.a;
        if (fVar == null || (aVar = fVar.t) == null || (a2 = com.jingdong.manto.i.a.a(fVar)) == null || aVar.f13052j == a2.f13052j) {
            return;
        }
        this.a.t = a2;
        Iterator<com.jingdong.manto.q.j> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().l();
        }
    }

    private void setCurrentSubPackageRoot(String str) {
        this.f14045g = this.a.a(str);
    }

    public void a(int i2) {
        com.jingdong.manto.q.o oVar = this.f14043e;
        if (oVar != null) {
            oVar.a(i2);
        }
    }

    public void a(int i2, boolean z, u uVar) {
        a(new s(i2, z, uVar));
    }

    public void a(int i2, boolean z, boolean z2) {
        com.jingdong.manto.q.o oVar = this.f14043e;
        if (oVar != null) {
            oVar.a(i2, z, z2);
        }
    }

    void a(com.jingdong.manto.q.j jVar, com.jingdong.manto.q.j jVar2, com.jingdong.manto.q.m mVar, u uVar) {
        this.d.remove(jVar2);
        if (jVar2.f14037c) {
            b(jVar2, false);
        } else {
            a aVar = new a(jVar2);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(jVar2, "translationX", 0.0f, jVar2.getWidth());
            ofFloat.setDuration(250L);
            a(ofFloat, aVar);
        }
        int hashCode = jVar2.i().hashCode();
        int hashCode2 = jVar.i().hashCode();
        com.jingdong.manto.q.o oVar = this.f14043e;
        jVar.a(mVar, (oVar != null && oVar.a == hashCode && oVar.b) ? "min" : (oVar != null && oVar.a == hashCode2 && oVar.f14079c) ? "max" : null);
        jVar.g();
        if (!jVar2.f14037c) {
            b bVar = new b(jVar, uVar);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(jVar, "translationX", -(jVar.getWidth() * 0.25f), 0.0f);
            ofFloat2.setDuration(250L);
            a(ofFloat2, bVar);
            return;
        }
        jVar.d();
        h();
        if (uVar != null) {
            uVar.onSuccess();
        }
    }

    void a(com.jingdong.manto.q.j jVar, boolean z) {
        if (jVar == null) {
            return;
        }
        this.d.remove(jVar);
        this.d.push(jVar);
        this.f14042c.remove(jVar);
        jVar.bringToFront();
        requestLayout();
        invalidate();
        jVar.g();
        c cVar = new c(jVar);
        if (!z) {
            cVar.run();
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(jVar, "translationX", jVar.getWidth(), 0.0f);
        ofFloat.setDuration(250L);
        a(ofFloat, cVar);
    }

    public final void a(Runnable runnable) {
        if (MantoThreadUtils.isMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public final void a(String str, u uVar) {
        a(new i(str, uVar));
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:133:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r13, com.jingdong.manto.q.m r14, com.jingdong.manto.q.l.u r15) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.q.l.a(java.lang.String, com.jingdong.manto.q.m, com.jingdong.manto.q.l$u):void");
    }

    public final void a(String str, String str2, int[] iArr) {
        a(new g(str, str2, iArr));
    }

    public final void a(String str, boolean z, u uVar) {
        a(new h(str, z, uVar));
    }

    public void b(com.jingdong.manto.q.j jVar, boolean z) {
        com.jingdong.manto.q.o oVar;
        if (jVar != null) {
            int hashCode = jVar.i().hashCode();
            if (!z && (oVar = this.f14043e) != null && oVar.a == hashCode && oVar.b) {
                jVar.setVisibility(8);
                removeView(jVar);
                this.f14043e.d = jVar;
                return;
            }
            jVar.setVisibility(8);
            jVar.f();
            removeView(jVar);
            jVar.a();
        }
    }

    public void c() {
        a();
        b();
        com.jingdong.manto.k.a.b().b(this);
    }

    public final void c(String str, u uVar) {
        a(new j(str, uVar));
    }

    public void d() {
        a(new r());
    }

    public com.jingdong.manto.q.n e() {
        com.jingdong.manto.q.n nVar;
        com.jingdong.manto.q.n nVar2;
        if (this.a.w()) {
            return null;
        }
        com.jingdong.manto.q.n nVar3 = this.b;
        if (nVar3 != null && TextUtils.equals(nVar3.q(), this.f14045g)) {
            com.jingdong.manto.q.n nVar4 = this.b;
            this.b = null;
            return nVar4;
        }
        b.d dVar = this.a.E;
        if (dVar == null || (nVar2 = dVar.b) == null) {
            nVar = new com.jingdong.manto.q.n();
        } else {
            dVar.b = null;
            nVar = nVar2;
        }
        if (!TextUtils.isEmpty(this.f14045g)) {
            nVar.g(this.f14045g);
        }
        nVar.a(getContext(), this.a);
        return nVar;
    }

    public void f() {
        if (this.a.w()) {
            return;
        }
        postDelayed(new f(), 200L);
    }

    public boolean g() {
        com.jingdong.manto.f fVar = this.a;
        if (fVar == null) {
            return false;
        }
        return fVar.P();
    }

    public final synchronized com.jingdong.manto.q.j getFirstPage() {
        com.jingdong.manto.q.j jVar;
        LinkedList<com.jingdong.manto.q.j> linkedList;
        try {
        } catch (Exception e2) {
            MantoLog.e(f14041h, e2.getMessage());
        }
        if (this.f14042c.isEmpty()) {
            if (!this.d.isEmpty()) {
                linkedList = this.d;
            }
            jVar = null;
        } else {
            linkedList = this.f14042c;
        }
        jVar = linkedList.getFirst();
        return jVar;
    }

    public int getPageCount() {
        return this.f14042c.size() + this.d.size();
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        if (g()) {
            i();
        }
    }
}
