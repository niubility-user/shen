package com.jingdong.manto.q;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.manto.R;
import com.jingdong.manto.i.a;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.m.k0;
import com.jingdong.manto.m.v;
import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.pkg.ipc.MantoPkgUpdate;
import com.jingdong.manto.q.t;
import com.jingdong.manto.sdk.api.IActionBar;
import com.jingdong.manto.sdk.api.ICustomMenuInterface;
import com.jingdong.manto.sdk.api.IHostMenuInterface;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class n extends com.jingdong.manto.m.e0 implements MantoAcrossMessage.Listener {
    private SparseArray<com.jingdong.manto.o.n> B;
    boolean C;
    boolean D;
    String E;
    String F;
    private String G;
    private com.jingdong.manto.q.u.d H;

    /* renamed from: e */
    private String f14067e;

    /* renamed from: f */
    private String f14068f;

    /* renamed from: g */
    ViewGroup f14069g;

    /* renamed from: h */
    FrameLayout f14070h;

    /* renamed from: i */
    public Context f14071i;

    /* renamed from: j */
    com.jingdong.manto.f f14072j;

    /* renamed from: k */
    public k0 f14073k;
    public com.jingdong.manto.widget.g.d q;
    int s;
    public com.jingdong.manto.q.r t;
    com.jingdong.manto.q.d u;
    public com.jingdong.manto.widget.input.i v;
    public com.jingdong.manto.q.t w;
    private volatile boolean d = true;

    /* renamed from: l */
    Set<f0> f14074l = Collections.newSetFromMap(new ConcurrentHashMap());

    /* renamed from: m */
    Set<g0> f14075m = Collections.newSetFromMap(new ConcurrentHashMap());

    /* renamed from: n */
    Set<c0> f14076n = Collections.newSetFromMap(new ConcurrentHashMap());
    public Set<d0> o = Collections.newSetFromMap(new ConcurrentHashMap());
    public Set<e0> p = Collections.newSetFromMap(new ConcurrentHashMap());
    String r = null;
    public boolean x = false;
    public boolean y = false;
    private boolean z = false;
    public Set<h0> A = Collections.newSetFromMap(new ConcurrentHashMap());
    public List<i0> I = new CopyOnWriteArrayList();

    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
            n.this = r1;
        }

        @Override // java.lang.Runnable
        public final void run() {
            n.this.w.g();
        }
    }

    /* loaded from: classes16.dex */
    public class a0 implements Runnable {
        a0() {
            n.this = r1;
        }

        @Override // java.lang.Runnable
        public final void run() {
            n.this.w.setLoadingPointsVisibility(true);
            n.this.w.b();
        }
    }

    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        b(String str, String str2) {
            n.this = r1;
            this.a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int a = com.jingdong.manto.ui.d.a(this.a, -1);
            n.this.d(this.b);
            n.this.c(this.a);
            n.this.w.f();
            ViewGroup viewGroup = n.this.f14069g;
            if (viewGroup != null) {
                viewGroup.setBackgroundColor(a);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class b0 implements Runnable {
        final /* synthetic */ boolean a;

        b0(boolean z) {
            n.this = r1;
            this.a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.this.w.a(this.a);
            n.this.w.setLoadingPointsVisibility(this.a);
        }
    }

    /* loaded from: classes16.dex */
    public class c implements Runnable {
        final /* synthetic */ String a;

        c(String str) {
            n.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageView imageView;
            AnimationDrawable a;
            if (FontsUtil.KEY_MULTI_LIGHT.equals(this.a)) {
                n.this.w.t.setImageDrawable(com.jingdong.manto.q.t.a(-1, new float[]{0.2f, 0.6f, 0.4f, 0.2f}));
                n.this.w.u.setImageDrawable(com.jingdong.manto.q.t.a(-1, new float[]{0.2f, 0.2f, 0.6f, 0.4f}));
                imageView = n.this.w.v;
                a = com.jingdong.manto.q.t.a(-1, new float[]{0.2f, 0.4f, 0.2f, 0.6f});
            } else {
                n.this.w.t.setImageDrawable(com.jingdong.manto.q.t.a(-16777216, new float[]{0.1f, 0.4f, 0.2f, 0.1f}));
                n.this.w.u.setImageDrawable(com.jingdong.manto.q.t.a(-16777216, new float[]{0.1f, 0.1f, 0.4f, 0.2f}));
                imageView = n.this.w.v;
                a = com.jingdong.manto.q.t.a(-16777216, new float[]{0.1f, 0.2f, 0.1f, 0.4f});
            }
            imageView.setImageDrawable(a);
        }
    }

    /* loaded from: classes16.dex */
    public interface c0 {
        void onDestroy();
    }

    /* loaded from: classes16.dex */
    public class d implements Runnable {
        final /* synthetic */ String a;

        d(String str) {
            n.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.this.w.setBackgroundColor(com.jingdong.manto.ui.d.a(this.a, -1));
        }
    }

    /* loaded from: classes16.dex */
    public interface d0 {
        void onBackground();
    }

    /* loaded from: classes16.dex */
    public class e implements t.c {
        e() {
            n.this = r1;
        }

        @Override // com.jingdong.manto.q.t.c
        public void a() {
            n.this.a("onPullDownRefresh", (String) null, (int[]) null);
        }
    }

    /* loaded from: classes16.dex */
    public interface e0 {
        void onForeground();
    }

    /* loaded from: classes16.dex */
    public class f implements t.b {
        f() {
            n.this = r1;
        }

        @Override // com.jingdong.manto.q.t.b
        public final void a(int i2) {
            n.this.v.setTranslationY(i2);
        }
    }

    /* loaded from: classes16.dex */
    public interface f0 {
    }

    /* loaded from: classes16.dex */
    public class g implements View.OnClickListener {
        g() {
            n.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            com.jingdong.manto.f fVar;
            n nVar = n.this;
            if (nVar.D && TextUtils.equals(nVar.F, "1") && (fVar = n.this.f14072j) != null) {
                new v.a().a(fVar.f13015g).a();
                return;
            }
            n.this.f14072j.f();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("vapp_type", n.this.f14072j.f13016h.type);
            } catch (Throwable th) {
                MantoLog.e(DYConstants.DY_TRACK, th);
            }
            MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), StringUtil.app_error_close, "applets_close", n.this.f14068f, n.this.r(), "", jSONObject.toString(), "", null);
        }
    }

    /* loaded from: classes16.dex */
    public interface g0 {
        void onReady();
    }

    /* loaded from: classes16.dex */
    public class h implements View.OnLongClickListener {
        final /* synthetic */ Activity a;

        /* loaded from: classes16.dex */
        public class a implements PkgManager.PkgHistoryListCallBack {

            /* renamed from: com.jingdong.manto.q.n$h$a$a */
            /* loaded from: classes16.dex */
            class RunnableC0658a implements Runnable {
                final /* synthetic */ List a;

                RunnableC0658a(List list) {
                    a.this = r1;
                    this.a = list;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (com.jingdong.manto.ui.d.a(h.this.a)) {
                        return;
                    }
                    h hVar = h.this;
                    com.jingdong.manto.widget.g.d dVar = n.this.q;
                    if (dVar != null) {
                        com.jingdong.manto.widget.g.e.a(hVar.a, dVar.getActionView(), this.a);
                    }
                }
            }

            /* loaded from: classes16.dex */
            public class b implements Runnable {

                /* renamed from: com.jingdong.manto.q.n$h$a$b$a */
                /* loaded from: classes16.dex */
                class RunnableC0659a implements Runnable {
                    final /* synthetic */ List a;

                    RunnableC0659a(List list) {
                        b.this = r1;
                        this.a = list;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (com.jingdong.manto.ui.d.a(h.this.a)) {
                            return;
                        }
                        h hVar = h.this;
                        com.jingdong.manto.widget.g.d dVar = n.this.q;
                        if (dVar != null) {
                            com.jingdong.manto.widget.g.e.a(hVar.a, dVar.getActionView(), this.a);
                        }
                    }
                }

                b() {
                    a.this = r1;
                }

                @Override // java.lang.Runnable
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    List<PkgHistoryEntity> d = com.jingdong.manto.b.k().d();
                    if (d == null || d.size() <= 0) {
                        return;
                    }
                    for (PkgHistoryEntity pkgHistoryEntity : d) {
                        if (!n.this.f14068f.equals(pkgHistoryEntity.appId)) {
                            arrayList.add(pkgHistoryEntity);
                        }
                    }
                    if (arrayList.size() > 0) {
                        com.jingdong.manto.sdk.thread.a.a(new RunnableC0659a(arrayList));
                    }
                }
            }

            a() {
                h.this = r1;
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgHistoryListCallBack
            public void onError(Throwable th) {
                com.jingdong.manto.b.d().diskIO().execute(new b());
            }

            @Override // com.jingdong.manto.pkg.PkgManager.PkgHistoryListCallBack
            public void onSuccess(List<PkgHistoryEntity> list) {
                ArrayList arrayList = new ArrayList();
                if (list == null || list.size() <= 0) {
                    return;
                }
                for (PkgHistoryEntity pkgHistoryEntity : list) {
                    if (!n.this.f14068f.equals(pkgHistoryEntity.appId)) {
                        arrayList.add(pkgHistoryEntity);
                    }
                }
                if (arrayList.size() > 0) {
                    com.jingdong.manto.sdk.thread.a.a(new RunnableC0658a(arrayList));
                }
            }
        }

        h(Activity activity) {
            n.this = r1;
            this.a = activity;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if ("0".equals(com.jingdong.manto.b.g().b("nav_history"))) {
                return true;
            }
            if (n.this.f14072j != null) {
                com.jingdong.manto.utils.e.b(this.a);
            }
            PkgManager.getHistoryList(new a());
            return true;
        }
    }

    /* loaded from: classes16.dex */
    public interface h0 {
        boolean a();
    }

    /* loaded from: classes16.dex */
    public class i implements View.OnClickListener {
        i() {
            n.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            n.this.t.getView().scrollTo(n.this.t.getScrollX(), 0);
            n nVar = n.this;
            if (nVar.y) {
                com.jingdong.manto.m.n0.c.b(nVar.f14072j, nVar.hashCode());
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface i0 {
        void a();

        void b();

        void c();

        void d();
    }

    /* loaded from: classes16.dex */
    public class j implements View.OnClickListener {
        final /* synthetic */ Activity a;

        j(Activity activity) {
            n.this = r1;
            this.a = activity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (n.this.f14072j != null) {
                com.jingdong.manto.utils.e.b(this.a);
            }
            if (n.this.I()) {
                return;
            }
            n.this.f14072j.f13014f.d();
        }
    }

    /* loaded from: classes16.dex */
    public class k implements Runnable {
        final /* synthetic */ int a;

        k(int i2) {
            n.this = r1;
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.g.d dVar = n.this.q;
            if (dVar != null) {
                dVar.setBackgroundColor(this.a);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class l implements View.OnClickListener {
        final /* synthetic */ Activity a;

        l(Activity activity) {
            n.this = r1;
            this.a = activity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (n.this.f14072j != null) {
                com.jingdong.manto.utils.e.b(this.a);
            }
            n.this.J();
        }
    }

    /* loaded from: classes16.dex */
    public class m implements View.OnClickListener {
        final /* synthetic */ Activity a;
        final /* synthetic */ com.jingdong.manto.o.p b;

        m(Activity activity, com.jingdong.manto.o.p pVar) {
            n.this = r1;
            this.a = activity;
            this.b = pVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (n.this.f14072j != null) {
                com.jingdong.manto.utils.e.b(this.a);
            }
            com.jingdong.manto.m.n0.b.a(n.this.f14072j);
            IActionBar iActionBar = (IActionBar) com.jingdong.a.n(IActionBar.class);
            if (iActionBar == null || !iActionBar.onMoreBtnClick(this.a, n.this.f14068f, n.this.r(), this.b.f13912g)) {
                this.b.a(n.this.q.getActionView(), n.this.B);
            }
        }
    }

    /* renamed from: com.jingdong.manto.q.n$n */
    /* loaded from: classes16.dex */
    public class ViewOnClickListenerC0660n implements View.OnClickListener {
        final /* synthetic */ Activity a;

        ViewOnClickListenerC0660n(Activity activity) {
            n.this = r1;
            this.a = activity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.manto.o.n nVar;
            com.jingdong.manto.utils.a0 a0Var;
            boolean z;
            n nVar2 = n.this;
            if (nVar2.f14072j.f13016h == null || (nVar = nVar2.p().get(4)) == null) {
                return;
            }
            if (n.this.f14072j.f13016h.favorite) {
                a0Var = nVar.a;
                z = false;
            } else {
                a0Var = nVar.a;
                z = true;
            }
            a0Var.b("do_fav", z);
            com.jingdong.manto.o.m mVar = com.jingdong.manto.o.o.b().a.get(4);
            if (mVar != null) {
                Activity activity = this.a;
                n nVar3 = n.this;
                mVar.a(activity, nVar3, nVar3.f14072j.f13017i, nVar);
            }
        }
    }

    /* loaded from: classes16.dex */
    class o implements Runnable {
        o() {
            n.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.this.b(false);
        }
    }

    /* loaded from: classes16.dex */
    public class p implements Runnable {
        final /* synthetic */ boolean a;

        p(boolean z) {
            n.this = r1;
            this.a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            PkgDetailEntity pkgDetailEntity;
            String string;
            boolean z;
            n nVar = n.this;
            if (nVar.q == null || (pkgDetailEntity = nVar.f14072j.f13016h) == null) {
                return;
            }
            if (pkgDetailEntity.favorite) {
                string = com.jingdong.manto.c.a().getString(R.string.manto_favo_succ);
                z = false;
            } else {
                string = com.jingdong.manto.c.a().getString(R.string.manto_page_menu_favor);
                z = true;
            }
            n.this.q.a(this.a ? 1 : -1, string, z);
        }
    }

    /* loaded from: classes16.dex */
    public class q implements Runnable {
        final /* synthetic */ String a;

        q(String str) {
            n.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.g.d dVar = n.this.q;
            if (dVar != null) {
                dVar.setTitle(this.a);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class r implements Runnable {
        final /* synthetic */ boolean a;

        r(boolean z) {
            n.this = r1;
            this.a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.g.d dVar = n.this.q;
            if (dVar != null) {
                dVar.setNavigationBarLoadingVisible(this.a);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class s implements Runnable {
        s() {
            n.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.g.d dVar = n.this.q;
            if (dVar != null) {
                dVar.a(true);
                n.this.z = true;
                n.this.f14072j.a(false);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class t implements DialogInterface.OnClickListener {
        t() {
            n.this = r1;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            n.this.f14072j.f13014f.d();
            dialogInterface.cancel();
        }
    }

    /* loaded from: classes16.dex */
    public class u implements Runnable {
        final /* synthetic */ String a;

        u(String str) {
            n.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.g.d dVar = n.this.q;
            if (dVar != null) {
                dVar.setForegroundStyle(this.a);
                n.this.a(this.a);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class v implements DialogInterface.OnClickListener {
        v(n nVar) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.cancel();
        }
    }

    /* loaded from: classes16.dex */
    public class w implements Runnable {
        final /* synthetic */ int a;

        w(int i2) {
            n.this = r1;
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.g.d dVar = n.this.q;
            if (dVar != null) {
                dVar.setForegroundColor(this.a);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class x implements Runnable {
        final /* synthetic */ double a;

        x(double d) {
            n.this = r1;
            this.a = d;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.g.d dVar = n.this.q;
            if (dVar != null) {
                dVar.setAlpha(this.a);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class y implements Runnable {
        y() {
            n.this = r1;
        }

        @Override // java.lang.Runnable
        public final void run() {
            n nVar = n.this;
            nVar.b(nVar.s, nVar.r);
        }
    }

    /* loaded from: classes16.dex */
    public class z implements Runnable {
        final /* synthetic */ int a;

        z(int i2) {
            n.this = r1;
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            n nVar = n.this;
            nVar.b(this.a, nVar.r);
        }
    }

    private void A() {
        Iterator<i0> it = this.I.iterator();
        while (it.hasNext()) {
            try {
                it.next().a();
            } catch (Throwable unused) {
            }
        }
    }

    private void B() {
        Iterator<i0> it = this.I.iterator();
        while (it.hasNext()) {
            try {
                it.next().c();
            } catch (Throwable unused) {
            }
        }
    }

    private void C() {
        Iterator<i0> it = this.I.iterator();
        while (it.hasNext()) {
            try {
                it.next().b();
            } catch (Throwable unused) {
            }
        }
    }

    private void D() {
        Iterator<i0> it = this.I.iterator();
        while (it.hasNext()) {
            try {
                it.next().d();
            } catch (Throwable unused) {
            }
        }
    }

    public void J() {
        com.jingdong.manto.f fVar = this.f14072j;
        com.jingdong.manto.q.l.c(fVar.f13014f, fVar.t.a, com.jingdong.manto.q.m.RE_LAUNCH, null);
    }

    private void K() {
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar == null || fVar.i() == null) {
            return;
        }
        com.jingdong.manto.widget.dialog.a.a(this.f14072j.i(), null, this.E, "\u786e\u5b9a", "\u53d6\u6d88", new t(), new v(this), null, null, null).show();
    }

    public void b(boolean z2) {
        MantoThreadUtils.runOnUIThread(new p(z2));
    }

    private SparseArray<com.jingdong.manto.o.n> j() {
        ArrayList<ICustomMenuInterface.CustomMenuData> customMenus;
        SparseArray<com.jingdong.manto.o.n> sparseArray = new SparseArray<>();
        if (z()) {
            ICustomMenuInterface iCustomMenuInterface = (ICustomMenuInterface) com.jingdong.a.n(ICustomMenuInterface.class);
            if (((IHostMenuInterface) com.jingdong.a.n(IHostMenuInterface.class)) != null) {
                com.jingdong.manto.o.n nVar = new com.jingdong.manto.o.n(1);
                sparseArray.put(nVar.f13908c, nVar);
                com.jingdong.manto.o.n nVar2 = new com.jingdong.manto.o.n(2);
                sparseArray.put(nVar2.f13908c, nVar2);
                com.jingdong.manto.o.n nVar3 = new com.jingdong.manto.o.n(3);
                sparseArray.put(nVar3.f13908c, nVar3);
            }
            if (iCustomMenuInterface == null || !iCustomMenuInterface.disableShortCut()) {
                com.jingdong.manto.o.n nVar4 = new com.jingdong.manto.o.n(8);
                sparseArray.put(nVar4.f13908c, nVar4);
            }
            if (iCustomMenuInterface == null || !iCustomMenuInterface.disableAbout()) {
                com.jingdong.manto.o.n nVar5 = new com.jingdong.manto.o.n(7);
                sparseArray.put(nVar5.f13908c, nVar5);
            }
            if (iCustomMenuInterface == null || !iCustomMenuInterface.disableToggleFavor()) {
                com.jingdong.manto.o.n nVar6 = new com.jingdong.manto.o.n(4);
                sparseArray.put(nVar6.f13908c, nVar6);
            }
            if (iCustomMenuInterface != null && !iCustomMenuInterface.disableFeedBack()) {
                com.jingdong.manto.o.n nVar7 = new com.jingdong.manto.o.n(6);
                sparseArray.put(nVar7.f13908c, nVar7);
            }
            if (iCustomMenuInterface == null || !iCustomMenuInterface.disableShare()) {
                com.jingdong.manto.o.n nVar8 = new com.jingdong.manto.o.n(5);
                sparseArray.put(nVar8.f13908c, nVar8);
            }
            if ((iCustomMenuInterface == null || !iCustomMenuInterface.disableDebugSwitch()) && h().N()) {
                com.jingdong.manto.o.n nVar9 = new com.jingdong.manto.o.n(9);
                sparseArray.put(nVar9.f13908c, nVar9);
            }
            if ((iCustomMenuInterface == null || !iCustomMenuInterface.disablePerformanceSwitch()) && h().O()) {
                com.jingdong.manto.o.n nVar10 = new com.jingdong.manto.o.n(10);
                sparseArray.put(nVar10.f13908c, nVar10);
            }
            if (iCustomMenuInterface != null && (customMenus = iCustomMenuInterface.getCustomMenus(com.jingdong.manto.c.a())) != null) {
                for (int i2 = 0; i2 < customMenus.size(); i2++) {
                    com.jingdong.manto.o.n nVar11 = new com.jingdong.manto.o.n(i2 + 100);
                    sparseArray.put(nVar11.f13908c, nVar11);
                }
            }
            return sparseArray;
        }
        return sparseArray;
    }

    private void u() {
        com.jingdong.manto.i.a aVar;
        Map<String, a.j> map;
        a.j jVar;
        a.c cVar;
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar == null) {
            return;
        }
        com.jingdong.manto.i.a aVar2 = fVar.t;
        boolean z2 = (aVar2 == null || (cVar = aVar2.d) == null || !cVar.a()) ? false : true;
        String str = this.f14067e;
        if (str != null && (aVar = this.f14072j.t) != null && (map = aVar.f13048f) != null && (jVar = map.get(com.jingdong.manto.utils.t.b(str))) != null) {
            z2 = jVar.a();
        }
        Activity i2 = this.f14072j.i();
        if (i2 == null || i2.isFinishing()) {
            return;
        }
        if (z2 || this.f14072j.w()) {
            if (z()) {
                this.q = new com.jingdong.manto.widget.g.c(i2, this.f14072j);
                this.x = true;
            }
        } else if (z()) {
            this.q = new com.jingdong.manto.widget.g.b(i2, this.f14072j);
            this.x = false;
        }
        if (!z() || this.q == null) {
            return;
        }
        com.jingdong.manto.o.p pVar = new com.jingdong.manto.o.p(i2, this);
        this.q.setOnCloseClickListener(new g());
        this.q.setOnHomeLongClickListener(new h(i2));
        this.q.setOnStatusBarClickListener(new i());
        this.q.setOnBackClickListener(new j(i2));
        this.q.setOnBackHomeClickListener(new l(i2));
        this.q.setOnOptionClickListener(new m(i2, pVar));
        this.q.setNavBarFavoriteClickListener(new ViewOnClickListenerC0660n(i2));
        PkgDetailEntity pkgDetailEntity = this.f14072j.f13016h;
        if (pkgDetailEntity == null || TextUtils.isEmpty(pkgDetailEntity.venderId)) {
            return;
        }
        b(true);
    }

    private void v() {
        RelativeLayout.LayoutParams layoutParams;
        com.jingdong.manto.widget.g.d dVar;
        if (!z() || (dVar = this.q) == null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(3, R.id.manto_action_bar_root);
        } else {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) dVar.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams2 = new RelativeLayout.LayoutParams(-1, com.jingdong.manto.utils.g.a(this.f14071i));
            }
            layoutParams2.addRule(10);
            View actionView = this.q.getActionView();
            int i2 = R.id.manto_action_bar_root;
            actionView.setId(i2);
            this.f14069g.addView(this.q.getActionView(), layoutParams2);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(3, i2);
        }
        this.f14069g.addView(this.f14070h, layoutParams);
    }

    private void w() {
        RelativeLayout.LayoutParams layoutParams;
        ViewGroup viewGroup;
        View view;
        if (!z() || this.q == null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            viewGroup = this.f14069g;
            view = this.f14070h;
        } else {
            this.f14069g.addView(this.f14070h, new RelativeLayout.LayoutParams(-1, -1));
            layoutParams = (RelativeLayout.LayoutParams) this.q.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, com.jingdong.manto.utils.g.a(this.f14071i));
            }
            layoutParams.addRule(11);
            this.q.getActionView().setId(R.id.manto_action_bar_root);
            viewGroup = this.f14069g;
            view = this.q.getActionView();
        }
        viewGroup.addView(view, layoutParams);
    }

    private void y() {
        if (this.t == null) {
            com.jingdong.manto.q.r rVar = new com.jingdong.manto.q.r(this.f14071i);
            this.t = rVar;
            k0 k0Var = new k0(this, rVar);
            this.f14073k = k0Var;
            this.t.addJavascriptInterface(k0Var, "JDJSCore");
            this.t.addJavascriptInterface(new com.jingdong.manto.d(this), "__deviceInfo");
        }
        this.t.setSubPackageRoot(this.G);
        this.t.setRuntime(this.f14072j);
        com.jingdong.manto.q.r rVar2 = this.t;
        rVar2.q = this.f14068f;
        rVar2.j();
        com.jingdong.manto.q.r rVar3 = this.t;
        if (rVar3.u) {
            a("onJdConfigReady", "", 0);
        } else {
            rVar3.init();
        }
        this.B = j();
        RelativeLayout relativeLayout = new RelativeLayout(this.f14071i);
        this.f14069g = relativeLayout;
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        com.jingdong.manto.q.t tVar = new com.jingdong.manto.q.t(this.f14071i, this.t);
        this.w = tVar;
        tVar.o = new e();
        tVar.p = new f();
        com.jingdong.manto.widget.input.i iVar = new com.jingdong.manto.widget.input.i(this.f14071i, this);
        this.v = iVar;
        this.w.addView(iVar);
        this.t.x = this.v;
        FrameLayout frameLayout = new FrameLayout(this.f14071i);
        this.f14070h = frameLayout;
        frameLayout.addView(this.w);
        this.u = new com.jingdong.manto.q.d(this.v);
        com.jingdong.manto.widget.input.o.c(this);
        A();
    }

    public final void E() {
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar != null) {
            fVar.p().b(this);
        }
        C();
        Iterator<d0> it = this.o.iterator();
        while (it.hasNext()) {
            it.next().onBackground();
        }
    }

    public final void F() {
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar != null) {
            fVar.p().b(this);
        }
        this.d = false;
        B();
        Iterator<c0> it = this.f14076n.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    public final void G() {
        String str;
        if (!this.x && this.q != null) {
            if (this.f14072j.f13014f.getPageCount() == 1) {
                this.q.b(true);
                if (this.f14072j.s()) {
                    this.z = true;
                    this.f14072j.a(false);
                }
                if ((this.q instanceof com.jingdong.manto.widget.g.b) && (str = this.t.t) != null && !str.equals(this.f14072j.t.a) && !this.f14072j.t.b.a(this.t.t) && !this.z) {
                    this.q.a(false);
                }
            } else {
                this.q.b(false);
            }
            this.q.a(true);
        }
        b(this.s, this.r);
        b(false);
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar != null) {
            fVar.p().a((MantoAcrossMessage.Listener) this);
            c(this.f14072j.z());
            O();
        }
        this.d = true;
        D();
        Iterator<e0> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().onForeground();
        }
    }

    public final void H() {
        Iterator<g0> it = this.f14075m.iterator();
        while (it.hasNext()) {
            it.next().onReady();
        }
    }

    public boolean I() {
        this.A.size();
        Iterator<h0> it = this.A.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (it.next().a()) {
                z2 = true;
            }
        }
        if (!z2 && this.D) {
            if (TextUtils.equals(this.F, "1")) {
                new v.a().a(this.f14072j.f13015g).a();
            } else if (this.f14072j.f13014f.getPageCount() > 1) {
                K();
            }
            z2 = true;
        }
        if (z2 || !this.C) {
            return z2;
        }
        return true;
    }

    public void L() {
        b(true);
    }

    public void M() {
        MantoThreadUtils.runOnUIThread(new a0());
    }

    public void N() {
        MantoThreadUtils.runOnUIThread(new a());
    }

    public void O() {
        com.jingdong.manto.f fVar;
        com.jingdong.manto.q.r rVar = this.t;
        if (rVar == null || (fVar = this.f14072j) == null) {
            return;
        }
        rVar.setCanScroll(fVar.c());
    }

    public View a(String str, int i2) {
        com.jingdong.manto.q.u.d dVar = this.H;
        if (dVar != null) {
            return dVar.a(str, i2);
        }
        return null;
    }

    public final void a(double d2) {
        if (z()) {
            MantoThreadUtils.runOnUIThread(new x(d2));
        }
    }

    public void a(int i2) {
        this.s = i2;
        MantoThreadUtils.runOnUIThread(new z(i2));
    }

    public void a(int i2, boolean z2) {
        com.jingdong.manto.o.n nVar = this.B.get(i2);
        if (nVar != null) {
            nVar.d = z2 ? 1 : 0;
        }
    }

    public final void a(Context context) {
        com.jingdong.manto.q.r rVar = new com.jingdong.manto.q.r(context);
        this.t = rVar;
        k0 k0Var = new k0(this, rVar);
        this.f14073k = k0Var;
        this.t.addJavascriptInterface(k0Var, "JDJSCore");
        this.t.addJavascriptInterface(new com.jingdong.manto.d(this), "__deviceInfo");
        com.jingdong.manto.q.r rVar2 = this.t;
        rVar2.u = true;
        rVar2.init();
    }

    public final void a(Context context, com.jingdong.manto.f fVar) {
        this.f14071i = context;
        this.f14072j = fVar;
        this.f14068f = fVar.f13017i;
        this.s = 0;
        y();
        com.jingdong.manto.i.a aVar = fVar.t;
        if (aVar == null || !aVar.b()) {
            return;
        }
        this.H = new com.jingdong.manto.q.u.d(this.t);
    }

    public final void a(c0 c0Var) {
        this.f14076n.add(c0Var);
    }

    public final void a(d0 d0Var) {
        this.o.add(d0Var);
    }

    public final void a(e0 e0Var) {
        this.p.add(e0Var);
    }

    public final void a(f0 f0Var) {
        this.f14074l.add(f0Var);
    }

    public final void a(g0 g0Var) {
        this.f14075m.add(g0Var);
    }

    public final void a(h0 h0Var) {
        this.A.add(h0Var);
    }

    public void a(i0 i0Var) {
        List<i0> list = this.I;
        if (list == null || list.contains(i0Var)) {
            return;
        }
        this.I.add(i0Var);
    }

    public void a(String str) {
        this.r = str;
        MantoThreadUtils.runOnUIThread(new y());
    }

    public void a(String str, int i2, View view, int i3, int i4, boolean z2) {
        com.jingdong.manto.q.u.d dVar = this.H;
        if (dVar != null) {
            dVar.a(str, i2, view, i3, i4, z2);
        }
    }

    public void a(String str, int i2, float[] fArr, int i3) {
        com.jingdong.manto.q.u.d dVar = this.H;
        if (dVar != null) {
            dVar.a(str, i2, fArr, i3);
        }
    }

    public void a(String str, String str2) {
        MantoThreadUtils.runOnUIThread(new b(str, str2));
    }

    @Override // com.jingdong.manto.m.e0
    public void a(String str, String str2, int[] iArr) {
        if (this.f14072j == null) {
            return;
        }
        h().f13015g.a(str, str2, hashCode());
    }

    public void a(List<com.jingdong.manto.m.n0.a> list) {
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar != null) {
            fVar.a(list);
        }
    }

    public void a(boolean z2) {
        MantoThreadUtils.runOnUIThread(new b0(z2));
    }

    public void a(boolean z2, Bundle bundle) {
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar != null) {
            fVar.b(z2);
            this.f14072j.a(bundle);
        }
        com.jingdong.manto.widget.g.d dVar = this.q;
        if (dVar != null) {
            dVar.setAnchorViewVisible(z2);
        }
    }

    public void a(boolean z2, String str, String str2) {
        this.D = z2;
        this.E = str;
        this.F = str2;
    }

    public void b(int i2) {
        if (z()) {
            MantoThreadUtils.runOnUIThread(new w(i2));
        }
    }

    final void b(int i2, String str) {
        if (!z() || this.q == null) {
            return;
        }
        MantoStatusBarUtil.setStatusBarColor(this.f14072j.i(), Build.VERSION.SDK_INT >= 23 ? 0 : Color.argb(80, 0, 0, 0), "black".equals(str));
        com.jingdong.manto.widget.g.d dVar = this.q;
        if (this.x) {
            i2 = 0;
        }
        dVar.setFakeStatusBarColor(i2);
    }

    public final void b(c0 c0Var) {
        this.f14076n.remove(c0Var);
    }

    public final void b(d0 d0Var) {
        this.o.remove(d0Var);
    }

    public final void b(e0 e0Var) {
        this.p.remove(e0Var);
    }

    public final void b(f0 f0Var) {
        this.f14074l.remove(f0Var);
    }

    public final void b(g0 g0Var) {
        this.f14075m.remove(g0Var);
    }

    public final void b(h0 h0Var) {
        this.A.remove(h0Var);
    }

    public void b(i0 i0Var) {
        List<i0> list = this.I;
        if (list != null) {
            list.remove(i0Var);
        }
    }

    public void b(String str) {
        this.f14067e = str;
        this.t.t = com.jingdong.manto.utils.t.b(str);
        com.jingdong.manto.q.r rVar = this.t;
        if (rVar.f14106n) {
            rVar.e();
            com.jingdong.manto.q.r rVar2 = this.t;
            rVar2.f(rVar2.t);
            if (TextUtils.equals(com.jingdong.manto.utils.m.a("onReady", "0"), "1")) {
                H();
            }
        }
    }

    public void b(String str, int i2) {
        com.jingdong.manto.q.u.d dVar = this.H;
        if (dVar != null) {
            dVar.b(str, i2);
        }
    }

    public void c(int i2) {
        if (z()) {
            MantoThreadUtils.runOnUIThread(new k(i2));
        }
    }

    public void c(String str) {
        MantoThreadUtils.runOnUIThread(new d(str));
    }

    public void c(boolean z2) {
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar != null) {
            fVar.b(z2);
        }
        com.jingdong.manto.widget.g.d dVar = this.q;
        if (dVar != null) {
            dVar.setAnchorViewVisible(z2);
        }
    }

    @Override // com.jingdong.manto.m.e0
    public k0 d() {
        return this.f14073k;
    }

    public void d(String str) {
        MantoThreadUtils.runOnUIThread(new c(str));
    }

    public void d(boolean z2) {
        this.C = z2;
    }

    @Override // com.jingdong.manto.m.e0
    public String e() {
        return "WebView";
    }

    public void e(String str) {
        if (z()) {
            int a2 = com.jingdong.manto.ui.d.a(str, -1);
            c(a2);
            a(a2);
        }
    }

    public void e(boolean z2) {
        if (this.q != null) {
            MantoThreadUtils.runOnUIThread(new r(z2));
        }
    }

    public void f(String str) {
        if (z()) {
            MantoThreadUtils.runOnUIThread(new u(str));
        }
    }

    @Override // com.jingdong.manto.m.e0
    public boolean f() {
        return this.d;
    }

    @Override // com.jingdong.manto.m.e0
    public IMantoWebViewJS g() {
        return this.t;
    }

    public void g(String str) {
        this.G = str;
    }

    @Override // com.jingdong.manto.m.e0
    public com.jingdong.manto.f h() {
        return this.f14072j;
    }

    public void h(String str) {
        if (this.q != null) {
            MantoThreadUtils.runOnUIThread(new q(str));
        }
    }

    public final void i() {
        k();
        this.f14075m.clear();
        this.f14076n.clear();
        this.f14074l.clear();
        this.o.clear();
        this.p.clear();
        this.I.clear();
    }

    public boolean i(String str) {
        com.jingdong.manto.q.u.d dVar = this.H;
        if (dVar != null) {
            return dVar.a(str);
        }
        return false;
    }

    public void k() {
        this.t.destroy();
        this.f14073k.a();
    }

    public View l() {
        return this.f14069g;
    }

    public List<com.jingdong.manto.m.n0.a> m() {
        com.jingdong.manto.f fVar = this.f14072j;
        if (fVar != null) {
            return fVar.l();
        }
        return null;
    }

    public com.jingdong.manto.q.d n() {
        return this.u;
    }

    public View o() {
        return this.f14070h;
    }

    @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
    public void onCalled(Object obj) {
        if (obj != null && (obj instanceof MantoPkgUpdate) && this.d) {
            MantoThreadUtils.post(new o(), 250);
        }
    }

    public SparseArray<com.jingdong.manto.o.n> p() {
        return this.B;
    }

    public String q() {
        com.jingdong.manto.q.r rVar = this.t;
        if (rVar != null) {
            return rVar.getSubPackageRoot();
        }
        return null;
    }

    public String r() {
        return this.f14067e;
    }

    public com.jingdong.manto.q.r s() {
        return this.t;
    }

    public void t() {
        String str;
        if (!(this.q instanceof com.jingdong.manto.widget.g.b) || (str = this.t.t) == null || str.equals(this.f14072j.t.a) || this.f14072j.t.b.a(this.t.t)) {
            return;
        }
        MantoThreadUtils.runOnUIThread(new s());
    }

    public void x() {
        u();
        if (this.q instanceof com.jingdong.manto.widget.g.c) {
            w();
        } else {
            v();
        }
    }

    public boolean z() {
        com.jingdong.manto.f fVar = this.f14072j;
        return fVar == null || fVar.B();
    }
}
