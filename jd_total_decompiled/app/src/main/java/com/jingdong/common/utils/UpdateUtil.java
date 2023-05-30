package com.jingdong.common.utils;

import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class UpdateUtil {
    private static final String CLASSMAME = "com.jingdong.app.mall.update.UpdateInitialization";
    private static final String TAG = "UpdateUtil";

    public static void checkMyJdGrayUpdate(IMyActivity iMyActivity) {
        try {
            Class.forName(CLASSMAME).getMethod("checkMyJdGrayUpdate", IMyActivity.class).invoke(null, iMyActivity);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void checkVersion(IMyActivity iMyActivity) {
        try {
            Class.forName(CLASSMAME).getMethod("checkVersion", IMyActivity.class).invoke(null, iMyActivity);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void dialogClean() {
        try {
            Class.forName(CLASSMAME).getMethod("cleanDialog", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void dialogShowInit(IDialogShow iDialogShow) {
        try {
            Class.forName(CLASSMAME).getMethod("checkDialogIsShowing", IDialogShow.class).invoke(null, iDialogShow);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static String getMyJdLayerImgUrl() {
        try {
            return (String) Class.forName(CLASSMAME).getMethod("getMyJdLayerImgUrl", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return "";
        }
    }

    public static String getRemoteApkVersion() {
        try {
            return (String) Class.forName(CLASSMAME).getMethod("getRemoteApkVersion", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return "";
        }
    }

    public static boolean isGrayWifiAutoDownload() {
        try {
            return ((Boolean) Class.forName(CLASSMAME).getMethod("isGrayWifiAutoDownload", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return false;
            }
            return false;
        }
    }

    public static void showMyJdGrayUpdateLayer(IMyActivity iMyActivity, IGrayUpdateLayerShow iGrayUpdateLayerShow) {
        try {
            Class.forName(CLASSMAME).getMethod("showMyJdGrayUpdateLayer", IMyActivity.class, IGrayUpdateLayerShow.class).invoke(null, iMyActivity, iGrayUpdateLayerShow);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static boolean showMyJdLayer() {
        try {
            return ((Boolean) Class.forName(CLASSMAME).getMethod("showMyJdLayer", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return false;
            }
            return false;
        }
    }

    public static boolean showMyJdRedPoint() {
        try {
            return ((Boolean) Class.forName(CLASSMAME).getMethod("showMyJdRedPoint", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return false;
            }
            return false;
        }
    }

    public static boolean showMyJdUserSettingsRedPoint() {
        try {
            return ((Boolean) Class.forName(CLASSMAME).getMethod("showMyJdUserSettingsRedPoint", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return false;
            }
            return false;
        }
    }

    public static void updateDialogShowInit(IDialogShow iDialogShow) {
        try {
            Class.forName(CLASSMAME).getMethod("checkUpdateDialogIsShowing", IDialogShow.class).invoke(null, iDialogShow);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }
}
