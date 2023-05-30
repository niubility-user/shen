package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeMtaReportListener {
    void getJDV(JDCallback jDCallback, JDCallback jDCallback2);

    void removeUserProperty(ArrayList<Object> arrayList);

    void savePageInfoWithSKU(HashMap hashMap);

    void sendClickData(HashMap hashMap);

    void sendClickDataExtend(HashMap hashMap);

    void sendClickDataWithJsonParam(HashMap hashMap);

    void sendCommonData(HashMap hashMap);

    void sendCommonDataExtend(HashMap hashMap);

    void sendCommonDataWithExt(HashMap hashMap);

    void sendExposureData(HashMap hashMap);

    void sendExposureDataWithJsonParam(HashMap hashMap);

    void sendExposureExtend(HashMap hashMap);

    void sendOrderDataWithExt(HashMap hashMap);

    void sendOrderExtend(HashMap hashMap);

    void sendPVExtend(HashMap hashMap);

    void sendPvData(HashMap hashMap);

    void sendVirtualOrderData(HashMap hashMap);

    void setUserProperty(HashMap hashMap);
}
