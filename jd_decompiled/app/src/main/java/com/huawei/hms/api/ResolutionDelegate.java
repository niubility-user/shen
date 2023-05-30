package com.huawei.hms.api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.activity.internal.BusResponseCallback;
import com.huawei.hms.activity.internal.ForegroundBusResponseMgr;
import com.huawei.hms.adapter.internal.CommonCode;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IntentUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class ResolutionDelegate implements IBridgeActivityDelegate {
    public static final String CALLBACK_METHOD = "CALLBACK_METHOD";
    private static final int REQUEST_CODE = 1002;
    private static final String TAG = "ResolutionDelegate";
    private WeakReference<Activity> mThisWeakRef;

    private void finishBridgeActivity() {
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.finish();
    }

    private Activity getActivity() {
        WeakReference<Activity> weakReference = this.mThisWeakRef;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    private BusResponseCallback getResponseCallback(String str) {
        return ForegroundBusResponseMgr.getInstance().get(str);
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 1002;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        Bundle bundle;
        this.mThisWeakRef = new WeakReference<>(activity);
        try {
            bundle = activity.getIntent().getExtras();
        } catch (Exception e2) {
            HMSLog.e(TAG, "getExtras exception:" + e2.getMessage());
            bundle = null;
        }
        if (bundle != null) {
            activity.startActivityForResult(IntentUtil.modifyIntentBehaviorsSafe((Intent) bundle.getParcelable(CommonCode.MapKey.HAS_RESOLUTION)), 1002);
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        this.mThisWeakRef = null;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i2, int i3, Intent intent) {
        if (i2 != getRequestCode()) {
            return false;
        }
        BusResponseCallback responseCallback = getResponseCallback(CALLBACK_METHOD);
        int isHuaweiMobileServicesAvailable = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this.mThisWeakRef.get(), 30000000);
        if (i3 == -1 && isHuaweiMobileServicesAvailable == 0) {
            HMSLog.i(TAG, "Make service available success.");
        } else {
            responseCallback.innerError(this.mThisWeakRef.get(), i3, "Make service available failed.");
        }
        finishBridgeActivity();
        return true;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i2, KeyEvent keyEvent) {
    }
}
