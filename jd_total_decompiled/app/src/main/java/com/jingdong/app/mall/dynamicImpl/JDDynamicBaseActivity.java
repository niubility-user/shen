package com.jingdong.app.mall.dynamicImpl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IContainerCallback;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.base.NewDynamicFetcher;
import com.jd.dynamic.base.interfaces.IAppRouter;
import com.jd.dynamic.lib.error.DynamicException;
import com.jd.dynamic.lib.lifecycle.EventExtend;
import com.jd.dynamic.lib.lifecycle.LifecycleEventObserverExtend;
import com.jd.dynamic.lib.lifecycle.LifecycleOwnerExtend;
import com.jingdong.app.mall.dynamicImpl.JDDynamicBaseActivity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.uniwidget.UnErrorPageView;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes3.dex */
public class JDDynamicBaseActivity extends BaseActivity implements LifecycleOwnerExtend {

    /* renamed from: g  reason: collision with root package name */
    private FrameLayout f8361g;

    /* renamed from: i  reason: collision with root package name */
    protected Intent f8363i;

    /* renamed from: j  reason: collision with root package name */
    private String f8364j;

    /* renamed from: k  reason: collision with root package name */
    private String f8365k;

    /* renamed from: l  reason: collision with root package name */
    private String f8366l;

    /* renamed from: m  reason: collision with root package name */
    private String f8367m;

    /* renamed from: n  reason: collision with root package name */
    private JDProgressBar f8368n;

