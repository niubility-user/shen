package com.jingdong.common.utils;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class DexAsyncUtil {
    public static final String ACTION_FINISH_SERVER_CONFIG = "ACTION_FINISH_SERVER_CONFIG";
    public static final String ACTION_INIT_MODULE = "ACTION_INIT_MODULE";
    public static final String JDHomeFragmentSimpleName = "JDHomeFragment";
    public static final String TAG = "async_dex";
    public static boolean needResetNavigationConfig;

    public static Intent getMainFrameActivityIntent(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.jingdong.app.mall.MainFrameActivity");
        return intent;
    }
}
