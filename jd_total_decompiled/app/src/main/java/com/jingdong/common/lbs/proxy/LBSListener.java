package com.jingdong.common.lbs.proxy;

/* loaded from: classes.dex */
public interface LBSListener {
    String getPin();

    String getUUID();

    boolean hasPrivacy();

    boolean isAppForeground();

    boolean isSceneAllowed(String str);
}
