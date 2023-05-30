package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeSystemListener {
    void changeStausBarColor(String str, JDCallback jDCallback, JDCallback jDCallback2);

    String desDecode(HashMap hashMap);

    String desEncode(HashMap hashMap);

    boolean doPay(HashMap hashMap);

    boolean doWeiXinLogin(HashMap hashMap);

    void getAndroidID(JDCallback jDCallback, JDCallback jDCallback2);

    void getCacheAddress(JDCallback jDCallback, JDCallback jDCallback2);

    void getCacheAddressScene(String str, JDCallback jDCallback, JDCallback jDCallback2);

    void getCartUUID(JDCallback jDCallback, JDCallback jDCallback2);

    void getCurrentAddress(JDCallback jDCallback, JDCallback jDCallback2);

    void getCurrentAddressScene(String str, JDCallback jDCallback, JDCallback jDCallback2);

    void getDeviceID(JDCallback jDCallback, JDCallback jDCallback2);

    void getDeviceInfo(JDCallback jDCallback, JDCallback jDCallback2);

    void isAppDebug(JDCallback jDCallback, JDCallback jDCallback2);

    void isDebugMode(JDCallback jDCallback, JDCallback jDCallback2);

    void isWifiVideoAutoPlay(JDCallback jDCallback, JDCallback jDCallback2);

    boolean jumpPay(HashMap hashMap);

    void payOutOrder(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void requestPermission(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void setBarMode(Activity activity, boolean z, JDCallback jDCallback, JDCallback jDCallback2);

    void setCacheAddress(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void setInputMode(Activity activity, String str, JDCallback jDCallback, JDCallback jDCallback2);

    void xSwitch(JDCallback jDCallback, JDCallback jDCallback2);
}
