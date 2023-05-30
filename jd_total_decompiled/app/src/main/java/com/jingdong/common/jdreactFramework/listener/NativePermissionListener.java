package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public interface NativePermissionListener {
    void getPermissionStatus(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void newRequestPermission(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void requestPermission(Activity activity, List list, JDCallback jDCallback, JDCallback jDCallback2);
}
