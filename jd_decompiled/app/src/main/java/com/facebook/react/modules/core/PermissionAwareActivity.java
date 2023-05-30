package com.facebook.react.modules.core;

/* loaded from: classes12.dex */
public interface PermissionAwareActivity {
    int checkPermission(String str, int i2, int i3);

    int checkSelfPermission(String str);

    void requestPermissions(String[] strArr, int i2, PermissionListener permissionListener);

    boolean shouldShowRequestPermissionRationale(String str);
}
