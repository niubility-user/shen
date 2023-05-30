package com.jingdong.sdk.aac.ui;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.sdk.aac.base.EventListener;
import com.jingdong.sdk.aac.base.ManagerRegistry;
import com.jingdong.sdk.aac.base.StatusRegistry;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.util.LifecycleUtil;
import de.greenrobot.event.EventBus;

/* loaded from: classes7.dex */
public abstract class LifecycleBaseActivity<VM extends BaseViewModel, N extends BaseNavigator> extends FragmentActivity implements EventListener {
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
        ManagerRegistry managerRegistry;
        return (this.isDestroy || (managerRegistry = this.managerRegistry) == null) ? "" : managerRegistry.getManagerKey();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initDependency();
        if (createLayout() == 0) {
            return;
        }
        super.setContentView(createLayout());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (isRegisterEventBus()) {
            this.managerRegistry.register(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (isRegisterEventBus()) {
            this.managerRegistry.unregister(this);
        }
    }

    public EventBus resetEventBus() {
        return EventBus.getDefault();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
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
