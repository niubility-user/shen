package com.facebook.react.modules.network;

/* loaded from: classes12.dex */
public class BaseInfoHelper {
    private static BaseInfoHelper mBaseInfoHelper;
    private static BaseInfoInterface mBaseInfoInterface;

    /* loaded from: classes12.dex */
    public interface BaseInfoInterface {
        String getUserAgent();
    }

    public static BaseInfoHelper newInstance() {
        if (mBaseInfoHelper == null) {
            mBaseInfoHelper = new BaseInfoHelper();
        }
        return mBaseInfoHelper;
    }

    public String getUserAgent() {
        BaseInfoInterface baseInfoInterface = mBaseInfoInterface;
        return baseInfoInterface != null ? baseInfoInterface.getUserAgent() : "";
    }

    public void setBaseInfo(BaseInfoInterface baseInfoInterface) {
        mBaseInfoInterface = baseInfoInterface;
    }
}
