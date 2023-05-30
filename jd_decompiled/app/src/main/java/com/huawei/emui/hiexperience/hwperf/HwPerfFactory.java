package com.huawei.emui.hiexperience.hwperf;

import android.content.Context;
import com.huawei.emui.hiexperience.hwperf.utils.HwPerfLog;

/* loaded from: classes12.dex */
public class HwPerfFactory {
    public static final int FEATURE_FLINGER_VELICITY = 1;
    public static final int FEATURE_IMAGE_FADE = 2;
    public static final int FEATURE_LAST_ID = 6;
    public static final int FEATURE_LIST_PRELOAD = 5;
    public static final int FEATURE_POOL_SIZE = 4;
    public static final int FEATURE_THUMB_IMAGE = 3;
    public static HwPerfFactory mFactory;
    public Context mContext = null;

    public static final HwPerfFactory getInstance(Context context) {
        HwPerfLog.i("API: HwPerfFactory getInstance");
        return null;
    }

    public HwPerfBase createFeature(int i2) {
        HwPerfLog.i("API: HwPerfFactory createFeature");
        return null;
    }
}
