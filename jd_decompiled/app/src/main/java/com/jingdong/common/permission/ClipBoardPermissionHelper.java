package com.jingdong.common.permission;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class ClipBoardPermissionHelper {
    private static final String ClipBoard_PERMISSION_FIRST_KEY = "ClipBoard_PERMISSION_FIRST_";
    private static final int DEFAULT_INTERVAL = 172800000;
    private static final String KEY_CLIPBOARD_PERMISSION = "clipBoardPermissionSwitch";
    private static final String KEY_CLIPBOARD_PERMISSION_INTERVAL = "clipBoardPermissionInterval";
    private static final String KEY_INTERVAL = "interval";
    private static final String KEY_SWITCH = "switch";
    public static final String TAG = "ClipBoardPermissionHelper";
    public static final String VALUE_CLIPBOARD_DISABLE = "0";
    public static final String VALUE_CLIPBOARD_ENABLE = "1";
    private static ConcurrentHashMap<String, CopyOnWriteArrayList<ClipBoardPermissionResultCallBack>> clipBoardPermissionMap;
    private static CopyOnWriteArrayList<ClipBoardPermissionResultCallBack> clipBoardPermissionResultCallBacks;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static final ConcurrentHashMap<String, Boolean> dialogShowingStatus = new ConcurrentHashMap<>();
    private static boolean isDialogShowing = false;

    /* loaded from: classes5.dex */
    public static abstract class ClipBoardPermissionResultCallBack {
        public void onDenied() {
        }

        public void onGranted() {
        }
    }

    public static void handleClipBoardPermissionDialog(final Context context, final String str, final String str2, final String str3, final ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && handler != null && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    handler.post(new Runnable() { // from class: com.jingdong.common.permission.ClipBoardPermissionHelper.1
                        /* JADX WARN: Removed duplicated region for block: B:152:0x0197  */
                        /* JADX WARN: Removed duplicated region for block: B:154:0x01b9  */
                        /* JADX WARN: Removed duplicated region for block: B:157:0x020c  */
                        @Override // java.lang.Runnable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public void run() {
                            /*
                                Method dump skipped, instructions count: 584
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.permission.ClipBoardPermissionHelper.AnonymousClass1.run():void");
                        }
                    });
                    return;
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                    return;
                }
                return;
            }
        }
        EventBus.getDefault().post(new ClipBoardDialogShow(false));
    }

    public static void notifySameModuleNameCallBack(String str, boolean z) {
        ConcurrentHashMap<String, CopyOnWriteArrayList<ClipBoardPermissionResultCallBack>> concurrentHashMap = clipBoardPermissionMap;
        if (concurrentHashMap == null || concurrentHashMap.get(str) == null) {
            return;
        }
        CopyOnWriteArrayList<ClipBoardPermissionResultCallBack> copyOnWriteArrayList = clipBoardPermissionMap.get(str);
        if (OKLog.D) {
            OKLog.d(TAG, "notifySameModuleNameCallBack->callBacks=" + copyOnWriteArrayList);
        }
        for (int size = copyOnWriteArrayList.size() - 1; size >= 0; size--) {
            ClipBoardPermissionResultCallBack clipBoardPermissionResultCallBack = copyOnWriteArrayList.get(size);
            if (clipBoardPermissionResultCallBack != null) {
                if (z) {
                    clipBoardPermissionResultCallBack.onGranted();
                } else {
                    clipBoardPermissionResultCallBack.onDenied();
                }
            }
        }
    }
}
