package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeDatePickerListener {
    void showDatePicker(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void showDatePicker2(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void showTimeWheelPicker(Activity activity, String str, String str2, JDCallback jDCallback, JDCallback jDCallback2);

    void showTimeWheelPicker2(Activity activity, String str, String str2, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void showWheelPicker(Activity activity, ArrayList arrayList, String str, JDCallback jDCallback, JDCallback jDCallback2);

    void showWheelPicker2(Activity activity, ArrayList arrayList, String str, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
