package com.jingdong.app.mall.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jingdong.app.mall.home.b;
import com.jingdong.app.mall.home.category.view.CaContentLayout;
import com.jingdong.app.mall.home.category.view.CaMoreLayout;
import com.jingdong.app.mall.home.deploy.view.layout.widget.LottieAnimationMask;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.bottomfloat.BottomFloatLayout;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.ctrl.r;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.LinearWithCenterIconFloorEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.floating.FloatLayout;
import com.jingdong.app.mall.home.floor.view.view.HomeFooterView;
import com.jingdong.app.mall.home.floor.view.view.MallFloorCategory;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleFactory;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.HourlyGoBridge;
import com.jingdong.app.mall.home.floor.view.widget.RuleFloatLayout;
import com.jingdong.app.mall.home.j;
import com.jingdong.app.mall.home.pullrefresh.BaseLoadingView;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView;
import com.jingdong.app.mall.home.state.old.ElderGuideLayout;
import com.jingdong.app.mall.home.v.b;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.app.mall.home.widget.recommend.HomeRecommend;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagepop.MessagePullUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.threadpool.ThreadManager;
import de.greenrobot.event.EventBus;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class JDHomeFragment extends JDTabFragment {
    private static volatile JDHomeFragment i0;
    public static int j0;
    public static int m0;
    private static boolean p0;
    private static boolean r0;
    private String D;
    private String E;
    private volatile com.jingdong.app.mall.home.tips.a H;
    private com.jingdong.app.mall.home.k I;
    private RelativeLayout J;
    private com.jingdong.app.mall.home.r.e.e L;
    private boolean M;
    private com.jingdong.app.mall.home.pulltorefresh.a N;
    private HomeRecommend P;
    private ElderGuideLayout T;
    private com.jingdong.app.mall.home.f V;
    private boolean W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    private float c0;
    private boolean d0;
    private long e0;
    private j.b f0;
    private com.jingdong.app.mall.home.n.c h0;

    /* renamed from: i */
    private HomeFooterView f8500i;

    /* renamed from: j */
    private SimpleDraweeView f8501j;

    /* renamed from: l */
    private FloatLayout f8503l;

    /* renamed from: m */
    private IHomeTitle f8504m;

    /* renamed from: n */
    private long f8505n;
    protected HomePullRefreshRecyclerView p;
    public HomeRecycleView q;
    private HomeRecyclerAdapter r;
    private Runnable s;
    private Drawable v;
    private BottomFloatLayout x;
    private RuleFloatLayout y;
    protected b.d z;
    private static final AtomicBoolean k0 = new AtomicBoolean(true);
    private static final AtomicBoolean l0 = new AtomicBoolean(true);
    private static boolean n0 = false;
    public static String o0 = "";
    private static long q0 = -1;
    private static AtomicBoolean s0 = new AtomicBoolean(false);

    /* renamed from: g */
    private final String f8498g = JDHomeFragment.class.getSimpleName();

    /* renamed from: h */
    private final AtomicBoolean f8499h = new AtomicBoolean(false);

    /* renamed from: k */
    private final com.jingdong.app.mall.home.floor.common.f f8502k = new com.jingdong.app.mall.home.floor.common.f(112, 112);
    private long o = 0;
    private final Drawable t = new ColorDrawable(IconFloorEntity.BGCOLOR_DEFAULT);
    private final Drawable u = new ColorDrawable(com.jingdong.app.mall.home.state.dark.a.d());
    protected RelativeLayout w = null;
    protected com.jingdong.app.mall.home.b A = com.jingdong.app.mall.home.b.m();
    protected com.jingdong.app.mall.home.a B = new com.jingdong.app.mall.home.a();
    private final com.jingdong.app.mall.home.r.b.c C = new com.jingdong.app.mall.home.r.b.c();
    private String F = "0";
    private final com.jingdong.app.mall.home.floor.ctrl.h G = com.jingdong.app.mall.home.floor.ctrl.h.N();
    private HttpResponse K = null;
    private final AtomicLong O = new AtomicLong();
    private int Q = -1;
    private boolean R = JDElderModeUtils.isElderMode();
    private boolean S = com.jingdong.app.mall.home.v.d.a.e();
    private final AtomicBoolean U = new AtomicBoolean(true);
    private final com.jingdong.app.mall.home.u.b a0 = new a();
    private com.jingdong.app.mall.home.o.a.b b0 = new i0();
    private final AtomicBoolean g0 = new AtomicBoolean(false);

    /* loaded from: classes4.dex */
    public class a implements com.jingdong.app.mall.home.u.b {

        /* renamed from: com.jingdong.app.mall.home.JDHomeFragment$a$a */
        /* loaded from: classes4.dex */
        class C0270a extends com.jingdong.app.mall.home.o.a.b {
            C0270a() {
                a.this = r1;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                if (JDHomeFragment.O0()) {
                    com.jingdong.app.mall.home.o.a.c.b(JDHomeFragment.this.thisActivity);
                }
            }
        }

        a() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.u.b
        public void a() {
            if (!JDPrivacyHelper.isAcceptPrivacy(JDHomeFragment.this.thisActivity) || com.jingdong.app.mall.home.o.a.h.c()) {
                return;
            }
            com.jingdong.app.mall.home.o.a.f.E0(new C0270a());
        }
    }

    /* loaded from: classes4.dex */
    public class a0 extends com.jingdong.app.mall.home.o.a.b {
        a0(JDHomeFragment jDHomeFragment) {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            com.jingdong.app.mall.ad.c.l().h();
        }
    }

    /* loaded from: classes4.dex */
    public class b implements BaseVerticalRefresh.i<RecyclerView> {
        b() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh.i
        public void a(BaseVerticalRefresh<RecyclerView> baseVerticalRefresh, BaseVerticalRefresh.l lVar) {
            if (Log.D) {
                Log.i(JDHomeFragment.this.f8498g, "OnRefreshListener2");
            }
            JDHomeFragment.this.Y = true;
            com.jingdong.app.mall.home.j.d();
            JDHomeFragment.this.n1(true, lVar == BaseVerticalRefresh.l.MANUAL_REFRESHING ? "2" : "1", 0);
            com.jingdong.app.mall.home.o.a.f.B0(JDHomeFragment.this.b0);
            com.jingdong.app.mall.home.o.a.f.F0(JDHomeFragment.this.b0, 10000L);
            JDHomeFragment.this.C.e();
        }
    }

    /* loaded from: classes4.dex */
    public static /* synthetic */ class b0 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.jingdong.app.mall.home.floor.common.i.t.values().length];
            a = iArr;
            try {
                iArr[com.jingdong.app.mall.home.floor.common.i.t.FLOAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.FLOAT_NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.ALMOSTTOP_FULLIMG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.LBS_TOP_TAB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.SEARCH_BOX.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.TITLE_B.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.SEARCHBARICON_RIGHTSPECIAL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.SEARCHBARICON_LEFT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.SHAKEFLOOR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.TOPROTATE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.BOTTOM_X_VIEW2.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.BOTTOM_TIME_FLOAT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.AUTO_JUMP_VIEW.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                a[com.jingdong.app.mall.home.floor.common.i.t.RULE_FLOAT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c implements BaseVerticalRefresh.h<RecyclerView> {
        c() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh.h
        public void onPullEvent(BaseVerticalRefresh<RecyclerView> baseVerticalRefresh, BaseVerticalRefresh.l lVar, BaseVerticalRefresh.g gVar) {
            if (lVar == BaseVerticalRefresh.l.PULL_TO_REFRESH || lVar == BaseVerticalRefresh.l.MANUAL_REFRESHING) {
                if (JDHomeFragment.this.f8504m == null || JDHomeFragment.this.f8504m.getHomeTitleView().getVisibility() != 0) {
                    return;
                }
                JDHomeFragment.this.f8504m.getHomeTitleView().setVisibility(4);
            } else if (lVar == BaseVerticalRefresh.l.RESET && JDHomeFragment.this.f8504m != null && JDHomeFragment.this.f8504m.getHomeTitleView().getVisibility() == 4) {
                JDHomeFragment.this.f8504m.getHomeTitleView().setVisibility(0);
            }
        }
    }

    /* loaded from: classes4.dex */
    class c0 implements b.a {
        c0() {
            JDHomeFragment.this = r1;
        }

        private void d() {
            if (com.jingdong.app.mall.home.b.m().p()) {
                return;
            }
            JDHomeFragment.this.r0();
            if (JDHomeFragment.this.T != null) {
                JDHomeFragment.this.T.c();
            }
            JDHomeFragment.this.E1(true);
            JDHomeFragment.this.n1(true, "7", 0);
        }

        @Override // com.jingdong.app.mall.home.v.b.a
        public void a() {
            d();
        }

        @Override // com.jingdong.app.mall.home.v.b.a
        public void b() {
            d();
            if (JDHomeFragment.O0() && com.jingdong.app.mall.home.state.old.a.g()) {
                JDHomeFragment jDHomeFragment = JDHomeFragment.this;
                jDHomeFragment.T = com.jingdong.app.mall.home.state.old.a.k(jDHomeFragment.thisActivity);
            }
        }

        @Override // com.jingdong.app.mall.home.v.b.a
        public void c() {
            JDHomeFragment jDHomeFragment = JDHomeFragment.this;
            jDHomeFragment.y1(jDHomeFragment.v);
            JDHomeFragment jDHomeFragment2 = JDHomeFragment.this;
            if (jDHomeFragment2.z != null && jDHomeFragment2.K != null) {
                JDHomeFragment.this.u1();
                JDHomeFragment jDHomeFragment3 = JDHomeFragment.this;
                jDHomeFragment3.z.d(jDHomeFragment3.K, true);
            }
            if (JDHomeFragment.this.f8504m != null) {
                JDHomeFragment.this.f8504m.onDarkThemeChange();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class d extends RecyclerView.OnScrollListener {
        d() {
            JDHomeFragment.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int i2) {
            if (Log.D) {
                Log.i(JDHomeFragment.this.f8498g, "onScrollStateChanged-splash:" + i2);
            }
            JDHomeFragment.j0 = i2;
            JDHomeFragment.this.E0();
            com.jingdong.app.mall.home.a.f8562k = JDHomeFragment.this.v0();
            JDHomeFragment.this.Y0(i2);
            if (JDHomeFragment.this.f8503l != null) {
                JDHomeFragment.this.f8503l.K(i2 == 0);
            }
            if (i2 == 0) {
                MallFloorEvent.h();
                JDHomeFragment jDHomeFragment = JDHomeFragment.this;
                jDHomeFragment.h1(jDHomeFragment.F0());
                com.jingdong.app.mall.home.p.b.d.c.g().q();
                if (JDHomeFragment.this.r != null) {
                    JDHomeFragment.this.r.u();
                }
                JDHomeFragment.this.C.l();
            } else {
                MallFloorEvent.g();
                if (JDHomeFragment.this.r != null) {
                    JDHomeFragment.this.r.t();
                }
                JDHomeFragment.this.C.k();
                com.jingdong.app.mall.home.a.t.m();
            }
            if (HomeRecommend.isLoadRecommend() && i2 == 0) {
                JDHomeFragment.this.S0();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NotNull RecyclerView recyclerView, int i2, int i3) {
            if (Log.D) {
                Log.i(JDHomeFragment.this.f8498g, "onScroll-splash");
            }
            if (JDHomeFragment.this.r == null) {
                return;
            }
            JDHomeFragment.this.W0();
        }
    }

    /* loaded from: classes4.dex */
    class d0 implements IDestroyListener {
        d0() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.common.frame.IDestroyListener
        public void onDestroy() {
            JDHomeFragment.this.v1();
        }
    }

    /* loaded from: classes4.dex */
    public class e implements HomeFooterView.RetryListener {
        e() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.view.view.HomeFooterView.RetryListener
        public void emptyRetry() {
            JDHomeFragment.o0 = "0.0";
            JDHomeFragment.this.p.d0();
        }
    }

    /* loaded from: classes4.dex */
    class e0 implements View.OnLayoutChangeListener {
        e0() {
            JDHomeFragment.this = r1;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            boolean z = false;
            if (i8 > 0 && !(i8 == i4 && i9 == i5)) {
                JDHomeFragment.this.e0(i4, i5, false);
            }
            if (i8 == 0 && i4 > 0 && JDHomeFragment.m0 != 0 && JDHomeFragment.this.K != null) {
                z = true;
            }
            if (z) {
                JDHomeFragment.this.e0(i4, i5, true);
            }
            JDHomeFragment.m0 = i8;
        }
    }

    /* loaded from: classes4.dex */
    public class f implements RecommendChildRecyclerView.OnRequestResultListener {
        f() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.OnRequestResultListener
        public void onFailed() {
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.OnRequestResultListener
        public void onSuccess(RecommendHomeTabs recommendHomeTabs) {
            JDHomeFragment.this.n0();
        }
    }

    /* loaded from: classes4.dex */
    class f0 implements MessageQueue.IdleHandler {
        f0() {
            JDHomeFragment.this = r1;
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            JDHomeFragment jDHomeFragment = JDHomeFragment.this;
            com.jingdong.app.mall.home.o.a.k.j(jDHomeFragment.w, jDHomeFragment.q);
            return false;
        }
    }

    /* loaded from: classes4.dex */
    public class g implements RecommendChildRecyclerView.onRecommendContentListener {
        g() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.onRecommendContentListener
        public void onRecommendTips(RecommendProduct recommendProduct) {
            if (Log.D) {
                Log.d(JDHomeFragment.this.f8498g, "onRecommendTips: " + recommendProduct);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class g0 extends com.jingdong.app.mall.home.o.a.b {
        g0() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            JDHomeFragment jDHomeFragment = JDHomeFragment.this;
            jDHomeFragment.e0(jDHomeFragment.w.getWidth(), JDHomeFragment.this.w.getHeight(), false);
        }
    }

    /* loaded from: classes4.dex */
    public class h extends com.jingdong.app.mall.home.o.a.b {
        h() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeFragment.this.D1();
        }
    }

    /* loaded from: classes4.dex */
    public class h0 extends com.jingdong.app.mall.home.o.a.b {
        h0() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            JDHomeFragment.this.A1();
        }
    }

    /* loaded from: classes4.dex */
    public class i extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ int f8513g;

        i(int i2) {
            JDHomeFragment.this = r1;
            this.f8513g = i2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            JDHomeFragment.this.h1(this.f8513g);
        }
    }

    /* loaded from: classes4.dex */
    public class i0 extends com.jingdong.app.mall.home.o.a.b {
        i0() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            HomePullRefreshRecyclerView homePullRefreshRecyclerView = JDHomeFragment.this.p;
            if (homePullRefreshRecyclerView != null) {
                homePullRefreshRecyclerView.I();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class j extends com.jingdong.app.mall.home.o.a.b {
        j() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeFragment.this.D1();
        }
    }

    /* loaded from: classes4.dex */
    public class k implements View.OnLayoutChangeListener {

        /* renamed from: g */
        final /* synthetic */ boolean f8517g;

        k(boolean z) {
            JDHomeFragment.this = r1;
            this.f8517g = z;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            JDHomeFragment.this.q.removeOnLayoutChangeListener(this);
            JDHomeFragment.this.a1();
            if (this.f8517g) {
                MallFloorEvent.h();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class l extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ int f8519g;

        l(int i2) {
            JDHomeFragment.this = r1;
            this.f8519g = i2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            JDHomeFragment.this.h1(this.f8519g);
        }
    }

    /* loaded from: classes4.dex */
    public class m extends com.jingdong.app.mall.home.o.a.b {
        m() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (JDHomeFragment.this.q.k()) {
                JDHomeFragment jDHomeFragment = JDHomeFragment.this;
                jDHomeFragment.B.w(jDHomeFragment.r, JDHomeFragment.this.q);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class n implements b.d {
        final /* synthetic */ long a;

        /* loaded from: classes4.dex */
        public class a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g */
            final /* synthetic */ HttpResponse f8522g;

            /* renamed from: h */
            final /* synthetic */ boolean f8523h;

            /* renamed from: com.jingdong.app.mall.home.JDHomeFragment$n$a$a */
            /* loaded from: classes4.dex */
            class C0271a extends com.jingdong.app.mall.home.o.a.b {

                /* renamed from: g */
                final /* synthetic */ long f8525g;

                /* renamed from: h */
                final /* synthetic */ boolean f8526h;

                /* renamed from: i */
                final /* synthetic */ JDJSONObject f8527i;

                /* renamed from: j */
                final /* synthetic */ String f8528j;

                /* renamed from: k */
                final /* synthetic */ List f8529k;

                /* renamed from: l */
                final /* synthetic */ List f8530l;

                C0271a(long j2, boolean z, JDJSONObject jDJSONObject, String str, List list, List list2) {
                    a.this = r1;
                    this.f8525g = j2;
                    this.f8526h = z;
                    this.f8527i = jDJSONObject;
                    this.f8528j = str;
                    this.f8529k = list;
                    this.f8530l = list2;
                }

                @Override // com.jingdong.app.mall.home.o.a.b
                public void safeRun() {
                    if (JDHomeFragment.this.O.get() > this.f8525g) {
                        return;
                    }
                    if (!this.f8526h) {
                        com.jingdong.app.mall.home.o.a.d.h();
                    }
                    JDHomeFragment.this.G.u0(this.f8527i, this.f8528j, JDHomeFragment.this.f8504m);
                    JDHomeFragment.this.B.v();
                    MallFloorEvent.f();
                    com.jingdong.app.mall.home.p.b.c.a.r().v();
                    if (JDHomeFragment.this.f8504m != null) {
                        IHomeTitle iHomeTitle = JDHomeFragment.this.f8504m;
                        com.jingdong.app.mall.home.o.a.f.n(iHomeTitle);
                        com.jingdong.app.mall.home.e.b().a((e.b) iHomeTitle);
                        JDHomeFragment.this.f8504m.beforeRefresh();
                        if (JDHomeFragment.this.f0 != null) {
                            JDHomeFragment.this.f8504m.setSearchBarDataEntity(JDHomeFragment.this.f0);
                            JDHomeFragment.this.f0 = null;
                        }
                    }
                    HomePullRefreshRecyclerView homePullRefreshRecyclerView = JDHomeFragment.this.p;
                    if (homePullRefreshRecyclerView != null) {
                        homePullRefreshRecyclerView.I();
                    }
                    a aVar = a.this;
                    JDHomeFragment.this.w1(aVar.f8522g);
                    JDHomeFragment.this.o1(this.f8527i, this.f8529k, this.f8530l, this.f8526h);
                    JDHomeFragment.this.J0(this.f8526h);
                    if (JDHomeFragment.this.f8504m != null) {
                        int F0 = JDHomeFragment.this.F0();
                        if (F0 > 0) {
                            JDHomeFragment.this.f8504m.changeSearchBarColorVarScrolling(F0);
                        }
                        JDHomeFragment.this.f8504m.afterRefresh();
                    }
                    JDHomeFragment.this.W0();
                    JDHomeFragment.this.m1();
                    JDHomeFragment.this.f8500i.setFooterState(4);
                    if (JDHomeFragment.this.W) {
                        JDHomeFragment.this.E1(false);
                    }
                    if (this.f8526h) {
                        return;
                    }
                    com.jingdong.app.mall.home.s.a.b().k();
                    com.jingdong.app.mall.home.o.a.d.c();
                }
            }

            a(HttpResponse httpResponse, boolean z) {
                n.this = r1;
                this.f8522g = httpResponse;
                this.f8523h = z;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                JDJSONObject p = com.jingdong.app.mall.home.o.a.k.p(this.f8522g);
                if (p != null && JDHomeFragment.i0 != null) {
                    MallFloorEvent.l();
                    boolean isCache = this.f8522g.isCache();
                    com.jingdong.app.mall.home.a.s(isCache);
                    if (!this.f8523h) {
                        com.jingdong.app.mall.home.q.a.a();
                    }
                    com.jingdong.app.mall.home.floor.common.i.s.s(p, isCache);
                    if (!isCache) {
                        com.jingdong.app.mall.home.i.b();
                        com.jingdong.app.mall.home.a.q = p.optString("configDisplayVersion", "10.0.0");
                        com.jingdong.app.mall.home.o.a.d.a = p.optBoolean("bottomNaviSwitch", false);
                        com.jingdong.app.mall.home.o.a.d.f10451c = p.optString("floatMutex", "");
                        com.jingdong.app.mall.home.state.old.a.i(p.optInt("appType", 0) == 1);
                        com.jingdong.app.mall.home.j.h(p);
                        com.jingdong.app.mall.home.floor.view.b.f.d.i();
                        com.jingdong.app.mall.home.r.c.a.y("Home_RefreshSuccess", "", com.jingdong.app.mall.home.b.n(JDHomeFragment.this.F, true));
                        com.jingdong.app.mall.home.r.c.a.i().j();
                        com.jingdong.app.mall.home.r.c.a.i().n();
                        com.jingdong.app.mall.home.r.c.a.i().r();
                        com.jingdong.app.mall.home.r.c.a.i().m();
                        com.jingdong.app.mall.home.r.c.a.i().p();
                    } else if (p.optInt("isLocalData") == 1) {
                        JDHomeFragment.u(JDHomeFragment.this, JDHomeFragment.y() - 1000);
                    }
                    com.jingdong.app.mall.home.floor.bottomfloat.a.d().i();
                    if (JDHomeFragment.this.o0(p, isCache, this.f8523h)) {
                        return;
                    }
                    JDHomeFragment.this.A1();
                    if (JDHomeFragment.this.h0 != null) {
                        JDHomeFragment.this.h0.i();
                    }
                    long unused = JDHomeFragment.q0 = com.jingdong.app.mall.home.n.h.c.i(p.optString("refresh_interval"));
                    String optString = p.optString("useComponentImg", "1");
                    JDHomeFragment.this.G.W(p, optString);
                    StringBuilder sb = new StringBuilder();
                    sb.append("isCache : ");
                    sb.append(isCache);
                    sb.append(" useSkin : ");
                    sb.append(!TextUtils.equals("0", optString));
                    com.jingdong.app.mall.home.o.a.k.e(sb.toString());
                    com.jingdong.app.mall.home.o.a.k.g("parseData");
                    JDHomeFragment.this.C.s();
                    JDHomeFragment jDHomeFragment = JDHomeFragment.this;
                    Map<String, Object> v = com.jingdong.app.mall.home.floor.common.i.s.v(jDHomeFragment, p, jDHomeFragment.thisActivity, isCache, this.f8523h);
                    JDHomeFragment.this.t1(false);
                    JDHomeFragment.this.M0(p, isCache, this.f8523h);
                    List<com.jingdong.app.mall.home.r.e.d> j2 = com.jingdong.app.mall.home.floor.common.i.s.j(v);
                    List<com.jingdong.app.mall.home.r.e.d> k2 = com.jingdong.app.mall.home.floor.common.i.s.k(v);
                    com.jingdong.app.mall.home.o.a.k.g("parseData");
                    if (j2 == null || j2.size() == 0) {
                        JDHomeFragment.this.r1();
                        return;
                    }
                    boolean unused2 = JDHomeFragment.n0 = true;
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    JDHomeFragment.this.O.set(elapsedRealtime);
                    com.jingdong.app.mall.home.o.a.f.E0(new C0271a(elapsedRealtime, isCache, p, optString, j2, k2));
                    return;
                }
                if (Log.D) {
                    Log.d(JDHomeFragment.this.f8498g, "onHomeEnd httpResponse.getFastJsonObject() == null");
                }
                JDHomeFragment.this.r1();
            }
        }

        /* loaded from: classes4.dex */
        class b extends com.jingdong.app.mall.home.o.a.b {
            b() {
                n.this = r1;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                if (JDHomeFragment.this.q.getChildCount() == 0 && !JDHomeFragment.n0) {
                    JDHomeFragment.this.f8500i.setFooterState(3);
                    JDHomeFragment.u(JDHomeFragment.this, JDHomeFragment.y() - 1000);
                    JDHomeFragment.this.r.B(JDHomeFragment.this.L);
                }
                JDHomeFragment.this.p.I();
            }
        }

        n(long j2) {
            JDHomeFragment.this = r1;
            this.a = j2;
        }

        @Override // com.jingdong.app.mall.home.b.d
        public void a(HttpGroup.HttpSettingParams httpSettingParams) {
        }

        @Override // com.jingdong.app.mall.home.b.d
        public void b(HttpError httpError) {
            JDHomeFragment.this.d0 = false;
            if (httpError != null) {
                Throwable exception = httpError.getException();
                StringBuilder sb = new StringBuilder();
                sb.append(JDHomeFragment.this.F.concat(CartConstant.KEY_YB_INFO_LINK).concat(JDHomeFragment.k0.get() ? "1" : "0").concat(CartConstant.KEY_YB_INFO_LINK));
                sb.append(httpError.getErrorCode());
                sb.append(CartConstant.KEY_YB_INFO_LINK);
                sb.append(exception == null ? "" : exception.getMessage());
                com.jingdong.app.mall.home.r.c.a.y("Home_RefreshFail", sb.toString(), "");
            }
            com.jingdong.app.mall.home.o.a.f.E0(new b());
        }

        @Override // com.jingdong.app.mall.home.b.d
        public void c() {
            if (JDHomeFragment.this.K != null) {
                d(JDHomeFragment.this.K, false);
            }
            JDHomeFragment.this.d0 = false;
        }

        @Override // com.jingdong.app.mall.home.b.d
        public void d(HttpResponse httpResponse, boolean z) {
            com.jingdong.app.mall.home.e.b().d();
            if (JDHomeFragment.this.K == null) {
                JDHomeFragment.this.w1(httpResponse);
            }
            JDHomeFragment.this.d0 = false;
            long nanoTime = System.nanoTime();
            if (Log.D) {
                Log.d(JDHomeFragment.this.f8498g, "timetest-onHomeEnd start-->> " + nanoTime);
                Log.d(JDHomeFragment.this.f8498g, "timetest-onHomeEnd start - initData start-->> " + (nanoTime - this.a));
            }
            if (httpResponse != null) {
                if (httpResponse != JDHomeFragment.this.K) {
                    JDHomeFragment.this.f8505n = SystemClock.elapsedRealtime();
                    JDHomeFragment.this.w1(httpResponse);
                }
                com.jingdong.app.mall.home.w.a.b(new a(httpResponse, z), false);
            } else if (Log.D) {
                Log.d(JDHomeFragment.this.f8498g, "onHomeEnd httpResponse == null");
            }
        }
    }

    /* loaded from: classes4.dex */
    public class o extends com.jingdong.app.mall.home.o.a.b {
        o() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeFragment.this.z.b(null);
        }
    }

    /* loaded from: classes4.dex */
    public class p extends com.jingdong.app.mall.home.tips.a {
        p(JDHomeFragment jDHomeFragment, RelativeLayout relativeLayout) {
            super(relativeLayout);
        }

        @Override // com.jingdong.app.mall.home.tips.a
        protected boolean k() {
            return JDHomeFragment.k0.get();
        }
    }

    /* loaded from: classes4.dex */
    public class q extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ Drawable f8534g;

        q(Drawable drawable) {
            JDHomeFragment.this = r1;
            this.f8534g = drawable;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeFragment.this.y1(this.f8534g);
            com.jingdong.app.mall.home.floor.ctrl.h.N().C();
        }
    }

    /* loaded from: classes4.dex */
    public class r extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ boolean f8536g;

        r(boolean z) {
            JDHomeFragment.this = r1;
            this.f8536g = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeFragment jDHomeFragment = JDHomeFragment.this;
            if (jDHomeFragment.p == null) {
                return;
            }
            if (jDHomeFragment.N == null) {
                JDHomeFragment.this.N = new com.jingdong.app.mall.home.pulltorefresh.a();
            }
            JDHomeFragment.this.m1();
            if (!this.f8536g) {
                BaseLoadingView n2 = JDHomeFragment.this.N.n();
                if (n2 instanceof JDHomeBaseLoadingView) {
                    ((JDHomeBaseLoadingView) n2).B();
                    return;
                }
                return;
            }
            BaseLoadingView p = JDHomeFragment.this.N.p(JDHomeFragment.this.thisActivity);
            JDHomeFragment jDHomeFragment2 = JDHomeFragment.this;
            jDHomeFragment2.p.b0(jDHomeFragment2.N);
            if (p instanceof JDHomeBaseLoadingView) {
                ((JDHomeBaseLoadingView) p).B();
            }
            JDHomeFragment.this.p.X(p);
        }
    }

    /* loaded from: classes4.dex */
    public class s extends com.jingdong.app.mall.home.o.a.b {
        s() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            int P = JDHomeFragment.this.G.P();
            HomePullRefreshRecyclerView homePullRefreshRecyclerView = JDHomeFragment.this.p;
            if (homePullRefreshRecyclerView != null) {
                homePullRefreshRecyclerView.c0(P);
            }
            if (JDHomeFragment.this.N != null) {
                JDHomeFragment.this.N.o(P);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class t implements r.b {
        t() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.r.b
        public void a() {
            JDHomeFragment.this.D1();
        }
    }

    /* loaded from: classes4.dex */
    public class u extends com.jingdong.app.mall.home.o.a.b {
        u() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (JDHomeFragment.this.P != null) {
                JDHomeFragment.this.f8499h.set(false);
                JDHomeFragment.this.P.resetContent();
            }
        }
    }

    /* loaded from: classes4.dex */
    class v extends com.jingdong.app.mall.home.o.a.b {
        v() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            ((BaseFragment) JDHomeFragment.this).needRemoveviewOnStop = false;
            String stringExtra = JDHomeFragment.this.thisActivity.getIntent().getStringExtra(LoginConstans.JUMP_DES);
            JDHomeFragment jDHomeFragment = JDHomeFragment.this;
            ((BaseFragment) jDHomeFragment).mHttpGroupWithNPS = jDHomeFragment.A.l();
            try {
                LoginUserBase.init();
            } catch (Throwable th) {
                com.jingdong.app.mall.home.o.a.f.s0(this, th);
            }
            if (JDHomeFragment.this.V != null) {
                JDHomeFragment.this.V.b(JDHomeFragment.this.thisActivity, stringExtra);
            }
            if (JDHomeFragment.this.D == null) {
                JDHomeFragment.this.D = LoginUserBase.getUserPin();
                JDHomeFragment jDHomeFragment2 = JDHomeFragment.this;
                jDHomeFragment2.E = jDHomeFragment2.D;
            }
        }
    }

    /* loaded from: classes4.dex */
    public class w extends com.jingdong.app.mall.home.o.a.b {
        w() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            JDHomeFragment.this.W0();
        }
    }

    /* loaded from: classes4.dex */
    public class x extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ boolean f8542g;

        /* renamed from: h */
        final /* synthetic */ String f8543h;

        x(boolean z, String str) {
            JDHomeFragment.this = r1;
            this.f8542g = z;
            this.f8543h = str;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeFragment.this.l1(this.f8542g, this.f8543h);
        }
    }

    /* loaded from: classes4.dex */
    public class y extends com.jingdong.app.mall.home.o.a.b {
        y() {
            JDHomeFragment.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeFragment jDHomeFragment = JDHomeFragment.this;
            jDHomeFragment.I0(jDHomeFragment.F, false);
        }
    }

    /* loaded from: classes4.dex */
    public class z extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ j.b f8546g;

        z(j.b bVar) {
            JDHomeFragment.this = r1;
            this.f8546g = bVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (JDHomeFragment.this.f8504m != null) {
                JDHomeFragment.this.f8504m.beforeSearchBoxWordRefresh();
                JDHomeFragment.this.f8504m.setSearchBarDataEntity(this.f8546g);
                return;
            }
            JDHomeFragment.this.f0 = this.f8546g;
        }
    }

    public void A1() {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new h0());
            return;
        }
        IHomeTitle homeTitle = HomeTitleFactory.getHomeTitle(this.thisActivity);
        IHomeTitle iHomeTitle = this.f8504m;
        if (iHomeTitle == null || iHomeTitle != homeTitle) {
            com.jingdong.app.mall.home.floor.ctrl.h.N().y0(null, null);
            ViewGroup viewGroup = (ViewGroup) this.w.findViewById(R.id.home_title_content);
            FloatLayout floatLayout = this.f8503l;
            if (floatLayout != null) {
                ViewGroup.LayoutParams layoutParams = floatLayout.getLayoutParams();
                com.jingdong.app.mall.home.o.a.f.n(layoutParams);
                ((RelativeLayout.LayoutParams) layoutParams).topMargin = homeTitle.getBarHeightSpread();
            }
            homeTitle.onScreenChanged(com.jingdong.app.mall.home.floor.common.d.f9279g);
            this.p.o0(homeTitle);
            View homeTitleView = homeTitle.getHomeTitleView();
            com.jingdong.app.mall.home.floor.common.i.m.b(viewGroup, homeTitleView, -1);
            homeTitleView.setAlpha(1.0f);
            IHomeTitle iHomeTitle2 = this.f8504m;
            if (iHomeTitle2 != null) {
                viewGroup.removeView(iHomeTitle2);
                this.f8504m.changeSearchBarColorVarScrolling(0);
                this.f8504m.onPause();
                com.jingdong.app.mall.home.a.f8565n = true;
                com.jingdong.app.mall.home.j.c();
                this.f8504m.onTitleChanged();
            }
            this.f8504m = homeTitle;
            homeTitle.bindFragment(this);
            t1(true);
            this.G.V(this.w, this.p, this.f8504m, this.thisActivity);
            D1();
        }
    }

    private static long C0() {
        long j2 = q0;
        if (j2 > 60000) {
            return j2;
        }
        return 600000L;
    }

    private com.jingdong.app.mall.home.r.c.b D0() {
        com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
        int max = Math.max((int) R2.attr.switchBackDrawable, com.jingdong.app.mall.home.floor.common.d.f9278f);
        RelativeLayout relativeLayout = this.w;
        if (relativeLayout != null) {
            max = Math.min(relativeLayout.getHeight(), max);
        }
        if (max > 0) {
            float f2 = max;
            bVar.a("scrollmax", Float.valueOf((this.c0 / f2) + 1.0f));
            bVar.a("scrollfinal", Float.valueOf((F0() / f2) + 1.0f));
        }
        return bVar;
    }

    public void J0(boolean z2) {
        if (z2 || !com.jingdong.app.mall.home.o.a.f.l0()) {
            return;
        }
        com.jingdong.app.mall.home.r.e.h g2 = this.B.g();
        JDJSONObject c2 = g2 != null ? g2.c(0, 0) : null;
        com.jingdong.app.mall.home.r.e.h hVar = c2 != null ? new com.jingdong.app.mall.home.r.e.h(c2) : null;
        if (hVar != null) {
            if (this.I == null) {
                this.I = new com.jingdong.app.mall.home.k(this.w);
            }
            this.I.s(hVar);
            if (TextUtils.isEmpty(LoginUserBase.getUserPin())) {
                com.jingdong.app.mall.home.o.a.d.j();
                return;
            }
            return;
        }
        com.jingdong.app.mall.home.k kVar = this.I;
        if (kVar != null) {
            kVar.C();
        }
        this.I = null;
    }

    private void K0(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        com.jingdong.app.mall.home.a.u.M(jDJSONObject.getIntValue("tagAnimations"));
        com.jingdong.app.mall.home.a.u.I(jDJSONObject.getIntValue("appCenterAnimations"));
    }

    private void L0(com.jingdong.app.mall.home.r.e.d dVar, boolean z2) {
        View contentView;
        com.jingdong.app.mall.home.r.e.h hVar = dVar.mParentModel;
        if (hVar == null || z2) {
            return;
        }
        com.jingdong.app.mall.home.floor.common.i.t tVar = dVar.q;
        if (com.jingdong.app.mall.home.floor.common.i.t.LOGIN == tVar) {
            this.B.A(hVar);
        } else if (com.jingdong.app.mall.home.o.a.f.k0()) {
            switch (b0.a[tVar.ordinal()]) {
                case 1:
                case 2:
                    if (this.f8503l == null || com.jingdong.app.mall.home.floor.bottompop.b.a()) {
                        return;
                    }
                    this.f8503l.z(hVar, tVar);
                    return;
                case 3:
                    this.B.u();
                    String O = com.jingdong.app.mall.home.o.a.f.O(LinearWithCenterIconFloorEntity.CLOSE_ID, "");
                    if (TextUtils.isEmpty(hVar.getJsonString("materialId")) || O.equals(hVar.getJsonString("materialId", "empty")) || (contentView = tVar.getFloorViewByCache(this.thisActivity).getContentView()) == null) {
                        return;
                    }
                    ((BaseMallFloor) contentView).onViewBind(dVar);
                    this.B.d(contentView, this.w, this.q, E0());
                    contentView.setContentDescription("\u5438\u9876\u697c\u5c42");
                    return;
                case 4:
                    TitleTabManager.getInstance().checkHomeTabName(hVar, this.f8504m);
                    return;
                case 5:
                    this.B.C(hVar);
                    return;
                case 6:
                    this.B.B(dVar);
                    return;
                case 7:
                    this.B.z(hVar);
                    return;
                case 8:
                    this.B.y(hVar);
                    return;
                case 9:
                    this.B.e(hVar, this.w);
                    return;
                case 10:
                    IHomeTitle iHomeTitle = this.f8504m;
                    if (iHomeTitle != null) {
                        iHomeTitle.addTitleResource(hVar);
                        return;
                    }
                    return;
                case 11:
                    if (com.jingdong.app.mall.home.floor.bottompop.b.a()) {
                        return;
                    }
                    this.C.c(hVar);
                    return;
                case 12:
                    if (com.jingdong.app.mall.home.floor.bottompop.b.a()) {
                        return;
                    }
                    if (this.x == null) {
                        this.x = new BottomFloatLayout(this.thisActivity);
                    }
                    this.C.u(this.x);
                    com.jingdong.app.mall.home.r.e.i iVar = new com.jingdong.app.mall.home.r.e.i(hVar.a(), hVar.Z);
                    iVar.mParentModel = hVar;
                    iVar.t(tVar, this.thisActivity);
                    boolean o2 = this.x.o(hVar, iVar);
                    com.jingdong.app.mall.home.floor.common.i.m.b(this.w, this.x, -1);
                    if (o2) {
                        com.jingdong.app.mall.home.o.a.d.j();
                        return;
                    }
                    return;
                case 13:
                    if (com.jingdong.app.mall.home.floor.bottompop.b.a()) {
                        return;
                    }
                    TitleTabManager.getInstance().initView2Builder(this.C, hVar, dVar);
                    return;
                case 14:
                    if (this.w == null) {
                        return;
                    }
                    if (this.y == null) {
                        this.y = new RuleFloatLayout(this.thisActivity);
                    }
                    this.y.e(this.w, hVar, this.w.indexOfChild(this.p) + 1);
                    return;
                default:
                    return;
            }
        }
    }

    public void M0(JDJSONObject jDJSONObject, boolean z2, boolean z3) {
        this.C.e();
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("tips");
        if (jSONObject != null && !z2 && !com.jingdong.app.mall.home.floor.bottompop.b.a()) {
            if (this.H == null) {
                this.H = new p(this, this.J);
            }
            this.H.o(new com.jingdong.app.mall.home.tips.c(jSONObject), z3);
            this.C.v(this.H);
            return;
        }
        this.C.t();
    }

    private boolean N0() {
        return H0() < 0;
    }

    public static boolean O0() {
        return k0.get();
    }

    public static boolean P0() {
        return s0.get();
    }

    public static boolean Q0() {
        return l0.get();
    }

    private boolean R0() {
        return (SystemClock.elapsedRealtime() - this.f8505n) - C0() > 0;
    }

    public void e0(int i2, int i3, boolean z2) {
        b.d dVar;
        HttpResponse httpResponse;
        try {
            BaseActivity baseActivity = this.thisActivity;
            if (baseActivity == null) {
                return;
            }
            int max = Math.max(i3, baseActivity.getWindowManager().getDefaultDisplay().getHeight());
            com.jingdong.app.mall.home.a.f8562k = i3;
            boolean h2 = com.jingdong.app.mall.home.floor.common.d.h(i2, max);
            com.jingdong.app.mall.home.n.c cVar = this.h0;
            if (cVar != null) {
                cVar.h(i3);
            }
            g1(h2, i2, i3);
            if ((!h2 && !z2) || (dVar = this.z) == null || (httpResponse = this.K) == null) {
                return;
            }
            dVar.d(httpResponse, true);
            HourlyGoBridge.dismissBubble();
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    private void f0(JDJSONObject jDJSONObject, boolean z2) {
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("homeArea");
        if (jSONObject == null) {
            this.Q = com.jingdong.app.mall.home.u.a.w().v();
        } else {
            this.Q = jSONObject.optInt("homeAreaCode");
        }
        com.jingdong.app.mall.home.u.a.w().t(jSONObject, this.f8504m);
        if (z2 || com.jingdong.app.mall.home.i.i()) {
            return;
        }
        com.jingdong.app.mall.home.o.a.c.c(jDJSONObject);
        com.jingdong.app.mall.home.o.a.c.a(this.thisActivity, this.f8504m, this.a0);
    }

    private void g1(boolean z2, int i2, int i3) {
        int E0 = E0();
        com.jingdong.app.mall.home.a aVar = this.B;
        int i4 = aVar == null ? 0 : aVar.i();
        HomeRecyclerAdapter homeRecyclerAdapter = this.r;
        if (homeRecyclerAdapter != null) {
            homeRecyclerAdapter.x(i4 + E0, i2, i3);
        }
        com.jingdong.app.mall.home.floor.ctrl.h.N().i0(z2);
        if (z2) {
            r0();
            HomePullRefreshRecyclerView homePullRefreshRecyclerView = this.p;
            if (homePullRefreshRecyclerView != null) {
                homePullRefreshRecyclerView.I();
            }
            com.jingdong.app.mall.home.floor.common.f.c(this.f8501j, this.f8502k);
            IHomeTitle iHomeTitle = this.f8504m;
            if (iHomeTitle != null) {
                iHomeTitle.onScreenChanged(com.jingdong.app.mall.home.floor.common.d.f9279g);
            }
            FloatLayout floatLayout = this.f8503l;
            if (floatLayout != null) {
                ViewGroup.LayoutParams layoutParams = floatLayout.getLayoutParams();
                com.jingdong.app.mall.home.o.a.f.n(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                IHomeTitle iHomeTitle2 = this.f8504m;
                if (iHomeTitle2 != null) {
                    E0 = iHomeTitle2.getBarHeightSpread();
                }
                layoutParams2.topMargin = E0;
                this.f8503l.setLayoutParams(layoutParams2);
                this.f8503l.J(com.jingdong.app.mall.home.floor.common.d.f9279g);
            }
            com.jingdong.app.mall.home.k kVar = this.I;
            if (kVar != null) {
                kVar.z();
            }
            RuleFloatLayout ruleFloatLayout = this.y;
            if (ruleFloatLayout != null) {
                ruleFloatLayout.h();
            }
            t1(false);
        }
    }

    private void h0() {
        if (T0()) {
            HourlyGoBridge.saveHourlyDirect();
            l0();
            IHomeTitle iHomeTitle = this.f8504m;
            boolean z2 = iHomeTitle != null && iHomeTitle.checkJumpNearby();
            this.Z = z2;
            if (z2) {
                return;
            }
            TitleTabManager.getInstance().setNeedRequest(true);
        }
    }

    public void h1(int i2) {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new l(i2));
            return;
        }
        FloatLayout floatLayout = this.f8503l;
        if (floatLayout != null) {
            floatLayout.L(i2);
        }
        com.jingdong.app.mall.home.floor.ctrl.c cVar = com.jingdong.app.mall.home.a.t;
        cVar.n(i2);
        com.jingdong.app.mall.home.floor.ctrl.a aVar = com.jingdong.app.mall.home.a.r;
        boolean z2 = !cVar.k() && com.jingdong.app.mall.home.floor.ctrl.a.h(this.q, E0(), i2);
        if (aVar != null) {
            aVar.e(z2, i2, E0());
        }
        this.C.m(this.q, i2);
        IHomeTitle iHomeTitle = this.f8504m;
        if (iHomeTitle != null) {
            iHomeTitle.changeSearchBarColorVarScrolling(i2);
        }
        com.jingdong.app.mall.home.floor.ctrl.r rVar = this.B.a;
        boolean z3 = rVar != null && rVar.d(i2);
        if (!z3 && this.f8501j.getVisibility() == 0) {
            this.f8501j.setVisibility(8);
        }
        com.jingdong.app.mall.home.k kVar = this.I;
        if (kVar != null) {
            kVar.F(z3);
        }
        this.C.q(z3, z2);
        HomeRecyclerAdapter homeRecyclerAdapter = this.r;
        if (homeRecyclerAdapter != null) {
            com.jingdong.app.mall.home.floor.common.i.k.e(homeRecyclerAdapter.p());
        }
    }

    public static boolean isDebug() {
        return p0;
    }

    private void k0() {
        RelativeLayout relativeLayout = this.w;
        if (relativeLayout != null && com.jingdong.app.mall.home.floor.common.d.e(relativeLayout.getWidth())) {
            com.jingdong.app.mall.home.o.a.f.E0(new g0());
        }
    }

    private void l0() {
        com.jingdong.app.mall.home.floor.ctrl.t.i.p().w();
        com.jingdong.app.mall.home.floor.ctrl.guide.a.j().o();
        com.jingdong.app.mall.home.u.a.w().A();
    }

    public void l1(boolean z2, String str) {
        HomeRecyclerAdapter homeRecyclerAdapter = this.r;
        if (homeRecyclerAdapter != null) {
            homeRecyclerAdapter.w();
        }
        this.X = false;
        this.F = str;
        if (this.s == null) {
            this.s = new y();
        }
        if (z2) {
            v1();
        }
        post(this.s);
    }

    private void m0() {
        com.jingdong.app.mall.home.floor.ctrl.t.i.p().D(true);
        com.jingdong.app.mall.ad.c.l().y(true);
        com.jingdong.app.mall.home.u.a.w().F(true);
    }

    public void m1() {
        com.jingdong.app.mall.home.o.a.f.E0(new s());
    }

    public void n0() {
        com.jingdong.app.mall.home.floor.ctrl.r rVar;
        com.jingdong.app.mall.home.a aVar = this.B;
        if (aVar == null || (rVar = aVar.a) == null || !rVar.f9510i.get()) {
            return;
        }
        this.B.a.f9510i.set(false);
        com.jingdong.app.mall.home.o.a.f.E0(new m());
    }

    public void n1(boolean z2, String str, int i2) {
        if (!this.d0) {
            this.d0 = true;
            q1();
            this.e0 = System.currentTimeMillis();
            if (i2 > 0) {
                com.jingdong.app.mall.home.o.a.f.F0(new x(z2, str), i2);
            } else {
                l1(z2, str);
            }
        } else if (System.currentTimeMillis() - this.e0 > 30000) {
            this.d0 = false;
        }
    }

    public boolean o0(JDJSONObject jDJSONObject, boolean z2, boolean z3) {
        com.jingdong.app.mall.home.floor.common.i.k.l();
        this.B.f8569g.set(false);
        this.M = !z2;
        LottieAnimationMask.e(false);
        com.jingdong.app.mall.home.a.t.q();
        if (!z2 && A0() <= 0) {
            com.jingdong.app.mall.home.c.f().g(jDJSONObject.optJSONObject("homeDialog"));
        }
        f0(jDJSONObject, z2);
        com.jingdong.app.mall.home.floor.common.i.l.g(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "userCategory", ""));
        com.jingdong.app.mall.home.floor.ctrl.n.l();
        com.jingdong.app.mall.home.floor.ctrl.o.i();
        com.jingdong.app.mall.home.floor.ctrl.l.i();
        com.jingdong.app.mall.home.floor.ctrl.m.f();
        String optString = jDJSONObject.optString("cycFirstTimeStamp", "");
        int optInt = jDJSONObject.optInt("cycNum", 0);
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString("HOME_CYCFIRSTTIMESTAMP", optString);
        edit.putInt("HOME_CYCNUM", optInt);
        edit.putString("HOME_CONTROL_MATERIALS", jDJSONObject.optString("controlMaterials", ""));
        edit.apply();
        if (!z2 && com.jingdong.app.mall.home.o.a.f.k0()) {
            if (!z3) {
                com.jingdong.app.mall.home.a.s.c(this.w, jDJSONObject);
            }
            try {
                BaseLoadingView m2 = this.p.m();
                if (m2 instanceof JDHomeBaseLoadingView) {
                    ((JDHomeBaseLoadingView) m2).R("");
                }
                com.jingdong.app.mall.home.floor.ctrl.t.h.d().i(jDJSONObject, this.thisActivity, z3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        int[] q2 = com.jingdong.app.mall.home.floor.common.i.m.q(com.jingdong.app.mall.home.v.c.a.e() ? "" : jDJSONObject.optString("homeBgColor", ""), IconFloorEntity.BGCOLOR_DEFAULT, true, false);
        com.jingdong.app.mall.home.a.w = -1;
        com.jingdong.app.mall.home.a.x = IconFloorEntity.BGCOLOR_DEFAULT;
        Drawable drawable = this.t;
        if (((q2 == null || com.jingdong.app.mall.home.state.old.a.f()) ? 0 : q2.length) > 0) {
            int i2 = q2[0];
            if (q2.length > 1) {
                com.jingdong.app.mall.home.a.w = i2;
                com.jingdong.app.mall.home.a.x = q2[1];
            } else if (i2 != -1) {
                com.jingdong.app.mall.home.a.w = i2;
                com.jingdong.app.mall.home.a.x = i2;
            }
            drawable = new ColorDrawable(com.jingdong.app.mall.home.a.x);
        }
        com.jingdong.app.mall.home.o.a.f.E0(new q(drawable));
        K0(jDJSONObject);
        com.jingdong.app.mall.home.floor.common.i.h.d(jDJSONObject.optJSONArray("futureFloorList"));
        return false;
    }

    public synchronized void o1(JDJSONObject jDJSONObject, List<com.jingdong.app.mall.home.r.e.d> list, List<com.jingdong.app.mall.home.r.e.d> list2, boolean z2) {
        IHomeTitle iHomeTitle;
        k0();
        FloatLayout floatLayout = this.f8503l;
        if (floatLayout != null) {
            floatLayout.M();
        }
        RuleFloatLayout ruleFloatLayout = this.y;
        if (ruleFloatLayout != null && !z2) {
            ruleFloatLayout.i();
        }
        this.B.u();
        com.jingdong.app.mall.home.a.u.n();
        E0();
        com.jingdong.app.mall.home.a.f8562k = v0();
        this.B.z(null);
        com.jingdong.app.mall.home.x.g.b().g();
        com.jingdong.app.mall.home.r.c.a.i().q();
        com.jingdong.app.mall.home.r.c.a.i().D(com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k);
        this.r.C(list, z2);
        r0();
        this.C.r();
        boolean z3 = false;
        for (int i2 = 0; i2 < list2.size(); i2++) {
            com.jingdong.app.mall.home.r.e.d dVar = list2.get(i2);
            if (dVar != null && dVar.mParentModel != null) {
                L0(dVar, z2);
                if (dVar.q == com.jingdong.app.mall.home.floor.common.i.t.TOPROTATE) {
                    z3 = true;
                }
            }
        }
        this.C.d();
        if (!z3 && (iHomeTitle = this.f8504m) != null) {
            iHomeTitle.resetLogo();
        }
        this.B.c();
        FloatLayout floatLayout2 = this.f8503l;
        if (floatLayout2 != null) {
            floatLayout2.p();
        }
        if (!com.jingdong.app.mall.home.floor.common.i.s.d.get()) {
            u1();
        } else if (HomeRecommend.isLoadRecommend() && com.jingdong.app.mall.home.floor.common.i.s.b < 5) {
            S0();
        }
        IHomeTitle iHomeTitle2 = this.f8504m;
        if (iHomeTitle2 != null) {
            this.B.b(iHomeTitle2);
        }
        j1();
        this.C.p();
        this.B.q(z2);
        this.B.a.c(this.q, this.f8501j, jDJSONObject, com.jingdong.app.mall.home.floor.common.i.s.d.get(), new t());
        W0();
        if (this.V != null && this.Y && com.jingdong.app.mall.home.floor.common.i.g.o()) {
            JDRouter.to(this.thisActivity, "router://JDNavigationModule/refreshNavigation").open();
            this.Y = false;
        }
    }

    private void p0() {
        HomePullRefreshRecyclerView homePullRefreshRecyclerView;
        if (s0.get() || (homePullRefreshRecyclerView = this.p) == null || !homePullRefreshRecyclerView.q0()) {
            return;
        }
        IHomeTitle iHomeTitle = this.f8504m;
        if (iHomeTitle == null || !iHomeTitle.isAnimating()) {
            o0 = w0();
            h1(0);
            D1();
            this.Y = true;
            this.p.d0();
        }
    }

    private void q1() {
        this.R = JDElderModeUtils.isElderMode();
        this.S = com.jingdong.app.mall.home.v.d.a.e();
        this.Q = com.jingdong.app.mall.home.u.a.w().v();
        this.D = LoginUserBase.getUserPin();
    }

    public void r1() {
        this.A.w();
        com.jingdong.app.mall.home.o.a.f.E0(new o());
    }

    private void s0(boolean z2) {
        if (z2) {
            IHomeTitle iHomeTitle = this.f8504m;
            if (iHomeTitle != null) {
                iHomeTitle.onBackPressed();
            }
            com.jingdong.app.mall.home.floor.common.i.u.k();
        }
    }

    public static void s1() {
        i0 = null;
    }

    public void t1(boolean z2) {
        com.jingdong.app.mall.home.o.a.f.E0(new r(z2));
    }

    static /* synthetic */ long u(JDHomeFragment jDHomeFragment, long j2) {
        long j3 = jDHomeFragment.f8505n - j2;
        jDHomeFragment.f8505n = j3;
        return j3;
    }

    public static Activity u0() {
        BaseActivity baseActivity = i0 == null ? null : i0.thisActivity;
        return (baseActivity == null || (Build.VERSION.SDK_INT >= 17 && baseActivity.isDestroyed())) ? (Activity) BaseFrameUtil.getInstance().getMainFrameActivity() : baseActivity;
    }

    public void u1() {
        com.jingdong.app.mall.home.o.a.f.E0(new u());
    }

    static /* synthetic */ long y() {
        return C0();
    }

    public void y1(Drawable drawable) {
        Window G0 = G0();
        this.v = drawable;
        if (com.jingdong.app.mall.home.state.dark.a.h()) {
            drawable = this.u;
        }
        boolean z2 = G0 != null;
        if (z2) {
            G0.setBackgroundDrawable(drawable);
        }
        RelativeLayout relativeLayout = this.w;
        if (relativeLayout != null) {
            if (z2) {
                drawable = null;
            }
            relativeLayout.setBackgroundDrawable(drawable);
        }
    }

    @CheckForNull
    public static JDHomeFragment z0() {
        if (i0 == null && Looper.getMainLooper() == Looper.myLooper()) {
            s0.set(false);
            i0 = new JDHomeFragment();
        }
        return i0;
    }

    public long A0() {
        com.jingdong.app.mall.home.g a2;
        com.jingdong.app.mall.home.f fVar = this.V;
        if (fVar == null || (a2 = fVar.a()) == null) {
            return 0L;
        }
        return a2.a();
    }

    public HttpResponse B0() {
        return this.K;
    }

    public void B1(JDJSONObject jDJSONObject, boolean z2, boolean z3) {
        HomeRecommend homeRecommend = this.P;
        if (homeRecommend != null) {
            homeRecommend.setRecommendUI(jDJSONObject, z2, z3);
        }
    }

    public void C1(j.b bVar) {
        com.jingdong.app.mall.home.o.a.f.E0(new z(bVar));
    }

    public void D1() {
        b1(false);
        HomeRecycleView homeRecycleView = this.q;
        if (homeRecycleView == null) {
            return;
        }
        boolean z2 = homeRecycleView.c() != 0;
        if (z2) {
            MallFloorEvent.g();
        }
        this.q.u(0);
        h1(0);
        this.G.g0(0);
        this.q.addOnLayoutChangeListener(new k(z2));
    }

    public int E0() {
        int i2;
        int currentBarHeight;
        IHomeTitle iHomeTitle = this.f8504m;
        if (iHomeTitle == null) {
            currentBarHeight = HomeTitleFactory.getDefaultHeight() + com.jingdong.app.mall.home.floor.ctrl.h.A;
            i2 = currentBarHeight;
        } else {
            int barHeightShrink = iHomeTitle.getBarHeightShrink();
            i2 = barHeightShrink;
            currentBarHeight = this.f8504m.getCurrentBarHeight();
        }
        com.jingdong.app.mall.home.a.f8561j = currentBarHeight;
        com.jingdong.app.mall.home.a.f8560i = i2;
        return i2;
    }

    public void E1(boolean z2) {
        this.W = z2;
        com.jingdong.app.mall.home.o.a.f.E0(new j());
    }

    public int F0() {
        HomeRecycleView homeRecycleView = this.q;
        if (homeRecycleView == null) {
            return 0;
        }
        int i2 = homeRecycleView.i();
        this.G.g0(P0() ? 0 : -i2);
        this.c0 = Math.max(this.c0, i2);
        com.jingdong.app.mall.home.i.p(i2);
        return i2;
    }

    public Window G0() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity.getWindow();
        }
        return null;
    }

    public long H0() {
        long A0 = A0();
        if (A0 > 0) {
            return this.o - A0;
        }
        return 0L;
    }

    public void I0(String str, boolean z2) {
        long nanoTime = System.nanoTime();
        n0 = false;
        if (this.z == null) {
            n nVar = new n(nanoTime);
            this.z = nVar;
            this.A.C(nVar);
        }
        com.jingdong.app.mall.home.o.a.f.a("SHome_Load");
        if ((!z2 || this.K == null) && this.A.A(str) && !TextUtils.isEmpty(str)) {
            this.F = str;
        }
    }

    public void S0() {
        com.jingdong.app.mall.home.floor.ctrl.r rVar;
        if (this.P == null || !com.jingdong.app.mall.home.floor.common.i.s.d.get()) {
            return;
        }
        if (this.f8499h.get() && this.P.hasRecommendData()) {
            return;
        }
        this.f8499h.set(true);
        this.P.loadRecommendData();
        com.jingdong.app.mall.home.a aVar = this.B;
        if (aVar == null || (rVar = aVar.a) == null) {
            return;
        }
        rVar.f9509h.set(true);
    }

    public boolean T0() {
        BaseActivity baseActivity;
        boolean z2 = false;
        if (this.X && (baseActivity = this.thisActivity) != null) {
            Intent intent = baseActivity.getIntent();
            if (intent != null && JumpUtil.VALUE_DES_APPHOMETOPTAB.equals(intent.getStringExtra("des"))) {
                z2 = true;
            }
            if (z2) {
                HourlyGoBridge.setHourGoInfo(intent);
            }
        }
        return z2;
    }

    public void U0(boolean z2) {
        com.jingdong.app.mall.home.i.q(false);
        j0(z2);
        com.jingdong.app.mall.home.p.b.d.c.g().l(z2);
        com.jingdong.app.mall.home.floor.view.b.f.e.j().p(false);
        a1();
        com.jingdong.app.mall.home.o.a.f.K0(this.g0);
        this.C.n();
        com.jingdong.app.mall.home.f fVar = this.V;
        if (fVar != null) {
            fVar.e();
        }
        HomeRecommend homeRecommend = this.P;
        if (homeRecommend != null) {
            homeRecommend.setNeedWaitSplash(false);
        }
        this.B.p(false);
        EventBus.getDefault().post(new MallFloorEvent("home_splash_close", com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k));
        if (!z2 && this.M) {
            com.jingdong.app.mall.home.o.a.c.a(this.thisActivity, this.f8504m, this.a0);
            TitleTabManager.getInstance().onSplashClose();
        } else {
            com.jingdong.app.mall.home.c.f().n();
        }
        com.jingdong.app.mall.home.k kVar = this.I;
        if (kVar == null || z2) {
            return;
        }
        kVar.y(true);
    }

    public void V0() {
        com.jingdong.app.mall.home.i.q(true);
        com.jingdong.app.mall.home.p.b.d.c.g().m();
        com.jingdong.app.mall.home.floor.view.b.f.e.j().p(true);
        b1(false);
        com.jingdong.app.mall.home.o.a.f.m(this.g0);
        this.C.o();
        HomeRecommend homeRecommend = this.P;
        if (homeRecommend != null) {
            homeRecommend.setNeedWaitSplash(true);
        }
        this.B.p(true);
        EventBus.getDefault().post(new MallFloorEvent("home_splash_open", com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k));
    }

    public void W0() {
        h1(F0());
    }

    public void X0(int i2, boolean z2, boolean z3) {
        FloatLayout floatLayout = this.f8503l;
        if (floatLayout != null && i2 >= 0) {
            floatLayout.K(i2 == 0);
        }
        if (z2 && i2 == 0) {
            MallFloorEvent.h();
            Y0(i2);
        }
        if (1 == i2) {
            com.jingdong.app.mall.home.a.t.m();
        }
        int F0 = F0();
        if (F0 < 0) {
            return;
        }
        if (z3) {
            com.jingdong.app.mall.home.o.a.f.u0(new i(F0));
        } else {
            h1(F0);
        }
    }

    public void Y0(int i2) {
        this.B.l(i2);
    }

    public void Z0() {
        com.jingdong.app.mall.home.n.c cVar = this.h0;
        if (cVar != null) {
            cVar.f();
        }
    }

    protected void a1() {
        if (com.jingdong.app.mall.home.a.u == null) {
            return;
        }
        E0();
        com.jingdong.app.mall.home.a.f8562k = v0();
        com.jingdong.app.mall.home.a.u.z();
    }

    protected void b1(boolean z2) {
        com.jingdong.app.mall.home.r.a.c cVar = com.jingdong.app.mall.home.a.u;
        if (cVar == null) {
            return;
        }
        cVar.N(z2);
    }

    public void c1(CategoryEntity.CaItem caItem, int i2) {
        if (i2 == 0 && com.jingdong.app.mall.home.n.c.p()) {
            return;
        }
        if (this.w == null) {
            com.jingdong.app.mall.home.o.a.l.i("homeLayout is null");
            return;
        }
        if (this.h0 == null) {
            this.h0 = new com.jingdong.app.mall.home.n.c(this, this.w, this.p, this.q);
        }
        boolean z2 = i2 != 0;
        boolean z3 = s0.get() ^ z2;
        s0.set(z2);
        this.h0.g(caItem, i2);
        if (z3) {
            F0();
            if (s0.get()) {
                com.jingdong.app.mall.home.k kVar = this.I;
                if (kVar != null) {
                    kVar.C();
                }
                e1(true);
                i1(true);
                FloatLayout floatLayout = this.f8503l;
                if (floatLayout != null) {
                    floatLayout.setVisibility(8);
                    return;
                }
                return;
            }
            CaMoreLayout.h();
            f1(true);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
        IHomeTitle iHomeTitle;
        com.jingdong.app.mall.home.n.c cVar;
        if (Log.D) {
            Log.d("navigation-click", this.f8498g + "   old-->" + i2 + " now-->" + i3);
        }
        boolean z2 = false;
        if (this.U.getAndSet(false)) {
            return;
        }
        if (i3 == 0 && i2 == i3) {
            z2 = true;
        }
        if (z2 && (cVar = this.h0) != null && cVar.j(4)) {
            return;
        }
        if (z2 && (iHomeTitle = this.f8504m) != null && iHomeTitle.onBackPressed()) {
            return;
        }
        if (z2 && s0.get()) {
            CategoryEntity.CaItem i4 = CaContentLayout.i();
            if (i4 != null) {
                com.jingdong.app.mall.home.n.g.v.b.c("Category_Main_Return", i4.getSrvString());
            }
            CaMoreLayout.p();
            com.jingdong.app.mall.home.floor.common.i.u.k();
            return;
        }
        if (i2 == i3 && i3 == 0 && !TextUtils.isEmpty(str) && com.jingdong.app.mall.home.o.a.l.b()) {
            CommonBridge.goToMWithUrl(this.thisActivity, str);
            JDMtaUtils.onClickWithPageId(this.thisActivity, "Home_NavigationIcon", getClass().getName(), "NavigationBar_Main");
        } else if (i2 == i3 && i3 == 0 && j0 == 0 && com.jingdong.app.mall.home.a.v) {
            p0();
        }
        com.jingdong.app.mall.home.o.a.f.a("SNavigationBar_Home");
    }

    public void d1() {
        this.X = true;
        g0();
        h0();
        s0(true ^ this.Z);
    }

    protected void e1(boolean z2) {
        com.jingdong.app.mall.home.n.c cVar;
        com.jingdong.app.mall.home.i.c();
        com.jingdong.app.mall.home.floor.common.i.k.l();
        CaMoreLayout.r();
        com.jingdong.app.mall.home.floor.view.b.f.d.i();
        if (!z2) {
            this.Y = false;
            l0.set(false);
        }
        this.Z = false;
        this.X = false;
        this.B.r();
        if (!z2 && P0() && (cVar = this.h0) != null) {
            cVar.k();
        }
        k0.set(false);
        EventBus.getDefault().post(new MallFloorEvent("home_pause"));
        b1(true);
        com.jingdong.app.mall.home.r.c.a.i().m();
        com.jingdong.app.mall.home.r.c.a.i().l();
        com.jingdong.app.mall.home.r.c.a.i().j();
        com.jingdong.app.mall.home.r.c.a.i().n();
        com.jingdong.app.mall.home.r.c.a.y("Home_ScrollScreen", "", D0().toString());
        this.c0 = 0.0f;
        IHomeTitle iHomeTitle = this.f8504m;
        if (iHomeTitle != null && !z2) {
            iHomeTitle.onPause();
        }
        HttpGroupWithNPS httpGroupWithNPS = this.mHttpGroupWithNPS;
        if (httpGroupWithNPS != null) {
            httpGroupWithNPS.onPause();
        }
        HomePullRefreshRecyclerView homePullRefreshRecyclerView = this.p;
        if (homePullRefreshRecyclerView != null) {
            homePullRefreshRecyclerView.U(false);
        }
        this.C.i();
        com.jingdong.app.mall.home.shakeandshow.f.a(this.thisActivity);
        com.jingdong.app.mall.home.x.g.b().c();
    }

    /* JADX WARN: Removed duplicated region for block: B:305:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x01c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void f1(boolean z2) {
        boolean z3;
        com.jingdong.app.mall.home.k kVar;
        RuleFloatLayout ruleFloatLayout;
        IHomeTitle iHomeTitle;
        FloatLayout floatLayout;
        com.jingdong.app.mall.home.i.d();
        com.jingdong.app.mall.home.o.a.f.i();
        CaMoreLayout.s();
        com.jingdong.app.mall.home.state.dark.a.b();
        TitleTabManager.getInstance().onLbsStateChanged();
        j0(false);
        l0.set(true);
        boolean d2 = com.jingdong.app.mall.home.j.d();
        String str = this.E;
        boolean z4 = (str == null || str.equals(LoginUserBase.getUserPin())) ? false : true;
        if (z4) {
            this.E = LoginUserBase.getUserPin();
            com.jingdong.app.mall.home.n.h.a.b();
        }
        if (!z2) {
            MessagePullUtils.pullMessageNotification(DeepLinkRankHelper.HOME);
        }
        y1(this.v);
        boolean z5 = this.R != JDElderModeUtils.isElderMode();
        boolean z6 = this.S != com.jingdong.app.mall.home.v.d.a.e();
        if (P0() && this.h0 != null) {
            if (!z5 && !z6) {
                IHomeTitle iHomeTitle2 = this.f8504m;
                if (iHomeTitle2 != null && !z2) {
                    iHomeTitle2.onResume(d2);
                }
                com.jingdong.app.mall.home.widget.b lastCreateView = com.jingdong.app.mall.home.floor.common.i.t.FLOOR_CATEGORY.getLastCreateView();
                if (lastCreateView instanceof MallFloorCategory) {
                    ((MallFloorCategory) lastCreateView).checkExpoItem();
                }
                if (z4) {
                    this.h0.m();
                }
                this.h0.l();
                return;
            }
            r0();
            return;
        }
        k0.set(true);
        com.jingdong.app.mall.home.r.c.a.i().p();
        com.jingdong.app.mall.home.r.c.a.i().r();
        if (!this.isUseBasePV) {
            com.jingdong.app.mall.home.r.c.a.C(this.thisActivity, this, getPageParam());
        }
        com.jingdong.app.mall.home.n.c cVar = this.h0;
        if (cVar != null) {
            cVar.j(4);
        }
        HomeRecyclerAdapter homeRecyclerAdapter = this.r;
        if (homeRecyclerAdapter != null) {
            homeRecyclerAdapter.onResume();
        }
        com.jingdong.app.mall.home.f fVar = this.V;
        if (fVar != null) {
            fVar.c();
        }
        HttpGroupWithNPS httpGroupWithNPS = this.mHttpGroupWithNPS;
        if (httpGroupWithNPS != null) {
            httpGroupWithNPS.onResume();
        }
        HomeRecycleView homeRecycleView = this.q;
        if (homeRecycleView != null) {
            homeRecycleView.m();
        }
        com.jingdong.app.mall.home.floor.ctrl.h.N().h0(this.f8504m);
        int v2 = com.jingdong.app.mall.home.u.a.w().v();
        if (!com.jingdong.app.mall.home.c.f().h()) {
            if (i0()) {
                if (JDElderModeUtils.isNeedShowNormalModeDialog()) {
                    JDElderModeUtils.showNormalModeDialog(this.thisActivity, "shouye");
                    com.jingdong.app.mall.home.o.a.k.e("Used NormalModeDialog : OnResume");
                }
            } else if (!z5 && !z6) {
                int i2 = this.Q;
                if (i2 >= 0 && i2 != v2) {
                    n1(true, "6", 0);
                } else if (R0() && !this.Z) {
                    n1(com.jingdong.app.mall.home.o.a.l.g(), "4", 1000);
                }
            } else {
                n1(true, "7", 0);
            }
            z3 = true;
            q1();
            com.jingdong.app.mall.home.k kVar2 = this.I;
            boolean z7 = kVar2 == null && kVar2.n();
            if (!z3) {
                E1(z5 || z6);
                com.jingdong.app.mall.home.floor.bottomfloat.a.d().i();
            } else {
                com.jingdong.app.mall.home.p.b.d.c.g().d();
            }
            com.jingdong.app.mall.home.floor.ctrl.t.h.d().h(N0(), z3);
            com.jingdong.app.mall.home.floor.bottomfloat.a.d().f();
            this.C.j(z3);
            kVar = this.I;
            if (kVar != null) {
                kVar.y(z7);
            }
            ruleFloatLayout = this.y;
            if (ruleFloatLayout != null) {
                ruleFloatLayout.f();
            }
            E0();
            com.jingdong.app.mall.home.a.f8562k = v0();
            EventBus.getDefault().post(new MallFloorEvent("home_resume", com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, z3));
            this.B.t();
            iHomeTitle = this.f8504m;
            if (iHomeTitle != null && !z2) {
                iHomeTitle.onResume(d2);
                this.f8504m.forceRefreshBarStatus();
                if (!z3) {
                    this.f8504m.changeSearchBarColorVarScrolling(F0());
                }
            }
            floatLayout = this.f8503l;
            if (floatLayout != null) {
                floatLayout.H();
            }
            k0();
            com.jingdong.app.mall.home.floor.ctrl.t.i.p().x();
            com.jingdong.app.mall.home.u.a.w().C(this.thisActivity);
            com.jingdong.app.mall.home.o.a.d.k();
            com.jingdong.app.mall.home.o.a.f.u0(new w());
            com.jingdong.app.mall.home.x.g.b().d();
        }
        z3 = false;
        q1();
        com.jingdong.app.mall.home.k kVar22 = this.I;
        if (kVar22 == null) {
        }
        if (!z3) {
        }
        com.jingdong.app.mall.home.floor.ctrl.t.h.d().h(N0(), z3);
        com.jingdong.app.mall.home.floor.bottomfloat.a.d().f();
        this.C.j(z3);
        kVar = this.I;
        if (kVar != null) {
        }
        ruleFloatLayout = this.y;
        if (ruleFloatLayout != null) {
        }
        E0();
        com.jingdong.app.mall.home.a.f8562k = v0();
        EventBus.getDefault().post(new MallFloorEvent("home_resume", com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, z3));
        this.B.t();
        iHomeTitle = this.f8504m;
        if (iHomeTitle != null) {
            iHomeTitle.onResume(d2);
            this.f8504m.forceRefreshBarStatus();
            if (!z3) {
            }
        }
        floatLayout = this.f8503l;
        if (floatLayout != null) {
        }
        k0();
        com.jingdong.app.mall.home.floor.ctrl.t.i.p().x();
        com.jingdong.app.mall.home.u.a.w().C(this.thisActivity);
        com.jingdong.app.mall.home.o.a.d.k();
        com.jingdong.app.mall.home.o.a.f.u0(new w());
        com.jingdong.app.mall.home.x.g.b().d();
    }

    public void g0() {
        com.jingdong.app.mall.home.floor.common.i.i.b(this.thisActivity);
        com.jingdong.app.mall.home.floor.ctrl.t.h.d().b(this.thisActivity);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public String getPageParam() {
        long A0 = A0();
        return (A0 <= 0 || SystemClock.elapsedRealtime() - A0 >= 600000) ? "0" : "1";
    }

    public boolean i0() {
        if (O0()) {
            String str = this.D;
            boolean z2 = (str == null || str.equals(LoginUserBase.getUserPin())) ? false : true;
            if (z2) {
                n1(true, "5", 0);
            }
            return z2;
        }
        return false;
    }

    protected void i1(boolean z2) {
        com.jingdong.app.mall.home.o.a.f.f();
        EventBus.getDefault().post(new MallFloorEvent("home_stop"));
        com.jingdong.app.mall.home.floor.ctrl.t.i.p().y();
        com.jingdong.app.mall.home.u.a.w().D(z2);
        com.jingdong.app.mall.home.o.a.f.H0(this);
        HomeRecyclerAdapter homeRecyclerAdapter = this.r;
        if (homeRecyclerAdapter != null) {
            homeRecyclerAdapter.v();
        }
        IHomeTitle iHomeTitle = this.f8504m;
        if (iHomeTitle != null && !z2) {
            iHomeTitle.onHomeStop();
        }
        RuleFloatLayout ruleFloatLayout = this.y;
        if (ruleFloatLayout != null) {
            ruleFloatLayout.g();
        }
    }

    protected void initView() {
        this.J = (RelativeLayout) this.w.findViewById(R.id.home_tips);
        HomePullRefreshRecyclerView homePullRefreshRecyclerView = (HomePullRefreshRecyclerView) this.w.findViewById(R.id.pull_refresh_scroll);
        this.p = homePullRefreshRecyclerView;
        homePullRefreshRecyclerView.a0(new b());
        this.p.q().setOverScrollMode(2);
        this.p.setFadingEdgeLength(0);
        this.p.setVerticalScrollBarEnabled(false);
        RecyclerView q2 = this.p.q();
        com.jingdong.app.mall.home.o.a.f.n(q2);
        this.q = (HomeRecycleView) q2;
        this.p.Z(new c());
        this.q.addOnScrollListener(new d());
        HomeFooterView homeFooterView = new HomeFooterView(this.thisActivity);
        this.f8500i = homeFooterView;
        this.L = new com.jingdong.app.mall.home.r.e.e(homeFooterView, com.jingdong.app.mall.home.floor.common.i.t.FLOOR_ERROR);
        this.f8500i.setFooterState(4);
        this.f8500i.setRetryListener(new e());
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) this.w.findViewById(R.id.direct_to_recommend);
        this.f8501j = simpleDraweeView;
        ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.width = this.f8502k.v();
        layoutParams2.height = this.f8502k.h();
        this.f8502k.G(new Rect(0, 0, 12, 4), layoutParams2);
        this.f8501j.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f8501j.setLayoutParams(layoutParams2);
        this.f8503l = (FloatLayout) this.w.findViewById(R.id.home_float_layout);
        A1();
        HomeRecyclerAdapter homeRecyclerAdapter = new HomeRecyclerAdapter(this.thisActivity, this.f8504m, this.q);
        this.r = homeRecyclerAdapter;
        HomeRecommend o2 = homeRecyclerAdapter.o();
        this.P = o2;
        if (o2 != null) {
            o2.setOnRequestResultListener(new f());
            this.P.setOnGetRecommendDataListener(new g());
        }
        this.q.setAdapter(this.r);
    }

    public void j0(boolean z2) {
        if (r0 || com.jingdong.app.mall.home.i.i()) {
            return;
        }
        r0 = true;
        ThreadManager.light().postDelay(new a0(this), 2000L);
    }

    public void j1() {
        E0();
        com.jingdong.app.mall.home.a aVar = this.B;
        int i2 = aVar == null ? 0 : aVar.i();
        HomeRecyclerAdapter homeRecyclerAdapter = this.r;
        if (homeRecyclerAdapter != null) {
            homeRecyclerAdapter.y(com.jingdong.app.mall.home.a.f8560i + i2);
        }
    }

    public void k1() {
        HomeRecycleView homeRecycleView = this.q;
        if (homeRecycleView == null) {
            return;
        }
        homeRecycleView.u(0);
        h1(0);
        MallFloorEvent.g();
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        com.jingdong.app.mall.home.s.a.b().m("jdHomeFragment", "onCreate");
        com.jingdong.app.mall.home.o.a.k.g("onCreate");
        long nanoTime = System.nanoTime();
        this.isTransStatusbar = true;
        if (Log.D) {
            Log.d(this.f8498g, "onCreate -->> ");
        }
        this.isUseBasePV = SwitchQueryFetcher.getSwitchBooleanValue("mp_pv_switch", false);
        setPageId(RecommendMtaUtils.Home_PageId);
        com.jingdong.app.mall.home.floor.common.i.u.i();
        com.jingdong.app.mall.home.w.a.a(new v());
        super.onCreate(bundle);
        TextScaleModeHelper.INSTANCE.getInstance().addOnTextSizeChangeListener(com.jingdong.app.mall.home.e.b());
        q0();
        if (Log.D) {
            Log.d(this.f8498g, "timetest-onCreate start-->> " + nanoTime);
            Log.d(this.f8498g, "timetest-onCreate duration-->> " + (System.nanoTime() - nanoTime));
        }
        com.jingdong.app.mall.home.o.a.k.g("onCreate");
        com.jingdong.app.mall.home.s.a.b().l("jdHomeFragment", "onCreate");
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        com.jingdong.app.mall.home.s.a.b().m("jdHomeFragment", "onCreateViews");
        com.jingdong.app.mall.home.o.a.k.g("onCreateViews");
        this.X = true;
        g0();
        if (T0()) {
            HourlyGoBridge.saveHourlyDirect();
            m0();
        }
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(2, 2);
        }
        com.jingdong.app.mall.home.floor.common.d.g(this.thisActivity);
        com.jingdong.app.mall.home.d.c(false);
        com.jingdong.app.mall.home.floor.ctrl.h.N().Y(this.thisActivity);
        com.jingdong.app.mall.home.v.b.c(this.thisActivity, new c0());
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.f8505n = elapsedRealtime;
        this.o = elapsedRealtime;
        this.w = (RelativeLayout) layoutInflater.inflate(R.layout.pull_refresh_scroll_new, (ViewGroup) null);
        this.thisActivity.addDestroyListener(new d0());
        this.w.addOnLayoutChangeListener(new e0());
        k0();
        initView();
        I0("0", true);
        Looper.myQueue().addIdleHandler(new f0());
        this.C.f(this.thisActivity, this.w);
        y1(this.t);
        com.jingdong.app.mall.home.o.a.k.g("onCreateViews");
        com.jingdong.app.mall.home.s.a.b().l("jdHomeFragment", "onCreateViews");
        return this.w;
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new MallFloorEvent("home_destroy"));
        com.jingdong.app.mall.home.u.a.w().A();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        String type = baseEvent.getType();
        type.hashCode();
        if (type.equals("overseas_change_area")) {
            p1("6");
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z2) {
        super.onHiddenChanged(z2);
        if (z2) {
            e1(false);
            i1(false);
            return;
        }
        f1(false);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.sdk.lib.compact.CompactBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (com.jingdong.app.mall.home.c.f().h()) {
                return true;
            }
            ElderGuideLayout elderGuideLayout = this.T;
            if ((elderGuideLayout != null && elderGuideLayout.c()) || com.jingdong.app.mall.home.floor.ctrl.t.i.p().w() || CaMoreLayout.p()) {
                return true;
            }
            com.jingdong.app.mall.home.n.c cVar = this.h0;
            if (cVar == null || !cVar.j(4)) {
                IHomeTitle iHomeTitle = this.f8504m;
                if (iHomeTitle == null || !iHomeTitle.onBackPressed()) {
                    if (s0.get()) {
                        com.jingdong.app.mall.home.floor.common.i.u.k();
                        return true;
                    } else if (com.jingdong.app.mall.home.floor.ctrl.t.h.d().e(N0())) {
                        return true;
                    } else {
                        this.f8505n = SystemClock.elapsedRealtime();
                        com.jingdong.app.mall.home.f fVar = this.V;
                        return fVar != null && fVar.d();
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        com.jingdong.app.mall.home.s.a.b().h();
        com.jingdong.app.mall.home.o.a.f.K0(null);
        e1(false);
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        com.jingdong.app.mall.home.s.a.b().i();
        com.jingdong.app.mall.home.s.a.b().m("jdHomeFragment", "onResume");
        com.jingdong.app.mall.home.o.a.k.g("onResume");
        super.onResume();
        f1(false);
        com.jingdong.app.mall.home.o.a.k.g("onResume");
        com.jingdong.app.mall.home.s.a.b().l("jdHomeFragment", "onResume");
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        i1(false);
    }

    public void p1(String str) {
        n1(true, str, 0);
        com.jingdong.app.mall.home.o.a.f.E0(new h());
    }

    public void q0() {
        IHomeTitle iHomeTitle = this.f8504m;
        if (iHomeTitle != null) {
            iHomeTitle.forceRefreshBarStatus();
            this.f8504m.changeSearchBarColorVarScrolling(F0());
        }
    }

    public void r0() {
        s0(true);
    }

    public HomeRecyclerAdapter t0() {
        return this.r;
    }

    public int v0() {
        RelativeLayout relativeLayout = this.w;
        int height = relativeLayout == null ? 0 : relativeLayout.getHeight();
        return height > 0 ? height : com.jingdong.app.mall.home.floor.common.d.f9278f - com.jingdong.app.mall.home.floor.common.i.m.s();
    }

    protected void v1() {
        if (!com.jingdong.app.mall.home.floor.common.i.s.d.get() || this.r == null) {
            return;
        }
        u1();
    }

    public String w0() {
        if (this.thisActivity == null) {
            return "0.0";
        }
        this.thisActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        return new DecimalFormat("0.0").format(Math.abs(F0() / (v0() - r0.top)));
    }

    public void w1(HttpResponse httpResponse) {
        if (httpResponse != null) {
            this.K = httpResponse;
        }
    }

    public RelativeLayout x0() {
        return this.w;
    }

    public void x1(int i2) {
        HomePullRefreshRecyclerView homePullRefreshRecyclerView = this.p;
        if (homePullRefreshRecyclerView == null) {
            return;
        }
        BaseLoadingView m2 = homePullRefreshRecyclerView.m();
        if (m2 instanceof JDHomeBaseLoadingView) {
            ((JDHomeBaseLoadingView) m2).Q(i2);
        }
    }

    public IHomeTitle y0() {
        return this.f8504m;
    }

    public void z1(boolean z2, com.jingdong.app.mall.home.f fVar) {
        this.V = fVar;
        p0 = z2;
    }
}
