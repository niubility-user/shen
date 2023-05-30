package com.jingdong.sdk.aac.repository;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.sdk.aac.base.ICommon;
import com.jingdong.sdk.aac.base.SafeRunnable;
import com.jingdong.sdk.aac.util.LifecycleUtil;

/* loaded from: classes7.dex */
public abstract class BaseRepository implements ICommon {
    protected boolean isDestroy = false;
    private String managerKey;

    public BaseRepository(String str) {
        this.managerKey = str;
    }

    public void excuteOnActivity(SafeRunnable safeRunnable) {
        FragmentActivity thisActivity;
        if (this.isDestroy || safeRunnable == null || (thisActivity = getThisActivity()) == null || thisActivity.isFinishing()) {
            return;
        }
        safeRunnable.runOnActivity(thisActivity);
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
    }
}
