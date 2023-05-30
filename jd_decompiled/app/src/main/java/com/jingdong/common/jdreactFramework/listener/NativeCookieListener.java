package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeCookieListener {
    void clearCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void clearWebkitCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getUnionCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getWebkitCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void setCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void setWebkitCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
