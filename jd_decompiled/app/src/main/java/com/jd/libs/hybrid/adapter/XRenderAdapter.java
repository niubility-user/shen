package com.jd.libs.hybrid.adapter;

import com.jd.libs.hybrid.HybridSDK;

/* loaded from: classes16.dex */
public abstract class XRenderAdapter implements IAdapter {
    public static final String NAME = "xRender";

    @Override // com.jd.libs.hybrid.adapter.IAdapter
    public String getName() {
        return NAME;
    }

    public abstract void getPreRenderData(boolean z, HybridSDK.PreRender preRender);
}
