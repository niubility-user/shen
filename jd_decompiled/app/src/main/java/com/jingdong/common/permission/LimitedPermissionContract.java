package com.jingdong.common.permission;

import android.os.Bundle;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
class LimitedPermissionContract {
    private static final String UNI_ALBUM_CLASS_NAME_1 = "PhotoAlbumActivity";
    private static final String UNI_ALBUM_CLASS_NAME_2 = "JdmmMediaPickerActivity";
    private static final String UNI_ALBUM_METHOD_NAME_1 = "checkReadStoragePermission";
    private static final String UNI_ALBUM_METHOD_NAME_2 = "getPermission";
    private static final String UNI_ALBUM_MODULE_NAME_1 = "unification";
    private static final String UNI_ALBUM_MODULE_NAME_2 = "mediaPicker";
    static List<String> notAllowedPermissions = Arrays.asList("android.permission.READ_PHONE_STATE", "android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS");
    static List<String> limitedStoragePermissions = Arrays.asList("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");

    LimitedPermissionContract() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSceneLegal(String str) {
        return "basicShoppingProcess".equals(str) || "marketingActivities".equals(str) || "locService".equals(str) || "receiveAddress".equals(str);
    }

    static boolean isUniAlbumScene(Bundle bundle) {
        if (bundle != null) {
            boolean equals = UNI_ALBUM_MODULE_NAME_1.equals(bundle.getString("moduleName", ""));
            boolean equals2 = UNI_ALBUM_CLASS_NAME_1.equals(bundle.getString("className", ""));
            boolean equals3 = UNI_ALBUM_METHOD_NAME_1.equals(bundle.getString("methodName", ""));
            boolean equals4 = UNI_ALBUM_MODULE_NAME_2.equals(bundle.getString("moduleName", ""));
            boolean equals5 = UNI_ALBUM_CLASS_NAME_2.equals(bundle.getString("className", ""));
            boolean equals6 = UNI_ALBUM_METHOD_NAME_2.equals(bundle.getString("methodName", ""));
            if ((equals && equals2 && equals3) || (equals4 && equals5 && equals6)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean shouldInterceptRequest(List<String> list, Bundle bundle) {
        if (list == null || list.size() == 0) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        for (String str : list) {
            if (notAllowedPermissions.contains(str)) {
                z = true;
            } else if (limitedStoragePermissions.contains(str)) {
                z2 = true;
            }
        }
        return z || (z2 && !isUniAlbumScene(bundle));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean shouldInterceptRequest(String[] strArr, Bundle bundle) {
        if (strArr == null || strArr.length <= 0) {
            return false;
        }
        return shouldInterceptRequest(Arrays.asList(strArr), bundle);
    }
}
