package com.laser.utils.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes13.dex */
public final class PermissionUtils {
    private static final List<String> PERMISSIONS = getPermissions();
    private static PermissionUtils sInstance;
    private FullCallback mFullCallback;
    private OnRationaleListener mOnRationaleListener;
    private Set<String> mPermissions;
    private List<String> mPermissionsDenied;
    private List<String> mPermissionsDeniedForever;
    private List<String> mPermissionsGranted;
    private List<String> mPermissionsRequest;
    private SimpleCallback mSimpleCallback;
    private ThemeCallback mThemeCallback;

    /* loaded from: classes13.dex */
    public interface FullCallback {
        void onDenied(List<String> list, List<String> list2);

        void onGranted(List<String> list);
    }

    /* loaded from: classes13.dex */
    public interface OnRationaleListener {

        /* loaded from: classes13.dex */
        public interface ShouldRequest {
            void again(boolean z);
        }

        void rationale(ShouldRequest shouldRequest);
    }

    @RequiresApi(api = 23)
    /* loaded from: classes13.dex */
    public static class PermissionActivity extends Activity {
        public static void start(Context context) {
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.addFlags(268435456);
            context.startActivity(intent);
        }

        @Override // android.app.Activity, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            finish();
            return true;
        }

        @Override // android.app.Activity
        protected void onCreate(@Nullable Bundle bundle) {
            getWindow().addFlags(262160);
            if (PermissionUtils.sInstance != null) {
                if (PermissionUtils.sInstance.mThemeCallback != null) {
                    PermissionUtils.sInstance.mThemeCallback.onActivityCreate(this);
                }
                super.onCreate(bundle);
                if (!PermissionUtils.sInstance.rationale(this)) {
                    if (PermissionUtils.sInstance.mPermissionsRequest != null) {
                        int size = PermissionUtils.sInstance.mPermissionsRequest.size();
                        if (size > 0) {
                            requestPermissions((String[]) PermissionUtils.sInstance.mPermissionsRequest.toArray(new String[size]), 1);
                            return;
                        } else {
                            finish();
                            return;
                        }
                    }
                    return;
                }
                finish();
                return;
            }
            super.onCreate(bundle);
            finish();
        }

        @Override // android.app.Activity
        public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
            PermissionUtils.sInstance.onRequestPermissionsResult(this);
            finish();
        }
    }

    /* loaded from: classes13.dex */
    public interface SimpleCallback {
        void onDenied();

        void onGranted();
    }

    /* loaded from: classes13.dex */
    public interface ThemeCallback {
        void onActivityCreate(Activity activity);
    }

    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }

    private void getPermissionsStatus(Activity activity) {
        for (String str : this.mPermissionsRequest) {
            if (isGranted(str)) {
                this.mPermissionsGranted.add(str);
            } else {
                this.mPermissionsDenied.add(str);
                if (!activity.shouldShowRequestPermissionRationale(str)) {
                    this.mPermissionsDeniedForever.add(str);
                }
            }
        }
    }

    public static boolean isGranted(String... strArr) {
        for (String str : strArr) {
            if (!isGranted(str)) {
                return false;
            }
        }
        return true;
    }

    public static void launchAppDetailsSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        Utils.getApp().startActivity(intent.addFlags(268435456));
    }

    public void onRequestPermissionsResult(Activity activity) {
        getPermissionsStatus(activity);
        requestCallback();
    }

    public void requestCallback() {
        if (this.mSimpleCallback != null) {
            if (this.mPermissionsRequest.size() != 0 && this.mPermissions.size() != this.mPermissionsGranted.size()) {
                if (!this.mPermissionsDenied.isEmpty()) {
                    this.mSimpleCallback.onDenied();
                }
            } else {
                this.mSimpleCallback.onGranted();
            }
            this.mSimpleCallback = null;
        }
        if (this.mFullCallback != null) {
            if (this.mPermissionsRequest.size() != 0 && this.mPermissions.size() != this.mPermissionsGranted.size()) {
                if (!this.mPermissionsDenied.isEmpty()) {
                    this.mFullCallback.onDenied(this.mPermissionsDeniedForever, this.mPermissionsDenied);
                }
            } else {
                this.mFullCallback.onGranted(this.mPermissionsGranted);
            }
            this.mFullCallback = null;
        }
        this.mOnRationaleListener = null;
        this.mThemeCallback = null;
    }

    @RequiresApi(api = 23)
    public void startPermissionActivity() {
        this.mPermissionsDenied = new ArrayList();
        this.mPermissionsDeniedForever = new ArrayList();
        PermissionActivity.start(Utils.getApp());
    }

    public PermissionUtils callback(SimpleCallback simpleCallback) {
        this.mSimpleCallback = simpleCallback;
        return this;
    }

    public PermissionUtils rationale(OnRationaleListener onRationaleListener) {
        this.mOnRationaleListener = onRationaleListener;
        return this;
    }

    public void request() {
        this.mPermissionsGranted = new ArrayList();
        this.mPermissionsRequest = new ArrayList();
        if (Build.VERSION.SDK_INT < 23) {
            this.mPermissionsGranted.addAll(this.mPermissions);
            requestCallback();
            return;
        }
        for (String str : this.mPermissions) {
            if (isGranted(str)) {
                this.mPermissionsGranted.add(str);
            } else {
                this.mPermissionsRequest.add(str);
            }
        }
        if (this.mPermissionsRequest.isEmpty()) {
            requestCallback();
        } else {
            startPermissionActivity();
        }
    }

    public PermissionUtils theme(ThemeCallback themeCallback) {
        this.mThemeCallback = themeCallback;
        return this;
    }

    public static List<String> getPermissions(String str) {
        try {
            return Arrays.asList(Utils.getApp().getPackageManager().getPackageInfo(str, 4096).requestedPermissions);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return Collections.emptyList();
        }
    }

    @RequiresApi(api = 23)
    public boolean rationale(Activity activity) {
        boolean z = false;
        if (this.mOnRationaleListener != null) {
            Iterator<String> it = this.mPermissionsRequest.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (activity.shouldShowRequestPermissionRationale(it.next())) {
                    getPermissionsStatus(activity);
                    this.mOnRationaleListener.rationale(new OnRationaleListener.ShouldRequest() { // from class: com.laser.utils.common.PermissionUtils.1
                        {
                            PermissionUtils.this = this;
                        }

                        @Override // com.laser.utils.common.PermissionUtils.OnRationaleListener.ShouldRequest
                        public void again(boolean z2) {
                            if (z2) {
                                PermissionUtils.this.startPermissionActivity();
                            } else {
                                PermissionUtils.this.requestCallback();
                            }
                        }
                    });
                    z = true;
                    break;
                }
            }
            this.mOnRationaleListener = null;
        }
        return z;
    }

    public PermissionUtils callback(FullCallback fullCallback) {
        this.mFullCallback = fullCallback;
        return this;
    }

    private static boolean isGranted(String str) {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(Utils.getApp(), str) == 0;
    }
}
