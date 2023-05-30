package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeLBSListener {
    void getAddress(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getAddressID(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getAddressList(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getLastLocation(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getLatLng(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void hasLocationPermissionWithScene(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void hasLocationPermissionWithSceneV2(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void manualRequestLocationPermissionWithScene(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void requestLocationPermissionWithScene(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
