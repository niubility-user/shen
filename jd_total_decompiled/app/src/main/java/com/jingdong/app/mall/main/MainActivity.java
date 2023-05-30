package com.jingdong.app.mall.main;

import android.app.Activity;
import android.content.Intent;
import android.content.res.JDMobiSec;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.jd.framework.json.JDJSONObject;
import com.jd.stat.security.jma.JMA;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.h.c.b;
import com.jingdong.app.mall.main.d;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.app.mall.privacy.JDPrivacyManager;
import com.jingdong.app.mall.privacy.PrivacyBridge;
import com.jingdong.app.mall.safemode.i;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.accessibility.AccessibilityUtil;
import com.jingdong.common.login.WebReqCookieUtil;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.unification.statusbar.UnDeviceHelper;
import com.jingdong.common.utils.CalorieImageExpUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DexAsyncUtil;
import com.jingdong.common.utils.FireEyeUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JDSecUtils;
import com.jingdong.common.utils.JMAUtils;
import com.jingdong.common.utils.NightModeUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.X5InitUtil;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.lib.monitor.MonitorInfo;
import com.jingdong.remoteimage.IMtaExceptionReport;
import com.jingdong.remoteimage.RemoteImageManager;
import com.jingdong.sdk.perfmonitor.PerfMonitor;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class MainActivity extends BaseActivity implements d.a {

    /* renamed from: n  reason: collision with root package name */
    public static IMtaExceptionReport f11232n = new a();
    private static com.jingdong.app.mall.h.c.a o = new b();

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.app.mall.main.c f11233g;

    /* renamed from: h  reason: collision with root package name */
    private com.jingdong.app.mall.main.c f11234h;

    /* renamed from: i  reason: collision with root package name */
    private com.jingdong.app.mall.main.d f11235i;

    /* renamed from: j  reason: collision with root package name */
    private AtomicBoolean f11236j = new AtomicBoolean(false);

    /* renamed from: k  reason: collision with root package name */
    private boolean f11237k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f11238l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f11239m;

    /* loaded from: classes4.dex */
    class a implements IMtaExceptionReport {
        a() {
        }

        @Override // com.jingdong.remoteimage.IMtaExceptionReport
        public void calorieGetImgExp(String str, String str2, String str3) {
            CalorieImageExpUtil.calorieGetImgExp(str, str2, str3);
        }

        @Override // com.jingdong.remoteimage.IMtaExceptionReport
        public void exceptionReport(HashMap<String, String> hashMap) {
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        }
    }

    /* loaded from: classes4.dex */
    class b implements com.jingdong.app.mall.h.c.a {
        b() {
        }

        @Override // com.jingdong.app.mall.h.c.a
        public void a(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put(JDMobiSec.n1("cd910f484d"), (Object) str);
            jDJSONObject.put(JDMobiSec.n1("dd970043"), (Object) JDMobiSec.n1("9e"));
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), JDMobiSec.n1("e691174366f0072c7541e33e41"), "", JDMobiSec.n1("e691174366ef033656"), "", "", jDJSONObject.toString(), null);
        }
    }

    /* loaded from: classes4.dex */
    class c extends com.jingdong.app.mall.main.c {
        c(Activity activity, com.jingdong.app.mall.main.c cVar) {
            super(activity, cVar);
        }

        @Override // com.jingdong.app.mall.main.c
        public void b() {
            MainActivity.this.finishActivity();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements JDPrivacyManager.PrivacyCallback {
        d() {
        }

        @Override // com.jingdong.app.mall.privacy.JDPrivacyManager.PrivacyCallback
        public void onClose(boolean z) {
            if (!z) {
                PrivacyBridge.initAfterDisagreePrivacy(false);
            }
            MainActivity.this.z();
            MainActivity.this.f11239m = false;
        }

        @Override // com.jingdong.app.mall.privacy.JDPrivacyManager.PrivacyCallback
        public void onDismiss() {
            MainActivity.this.f11239m = false;
        }
    }

    private void A() {
        if (this.f11238l) {
            return;
        }
        MonitorInfo.setRunStage(1);
        NightModeUtil.setNightModeAlpha(this);
        com.jingdong.app.mall.performance.b.m().s();
        JDMtaUtils.sendExposureDataWithExt(this, JDMobiSec.n1("e691174366e00e365644c42948bf448fe9fb5e8148"), "", JDMobiSec.n1("ef8e0a7975c317315b48"), "", "", AccessibilityUtil.getRunningAccessibilityServiceInfo(this).toString(), null);
        RemoteImageManager.init(RemoteImageManager.newBuilder().setApplication(JdSdk.getInstance().getApplication()).setHost(Configuration.getNgwHost()).setDebug(false).setExceptionReport(f11232n));
        com.jingdong.app.mall.h.c.b e2 = com.jingdong.app.mall.h.c.b.e();
        b.c h2 = com.jingdong.app.mall.h.c.b.h();
        h2.d(JdSdk.getInstance().getApplication());
        h2.e(Configuration.getNgwHost());
        h2.f(o);
        e2.f(h2);
        PerfMonitor.getInstance().install(JdSdk.getInstance().getApplication(), SwitchQueryFetcher.getSwitchBooleanValue(JDMobiSec.n1("de9b084074cd0c364c4fe5"), false));
        this.f11238l = true;
    }

    private void B() {
        boolean isAcceptPrivacy = JDPrivacyHelper.isAcceptPrivacy(this);
        WebUtils.privacyAgreedOnAppStart = isAcceptPrivacy;
        if (!isAcceptPrivacy && !this.f11239m) {
            try {
                PrivacyBridge.launchPrivacyDialog(false, true, this, new d());
                this.f11239m = true;
            } catch (Throwable unused) {
                JDPrivacyManager.getInstance().savePrivacy(true);
                isAcceptPrivacy = true;
            }
        }
        if (!isAcceptPrivacy || this.f11239m) {
            return;
        }
        z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishActivity() {
        this.f11237k = true;
        x();
    }

    private void x() {
        if (this.f11237k && this.f11235i.a()) {
            FireEyeUtils.reportFireEyeEvent(JDMobiSec.n1("de910d434bed0c"), false);
            com.jingdong.app.mall.home.s.a.b().f();
            com.jingdong.app.mall.home.s.a b2 = com.jingdong.app.mall.home.s.a.b();
            String n1 = JDMobiSec.n1("c39f134878c116364e49e324");
            String n12 = JDMobiSec.n1("c991324954c7");
            b2.m(n1, n12);
            String preName = BaseFrameUtil.getPreName();
            try {
                startActivity(new Intent(this, MainFrameActivity.class));
            } catch (Throwable unused) {
                startActivity(DexAsyncUtil.getMainFrameActivityIntent(this));
            }
            CommonBase.getJdSharedPreferences().edit().putBoolean(preName, true).apply();
            finish();
            com.jingdong.app.mall.home.s.a.b().l(n1, n12);
        }
    }

    private void y() {
        if (this.f11236j.getAndSet(true)) {
            return;
        }
        PerformanceReporter.init();
        WebUtils.fix64();
        X5InitUtil.preloadX5(getApplicationContext());
        PrivacyBridge.requestHomeData();
        SwitchQueryFetcher.getFetcher().fetch(JDMobiSec.n1("9f"));
        WebViewHelper.getUrlFilterRule();
        WebReqCookieUtil.initWebCookie();
        if (JDPrivacyHelper.isAcceptPrivacy(this)) {
            JMA.genSoftKey(this);
            String n1 = JDMobiSec.n1("fdbf195250d40b2b4173e33c5bbf");
            JMAUtils.JMAReportReferrerForMainActivity(n1, this);
            JDSecUtils.report(n1, this);
            com.jingdong.app.mall.log.e.b().g();
        }
        WebHybridUtils.loadConfig();
        WebHybridUtils.loadBuildInConfig();
        if (XView2Manager.mIsXViewEnable && !SwitchQueryFetcher.isXTime()) {
            XView2Manager.getInstance().requestXViewData();
        }
        JDMtaUtils.acceptPrivacyProtocol(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        y();
        A();
        this.f11233g.b();
    }

    @Override // com.jingdong.app.mall.main.d.a
    public void m() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Intent intent;
        com.jingdong.app.mall.home.s.a b2 = com.jingdong.app.mall.home.s.a.b();
        String n1 = JDMobiSec.n1("c39f134878c116364e49e324");
        String n12 = JDMobiSec.n1("c19039545cc3163a");
        b2.m(n1, n12);
        this.statusBarTransparentEnable = true;
        UnDeviceHelper.init();
        setUseBasePV(false);
        setNetworkModel(false);
        this.finishAfterSuperOnCreate = i.g().m();
        super.onCreate(bundle);
        if (this.finishAfterSuperOnCreate) {
            i.g().n();
            Process.killProcess(Process.myPid());
            System.exit(0);
            return;
        }
        if (!isTaskRoot() && (intent = getIntent()) != null) {
            String action = intent.getAction();
            if (intent.hasCategory(JDMobiSec.n1("cf901e5456cb0671514ee33847bf1f9fd7ca439648c36b4a35e329369f3b5636")) && JDMobiSec.n1("cf901e5456cb0671514ee33847bf1f9dd5ca4f9e499f5f2530ec").equals(action)) {
                finish();
                return;
            }
        }
        getIntent().replaceExtras(new Bundle());
        setContentView(R.layout.activity_jd_main);
        this.f11234h = new c(this, null);
        this.f11233g = new com.jingdong.app.mall.main.a(this, new e(this, new com.jingdong.app.mall.main.b(this, this.f11234h)));
        if (JDPrivacyHelper.isAcceptPrivacy(this)) {
            JDMtaUtils.acceptPrivacyProtocol(true);
        }
        this.f11237k = false;
        com.jingdong.app.mall.main.d dVar = new com.jingdong.app.mall.main.d();
        this.f11235i = dVar;
        dVar.b(this, this);
        OpenLinkTimeManager.getInstance().reset();
        com.jingdong.app.mall.home.s.a.b().l(n1, n12);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.jingdong.app.mall.main.c cVar = this.f11233g;
        if (cVar != null) {
            cVar.a();
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f11233g.d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        com.jingdong.app.mall.home.s.a b2 = com.jingdong.app.mall.home.s.a.b();
        String n1 = JDMobiSec.n1("c39f134878c116364e49e324");
        String n12 = JDMobiSec.n1("c19028434ad70f3a");
        b2.m(n1, n12);
        super.onResume();
        B();
        com.jingdong.app.mall.home.s.a.b().l(n1, n12);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        com.jingdong.app.mall.main.c cVar = this.f11233g;
        if (cVar != null) {
            cVar.e();
        }
    }
}