    /* renamed from: h  reason: collision with root package name */
    protected List<LifecycleEventObserverExtend> f8362h = new ArrayList();
    private boolean o = false;
    private String p = null;
    private String q = null;
    private boolean r = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class a implements NewDynamicFetcher.GlobalConfigListener {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b() {
            JDDynamicBaseActivity.this.handleDynamicTemplate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(Exception exc) {
            JDDynamicBaseActivity.this.y();
            JDDynamicBaseActivity.this.f8368n.setVisibility(8);
            JDDynamicBaseActivity.this.onDynamicLoadError(exc);
            if (JDDynamicBaseActivity.this.A()) {
                return;
            }
            JDDynamicBaseActivity.this.useLocal();
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onEnd(boolean z) {
            JDDynamicBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.jingdong.app.mall.dynamicImpl.e
                @Override // java.lang.Runnable
                public final void run() {
                    JDDynamicBaseActivity.a.this.b();
                }
            });
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onError(final Exception exc) {
            JDDynamicBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.jingdong.app.mall.dynamicImpl.d
                @Override // java.lang.Runnable
                public final void run() {
                    JDDynamicBaseActivity.a.this.d(exc);
                }
            });
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onStart() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class b implements IContainerCallback {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(DynamicException dynamicException) {
            JDDynamicBaseActivity.this.y();
            JDDynamicBaseActivity.this.f8368n.setVisibility(8);
            JDDynamicBaseActivity.this.onDynamicLoadError(dynamicException);
            if (JDDynamicBaseActivity.this.A()) {
                return;
            }
            JDDynamicBaseActivity.this.useLocal();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d() {
            JDDynamicBaseActivity.this.f8368n.setVisibility(8);
            JDDynamicBaseActivity.this.onDynamicLoadSuccess();
        }

        @Override // com.jd.dynamic.apis.IContainerCallback
        public void onError(@NonNull final DynamicException dynamicException) {
            JDDynamicBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.jingdong.app.mall.dynamicImpl.g
                @Override // java.lang.Runnable
                public final void run() {
                    JDDynamicBaseActivity.b.this.b(dynamicException);
                }
            });
        }

        @Override // com.jd.dynamic.apis.IContainerCallback
        public void onSuccess() {
            JDDynamicBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.jingdong.app.mall.dynamicImpl.f
                @Override // java.lang.Runnable
                public final void run() {
                    JDDynamicBaseActivity.b.this.d();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean A() {
        return (TextUtils.isEmpty(this.p) || TextUtils.isEmpty(this.q)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void C(LifecycleEventObserverExtend lifecycleEventObserverExtend) {
        lifecycleEventObserverExtend.onStateChanged(this, EventExtend.ON_ACTIVITY_RESULT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void D(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void F(LifecycleEventObserverExtend lifecycleEventObserverExtend) {
        lifecycleEventObserverExtend.onStateChanged(this, EventExtend.ON_ACTIVITY_RESULT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void G(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void I(View view) {
        if (TextUtils.isEmpty(this.f8366l) || TextUtils.isEmpty(this.f8365k)) {
            return;
        }
        useDynamic();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDynamicTemplate() {
        IDynamicDriver driver = DynamicSdk.getDriver();
        DYContainerConfig dYContainerConfig = new DYContainerConfig(this, this.f8365k, this.f8366l, getCustomFunctionFactory());
        if (this.r) {
            dYContainerConfig.setUseAsyncItem(true);
        }
        dYContainerConfig.setUseCustomBackup(this.o);
        dYContainerConfig.setPackageName(getAppPackageName());
        DynamicContainer createContainer = driver.createContainer(dYContainerConfig);
        if (createContainer != null) {
            getDynamicContainer().addView(createContainer, new FrameLayout.LayoutParams(-1, -1));
            if (!TextUtils.isEmpty(this.f8367m)) {
                try {
                    createContainer.setData(new JSONObject(new String(Base64.decode(this.f8367m.replace(LangUtils.SINGLE_SPACE, MqttTopic.SINGLE_LEVEL_WILDCARD), 0))));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            createContainer.load(new b());
        }
    }

    private void useDynamic() {
        if (getDynamicContainer() == null) {
            useLocal();
            return;
        }
        getDynamicContainer().removeAllViews();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        getDynamicContainer().addView(this.f8368n, layoutParams);
        if (DynamicSdk.getDriver().getTemplateStatus(this.f8365k, this.f8366l).haveLocal()) {
            handleDynamicTemplate();
        } else {
            DynamicSdk.getEngine().newFetchTemplates(new a(), false, this.f8365k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        if (this.o && !TextUtils.isEmpty(this.q)) {
            IAppRouter appRouter = DynamicSdk.getEngine().getAppRouter();
            if ("app".equals(this.q) && !TextUtils.isEmpty(this.p) && appRouter != null) {
                appRouter.jumpWithUrl(this, this.p);
            }
            if ("h5".equals(this.q) && !TextUtils.isEmpty(this.p) && appRouter != null) {
                appRouter.jumpToH5(this, this.p);
            }
        }
        if (A()) {
            finish();
        }
    }

    private void z() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("darkMode");
        String stringExtra2 = intent.getStringExtra("transparentStatusBar");
        if ("1".equals(stringExtra2)) {
            UnStatusBarTintUtil.setStatusBar4Base(this, 1, false);
            if ("1".equals(stringExtra)) {
                UnStatusBarTintUtil.setStatusBarLightMode(this);
            } else if ("0".equals(stringExtra)) {
                UnStatusBarTintUtil.setStatusBarDarkMode(this);
            }
        } else if ("0".equals(stringExtra2)) {
            UnStatusBarTintUtil.setStatusBar4Base(this, 0, "0".equals(stringExtra));
        }
    }

    @Override // com.jd.dynamic.lib.lifecycle.LifecycleOwnerExtend
    public void addLifecycleEventObserver(LifecycleEventObserverExtend lifecycleEventObserverExtend) {
        this.f8362h.add(lifecycleEventObserverExtend);
    }

    protected String getAppPackageName() {
        return getPackageName();
    }

    public String getAppType() {
        return this.f8364j;
    }

    public String getBizField() {
        return this.f8366l;
    }

    protected View getContentView() {
        FrameLayout frameLayout = new FrameLayout(this);
        this.f8361g = frameLayout;
        return frameLayout;
    }

    protected IFunctionFactory getCustomFunctionFactory() {
        if (TextUtils.isEmpty(this.f8365k) || TextUtils.isEmpty(this.f8366l)) {
            return null;
        }
        return DynamicSdk.getDriver().getFunctionFactory(this.f8365k, this.f8366l);
    }

    protected FrameLayout getDynamicContainer() {
        return this.f8361g;
    }

    @Override // com.jd.dynamic.lib.lifecycle.LifecycleOwnerExtend
    public Intent getResultIntent() {
        return this.f8363i;
    }

    public String getSystemCode() {
        return this.f8365k;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        this.f8363i = intent;
        Observable.from(this.f8362h).forEach(new Action1() { // from class: com.jingdong.app.mall.dynamicImpl.i
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                JDDynamicBaseActivity.this.C((LifecycleEventObserverExtend) obj);
            }
        }, new Action1() { // from class: com.jingdong.app.mall.dynamicImpl.h
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                JDDynamicBaseActivity.D((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f8364j = getIntent().getStringExtra("appType");
        this.f8365k = getIntent().getStringExtra(DYConstants.DYN_PRV_SYSCODE_KEY);
        this.f8366l = getIntent().getStringExtra("bizField");
        this.f8367m = getIntent().getStringExtra("businessData");
        Intent intent = getIntent();
        try {
            this.o = "1".equals(intent.getStringExtra("isUseCustomBackup"));
            this.r = "1".equals(intent.getStringExtra("isCacheCalcValue"));
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("customBackupConfig"));
            this.q = jSONObject.optString("type");
            this.p = jSONObject.optString("url");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f8368n = new JDProgressBar(this);
        setContentView(getContentView());
        if (!TextUtils.isEmpty(this.f8366l) && !TextUtils.isEmpty(this.f8365k)) {
            useDynamic();
        } else {
            useLocal();
            onDynamicLoadError(new Exception());
        }
        z();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.f8362h.clear();
        super.onDestroy();
    }

    protected void onDynamicLoadError(Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("errCode", "941");
        hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
        hashMap.put("function", "loadpage");
        hashMap.put("errMsg", getAppType() + "-" + getSystemCode());
        hashMap.put("url", getBizField());
        hashMap.put("postData", exc.toString());
        ExceptionReporter.sendExceptionData(this, hashMap);
    }

    protected void onDynamicLoadSuccess() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f8363i = intent;
        Observable.from(this.f8362h).forEach(new Action1() { // from class: com.jingdong.app.mall.dynamicImpl.j
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                JDDynamicBaseActivity.this.F((LifecycleEventObserverExtend) obj);
            }
        }, new Action1() { // from class: com.jingdong.app.mall.dynamicImpl.l
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                JDDynamicBaseActivity.G((Throwable) obj);
            }
        });
    }

    public void useLocal() {
        try {
            if (getDynamicContainer() != null) {
                getDynamicContainer().removeAllViews();
                UnErrorPageView unErrorPageView = new UnErrorPageView(this);
                unErrorPageView.setAutoDarkMode(true);
                unErrorPageView.setAutoElderMode(true);
                unErrorPageView.setTipText("\u6a21\u7248\u52a0\u8f7d\u5931\u8d25", "\u70b9\u51fb\u53ef\u91cd\u65b0\u52a0\u8f7d");
                unErrorPageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.dynamicImpl.k
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        JDDynamicBaseActivity.this.I(view);
                    }
                });
                getDynamicContainer().addView(unErrorPageView, new FrameLayout.LayoutParams(-1, -1));
            }
        } catch (Exception e2) {
            onDynamicLoadError(e2);
        }
    }
}
