package com.jingdong.sdk.aac.ui;

import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.sdk.aac.base.EventListener;
import com.jingdong.sdk.aac.base.ManagerRegistry;
import com.jingdong.sdk.aac.base.StatusRegistry;
import com.jingdong.sdk.aac.base.UnProguard;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.util.LifecycleUtil;

/* loaded from: classes7.dex */
public abstract class LifecycleBaseViewHolder<VM extends BaseViewModel, N extends BaseNavigator> implements EventListener, UnProguard {
    private static final String TAG = "LifecycleBaseViewHolder";
    protected Context mContext;
    protected N mNavigator;
    private StatusRegistry mStatusRegistry;
    private ManagerRegistry managerRegistry;
    protected VM mViewModel = null;
    protected boolean isDestroy = false;
    private String mViewModelKey = null;

    public LifecycleBaseViewHolder(Context context, String str) {
        if (context != null && (context instanceof FragmentActivity)) {
            this.mContext = context;
            initDependency(str);
            return;
        }
        throw new IllegalArgumentException("context must be instanceof FragmentActivity!");
    }

    private void initDependency(String str) {
        ManagerRegistry managerRegistry = new ManagerRegistry(str);
        this.managerRegistry = managerRegistry;
        Context context = this.mContext;
        if (context != null && (context instanceof FragmentActivity)) {
            managerRegistry.setActivity((FragmentActivity) context);
            if (!TextUtils.isEmpty(getActionName())) {
                this.mStatusRegistry = new StatusRegistry(getManagerKey(), getActionName(), this);
            }
            if (this.mViewModel == null) {
                if (getViewModelClass() != null) {
                    String createKey = LifecycleUtil.createKey();
                    this.mViewModelKey = createKey;
                    VM vm = (VM) getViewModel(createKey, getViewModelClass());
                    this.mViewModel = vm;
                    if (vm != null) {
                        vm.setViewModelKey(this.mViewModelKey);
                    }
                } else {
                    this.mViewModel = createViewModel();
                }
            }
            if (this.mNavigator == null) {
                this.mNavigator = createNavigator();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("mContext must be instance of FragmentActivity!");
    }

    protected abstract N createNavigator();

    protected VM createViewModel() {
        return null;
    }

    @Override // com.jingdong.sdk.aac.base.EventListener
    public String getActionName() {
        return null;
    }

    protected Fragment getFragment() {
        return null;
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
        if (getFragment() == null) {
            Context context = this.mContext;
            if (context != null && (context instanceof FragmentActivity)) {
                return (T) LifecycleUtil.getInstance().getViewModel((FragmentActivity) this.mContext, cls, getManagerKey());
            }
            throw new IllegalArgumentException("mContext must be instance of FragmentActivity!");
        }
        return (T) LifecycleUtil.getInstance().getViewModel(getFragment(), cls, getManagerKey());
    }

    public abstract Class<VM> getViewModelClass();

    public String getViewModelKey() {
        return this.mViewModelKey;
    }

    public void initData(Fragment fragment) {
    }

    public void onDestroy() {
        this.isDestroy = true;
        StatusRegistry statusRegistry = this.mStatusRegistry;
        if (statusRegistry != null) {
            statusRegistry.onDestroy();
        }
        this.mStatusRegistry = null;
        this.mContext = null;
    }

    @Override // com.jingdong.sdk.aac.base.EventListener
    public void onEvent(String str, Object obj) {
    }

    public <T extends BaseViewModel> T getViewModel(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return (T) getViewModel(cls);
        }
        if (getFragment() == null) {
            Context context = this.mContext;
            if (context != null && (context instanceof FragmentActivity)) {
                return (T) LifecycleUtil.getInstance().getViewModel((FragmentActivity) this.mContext, str, cls, getManagerKey());
            }
            throw new IllegalArgumentException("mContext must be instance of FragmentActivity!");
        }
        return (T) LifecycleUtil.getInstance().getViewModel(getFragment(), str, cls, getManagerKey());
    }

    public LifecycleBaseViewHolder(Context context, Fragment fragment, String str) {
        if (context != null && (context instanceof FragmentActivity)) {
            this.mContext = context;
            initData(fragment);
            initDependency(str);
            return;
        }
        throw new IllegalArgumentException("context must be instanceof FragmentActivity!");
    }

    protected VM getViewModel() {
        VM vm = this.mViewModel;
        if (vm != null) {
            return vm;
        }
        throw new NullPointerException("viewModel is null ! you should create a viewModel.");
    }
}
