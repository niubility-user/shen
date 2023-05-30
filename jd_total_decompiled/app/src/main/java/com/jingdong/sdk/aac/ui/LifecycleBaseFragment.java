package com.jingdong.sdk.aac.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.sdk.aac.base.EventListener;
import com.jingdong.sdk.aac.base.ManagerRegistry;
import com.jingdong.sdk.aac.base.StatusRegistry;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.util.LifecycleUtil;
import de.greenrobot.event.EventBus;

/* loaded from: classes7.dex */
public abstract class LifecycleBaseFragment<VM extends BaseViewModel, N extends BaseNavigator> extends Fragment implements EventListener {
    protected boolean isDestroy;
    protected N mNavigator;
    private String mParentKey;
    private StatusRegistry mStatusRegistry;
    protected VM mViewModel;
    private ManagerRegistry managerRegistry;
    protected View rootView;

    public LifecycleBaseFragment() {
        this("");
    }

    private void initDependency() {
        String str;
        if (TextUtils.isEmpty(this.mParentKey)) {
            str = (isUseParenKey() && (getActivity() instanceof LifecycleBaseActivity)) ? ((LifecycleBaseActivity) getActivity()).getManagerKey() : null;
        } else {
            str = this.mParentKey;
        }
        ManagerRegistry managerRegistry = new ManagerRegistry(str);
        this.managerRegistry = managerRegistry;
        managerRegistry.setActivity(getActivity());
        this.managerRegistry.setEventBus(resetEventBus());
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

    protected abstract N createNavigator();

    @Nullable
    protected abstract VM createViewModel();

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
        return this.mNavigator;
    }

    public <T extends BaseViewModel> T getViewModel(Class<T> cls) {
        return (T) LifecycleUtil.getInstance().getViewModel(this, cls, getManagerKey());
    }

    public boolean isRegisterEventBus() {
        return false;
    }

    protected boolean isUseParenKey() {
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        initDependency();
        super.onCreate(bundle);
        if (this.rootView == null) {
            try {
                this.rootView = onCreateViews(getLayoutInflater(), bundle);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.rootView;
    }

    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.isDestroy = true;
        super.onDestroy();
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

    @Override // com.jingdong.sdk.aac.base.EventListener
    public void onEvent(String str, Object obj) {
    }

    public void onEventMainThread(Object obj) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (isRegisterEventBus()) {
            this.managerRegistry.register(this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (isRegisterEventBus()) {
            this.managerRegistry.unregister(this);
        }
    }

    protected EventBus resetEventBus() {
        return EventBus.getDefault();
    }

    public LifecycleBaseFragment(String str) {
        this.mViewModel = null;
        this.isDestroy = false;
        this.mParentKey = "";
        this.mParentKey = str;
    }

    public <T extends BaseViewModel> T getViewModel(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return (T) getViewModel(cls);
        }
        return (T) LifecycleUtil.getInstance().getViewModel(this, str, cls, getManagerKey());
    }

    public <T extends BaseViewModel> T getViewModel(FragmentActivity fragmentActivity, Class<T> cls) {
        return (T) LifecycleUtil.getInstance().getViewModel(fragmentActivity, cls, getManagerKey());
    }

    public <T extends BaseViewModel> T getViewModel(FragmentActivity fragmentActivity, String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return (T) getViewModel(cls);
        }
        return (T) LifecycleUtil.getInstance().getViewModel(fragmentActivity, str, cls, getManagerKey());
    }

    protected VM getViewModel() {
        return this.mViewModel;
    }
}
