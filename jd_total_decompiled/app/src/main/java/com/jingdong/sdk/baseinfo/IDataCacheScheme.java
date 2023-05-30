package com.jingdong.sdk.baseinfo;

/* loaded from: classes.dex */
public interface IDataCacheScheme {
    public static final int SCHEME_CONTENT_PROVIDER = 1;
    public static final int SCHEME_NO_CACHE = 0;
    public static final int SCHEME_SP = 2;

    int getScheme();
}
