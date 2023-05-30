package com.jingdong.app.mall.im.business;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.permission.FloatPermissionManager;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.permission.UphoneCallback;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.callback.FloatPermissionListener;
import com.jingdong.service.callback.PermissionListener;
import com.jingdong.service.impl.IMPermission;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class u extends IMPermission {
    private static final String a = "u";

    /* loaded from: classes4.dex */
    class a extends PermissionHelper.PermissionResultCallBack {
        final /* synthetic */ PermissionListener a;

        a(u uVar, PermissionListener permissionListener) {
            this.a = permissionListener;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            super.onCanceled();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onCanceled();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onGranted();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            super.onIgnored();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onIgnored();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onOpenSetting() {
            super.onOpenSetting();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onOpenSetting();
            }
        }
    }

    /* loaded from: classes4.dex */
    class b extends PermissionHelper.PermissionResultCallBack {
        final /* synthetic */ PermissionListener a;

        b(u uVar, PermissionListener permissionListener) {
            this.a = permissionListener;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            super.onCanceled();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onCanceled();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onGranted();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            super.onIgnored();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onIgnored();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onOpenSetting() {
            super.onOpenSetting();
            PermissionListener permissionListener = this.a;
            if (permissionListener != null) {
                permissionListener.onOpenSetting();
            }
        }
    }

    /* loaded from: classes4.dex */
    class c implements UphoneCallback {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FloatPermissionListener f11138g;

        c(u uVar, FloatPermissionListener floatPermissionListener) {
            this.f11138g = floatPermissionListener;
        }

        @Override // com.jingdong.common.permission.UphoneCallback
        public void invoke(boolean z, String str) {
            FloatPermissionListener floatPermissionListener;
            if (!z || (floatPermissionListener = this.f11138g) == null) {
                return;
            }
            floatPermissionListener.invoke(z, str);
        }

        @Override // com.jingdong.common.permission.UphoneCallback
        public void onIgnored() {
        }
    }

    @Override // com.jingdong.service.impl.IMPermission, com.jingdong.service.service.PermissionService
    public boolean applyFloatWindow(Context context, String str, Bundle bundle, FloatPermissionListener floatPermissionListener) {
        FloatPermissionManager.getInstance().applyFloatWindow(context, str, bundle, new c(this, floatPermissionListener));
        return false;
    }

    @Override // com.jingdong.service.impl.IMPermission, com.jingdong.service.service.PermissionService
    public Bundle generateBundle(HashMap hashMap) {
        StringBuilder sb = new StringBuilder();
        String str = a;
        sb.append(str);
        sb.append("--- generateBundle");
        OKLog.d("bundleicssdkservice", sb.toString());
        if (hashMap == null) {
            return null;
        }
        String str2 = (String) hashMap.get("moduleName");
        String str3 = (String) hashMap.get("className");
        String str4 = (String) hashMap.get("methodName");
        boolean booleanValue = ((Boolean) hashMap.get(PermissionHelper.PARAM_USER_INITIATIVE)).booleanValue();
        OKLog.d("bundleicssdkservice", str + "--- generateBundle --- moduleName: " + str2 + " className: " + str3 + " methodName: " + str4 + " isInitiative: " + booleanValue);
        return PermissionHelper.generateBundle(str2, str3, str4, booleanValue);
    }

    @Override // com.jingdong.service.impl.IMPermission, com.jingdong.service.service.PermissionService
    public boolean hasPermission(Activity activity, String str) {
        boolean hasPermission = PermissionHelper.hasPermission(activity, str);
        OKLog.d("bundleicssdkservice", a + "--- hasPermission :" + hasPermission);
        return hasPermission;
    }

    @Override // com.jingdong.service.impl.IMPermission, com.jingdong.service.service.PermissionService
    public boolean permissionSwitch() {
        OKLog.d("bundleicssdkservice", a + "--- permissionSwitch");
        return true;
    }

    @Override // com.jingdong.service.impl.IMPermission, com.jingdong.service.service.PermissionService
    public void requestPermission(Activity activity, Bundle bundle, String str, PermissionListener permissionListener, String str2, String str3) {
        OKLog.d("bundleicssdkservice", a + "--- requestPermission");
        PermissionHelper.requestPermission(activity, bundle, str, new a(this, permissionListener), str2, str3);
    }

    @Override // com.jingdong.service.impl.IMPermission, com.jingdong.service.service.PermissionService
    public boolean hasPermission(Activity activity, List<String> list) {
        boolean hasPermission = PermissionHelper.hasPermission(activity, list);
        OKLog.d("bundleicssdkservice", a + "--- hasPermission :" + hasPermission);
        return hasPermission;
    }

    @Override // com.jingdong.service.impl.IMPermission, com.jingdong.service.service.PermissionService
    public void requestPermission(Activity activity, Bundle bundle, List<String> list, PermissionListener permissionListener, List<String> list2, List<String> list3) {
        OKLog.d("bundleicssdkservice", a + "--- requestPermission");
        PermissionHelper.requestPermission(activity, bundle, list, new b(this, permissionListener), list2, list3);
    }
}
