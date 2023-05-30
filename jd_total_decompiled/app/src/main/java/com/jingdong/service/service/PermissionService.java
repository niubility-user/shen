package com.jingdong.service.service;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.service.callback.FloatPermissionListener;
import com.jingdong.service.callback.PermissionListener;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes10.dex */
public interface PermissionService {
    boolean applyFloatWindow(Context context, String str, Bundle bundle, FloatPermissionListener floatPermissionListener);

    Bundle generateBundle(HashMap hashMap);

    boolean hasPermission(Activity activity, String str);

    boolean hasPermission(Activity activity, List<String> list);

    boolean permissionSwitch();

    void requestPermission(Activity activity, Bundle bundle, String str, PermissionListener permissionListener, String str2, String str3);

    void requestPermission(Activity activity, Bundle bundle, List<String> list, PermissionListener permissionListener, List<String> list2, List<String> list3);
}
