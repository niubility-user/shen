package com.jingdong.sdk.aac.model;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import com.jingdong.sdk.aac.base.EventListener;
import com.jingdong.sdk.aac.base.ICommon;
import com.jingdong.sdk.aac.base.ManagerRegistry;
import com.jingdong.sdk.aac.base.SafeRunnable;
import com.jingdong.sdk.aac.base.StatusRegistry;
import com.jingdong.sdk.aac.base.UnProguard;
import de.greenrobot.event.EventBus;

/* loaded from: classes7.dex */
public abstract class BaseViewModel extends ViewModel implements ICommon, EventListener, UnProguard {
    protected boolean isDestroy = false;
    private StatusRegistry mStatusRegistry;
    private String mViewModelKey;
    private ManagerRegistry managerRegistry;

    public BaseViewModel(String str) {
        if (!TextUtils.isEmpty(str)) {
            ManagerRegistry managerRegistry = new ManagerRegistry(str);
            this.managerRegistry = managerRegistry;
            managerRegistry.setEventBus(resetEventBus());
            if (isRegisterEventBus()) {
                this.managerRegistry.register(this);
            }
            if (TextUtils.isEmpty(getActionName())) {
                return;
            }
            this.mStatusRegistry = new StatusRegistry(getManagerKey(), getActionName(), this);
            return;
        }
        throw new NullPointerException("managerKey can not be null!");
    }

    public void excuteOnActivity(SafeRunnable safeRunnable) {
        this.managerRegistry.excuteOnActivity(safeRunnable);
    }

    @Override // com.jingdong.sdk.aac.base.EventListener
    public String getActionName() {
        return null;
    }

    public EventBus getEventBus() {
        return this.managerRegistry.getEventBus();
    }

    @Override // com.jingdong.sdk.aac.base.ICommon
    public String getManagerKey() {
        return this.managerRegistry.getManagerKey();
    }

    @Override // com.jingdong.sdk.aac.base.ICommon
    public <T extends FragmentActivity> T getThisActivity() {
        return (T) this.managerRegistry.getThisActivity();
    }

    public String getViewModelKey() {
        return this.mViewModelKey;
    }

    public boolean isRegisterEventBus() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        this.isDestroy = true;
        super.onCleared();
        onDestroy();
        if (isRegisterEventBus()) {
            this.managerRegistry.unregister(this);
        }
        StatusRegistry statusRegistry = this.mStatusRegistry;
        if (statusRegistry != null) {
            statusRegistry.onDestroy();
        }
        this.mStatusRegistry = null;
    }

    public abstract void onDestroy();

    @Override // com.jingdong.sdk.aac.base.EventListener
    public void onEvent(String str, Object obj) {
    }

    public void onEventMainThread(Object obj) {
    }

    public void onTrimMemory(int i2) {
    }

    public EventBus resetEventBus() {
        return EventBus.getDefault();
    }

    public void setViewModelKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mViewModelKey = str;
    }
}
