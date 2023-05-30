package com.jd.libs.hybrid.adapter;

import java.util.List;

/* loaded from: classes16.dex */
public abstract class CookieAdapter implements IAdapter {
    public static final String NAME = "adapter_cookie";

    public abstract String getCookieString(String str);

    @Override // com.jd.libs.hybrid.adapter.IAdapter
    public String getName() {
        return NAME;
    }

    public abstract boolean saveCookieString(String str, List<String> list);
}
