package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.R;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class DeepLinkScanHelper {
    public static final String HOST_SCAN_AR_CAPTURE = "scanarcapture";
    public static final String HOST_SCAN_CAPTURE = "scancapture";
    public static final String HOST_SCAN_PHOTOBUY = "scanphotobuy";
    public static final int REQ_M_TO_SCAN = 6667;
    public static final int SCAN = 1235;
    public static final int TAB_AR = 2;
    public static final int TAB_PHOTOBUY = 1;
    public static final int TAB_SCAN = 0;
    private static final HashMap<String, SoftReference<Activity>> taskMap = new HashMap<>();

    public static void clearAllActivity() {
        taskMap.clear();
    }

    public static void finish2Activity(String str, String str2) {
        HashMap<String, SoftReference<Activity>> hashMap = taskMap;
        if (hashMap == null || hashMap.size() <= 0) {
            return;
        }
        Iterator<Map.Entry<String, SoftReference<Activity>>> it = hashMap.entrySet().iterator();
        Activity activity = null;
        Activity activity2 = null;
        while (it.hasNext()) {
            SoftReference<Activity> value = it.next().getValue();
            if (value != null) {
                Activity activity3 = value.get();
                if (activity3 != null && str.equals(activity3.getClass().toString())) {
                    activity3.finish();
                    activity = activity3;
                }
                if (activity3 != null && str2.equals(activity3.getClass().toString())) {
                    activity3.finish();
                    activity2 = activity3;
                }
            }
        }
        if (activity != null) {
            removeActivity(activity);
        }
        if (activity2 != null) {
            removeActivity(activity2);
        }
    }

    public static void finishActivity(String str) {
        Activity activity;
        HashMap<String, SoftReference<Activity>> hashMap = taskMap;
        if (hashMap == null || hashMap.size() <= 0) {
            return;
        }
        Activity activity2 = null;
        Iterator<Map.Entry<String, SoftReference<Activity>>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            SoftReference<Activity> value = it.next().getValue();
            if (value != null && (activity = value.get()) != null && str.equals(activity.getClass().toString())) {
                activity.finish();
                activity2 = activity;
            }
        }
        if (activity2 != null) {
            removeActivity(activity2);
        }
    }

    public static void finishInterfaceActivity(IMyActivity iMyActivity) {
        if (iMyActivity == null || !(iMyActivity instanceof Context)) {
            return;
        }
        JumpUtil.finishInterfaceActivity((Context) iMyActivity);
    }

    public static void putActivity(Activity activity) {
        taskMap.put(activity.getClass().toString(), new SoftReference<>(activity));
    }

    public static void removeActivity(Activity activity) {
        taskMap.remove(activity.getClass().toString());
    }

    public static void startARCapterActivity(IMyActivity iMyActivity) {
        if (OKLog.D) {
            OKLog.d("MMM", "=> startARCapterActivity");
        }
        Bundle bundle = new Bundle();
        bundle.putInt("position", 2);
        startCaptureActivity(iMyActivity, bundle);
    }

    public static void startArCaptureActivity(IMyActivity iMyActivity, Bundle bundle) {
        startARCapterActivity(iMyActivity);
    }

    public static void startCaptureActivity(final IMyActivity iMyActivity, final Bundle bundle) {
        if (OKLog.D) {
            OKLog.d("MMM", "=> startCaptureActivity");
        }
        if (PermissionHelper.hasPermission(iMyActivity.getThisActivity(), "android.permission.CAMERA")) {
            if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                    return;
                }
                return;
            }
            DeepLinkDispatch.startActivityDirect(iMyActivity.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SCAN_CAPTURE).toString(), bundle);
            return;
        }
        PermissionHelper.requestPermission(iMyActivity.getThisActivity(), PermissionHelper.generateBundle("deeplink", DeepLinkScanHelper.class.getSimpleName(), "startCaptureActivity", true), "android.permission.CAMERA", new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkScanHelper.3
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                    if (OKLog.D) {
                        OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                        return;
                    }
                    return;
                }
                DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkScanHelper.HOST_SCAN_CAPTURE).toString(), bundle);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
            }
        }, "\u6444\u50cf\u5934", JdSdk.getInstance().getApplication().getString(R.string.permission_camera_full_msg));
    }

    public static void startCaptureActivity2(final Activity activity, final Bundle bundle) {
        if (OKLog.D) {
            OKLog.d("MMM", "=> startCaptureActivity");
        }
        if (PermissionHelper.hasPermission(activity, "android.permission.CAMERA")) {
            if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                    return;
                }
                return;
            }
            DeepLinkDispatch.startActivityDirect(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SCAN_CAPTURE).toString(), bundle);
            return;
        }
        PermissionHelper.requestPermission(activity, PermissionHelper.generateBundle("deeplink", DeepLinkScanHelper.class.getSimpleName(), "startCaptureActivity", true), "android.permission.CAMERA", new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkScanHelper.4
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                    if (OKLog.D) {
                        OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                        return;
                    }
                    return;
                }
                DeepLinkDispatch.startActivityDirect(activity, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkScanHelper.HOST_SCAN_CAPTURE).toString(), bundle);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
            }
        }, "\u6444\u50cf\u5934", JdSdk.getInstance().getApplication().getString(R.string.permission_camera_full_msg));
    }

    public static void startCaptureActivityForOpenApp(final IMyActivity iMyActivity, final Bundle bundle) {
        if (OKLog.D) {
            OKLog.d("MMM", "=> startCaptureActivity");
        }
        if (PermissionHelper.hasPermission(iMyActivity.getThisActivity(), "android.permission.CAMERA")) {
            if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                    return;
                }
                return;
            }
            DeepLinkDispatch.startActivityDirect(iMyActivity.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SCAN_CAPTURE).toString(), bundle);
            finishInterfaceActivity(iMyActivity);
            return;
        }
        PermissionHelper.requestPermission(iMyActivity.getThisActivity(), PermissionHelper.generateBundle("deeplink", DeepLinkScanHelper.class.getSimpleName(), "startCaptureActivity", true), "android.permission.CAMERA", new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkScanHelper.2
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> onCanceled");
                }
                DeepLinkScanHelper.finishInterfaceActivity(IMyActivity.this);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> onDenied");
                }
                DeepLinkScanHelper.finishInterfaceActivity(IMyActivity.this);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> onGranted");
                }
                if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                    if (OKLog.D) {
                        OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                        return;
                    }
                    return;
                }
                DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkScanHelper.HOST_SCAN_CAPTURE).toString(), bundle);
                DeepLinkScanHelper.finishInterfaceActivity(IMyActivity.this);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> onIgnored");
                }
                DeepLinkScanHelper.finishInterfaceActivity(IMyActivity.this);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> onOpenSetting");
                }
                DeepLinkScanHelper.finishInterfaceActivity(IMyActivity.this);
            }
        }, "\u6444\u50cf\u5934", JdSdk.getInstance().getApplication().getString(R.string.permission_camera_full_msg));
    }

    public static void startCaptureActivityForResult(final Activity activity, final Bundle bundle, final int i2, final boolean z) {
        if (OKLog.D) {
            OKLog.d("MMM", "=> startCaptureActivity");
        }
        if (PermissionHelper.hasPermission(activity, "android.permission.CAMERA")) {
            if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                    return;
                }
                return;
            }
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SCAN_CAPTURE).toString(), bundle, i2);
            return;
        }
        PermissionHelper.requestPermission(activity, PermissionHelper.generateBundle("deeplink", DeepLinkScanHelper.class.getSimpleName(), "startCaptureActivityForResult", true), "android.permission.CAMERA", new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkScanHelper.5
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
                if (!z || activity.isFinishing()) {
                    return;
                }
                activity.finish();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
                if (!z || activity.isFinishing()) {
                    return;
                }
                activity.finish();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                    if (OKLog.D) {
                        OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                        return;
                    }
                    return;
                }
                DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkScanHelper.HOST_SCAN_CAPTURE).toString(), bundle, i2);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
            }
        }, "\u6444\u50cf\u5934", JdSdk.getInstance().getApplication().getString(R.string.permission_camera_full_msg));
    }

    public static void startCaptureActivityForResultFromOrderScan(IMyActivity iMyActivity, Bundle bundle, int i2) {
        if (OKLog.D) {
            OKLog.d("MMM", "startCaptureActivityForResultFromOrderScan");
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("isFromOrderScan", 1);
        startCaptureActivityForResult(iMyActivity.getThisActivity(), bundle, i2, false);
    }

    public static void startCaptureActivityForResultFromSam(IMyActivity iMyActivity, int i2) {
        if (OKLog.D) {
            OKLog.d("MMM", "startCaptureActivityForResultFromSam");
        }
        Bundle bundle = new Bundle();
        bundle.putInt("isFromSam", 1);
        startCaptureActivityForResult(iMyActivity.getThisActivity(), bundle, i2, false);
    }

    public static void startCaptureActivityForWeixinScan(final IMyActivity iMyActivity, final Bundle bundle) {
        if (OKLog.D) {
            OKLog.d("MMM", "=> startCaptureActivity");
        }
        if (PermissionHelper.hasPermission(iMyActivity.getThisActivity(), "android.permission.CAMERA")) {
            if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                if (OKLog.D) {
                    OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                    return;
                }
                return;
            }
            DeepLinkDispatch.startActivityDirect(iMyActivity.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SCAN_CAPTURE).toString(), bundle);
            if (iMyActivity.getThisActivity().isFinishing()) {
                return;
            }
            iMyActivity.getThisActivity().finish();
            return;
        }
        PermissionHelper.requestPermission(iMyActivity.getThisActivity(), PermissionHelper.generateBundle("deeplink", DeepLinkScanHelper.class.getSimpleName(), "startCaptureActivity", true), "android.permission.CAMERA", new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkScanHelper.1
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
                if (IMyActivity.this.getThisActivity().isFinishing()) {
                    return;
                }
                IMyActivity.this.getThisActivity().finish();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
                    if (OKLog.D) {
                        OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                        return;
                    }
                    return;
                }
                DeepLinkDispatch.startActivityDirect(IMyActivity.this.getThisActivity(), new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkScanHelper.HOST_SCAN_CAPTURE).toString(), bundle);
                if (IMyActivity.this.getThisActivity().isFinishing()) {
                    return;
                }
                IMyActivity.this.getThisActivity().finish();
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
            }
        }, "\u6444\u50cf\u5934", JdSdk.getInstance().getApplication().getString(R.string.permission_camera_full_msg));
    }

    public static void startPhotoBuyActivity(IMyActivity iMyActivity) {
        if (OKLog.D) {
            OKLog.d("MMM", "=> startPhotoBuyActivity");
        }
        Bundle bundle = new Bundle();
        bundle.putInt("position", 1);
        startCaptureActivity(iMyActivity, bundle);
    }

    public static void startPhotoBuyResultActivity(Activity activity, Bundle bundle) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(13))) {
            if (OKLog.D) {
                OKLog.d("MMM", "=> MASK_AURA_SWITCH_SCAN close");
                return;
            }
            return;
        }
        DeepLinkDispatch.startActivityDirect(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SCAN_PHOTOBUY).toString(), bundle);
    }
}
