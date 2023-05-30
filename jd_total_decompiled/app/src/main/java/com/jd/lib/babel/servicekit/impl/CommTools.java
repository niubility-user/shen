package com.jd.lib.babel.servicekit.impl;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.uimanager.ThemedReactContext;
import com.jingdong.common.BaseFrameUtil;

/* loaded from: classes13.dex */
public class CommTools {
    public static Activity getActivity(Context context) {
        Activity currentActivity;
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (!(context instanceof ThemedReactContext) || (currentActivity = ((ThemedReactContext) context).getCurrentActivity()) == null) {
            if (BaseFrameUtil.getInstance().getCurrentMyActivity() == null) {
                return null;
            }
            return (Activity) BaseFrameUtil.getInstance().getCurrentMyActivity();
        }
        return currentActivity;
    }
}
