package com.jingdong.cleanmvp.presenter;

import android.content.Intent;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import de.greenrobot.event.EventBus;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public abstract class BasePresenter<UI extends IBaseUI> {
    private static final String TAG = "BasePresenter";
    protected boolean isShow = false;
    protected WeakReference<UI> mUI = null;
    private boolean isUnregisterInStop = false;

    public BasePresenter() {
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    public void attachUI(UI ui) {
        if (ui == null || this.isShow) {
            return;
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        this.mUI = new WeakReference<>(ui);
        this.isShow = true;
        onAttach(ui);
    }

    protected abstract UI createNullObject();

    public synchronized void detachUI(UI ui) {
        if (ui != null) {
            onDetach(ui);
            EventBus.getDefault().unregister(this);
            this.mUI = null;
        }
    }

    public synchronized UI getUI() {
        WeakReference<UI> weakReference = this.mUI;
        if (weakReference != null && weakReference.get() != null) {
            return this.mUI.get();
        }
        return createNullObject();
    }

    public boolean handleIntent(Intent intent) {
        return false;
    }

    public boolean isShow() {
        return this.isShow;
    }

    protected abstract void onAttach(UI ui);

    protected abstract void onDetach(UI ui);

    public abstract void onEvent(BaseEvent baseEvent);

    public abstract void onEventMainThread(BaseEvent baseEvent);

    protected abstract void onSuspend();

    public void onTrimMemory(int i2) {
    }

    public void setIsShow(boolean z) {
        this.isShow = z;
    }

    public void suspend() {
        if (this.isShow) {
            this.isShow = false;
        }
        if (this.isUnregisterInStop) {
            EventBus.getDefault().unregister(this);
        }
        onSuspend();
    }

    public void unregisterEventBusInStop(boolean z) {
        this.isUnregisterInStop = z;
    }
}
