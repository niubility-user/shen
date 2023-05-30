package com.jdcn.fido.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import cn.com.union.fido.common.FingerAuthenticatorInfo;
import cn.com.union.fido.db.AuthenticationManager;
import cn.com.union.fido.ui.FIDOUISDK;
import com.jdcn.fido.constant.StatusCode;
import com.jdcn.fido.sdk.IFidoCallback;
import jd.wjlogin_sdk.util.z;

/* loaded from: classes18.dex */
public class FidoServiceUtil {
    public static int fingerStatus(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 23) {
                return 101;
            }
            if (!FingerAuthenticatorInfo.manufacturerCheck()) {
                TrackerUtil.tracker(context, null, "", "getDeviceId_run_manufacturerCheck");
                return 401;
            }
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return 401;
            }
            if (packageManager.hasSystemFeature("android.hardware.fingerprint")) {
                if (context.checkSelfPermission("android.permission.USE_FINGERPRINT") != 0) {
                    return 103;
                }
                if (isNetworkAvailable(context)) {
                    FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint");
                    if (fingerprintManager == null) {
                        return 401;
                    }
                    if (fingerprintManager.isHardwareDetected()) {
                        TrackerUtil.tracker(context, null, "", "getDeviceId_run_003");
                        if (RootUtil.isRootSystem(context)) {
                            return 105;
                        }
                        TrackerUtil.tracker(context, null, "", "getDeviceId_run_004");
                        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
                        if (keyguardManager == null) {
                            return 401;
                        }
                        if (keyguardManager.isKeyguardSecure()) {
                            return !fingerprintManager.hasEnrolledFingerprints() ? 107 : 0;
                        }
                        return 106;
                    }
                    return 102;
                }
                return 104;
            }
            return 102;
        } catch (Throwable unused) {
            return 401;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static short getStatusCode(int i2) {
        if (i2 != 0) {
            if (i2 != 3) {
                if (i2 != 5) {
                    if (i2 != 255) {
                        if (i2 != 7) {
                            if (i2 != 8) {
                                if (i2 != 9) {
                                    switch (i2) {
                                        case 16:
                                            return StatusCode.STATUS_VERIFY_TOO_MUCH;
                                        case 17:
                                            break;
                                        case 18:
                                            return StatusCode.STATUS_FINGERS_CHANGER;
                                        case 19:
                                            return StatusCode.STATUS_VERIFY_THREE_TIME;
                                        default:
                                            switch (i2) {
                                                case 22:
                                                    return StatusCode.STATUS_CHANGE_MODLE;
                                                case 23:
                                                    return StatusCode.STATUS_KEYHANDLE_ERROR;
                                                case 24:
                                                    return StatusCode.STATUS_USER_INTERRUPTED;
                                                default:
                                                    switch (i2) {
                                                        case 32:
                                                            return z.Q;
                                                        case 33:
                                                            return z.R;
                                                        case 34:
                                                            break;
                                                        case 35:
                                                            return (short) 106;
                                                        case 36:
                                                            return (short) 107;
                                                        default:
                                                            return (short) 401;
                                                    }
                                            }
                                    }
                                    return (short) 102;
                                }
                                return StatusCode.STATUS_FACETID_NULL;
                            }
                            return StatusCode.STATUS_AUTH_FAILED;
                        }
                        return StatusCode.STATUS_UNREGISERED_FACED_ID;
                    }
                    return (short) 401;
                }
                return StatusCode.STATUS_NO_AUTHENTICATOR;
            }
            return StatusCode.STATUS_USER_CANCELLED;
        }
        return (short) 0;
    }

    public static String getUserName(Context context, Bundle bundle) {
        String string = bundle.getString("deviceId");
        return TextUtils.isEmpty(string) ? FingerDeviceIdManger.getOrGenerateDeviceId(context) : string;
    }

    public static void handleBundle(Bundle bundle, IFidoCallback iFidoCallback) {
        if (bundle.containsKey("normalHintText")) {
            FIDOUISDK.setNormalHintText(bundle.getString("normalHintText"));
        }
        if (bundle.containsKey("normalHintColor")) {
            FIDOUISDK.setNormalHintColor(bundle.getInt("normalHintColor"));
        }
        if (bundle.containsKey("btnBottomText")) {
            FIDOUISDK.setBtnBottomText(bundle.getString("btnBottomText"));
        }
        if (bundle.containsKey("btnBottomColor")) {
            FIDOUISDK.setBtnBottomColor(bundle.getInt("btnBottomColor"));
        }
        if (bundle.containsKey("abnormalHintText")) {
            FIDOUISDK.setAbnormalHintText(bundle.getString("abnormalHintText"));
        }
        if (bundle.containsKey("abnormalHintColor")) {
            FIDOUISDK.setAbnormalHintColor(bundle.getInt("abnormalHintColor"));
        }
        if (bundle.containsKey("btnLeftText")) {
            FIDOUISDK.setBtnLeftText(bundle.getString("btnLeftText"));
        }
        if (bundle.containsKey("btnLeftColor")) {
            FIDOUISDK.setBtnLeftColor(bundle.getInt("btnLeftColor"));
        }
        if (bundle.containsKey("btnRinghtText")) {
            FIDOUISDK.setBtnRinghtText(bundle.getString("btnRinghtText"));
        }
        if (bundle.containsKey("btnRinghtColor")) {
            FIDOUISDK.setBtnRinghtColor(bundle.getInt("btnRinghtColor"));
        }
        if (bundle.containsKey("hasOtherAuthMode")) {
            FIDOUISDK.setHasOtherAuthMode(bundle.getString("hasOtherAuthMode"));
        }
        if (iFidoCallback != null) {
            FIDOUISDK.setCallback(iFidoCallback);
        }
    }

    public static int hasKeyEntitByUserName(Activity activity, String str) {
        if (activity != null) {
            return new AuthenticationManager(activity).getCountByUserName(str);
        }
        return -1;
    }

    private static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            return activeNetworkInfo.isConnected();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int toStatusCode(int i2) {
        switch (i2) {
            case 16:
                return 0;
            case 17:
                return 201;
            case 18:
                return 300;
            case 19:
                return 302;
            case 20:
            case 22:
            case 23:
            default:
                return 401;
            case 21:
                return 211;
            case 24:
                return 303;
            case 25:
                return 301;
            case 26:
                return 101;
            case 27:
                return 103;
            case 28:
                return 102;
            case 29:
                return 106;
            case 30:
                return 107;
        }
    }
}
