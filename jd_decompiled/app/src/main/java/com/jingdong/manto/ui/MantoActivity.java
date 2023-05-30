package com.jingdong.manto.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.R;
import com.jingdong.manto.card.CardLaunchCallback;
import com.jingdong.manto.card.CardRequestParameter;
import com.jingdong.manto.g;
import com.jingdong.manto.h;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.m.k0;
import com.jingdong.manto.n.f;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.q.j;
import com.jingdong.manto.q.l;
import com.jingdong.manto.q.n;
import com.jingdong.manto.q.s;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.i;
import com.jingdong.manto.utils.u;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.jingdong.manto.widget.f;
import com.jingdong.manto.widget.input.InputUtil;
import com.jingdong.manto.widget.input.x;
import com.jingdong.manto.widget.input.z.f;
import java.io.InputStream;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoActivity extends MantoBaseActivity implements com.jingdong.manto.e, com.jingdong.manto.m.p0.a {
    private g a;
    Intent b;
    private s d;

    /* renamed from: e */
    FrameLayout f14245e;

    /* renamed from: f */
    private x f14246f;

    /* renamed from: g */
    private int f14247g;

    /* renamed from: h */
    private int f14248h;

    /* renamed from: i */
    private int f14249i;

    /* renamed from: j */
    private boolean f14250j;

    /* renamed from: k */
    private boolean f14251k;

    /* renamed from: l */
    private boolean f14252l;
    private View o;
    private com.jingdong.manto.i.c p;

    /* renamed from: c */
    private final com.jingdong.manto.widget.input.g f14244c = new com.jingdong.manto.widget.input.g(this);

    /* renamed from: m */
    private ComponentCallbacks2 f14253m = new com.jingdong.manto.ui.a(this);

    /* renamed from: n */
    private boolean f14254n = false;
    f.b q = new a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements f.b {
        a() {
            MantoActivity.this = r1;
        }

        @Override // com.jingdong.manto.n.f.b
        public void a() {
            MantoActivity.this.finish();
        }
    }

    /* loaded from: classes16.dex */
    public class b implements Runnable {
        b(String str) {
            MantoActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoActivity.this.f14252l = false;
            Intent intent = MantoActivity.this.getIntent();
            MantoActivity.this.c();
            MantoActivity.this.a(intent);
        }
    }

    /* loaded from: classes16.dex */
    class c implements f.a {
        c() {
            MantoActivity.this = r1;
        }

        @Override // com.jingdong.manto.widget.input.z.f.a
        public final void a() {
            MantoActivity.this.f14244c.a();
        }
    }

    /* loaded from: classes16.dex */
    class d implements n.c0 {
        final /* synthetic */ k0 a;

        d(MantoActivity mantoActivity, k0 k0Var) {
            this.a = k0Var;
        }

        @Override // com.jingdong.manto.q.n.c0
        public void onDestroy() {
            this.a.a();
        }
    }

    /* loaded from: classes16.dex */
    class e implements View.OnTouchListener {
        e(MantoActivity mantoActivity) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    public void a(Intent intent) {
        com.jingdong.manto.i.c cVar;
        com.jingdong.manto.i.c cVar2;
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        try {
            intent.setExtrasClassLoader(com.jingdong.manto.i.c.class.getClassLoader());
            Bundle bundleExtra = intent.getBundleExtra("bundles");
            cVar = (com.jingdong.manto.i.c) bundleExtra.getParcelable("key_manto_init_config");
            com.jingdong.manto.r.d.d = bundleExtra.getLong("launchInfoStartTime", 0L);
            com.jingdong.manto.r.d.f14138i = bundleExtra.getLong("infoCost", 0L);
            com.jingdong.manto.r.d.f14139j = bundleExtra.getLong("loadingStartTime", 0L);
        } catch (Exception unused) {
            cVar = null;
        }
        g gVar = this.a;
        if (gVar == null || cVar == null) {
            return;
        }
        gVar.b(false);
        com.jingdong.manto.f a2 = this.a.a(cVar.a);
        if (a2 == null || (cVar2 = a2.r) == null) {
            this.a.c(null, cVar, null);
        } else {
            boolean f2 = cVar2.f();
            boolean equals = TextUtils.equals("1", cVar.f13082e);
            if (f2 && equals) {
                com.jingdong.manto.i.c cVar3 = a2.r;
                cVar3.f13083f = cVar.f13083f;
                cVar3.f13090m = cVar.f13090m;
                cVar3.f13091n = cVar.f13091n;
                a2.D();
            } else {
                this.a.b();
                this.a.b((com.jingdong.manto.f) null, cVar, (CardLaunchCallback) null);
            }
        }
        this.p = cVar;
    }

    private void a(Configuration configuration, String str) {
        if (configuration != null) {
            this.f14247g = configuration.orientation;
            this.f14248h = configuration.screenWidthDp;
            this.f14249i = configuration.screenHeightDp;
        }
        if (this.a != null) {
            this.f14252l = true;
            MantoThreadUtils.post(new b(str), 0);
        }
    }

    private boolean a(Configuration configuration) {
        if (configuration != null && this.f14247g == configuration.orientation) {
            if (this.f14248h != configuration.screenWidthDp) {
                return true;
            }
            if (Math.abs(this.f14249i - configuration.screenHeightDp) > 10 && this.f14249i != configuration.screenHeightDp) {
                return true;
            }
        }
        return false;
    }

    private void b() {
        try {
            registerComponentCallbacks(this.f14253m);
        } catch (Exception e2) {
            MantoLog.e("MantoActivity", e2);
        }
    }

    public void c() {
        com.jingdong.manto.preload.b.j();
        com.jingdong.manto.f latestRuntime = getLatestRuntime();
        if (latestRuntime != null) {
            latestRuntime.J();
        }
    }

    public final n a() {
        com.jingdong.manto.f g2;
        l lVar;
        j firstPage;
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null || lVar == null || (firstPage = lVar.getFirstPage()) == null) {
            return null;
        }
        return firstPage.i();
    }

    @Override // com.jingdong.manto.MantoCore
    public void addPicInPicPage(int i2, boolean z, boolean z2) {
        com.jingdong.manto.f latestRuntime = getLatestRuntime();
        if (latestRuntime != null) {
            latestRuntime.a(i2, z, z2);
        }
    }

    @Override // com.jingdong.manto.MantoCore
    public boolean convertNativeBuffer(JSONObject jSONObject, Map map, boolean z) {
        com.jingdong.manto.f g2;
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null) {
            return false;
        }
        return u.a(g2.f13015g, jSONObject, map, z);
    }

    @Override // com.jingdong.manto.MantoCore
    public void dispatchEevent(String str, JSONObject jSONObject, int i2) {
        com.jingdong.manto.f g2;
        h hVar;
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null || (hVar = g2.f13015g) == null) {
            return;
        }
        hVar.a(str, jSONObject != null ? jSONObject.toString() : null, i2);
    }

    @Override // com.jingdong.manto.MantoCore
    public void dispatchEeventToPage(String str, JSONObject jSONObject, int[] iArr) {
        com.jingdong.manto.f g2;
        l lVar;
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null) {
            return;
        }
        lVar.a(str, jSONObject != null ? jSONObject.toString() : null, iArr);
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    @SuppressLint({"RestrictedApi"})
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        x xVar = this.f14246f;
        return (xVar == null || !xVar.a()) ? super.dispatchKeyEvent(keyEvent) : this.f14246f.dispatchKeyEvent(keyEvent);
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, android.app.Activity
    public void finish() {
        com.jingdong.manto.m.c1.l.c().b();
        if (Build.VERSION.SDK_INT >= 21) {
            super.finishAndRemoveTask();
        } else {
            super.finish();
        }
        overridePendingTransition(R.anim.manto_slide_in_left, R.anim.manto_slide_out_right);
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, com.jingdong.manto.MantoActivityResult
    public Activity getActivity() {
        return this;
    }

    @Override // com.jingdong.manto.MantoCore
    public MantoActivityResult getActivityResultImpl() {
        return this;
    }

    @Override // com.jingdong.manto.MantoCore
    public Bitmap getBitmap(String str) {
        if (this.a == null) {
            return null;
        }
        return com.jingdong.manto.utils.b.a().a(this.a.g(), str);
    }

    @Override // com.jingdong.manto.e
    public CardRequestParameter getCardRequestParameter() {
        return null;
    }

    @Override // com.jingdong.manto.MantoCore
    public InputStream getFile(String str) {
        g gVar = this.a;
        if (gVar == null) {
            return null;
        }
        return com.jingdong.manto.pkg.b.g.d(gVar.g(), str);
    }

    @Override // com.jingdong.manto.e
    public com.jingdong.manto.f getLatestRuntime() {
        g gVar = this.a;
        if (gVar != null) {
            return gVar.g();
        }
        return null;
    }

    @Override // com.jingdong.manto.e
    public com.jingdong.manto.jsapi.webview.g getMantoWebViewContainer() {
        com.jingdong.manto.f g2;
        l lVar;
        g gVar = this.a;
        if (gVar != null && (g2 = gVar.g()) != null && (lVar = g2.f13014f) != null && lVar.getFirstPage() != null && g2.f13014f.getFirstPage().i() != null) {
            View findViewById = g2.f13014f.getFirstPage().i().l().findViewById(R.id.manto_pageview_html_webview);
            if (findViewById instanceof com.jingdong.manto.jsapi.webview.g) {
                return (com.jingdong.manto.jsapi.webview.g) findViewById;
            }
        }
        return null;
    }

    @Override // com.jingdong.manto.MantoCore
    public final ViewGroup getPageInnerContentView() {
        com.jingdong.manto.f g2;
        l lVar;
        j firstPage;
        n i2;
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null || lVar == null || (firstPage = lVar.getFirstPage()) == null || (i2 = firstPage.i()) == null) {
            return null;
        }
        return (ViewGroup) i2.o();
    }

    @Override // com.jingdong.manto.e
    public void hideLoading() {
        View view = this.o;
        if (view != null) {
            view.setVisibility(8);
            if (ViewGroup.class.isInstance(this.o.getParent())) {
                ((ViewGroup) this.o.getParent()).removeView(this.o);
            }
            this.o = null;
        }
    }

    @Override // com.jingdong.manto.e
    public void hideSplash(s.c cVar) {
        s sVar = this.d;
        if (sVar != null) {
            sVar.a(cVar);
        } else if (cVar != null) {
            cVar.a();
        }
    }

    @Override // com.jingdong.manto.e
    public boolean isSameToHostTask() {
        return this.f14254n;
    }

    @Override // android.app.Activity
    public boolean moveTaskToBack(boolean z) {
        return super.moveTaskToBack(z);
    }

    @Override // com.jingdong.manto.m.p0.a
    public void onAudioInterruptionBegin() {
        com.jingdong.manto.f g2;
        com.jingdong.manto.m.p0.d.a.b();
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null || g2.f13014f == null) {
            return;
        }
        com.jingdong.manto.m.p0.e.b.a(g2.f13015g, false);
        try {
            g2.f13014f.getFirstPage().i().a("onAudioInterruptionBegin", "", (int[]) null);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.m.p0.a
    public void onAudioInterruptionEnd() {
        com.jingdong.manto.f g2;
        com.jingdong.manto.m.p0.d.a.c();
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null || g2.f13014f == null) {
            return;
        }
        com.jingdong.manto.m.p0.e.b.b(g2.f13015g, false);
        try {
            g2.f13014f.getFirstPage().i().a("onAudioInterruptionEnd", "", (int[]) null);
        } catch (Throwable unused) {
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        g gVar = this.a;
        if (gVar == null) {
            super.onBackPressed();
            return;
        }
        com.jingdong.manto.f g2 = gVar.g();
        if (g2 == null) {
            super.onBackPressed();
            return;
        }
        l lVar = g2.f13014f;
        if (lVar == null) {
            g2.g();
            super.onBackPressed();
        } else if (lVar.getFirstPage() == null || lVar.getFirstPage().i() == null || !lVar.getFirstPage().i().I()) {
            lVar.d();
        }
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration == null || this.f14250j || this.f14251k || !a(configuration)) {
            return;
        }
        a(configuration, "onConfigurationChanged");
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        com.jingdong.manto.i.c cVar;
        super.onCreate(bundle);
        this.f14254n = TextUtils.equals(MantoUtils.getActivityTaskAffinity(getComponentName()), MantoProcessUtil.a);
        int i2 = 0;
        MantoStatusBarUtil.setStatusBarColor(this, 0, true);
        this.f14244c.a.getWindow().setSoftInputMode(16);
        FrameLayout frameLayout = new FrameLayout(this);
        this.f14245e = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setContentView(frameLayout);
        g gVar = new g();
        this.a = gVar;
        gVar.a(this, this.q, frameLayout);
        Intent intent = getIntent();
        this.b = intent;
        if (intent != null) {
            setIntent(intent);
            if (this.b.getExtras() == null) {
                finish();
                return;
            }
            try {
                this.b.setExtrasClassLoader(com.jingdong.manto.i.c.class.getClassLoader());
                Bundle bundleExtra = this.b.getBundleExtra("bundles");
                cVar = (com.jingdong.manto.i.c) bundleExtra.getParcelable("key_manto_init_config");
                i2 = bundleExtra.getInt("darkMode", 0);
                com.jingdong.manto.r.d.d = bundleExtra.getLong("launchInfoStartTime", 0L);
                com.jingdong.manto.r.d.f14138i = bundleExtra.getLong("infoCost", 0L);
                com.jingdong.manto.r.d.f14139j = bundleExtra.getLong("loadingStartTime", 0L);
                com.jingdong.manto.k.a.b().a(i2);
            } catch (Exception unused) {
                cVar = null;
            }
            if (cVar == null) {
                finish();
                return;
            }
            if (bundle == null || !bundle.getBoolean("config_saved")) {
                showSplashView(cVar.b, TextUtils.isEmpty(cVar.r) ? cVar.f13081c : cVar.r, i2);
                this.p = cVar;
            } else {
                this.a.b(true);
                com.jingdong.manto.i.c cVar2 = (com.jingdong.manto.i.c) bundle.getParcelable("last_launch_conf");
                if (cVar2 != null) {
                    cVar = cVar2;
                }
            }
            this.a.c(null, cVar, null);
            this.p = cVar;
            this.b = null;
        }
        Activity activity = this.f14244c.a;
        if (activity != null && activity.getWindow() != null && frameLayout.getParent() != null && (frameLayout.getParent() instanceof ViewGroup)) {
            this.f14244c.b = true;
            ViewGroup viewGroup = (ViewGroup) frameLayout.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(frameLayout);
                x xVar = new x(this, frameLayout);
                this.f14246f = xVar;
                viewGroup.addView(xVar, new ViewGroup.LayoutParams(-1, -1));
                if (Build.VERSION.SDK_INT < 20) {
                    InputUtil.resetPadding(this.f14244c.a, viewGroup);
                    this.f14244c.a(this.f14246f);
                    if (com.jingdong.manto.widget.input.g.a(this.f14244c.a)) {
                        com.jingdong.manto.widget.input.z.f.a(viewGroup, new c());
                    }
                }
            }
        }
        com.jingdong.manto.widget.f.a(this).a((f.d) null);
        Configuration configuration = getResources().getConfiguration();
        if (configuration != null) {
            this.f14247g = configuration.orientation;
            this.f14249i = configuration.screenHeightDp;
            this.f14248h = configuration.screenWidthDp;
        }
        b();
        com.jingdong.manto.m.p0.b.a().b();
        com.jingdong.manto.m.p0.b.a().a(this);
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c();
        g gVar = this.a;
        if (gVar != null) {
            gVar.a();
        }
        com.jingdong.manto.m.p0.b.a().g();
        try {
            unregisterComponentCallbacks(this.f14253m);
        } catch (Exception e2) {
            MantoLog.e("MantoActivity", e2);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        this.f14250j = z;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        this.f14250j = z;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (this.f14252l) {
            this.f14252l = false;
            return;
        }
        Configuration configuration = getResources().getConfiguration();
        if (a(configuration)) {
            a(configuration, "onNewIntent");
            return;
        }
        a(intent);
        if (intent == null || !intent.getBooleanExtra("key_manto_bring_ui_to_front", false)) {
            return;
        }
        g gVar = this.a;
        if (gVar == null || gVar.f13027g.size() == 0) {
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f14252l = false;
        g gVar = this.a;
        if (gVar == null) {
            return;
        }
        com.jingdong.manto.f g2 = gVar.g();
        if (g2 != null) {
            g2.F();
        }
        com.jingdong.manto.r.d.a(this.p);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        super.onPictureInPictureModeChanged(z);
        this.f14251k = z;
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        super.onPictureInPictureModeChanged(z, configuration);
        this.f14251k = z;
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        com.jingdong.manto.f g2;
        super.onRequestPermissionsResult(i2, strArr, iArr);
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null) {
            return;
        }
        i.a(g2.f13017i, i2, strArr, iArr);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        com.jingdong.manto.f g2;
        super.onResume();
        this.f14252l = false;
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null) {
            return;
        }
        g2.G();
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null || getIntent() == null || getIntent().getBundleExtra("bundles") == null) {
            return;
        }
        getIntent().setExtrasClassLoader(com.jingdong.manto.i.c.class.getClassLoader());
        com.jingdong.manto.i.c cVar = (com.jingdong.manto.i.c) getIntent().getBundleExtra("bundles").getParcelable("key_manto_init_config");
        if (cVar != null) {
            bundle.putParcelable("last_launch_conf", cVar);
        }
        bundle.putBoolean("config_saved", true);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        int i2;
        super.onWindowAttributesChanged(layoutParams);
        com.jingdong.manto.widget.input.g gVar = this.f14244c;
        if (layoutParams == null || (i2 = layoutParams.flags) == gVar.f14457c) {
            return;
        }
        gVar.f14457c = i2;
        gVar.a();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // com.jingdong.manto.e
    public int registMantoWebviewInterface(IMantoWebViewJS iMantoWebViewJS, String str) {
        com.jingdong.manto.f g2;
        l lVar;
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null || lVar.getFirstPage() == null || g2.f13014f.getFirstPage().i() == null) {
            return 0;
        }
        n i2 = g2.f13014f.getFirstPage().i();
        k0 k0Var = new k0(this, i2, iMantoWebViewJS);
        g2.f13014f.getFirstPage().i().a(new d(this, k0Var));
        iMantoWebViewJS.addJavascriptInterface(k0Var, str);
        return i2.hashCode();
    }

    @Override // com.jingdong.manto.MantoCore
    public void removePicInPicPage(int i2) {
        com.jingdong.manto.f latestRuntime = getLatestRuntime();
        if (latestRuntime != null) {
            latestRuntime.a(i2);
        }
    }

    @Override // com.jingdong.manto.e
    public void removeSplashView() {
        s sVar = this.d;
        if (sVar == null || sVar.getParent() == null) {
            return;
        }
        ((ViewGroup) this.d.getParent()).removeView(this.d);
        this.d = null;
    }

    @Override // com.jingdong.manto.MantoCore
    public void restoreWebViewFocus(boolean z) {
        g gVar = this.a;
        if (gVar == null) {
            return;
        }
        gVar.a(z);
    }

    @Override // com.jingdong.manto.MantoCore
    public void setAnchorViewVisible(boolean z, Bundle bundle) {
        n a2 = a();
        if (a2 != null) {
            a2.a(z, bundle);
        }
    }

    @Override // com.jingdong.manto.e
    public void setGestureMode(String str) {
    }

    @Override // com.jingdong.manto.e
    public void setTaskDescription() {
        g gVar;
        com.jingdong.manto.f g2;
        PkgDetailEntity pkgDetailEntity;
        try {
            if (com.jingdong.manto.b.o() || isSameToHostTask() || (gVar = this.a) == null || (g2 = gVar.g()) == null || (pkgDetailEntity = g2.f13016h) == null) {
                return;
            }
            String str = pkgDetailEntity.name;
            if (Build.VERSION.SDK_INT >= 21) {
                setTaskDescription(new ActivityManager.TaskDescription(str));
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.e
    public void setTitle(String str, int i2) {
        com.jingdong.manto.f g2;
        l lVar;
        n i3;
        g gVar = this.a;
        if (gVar == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null || lVar.getFirstPage() == null || g2.f13014f.getFirstPage().i() == null || (i3 = g2.f13014f.getFirstPage().i()) == null || i3.hashCode() != i2) {
            return;
        }
        i3.h(str);
    }

    @Override // com.jingdong.manto.e
    public void showLoading() {
        if (this.o != null) {
            hideLoading();
        }
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(com.jingdong.manto.c.a()).inflate(R.layout.manto_toast, (ViewGroup) null);
        this.o = linearLayout;
        linearLayout.findViewById(R.id.ll_loading).setVisibility(0);
        this.o.findViewById(R.id.ll_success).setVisibility(8);
        ((TextView) this.o.findViewById(R.id.toast_loading_title)).setText("\u52a0\u8f7d\u4e2d");
        this.o.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.o.findViewById(R.id.toast_root).getBackground().setColorFilter(Color.parseColor("#88000000"), PorterDuff.Mode.SRC_ATOP);
        this.o.setOnTouchListener(new e(this));
        ViewGroup pageInnerContentView = getPageInnerContentView();
        if (pageInnerContentView != null) {
            pageInnerContentView.addView(this.o);
        }
    }

    @Override // com.jingdong.manto.e
    public void showSplashView(String str, String str2, int i2) {
        if (this.d == null) {
            s sVar = new s(this, i2);
            this.d = sVar;
            this.f14245e.addView(sVar);
            this.d.setLoadingTitle(str);
            if (TextUtils.isEmpty(str2)) {
                this.d.setMantoIcon(R.drawable.manto_icon_default);
            } else {
                this.d.setMantoIcon(str2);
            }
            this.d.a();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.startActivityForResult(intent, i2, bundle);
        } else {
            super.startActivityForResult(intent, i2);
        }
    }
}
