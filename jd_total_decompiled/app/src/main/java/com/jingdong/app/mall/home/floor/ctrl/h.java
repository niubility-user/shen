package com.jingdong.app.mall.home.floor.ctrl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleFactory;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import com.jingdong.app.mall.home.widget.HomeTopBgView;
import com.jingdong.app.mall.privacy.JDPrivacyAgreeEvent;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.permission.ClipBoardDialogShow;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.title.theme.IThemeChangeListener;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.platform.business.personal.R2;
import de.greenrobot.event.EventBus;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class h {
    public static volatile int A = 0;
    private static final AtomicBoolean B = new AtomicBoolean(false);
    public static boolean C = false;
    public static boolean D = false;
    private static volatile Bitmap E = null;
    private static Pair<Bitmap, Matrix> F = null;
    private static Pair<String, Bitmap> G = null;
    private static Pair<String, Bitmap> H = null;
    private static Pair<String, Bitmap> I = null;
    private static final RectF J;
    private static final RectF K;
    private static final Matrix L;
    private static final Matrix M;
    private static final JDDisplayImageOptions N;
    private static boolean o = false;
    public static int p = 0;
    private static int q = 140;
    private static volatile long r = 0;
    private static volatile int s = 0;
    private static volatile com.jingdong.app.mall.home.r.e.d t = null;
    public static volatile int u = 280;
    public static volatile int v = 286;
    public static volatile int w = 162;
    public static volatile int x;
    private static volatile int y;
    private static volatile int z;
    private RelativeLayout a;
    private HomePullRefreshRecyclerView b;

    /* renamed from: c  reason: collision with root package name */
    private HomeTopBgView f9445c;
    private IHomeTitle d;

    /* renamed from: e  reason: collision with root package name */
    private final AtomicBoolean f9446e;

    /* renamed from: f  reason: collision with root package name */
    private final AtomicInteger f9447f;

    /* renamed from: g  reason: collision with root package name */
    private JDJSONObject f9448g;

    /* renamed from: h  reason: collision with root package name */
    private String f9449h;

    /* renamed from: i  reason: collision with root package name */
    private int f9450i;

    /* renamed from: j  reason: collision with root package name */
    private int f9451j;

    /* renamed from: k  reason: collision with root package name */
    private int f9452k;

    /* renamed from: l  reason: collision with root package name */
    private int f9453l;

    /* renamed from: m  reason: collision with root package name */
    private IThemeChangeListener f9454m;

    /* renamed from: n  reason: collision with root package name */
    private FrameLayout f9455n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            h hVar = h.this;
            hVar.f9455n = hVar.b.r();
            if (h.this.f9455n == null) {
                return;
            }
            if (h.this.f9445c == null) {
                h.this.f9455n.setClipChildren(false);
                h.m0();
                h.this.f9445c = new HomeTopBgView(h.this.b.getContext());
                h.this.f9445c.setId(R.id.home_top_atmosphere_img);
                h.this.f9445c.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            }
            h.this.f9445c.setTranslationY(h.this.f9450i);
            com.jingdong.app.mall.home.floor.common.i.m.b(h.this.f9455n, h.this.f9445c, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (h.this.a == null) {
                return;
            }
            Bitmap I = h.this.I(null, h.G);
            if (I == null || I.isRecycled()) {
                Bitmap c2 = com.jingdong.app.mall.home.floor.ctrl.f.c(h.this.a.getResources(), R.drawable.home900_top_background);
                h.b(c2.getPixel(c2.getWidth() >> 1, 2));
                Pair unused = h.G = new Pair(null, c2);
                h.this.v0(c2);
                return;
            }
            h.this.v0(I);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Bitmap f9458g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f9459h;

        c(Bitmap bitmap, int i2) {
            this.f9458g = bitmap;
            this.f9459h = i2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            h.this.s0(this.f9458g, this.f9459h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements IThemeChangeListener {
        d() {
        }

        @Override // com.jingdong.common.unification.title.theme.IThemeChangeListener
        public void onThemeChange(boolean z, String str) {
            if (h.this.f9446e.get()) {
                String R = h.this.R();
                h hVar = h.this;
                if (!z) {
                    R = null;
                }
                hVar.f0(R);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ String a;

        e(String str) {
            this.a = str;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            if (bitmap == null || bitmap.isRecycled()) {
                h.this.w0(null);
            } else if (h.this.Z(bitmap)) {
                h.this.w0(null);
            } else {
                Pair pair = h.I;
                Pair unused = h.I = new Pair(this.a, bitmap);
                h.this.w0(bitmap);
                h.this.k0(pair, bitmap);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ IHomeTitle f9461c;
        final /* synthetic */ boolean d;

        f(String str, String str2, IHomeTitle iHomeTitle, boolean z) {
            this.a = str;
            this.b = str2;
            this.f9461c = iHomeTitle;
            this.d = z;
        }

        public void a(String str) {
            h.this.f9447f.set(2);
            h.this.q0(null);
            h.this.z0(this.f9461c, false);
            com.jingdong.app.mall.home.o.a.f.q("2", "loadBigImgFailed", str);
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            if (bitmap != null && !bitmap.isRecycled()) {
                if (!h.this.Z(bitmap)) {
                    Pair pair = h.H;
                    Pair unused = h.H = new Pair(this.b, bitmap);
                    if (h.this.f9447f.get() != 0) {
                        h.this.f9447f.set(1);
                    }
                    h.this.z0(this.f9461c, this.d);
                    h.this.q0(bitmap);
                    h.this.k0(pair, bitmap);
                    return;
                }
                a(this.a);
                return;
            }
            a(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class g {
        static h a = new h(null);
    }

    static {
        boolean m2 = com.jingdong.app.mall.home.a.m();
        C = m2;
        D = m2;
        J = new RectF();
        K = new RectF();
        L = new Matrix();
        M = new Matrix();
        N = com.jingdong.app.mall.home.floor.ctrl.f.a().bitmapConfig(Bitmap.Config.ARGB_8888).isScale(false).resetViewBeforeLoading(false);
    }

    /* synthetic */ h(a aVar) {
        this();
    }

    private void B() {
        if (this.b == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.E0(new a());
    }

    private Bitmap F(Bitmap bitmap) {
        Bitmap bitmap2;
        Pair<String, Bitmap> pair;
        Object obj;
        Pair<String, Bitmap> pair2;
        Object obj2;
        if (this.f9446e.get() && (pair2 = I) != null && (obj2 = pair2.second) != null && !((Bitmap) obj2).isRecycled()) {
            bitmap2 = (Bitmap) I.second;
        } else {
            bitmap2 = (this.f9447f.get() != 1 || (pair = H) == null || (obj = pair.second) == null || ((Bitmap) obj).isRecycled()) ? bitmap : (Bitmap) H.second;
        }
        if (bitmap2 != bitmap) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    public static int G() {
        z = T() + com.jingdong.app.mall.home.floor.common.d.d(s);
        return z;
    }

    public static int H(boolean z2) {
        return z2 ? 25 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Bitmap I(String str, Pair<String, Bitmap> pair) {
        if (pair != null) {
            Object obj = pair.second;
            if (obj != null && !((Bitmap) obj).isRecycled()) {
                if (TextUtils.isEmpty(str) || str.equals(pair.first)) {
                    return (Bitmap) pair.second;
                }
                return null;
            }
        }
        return null;
    }

    public static int J() {
        return A + com.jingdong.app.mall.home.floor.common.d.d(w);
    }

    public static int M() {
        return com.jingdong.app.mall.home.floor.common.d.d(R2.attr.ratingBarStyleIndicator) + A;
    }

    public static h N() {
        return g.a;
    }

    public static int Q() {
        return T() + com.jingdong.app.mall.home.floor.common.d.d(20) + com.jingdong.app.mall.home.floor.common.d.d(p) + com.jingdong.app.mall.home.floor.common.d.d(JDHomeBaseLoadingView.P);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String R() {
        String titleBgUrl = ThemeTitleHelper.getTitleBgUrl("INDEX9");
        return TextUtils.isEmpty(titleBgUrl) ? ThemeTitleHelper.getTitleBgUrl("BIGINDEX") : titleBgUrl;
    }

    public static int T() {
        return A + com.jingdong.app.mall.home.floor.common.d.d(w) + x;
    }

    private void X() {
        if (this.f9446e.get() && this.f9454m == null) {
            this.f9454m = new d();
        }
        if (this.f9446e.get()) {
            ThemeTitleHelper.setThemeTitleChangeListener("BIGINDEX", this.f9454m);
            f0(R());
            return;
        }
        x0(null);
        e0();
        IThemeChangeListener iThemeChangeListener = this.f9454m;
        if (iThemeChangeListener != null) {
            ThemeTitleHelper.removeThemeTitleChangeListener(iThemeChangeListener);
            this.f9454m = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Z(Bitmap bitmap) {
        boolean z2 = true;
        if (bitmap == null) {
            return true;
        }
        float width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width >= 750.0f && height >= 514 && height / width >= 0.6f) {
            z2 = false;
        }
        if (!z2) {
            com.jingdong.app.mall.home.o.a.k.e("Invalid Bitmap width = " + width + " height = " + height);
        }
        return z2;
    }

    private boolean a0(Bitmap bitmap) {
        Pair<String, Bitmap> pair;
        Object obj;
        return (this.f9447f.get() != 1 || (pair = H) == null || (obj = pair.second) == null || ((Bitmap) obj).isRecycled() || bitmap != H.second) ? false : true;
    }

    static /* synthetic */ int b(int i2) {
        return i2;
    }

    private void d0(String str, IHomeTitle iHomeTitle, boolean z2) {
        String md5 = Md5Encrypt.md5(str);
        if (I(md5, H) != null) {
            z0(iHomeTitle, z2);
            q0((Bitmap) H.second);
            return;
        }
        String b2 = com.jingdong.app.mall.home.m.a.b(str);
        if (!TextUtils.isEmpty(b2)) {
            str = b2;
        }
        com.jingdong.app.mall.home.floor.ctrl.f.j(str, N, new f(str, md5, iHomeTitle, z2));
    }

    private void e0() {
        Bitmap I2 = I(null, G);
        if (I2 != null && !I2.isRecycled()) {
            v0(I2);
        } else {
            com.jingdong.app.mall.home.w.a.a(new b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(String str) {
        if (!TextUtils.isEmpty(str) && !com.jingdong.app.mall.home.v.d.a.f()) {
            String concat = str.startsWith("file://") ? "file://" : "file://".concat(str);
            com.jingdong.app.mall.home.o.a.k.e(concat);
            String md5 = Md5Encrypt.md5(concat);
            if (I(md5, I) != null) {
                w0((Bitmap) I.second);
                return;
            } else {
                com.jingdong.app.mall.home.floor.ctrl.f.j(concat, N, new e(md5));
                return;
            }
        }
        w0(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void k0(Pair<String, Bitmap> pair, Bitmap bitmap) {
        Object obj;
        if (pair != null) {
            Object obj2 = pair.second;
            if (obj2 != null && !((Bitmap) obj2).isRecycled() && bitmap != (obj = pair.second)) {
                ((Bitmap) obj).recycle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void m0() {
        y = com.jingdong.app.mall.home.floor.common.d.d(q) - A;
        G();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q0(Bitmap bitmap) {
        if (this.f9447f.get() == 0) {
            return;
        }
        if (this.f9447f.get() == 2) {
            e0();
        } else if (bitmap == null || bitmap.isRecycled()) {
        } else {
            r0(bitmap);
        }
    }

    private void r0(Bitmap bitmap) {
        if (this.a == null) {
            return;
        }
        int M2 = M();
        if (this.f9447f.get() == 1 && M2 <= 0) {
            com.jingdong.app.mall.home.o.a.f.u0(new c(bitmap, M2));
        } else {
            s0(bitmap, M2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void s0(Bitmap bitmap, int i2) {
        Bitmap F2 = F(bitmap);
        if (F2 != null && !F2.isRecycled()) {
            E = F2;
            RectF rectF = J;
            rectF.bottom = E.getHeight();
            float width = E.getWidth();
            rectF.right = width;
            float f2 = width / com.jingdong.app.mall.home.floor.common.d.f9279g;
            int d2 = com.jingdong.app.mall.home.floor.common.d.d(160);
            Matrix matrix = M;
            matrix.reset();
            boolean a0 = a0(E);
            int i3 = -1;
            if (a0 && i2 > 0 && !D) {
                G();
                matrix.setRectToRect(rectF, new RectF(0.0f, z - (rectF.bottom / f2), com.jingdong.app.mall.home.floor.common.d.f9279g, z), Matrix.ScaleToFit.FILL);
                int i4 = this.f9453l;
                if (TextUtils.equals("1", this.f9449h)) {
                    d2 = -1;
                }
                i3 = i4;
            } else {
                matrix.setRectToRect(rectF, new RectF(0.0f, -y, com.jingdong.app.mall.home.floor.common.d.f9279g, (rectF.bottom / f2) - y), Matrix.ScaleToFit.FILL);
            }
            HomeTopBgView homeTopBgView = this.f9445c;
            if (homeTopBgView != null) {
                homeTopBgView.f(i3, a0);
                this.f9445c.g(E, matrix, d2);
            }
            IHomeTitle iHomeTitle = this.d;
            if (iHomeTitle != null) {
                iHomeTitle.setBitmap(E, matrix);
            }
            com.jingdong.app.mall.home.a.t.l();
            if (this.f9447f.get() == 0) {
                k0(H, E);
            }
            if (!this.f9446e.get()) {
                k0(I, E);
            }
            JDHomeFragment z0 = JDHomeFragment.z0();
            if (z0 != null) {
                z0.Z0();
            }
        } else {
            e0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v0(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        r0(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w0(Bitmap bitmap) {
        if (!this.f9446e.get()) {
            x0(null);
        } else if (bitmap != null && !bitmap.isRecycled()) {
            r0(bitmap);
        } else {
            k0(I, null);
            e0();
        }
    }

    private void x0(Bitmap bitmap) {
        if (this.d == null) {
            return;
        }
        if (bitmap != null && !bitmap.isRecycled()) {
            RectF rectF = K;
            rectF.bottom = bitmap.getHeight();
            float width = bitmap.getWidth();
            rectF.right = width;
            Matrix matrix = L;
            matrix.reset();
            matrix.setRectToRect(rectF, new RectF(0.0f, -y, com.jingdong.app.mall.home.floor.common.d.f9279g, (rectF.bottom / (width / com.jingdong.app.mall.home.floor.common.d.f9279g)) - y), Matrix.ScaleToFit.FILL);
            this.d.setSkinBitmap(bitmap, matrix);
            com.jingdong.app.mall.home.a.t.l();
            return;
        }
        this.d.setSkinBitmap(null, null);
        com.jingdong.app.mall.home.a.t.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z0(IHomeTitle iHomeTitle, boolean z2) {
        if (iHomeTitle != null) {
            iHomeTitle.onUiChanged(z2);
        }
    }

    public void C() {
        HomeTopBgView homeTopBgView = this.f9445c;
        if (homeTopBgView != null) {
            homeTopBgView.a();
        }
    }

    public boolean D() {
        return TextUtils.equals("1", this.f9449h) && this.f9446e.get();
    }

    public synchronized void E(int i2, com.jingdong.app.mall.home.r.e.d dVar) {
        if (r >= 0 && i2 > 0) {
            if (TextUtils.equals("1", dVar.getJsonString("alignSkin"))) {
                o0(i2, dVar);
            }
        }
    }

    public int K(int i2) {
        if (i2 < 0) {
            return A + com.jingdong.app.mall.home.floor.common.d.d(90);
        }
        IHomeTitle iHomeTitle = this.d;
        if (iHomeTitle != null) {
            return iHomeTitle.getTopHeight();
        }
        return T();
    }

    public Pair<Bitmap, Matrix> L() {
        Bitmap bitmap;
        Pair<Bitmap, Matrix> pair = F;
        if (pair != null && (bitmap = (Bitmap) pair.first) != null && !bitmap.isRecycled()) {
            return F;
        }
        if (E == null || E.isRecycled()) {
            return null;
        }
        return new Pair<>(E, M);
    }

    public int O() {
        return this.f9452k;
    }

    public int P() {
        return T() - com.jingdong.app.mall.home.floor.common.d.d(x > 0 ? 32 : 24);
    }

    public Pair<Bitmap, Matrix> S() {
        return F;
    }

    public boolean U() {
        return (C || this.f9446e.get() || this.f9447f.get() == 0) ? false : true;
    }

    public void V(View view, HomePullRefreshRecyclerView homePullRefreshRecyclerView, IHomeTitle iHomeTitle, BaseActivity baseActivity) {
        if (view == null || baseActivity == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.n(view);
        this.a = (RelativeLayout) view;
        this.b = homePullRefreshRecyclerView;
        this.d = iHomeTitle;
        if (c0()) {
            A = UnStatusBarTintUtil.getStatusBarHeight((Activity) baseActivity);
        }
        y = com.jingdong.app.mall.home.floor.common.d.d(q) - A;
        B();
        e0();
        g0(0);
    }

    public void W(JDJSONObject jDJSONObject, String str) {
        String jSONStringWithDefault = com.jingdong.app.mall.home.state.old.a.f() ? "" : JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "topBgImgBig", "");
        boolean equals = TextUtils.equals("1", str);
        boolean z2 = !com.jingdong.app.mall.home.o.a.f.k0() || TextUtils.equals("1", JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "forceClip", "0")) || com.jingdong.app.mall.home.state.old.a.f();
        C = z2;
        D = z2 || equals;
        B.set(!z2 && equals);
        p = C ? 0 : 120;
        this.f9447f.set(!TextUtils.isEmpty(jSONStringWithDefault) ? 1 : 0);
    }

    public void Y(BaseActivity baseActivity) {
        boolean z2 = baseActivity != null && baseActivity.isStatusBarTintEnable();
        o = z2;
        if (z2) {
            A = UnStatusBarTintUtil.getStatusBarHeight((Activity) baseActivity);
        }
    }

    public void b0(boolean z2) {
        com.jingdong.app.mall.home.v.c.a.b(this.f9445c, z2);
    }

    public boolean c0() {
        return o;
    }

    public void g0(int i2) {
        if (this.f9451j != 0 && B.get()) {
            i2 = this.f9451j;
        }
        this.f9450i = i2;
        HomeTopBgView homeTopBgView = this.f9445c;
        if (homeTopBgView != null) {
            homeTopBgView.setTranslationY(i2);
        }
    }

    public void h0(IHomeTitle iHomeTitle) {
        if (E == null || !E.isRecycled()) {
            return;
        }
        com.jingdong.app.mall.home.o.a.k.e("Current Bitmap Recycled Reload Bitmap");
        u0(this.f9448g, this.f9449h, iHomeTitle);
    }

    public void i0(boolean z2) {
        if (z2) {
            g0(0);
        }
        m0();
        r0(E);
    }

    public void j0(int i2) {
        this.f9452k = Math.abs(i2);
        int i3 = this.f9451j;
        if (i2 != 0) {
            EventBus.getDefault().post(new MallFloorEvent("home_pull_down"));
        }
        if (!B.get()) {
            i2 = 0;
        }
        this.f9451j = i2;
        if (i2 == 0 && i3 == 0) {
            return;
        }
        g0(i2);
    }

    public synchronized void l0(long j2, boolean z2) {
        t = null;
        if (z2) {
            s = 0;
            r = j2;
            return;
        }
        if (s == 0) {
            r = -1L;
        }
    }

    public void n0(com.jingdong.app.mall.home.r.e.d dVar) {
        u = 280 - Math.min(Math.max(0, dVar.mParentModel.getJsonInt("cutHeight")), 80);
        v = u + 8;
        o0(v, dVar);
    }

    public synchronized void o0(int i2, com.jingdong.app.mall.home.r.e.d dVar) {
        com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
        int i3 = 0;
        boolean z2 = t == dVar;
        if (!z2 && hVar != null) {
            i3 = hVar.f10692c + hVar.b;
        }
        if (p0(dVar.getParseTime(), i2 + i3, z2)) {
            t = dVar;
        }
    }

    public void onEventMainThread(Object obj) {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            return;
        }
        if (obj instanceof JDPrivacyAgreeEvent) {
            z0.p1("8");
        } else if (obj instanceof LoginEvent) {
            z0.i0();
        } else if (obj instanceof ClipBoardDialogShow) {
            if (JDHomeFragment.Q0() && ((ClipBoardDialogShow) obj).isShow) {
                com.jingdong.app.mall.home.o.a.h.g();
            }
        } else if (obj instanceof BaseEvent) {
            BaseEvent baseEvent = (BaseEvent) obj;
            String message = baseEvent.getMessage();
            String type = baseEvent.getType();
            if (JDHomeFragment.Q0() && TextUtils.equals("keyShareEvent", type) && TextUtils.equals("1", message)) {
                com.jingdong.app.mall.home.o.a.h.p();
            }
        }
    }

    public synchronized boolean p0(long j2, int i2, boolean z2) {
        if (!z2) {
            if (j2 != r || s != 0) {
                return false;
            }
        }
        s = i2;
        m0();
        return true;
    }

    public void t0(int i2) {
        int i3 = i2 == 0 ? 162 : 152;
        if (com.jingdong.app.mall.home.state.old.a.f()) {
            i3 = 228;
        } else {
            int searchType = HomeTitleFactory.getSearchType();
            if (searchType == 2) {
                i3 += 16;
            } else if (searchType == 1) {
                i3 += 8;
            }
        }
        w = i3;
        q = 140;
        x = i2;
        m0();
    }

    public synchronized void u0(JDJSONObject jDJSONObject, String str, IHomeTitle iHomeTitle) {
        if (com.jingdong.app.mall.home.o.a.f.k0()) {
            this.f9448g = jDJSONObject;
            this.f9449h = str;
            String jSONStringWithDefault = com.jingdong.app.mall.home.state.old.a.f() ? "" : JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "topBgImgBig", "");
            boolean z2 = !TextUtils.isEmpty(jSONStringWithDefault);
            boolean z3 = z2 && 1 == jDJSONObject.optInt("showLightIcon", 0);
            this.f9446e.set(!com.jingdong.app.mall.home.v.d.a.f() && TextUtils.equals("1", JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "userSkin", "0")));
            z0(iHomeTitle, z3);
            if (z2) {
                this.f9453l = com.jingdong.app.mall.home.floor.common.i.m.o(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "topBgImgBigColor", ""), 0)[0];
                this.f9447f.set(1);
                X();
                d0(jSONStringWithDefault, iHomeTitle, z3);
                return;
            }
            this.f9447f.set(0);
            X();
        }
    }

    public void y0(Bitmap bitmap, Matrix matrix) {
        F = bitmap == null ? null : new Pair<>(bitmap, matrix);
    }

    private h() {
        this.f9446e = new AtomicBoolean(false);
        this.f9447f = new AtomicInteger(0);
        this.f9450i = 0;
        this.f9451j = 0;
        this.f9452k = 0;
        this.f9453l = 0;
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }
}
