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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getRotationDegree(int r5, int r6) {
        /*
            java.lang.String r0 = "Nexus5X"
            boolean r0 = equalsModel(r0)
            r1 = 0
            r2 = 270(0x10e, float:3.78E-43)
            r3 = 90
            r4 = 180(0xb4, float:2.52E-43)
            if (r0 == 0) goto L1d
            if (r6 != 0) goto L1d
            if (r5 != 0) goto L14
            goto L2a
        L14:
            if (r5 != r4) goto L17
            goto L27
        L17:
            if (r5 != r3) goto L1a
            goto L31
        L1a:
            if (r5 != r2) goto L31
            goto L2f
        L1d:
            r0 = 1
            if (r5 != 0) goto L23
            if (r6 != r0) goto L27
            goto L2a
        L23:
            if (r5 != r4) goto L2d
            if (r6 != r0) goto L2a
        L27:
            r1 = 90
            goto L31
        L2a:
            r1 = 270(0x10e, float:3.78E-43)
            goto L31
        L2d:
            if (r5 != r3) goto L31
        L2f:
            r1 = 180(0xb4, float:2.52E-43)
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.rec.MantoVideoUtil.getRotationDegree(int, int):int");
    }

    public static int getVideoBitRate(int i2, int i3) {
        return (i2 >= 1920 || i3 >= 1920) ? 4194304 : 2097152;
    }
}
