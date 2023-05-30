package com.jingdong.service.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.service.BaseService;
import com.jingdong.service.callback.FloatPermissionListener;
import com.jingdong.service.callback.PermissionListener;
import com.jingdong.service.service.PermissionService;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes10.dex */
public class IMPermission extends BaseService implements PermissionService {
    private static final String TAG = "IMPermission";

    public boolean applyFloatWindow(Context context, String str, Bundle bundle, FloatPermissionListener floatPermissionListener) {
        return false;
    }

    public Bundle generateBundle(HashMap hashMap) {
        return null;
    }

    public boolean hasPermission(Activity activity, String str) {
        return false;
    }

    public boolean hasPermission(Activity activity, List<String> list) {
        return false;
    }

    public boolean permissionSwitch() {
        return false;
    }

    public void requestPermission(Activity activity, Bundle bundle, String str, PermissionListener permissionListener, String str2, String str3) {
    }

    public void requestPermission(Activity activity, Bundle bundle, List<String> list, PermissionListener permissionListener, List<String> list2, List<String> list3) {
    }
}
