package com.jdjr.risk.jdcn.common.permisson;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.jingdong.common.permission.PermissionHelper;

/* loaded from: classes18.dex */
public class FsRequestCameraPermissionUtils {

    /* loaded from: classes18.dex */
    public static abstract class PermissionResultCallBack {
        Bundle params;
        private boolean rejectNotAsk;

        public void onCanceled() {
        }

        public void onDenied() {
        }

        public void onGranted() {
        }

        public void onIgnored() {
        }

        public void onOpenSetting() {
        }
    }

    public static void onRequestPermissionsResult(Activity activity, int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        PermissionHelper.onRequestPermissionsResult(activity, i2, strArr, iArr);
    }

    public static void releasePermissionCallback() {
    }

    public static void requestPermission(Activity activity, Bundle bundle, String str, final PermissionResultCallBack permissionResultCallBack, String str2, String str3) {
        PermissionHelper.requestPermission(activity, PermissionHelper.generateBundle(bundle.getString("moduleName"), bundle.getString("className"), bundle.getString("methodName"), Boolean.valueOf(bundle.getBoolean(PermissionHelper.PARAM_USER_INITIATIVE, true)).booleanValue()), str, new PermissionHelper.PermissionResultCallBack() { // from class: com.jdjr.risk.jdcn.common.permisson.FsRequestCameraPermissionUtils.1
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public final void onCanceled() {
                super.onCanceled();
                if (PermissionResultCallBack.this != null) {
                    PermissionResultCallBack.this.onCanceled();
                }
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public final void onDenied() {
                super.onDenied();
                if (PermissionResultCallBack.this != null) {
                    PermissionResultCallBack.this.onDenied();
                }
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public final void onGranted() {
                super.onGranted();
                if (PermissionResultCallBack.this != null) {
                    PermissionResultCallBack.this.onGranted();
                }
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public final void onIgnored() {
                super.onIgnored();
                if (PermissionResultCallBack.this != null) {
                    PermissionResultCallBack.this.onIgnored();
                }
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public final void onOpenSetting() {
                super.onOpenSetting();
                if (PermissionResultCallBack.this != null) {
                    PermissionResultCallBack.this.onOpenSetting();
                }
            }
        }, str2, str3);
    }
}
