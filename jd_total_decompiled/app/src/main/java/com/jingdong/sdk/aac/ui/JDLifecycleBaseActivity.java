package com.jingdong.sdk.aac.ui;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jingdong.sdk.aac.base.EventListener;
import com.jingdong.sdk.aac.base.ManagerRegistry;
import com.jingdong.sdk.aac.base.StatusRegistry;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.util.LifecycleUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.lib.ui.CompactActivity;
import de.greenrobot.event.EventBus;

/* loaded from: classes7.dex */
public abstract class JDLifecycleBaseActivity<VM extends BaseViewModel, N extends BaseNavigator> extends CompactActivity implements EventListener {
    private static final String TAG = "LifecycleBaseActivity";
    protected N mNavigator;
    private StatusRegistry mStatusRegistry;
    private ManagerRegistry managerRegistry;
    protected VM mViewModel = null;
    protected boolean isDestroy = false;

    private void initDependency() {
        ManagerRegistry managerRegistry = new ManagerRegistry();
        this.managerRegistry = managerRegistry;
        managerRegistry.setEventBus(resetEventBus());
        this.managerRegistry.setActivity(this);
        if (!TextUtils.isEmpty(getActionName())) {
            this.mStatusRegistry = new StatusRegistry(getManagerKey(), getActionName(), this);
        }
        if (this.mViewModel == null) {
            this.mViewModel = createViewModel();
        }
        if (this.mNavigator == null) {
            this.mNavigator = createNavigator();
        }
    }

    protected abstract int createLayout();

    protected abstract N createNavigator();

    public abstract VM createViewModel();

    @Override // com.jingdong.sdk.aac.base.EventListener
    public String getActionName() {
        return null;
    }

    public EventBus getEventBus() {
        return this.managerRegistry.getEventBus();
    }

    public String getManagerKey() {
        return this.managerRegistry.getManagerKey();
    }

    protected N getNavigator() {
        N n2 = this.mNavigator;
        if (n2 != null) {
            return n2;
        }
        throw new NullPointerException("navigator is null ! you should create a navigator.");
    }

    public <T extends BaseViewModel> T getViewModel(Class<T> cls) {
        return (T) LifecycleUtil.getInstance().getViewModel(this, cls, getManagerKey());
    }

    public boolean isRegisterEventBus() {
        return false;
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initDependency();
        if (createLayout() == 0) {
            if (OKLog.D) {
                OKLog.d(TAG, "createLayout() returned 0, If you don't want to use createLayout(), but implement your own view,you have to override setContentView();");
                return;
            }
            return;
        }
        super.setContentView(createLayout());
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (OKLog.D) {
            OKLog.d(TAG, " onDestroy  --->  : ");
        }
        this.isDestroy = true;
        super.onDestroy();
        LifecycleUtil.getInstance().removeActivity(getManagerKey());
        ManagerRegistry managerRegistry = this.managerRegistry;
        if (managerRegistry != null) {
            managerRegistry.onDestroy();
        }
        StatusRegistry statusRegistry = this.mStatusRegistry;
        if (statusRegistry != null) {
            statusRegistry.onDestroy();
        }
        this.mStatusRegistry = null;
    }

    public void onEvent(Object obj) {
    }

    @Override // com.jingdong.sdk.aac.base.EventListener
    public void onEvent(String str, Object obj) {
    }

    public void onEventMainThread(Object obj) {
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (isRegisterEventBus()) {
            this.managerRegistry.register(this);
        }
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (isRegisterEventBus()) {
            this.managerRegistry.unregister(this);
        }
    }

    public EventBus resetEventBus() {
        return EventBus.getDefault();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated
    public void setContentView(int i2) {
        super.setContentView(i2);
    }

    public <T extends BaseViewModel> T getViewModel(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return (T) getViewModel(cls);
        }
        return (T) LifecycleUtil.getInstance().getViewModel(this, str, cls, getManagerKey());
    }

    protected VM getViewModel() {
        VM vm = this.mViewModel;
        if (vm != null) {
            return vm;
        }
        throw new NullPointerException("viewModel is null ! you should create a viewModel.");
    }
}
