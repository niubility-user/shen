package com.jingdong.sdk.platform.lib.openapi.ui;

import androidx.fragment.app.Fragment;

/* loaded from: classes10.dex */
public abstract class AbsFragmentCompact implements ILifecycle {
    protected Fragment mFragment;

    public AbsFragmentCompact(Fragment fragment) {
        this.mFragment = fragment;
    }
}
