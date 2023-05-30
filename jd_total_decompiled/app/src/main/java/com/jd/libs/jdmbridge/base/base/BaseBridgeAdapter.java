package com.jd.libs.jdmbridge.base.base;

import com.jd.libs.jdmbridge.base.JDBridgeManager;
import com.jd.libs.jdmbridge.base.base.IProxy;

/* loaded from: classes16.dex */
public abstract class BaseBridgeAdapter<T extends IProxy> {
    protected JDBridgeManager mBridgeManager;

    public BaseBridgeAdapter(JDBridgeManager jDBridgeManager) {
        this.mBridgeManager = jDBridgeManager;
    }

    public abstract T getProxy();
}
