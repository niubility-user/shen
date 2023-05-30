package com.jingdong.sdk.platform.manager;

import com.jingdong.sdk.platform.base.UnProguard;

/* loaded from: classes10.dex */
public class ViewHolderManagerProxy extends UnProguard {
    private static ViewHolderManager mViewHolderManager;

    public static synchronized ViewHolderManager getInstance() {
        ViewHolderManager viewHolderManager;
        synchronized (ViewHolderManagerProxy.class) {
            if (mViewHolderManager == null) {
                mViewHolderManager = new ViewHolderManagerImpl();
            }
            viewHolderManager = mViewHolderManager;
        }
        return viewHolderManager;
    }
}
