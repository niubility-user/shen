package com.jingdong.sdk.aac.base;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.sdk.aac.util.LifecycleUtil;
import com.jingdong.sdk.aac.util.SyncEventBus;
import de.greenrobot.event.EventBus;

/* loaded from: classes7.dex */
public class ManagerRegistry implements ICommon {
    private boolean isCreateKey;
    private boolean isDestroy;
    private boolean isSetActivity;
    private EventBus mEventBus;
    private String managerKey;

    public ManagerRegistry(String str) {
        this.isCreateKey = false;
        this.isSetActivity = false;
        this.isDestroy = false;
        this.managerKey = str;
        if (TextUtils.isEmpty(str)) {
            this.managerKey = LifecycleUtil.createKey();
            this.isCreateKey = true;
        }
    }

    public void excuteOnActivity(SafeRunnable safeRunnable) {
        FragmentActivity thisActivity;
        if (this.isDestroy || safeRunnable == null || (thisActivity = getThisActivity()) == null || thisActivity.isFinishing()) {
            return;
        }
        safeRunnable.runOnActivity(thisActivity);
    }

    public EventBus getEventBus() {
        EventBus eventBus = this.mEventBus;
        return eventBus != null ? eventBus : EventBus.getDefault();
    }

    @Override // com.jingdong.sdk.aac.base.ICommon
    public String getManagerKey() {
        return this.managerKey;
    }

    @Override // com.jingdong.sdk.aac.base.ICommon
    public <T extends FragmentActivity> T getThisActivity() {
        if (TextUtils.isEmpty(this.managerKey)) {
            return null;
        }
        return (T) LifecycleUtil.getInstance().getActivity(this.managerKey);
    }

    public void onDestroy() {
        this.isDestroy = true;
        if (this.isCreateKey) {
            SyncEventBus.destroy(this.managerKey);
        }
        if (this.isSetActivity) {
            LifecycleUtil.getInstance().removeActivity(this.managerKey);
        }
    }

    public void register(Object obj) {
        if (getEventBus().isRegistered(obj)) {
            return;
        }
        getEventBus().register(obj);
    }

    public void setActivity(FragmentActivity fragmentActivity) {
        if ((this.isCreateKey || LifecycleUtil.getInstance().getActivity(this.managerKey) == null) && fragmentActivity != null) {
            this.isSetActivity = LifecycleUtil.getInstance().setActivity(getManagerKey(), fragmentActivity);
        }
    }

    public void setEventBus(EventBus eventBus) {
        this.mEventBus = eventBus;
    }

    public void unregister(Object obj) {
        getEventBus().unregister(obj);
    }

    public ManagerRegistry() {
        this("");
    }
}
