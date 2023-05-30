package com.jingdong.common.jdreactFramework.helper;

import android.app.Activity;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class PermissionHelper {
    private static final String TAG = "PermissionHelper";
    private static Map<Integer, PermissionRequestListener> mListenerMap = new HashMap();
    private static int sIncRequestCode;

    /* loaded from: classes5.dex */
    public static abstract class DefaultPermissionRequestListener implements PermissionRequestListener {
        @Override // com.jingdong.common.jdreactFramework.helper.PermissionHelper.PermissionRequestListener
        public void onPermissionRequest(boolean z, String str) {
        }

        @Override // com.jingdong.common.jdreactFramework.helper.PermissionHelper.PermissionRequestListener
        public void onPermissionRequest(boolean z, @NonNull String[] strArr, int[] iArr) {
        }
    }

    /* loaded from: classes5.dex */
    public interface PermissionRequestListener {
        void onPermissionRequest(boolean z, String str);

        void onPermissionRequest(boolean z, @NonNull String[] strArr, int[] iArr);
    }

    public static void checkPermission(Activity activity, String str, PermissionRequestListener permissionRequestListener) {
        checkPermission(activity, new String[]{str}, permissionRequestListener);
    }

    public static void dispatchPermissionResult(Activity activity, int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        PermissionRequestListener permissionRequestListener;
        if (strArr.length == 0 || iArr.length == 0) {
            return;
        }
        boolean z = false;
        int i3 = 0;
        while (true) {
            if (i3 >= strArr.length) {
                break;
            }
            String str = strArr[i3];
            boolean z2 = iArr[i3] == 0;
            JLog.d(TAG, "request permission result, permission: " + str + ", granted:" + z2);
            if (!z2) {
                ActivityCompat.shouldShowRequestPermissionRationale(activity, strArr[i3]);
            }
            i3++;
        }
        Map<Integer, PermissionRequestListener> map = mListenerMap;
        if (map == null || map.isEmpty() || (permissionRequestListener = mListenerMap.get(Integer.valueOf(i2))) == null) {
            return;
        }
        if (strArr.length == 1) {
            permissionRequestListener.onPermissionRequest(iArr[0] == 0, strArr[0]);
        } else {
            int length = iArr.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    z = true;
                    break;
                } else if (iArr[i4] != 0) {
                    permissionRequestListener.onPermissionRequest(false, strArr, iArr);
                    break;
                } else {
                    i4++;
                }
            }
            if (z) {
                permissionRequestListener.onPermissionRequest(true, strArr, iArr);
            }
        }
        mListenerMap.remove(Integer.valueOf(i2));
    }

    public static boolean permissionIsRefuseAndHide(Activity activity, String str) {
        return (ContextCompat.checkSelfPermission(activity, str) == 0 || ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
    }

    public static void requestCallPhonePermission(Activity activity, PermissionRequestListener permissionRequestListener) {
    }

    public static void requestConstactsPermission(Activity activity, PermissionRequestListener permissionRequestListener) {
    }

    public static void requestExternalStoragePermission(Activity activity, PermissionRequestListener permissionRequestListener) {
    }

    public static void requestForegroundLocationPermission(Activity activity, PermissionRequestListener permissionRequestListener) {
    }

    public static void requestLocationPermission(Activity activity, PermissionRequestListener permissionRequestListener) {
    }

    public static void requestReadPhoneStatePermission(Activity activity, PermissionRequestListener permissionRequestListener) {
    }

    public static void requestRecordAudioPermission(Activity activity, PermissionRequestListener permissionRequestListener) {
        checkPermission(activity, "android.permission.RECORD_AUDIO", permissionRequestListener);
    }

    public static void checkPermission(Activity activity, String[] strArr, PermissionRequestListener permissionRequestListener) {
        if (activity == null || strArr == null || strArr.length <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                        JLog.d(TAG, "request " + str);
                        arrayList.add(str);
                    } else {
                        JLog.d(TAG, "already has permission:" + str);
                    }
                } catch (Exception e2) {
                    JLog.e(TAG, "check self permission failed. " + e2);
                    arrayList.add(str);
                }
            }
        }
        if (arrayList.isEmpty()) {
            if (permissionRequestListener != null) {
                if (strArr.length == 1) {
                    permissionRequestListener.onPermissionRequest(true, strArr[0]);
                    return;
                } else {
                    permissionRequestListener.onPermissionRequest(true, strArr, null);
                    return;
                }
            }
            return;
        }
        int i2 = sIncRequestCode + 1;
        sIncRequestCode = i2;
        if (i2 > 255) {
            sIncRequestCode = 0;
        }
        mListenerMap.put(Integer.valueOf(sIncRequestCode), permissionRequestListener);
        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), sIncRequestCode);
    }
}
