package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeUserLoginListener {
    HashMap getUserInfo();

    String getUserPin();

    boolean isLogin();

    void login(JDCallback jDCallback, JDCallback jDCallback2);

    void login(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
