package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Intent;
import com.jingdong.common.ui.JdAreaCodeSelectActivity;

/* loaded from: classes6.dex */
public class JDAreaCodeSelectUtil {
    public static final int AREA_CODE = 1315;

    /* loaded from: classes6.dex */
    public interface AreaCodeSelectListener {
        void onSelect(String str);
    }

    public static void showAreaCodeSelectView(Activity activity, AreaCodeSelectListener areaCodeSelectListener) {
        if (activity == null || areaCodeSelectListener == null) {
            return;
        }
        startAreaCodeActivityForCallback(activity, areaCodeSelectListener);
    }

    public static void startAreaCodeActivityForCallback(Activity activity, AreaCodeSelectListener areaCodeSelectListener) {
        AreaCodeSelectListenerParcel areaCodeSelectListenerParcel = new AreaCodeSelectListenerParcel(new AreaCodeSelectListenerBinder(areaCodeSelectListener));
        Intent intent = new Intent(activity, JdAreaCodeSelectActivity.class);
        intent.putExtra("parcel", areaCodeSelectListenerParcel);
        activity.startActivityForResult(intent, 1315);
    }
}
