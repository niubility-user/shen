package com.jd.manto.d;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.manto.map.Tools;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.permission.entity.SceneStatus;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.List;

/* loaded from: classes17.dex */
public class u implements IPermission {

    /* renamed from: g  reason: collision with root package name */
    static final Bundle f6670g = PermissionHelper.generateBundle("manto", "manto", "impl", true);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a extends PermissionHelper.PermissionSceneCallback {
        final /* synthetic */ IPermission.PermissionCallBack a;

        a(u uVar, IPermission.PermissionCallBack permissionCallBack) {
            this.a = permissionCallBack;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onAgree() {
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onGranted();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            super.onCanceled();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onDisagree() {
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onGranted();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            super.onIgnored();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onTimeLimited() {
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }
    }

    /* loaded from: classes17.dex */
    class b extends PermissionHelper.PermissionResultCallBack {
        final /* synthetic */ IPermission.PermissionCallBack a;

        b(u uVar, IPermission.PermissionCallBack permissionCallBack) {
            this.a = permissionCallBack;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onGranted();
            }
        }
    }

    /* loaded from: classes17.dex */
    class c extends PermissionHelper.PermissionResultCallBack {
        final /* synthetic */ IPermission.PermissionCallBack a;

        c(u uVar, IPermission.PermissionCallBack permissionCallBack) {
            this.a = permissionCallBack;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            super.onCanceled();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onGranted();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            super.onIgnored();
            IPermission.PermissionCallBack permissionCallBack = this.a;
            if (permissionCallBack != null) {
                permissionCallBack.onDenied();
            }
        }
    }

    @Override // com.jingdong.manto.sdk.api.IPermission
    public String getAppNameAsPrefix() {
        return StringUtil.app_name;
    }

    @Override // com.jingdong.manto.sdk.api.IPermission
    public boolean hasLocationPermissionWithScene(String str, String str2) {
        return PermissionHelper.hasLocationPermissionWithScene(str, str2);
    }

    @Override // com.jingdong.manto.sdk.api.IPermission
    public boolean hasPermission(String str) {
        return PermissionHelper.hasPermission(f6670g, str);
    }

    @Override // com.jingdong.manto.sdk.api.IPermission
    public boolean hasPermissions(String[] strArr) {
        return PermissionHelper.hasGrantedPermissions(null, f6670g, strArr, false, null);
    }

    @Override // com.jingdong.manto.sdk.api.IPermission
    public void onRequestPermissionsResult(Activity activity, int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        PermissionHelper.onRequestPermissionsResult(activity, i2, strArr, iArr);
    }

    @Override // com.jingdong.manto.sdk.api.IPermission
    public void requestLocationPermissionWithScene(Activity activity, IPermission.PermissionCallBack permissionCallBack, String str, String str2, String str3) {
        SceneStatus hasLocationPermissionWithSceneV2 = PermissionHelper.hasLocationPermissionWithSceneV2(str, str2);
        if (hasLocationPermissionWithSceneV2 == SceneStatus.HAS_ALL_PERMISSION) {
            permissionCallBack.onGranted();
        } else if (hasLocationPermissionWithSceneV2 == SceneStatus.NO_SCENE_PERMISSION) {
            LBSSceneSwitchHelper.saveLbsSceneSwitch("locService", true);
            permissionCallBack.onGranted();
        } else {
            PermissionHelper.manualRequestLocationPermissionWithScene(activity, new a(this, permissionCallBack), str, str2, str3);
        }
    }

    @Override // com.jingdong.manto.sdk.api.IPermission
    public void requestPermission(Activity activity, String str, String str2, String str3, IPermission.PermissionCallBack permissionCallBack) {
        if (!TextUtils.equals(str, "android.permission.ACCESS_FINE_LOCATION") && !TextUtils.equals(str, "android.permission.ACCESS_COARSE_LOCATION")) {
            PermissionHelper.requestPermission(activity, f6670g, str, new b(this, permissionCallBack), str2, str3);
        } else {
            requestLocationPermissionWithScene(activity, permissionCallBack, "locService", Tools.JD_LOCATION_ID, str3);
        }
    }

    @Override // com.jingdong.manto.sdk.api.IPermission
    public void requestPermissions(Activity activity, List<String> list, List<String> list2, List<String> list3, IPermission.PermissionCallBack permissionCallBack) {
        PermissionHelper.requestPermission(activity, f6670g, list, new c(this, permissionCallBack), list2, list3);
    }
}
