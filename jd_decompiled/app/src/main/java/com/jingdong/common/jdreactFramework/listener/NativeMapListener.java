package com.jingdong.common.jdreactFramework.listener;

import android.content.Context;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeMapListener {
    void calculateDistance(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void takeSnapshot(int i2, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, Context context);
}
