package com.jingdong.sdk.platform.lib.openapi.ui;

import androidx.fragment.app.FragmentActivity;

/* loaded from: classes10.dex */
public abstract class AbsActivityCompact implements ILifecycle {
    protected FragmentActivity mActivity;

    public AbsActivityCompact(FragmentActivity fragmentActivity) {
        this.mActivity = fragmentActivity;
    }
}
