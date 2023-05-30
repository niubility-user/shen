package com.jingdong.common.utils.inter;

import android.os.SystemClock;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;

/* loaded from: classes6.dex */
public class JDOverseasUtil {
    private static final String KEY_OVERSEAS_AREA_CODE = "key_overseas_area_code";
    private static boolean sAlreadyGet = false;
    private static int sAreaId = 0;
    private static long sUpdateOverseasAreaTime = -1;

    public static boolean checkOverseasNeedUpdate(long j2) {
        long j3 = sUpdateOverseasAreaTime;
        return j3 >= 0 && j3 > j2;
    }

    public static int getCurrentOverseasArea() {
        if (sAlreadyGet) {
            return sAreaId;
        }
        updateValueFromSp();
        return sAreaId;
    }

    public static void updateCurrentOverseasArea(int i2) {
        int max = Math.max(i2, 0);
        if (max != sAreaId) {
            sAreaId = max;
            sUpdateOverseasAreaTime = SystemClock.elapsedRealtime();
            SharedPreferencesUtil.putInt(KEY_OVERSEAS_AREA_CODE, max);
            JDElderModeUtils.onElderOverseasAreaChanged(max);
            OverseasEvent.postOverseasChanged();
        }
    }

    private static void updateValueFromSp() {
        sAlreadyGet = true;
        sAreaId = Math.max(SharedPreferencesUtil.getInt(KEY_OVERSEAS_AREA_CODE, 0), 0);
    }
}
