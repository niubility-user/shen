package com.jingdong.app.mall.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.privacy.JDPrivacyAgreeEvent;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.gray.GrayModelListener;
import com.jingdong.common.gray.JDGrayModelUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uilistener.IWebViewUrlInterceptor;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.tencent.smtt.sdk.WebView;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class MainRightWebActivity extends BaseActivity {
    private static final Handler r;
    private static final AtomicBoolean s;
    private static long t;

    /* renamed from: g  reason: collision with root package name */
    private FitTopImage f8579g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f8580h;

    /* renamed from: i  reason: collision with root package name */
    private RelativeLayout f8581i;

    /* renamed from: j  reason: collision with root package name */
    private CommonMFragment f8582j;

    /* renamed from: k  reason: collision with root package name */
    private IWebViewUrlInterceptor f8583k;

    /* renamed from: l  reason: collision with root package name */
    private MainRightErrorLayout f8584l;

    /* renamed from: m  reason: collision with root package name */
    private View f8585m;

    /* renamed from: n  reason: collision with root package name */
    private String f8586n;
    private String o;
    private String p;
    private boolean q;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            MainRightWebActivity.this.R();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            com.jingdong.app.mall.home.floor.common.i.m.K(MainRightWebActivity.this.f8579g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c(MainRightWebActivity mainRightWebActivity) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* loaded from: classes4.dex */
    class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            MainRightWebActivity.this.U();
            MainRightWebActivity.this.checkPrivacyView();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f8590g;

        e(String str) {
            this.f8590g = str;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            XRender.getInstance().setRenderUrl(this.f8590g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements GrayModelListener {
        f() {
        }

        @Override // com.jingdong.common.gray.GrayModelListener
        public void onModelChanged() {
            JDGrayModelUtils.getInstance().setViewGray(MainRightWebActivity.this.f8585m);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g implements HttpGroup.OnCommonListener {
        g() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            MainRightWebActivity.this.q = false;
            MainRightWebActivity.this.T("onRequest onEnd");
            if (httpResponse == null) {
                MainRightWebActivity.this.V();
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                MainRightWebActivity.this.V();
            } else {
                MainRightWebActivity.this.W(fastJsonObject);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MainRightWebActivity.this.q = false;
            MainRightWebActivity.this.V();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class h extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDJSONObject f8593g;

        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ String f8595g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ boolean f8596h;

            a(String str, boolean z) {
                this.f8595g = str;
                this.f8596h = z;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                MainRightWebActivity.this.N(this.f8595g, this.f8596h);
            }
        }

        h(JDJSONObject jDJSONObject) {
            this.f8593g = jDJSONObject;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            String optString = this.f8593g.optString(CartConstant.KEY_JUMPURL, "");
            if (!TextUtils.isEmpty(optString) || !TextUtils.isEmpty(MainRightWebActivity.this.o)) {
                MainRightWebActivity.this.Z();
                if (TextUtils.isEmpty(optString)) {
                    return;
                }
                MainRightWebActivity.this.T("onRequestSuccess");
                boolean equals = TextUtils.equals("1", this.f8593g.optString("forceLoad", "0"));
                boolean equals2 = TextUtils.equals("1", this.f8593g.optString("immersive", "1"));
                com.jingdong.app.mall.home.o.a.f.A0("marinRight_cacheUrl", optString);
                com.jingdong.app.mall.home.o.a.f.y0("marinRight_cacheImmersive", equals2 ? 1 : 0);
                if (!TextUtils.equals(MainRightWebActivity.this.o, "https://h5static.m.jd.com/mall/active/DvFPmKRas9GYjDPZjT9NT14BAv6/index.html?has_native=0&transparent=1&jwebprog=0&hybrid_err_view=1&magic_windows=1") || equals) {
                    MainRightWebActivity.r.removeCallbacksAndMessages(null);
                    MainRightWebActivity.r.postDelayed(new a(optString, equals2), 20L);
                    return;
                }
                return;
            }
            MainRightWebActivity.this.V();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class i extends com.jingdong.app.mall.home.o.a.b {
        i() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            MainRightWebActivity.this.K();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class j extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f8599g;

        j(int i2) {
            this.f8599g = i2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            MainRightWebActivity.this.M(this.f8599g + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class k implements ICheckUrl {
        k() {
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public boolean checkReturn() {
            return false;
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public boolean checkUrl(WebView webView, String str) {
            MainRightWebActivity.this.R();
            return false;
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public String getKey() {
            return "MainRightWebCheckCommit";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class l implements ICheckUrl {
        l() {
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public boolean checkReturn() {
            return false;
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public boolean checkUrl(WebView webView, String str) {
            MainRightWebActivity.this.R();
            return false;
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public String getKey() {
            return "MainRightWebCheckFinish";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class m extends MainRightErrorLayout {
        m(Context context) {
            super(context);
        }

        @Override // com.jingdong.app.mall.home.activity.MainRightErrorLayout
        protected void c() {
            MainRightWebActivity.this.a0();
        }
    }

    static {
        JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
        r = new Handler(Looper.getMainLooper());
        s = new AtomicBoolean(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
        if (!TextUtils.isEmpty(this.o)) {
            Z();
            return;
        }
        b0("1", DYConstants.DY_NULL_STR);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root_content);
        if (this.f8584l == null) {
            m mVar = new m(this);
            this.f8584l = mVar;
            mVar.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        }
        com.jingdong.app.mall.home.floor.common.i.m.a(relativeLayout, this.f8584l);
    }

    private void L() {
        try {
            M(0);
            com.jingdong.app.mall.home.floor.common.i.m.K(this.f8579g);
            View peekDecorView = getWindow().peekDecorView();
            FitTopImage fitTopImage = new FitTopImage(this);
            this.f8579g = fitTopImage;
            fitTopImage.setBackgroundResource(com.jingdong.app.mall.home.o.a.f.X(R.drawable.main_righ_web_place_holder));
            com.jingdong.app.mall.home.floor.common.i.m.a((ViewGroup) peekDecorView, this.f8579g);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        com.jingdong.app.mall.home.o.a.f.F0(new a(), Final.FIVE_SECOND);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(int i2) {
        CommonMFragment commonMFragment = this.f8582j;
        if (commonMFragment == null || i2 > 10) {
            return;
        }
        JDWebView jdWebView = commonMFragment.getJdWebView();
        if (jdWebView == null) {
            com.jingdong.app.mall.home.o.a.f.F0(new j(i2), 100L);
            return;
        }
        if (!jdWebView.isPageFinished()) {
            IWebViewUrlInterceptor webViewUrlInterceptor = this.f8582j.getWebUiBinder().getWebViewUrlInterceptor();
            if (webViewUrlInterceptor == null || webViewUrlInterceptor == this.f8583k) {
                return;
            }
            this.f8583k = webViewUrlInterceptor;
            webViewUrlInterceptor.addUrlCheckOnCommitVisible(new k());
            webViewUrlInterceptor.addUrlCheckOnPageFinish(new l());
        }
        if (jdWebView.isPageFinished()) {
            R();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N(String str, boolean z) {
        T("addWebFragment");
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, this.o)) {
            return;
        }
        if (TextUtils.equals(str, this.o)) {
            Y();
            return;
        }
        String excludeQuery = HybridUrlUtils.excludeQuery(str);
        if (TextUtils.equals(excludeQuery, this.p)) {
            Y();
            return;
        }
        T("addWebFragment start");
        this.p = excludeQuery;
        this.f8586n = LoginUserBase.getUserPin();
        this.o = str;
        Z();
        b0("0", this.o);
        Bundle bundle = new Bundle();
        this.f8582j = new CommonMFragment();
        bundle.putBoolean(MBaseKeyNames.KEY_SWITCH_IMMERSIVE_OPEN, z);
        bundle.putBoolean(MBaseKeyNames.IS_USE_RIGHT_BUTTON, false);
        bundle.putBoolean("isTopBarGone", false);
        bundle.putBoolean(MKeyNames.NEED_CHECK_NATIVE, false);
        bundle.putBoolean(MKeyNames.IS_USE_BACK_BUTTON, false);
        bundle.putBoolean(MBaseKeyNames.IS_SHOW_MORE_BTN, false);
        bundle.putBoolean(MKeyNames.SHOW_ERROR_VIEW, true);
        bundle.putString("url", str);
        this.f8582j.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content_layout, this.f8582j);
        beginTransaction.commitAllowingStateLoss();
        L();
        T("addWebFragment end");
    }

    private void O() {
        if (TextUtils.isEmpty(this.o)) {
            String O = com.jingdong.app.mall.home.o.a.f.O("marinRight_cacheUrl", "");
            if (TextUtils.isEmpty(O)) {
                O = "https://h5static.m.jd.com/mall/active/DvFPmKRas9GYjDPZjT9NT14BAv6/index.html?has_native=0&transparent=1&jwebprog=0&hybrid_err_view=1&magic_windows=1";
            }
            N(O, com.jingdong.app.mall.home.o.a.f.M("marinRight_cacheImmersive", 1) == 1);
        }
    }

    private void P() {
        this.f8585m = getWindow().getDecorView();
        JDGrayModelUtils.getInstance().addListener(new f());
        JDGrayModelUtils.getInstance().setViewGray(this.f8585m);
    }

    private void Q() {
        com.jingdong.app.mall.home.o.a.f.F0(new b(), 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        try {
            Q();
            FitTopImage fitTopImage = this.f8579g;
            if (fitTopImage == null || this.f8580h) {
                return;
            }
            this.f8580h = true;
            fitTopImage.animate().setDuration(50L).alpha(0.0f);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean S() {
        return UnAndroidUtils.mateXEasyClient(JdSdk.getInstance().getApplicationContext()) || UnAndroidUtils.mateXEasyClientNew(JdSdk.getInstance().getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(String str) {
        com.jingdong.app.mall.home.o.a.f.r0(this, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U() {
        try {
            if (TextUtils.isEmpty(this.f8586n) && !TextUtils.isEmpty(this.o) && LoginUserBase.hasLogin()) {
                this.f8582j.disableDefaultVisibleCallback(true);
                this.f8582j.loadWeb(this.o);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        T("onRequest onEnd");
        com.jingdong.app.mall.home.o.a.f.E0(new i());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W(JDJSONObject jDJSONObject) {
        com.jingdong.app.mall.home.o.a.f.E0(new h(jDJSONObject));
    }

    public static void X() {
        if (s.getAndSet(true) || !com.jingdong.app.mall.home.o.a.f.k0()) {
            return;
        }
        String O = com.jingdong.app.mall.home.o.a.f.O("marinRight_cacheUrl", "");
        if (SwitchQueryFetcher.getSwitchBooleanValue("marinRight_preClose", false)) {
            return;
        }
        if (TextUtils.isEmpty(O) && S()) {
            O = "https://h5static.m.jd.com/mall/active/DvFPmKRas9GYjDPZjT9NT14BAv6/index.html?has_native=0&transparent=1&jwebprog=0&hybrid_err_view=1&magic_windows=1";
        }
        if (TextUtils.isEmpty(O)) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.E0(new e(HybridUrlUtils.excludeQuery(O)));
    }

    private void Y() {
        try {
            T("reloadWebUrl start");
            String userPin = LoginUserBase.getUserPin();
            if (TextUtils.equals(userPin, this.f8586n)) {
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.f8582j != null && !TextUtils.isEmpty(this.o) && !this.f8582j.isLoginStateSyncing() && elapsedRealtime - t >= 200) {
                t = elapsedRealtime;
                this.f8586n = userPin;
                this.f8582j.loadWeb();
                T("reloadWebUrl end");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z() {
        com.jingdong.app.mall.home.floor.common.i.m.K(this.f8581i);
        com.jingdong.app.mall.home.floor.common.i.m.K(this.f8584l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a0() {
        if (!JDPrivacyHelper.isAcceptPrivacy(this) || this.q) {
            return;
        }
        T("requestWebUrl");
        this.q = true;
        d0(new g());
    }

    private void addPrivacyView(ViewGroup viewGroup) {
        if (this.f8581i == null) {
            RelativeLayout relativeLayout = new RelativeLayout(this);
            this.f8581i = relativeLayout;
            relativeLayout.setOnClickListener(new c(this));
            RelativeLayout relativeLayout2 = new RelativeLayout(this);
            ImageView imageView = new ImageView(this);
            imageView.setId(R.id.main_right_privacy_img);
            imageView.setBackgroundResource(com.jingdong.app.mall.home.o.a.f.l0() ? R.drawable.temp_privacy_default : R.drawable.y_10);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            int b2 = com.jingdong.app.mall.home.floor.common.d.b(this, 320);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(b2, b2);
            layoutParams.addRule(14);
            relativeLayout2.addView(imageView, layoutParams);
            int b3 = com.jingdong.app.mall.home.floor.common.d.b(this, -3);
            com.jingdong.app.mall.home.floor.common.g gVar = new com.jingdong.app.mall.home.floor.common.g(this, false);
            gVar.l(-9079435);
            gVar.i("\u5f00\u542f\u6388\u6743\uff0c\u53ef\u4eab\u66f4\u591a\u670d\u52a1");
            gVar.d(17);
            TextView a2 = gVar.a();
            a2.setPadding(0, b3, 0, b3);
            a2.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.b(this, 28));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(14);
            layoutParams2.addRule(3, imageView.getId());
            relativeLayout2.addView(a2, layoutParams2);
            com.jingdong.app.mall.home.floor.common.g gVar2 = new com.jingdong.app.mall.home.floor.common.g(this, false);
            gVar2.l(-9079435);
            gVar2.i("\u70b9\u51fb\u5e95\u90e8\u201c\u6211\u7684\u201d-\u5f39\u6846\u4e2d\u9009\u62e9\u201c\u540c\u610f\u5e76\u7ee7\u7eed\u201d");
            gVar2.d(17);
            TextView a3 = gVar2.a();
            a3.setPadding(0, b3, 0, b3);
            a3.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.b(this, 24));
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, com.jingdong.app.mall.home.floor.common.d.b(this, 112));
            layoutParams3.addRule(14);
            layoutParams3.addRule(3, imageView.getId());
            relativeLayout2.addView(a3, layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams4.addRule(13);
            this.f8581i.addView(relativeLayout2, layoutParams4);
            viewGroup.addView(this.f8581i, new ViewGroup.LayoutParams(-1, -1));
        }
    }

    private void b0(String str, String str2) {
        com.jingdong.app.mall.home.r.c.d b2 = com.jingdong.app.mall.home.r.c.d.b("Home_FoldableSmartphoneExpo");
        b2.a("type", str);
        b2.a("url", str2);
        b2.d();
    }

    private void c0() {
        if (JDPrivacyHelper.isAcceptPrivacy(this)) {
            T("startLoading");
            O();
            a0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPrivacyView() {
        if (JDPrivacyHelper.isAcceptPrivacy(this)) {
            c0();
            return;
        }
        try {
            T("checkPrivacyView");
            b0("2", DYConstants.DY_NULL_STR);
            View peekDecorView = getWindow().peekDecorView();
            if (peekDecorView instanceof ViewGroup) {
                peekDecorView.setBackgroundColor(IconFloorEntity.BGCOLOR_DEFAULT);
                addPrivacyView((ViewGroup) peekDecorView);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void d0(HttpGroup.HttpTaskListener httpTaskListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("foldablePhone");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        httpSetting.setListener(httpTaskListener);
        com.jingdong.app.mall.home.o.a.f.b0(httpSetting);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.statusBarTransparentEnable = true;
        this.isUseBasePV = false;
        setContentView(R.layout.activity_main_right_web);
        checkPrivacyView();
        com.jingdong.app.mall.home.o.a.f.G0(this);
        try {
            P();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        RelativeLayout relativeLayout = this.f8581i;
        if (relativeLayout != null && (baseEvent instanceof JDPrivacyAgreeEvent)) {
            com.jingdong.app.mall.home.floor.common.i.m.K(relativeLayout);
            checkPrivacyView();
        } else if (baseEvent instanceof LoginEvent) {
            T("onEventMainThread");
            com.jingdong.app.mall.home.o.a.f.F0(new d(), 10L);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(this.o)) {
            c0();
        }
    }
}
