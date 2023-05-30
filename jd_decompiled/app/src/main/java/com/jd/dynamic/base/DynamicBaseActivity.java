package com.jd.dynamic.base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IContainerCallback;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicBaseActivity;
import com.jd.dynamic.base.NewDynamicFetcher;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.error.DynamicException;
import com.jd.dynamic.lib.lifecycle.EventExtend;
import com.jd.dynamic.lib.lifecycle.LifecycleEventObserverExtend;
import com.jd.dynamic.lib.lifecycle.LifecycleOwnerExtend;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.utils.LangUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

@Keep
/* loaded from: classes13.dex */
public abstract class DynamicBaseActivity extends FragmentActivity implements LifecycleOwnerExtend {
    private String appType;
    private String bizField;
    private String businessData;
    private DynamicTemplateEngine mEngine;
    protected List<LifecycleEventObserverExtend> mObserverList = new ArrayList();
    protected Intent mResultIntent;
    private JDProgressBar progressBar;
    private String systemCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.dynamic.base.DynamicBaseActivity$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public class AnonymousClass1 implements NewDynamicFetcher.GlobalConfigListener {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a() {
            DynamicBaseActivity.this.handleDynamicTemplate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(Exception exc) {
            DynamicBaseActivity.this.progressBar.setVisibility(8);
            DynamicBaseActivity.this.onDynamicLoadError(exc);
            DynamicBaseActivity.this.useLocal();
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onEnd(boolean z) {
            DynamicBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.dynamic.base.b
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicBaseActivity.AnonymousClass1.this.a();
                }
            });
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onError(final Exception exc) {
            DynamicBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.dynamic.base.a
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicBaseActivity.AnonymousClass1.this.b(exc);
                }
            });
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onStart() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.dynamic.base.DynamicBaseActivity$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    public class AnonymousClass2 implements IContainerCallback {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a() {
            DynamicBaseActivity.this.progressBar.setVisibility(8);
            DynamicBaseActivity.this.onDynamicLoadSuccess();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(DynamicException dynamicException) {
            DynamicBaseActivity.this.progressBar.setVisibility(8);
            DynamicBaseActivity.this.onDynamicLoadError(dynamicException);
            DynamicBaseActivity.this.useLocal();
        }

        @Override // com.jd.dynamic.apis.IContainerCallback
        public void onError(@NonNull final DynamicException dynamicException) {
            DynamicBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.dynamic.base.d
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicBaseActivity.AnonymousClass2.this.b(dynamicException);
                }
            });
        }

        @Override // com.jd.dynamic.apis.IContainerCallback
        public void onSuccess() {
            DynamicBaseActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.dynamic.base.c
                @Override // java.lang.Runnable
                public final void run() {
                    DynamicBaseActivity.AnonymousClass2.this.a();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDynamicTemplate() {
        IDynamicDriver driver = DynamicSdk.getDriver();
        DYContainerConfig dYContainerConfig = new DYContainerConfig(this, this.systemCode, this.bizField, getCustomFunctionFactory());
        dYContainerConfig.setPackageName(getAppPackageName());
        DynamicContainer createContainer = driver.createContainer(dYContainerConfig);
        if (createContainer != null) {
            getDynamicContainer().addView(createContainer, new FrameLayout.LayoutParams(-1, -1));
            if (!TextUtils.isEmpty(this.businessData)) {
                try {
                    createContainer.setData(new JSONObject(new String(Base64.decode(this.businessData.replace(LangUtils.SINGLE_SPACE, MqttTopic.SINGLE_LEVEL_WILDCARD), 0))));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (!createContainer.load()) {
                createContainer.load(new AnonymousClass2());
                return;
            }
            this.progressBar.setVisibility(8);
            onDynamicLoadSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void v(LifecycleEventObserverExtend lifecycleEventObserverExtend) {
        lifecycleEventObserverExtend.onStateChanged(this, EventExtend.ON_ACTIVITY_RESULT);
    }

    private void useDynamic() {
        if (getDynamicContainer() == null) {
            useLocal();
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        getDynamicContainer().addView(this.progressBar, layoutParams);
        if (com.jd.dynamic.b.e.a.b.a(this.systemCode, this.bizField) != null) {
            handleDynamicTemplate();
        } else {
            DynamicSdk.getEngine().newFetchTemplates(new AnonymousClass1(), false, this.systemCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void w(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void y(LifecycleEventObserverExtend lifecycleEventObserverExtend) {
        lifecycleEventObserverExtend.onStateChanged(this, EventExtend.ON_ACTIVITY_RESULT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void z(Throwable th) {
    }

    @Override // com.jd.dynamic.lib.lifecycle.LifecycleOwnerExtend
    public void addLifecycleEventObserver(LifecycleEventObserverExtend lifecycleEventObserverExtend) {
        this.mObserverList.add(lifecycleEventObserverExtend);
    }

    protected String getAppPackageName() {
        return getPackageName();
    }

    public String getAppType() {
        return this.appType;
    }

    public String getBizField() {
        return this.bizField;
    }

    protected abstract View getContentView();

    protected IFunctionFactory getCustomFunctionFactory() {
        return null;
    }

    protected abstract FrameLayout getDynamicContainer();

    protected JSONObject getInitJSON() {
        return null;
    }

    protected InputStream getLocalXmlStream() {
        return null;
    }

    @Override // com.jd.dynamic.lib.lifecycle.LifecycleOwnerExtend
    public Intent getResultIntent() {
        return this.mResultIntent;
    }

    public String getSystemCode() {
        return this.systemCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        this.mResultIntent = intent;
        Observable.from(this.mObserverList).forEach(new Action1() { // from class: com.jd.dynamic.base.e
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynamicBaseActivity.this.v((LifecycleEventObserverExtend) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.base.f
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynamicBaseActivity.w((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.appType = getIntent().getStringExtra("appType");
        this.systemCode = getIntent().getStringExtra(DYConstants.DYN_PRV_SYSCODE_KEY);
        this.bizField = getIntent().getStringExtra("bizField");
        this.businessData = getIntent().getStringExtra("businessData");
        this.progressBar = new JDProgressBar(this);
        setContentView(getContentView());
        if (TextUtils.isEmpty(this.bizField) || TextUtils.isEmpty(this.systemCode)) {
            useLocal();
        } else {
            useDynamic();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mObserverList.clear();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDynamicLoadError(Exception exc) {
    }

    protected void onDynamicLoadSuccess() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mResultIntent = intent;
        Observable.from(this.mObserverList).forEach(new Action1() { // from class: com.jd.dynamic.base.g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynamicBaseActivity.this.y((LifecycleEventObserverExtend) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.base.h
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynamicBaseActivity.z((Throwable) obj);
            }
        });
    }

    protected void updateJson(JSONObject jSONObject) {
        this.mEngine.newRefreshView(jSONObject);
    }

    public void useLocal() {
        try {
            XMLParse xMLParse = new XMLParse(getLocalXmlStream());
            ViewNode parse = xMLParse.parse();
            parse.unBindMaps = xMLParse.unBindMaps;
            DynamicTemplateEngine dynamicTemplateEngine = new DynamicTemplateEngine(getAppPackageName(), this, getDynamicContainer(), getCustomFunctionFactory());
            this.mEngine = dynamicTemplateEngine;
            dynamicTemplateEngine.newInitTemplate(parse, getInitJSON(), null);
        } catch (Exception e2) {
            onDynamicLoadError(e2);
        }
    }
}
