package com.jingdong.manto.jsapi.refact.rec;

import android.content.Context;
import android.graphics.Color;
import android.media.CamcorderProfile;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes15.dex */
class MantoVideoUtil {
    MantoVideoUtil() {
    }

    public static boolean equalsModel(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String deviceModel = BaseInfo.getDeviceModel();
        if (deviceModel != null) {
            try {
                if (deviceModel.length() > 25) {
                    deviceModel = deviceModel.substring(0, 25);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (deviceModel == null) {
            deviceModel = "";
        }
        return TextUtils.equals(deviceModel, str);
    }

    public static int getDarkColor(Context context, int i2, float f2) {
        return Color.argb(Color.alpha(i2), Math.min(Math.round(Color.red(i2) * f2), 255), Math.min(Math.round(Color.green(i2) * f2), 255), Math.min(Math.round(Color.blue(i2) * f2), 255));
    }

    public static int getFrameRate() {
        CamcorderProfile camcorderProfile;
        if (CamcorderProfile.hasProfile(0, 5)) {
            camcorderProfile = CamcorderProfile.get(0, 5);
        } else {
            int i2 = 4;
            if (!CamcorderProfile.hasProfile(0, 4)) {
                i2 = 7;
                if (!CamcorderProfile.hasProfile(0, 7)) {
                    camcorderProfile = CamcorderProfile.get(0, 0);
                }
            }
            camcorderProfile = CamcorderProfile.get(i2);
        }
        if (camcorderProfile != null) {
            MantoLog.d("better", "videoFrameRate:" + camcorderProfile.videoFrameRate);
            return camcorderProfile.videoFrameRate;
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0027 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002a A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x002f A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int getRotationDegree(int i2, int i3) {
        if (equalsModel("Nexus5X") && i3 == 0) {
            if (i2 != 0) {
                if (i2 != 180) {
                    if (i2 == 90 || i2 != 270) {
                        return 0;
                    }
                }
            }
        } else if (i2 == 0) {
            return i3 == 1 ? 270 : 90;
        } else if (i2 != 180) {
            return i2 == 90 ? 180 : 0;
        } else if (i3 == 1) {
        }
    }

    public static int getVideoBitRate(int i2, int i3) {
        return (i2 >= 1920 || i3 >= 1920) ? 4194304 : 2097152;
    }
}
