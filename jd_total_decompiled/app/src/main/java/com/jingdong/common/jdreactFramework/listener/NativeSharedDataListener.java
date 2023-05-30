package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeSharedDataListener {
    void addSharedData(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getSharedData(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void putSharedData(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void queryAllSharedData(JDCallback jDCallback, JDCallback jDCallback2);

    void querySharedDataByName(String str, JDCallback jDCallback, JDCallback jDCallback2);
}
