package com.jingdong.manto.card;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.R;
import com.jingdong.manto.f;
import com.jingdong.manto.g;
import com.jingdong.manto.h;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.launch.UIConfig;
import com.jingdong.manto.m.k0;
import com.jingdong.manto.n.f;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.q.j;
import com.jingdong.manto.q.l;
import com.jingdong.manto.q.n;
import com.jingdong.manto.q.s;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.utils.u;
import com.jingdong.manto.widget.input.x;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class MantoCardView extends FrameLayout implements com.jingdong.manto.e, MantoActivityResult, com.jingdong.manto.m.p0.a {
    private static final String TAG = "CardView";
    private CardMode cardMode;
    private CardRequestParameter cardRequestParameter;
    private ComponentCallbacks2 componentCallbacks2;
    private Path cornerPath;
    private RectF cornerRectF;
    private volatile boolean destroyed;
    private Reference<Activity> hostActivityRef;
    private com.jingdong.manto.i.c lastInitConfig;
    private float leftBottomRadius;
    private float leftTopRadius;
    private View loadingToastView;
    private MantoActivityResult.ResultCallback preResultCallback;
    private float[] radii;
    public MantoActivityResult.ResultCallback resultCallback;
    private float rightBottomRadius;
    private float rightTopRadius;
    private FrameLayout rootLayout;
    private g runtimeContainer;
    private s splashView;
    private UIConfig uiConfig;
    private x uiRootFrameLayout;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements MantoPreLaunchProcess.b {
        final /* synthetic */ CardLaunchCallback a;

        /* renamed from: com.jingdong.manto.card.MantoCardView$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0506a implements Runnable {
            final /* synthetic */ MantoPreLaunchProcess.LaunchError a;

            RunnableC0506a(MantoPreLaunchProcess.LaunchError launchError) {
                this.a = launchError;
            }

            @Override // java.lang.Runnable
            public void run() {
                CardLaunchCallback cardLaunchCallback = a.this.a;
                if (cardLaunchCallback != null) {
                    cardLaunchCallback.onLaunchError(this.a);
                }
            }
        }

        a(CardLaunchCallback cardLaunchCallback) {
            this.a = cardLaunchCallback;
        }

        @Override // com.jingdong.manto.launch.MantoPreLaunchProcess.b
        public void a(com.jingdong.manto.i.c cVar) {
            CardLaunchCallback cardLaunchCallback = this.a;
            if (cardLaunchCallback != null) {
                cardLaunchCallback.onBeginLaunch();
            }
            MantoCardView.this.launch(cVar, this.a);
        }

        @Override // com.jingdong.manto.launch.MantoPreLaunchProcess.b
        public void onLaunchError(MantoPreLaunchProcess.LaunchError launchError) {
            MantoThreadUtils.runOnUIThreadImmediately(new RunnableC0506a(launchError));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ com.jingdong.manto.i.c f13005c;
        final /* synthetic */ MantoPreLaunchProcess.b d;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                bVar.d.a(bVar.f13005c);
            }
        }

        /* renamed from: com.jingdong.manto.card.MantoCardView$b$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0507b implements PkgManager.k {
            final /* synthetic */ long a;
            final /* synthetic */ boolean b;

            /* renamed from: com.jingdong.manto.card.MantoCardView$b$b$a */
            /* loaded from: classes15.dex */
            class a implements Runnable {
                a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    b bVar = b.this;
                    bVar.d.a(bVar.f13005c);
                }
            }

            /* renamed from: com.jingdong.manto.card.MantoCardView$b$b$b  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            class RunnableC0508b implements Runnable {
                final /* synthetic */ MantoPreLaunchProcess.LaunchError a;

                RunnableC0508b(MantoPreLaunchProcess.LaunchError launchError) {
                    this.a = launchError;
                }

                @Override // java.lang.Runnable
                public void run() {
                    b.this.d.onLaunchError(this.a);
                }
            }

            /* renamed from: com.jingdong.manto.card.MantoCardView$b$b$c */
            /* loaded from: classes15.dex */
            class c implements Runnable {
                c() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    b bVar = b.this;
                    bVar.d.a(bVar.f13005c);
                }
            }

            /* renamed from: com.jingdong.manto.card.MantoCardView$b$b$d */
            /* loaded from: classes15.dex */
            class d implements Runnable {
                d() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    MantoPreLaunchProcess.LaunchError launchError = new MantoPreLaunchProcess.LaunchError();
                    launchError.errorCode = PkgDetailEntity.NO_INFO;
                    launchError.msg = "\u6682\u65f6\u65e0\u6cd5\u83b7\u53d6\u5c0f\u7a0b\u5e8f\u4fe1\u606f";
                    launchError.word = "\u8bf7\u67e5\u770b\u7f51\u7edc\u94fe\u63a5\u60c5\u51b5\uff0c\u5e76\u4f7f\u7528\u7cfb\u7edf\u9ed8\u8ba4\u65f6\u95f4\u540e\u518d\u6b21\u5c1d\u8bd5";
                    launchError.title = "\u8fd4\u56de";
                    b.this.d.onLaunchError(launchError);
                }
            }

            C0507b(long j2, boolean z) {
                this.a = j2;
                this.b = z;
            }

            @Override // com.jingdong.manto.pkg.PkgManager.k
            public void a(PkgDetailEntity pkgDetailEntity) {
                com.jingdong.manto.r.d.f14138i = System.currentTimeMillis() - this.a;
                if (this.b) {
                    return;
                }
                b.this.f13005c.b(pkgDetailEntity);
                if (b.this.d != null) {
                    MantoThreadUtils.runOnUIThread(new a());
                    com.jingdong.manto.b.k().a(new PkgCollectEntity(pkgDetailEntity.appId, pkgDetailEntity.type, pkgDetailEntity.name, pkgDetailEntity.logo, pkgDetailEntity.favorite, System.currentTimeMillis()));
                }
            }

            @Override // com.jingdong.manto.pkg.PkgManager.k
            public void onError(Throwable th, JSONObject jSONObject) {
                JSONObject optJSONObject;
                int optInt;
                if (jSONObject != null && (optJSONObject = jSONObject.optJSONObject("error")) != null && (optInt = optJSONObject.optInt("code")) != 0) {
                    com.jingdong.manto.pkg.a k2 = com.jingdong.manto.b.k();
                    b bVar = b.this;
                    k2.b(bVar.a, String.valueOf(bVar.b));
                    if (this.b) {
                        return;
                    }
                    MantoPreLaunchProcess.LaunchError launchError = new MantoPreLaunchProcess.LaunchError();
                    launchError.errorCode = optInt;
                    launchError.title = optJSONObject.optString("title");
                    launchError.msg = optJSONObject.optString("msg");
                    launchError.word = optJSONObject.optString("word");
                    if (b.this.d != null) {
                        MantoThreadUtils.runOnUIThread(new RunnableC0508b(launchError));
                    }
                } else if (this.b) {
                } else {
                    com.jingdong.manto.pkg.a k3 = com.jingdong.manto.b.k();
                    b bVar2 = b.this;
                    PkgDetailEntity c2 = k3.c(bVar2.a, String.valueOf(bVar2.b));
                    if (c2 == null) {
                        if (b.this.d != null) {
                            MantoThreadUtils.runOnUIThread(new d());
                            return;
                        }
                        return;
                    }
                    b.this.f13005c.b(c2);
                    if (b.this.d != null) {
                        MantoThreadUtils.runOnUIThread(new c());
                        com.jingdong.manto.b.k().a(new PkgCollectEntity(c2.appId, c2.type, c2.name, c2.logo, c2.favorite, System.currentTimeMillis()));
                    }
                }
            }
        }

        b(MantoCardView mantoCardView, String str, String str2, com.jingdong.manto.i.c cVar, MantoPreLaunchProcess.b bVar) {
            this.a = str;
            this.b = str2;
            this.f13005c = cVar;
            this.d = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            PkgDetailEntity c2;
            long currentTimeMillis = System.currentTimeMillis();
            if (MantoCardHelper.useCachedCardJdaInfo() && (c2 = com.jingdong.manto.b.k().c(this.a, String.valueOf(this.b))) != null && TextUtils.isEmpty(c2.templateId)) {
                z = true;
                com.jingdong.manto.r.d.f14138i = System.currentTimeMillis() - currentTimeMillis;
                this.f13005c.b(c2);
                if (this.d != null) {
                    MantoThreadUtils.runOnUIThread(new a());
                    com.jingdong.manto.b.k().a(new PkgCollectEntity(c2.appId, c2.type, c2.name, c2.logo, c2.favorite, System.currentTimeMillis()));
                }
            } else {
                z = false;
            }
            PkgManager.requestPkgDetail(this.a, String.valueOf(this.b), new C0507b(currentTimeMillis, z));
        }
    }

    /* loaded from: classes15.dex */
    class c implements View.OnTouchListener {
        c(MantoCardView mantoCardView) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* loaded from: classes15.dex */
    class d implements n.c0 {
        final /* synthetic */ k0 a;

        d(MantoCardView mantoCardView, k0 k0Var) {
            this.a = k0Var;
        }

        @Override // com.jingdong.manto.q.n.c0
        public void onDestroy() {
            this.a.a();
        }
    }

    /* loaded from: classes15.dex */
    class e implements Runnable {
        final /* synthetic */ MotionEvent a;

        e(MotionEvent motionEvent) {
            this.a = motionEvent;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.getAction() == 0) {
                try {
                    HashMap hashMap = new HashMap();
                    JSONObject makeClickETModel = MantoCardHelper.makeClickETModel(MantoCardView.this.cardRequestParameter);
                    if (makeClickETModel == null) {
                        return;
                    }
                    hashMap.put(EtModelMaker.KEY_ET_MODEL, makeClickETModel.toString());
                    MantoTrack.sendCommonDataWithExt(com.jingdong.a.g(), "\u5c0f\u90e8\u4ef6\u6a21\u5757\u7ec4\u4ef6\u70b9\u51fb", MantoCardHelper.CARD_CLICK_CLICK_CARD, "", "", "", "", "J_SmartCard", hashMap, "99008948", "1");
                } catch (Throwable unused) {
                }
            }
        }
    }

    public MantoCardView(@NonNull Context context) {
        super(com.jingdong.a.g());
        this.resultCallback = null;
        this.preResultCallback = null;
        this.cardMode = CardMode.DEFAULT;
        initView(com.jingdong.a.g(), null);
    }

    public MantoCardView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(com.jingdong.a.g(), attributeSet);
        this.resultCallback = null;
        this.preResultCallback = null;
        this.cardMode = CardMode.DEFAULT;
        initView(com.jingdong.a.g(), attributeSet);
    }

    public MantoCardView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(com.jingdong.a.g(), attributeSet, i2);
        this.resultCallback = null;
        this.preResultCallback = null;
        this.cardMode = CardMode.DEFAULT;
        initView(com.jingdong.a.g(), attributeSet);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.cornerPath = new Path();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MantoCardView);
        if (obtainStyledAttributes != null) {
            this.leftTopRadius = obtainStyledAttributes.getFloat(R.styleable.MantoCardView_leftTopRadius, 0.0f);
            this.leftBottomRadius = obtainStyledAttributes.getFloat(R.styleable.MantoCardView_leftBottomRadius, 0.0f);
            this.rightTopRadius = obtainStyledAttributes.getFloat(R.styleable.MantoCardView_rightTopRadius, 0.0f);
            float f2 = obtainStyledAttributes.getFloat(R.styleable.MantoCardView_rightBottomRadius, 0.0f);
            this.rightBottomRadius = f2;
            float f3 = this.leftTopRadius;
            float f4 = this.rightTopRadius;
            float f5 = this.leftBottomRadius;
            this.radii = new float[]{f3, f3, f4, f4, f2, f2, f5, f5};
            obtainStyledAttributes.recycle();
        }
        FrameLayout frameLayout = new FrameLayout(context);
        this.rootLayout = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        x xVar = new x(com.jingdong.a.g(), this.rootLayout);
        this.uiRootFrameLayout = xVar;
        addView(xVar, new ViewGroup.LayoutParams(-1, -1));
        this.runtimeContainer = new g();
    }

    private boolean isShowSplash() {
        UIConfig uIConfig = this.uiConfig;
        return uIConfig == null || !uIConfig.isHideSplash();
    }

    private void registerComponentCallback() {
        try {
            this.componentCallbacks2 = new com.jingdong.manto.ui.a(this);
            Activity activity = this.hostActivityRef.get();
            if (activity != null) {
                activity.registerComponentCallbacks(this.componentCallbacks2);
            }
        } catch (Exception e2) {
            MantoLog.e(TAG, e2);
        }
    }

    private void resetPath() {
        Path path = this.cornerPath;
        if (path == null || this.cornerRectF == null) {
            return;
        }
        path.reset();
        float[] fArr = this.radii;
        if (fArr != null) {
            this.cornerPath.addRoundRect(this.cornerRectF, fArr, Path.Direction.CW);
        }
        this.cornerPath.close();
    }

    @Override // com.jingdong.manto.MantoCore
    public void addPicInPicPage(int i2, boolean z, boolean z2) {
        f latestRuntime = getLatestRuntime();
        if (latestRuntime != null) {
            latestRuntime.a(i2, z, z2);
        }
    }

    @Override // com.jingdong.manto.MantoCore
    public boolean convertNativeBuffer(JSONObject jSONObject, Map map, boolean z) {
        g gVar;
        if (this.destroyed || (gVar = this.runtimeContainer) == null) {
            return false;
        }
        return u.a(gVar.g().f13015g, jSONObject, map, z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(this.cornerPath);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // com.jingdong.manto.MantoCore
    public void dispatchEevent(String str, JSONObject jSONObject, int i2) {
        g gVar;
        f g2;
        h hVar;
        if (this.destroyed || (gVar = this.runtimeContainer) == null || (g2 = gVar.g()) == null || (hVar = g2.f13015g) == null) {
            return;
        }
        hVar.a(str, jSONObject != null ? jSONObject.toString() : null, i2);
    }

    @Override // com.jingdong.manto.MantoCore
    public void dispatchEeventToPage(String str, JSONObject jSONObject, int[] iArr) {
        g gVar;
        f g2;
        l lVar;
        if (this.destroyed || (gVar = this.runtimeContainer) == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null) {
            return;
        }
        lVar.a(str, jSONObject != null ? jSONObject.toString() : null, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View, com.jingdong.manto.e
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!isLightMode()) {
            com.jingdong.manto.b.d().networkIO().execute(new e(motionEvent));
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(this.cornerPath);
        super.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // com.jingdong.manto.MantoCore, com.jingdong.manto.MantoActivityResult
    public Activity getActivity() {
        Reference<Activity> reference = this.hostActivityRef;
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    @Override // com.jingdong.manto.MantoCore
    public MantoActivityResult getActivityResultImpl() {
        return this;
    }

    @Override // com.jingdong.manto.MantoCore
    public Bitmap getBitmap(String str) {
        if (this.destroyed || this.runtimeContainer == null) {
            return null;
        }
        return com.jingdong.manto.utils.b.a().a(this.runtimeContainer.g(), str);
    }

    public CardMode getCardMode() {
        return this.cardMode;
    }

    @Override // com.jingdong.manto.e
    public CardRequestParameter getCardRequestParameter() {
        return this.cardRequestParameter;
    }

    @Override // com.jingdong.manto.MantoCore
    public InputStream getFile(String str) {
        g gVar;
        if (this.destroyed || (gVar = this.runtimeContainer) == null) {
            return null;
        }
        return com.jingdong.manto.pkg.b.g.d(gVar.g(), str);
    }

    @Override // com.jingdong.manto.e
    public f getLatestRuntime() {
        g gVar;
        if (this.destroyed || (gVar = this.runtimeContainer) == null) {
            return null;
        }
        return gVar.g();
    }

    @Override // com.jingdong.manto.e
    public com.jingdong.manto.jsapi.webview.g getMantoWebViewContainer() {
        g gVar;
        f g2;
        l lVar;
        if (!this.destroyed && (gVar = this.runtimeContainer) != null && (g2 = gVar.g()) != null && (lVar = g2.f13014f) != null && lVar.getFirstPage() != null && g2.f13014f.getFirstPage().i() != null) {
            View findViewById = g2.f13014f.getFirstPage().i().l().findViewById(R.id.manto_pageview_html_webview);
            if (findViewById instanceof com.jingdong.manto.jsapi.webview.g) {
                return (com.jingdong.manto.jsapi.webview.g) findViewById;
            }
        }
        return null;
    }

    @Override // com.jingdong.manto.MantoCore
    public ViewGroup getPageInnerContentView() {
        g gVar;
        f g2;
        l lVar;
        j firstPage;
        n i2;
        if (this.destroyed || (gVar = this.runtimeContainer) == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null || lVar == null || (firstPage = lVar.getFirstPage()) == null || (i2 = firstPage.i()) == null) {
            return null;
        }
        return (ViewGroup) i2.o();
    }

    public final n getPageView() {
        g gVar;
        f g2;
        l lVar;
        j firstPage;
        if (this.destroyed || (gVar = this.runtimeContainer) == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null || lVar == null || (firstPage = lVar.getFirstPage()) == null) {
            return null;
        }
        return firstPage.i();
    }

    @Override // com.jingdong.manto.MantoActivityResult
    public MantoActivityResult.ResultCallback getResultCallback() {
        return this.resultCallback;
    }

    public UIConfig getUiConfig() {
        return this.uiConfig;
    }

    @Override // com.jingdong.manto.e
    public void hideLoading() {
        View view = this.loadingToastView;
        if (view != null) {
            view.setVisibility(8);
            if (ViewGroup.class.isInstance(this.loadingToastView.getParent())) {
                ((ViewGroup) this.loadingToastView.getParent()).removeView(this.loadingToastView);
            }
            this.loadingToastView = null;
        }
    }

    @Override // com.jingdong.manto.e
    public void hideSplash(s.c cVar) {
        s sVar = this.splashView;
        if (sVar != null) {
            sVar.a(cVar);
        } else if (cVar != null) {
            cVar.a();
        }
    }

    @Override // com.jingdong.manto.MantoCore
    public boolean isFinishing() {
        Activity activity;
        Reference<Activity> reference = this.hostActivityRef;
        if (reference == null || (activity = reference.get()) == null) {
            return true;
        }
        return activity.isFinishing();
    }

    public boolean isLightMode() {
        com.jingdong.manto.i.c cVar = this.lastInitConfig;
        if (cVar != null) {
            return TextUtils.equals(cVar.q, "1") || TextUtils.equals(this.lastInitConfig.q, "2") || TextUtils.equals(this.lastInitConfig.q, "3");
        }
        return false;
    }

    @Override // com.jingdong.manto.e
    public boolean isSameToHostTask() {
        return false;
    }

    void launch(com.jingdong.manto.i.c cVar, CardLaunchCallback cardLaunchCallback) {
        if (this.destroyed) {
            return;
        }
        registerComponentCallback();
        g gVar = this.runtimeContainer;
        if (gVar != null) {
            gVar.c(null, cVar, cardLaunchCallback);
        }
    }

    public void launchMiniProgram(LaunchParam launchParam) {
        launchMiniProgram(launchParam, null);
    }

    public void launchMiniProgram(LaunchParam launchParam, CardLaunchCallback cardLaunchCallback) {
        if (cardLaunchCallback != null) {
            cardLaunchCallback.onStart();
        }
        this.uiConfig = launchParam.uiConfig;
        g gVar = this.runtimeContainer;
        if (gVar != null) {
            gVar.a(this, (f.b) null, this.rootLayout);
        }
        String str = launchParam.appId;
        String str2 = launchParam.debugType;
        String str3 = launchParam.launchPath;
        String str4 = launchParam.sourcePath;
        String str5 = launchParam.sourceSubPkgJson;
        String str6 = launchParam.extrasJson;
        String str7 = launchParam.scene;
        String str8 = launchParam.pageAlias;
        String str9 = launchParam.mpMode;
        com.jingdong.manto.i.c cVar = new com.jingdong.manto.i.c();
        cVar.a = str;
        cVar.f13083f = str3;
        cVar.f13088k = str4;
        cVar.f13089l = str5;
        cVar.f13082e = str2;
        cVar.f13090m = str6;
        cVar.f13091n = str7;
        cVar.o = str8;
        cVar.f13086i = this.uiConfig;
        cVar.q = str9;
        this.lastInitConfig = cVar;
        a aVar = new a(cardLaunchCallback);
        if (!TextUtils.isEmpty(str4)) {
            aVar.a(cVar);
            return;
        }
        if (TextUtils.equals(str2, "13")) {
            try {
                JSONObject jSONObject = new JSONObject(com.jingdong.manto.b.f().getSharedPreferences("mini-dev-mode", 0).getString("key", ""));
                cVar.a = jSONObject.optString("appId");
                cVar.b = jSONObject.optString("name");
                cVar.f13081c = jSONObject.optString("logoUrl");
                cVar.f13082e = "13";
                PkgDetailEntity pkgDetailEntity = new PkgDetailEntity();
                pkgDetailEntity.appId = cVar.a;
                pkgDetailEntity.name = cVar.b;
                pkgDetailEntity.logo = cVar.f13081c;
                pkgDetailEntity.type = "13";
                pkgDetailEntity.build = jSONObject.optString(HybridSDK.APP_VERSION_CODE, "1");
                pkgDetailEntity.description = jSONObject.optString("description", "");
                pkgDetailEntity.pkgUrl = jSONObject.getString("pkgUrl");
                pkgDetailEntity.versionName = jSONObject.optString("version", "V1.0");
                cVar.b(pkgDetailEntity);
                aVar.a(cVar);
                return;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        com.jingdong.manto.b.d().networkIO().execute(new b(this, str, str2, cVar, aVar));
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        MantoActivityResult.ResultCallback resultCallback = this.resultCallback;
        if (resultCallback != null) {
            resultCallback.onActivityResult(i2, i3, intent);
            this.resultCallback = null;
        }
    }

    @Override // com.jingdong.manto.m.p0.a
    public void onAudioInterruptionBegin() {
        com.jingdong.manto.f g2;
        if (this.destroyed) {
            return;
        }
        com.jingdong.manto.m.p0.d.a.b();
        g gVar = this.runtimeContainer;
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
        if (this.destroyed) {
            return;
        }
        com.jingdong.manto.m.p0.d.a.c();
        g gVar = this.runtimeContainer;
        if (gVar == null || (g2 = gVar.g()) == null || g2.f13014f == null) {
            return;
        }
        com.jingdong.manto.m.p0.e.b.b(g2.f13015g, false);
        try {
            g2.f13014f.getFirstPage().i().a("onAudioInterruptionEnd", "", (int[]) null);
        } catch (Throwable unused) {
        }
    }

    public void onBackPressed() {
        com.jingdong.manto.f g2;
        g gVar = this.runtimeContainer;
        if (gVar == null || (g2 = gVar.g()) == null) {
            return;
        }
        l lVar = g2.f13014f;
        if (lVar == null) {
            g2.g();
        } else if (lVar.getFirstPage() == null || lVar.getFirstPage().i() == null || !lVar.getFirstPage().i().I()) {
            lVar.d();
        }
    }

    public void onDestroy() {
        if (this.destroyed) {
            return;
        }
        this.destroyed = true;
        g gVar = this.runtimeContainer;
        if (gVar != null) {
            gVar.a();
        }
        this.runtimeContainer = null;
        com.jingdong.manto.m.p0.b.a().g();
        try {
            Activity activity = this.hostActivityRef.get();
            if (activity != null) {
                activity.unregisterComponentCallbacks(this.componentCallbacks2);
            }
        } catch (Exception e2) {
            MantoLog.e(TAG, e2);
        }
    }

    public void onPause() {
        g gVar;
        if (this.destroyed || (gVar = this.runtimeContainer) == null) {
            return;
        }
        com.jingdong.manto.f g2 = gVar.g();
        if (g2 != null) {
            g2.F();
        }
        com.jingdong.manto.r.d.a(this.lastInitConfig);
    }

    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        Activity activity;
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission == null || (activity = this.hostActivityRef.get()) == null) {
            return;
        }
        iPermission.onRequestPermissionsResult(activity, i2, strArr, iArr);
    }

    public void onResume() {
        g gVar;
        com.jingdong.manto.f g2;
        if (this.destroyed || (gVar = this.runtimeContainer) == null || (g2 = gVar.g()) == null) {
            return;
        }
        g2.G();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.cornerRectF = new RectF(0.0f, 0.0f, i2, i3);
        resetPath();
    }

    @Override // com.jingdong.manto.e
    public int registMantoWebviewInterface(IMantoWebViewJS iMantoWebViewJS, String str) {
        g gVar;
        com.jingdong.manto.f g2;
        l lVar;
        if (this.destroyed || (gVar = this.runtimeContainer) == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null || lVar.getFirstPage() == null || g2.f13014f.getFirstPage().i() == null) {
            return 0;
        }
        n i2 = g2.f13014f.getFirstPage().i();
        k0 k0Var = new k0(com.jingdong.a.g(), i2, iMantoWebViewJS);
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
        s sVar = this.splashView;
        if (sVar == null || sVar.getParent() == null) {
            return;
        }
        ((ViewGroup) this.splashView.getParent()).removeView(this.splashView);
        this.splashView = null;
    }

    @Override // com.jingdong.manto.MantoActivityResult
    public void restoreResultCallback() {
        this.resultCallback = this.preResultCallback;
        this.preResultCallback = null;
    }

    @Override // com.jingdong.manto.MantoCore
    public void restoreWebViewFocus(boolean z) {
        g gVar = this.runtimeContainer;
        if (gVar == null) {
            return;
        }
        gVar.a(z);
    }

    @Override // com.jingdong.manto.MantoCore
    public void setAnchorViewVisible(boolean z, Bundle bundle) {
        n pageView = getPageView();
        if (pageView != null) {
            pageView.a(z, bundle);
        }
    }

    public void setCardMode(CardMode cardMode) {
        this.cardMode = cardMode;
    }

    public void setCardRequestParameter(CardRequestParameter cardRequestParameter) {
        this.cardRequestParameter = cardRequestParameter;
    }

    public void setCornerRadius(float f2, float f3, float f4, float f5) {
        this.leftTopRadius = f2;
        this.leftBottomRadius = f3;
        this.rightTopRadius = f4;
        this.rightBottomRadius = f5;
        this.radii = new float[]{f2, f2, f4, f4, f5, f5, f3, f3};
        if (this.cornerRectF != null) {
            resetPath();
        }
    }

    @Override // com.jingdong.manto.e
    public void setGestureMode(String str) {
        g gVar;
        com.jingdong.manto.f g2;
        if (this.destroyed || (gVar = this.runtimeContainer) == null || (g2 = gVar.g()) == null) {
            return;
        }
        g2.c(str);
    }

    public void setHostActivity(Activity activity) {
        this.hostActivityRef = new SoftReference(activity);
    }

    @Override // com.jingdong.manto.MantoActivityResult
    public void setResultCallback(MantoActivityResult.ResultCallback resultCallback) {
        this.preResultCallback = this.resultCallback;
        this.resultCallback = resultCallback;
    }

    @Override // com.jingdong.manto.e
    public void setTaskDescription() {
    }

    @Override // com.jingdong.manto.e
    public void setTitle(String str, int i2) {
        g gVar;
        com.jingdong.manto.f g2;
        l lVar;
        n i3;
        if (this.destroyed || (gVar = this.runtimeContainer) == null || (g2 = gVar.g()) == null || (lVar = g2.f13014f) == null || lVar.getFirstPage() == null || g2.f13014f.getFirstPage().i() == null || (i3 = g2.f13014f.getFirstPage().i()) == null || i3.hashCode() != i2) {
            return;
        }
        i3.h(str);
    }

    @Override // com.jingdong.manto.e
    public void showLoading() {
        if (this.loadingToastView != null) {
            hideLoading();
        }
        View inflate = LayoutInflater.from(com.jingdong.manto.c.a()).inflate(R.layout.manto_toast, (ViewGroup) null);
        this.loadingToastView = inflate;
        inflate.findViewById(R.id.ll_loading).setVisibility(0);
        this.loadingToastView.findViewById(R.id.ll_success).setVisibility(8);
        ((TextView) this.loadingToastView.findViewById(R.id.toast_loading_title)).setText("\u52a0\u8f7d\u4e2d");
        this.loadingToastView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.loadingToastView.findViewById(R.id.toast_root).getBackground().setColorFilter(Color.parseColor("#88000000"), PorterDuff.Mode.SRC_ATOP);
        this.loadingToastView.setOnTouchListener(new c(this));
        if (getPageInnerContentView() != null) {
            getPageInnerContentView().addView(this.loadingToastView);
        }
    }

    @Override // com.jingdong.manto.e
    public void showSplashView(String str, String str2, int i2) {
        if (isShowSplash() && this.splashView == null) {
            s sVar = new s(getActivity(), i2);
            this.splashView = sVar;
            this.rootLayout.addView(sVar);
            this.splashView.setLoadingTitle(str);
            if (TextUtils.isEmpty(str2)) {
                this.splashView.setMantoIcon(R.drawable.manto_icon_default);
            } else {
                this.splashView.setMantoIcon(str2);
            }
            this.splashView.a();
        }
    }

    public void updateProgress(long j2, long j3) {
        s sVar = this.splashView;
        if (sVar != null) {
            sVar.a(j2, j3);
        }
    }
}
