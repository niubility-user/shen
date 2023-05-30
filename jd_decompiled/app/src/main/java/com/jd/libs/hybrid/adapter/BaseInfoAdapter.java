package com.jd.libs.hybrid.adapter;

import android.app.Activity;

/* loaded from: classes16.dex */
public abstract class BaseInfoAdapter implements IAdapter {
    public static final String NAME = "adapter_base_info";

    public abstract String getArea();

    public abstract String getLat();

    public abstract String getLng();

    @Override // com.jd.libs.hybrid.adapter.IAdapter
    public String getName() {
        return NAME;
    }

    public abstract String getUrl(Activity activity);
}
